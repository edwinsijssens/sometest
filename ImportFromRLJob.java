package com.spr.flux.methods.jobs.imports.importFromRL;

import java.io.File;
import java.io.FileFilter;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.client.IDfWorkitem;
import com.documentum.fc.common.DfException;
import com.documentum.mthdservlet.IDmMethod;
import com.spr.flux.common.dm.DMSession;
import com.spr.flux.common.dm.util.SessionHelper;
import com.spr.flux.common.exportToRL.ReferenceLinkingConfigurationObject;
import com.spr.flux.common.log.Logger;
import com.spr.flux.common.util.DateUtil;
import com.spr.flux.common.util.FileHelper;
import com.spr.flux.common.util.other.TheAppBase;
import com.spr.flux.methods.jobs.AbstractJob;
import com.spr.flux.methods.utilities.MethodToMainAdaptor;

/**
 * The job that imports data coming back from reference linking.
 *
 * @author Konstantin Petrosov
 * @author Surender Kumar, Mikhail Chirkov (copied from BWF and updated)
 */
public class ImportFromRLJob extends AbstractJob implements IDmMethod {

    /** Session manager set up for performer of 'Waiting For RL' task. */
    private IDfSessionManager performerSessionManager = null;

    /** Session for performer of 'Waiting For RL' task. */
    private DMSession performerSession = null;

    /** The session for the job user. */
    private IDfSession session = null;

    /** Configuration object. */
    private ReferenceLinkingConfigurationObject config;

    /**
     * Application main.
     *
     * @param args
     *            Parameters.
     * @throws Exception
     *             on job error
     */
    public static void main(final String[] args) throws Exception {
        TheAppBase.setUpTestConsole("com.spr", Level.ALL, true);
        final ImportFromRLJob job = new ImportFromRLJob();
        job.jobMain(args);
    }

    /**
     * Iterates over all files inside the configured directory and tries to find the appropriate
     * task.
     *
     * @throws Exception
     *             In case of errors
     */
    @Override
    protected void doWork() throws Exception {
        final SessionHelper sessionHelper = newSessionHelper();
        try {
            session = sessionHelper.getLocalSession();
            performerSession = newPerformerSession();
            performWork();
        } finally {
            releasePerformerSession();
            sessionHelper.releaseLocalSession();
        }
    }

    /**
     * Gets the configuration object.
     *
     * @return the config object
     * @throws DfException
     *             on documentum error
     */
    protected final ReferenceLinkingConfigurationObject getConfigurationObject() throws DfException {
        if (config == null) {
            config = new ReferenceLinkingConfigurationObject(getSession());
        }
        return config;
    }

    /**
     * @return the session
     */
    protected IDfSession getSession() {
        return this.session;
    }

    /**
     * @return the performerSession
     */
    protected DMSession getPerformerSession() {
        return this.performerSession;
    }

    /**
     * Login with user name of performer of waiting for RL task.
     *
     * @return new performer session.
     * @throws DfException
     *             -- Server error.
     */
    private DMSession newPerformerSession() throws DfException {
        if (performerSessionManager == null) {
            LOGGER.debug("Initial login code called.");
            final String user = getConfigurationObject().getPerformerName();
            final String password = getConfigurationObject().getPerformerPassword();
            performerSessionManager = SessionHelper.newManager(getDocbaseName(), user, password);
        } else {
            releasePerformerSession();
        }

        return new DMSession(performerSessionManager.getSession(getDocbaseName()));
    }

    /**
     * Release session taken under performer of waiting for RL task.
     */
    private void releasePerformerSession() {
        if (performerSession != null) {
            performerSession.release();
            performerSession = null;
        }
    }

