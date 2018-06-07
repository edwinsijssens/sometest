package com.spr.jfluxpackagegenerator.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.spr.jfluxpackagegenerator.jobsheet.JobSheet;
import com.spr.jfluxpackagegenerator.jobsheet.builder.ArticleJobSheetBuilder;
import com.spr.jfluxpackagegenerator.jobsheet.builder.CommonJobSheetBuilder;
import com.spr.jfluxpackagegenerator.jobsheet.builder.IssueJobSheetBuilder;
import com.spr.jfluxpackagegenerator.model.data.ArticleDataStorage;
import com.spr.jfluxpackagegenerator.model.data.DataStorage;
import com.spr.jfluxpackagegenerator.model.enums.IssueType;
import com.spr.jfluxpackagegenerator.model.enums.Stage;
import com.spr.jfluxpackagegenerator.model.enums.Vendor;
import com.spr.jfluxpackagegenerator.ui.layout.DataLayoutDecorator;
import com.spr.jfluxpackagegenerator.ui.layout.MainUIController;
import com.spr.jfluxpackagegenerator.ui.layout.control.creators.AcdcControlCreator;
import com.spr.jfluxpackagegenerator.ui.layout.control.creators.ArticleGrantsControlCreator;
import com.spr.jfluxpackagegenerator.ui.layout.control.creators.ArticleInfoControlCreator;
import com.spr.jfluxpackagegenerator.ui.layout.control.creators.AuthorGroupControlCreator;
import com.spr.jfluxpackagegenerator.ui.layout.control.creators.DeliverablesCompoundControlCreator;
import com.spr.jfluxpackagegenerator.ui.layout.control.creators.DeliverablesDiscreteControlCreator;
import com.spr.jfluxpackagegenerator.ui.layout.control.creators.FilesToPublisherControlCreator;
import com.spr.jfluxpackagegenerator.ui.layout.control.creators.MainControlCreator;
import com.spr.jfluxpackagegenerator.ui.layout.control.creators.ProductionInfoControlCreator;
import com.spr.jfluxpackagegenerator.ui.layout.control.creators.PublisherJournalInfoControlCreator;
import com.spr.jfluxpackagegenerator.ui.layout.control.creators.VolumeIssueInfoControlCreator;
import com.spr.jfluxpackagegenerator.ui.layout.id.ArticleInfoLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.IssueInfoLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.JournalInfoLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.MainControlLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.VolumeInfoLayoutIds;
import com.spr.jfluxpackagegenerator.util.DOMHelper;
import com.spr.jfluxpackagegenerator.util.FTPUploadFile;
import com.spr.jfluxpackagegenerator.util.FileHelper;
import com.spr.jfluxpackagegenerator.util.JobSheetHelper;
import com.spr.jfluxpackagegenerator.util.PackageCreator;
import com.spr.jfluxpackagegenerator.util.PackageFileNameGenerator;
import com.spr.jfluxpackagegenerator.util.Settings;
import com.spr.packagegenerator.controller.PGDataController;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;

@SuppressWarnings("serial")
@Theme("jfluxpackagegenerator")
public class JFluxPackageGeneratorUI extends UI {
    
    
    private static final Logger LOG = Logger.getLogger(JFluxPackageGeneratorUI.class);
    
    private DataLayoutDecorator parentLayoutDecorator = null;
    
    private DataLayoutDecorator jobsheetLayoutDecoratorS100 = null;
    
    private DataLayoutDecorator jobsheetLayoutDecoratorS500 = null;
    
    private DataLayoutDecorator jobsheetLayoutDecoratorS200 = null;
    
    private final List<DataLayoutDecorator> articleLayoutDecorators =
            new ArrayList<DataLayoutDecorator>(5);
    
    private Stage currentStage = Stage.S100EXTERNAL;
    
    public PGUIController mainUIController;
    
    PGDataController dataController = new PGDataController();
    
    private static final String PROJECT_NAME = "JFlux";
    
    private static final String LOGFILE = "/home/das21/PGLog.log";
    
    private static final String LOGPATTERN = "%d [%p|%c|%C{1}] %m%n";
    
