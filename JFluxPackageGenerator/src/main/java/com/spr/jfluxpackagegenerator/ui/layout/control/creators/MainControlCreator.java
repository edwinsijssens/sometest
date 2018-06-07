package com.spr.jfluxpackagegenerator.ui.layout.control.creators;

import java.util.HashMap;
import java.util.Map;

import com.spr.jfluxpackagegenerator.model.enums.Stage;
import com.spr.jfluxpackagegenerator.model.enums.Vendor;
import com.spr.jfluxpackagegenerator.ui.layout.MainUIController;
import com.spr.jfluxpackagegenerator.ui.layout.id.ArticleGrantsLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.MainControlLayoutIds;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author cjn6673
 */
public class MainControlCreator implements LayoutControlCreator {
    
    
    private NativeSelect jobSheetSelector;
    
    public final NativeSelect envSelector = new NativeSelect();
    
    private final ComboBox artCountSelector = new ComboBox();
    
    private final NativeSelect vendorSelector = new NativeSelect();
    
    public final Map<String, String> map = new HashMap<String, String>();
    
    private static final String HOSTNAME = "ftp.springer.com";
    
    private static final String USERNAME = "jflux";
    
    private static final String PASSWORD = "fjpdapsu";
    
    public static String envVal = "Integration";
    
    @Override
    public void createComponents(final CustomLayout layout, final MainUIController controller) {
        
        final Button config = new Button();
        config.setId(ArticleGrantsLayoutIds.BTN_CONFIG.toString());
        config.setIcon(FontAwesome.COG);
        
        config.setWidthUndefined();
        config.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        layout.addComponent(config, ArticleGrantsLayoutIds.BTN_CONFIG.toString());
        
        final Button refresh = new Button();
        refresh.setId(ArticleGrantsLayoutIds.IMG_HOME.toString());
        refresh.setIcon(FontAwesome.HOME);
        
        refresh.setSizeFull();
        refresh.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        layout.addComponent(refresh, ArticleGrantsLayoutIds.IMG_HOME.toString());
        
        refresh.addClickListener(new Button.ClickListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void buttonClick(final Button.ClickEvent clickEvent) {
                
                Page.getCurrent().reload();
            }
        });
        
        // subwindow for configuration
        
        final Window subWindow = new Window();
        final CustomLayout windowConfig = new CustomLayout("config_setting");
        
        subWindow.setModal(true);
        subWindow.setContent(windowConfig);
        final PasswordField secretKey = new PasswordField();
        secretKey.setId(MainControlLayoutIds.TXT_SECRET_KEY.toString());
        windowConfig.addComponent(secretKey, MainControlLayoutIds.TXT_SECRET_KEY.toString());
        subWindow.center();
        subWindow.setHeight("400px");
        subWindow.setWidth("700px");
        
        final Button secretSubmit = new Button("Confirm");
        
        secretSubmit.setId(MainControlLayoutIds.BTN_SECRET_SUBMIT.toString());
        windowConfig.addComponent(secretSubmit, MainControlLayoutIds.BTN_SECRET_SUBMIT.toString());
        secretSubmit.setClickShortcut(KeyCode.ENTER);
        
        // Subwindow inside subwindow
        
        final Window ftpWindow = new Window();
        final CustomLayout bfluxftpConfig = new CustomLayout("ftp_Jflux_config");
        final VerticalLayout acdcContent = new VerticalLayout();
        ftpWindow.setContent(bfluxftpConfig);
        ftpWindow.setModal(true);
        ftpWindow.setHeight("400px");
        ftpWindow.setWidth("700px");
        
        // Fields in ftpwindow
        
        final TextField hostName = new TextField();
        hostName.setId(MainControlLayoutIds.TXT_HOST_NAME.toString());
        hostName.setValue(HOSTNAME);
        hostName.setEnabled(false);
        bfluxftpConfig.addComponent(hostName, MainControlLayoutIds.TXT_HOST_NAME.toString());
        
        final ComboBox envCombo2 = new ComboBox();
        envCombo2.setId(MainControlLayoutIds.LST_FOR_WINDOW_ENVT.toString());
        envCombo2.setEnabled(false);
        envCombo2.setNullSelectionAllowed(false);
        envCombo2.addItems(controller.getSettings().getConfig().getEnvsByName().keySet());
        envCombo2.select(controller.getSettings().getConfig().getDefaultEnvId());
        bfluxftpConfig.addComponent(envCombo2, MainControlLayoutIds.LST_FOR_WINDOW_ENVT.toString());
        