    /**
     * Wrapper for real functionality - within performer session.
     *
     * @throws DfException
     */
    private void performWork() throws DfException {
        final String rlUser = getConfigurationObject().getPerformerName();
        final WorkSetMapper mapper = new WorkSetMapper(getPerformerSession(), rlUser);

        final ImportMaps importMaps = getFiles();
        LOGGER.debug("Found " + importMaps.xml.size() + " XML file(s).");
        LOGGER.debug("Found " + importMaps.enrichlog.size() + " EnrichLog file(s).");
        LOGGER.debug("Found " + importMaps.log.size() + " Log file(s).");

        final RLItemProcessorFactory rlItemProcessorFactory = new RLItemProcessorFactory(
                mapper,
                getConfigurationObject());

        final List<RLItemProcessor> processorList = new LinkedList<RLItemProcessor>();

        if (0 == importMaps.xml.size()) {
            // At no files run - check all projects, some of them might be ready to move further
            for (final WorkSet workSet : mapper.getMap().values()) {
                if (workSet instanceof ProjectWorkSet) {
                    processorList
                            .add(rlItemProcessorFactory.createRLITemProcessor(workSet, null, null));
                }
            }
        } else {

            for (final Map.Entry<String, File> entry : importMaps.xml.entrySet()) {

                final String key = entry.getKey();
                final File xmlfile = importMaps.xml.get(key);
                final File enrichlogfile = importMaps.enrichlog.get(key);
                final File logfile = importMaps.log.get(key);
                if (null != logfile) {
                    processorList.add(rlItemProcessorFactory.createRLItemProcessor(logfile));
                }
                if (null != enrichlogfile) {
                    processorList.add(rlItemProcessorFactory.createRLItemProcessor(enrichlogfile));
                    processorList.add(rlItemProcessorFactory.createRLItemProcessor(xmlfile));
                } else {
                    checkWaitingTimeOfXMLFile(entry.getValue(), mapper, rlItemProcessorFactory);
                }

            }
        }

        processRLItems(processorList);

        // Check for waiting time if enabled to do so
        if (isCheckForWaitingTimeEnabled()) {
            checkForWaitingTime(mapper, rlItemProcessorFactory);
        }
    }

    /**
     * Process items for import.
     *
     * @param rlProcessors
     *            processors.
     * @throws DfException
     */
    private void processRLItems(final List<RLItemProcessor> rlProcessors) throws DfException {
        final List<IDfWorkitem> errorWorkitems = new ArrayList<IDfWorkitem>();
        for (final RLItemProcessor rlProcessor : rlProcessors) {
            try {
                if (rlProcessor.moveFile()) {
                    rlProcessor.completeWorkitem();
                }
            } catch (final Exception ex) {
                LOGGER.error("Error occurred during import from RL: " + ex.getMessage(), ex);
                if (!errorWorkitems.contains(rlProcessor.getWorkSet().getWorkitem())) {
                    errorWorkitems.add(rlProcessor.getWorkSet().getWorkitem());
                    rlProcessor.onError(performerSession, ex);
                }
            }
        }
    }

    /**
     * Checks whether the check for waiting time is enabled in the configuration.
     *
     * @return <code>true</code> if the check is enabled, <code>false</code> otherwise.
     * @throws DfException
     */
    private boolean isCheckForWaitingTimeEnabled() throws DfException {
        return getConfigurationObject().getWaitingTimeFlag();
    }

    /**
     * Checks whether for some tasks the maximum waiting-for-reply time was exceeded. If some task
     * exceeds the maximum waiting time, the corresponding workflow gets routed to Fix Error task.
     *
     * @param mapper
     *            Work set mapper that holds all "Waiting For RL" activities.
     * @param rlItemProcessorFactory
     *            processors factory.
     * @throws DfException
     */
    private void checkForWaitingTime(
            final WorkSetMapper mapper,
            final RLItemProcessorFactory rlItemProcessorFactory) throws DfException {
        LOGGER.debug("Checking waiting time of the tasks");

        final int waitingHours = getConfigurationObject().getMaxWaitingTime();
        final Date comparisonDate = DateUtil.inTheFuture(Calendar.HOUR, -waitingHours);
        final List<IDfWorkitem> errorWorkitems = new ArrayList<IDfWorkitem>();

        for (final WorkSet workSet : mapper.getMap().values()) {
            if (workSet.getDateSent().before(comparisonDate)
                    && !errorWorkitems.contains(workSet.getWorkitem())) {
                LOGGER.error(ERROR_MSG_WAITING_TIME_EXCEEDED);
                errorWorkitems.add(workSet.getWorkitem());
                rlItemProcessorFactory
                        .createRLITemProcessor(workSet, null, null)
                        .onError(performerSession, new Exception(ERROR_MSG_WAITING_TIME_EXCEEDED));
            }
        }
    }

