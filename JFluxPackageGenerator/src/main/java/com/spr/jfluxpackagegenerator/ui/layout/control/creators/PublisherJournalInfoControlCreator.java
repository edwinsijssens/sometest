package com.spr.jfluxpackagegenerator.ui.layout.control.creators;

import com.spr.jfluxpackagegenerator.model.enums.NumberingStyle;
import com.spr.jfluxpackagegenerator.model.enums.OutputMedium;
import com.spr.jfluxpackagegenerator.ui.layout.MainUIController;
import com.spr.jfluxpackagegenerator.ui.layout.id.JournalInfoLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.PublisherInfoLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.validator.NotEmptyAttributeValidator;
import com.spr.packagegenerator.constants.PackageGeneratorConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;

public class PublisherJournalInfoControlCreator implements LayoutControlCreator {
    
    
    @Override
    public void createComponents(final CustomLayout layout, final MainUIController controller) {
        
        // Publisher Information section widgets
        final TextField pubNameField = new TextField();
        pubNameField.setId(PublisherInfoLayoutIds.TXT_PUB_NAME.toString());
        layout.addComponent(pubNameField, PublisherInfoLayoutIds.TXT_PUB_NAME.toString());
        
        final TextField pubImprintField = new TextField();
        layout.addComponent(pubImprintField, PublisherInfoLayoutIds.TXT_IMP_NM.toString());
        pubImprintField.setId(PublisherInfoLayoutIds.TXT_IMP_NM.toString());
        
        final TextField pubImprintPubLocField = new TextField();
        layout.addComponent(pubImprintPubLocField, PublisherInfoLayoutIds.TXT_PUB_LOC.toString());
        pubImprintPubLocField.setId(PublisherInfoLayoutIds.TXT_PUB_LOC.toString());
        
        final TextField pubURLField = new TextField();
        layout.addComponent(pubURLField, PublisherInfoLayoutIds.TXT_PUB_URL.toString());
        pubURLField.setId(PublisherInfoLayoutIds.TXT_PUB_URL.toString());
        
        // lnk_pub_info_rem..
        final Link pubRemovelink = new Link();
        pubRemovelink.setCaption(PackageGeneratorConstants.REMOVE_FROM_JS);
        layout.addComponent(pubRemovelink, PublisherInfoLayoutIds.LNK_PUB_INFO_REM.toString());
        pubRemovelink.setId(PublisherInfoLayoutIds.LNK_PUB_INFO_REM.toString());
        
        final Button btnPubAddJobSheetDetails = new Button();
        btnPubAddJobSheetDetails.setId(PublisherInfoLayoutIds.BTN_PUB_INFO_ADD.toString());
        btnPubAddJobSheetDetails.setCaption(PackageGeneratorConstants.ADD_TO_JS);
        
        btnPubAddJobSheetDetails.addClickListener(new Button.ClickListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = -7800313274393588305L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                controller.createJobSheet();
                controller.showJobsheet();
                
            }
        });
        //
        layout.addComponent(btnPubAddJobSheetDetails,
                PublisherInfoLayoutIds.BTN_PUB_INFO_ADD.toString());
        
        // Journal Information section widgets
        
        final ComboBox aProductTypeBox = new ComboBox();
        aProductTypeBox.setId(JournalInfoLayoutIds.LST_JPROD_TYPE.toString());
        aProductTypeBox.addItem("ArchiveJournal");
        aProductTypeBox.addItem("NonStandardArchiveJournal");
        aProductTypeBox.addItem("Magazine");
        aProductTypeBox.addItem("Legacy");
        layout.addComponent(aProductTypeBox, JournalInfoLayoutIds.LST_JPROD_TYPE.toString());
        
        final ComboBox aNumberStyleBox = new ComboBox();
        aNumberStyleBox.setId(JournalInfoLayoutIds.LST_JPROR_NUM.toString());
        aNumberStyleBox.addItems((Object[]) NumberingStyle.values());
        layout.addComponent(aNumberStyleBox, JournalInfoLayoutIds.LST_JPROR_NUM.toString());
        
        final ComboBox aOutputMediumBox = new ComboBox();
        aOutputMediumBox.setId(JournalInfoLayoutIds.LST_JPROR_OUT.toString());
        aOutputMediumBox.addItems((Object[]) OutputMedium.values());
        layout.addComponent(aOutputMediumBox, JournalInfoLayoutIds.LST_JPROR_OUT.toString());
        
        final TextField journalIDField = new TextField();
        journalIDField.setId(JournalInfoLayoutIds.TXT_JPROR_ID.toString());
        journalIDField.addValidator(new NotEmptyAttributeValidator("Journal ID must not be empty"));
        journalIDField.setValidationVisible(false);
        layout.addComponent(journalIDField, JournalInfoLayoutIds.TXT_JPROR_ID.toString());
        
        final TextField journalDOIField = new TextField();
        journalDOIField.setId(JournalInfoLayoutIds.TXT_JPROR_DOI.toString());
        layout.addComponent(journalDOIField, JournalInfoLayoutIds.TXT_JPROR_DOI.toString());
        
        final TextField journalPrintISSNField = new TextField();
        journalPrintISSNField.setId(JournalInfoLayoutIds.TXT_JPROR_PISSN.toString());
        layout.addComponent(journalPrintISSNField, JournalInfoLayoutIds.TXT_JPROR_PISSN.toString());
        
        final TextField journalElectronicISSNField = new TextField();
        journalElectronicISSNField.setId(JournalInfoLayoutIds.TXT_JPROR_EISSN.toString());
        layout.addComponent(journalElectronicISSNField,
                JournalInfoLayoutIds.TXT_JPROR_EISSN.toString());
        
        final TextField journalTitle = new TextField();
        journalTitle.setId(JournalInfoLayoutIds.TXT_JPROR_TITLE.toString());
        layout.addComponent(journalTitle, JournalInfoLayoutIds.TXT_JPROR_TITLE.toString());
        
        final TextField journalSubTitle = new TextField();
        journalSubTitle.setId(JournalInfoLayoutIds.TXT_JPROR_STITLE.toString());
        layout.addComponent(journalSubTitle, JournalInfoLayoutIds.TXT_JPROR_STITLE.toString());
        
        final TextField journalAbbTitle = new TextField();
        journalAbbTitle.setId(JournalInfoLayoutIds.TXT_JPROR_ATITLE.toString());
        layout.addComponent(journalAbbTitle, JournalInfoLayoutIds.TXT_JPROR_ATITLE.toString());
        
        final TextField journalPrimarySubject = new TextField();
        journalPrimarySubject.setId(JournalInfoLayoutIds.TXT_JPROR_PSUB.toString());
        layout.addComponent(journalPrimarySubject, JournalInfoLayoutIds.TXT_JPROR_PSUB.toString());
        
        final TextField journalSecSubject = new TextField();
        journalSecSubject.setId(JournalInfoLayoutIds.TXT_JPROR_SSUB.toString());
        layout.addComponent(journalSecSubject, JournalInfoLayoutIds.TXT_JPROR_SSUB.toString());
        
        final Link jouRemovelink = new Link();
        jouRemovelink.setCaption(PackageGeneratorConstants.REMOVE_FROM_JS);
        jouRemovelink.setId(JournalInfoLayoutIds.LNK_JOUR_INFO_REM.toString());
        layout.addComponent(jouRemovelink, JournalInfoLayoutIds.LNK_JOUR_INFO_REM.toString());
        
        final Button btnJouAddJobSheetDetails = new Button();
        btnJouAddJobSheetDetails.setId(JournalInfoLayoutIds.BTN_JOUR_INFO_ADD.toString());
        btnJouAddJobSheetDetails.setCaption(PackageGeneratorConstants.ADD_TO_JS);
        
        btnJouAddJobSheetDetails.addClickListener(new Button.ClickListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = 3110010859168479934L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                controller.createJobSheet();
                controller.showJobsheet();
                
            }
        });
        //
        layout.addComponent(btnJouAddJobSheetDetails,
                JournalInfoLayoutIds.BTN_JOUR_INFO_ADD.toString());
        
        // Generate Package Button details
        final Button btnGenPackage = new Button();
        btnGenPackage.setId(JournalInfoLayoutIds.BTN_GEN_PACK.toString());
        btnGenPackage.setCaption("Generate Package");
        btnGenPackage.addClickListener(new Button.ClickListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = 9203266166947905689L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                if (controller.validateFields()) {
                    controller.createJobSheet();
                    controller.showJobsheet();
                    controller.generatePackage();
                    controller.sendPackage();
                }
                
            }
        });
        //
        layout.addComponent(btnGenPackage, JournalInfoLayoutIds.BTN_GEN_PACK.toString());
        
    }
}