        final TextField portNo = new TextField();
        portNo.setId(MainControlLayoutIds.TXT_PORT_NO.toString());
        portNo.setValue("21");
        portNo.setEnabled(false);
        bfluxftpConfig.addComponent(portNo, MainControlLayoutIds.TXT_PORT_NO.toString());
        
        final TextField userName = new TextField();
        userName.setId(MainControlLayoutIds.TXT_USR_NAME.toString());
        userName.setValue(USERNAME);
        userName.setEnabled(false);
        bfluxftpConfig.addComponent(userName, MainControlLayoutIds.TXT_USR_NAME.toString());
        
        final TextField ftpPass = new TextField();
        ftpPass.setId(MainControlLayoutIds.TXT_FTP_PASS.toString());
        ftpPass.setValue(PASSWORD);
        ftpPass.setEnabled(false);
        bfluxftpConfig.addComponent(ftpPass, MainControlLayoutIds.TXT_FTP_PASS.toString());
        
        final TextField ftpDir = new TextField();
        ftpDir.setId(MainControlLayoutIds.TXT_FTP_DIR.toString());
        ftpDir.setValue("/Vendors/Integration/Crest/In/");
        ftpDir.setEnabled(false);
        bfluxftpConfig.addComponent(ftpDir, MainControlLayoutIds.TXT_FTP_DIR.toString());
        
        final Button edit = new Button("Edit");
        edit.setId(MainControlLayoutIds.BTN_EDIT.toString());
        
        bfluxftpConfig.addComponent(edit, MainControlLayoutIds.BTN_EDIT.toString());
        