    // CreatePackage
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = JFluxPackageGeneratorUI.class)
    public static class Servlet extends VaadinServlet {
        
    }
    
    @Override
    protected void init(final VaadinRequest request) {
        
        try {
            VaadinSession.getCurrent().getSession().setMaxInactiveInterval(72000);
            
            // Create a few components and bind them to the location tags
            mainUIController = new PGUIController();
            
            ConsoleAppender console = new ConsoleAppender(); // create appender
            // configure the appender
            console.setLayout(new PatternLayout(LOGPATTERN));
            console.setThreshold(Level.DEBUG);
            console.activateOptions();
            // add appender to any Logger (here is root)
            Logger.getRootLogger().addAppender(console);
            
            FileAppender fa = new FileAppender();
            fa.setFile(LOGFILE);
            fa.setLayout(new PatternLayout(LOGPATTERN));
            fa.setThreshold(Level.DEBUG);
            fa.setAppend(true);
            fa.activateOptions();
            
            // add appender to any Logger (here is root)
            Logger.getRootLogger().addAppender(fa);
            
            // create Main layout and fill control components
            parentLayoutDecorator = new DataLayoutDecorator(new CustomLayout("mainLayout"));
            final MainControlCreator creator = new MainControlCreator();
            creator.createComponents(parentLayoutDecorator.getLayout(), mainUIController);
            
            jobsheetLayoutDecoratorS100 =
                    new DataLayoutDecorator(new CustomLayout("jobsheetLayoutS100"));
            jobsheetLayoutDecoratorS500 =
                    new DataLayoutDecorator(new CustomLayout("jobsheetLayoutS500"));
            jobsheetLayoutDecoratorS200 =
                    new DataLayoutDecorator(new CustomLayout("jobsheetLayoutS200"));
            
            // create JobsheetLayout and create controls for Publisher and
            // Journal Info sections
            final PublisherJournalInfoControlCreator pubJouInfoCreator =
                    new PublisherJournalInfoControlCreator();
            pubJouInfoCreator.createComponents(jobsheetLayoutDecoratorS100.getLayout(),
                    mainUIController);
            
            pubJouInfoCreator.createComponents(jobsheetLayoutDecoratorS500.getLayout(),
                    mainUIController);
            
            // create ACDC Layout control for s200 and manuscript
            final AcdcControlCreator acdcControlCreator = new AcdcControlCreator();
            acdcControlCreator.createComponents(jobsheetLayoutDecoratorS200.getLayout(),
                    mainUIController);
            
            // create Issue and Volume info controls on JobSheet Layout
            final VolumeIssueInfoControlCreator viCreator = new VolumeIssueInfoControlCreator();
            viCreator.createComponents(jobsheetLayoutDecoratorS500.getLayout(), mainUIController);
            
            // create main ProductionInfo controls like ExternalPublisher
            // contact information
            final ProductionInfoControlCreator prodControlCreator =
                    new ProductionInfoControlCreator();
            prodControlCreator.createComponents(jobsheetLayoutDecoratorS100.getLayout(),
                    mainUIController);
            
            prodControlCreator.createComponents(jobsheetLayoutDecoratorS500.getLayout(),
                    mainUIController);
            
            // create Deliverables controls for discrete
            final DeliverablesDiscreteControlCreator discControlCreator =
                    new DeliverablesDiscreteControlCreator();
            discControlCreator.createComponents(jobsheetLayoutDecoratorS100.getLayout(),
                    mainUIController);
            
            // create Deliverables controls for compound
            final DeliverablesCompoundControlCreator compControlCreator =
                    new DeliverablesCompoundControlCreator();
            compControlCreator.createComponents(jobsheetLayoutDecoratorS500.getLayout(),
                    mainUIController);
            
            // tab adding
            final TabSheet tabsheet = (TabSheet) parentLayoutDecorator
                    .getComponentById(MainControlLayoutIds.TABSHEET_INFO.toString());
            
            tabsheet.addTab(jobsheetLayoutDecoratorS100.getLayout(), "S100 Jobsheet");
            final Tab jsTab =
                    tabsheet.addTab(jobsheetLayoutDecoratorS500.getLayout(), "S500 Jobsheet");
            jsTab.setVisible(false);
            final Tab s200Tab =
                    tabsheet.addTab(jobsheetLayoutDecoratorS200.getLayout(), "S200 Pkg generator");
            s200Tab.setVisible(false);
            
            // fill with controls and add article tabs
            final ArticleInfoControlCreator artInfo = new ArticleInfoControlCreator();
            final ArticleGrantsControlCreator artGrantsCreator = new ArticleGrantsControlCreator();
            final FilesToPublisherControlCreator fltpCreator = new FilesToPublisherControlCreator();
            final AuthorGroupControlCreator agCreator = new AuthorGroupControlCreator();
            
            for (int i = 0; i < 5; i++) {
                articleLayoutDecorators
                        .add(new DataLayoutDecorator(new CustomLayout("articleXmlLayout")));
            }
            
            int artCount = 1;
            final Iterator<DataLayoutDecorator> iter = articleLayoutDecorators.iterator();
            while (iter.hasNext()) {
                final DataLayoutDecorator decorator = iter.next();
                
                artInfo.createComponents(decorator.getLayout(), mainUIController);
                artGrantsCreator.createComponents(decorator.getLayout(), mainUIController);
                fltpCreator.createComponents(decorator.getLayout(), mainUIController);
                agCreator.createComponents(decorator.getLayout(), mainUIController);
                final Tab tab = tabsheet.addTab(decorator.getLayout(), "Article " + artCount);
                
                tab.setVisible(artCount == 1);
                
                artCount++;
                
            }
            
            setContent(parentLayoutDecorator.getLayout());
        } catch (final Exception exe) {
            LOG.error("JFluxPackageGeneratorUI.init()  Exception Occured " + exe);
            
        }
    }
    
    public class PGUIController implements MainUIController {
        
        
        public PGUIController() throws Exception {}
        
        private final List<DataStorage> storages = new ArrayList<DataStorage>();
        
        private CommonJobSheetBuilder builder;
        
        private JobSheet jobsheet;
        
        private Integer articleCount = 1;
        
        private final Settings settings = new Settings();
        
        @Override
        public Settings getSettings() {
            return settings;
        }
        
        private String environment = settings.getConfig().getDefaultEnvId();
        
        private Vendor vendor = Vendor.Crest;
        
        private File packageFile;
        
        @Override
        public void createJobSheet() {
            
            LOG.debug("new jobsheet generation");
            // get new data values from UI controls
            refreshDataStorages();
            
            // create jobsheet builder according the current stage
            builder = Stage.S100EXTERNAL.equals(getCurrentStage())
                    ? new ArticleJobSheetBuilder(storages) : new IssueJobSheetBuilder(storages);
            builder.build();
            jobsheet = builder.getProduct();
            
        }
        
        @Override
        public void showJobsheet() {
            try {
                
                LOG.debug("show new jobsheet ");
                final String sViewer = DOMHelper
                        .serializeDocument(JobSheetHelper.convertJobSheetToDocument(jobsheet));
                
                final TextArea txtXMViewer = (TextArea) parentLayoutDecorator
                        .getComponentById(MainControlLayoutIds.JobSheetXMLContainer.toString());
                
                txtXMViewer.clear();
                txtXMViewer.setValue(sViewer);
                
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        }
        
        private Stage getCurrentStage() {
            return currentStage;
            
        }
        
        private int getArticleCount() {
            return articleCount;
        }
        
        public String getEnvironment() {
            return environment;
        }
        
        private Vendor getVendor() {
            return vendor;
        }
        
        @Override
        public void refreshDataStorages() {
            
            LOG.debug("Get data from UI controls ");
            storages.clear();
            storages.add(new DataStorage(parentLayoutDecorator.getFieldAndSelectValues()));
            if (Stage.S100EXTERNAL.equals(getCurrentStage())) {
                storages.add(
                        new DataStorage(jobsheetLayoutDecoratorS100.getFieldAndSelectValues()));
                storages.add(new ArticleDataStorage(
                        articleLayoutDecorators.get(0).getFieldAndSelectValues(), 1));
                
            } else {
                storages.add(
                        new DataStorage(jobsheetLayoutDecoratorS500.getFieldAndSelectValues()));
                for (int i = 0; i < getArticleCount(); i++) {
                    storages.add(new ArticleDataStorage(
                            articleLayoutDecorators.get(i).getFieldAndSelectValues(), i + 1));
                }
            }
        }
        
        @Override
        public void generatePackage() {
            try {
                final PackageCreator creator = new PackageCreator(
                        JobSheetHelper.convertJobSheetToDocument(jobsheet), settings);
                packageFile = creator.createPackage(builder.getJobSheetName(),
                        generatePackageName(), builder.getTemplatesForFiles());
                
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
            
        }
        
        private String getValueById(final String id) {
            for (int i = 0; i < storages.size(); i++) {
                final String value = storages.get(i).getDataValues().get(id);
                if (value != null) {
                    return value;
                }
            }
            return "";
        }
        
        private String generatePackageName() {
            // replace with models???
            final String journalId = getValueById(JournalInfoLayoutIds.TXT_JPROR_ID.toString());
            if (Stage.S100EXTERNAL.equals(getCurrentStage())) {
                final String regYear = getValueById(ArticleInfoLayoutIds.TXT_COPY_YR.toString());
                final String articleId = getValueById(ArticleInfoLayoutIds.TXT_ART_ID.toString());
                
                return PackageFileNameGenerator.createArticleFileName(journalId, regYear,
                        articleId);
            } else {
                final String volumeId = getValueById(VolumeInfoLayoutIds.TXT_VOL_ID_ST.toString());
                final String issueIdStart =
                        getValueById(IssueInfoLayoutIds.TXT_ISS_ID_ST.toString());
                final String issueType = getValueById(IssueInfoLayoutIds.LST_ISS_TYPE.toString());
                return PackageFileNameGenerator.createIssueFileName(journalId, volumeId,
                        issueIdStart, IssueType.Supplement.toString().equalsIgnoreCase(issueType));
            }
        }
        
        @Override
        public void saveToDb() {
            
            dataController.saveJfluxPgInfo(PROJECT_NAME, getCurrentStage().toString(),
                    getEnvironment(), packageFile, getVendor().toString());
            
        }
        
        @Override
        public void sendPackage() {
            if (packageFile != null) {
                try {
                    
                    final FTPUploadFile upload = new FTPUploadFile(settings);
                    upload.ftpUploadFile(getEnvironment(), getVendor(),
                            FileHelper.getNameWithoutExtension(packageFile),
                            packageFile.getAbsolutePath());
                    
                    MessageBox.showPlain(Icon.INFO, "Info", "A package was generated successfully.",
                            ButtonId.OK);
                    
                } catch (final Exception e) {
                    
                    LOG.error("Error during ftp transfer: " + e.getMessage());
                    
                }
            } else {
                LOG.error("Error: packageFile is not created!");
                
            }
            
        }
        
        @Override
        public void setStage(final Stage value) {
            currentStage = value;
            
        }
        
        @Override
        public void setVendor(final Vendor value) {
            vendor = value;
            
        }
        
        @Override
        public void setEnv(final String value) {
            environment = value;
            
        }
        
        @Override
        public void setArticleCount(final Integer value) {
            articleCount = value;
            
        }
        
        @Override
        public void reArrangeTabs() {
            
            final TabSheet tabsheet = (TabSheet) parentLayoutDecorator
                    .getComponentById(MainControlLayoutIds.TABSHEET_INFO.toString());
            
            final Tab tab100 = tabsheet.getTab(jobsheetLayoutDecoratorS100.getLayout());
            final Tab tab500 = tabsheet.getTab(jobsheetLayoutDecoratorS500.getLayout());
            
            tab100.setVisible(Stage.S100EXTERNAL.equals(getCurrentStage()));
            tab500.setVisible(Stage.S500EXTERNAL.equals(getCurrentStage()));
            
            for (int i = 0; i < articleLayoutDecorators.size(); i++) {
                final int artTabNumber = i + 1;
                final Tab tab = tabsheet.getTab(articleLayoutDecorators.get(i).getLayout());
                
                if (Stage.S500EXTERNAL.equals(getCurrentStage())) {
                    tab.setVisible(getArticleCount() >= artTabNumber);
                } else {
                    tab.setVisible(artTabNumber == 1);
                    
                }
            }
            
        }
        
        @Override
        public boolean validateFields() {
            final Set<String> errorMessages = new HashSet<>();
            
            for (final DataLayoutDecorator decorator : getActiveDecorators()) {
                for (final Component c : decorator.getLayout()) {
                    if (c instanceof TextField) {
                        final TextField textField = (TextField) c;
                        try {
                            textField.validate();
                        } catch (final InvalidValueException e) {
                            errorMessages.add(e.getMessage());
                        }
                    }
                }
            }
            
            final boolean success = errorMessages.isEmpty();
            if (!success) {
                MessageBox.showPlain(Icon.WARN, "Validation", StringUtils.join(errorMessages, "\n"),
                        ButtonId.OK);
            }
            
            return success;
        }
        
        private List<DataLayoutDecorator> getActiveDecorators() {
            final List<DataLayoutDecorator> decorators = new ArrayList<>();
            
            int articleCount = 1;
            if (Stage.S500EXTERNAL.equals(getCurrentStage())) {
                articleCount = getArticleCount();
                decorators.add(jobsheetLayoutDecoratorS500);
            } else {
                decorators.add(jobsheetLayoutDecoratorS100);
            }
            decorators.addAll(articleLayoutDecorators.subList(0, articleCount));
            
            return decorators;
        }
        
        @Override
        public void showTabs() {
            final Stage stage = currentStage;
            final TabSheet tabsheet = (TabSheet) parentLayoutDecorator
                    .getComponentById(MainControlLayoutIds.TABSHEET_INFO.toString());
            final Tab tab100 = tabsheet.getTab(jobsheetLayoutDecoratorS100.getLayout());
            final Tab tabS200 = tabsheet.getTab(jobsheetLayoutDecoratorS200.getLayout());
            
            final Tab art = tabsheet.getTab(articleLayoutDecorators.get(0).getLayout());
            
            if (stage == Stage.S100EXTERNAL) {
                
                tab100.setVisible(Stage.S100EXTERNAL.equals(getCurrentStage()));
                tabS200.setVisible(false);
                
            } else if (stage == Stage.S200EXTERNAL) {
                
                tabS200.setVisible(Stage.S200EXTERNAL.equals(getCurrentStage()));
                tab100.setVisible(false);
                art.setVisible(false);
                
            } else {
                tabS200.setVisible(false);
                
                tab100.setVisible(false);
            }
            
        }
    }
    
}