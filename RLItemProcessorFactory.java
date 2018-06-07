package com.spr.flux.methods.jobs.imports.importFromRL;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.documentum.bpm.IDfWorkitemEx;
import com.documentum.fc.common.DfException;
import com.spr.flux.common.DiscreteProductionState;
import com.spr.flux.common.PackageName;
import com.spr.flux.common.ProjectProductionState;
import com.spr.flux.common.dm.DMSession;
import com.spr.flux.common.dm.logic.Chapter;
import com.spr.flux.common.dm.someattrvalues.ErrorCategory;
import com.spr.flux.common.exportToRL.ReferenceLinkingConfigurationObject;
import com.spr.flux.common.generated.dm.SprWFS400SendChaptertoDDSConst;
import com.spr.flux.common.generated.dm.SprWFS700SendToDDSConst;
import com.spr.flux.common.log.Logger;
import com.spr.flux.common.util.FileHelper;
import com.spr.flux.common.util.activity.ErrorDetails;
import com.spr.flux.common.util.activity.WorkflowErrorHandler;
import com.spr.flux.common.util.activity.WorkflowExecutionContext;
import com.spr.flux.common.util.dmutil.datatype.DMWorkitemHelper;

/**
 * Creats RL items processors.
 *
 * @author Pavel Ilisnkiy
 */
public class RLItemProcessorFactory {

    /**
     * Constructor.
     *
     * @param mapper
     *            Work set repository.
     * @param configObject
     *            Job Configuration object.
     */
    public RLItemProcessorFactory(
                                  final WorkSetMapper mapper,
                                  final ReferenceLinkingConfigurationObject configObject) {
        this.mapper = mapper;
        this.configObject = configObject;
    }

    /**
     * Creates RL item processor.
     *
     * @param file
     *            File to process.
     * @return RL item processor.
     */
    public RLItemProcessor createRLItemProcessor(final File file) {
        final String fileName = file.getName();

        final String objectName = file.getName().replaceFirst("^([^.]+).*", "$1").replace(
                "_OnlinePDF",
                "_XML");

        final WorkSet workSet = mapper.getWorkSet(objectName);
        return createRLITemProcessor(workSet, file, null);
    }

    /**
     * Creates RL item processor.
     *
     * @param workSet
     *            work set.
     * @param file
     *            file to process.
     * @param enrichLogFile
     *            TODO
     * @return RL item processor.
     */
    public RLItemProcessor createRLITemProcessor(
            final WorkSet workSet,
            final File file,
            final File enrichLogFile) {
        return new RLItemProcessorImpl(
                createMoveFileStrategy(file, workSet, enrichLogFile),
                createCompleteWorkflowStrategy(workSet),
                createErrorHandler(workSet),
                workSet,
                configObject,
                file);
    }

    /**
     * Creates move file strategy.
     *
     * @param file
     *            file to process.
     * @param workSet
     *            work set.
     * @param enrichLogFile
     *            TODO
     * @return move file strategy.
     */
    private MoveFileStrategy createMoveFileStrategy(
            final File file,
            final WorkSet workSet,
            final File enrichLogFile) {
        if (null == file) {
            return MoveFileStrategy.NULL_FILE_STRATEGY;
        } else if (file.getName().endsWith(MoveFileStrategy.FILE_EXTENSION_LOG)) {
            return MoveFileStrategy.REMOVE_FILE_STRATEGY;
        } else if (file.getName().endsWith(MoveFileStrategy.FILE_EXTENSION_ENRICHLOGLOG)) {
            return MoveFileStrategy.ENRICHILE_FILE_STRATEGY;
        } else if (null == workSet) {
            return System.currentTimeMillis() - file.lastModified() > OLD_FILE_DEFINITION_MILLS
                    ? MoveFileStrategy.UNKNOWN_FILE_STRATEGY
                    : MoveFileStrategy.LEAVE_FILE_STRATEGY;
        } else if (workSet instanceof ChapterProjectWorkSet || workSet instanceof ChapterWorkSet) {
            return new ChapterMoveStrategy(file);
        } else if (workSet instanceof BookFmBmProjectWorkSet) {
            return new FMBMMoveStrategy(file);
        }
        return MoveFileStrategy.LEAVE_FILE_STRATEGY;
    }

