package com.spr.jfluxpackagegenerator.ui.layout.control.creators;

import com.spr.jfluxpackagegenerator.model.enums.Decision;
import com.spr.jfluxpackagegenerator.ui.layout.MainUIController;
import com.spr.jfluxpackagegenerator.ui.layout.id.FilesToPublisherLayoutIds;
import com.spr.packagegenerator.constants.PackageGeneratorConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Link;

public class FilesToPublisherControlCreator implements LayoutControlCreator {
    
    
    @Override
    public void createComponents(final CustomLayout layout, final MainUIController controller) {
        
        // Start Add Files to Publisher
        final ComboBox aARootFileBox = new ComboBox();
        aARootFileBox.setId(FilesToPublisherLayoutIds.LST_FTP_ARF.toString());
        aARootFileBox.addItems((Object[]) Decision.getBooleanValues());
        aARootFileBox.select(Decision.No);
        layout.addComponent(aARootFileBox, FilesToPublisherLayoutIds.LST_FTP_ARF.toString());
        
        final ComboBox aARelatedObjectFileBox = new ComboBox();
        aARelatedObjectFileBox.setId(FilesToPublisherLayoutIds.LST_FTP_AROF.toString());
        aARelatedObjectFileBox.addItems((Object[]) Decision.getBooleanValues());
        aARelatedObjectFileBox.select(Decision.No);
        aARelatedObjectFileBox.setEnabled(false); // TODO: check what it exactly is
        layout.addComponent(aARelatedObjectFileBox,
                FilesToPublisherLayoutIds.LST_FTP_AROF.toString());
        
        final ComboBox aVideoObject1Box = new ComboBox();
        aVideoObject1Box.setId(FilesToPublisherLayoutIds.LST_FTP_VO1.toString());
        aVideoObject1Box.addItems((Object[]) Decision.getBooleanValues());
        aVideoObject1Box.select(Decision.No);
        layout.addComponent(aVideoObject1Box, FilesToPublisherLayoutIds.LST_FTP_VO1.toString());
        
        final ComboBox aVideoObject2Box = new ComboBox();
        aVideoObject2Box.setId(FilesToPublisherLayoutIds.LST_FTP_VO2.toString());
        aVideoObject2Box.addItems((Object[]) Decision.getBooleanValues());
        aVideoObject2Box.select(Decision.No);
        layout.addComponent(aVideoObject2Box, FilesToPublisherLayoutIds.LST_FTP_VO2.toString());
        
        final ComboBox aAudioObject1Box = new ComboBox();
        aAudioObject1Box.setId(FilesToPublisherLayoutIds.LST_FTP_AO1.toString());
        aAudioObject1Box.addItems((Object[]) Decision.getBooleanValues());
        aAudioObject1Box.select(Decision.No);
        layout.addComponent(aAudioObject1Box, FilesToPublisherLayoutIds.LST_FTP_AO1.toString());
        
        final ComboBox aAudioObject2Box = new ComboBox();
        aAudioObject2Box.setId(FilesToPublisherLayoutIds.LST_FTP_AO2.toString());
        aAudioObject2Box.addItems((Object[]) Decision.getBooleanValues());
        aAudioObject2Box.select(Decision.No);
        layout.addComponent(aAudioObject2Box, FilesToPublisherLayoutIds.LST_FTP_AO2.toString());
        
        final ComboBox aImageObject1Box = new ComboBox();
        aImageObject1Box.setId(FilesToPublisherLayoutIds.LST_FTP_IO1.toString());
        aImageObject1Box.addItems((Object[]) Decision.getBooleanValues());
        aImageObject1Box.select(Decision.No);
        layout.addComponent(aImageObject1Box, FilesToPublisherLayoutIds.LST_FTP_IO1.toString());
        
        final ComboBox aImageObject2Box = new ComboBox();
        aImageObject2Box.setId(FilesToPublisherLayoutIds.LST_FTP_IO2.toString());
        aImageObject2Box.addItems((Object[]) Decision.getBooleanValues());
        aImageObject2Box.select(Decision.No);
        layout.addComponent(aImageObject2Box, FilesToPublisherLayoutIds.LST_FTP_IO2.toString());
        
        final ComboBox aDataObject1Box = new ComboBox();
        aDataObject1Box.setId(FilesToPublisherLayoutIds.LST_FTP_DO1.toString());
        aDataObject1Box.addItems((Object[]) Decision.getBooleanValues());
        aDataObject1Box.select(Decision.No);
        layout.addComponent(aDataObject1Box, FilesToPublisherLayoutIds.LST_FTP_DO1.toString());
        
        final ComboBox aDataObject2Box = new ComboBox();
        aDataObject2Box.setId(FilesToPublisherLayoutIds.LST_FTP_DO2.toString());
        aDataObject2Box.addItems((Object[]) Decision.getBooleanValues());
        aDataObject2Box.select(Decision.No);
        layout.addComponent(aDataObject2Box, FilesToPublisherLayoutIds.LST_FTP_DO2.toString());
        
        final ComboBox aOnlinePDFFileBox = new ComboBox();
        aOnlinePDFFileBox.setId(FilesToPublisherLayoutIds.LST_FTP_OPDF.toString());
        aOnlinePDFFileBox.addItems((Object[]) Decision.getBooleanValues());
        aOnlinePDFFileBox.select(Decision.No);
        layout.addComponent(aOnlinePDFFileBox, FilesToPublisherLayoutIds.LST_FTP_OPDF.toString());
        
        final ComboBox aPrintPDFFileBox = new ComboBox();
        aPrintPDFFileBox.setId(FilesToPublisherLayoutIds.LST_FTP_PPDF.toString());
        aPrintPDFFileBox.addItems((Object[]) Decision.getBooleanValues());
        aPrintPDFFileBox.select(Decision.No);
        layout.addComponent(aPrintPDFFileBox, FilesToPublisherLayoutIds.LST_FTP_PPDF.toString());
        
        final ComboBox aOffprintOrderFileBox = new ComboBox();
        aOffprintOrderFileBox.setId(FilesToPublisherLayoutIds.LST_FTP_OOPDF.toString());
        aOffprintOrderFileBox.addItems((Object[]) Decision.getBooleanValues());
        aOffprintOrderFileBox.select(Decision.No);
        layout.addComponent(aOffprintOrderFileBox,
                FilesToPublisherLayoutIds.LST_FTP_OOPDF.toString());
        
        final ComboBox aAuthorFeedbackPDFFileBox = new ComboBox();
        aAuthorFeedbackPDFFileBox.setId(FilesToPublisherLayoutIds.LST_FTP_AFPDF.toString());
        aAuthorFeedbackPDFFileBox.addItems((Object[]) Decision.getBooleanValues());
        aAuthorFeedbackPDFFileBox.select(Decision.No);
        layout.addComponent(aAuthorFeedbackPDFFileBox,
                FilesToPublisherLayoutIds.LST_FTP_AFPDF.toString());
        
        final ComboBox aCheckListPDFFileBox = new ComboBox();
        aCheckListPDFFileBox.setId(FilesToPublisherLayoutIds.LST_FTP_CPDF.toString());
        aCheckListPDFFileBox.addItems((Object[]) Decision.getBooleanValues());
        aCheckListPDFFileBox.select(Decision.No);
        layout.addComponent(aCheckListPDFFileBox,
                FilesToPublisherLayoutIds.LST_FTP_CPDF.toString());
        
        final ComboBox aCopyrightTransferFileBox = new ComboBox();
        aCopyrightTransferFileBox.setId(FilesToPublisherLayoutIds.LST_FTP_CTPDF.toString());
        aCopyrightTransferFileBox.addItems((Object[]) Decision.getBooleanValues());
        aCopyrightTransferFileBox.select(Decision.No);
        layout.addComponent(aCopyrightTransferFileBox,
                FilesToPublisherLayoutIds.LST_FTP_CTPDF.toString());
        
        final ComboBox aDeltaPDFFileBox = new ComboBox();
        aDeltaPDFFileBox.setId(FilesToPublisherLayoutIds.LST_FTP_DPDF.toString());
        aDeltaPDFFileBox.addItems((Object[]) Decision.getBooleanValues());
        aDeltaPDFFileBox.select(Decision.No);
        layout.addComponent(aDeltaPDFFileBox, FilesToPublisherLayoutIds.LST_FTP_DPDF.toString());
        
        final ComboBox aEpsilonFilePDFBox = new ComboBox();
        aEpsilonFilePDFBox.setId(FilesToPublisherLayoutIds.LST_FTP_EPDF.toString());
        aEpsilonFilePDFBox.addItems((Object[]) Decision.getBooleanValues());
        aEpsilonFilePDFBox.select(Decision.No);
        layout.addComponent(aEpsilonFilePDFBox, FilesToPublisherLayoutIds.LST_FTP_EPDF.toString());
        
        final ComboBox aPitStopReportPDFFileBox = new ComboBox();
        aPitStopReportPDFFileBox.setId(FilesToPublisherLayoutIds.LST_FTP_PSRPDF.toString());
        aPitStopReportPDFFileBox.addItem("No");
        aPitStopReportPDFFileBox.addItem("Yes - without Error");
        aPitStopReportPDFFileBox.addItem("Yes - with Error");
        aPitStopReportPDFFileBox.select("Yes - without Error");
        layout.addComponent(aPitStopReportPDFFileBox,
                FilesToPublisherLayoutIds.LST_FTP_PSRPDF.toString());
        
        final ComboBox aOpenAccessStatPDFBox = new ComboBox();
        aOpenAccessStatPDFBox.setId(FilesToPublisherLayoutIds.LST_FTP_OASPDF.toString());
        aOpenAccessStatPDFBox.addItems((Object[]) Decision.getBooleanValues());
        aOpenAccessStatPDFBox.select(Decision.No);
        layout.addComponent(aOpenAccessStatPDFBox,
                FilesToPublisherLayoutIds.LST_FTP_OASPDF.toString());
        
        final ComboBox aPRSMetadataFileBox = new ComboBox();
        aPRSMetadataFileBox.setId(FilesToPublisherLayoutIds.LST_FTP_PRSM.toString());
        aPRSMetadataFileBox.addItems((Object[]) Decision.getBooleanValues());
        aPRSMetadataFileBox.select(Decision.No);
        layout.addComponent(aPRSMetadataFileBox, FilesToPublisherLayoutIds.LST_FTP_PRSM.toString());
        
        final ComboBox aReferencePDFFileBox = new ComboBox();
        aReferencePDFFileBox.setId(FilesToPublisherLayoutIds.LST_FTP_RPDF.toString());
        aReferencePDFFileBox.addItems((Object[]) Decision.getBooleanValues());
        aReferencePDFFileBox.select(Decision.No);
        layout.addComponent(aReferencePDFFileBox,
                FilesToPublisherLayoutIds.LST_FTP_RPDF.toString());
        
        final ComboBox aCorrectionSheetXMLBox = new ComboBox();
        aCorrectionSheetXMLBox.setId(FilesToPublisherLayoutIds.LST_FTP_CSXML.toString());
        aCorrectionSheetXMLBox.addItems((Object[]) Decision.getBooleanValues());
        aCorrectionSheetXMLBox.select(Decision.No);
        layout.addComponent(aCorrectionSheetXMLBox,
                FilesToPublisherLayoutIds.LST_FTP_CSXML.toString());
        
        final ComboBox aEPubFileBox = new ComboBox();
        aEPubFileBox.setId(FilesToPublisherLayoutIds.LST_FTP_EPUB.toString());
        aEPubFileBox.addItems((Object[]) Decision.getBooleanValues());
        aEPubFileBox.select(Decision.No);
        layout.addComponent(aEPubFileBox, FilesToPublisherLayoutIds.LST_FTP_EPUB.toString());
        
        final ComboBox aManuscriptFileBox = new ComboBox();
        aManuscriptFileBox.setId(FilesToPublisherLayoutIds.LST_FTP_MAN.toString());
        aManuscriptFileBox.addItems((Object[]) Decision.getBooleanValues());
        aManuscriptFileBox.select(Decision.No);
        layout.addComponent(aManuscriptFileBox, FilesToPublisherLayoutIds.LST_FTP_MAN.toString());
        
        final ComboBox aTexFileBox = new ComboBox();
        aTexFileBox.setId(FilesToPublisherLayoutIds.LST_FTP_TEX.toString());
        aTexFileBox.addItems((Object[]) Decision.getBooleanValues());
        aTexFileBox.select(Decision.No);
        layout.addComponent(aTexFileBox, FilesToPublisherLayoutIds.LST_FTP_TEX.toString());
        
        final Link filePublisherRemovelink = new Link();
        filePublisherRemovelink.setId(FilesToPublisherLayoutIds.LNK_FTP_REM.toString());
        filePublisherRemovelink.setCaption(PackageGeneratorConstants.REMOVE_FROM_JS);
        layout.addComponent(filePublisherRemovelink,
                FilesToPublisherLayoutIds.LNK_FTP_REM.toString());
        
        final Button btnFilePublisherAddJobSheetDetails = new Button();
        btnFilePublisherAddJobSheetDetails.setId(FilesToPublisherLayoutIds.BTN_FTP_ADD.toString());
        btnFilePublisherAddJobSheetDetails.setCaption(PackageGeneratorConstants.ADD_TO_JS);
        
        btnFilePublisherAddJobSheetDetails.addClickListener(new Button.ClickListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = -1136826117299414207L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                controller.createJobSheet();
                controller.showJobsheet();
            }
        });
        layout.addComponent(btnFilePublisherAddJobSheetDetails,
                FilesToPublisherLayoutIds.BTN_FTP_ADD.toString());
        // End Add Files to Publisher
        
    }
}