        final Button save = new Button("Save");
        save.setId(MainControlLayoutIds.BTN_SAVE.toString());
        save.setEnabled(false);
        bfluxftpConfig.addComponent(save, MainControlLayoutIds.BTN_SAVE.toString());
        layout.addComponent(acdcContent);
        edit.addClickListener(new Button.ClickListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                envCombo2.setEnabled(true);
                hostName.setEnabled(true);
                portNo.setEnabled(true);
                userName.setEnabled(true);
                ftpPass.setEnabled(true);
                ftpDir.setEnabled(true);
                
            }
        });
        
        secretSubmit.addClickListener(new Button.ClickListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                final String key = secretKey.getValue();
                if (key.equals("jfluxext@admin")) {
                    layout.getUI().getUI().addWindow(ftpWindow);
                    
                } else {
                    final Notification notif = new Notification(
                            "Incorrect key ,please contact ViV admin team.", Type.ERROR_MESSAGE);
                    notif.setDelayMsec(5000);
                    notif.setPosition(Position.MIDDLE_CENTER);
                    notif.show(Page.getCurrent());
                }
                
            }
            
        });
        ftpWindow.addCloseListener(new CloseListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void windowClose(final CloseEvent e) {
                layout.getUI().getUI().removeWindow(subWindow);
            }
        });
        
        config.addClickListener(new Button.ClickListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void buttonClick(final Button.ClickEvent clickEvent) {
                layout.getUI().getUI().addWindow(subWindow);
            }
        });
        
        envCombo2.addValueChangeListener(new ValueChangeListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                final String envt = envCombo2.getValue().toString();
                
                switch (envt) {
                    case "Integration":
                        hostName.setValue(HOSTNAME);
                        portNo.setValue("21");
                        userName.setValue(USERNAME);
                        ftpPass.setValue(PASSWORD);
                        ftpDir.setValue("/Vendors/Integration/Crest/In/");
                        break;
                    case "Acceptance":
                        hostName.setValue("senldogo0451.springer-sbm.com");
                        portNo.setValue("21");
                        userName.setValue("jwft4jflux");
                        ftpPass.setValue("xulfj4tfwj");
                        ftpDir.setValue(
                                "/u04/spct/work/JFlux/ImportFromVendor/vendors/acdc4jwft/In/");
                        break;
                    case "Development":
                        hostName.setValue(HOSTNAME);
                        portNo.setValue("21");
                        userName.setValue(USERNAME);
                        ftpPass.setValue(PASSWORD);
                        ftpDir.setValue("/Vendors/Development/Crest/In/");
                        break;
                    case "Staging":
                        hostName.setValue(HOSTNAME);
                        portNo.setValue("21");
                        userName.setValue(USERNAME);
                        ftpPass.setValue(PASSWORD);
                        ftpDir.setValue("/Vendors/Staging/Crest/In/");
                        break;
                    
                }
                
            }
        });
        
        // Vendor Selector
        
        vendorSelector.setId(MainControlLayoutIds.LST_VENDOR_SELECT.toString());
        vendorSelector.addItems((Object[]) Vendor.values());
        vendorSelector.select(Vendor.Crest);
        
        vendorSelector.addValueChangeListener(new Property.ValueChangeListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = 8970554910588693956L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                controller.setVendor((Vendor) event.getProperty().getValue());
                
            }
        });
        layout.addComponent(vendorSelector, vendorSelector.getId());
        
        // Environment Selector
        
        envSelector.setId(MainControlLayoutIds.LST_ENV_SELECT.toString());
        envSelector.addItems(controller.getSettings().getConfig().getEnvsByName().keySet());
        envSelector.setValue(controller.getSettings().getConfig().getDefaultEnvId());
        envSelector.select(controller.getSettings().getConfig().getEnvsByName());
        envSelector.setNullSelectionAllowed(false);
        envSelector.addValueChangeListener(new Property.ValueChangeListener() {
            
            
            public static final long serialVersionUID = 5074935535556972272L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                controller.setEnv((String) event.getProperty().getValue());
                
                envVal = (String) envSelector.getValue();
                
            }
            
        });
        layout.addComponent(envSelector, envSelector.getId());
        
        // Articles count
        
        artCountSelector.setId(MainControlLayoutIds.LST_ART_COUNT.toString());
        final Integer int1 = new Integer(1);
        artCountSelector.addItem();
        artCountSelector.addItem(new Integer(2));
        artCountSelector.addItem(new Integer(3));
        artCountSelector.addItem(new Integer(4));
        artCountSelector.addItem(new Integer(5));
        artCountSelector.select(int1);
        
        artCountSelector.addValueChangeListener(new Property.ValueChangeListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = -760296036733305090L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                controller.setArticleCount((Integer) event.getProperty().getValue());
                controller.reArrangeTabs();
            }
        });
        layout.addComponent(artCountSelector, artCountSelector.getId());
        
        // tabsheet
        final TabSheet tabsheet = new TabSheet();
        tabsheet.setId(MainControlLayoutIds.TABSHEET_INFO.toString());
        
        layout.addComponent(tabsheet, tabsheet.getId());
        
        // JobSheet Viewer
        final TextArea txtXMViewer = new TextArea();
        txtXMViewer.setId(MainControlLayoutIds.JobSheetXMLContainer.toString());
        
        layout.addComponent(txtXMViewer, txtXMViewer.getId());
        // Stage Selector
        jobSheetSelector = new NativeSelect();
        jobSheetSelector.setId(MainControlLayoutIds.LST_JS_SELECT.toString());
        jobSheetSelector.addItems((Object[]) Stage.values());
        jobSheetSelector.select(Stage.S100EXTERNAL);
        jobSheetSelector.setNullSelectionAllowed(false);
        jobSheetSelector.addValueChangeListener(new Property.ValueChangeListener() {
            
            
            private static final long serialVersionUID = 8076218768109569524L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                
                controller.setStage((Stage) event.getProperty().getValue());
                controller.reArrangeTabs();
                controller.showTabs();
                if (jobSheetSelector.getValue().equals(Stage.S200EXTERNAL)) {
                    envSelector.removeItem("Staging");
                    envSelector.removeItem("Development");
                    
                    vendorSelector.setEnabled(false);
                    
                    artCountSelector.setEnabled(false);
                } else {
                    
                    vendorSelector.setEnabled(true);
                    artCountSelector.setEnabled(true);
                    envSelector.addItems(
                            controller.getSettings().getConfig().getEnvsByName().keySet());
                    
                }
            }
        });
        
        layout.addComponent(jobSheetSelector, jobSheetSelector.getId());
        
    }
    
    public static String getEnvVal() {
        System.out.println("env val is " + envVal);
        return envVal;
    }
    
    public void setEnvVal(final String envtval) {
        this.envVal = envtval;
    }
    
}