    /**
     * Creates complete RL waiting workitem strategy.
     *
     * @param workSet
     *            work set.
     * @return created strategy.
     */
    private CompleteWorkitemStrategy createCompleteWorkflowStrategy(final WorkSet workSet) {
        if (workSet instanceof ChapterWorkSet) {
            return CompleteWorkitemStrategy.DISCRETE_WORKITEM_COMPLETE_STRATEGY;
        } else if (workSet instanceof ProjectWorkSet) {
            return CompleteWorkitemStrategy.PROJECT_WORKITEM_COMPLETE_STRATEGY;
        }
        return CompleteWorkitemStrategy.NULL_COMPLETE_STRATEGY;
    }

    /**
     * Creates error handler.
     *
     * @param workSet
     *            work set.
     * @return created error handler.
     */
    private ErrorHandler createErrorHandler(final WorkSet workSet) {
        if (workSet instanceof ChapterWorkSet) {
            return ErrorHandler.DISCRETE_WORKITEM_ERROR_HANDLER;
        } else if (workSet instanceof ProjectWorkSet) {
            return ErrorHandler.PROJECT_WORKITEM_ERROR_HANDLER;
        }
        return ErrorHandler.NULL_ERROR_HANDLER;
    }

    /**
     * RL item processor implementation.
     *
     * @author Pavel Ilinskiy
     */
    private static final class RLItemProcessorImpl implements RLItemProcessor {

        /** Move file strategy. */
        private final MoveFileStrategy moveFileStrategy;

        /** Complete RL waiting workitem strategy. */
        private final CompleteWorkitemStrategy completeWorkflowStrategy;

        /** Error handler. */
        private final ErrorHandler errorHandler;

        /** RL job config. */
        private final ReferenceLinkingConfigurationObject config;

        /** Work set. */
        private final WorkSet workSet;

        /** File to process. */
        private final File file;

