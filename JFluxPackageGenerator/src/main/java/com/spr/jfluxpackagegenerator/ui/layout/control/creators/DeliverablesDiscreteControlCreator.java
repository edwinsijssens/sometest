package com.spr.jfluxpackagegenerator.ui.layout.control.creators;

import com.spr.jfluxpackagegenerator.model.enums.Decision;
import com.spr.jfluxpackagegenerator.ui.layout.MainUIController;
import com.spr.jfluxpackagegenerator.ui.layout.id.DeliverablesDiscreteLayoutIds;
import com.spr.packagegenerator.constants.PackageGeneratorConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Link;

public class DeliverablesDiscreteControlCreator implements LayoutControlCreator {
    
    
    @Override
    public void createComponents(final CustomLayout layout, final MainUIController controller) {
        // Start Tag Deliverables for Discrete Objects //
        final ComboBox aXMLWithBodyBox = new ComboBox();
        aXMLWithBodyBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_XWB.toString());
        aXMLWithBodyBox.addItems((Object[]) Decision.getBooleanValues());
        aXMLWithBodyBox.select(Decision.No);
        layout.addComponent(aXMLWithBodyBox, DeliverablesDiscreteLayoutIds.LST_DEL_XWB.toString());
        
        final ComboBox aXMLWithBodyRefBox = new ComboBox();
        aXMLWithBodyRefBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_XWBRO.toString());
        aXMLWithBodyRefBox.addItems((Object[]) Decision.getBooleanValues());
        aXMLWithBodyRefBox.select(Decision.No);
        layout.addComponent(aXMLWithBodyRefBox,
                DeliverablesDiscreteLayoutIds.LST_DEL_XWBRO.toString());
        
        final ComboBox aOnlinePDFBox = new ComboBox();
        aOnlinePDFBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_OPDF.toString());
        aOnlinePDFBox.addItems((Object[]) Decision.getBooleanValues());
        aOnlinePDFBox.select(Decision.No);
        layout.addComponent(aOnlinePDFBox, DeliverablesDiscreteLayoutIds.LST_DEL_OPDF.toString());
        
        final ComboBox aPrintPDFBox = new ComboBox();
        aPrintPDFBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_PPDF.toString());
        aPrintPDFBox.addItems((Object[]) Decision.getBooleanValues());
        aPrintPDFBox.select(Decision.No);
        layout.addComponent(aPrintPDFBox, DeliverablesDiscreteLayoutIds.LST_DEL_PPDF.toString());
        
        final ComboBox aTEXBox = new ComboBox();
        aTEXBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_TEX.toString());
        aTEXBox.addItems((Object[]) Decision.getBooleanValues());
        aTEXBox.select(Decision.No);
        layout.addComponent(aTEXBox, DeliverablesDiscreteLayoutIds.LST_DEL_TEX.toString());
        
        final ComboBox aOnlineMediaObjectBox = new ComboBox();
        aOnlineMediaObjectBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_OMO.toString());
        aOnlineMediaObjectBox.addItem(Decision.No);
        aOnlineMediaObjectBox.addItem(Decision.IfApplies);
        aOnlineMediaObjectBox.select(Decision.No);
        layout.addComponent(aOnlineMediaObjectBox,
                DeliverablesDiscreteLayoutIds.LST_DEL_OMO.toString());
        
        final ComboBox aPrintMediaObjectBox = new ComboBox();
        aPrintMediaObjectBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_PMO.toString());
        aPrintMediaObjectBox.addItem(Decision.No);
        aPrintMediaObjectBox.addItem(Decision.IfApplies);
        aPrintMediaObjectBox.select(Decision.No);
        layout.addComponent(aPrintMediaObjectBox,
                DeliverablesDiscreteLayoutIds.LST_DEL_PMO.toString());
        
        final ComboBox aReferencePDFBox = new ComboBox();
        aReferencePDFBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_RPDF.toString());
        aReferencePDFBox.addItems((Object[]) Decision.getBooleanValues());
        aReferencePDFBox.select(Decision.No);
        layout.addComponent(aReferencePDFBox,
                DeliverablesDiscreteLayoutIds.LST_DEL_RPDF.toString());
        
        final ComboBox aAuthorFeedbackPDFBox = new ComboBox();
        aAuthorFeedbackPDFBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_AFPDF.toString());
        aAuthorFeedbackPDFBox.addItems((Object[]) Decision.getBooleanValues());
        aAuthorFeedbackPDFBox.select(Decision.No);
        layout.addComponent(aAuthorFeedbackPDFBox,
                DeliverablesDiscreteLayoutIds.LST_DEL_AFPDF.toString());
        
        final ComboBox aDeltaPDFBox = new ComboBox();
        aDeltaPDFBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_DPDF.toString());
        aDeltaPDFBox.addItems((Object[]) Decision.getBooleanValues());
        aDeltaPDFBox.select(Decision.No);
        layout.addComponent(aDeltaPDFBox, DeliverablesDiscreteLayoutIds.LST_DEL_DPDF.toString());
        
        final ComboBox aCopyrightTransferBox = new ComboBox();
        aCopyrightTransferBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_CPYT.toString());
        aCopyrightTransferBox.addItems((Object[]) Decision.values());
        aCopyrightTransferBox.select(Decision.No);
        layout.addComponent(aCopyrightTransferBox,
                DeliverablesDiscreteLayoutIds.LST_DEL_CPYT.toString());
        
        final ComboBox aOpenAccessBox = new ComboBox();
        aOpenAccessBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_OAS.toString());
        aOpenAccessBox.addItem(Decision.No);
        aOpenAccessBox.addItem(Decision.IfApplies);
        aOpenAccessBox.select(Decision.No);
        layout.addComponent(aOpenAccessBox, DeliverablesDiscreteLayoutIds.LST_DEL_OAS.toString());
        
        final ComboBox aOffprintOrderBox = new ComboBox();
        aOffprintOrderBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_OPO.toString());
        aOffprintOrderBox.addItem(Decision.No);
        aOffprintOrderBox.addItem(Decision.IfApplies);
        aOffprintOrderBox.select(Decision.No);
        layout.addComponent(aOffprintOrderBox,
                DeliverablesDiscreteLayoutIds.LST_DEL_OPO.toString());
        
        final ComboBox aPitStopReportBox = new ComboBox();
        aPitStopReportBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_PSR.toString());
        aPitStopReportBox.addItems((Object[]) Decision.getBooleanValues());
        aPitStopReportBox.select(Decision.Yes);
        layout.addComponent(aPitStopReportBox,
                DeliverablesDiscreteLayoutIds.LST_DEL_PSR.toString());
        
        final ComboBox aPRSMetadataBox = new ComboBox();
        aPRSMetadataBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_PRSM.toString());
        aPRSMetadataBox.addItems((Object[]) Decision.getBooleanValues());
        aPRSMetadataBox.select(Decision.No);
        layout.addComponent(aPRSMetadataBox, DeliverablesDiscreteLayoutIds.LST_DEL_PRSM.toString());
        
        final ComboBox aManuscriptBox = new ComboBox();
        aManuscriptBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_MAN.toString());
        aManuscriptBox.addItems((Object[]) Decision.getBooleanValues());
        aManuscriptBox.select(Decision.No);
        layout.addComponent(aManuscriptBox, DeliverablesDiscreteLayoutIds.LST_DEL_MAN.toString());
        
        final ComboBox aEpsilonPDF = new ComboBox();
        aEpsilonPDF.setId(DeliverablesDiscreteLayoutIds.LST_DEL_EPDF.toString());
        aEpsilonPDF.addItems((Object[]) Decision.getBooleanValues());
        aEpsilonPDF.select(Decision.No);
        layout.addComponent(aEpsilonPDF, DeliverablesDiscreteLayoutIds.LST_DEL_EPDF.toString());
        
        final ComboBox aCorrectionSheet = new ComboBox();
        aCorrectionSheet.setId(DeliverablesDiscreteLayoutIds.LST_DEL_CORS.toString());
        aCorrectionSheet.addItem(Decision.IfApplies);
        aCorrectionSheet.select(Decision.IfApplies);
        layout.addComponent(aCorrectionSheet,
                DeliverablesDiscreteLayoutIds.LST_DEL_CORS.toString());
        
        final ComboBox aPeerReviewPDFBox = new ComboBox();
        aPeerReviewPDFBox.setId(DeliverablesDiscreteLayoutIds.LST_DEL_PRP.toString());
        aPeerReviewPDFBox.addItems((Object[]) Decision.getBooleanValues());
        aPeerReviewPDFBox.select(Decision.No);
        layout.addComponent(aPeerReviewPDFBox,
                DeliverablesDiscreteLayoutIds.LST_DEL_PRP.toString());
        
        final Link delDisObjRemovelink = new Link();
        delDisObjRemovelink.setId(DeliverablesDiscreteLayoutIds.LNK_CONP_REM.toString());
        delDisObjRemovelink.setCaption(PackageGeneratorConstants.REMOVE_FROM_JS);
        layout.addComponent(delDisObjRemovelink,
                DeliverablesDiscreteLayoutIds.LNK_DEL_REM.toString());
        
        final Button btndelDisObjAddJobSheetDetails = new Button();
        btndelDisObjAddJobSheetDetails.setId(DeliverablesDiscreteLayoutIds.BTN_DEL_ADD.toString());
        
        btndelDisObjAddJobSheetDetails.setCaption(PackageGeneratorConstants.ADD_TO_JS);
        
        btndelDisObjAddJobSheetDetails.addClickListener(new Button.ClickListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = -4644547975636340055L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                controller.createJobSheet();
                controller.showJobsheet();
                
            }
        });
        layout.addComponent(btndelDisObjAddJobSheetDetails,
                DeliverablesDiscreteLayoutIds.BTN_DEL_ADD.toString());
        // End of Deliverables for Discrete Objects //
        
    }
    
}