    /**
     * Checks whether for some xml files the maximum waiting time was exceeded. If some xml files
     * exceeds the maximum waiting time, the corresponding workflow gets routed to Fix Error task.
     * 
     * @param xmlFile
     *            xmlFile.
     * @param mapper
     *            Work set mapper that holds all "Waiting For RL" activities.
     * @param rlItemProcessorFactory
     *            processors factory.
     * @throws DfException
     */
    private void checkWaitingTimeOfXMLFile(
            final File xmlFile,
            final WorkSetMapper mapper,
            final RLItemProcessorFactory rlItemProcessorFactory) throws DfException {
        LOGGER.debug("Checking waiting time of the xml File " + xmlFile.getName());

        final int waitingHours = getConfigurationObject().getMaxWaitingTimeOfXMLFile();
        final Date comparisonDate = DateUtil.inTheFuture(Calendar.HOUR, -waitingHours);
        final List<IDfWorkitem> errorWorkitems = new ArrayList<IDfWorkitem>();

        for (final WorkSet workSet : mapper.getMap().values()) {
            if (workSet.getDateSent().before(comparisonDate)
                    && !errorWorkitems.contains(workSet.getWorkitem())) {
                LOGGER.error("Enrich Log file is missing.");
                errorWorkitems.add(workSet.getWorkitem());
                rlItemProcessorFactory
                        .createRLITemProcessor(workSet, xmlFile, null)
                        .onError(performerSession, new Exception("Enrich Log file is missing."));
            }
        }
    }

    /**
     * Gets the files that are currently in the output dir of the reference linker.
     *
     * @return array with the xml and log files in the output dir of the reference linker.
     * @throws DfException
     *             In case of errors
     */
    private ImportMaps getFiles() throws DfException {
        final String dirName = getConfigurationObject().getDirOutput();
        final File importDir = new File(dirName);
        FileHelper.assertReadWriteDirectory(importDir);
        LOGGER.debug("Importing from directory " + dirName);
        final String[] extensions = new String[] {
                FILE_EXTENSION_CONTENT,
                FILE_EXTENSION_LOG,
                FILE_EXTENSION_ENRICHLOG };

        final ImportMaps xmlMap = new ImportMaps();

        for (int i = 0; i < extensions.length; i++) {
            final String extension = extensions[i];

            final File[] importFiles = importDir.listFiles(new FileFilter() {

                @Override
                public boolean accept(final File pathname) {
                    final String fileName = pathname.getName();

                    return fileName.endsWith(extension);
                }

            });
            for (File file : importFiles) {
                final String f1 = file.getName().replaceFirst("^([^.]+).*", "$1");
                switch (i) {
                    case 0:
                        xmlMap.xml.put(f1, file);
                        break;
                    case 1:
                        xmlMap.log.put(f1, file);
                        break;
                    case 2:
                        xmlMap.enrichlog.put(f1, file);
                        break;
                }
            }
        }

        return xmlMap;
    }

    /**
     * @author sgn3102
     */
    private static class ImportMaps {

        private final Map<String, File> xml = new HashMap<>();

        private final Map<String, File> enrichlog = new HashMap<>();

        private final Map<String, File> log = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.documentum.mthdservlet.IDmMethod#execute(java.util.Map, java.io.OutputStream)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void execute(final Map argMap, final OutputStream responseStream) throws Exception {
        MethodToMainAdaptor.runIt(argMap, responseStream, this);
    }

    /** The content file extension. */
    private static final String FILE_EXTENSION_CONTENT = ".xml";

    /** The log file extension. */
    private static final String FILE_EXTENSION_LOG = ".log";

    /** The enrich og file extension. */
    private static final String FILE_EXTENSION_ENRICHLOG = ".enrichlog";

    /** Error message about waiting time being exceeded for particular task. */
    private static final String ERROR_MSG_WAITING_TIME_EXCEEDED = //
            "Maximum waiting time exceeded - the task is waiting for response from RL server for too long";

    /** The logger for this class. */
    private static final Logger LOGGER = new Logger(ImportFromRLJob.class);
}