        /**
         * Constructor.
         *
         * @param moveFileStrategy
         *            .
         * @param completeWorkflowStrategy
         *            .
         * @param errorHandler
         *            .
         * @param workSet
         *            .
         * @param config
         *            .
         * @param file
         *            .
         */
        private RLItemProcessorImpl(
                                    final MoveFileStrategy moveFileStrategy,
                                    final CompleteWorkitemStrategy completeWorkflowStrategy,
                                    final ErrorHandler errorHandler,
                                    final WorkSet workSet,
                                    final ReferenceLinkingConfigurationObject config,
                                    final File file) {
            this.moveFileStrategy = moveFileStrategy;
            this.completeWorkflowStrategy = completeWorkflowStrategy;
            this.errorHandler = errorHandler;
            this.config = config;
            this.workSet = workSet;
            this.file = file;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean moveFile() throws DfException, IOException {
            return moveFileStrategy.moveFile(file, workSet, config);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void completeWorkitem() throws DfException {
            completeWorkflowStrategy.completeWorkitem(workSet);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onError(final DMSession session, final Exception e) throws DfException {
            LOGGER.debug("Performer : " + session.getUserName());
            errorHandler.handleError(session, e, workSet);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public WorkSet getWorkSet() {
            return workSet;
        }
    }

    /**
     * @author Pavel Ilinskiy
     */
    private interface MoveFileStrategy {

        /**
         * Move processed file.
         *
         * @param file
         *            File to move.
         * @param workSet
         *            work set.
         * @param config
         *            RL job confuguration/
         * @return true to continue process.
         * @throws DfException
         * @throws IOException
         */
        boolean moveFile(
                File file,
                WorkSet workSet,
                ReferenceLinkingConfigurationObject config) throws DfException, IOException;

        /** Remove file strategy. */
        MoveFileStrategy REMOVE_FILE_STRATEGY = new MoveFileStrategy() {

            @Override
            public boolean moveFile(
                    final File file,
                    final WorkSet workSet,
                    final ReferenceLinkingConfigurationObject config) throws DfException,
                                                                      IOException {
                LOGGER.debug("Deleting file: " + file);
                FileHelper.deleteRecursive(file);
                return false;
            }
        };

        /** Remove file strategy. */
        MoveFileStrategy ENRICHILE_FILE_STRATEGY = new MoveFileStrategy() {

            @Override
            public boolean moveFile(
                    final File file,
                    final WorkSet workSet,
                    final ReferenceLinkingConfigurationObject config) throws DfException,
                                                                      IOException {

                checkXPath(file);

                return false;
            }
        };

        /** Unknown file strategy. */
        MoveFileStrategy UNKNOWN_FILE_STRATEGY = new MoveFileStrategy() {

            @Override
            public boolean moveFile(
                    final File file,
                    final WorkSet workSet,
                    final ReferenceLinkingConfigurationObject config) throws DfException,
                                                                      IOException {
                LOGGER.error(
                        "No task found waiting for file [{0}]. Moving it to 'unknown' directory.",
                        file.getPath());

                FileHelper.assertMoveFile(file, new File(config.getDirUnknown()));
                return false;
            }
        };

        /** Leave file as is strategy. */
        MoveFileStrategy LEAVE_FILE_STRATEGY = new MoveFileStrategy() {

            @Override
            public boolean moveFile(
                    final File file,
                    final WorkSet workSet,
                    final ReferenceLinkingConfigurationObject config) throws DfException,
                                                                      IOException {
                LOGGER.warn(
                        "Leaving file though no task found waiting for it: [{0}]",
                        file.getAbsoluteFile());
                return false;
            }
        };

        /** Process file but do nothing. */
        MoveFileStrategy NULL_FILE_STRATEGY = new MoveFileStrategy() {

            @Override
            public boolean moveFile(
                    final File file,
                    final WorkSet workSet,
                    final ReferenceLinkingConfigurationObject config) throws DfException,
                                                                      IOException {
                return true;
            }
        };

        /** Log file extension. */
        String FILE_EXTENSION_LOG = ".log";

        /** EnrichlogLog file extension. */
        String FILE_EXTENSION_ENRICHLOGLOG = ".enrichlog";

    }

    /**
     * Complete RL waiting workitem strategy.
     *
     * @author Pavel Ilinskiy
     */
    private interface CompleteWorkitemStrategy {

        /**
         * Completes workitem.
         *
         * @param workSet
         *            work set.
         * @throws DfException
         */
        void completeWorkitem(WorkSet workSet) throws DfException;

        /** Completes workitem of discrete workflow. */
        CompleteWorkitemStrategy DISCRETE_WORKITEM_COMPLETE_STRATEGY = new CompleteWorkitemStrategy() {

            @Override
            public void completeWorkitem(final WorkSet workSet) throws DfException {
                DMWorkitemHelper.completeWorkitem(
                        workSet.getWorkitem(),
                        SprWFS400SendChaptertoDDSConst.SET_STATE_TO_RL_COMPLETE);
            }
        };

        /** Completes workitem of project workflow. */
        CompleteWorkitemStrategy PROJECT_WORKITEM_COMPLETE_STRATEGY = new CompleteWorkitemStrategy() {

            @Override
            public void completeWorkitem(final WorkSet workSet) throws DfException {
                if ((workSet instanceof ProjectWorkSet)
                        && RLUtils.checkProjectWorkSetComplete((ProjectWorkSet) workSet)) {
                    DMWorkitemHelper.completeWorkitem(
                            workSet.getWorkitem(),
                            SprWFS700SendToDDSConst.SET_STATE_TO_RL_COMPLETE);
                }
            }
        };

        /** Null strategy. */
        CompleteWorkitemStrategy NULL_COMPLETE_STRATEGY = new CompleteWorkitemStrategy() {

            @Override
            public void completeWorkitem(final WorkSet workSet) throws DfException {}
        };
    }

    /**
     * Error handler.
     *
     * @author Pavel Ilinskiy
     */
    private interface ErrorHandler {

        /**
         * Handles error.
         *
         * @param session
         *            docbase session wrapper.
         * @param e
         *            Exception.
         * @param workSet
         *            work set.
         * @throws DfException
         */
        void handleError(DMSession session, Exception e, WorkSet workSet) throws DfException;

        /**
         * Handle discrete workflow errors.
         */
        ErrorHandler DISCRETE_WORKITEM_ERROR_HANDLER = new ErrorHandler() {

            @Override
            public void handleError(
                    final DMSession session,
                    final Exception e,
                    final WorkSet workSet) throws DfException {
                if (!(workSet instanceof ChapterWorkSet)) {
                    throw new IllegalArgumentException("Chapter Work Set expected");
                }
                final ChapterWorkSet chapterWorkSet = (ChapterWorkSet) workSet;
                final ErrorDetails errorDetails = new ErrorDetails(e, ErrorCategory.RL, session);
                final WorkflowExecutionContext executionContext = new WorkflowExecutionContext(
                        chapterWorkSet.getWorkitem().getObjectId(),
                        chapterWorkSet.getChapter(),
                        PackageName.DISCRETE_OBJECT);
                ((IDfWorkitemEx) chapterWorkSet.getWorkitem()).setPrimitiveObjectValue(
                        PROCESS_VAR_ERROR_PROD_STATE,
                        DiscreteProductionState.ERROR_ENRICHMENT_CONTROLLER);
                ((IDfWorkitemEx) chapterWorkSet.getWorkitem()).setPrimitiveObjectValue(
                        PROCESS_VAR_ERROR_CATEGORY_TERM,
                        errorDetails.getCategoryTerm());
                WorkflowErrorHandler.registerError(errorDetails, executionContext);
                DMWorkitemHelper.completeWorkitem(
                        chapterWorkSet.getWorkitem(),
                        SprWFS400SendChaptertoDDSConst.SET_TO_ERROR_STATE);
            }
        };

        /**
         * Handle project workflow error.
         */
        ErrorHandler PROJECT_WORKITEM_ERROR_HANDLER = new ErrorHandler() {

            @Override
            public void handleError(
                    final DMSession session,
                    final Exception e,
                    final WorkSet workSet) throws DfException {
                if (!(workSet instanceof ProjectWorkSet)) {
                    throw new IllegalArgumentException("Project Work Set expected");
                }
                final ProjectWorkSet projectWorkSet = (ProjectWorkSet) workSet;
                final ErrorDetails errorDetails = new ErrorDetails(e, ErrorCategory.RL, session);
                final WorkflowExecutionContext executionContext = new WorkflowExecutionContext(
                        projectWorkSet.getWorkitem().getObjectId(),
                        projectWorkSet.getProject(),
                        PackageName.PROJECT);
                ((IDfWorkitemEx) projectWorkSet.getWorkitem()).setPrimitiveObjectValue(
                        PROCESS_VAR_ERROR_PROD_STATE,
                        ProjectProductionState.ERROR_ENRICHMENT_CONTROLLER);
                ((IDfWorkitemEx) projectWorkSet.getWorkitem()).setPrimitiveObjectValue(
                        PROCESS_VAR_ERROR_CATEGORY_TERM,
                        errorDetails.getCategoryTerm());
                WorkflowErrorHandler.registerError(errorDetails, executionContext);
                DMWorkitemHelper.completeWorkitem(
                        projectWorkSet.getWorkitem(),
                        SprWFS700SendToDDSConst.LOGGING_DUMMY_ACTIVITY_5);
            }
        };

        /**
         * Null handler.
         */
        ErrorHandler NULL_ERROR_HANDLER = new ErrorHandler() {

            @Override
            public void handleError(
                    final DMSession session,
                    final Exception e,
                    final WorkSet workSet) throws DfException {}
        };

        /** The name of process variable. */
        String PROCESS_VAR_ERROR_PROD_STATE = "error_prod_state";

        /** The name of process variable. */
        String PROCESS_VAR_ERROR_CATEGORY_TERM = "error_category_term";
    }

    /**
     * @param file
     *            Check Xpath in Enrichfile.
     * @throws DfException
     *             Exception.
     */
    static void checkXPath(final File file) throws DfException {
        try {
            if (null == file) {
                throw new DfException("Enrich log File is missing");
            }

            final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = builderFactory.newDocumentBuilder();
            final Document xmlDocument = builder.parse(file);
            final XPath xpath = XPathFactory.newInstance().newXPath();
            LOGGER.debug("Checking if Xpath presents");
            final String expression = "//Service[@Mandatory='true' and not(Result/Success)]";
            final Boolean error = (Boolean) xpath
                    .compile(expression)
                    .evaluate(xmlDocument, XPathConstants.BOOLEAN);

            LOGGER.debug("Deleting file: " + file);
            FileHelper.deleteRecursive(file);

            if (error) {
                throw new DfException("Enrich Log file contains error");
            }

        } catch (final ParserConfigurationException ex) {
            throw new DfException(ex.getMessage());
        } catch (final SAXException ex) {
            throw new DfException(ex.getMessage());
        } catch (final XPathExpressionException ex) {
            throw new DfException(ex.getMessage());
        } catch (final IOException ex) {
            throw new DfException(ex.getMessage());
        }
    };

    /**
     * @author sgn3102
     */
    private static class ChapterMoveStrategy implements MoveFileStrategy {

        private final File enrichFile;

        /**
         * @param enrichFile
         *            -- Where null is an error or the file might contain an error indication.
         */
        private ChapterMoveStrategy(final File enrichFile) {
            this.enrichFile = enrichFile;
        }

        /**
         * {@inheritDoc}
         *
         * @see com.spr.flux.methods.jobs.imports.importFromRL.RLItemProcessorFactory2.
         *      MoveFileStrategy#moveFile(java.io.File,
         *      com.spr.flux.methods.jobs.imports.importFromRL.WorkSet,
         *      com.spr.flux.common.exportToRL.ReferenceLinkingConfigurationObject)
         */
        @Override
        public boolean moveFile(
                final File file,
                final WorkSet workSet,
                final ReferenceLinkingConfigurationObject config) throws DfException, IOException {

            Chapter chapter = null;
            if (workSet instanceof ChapterWorkSet) {
                chapter = ((ChapterWorkSet) workSet).getChapter();
            } else if (workSet instanceof ChapterProjectWorkSet) {
                chapter = ((ChapterProjectWorkSet) workSet).getChapter();
            } else {
                throw new IllegalArgumentException(
                        "Invalid work set. It does not contain chapter.");
            }
            final File tempDir = RLUtils.getChapterTempDir(chapter);
            LOGGER.debug("Moving '" + file.getAbsolutePath() + "' to '" + tempDir.getPath() + "'");
            FileHelper.assertMoveFile(file, tempDir);
            return true;
        }

    }

    /**
     * @author sgn3102
     */
    private static class FMBMMoveStrategy implements MoveFileStrategy {

        private final File enrichFile;

        /**
         * @param enrichFile
         *            -- Where null is an error or the file might contain an error indication.
         */
        private FMBMMoveStrategy(final File enrichFile) {
            this.enrichFile = enrichFile;
        }

        /**
         * {@inheritDoc}
         *
         * @see com.spr.flux.methods.jobs.imports.importFromRL.RLItemProcessorFactory2.
         *      MoveFileStrategy#moveFile(java.io.File,
         *      com.spr.flux.methods.jobs.imports.importFromRL.WorkSet,
         *      com.spr.flux.common.exportToRL.ReferenceLinkingConfigurationObject)
         */
        @Override
        public boolean moveFile(
                final File file,
                final WorkSet workSet,
                final ReferenceLinkingConfigurationObject config) throws DfException, IOException {

            if (workSet instanceof BookFmBmProjectWorkSet) {
                final File tempDir = RLUtils.getBookFmBmDir((BookFmBmProjectWorkSet) workSet);
                LOGGER.debug(
                        "Moving '" + file.getAbsolutePath() + "' to '" + tempDir.getPath() + "'");
                FileHelper.assertMoveFile(file, tempDir);
                return true;
            }
            throw new IllegalArgumentException("BookFmBmProjectWorkSet expected");
        }
    }

    /** File is old if it is older than. */
    private static final long OLD_FILE_DEFINITION_MILLS = 24 * 60 * 60 * 1000; // one hour

    /** The logger for this class. */
    private static final Logger LOGGER = new Logger(RLItemProcessorFactory.class);

    /** Work set repository. */
    private final WorkSetMapper mapper;

    /** Job Configuration object. */
    private final ReferenceLinkingConfigurationObject configObject;

}
