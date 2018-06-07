package com.spr.jfluxpackagegenerator.ui.layout.control.creators;

import com.spr.jfluxpackagegenerator.model.enums.Decision;
import com.spr.jfluxpackagegenerator.ui.layout.MainUIController;
import com.spr.jfluxpackagegenerator.ui.layout.id.DeliverablesCompoundLayoutIds;
import com.spr.packagegenerator.constants.PackageGeneratorConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Link;

public class DeliverablesCompoundControlCreator implements LayoutControlCreator {
    
    
    @Override
    public void createComponents(final CustomLayout layout, final MainUIController controller) {
        
        final ComboBox aCoverPDFBox = new ComboBox();
        aCoverPDFBox.setId(DeliverablesCompoundLayoutIds.LST_DELC_CPP.toString());
        aCoverPDFBox.addItems((Object[]) Decision.getBooleanValues());
        aCoverPDFBox.select(Decision.No);
        layout.addComponent(aCoverPDFBox, DeliverablesCompoundLayoutIds.LST_DELC_CPP.toString());
        
        final ComboBox aCoverFigureBox = new ComboBox();
        aCoverFigureBox.setId(DeliverablesCompoundLayoutIds.LST_DELC_CF.toString());
        aCoverFigureBox.addItems((Object[]) Decision.getBooleanValues());
        aCoverFigureBox.select(Decision.No);
        layout.addComponent(aCoverFigureBox, DeliverablesCompoundLayoutIds.LST_DELC_CF.toString());
        
        final ComboBox aFPPBox = new ComboBox();
        aFPPBox.setId(DeliverablesCompoundLayoutIds.LST_DELC_FPP.toString());
        aFPPBox.addItems((Object[]) Decision.getBooleanValues());
        aFPPBox.select(Decision.No);
        layout.addComponent(aFPPBox, DeliverablesCompoundLayoutIds.LST_DELC_FPP.toString());
        
        final ComboBox aBPPBox = new ComboBox();
        aBPPBox.setId(DeliverablesCompoundLayoutIds.LST_DELC_BPP.toString());
        aBPPBox.addItems((Object[]) Decision.getBooleanValues());
        aBPPBox.select(Decision.No);
        layout.addComponent(aBPPBox, DeliverablesCompoundLayoutIds.LST_DELC_BPP.toString());
        
        final ComboBox aAPPBox = new ComboBox();
        aAPPBox.setId(DeliverablesCompoundLayoutIds.LST_DELC_APP.toString());
        aAPPBox.addItem(Decision.No);
        aAPPBox.addItem(Decision.IfApplies);
        aAPPBox.select(Decision.No);
        layout.addComponent(aAPPBox, DeliverablesCompoundLayoutIds.LST_DELC_APP.toString());
        
        final ComboBox aDCOBox = new ComboBox();
        aDCOBox.setId(DeliverablesCompoundLayoutIds.LST_DELC_DCO.toString());
        aDCOBox.addItems((Object[]) Decision.getBooleanValues());
        aDCOBox.select(Decision.Yes);
        layout.addComponent(aDCOBox, DeliverablesCompoundLayoutIds.LST_DELC_DCO.toString());
        
        final ComboBox aDCPBox = new ComboBox();
        aDCPBox.setId(DeliverablesCompoundLayoutIds.LST_DELC_DCP.toString());
        aDCPBox.addItems((Object[]) Decision.getBooleanValues());
        aDCPBox.select(Decision.No);
        layout.addComponent(aDCPBox, DeliverablesCompoundLayoutIds.LST_DELC_DCP.toString());
        
        final ComboBox aPSRBox = new ComboBox();
        aPSRBox.setId(DeliverablesCompoundLayoutIds.LST_DELC_PSR.toString());
        aPSRBox.addItems((Object[]) Decision.getBooleanValues());
        aPSRBox.select(Decision.Yes);
        layout.addComponent(aPSRBox, DeliverablesCompoundLayoutIds.LST_DELC_PSR.toString());
        
        final ComboBox aDCXBBox = new ComboBox();
        aDCXBBox.setId(DeliverablesCompoundLayoutIds.LST_DELC_DCXB.toString());
        aDCXBBox.addItems((Object[]) Decision.values());
        aDCXBBox.select(Decision.No);
        layout.addComponent(aDCXBBox, DeliverablesCompoundLayoutIds.LST_DELC_DCXB.toString());
        
        final ComboBox aDCXRBox = new ComboBox();
        aDCXRBox.setId(DeliverablesCompoundLayoutIds.LST_DELC_DCXR.toString());
        aDCXRBox.addItems((Object[]) Decision.values());
        aDCXRBox.select(Decision.Yes);
        layout.addComponent(aDCXRBox, DeliverablesCompoundLayoutIds.LST_DELC_DCXR.toString());
        
        final ComboBox aDCOMOBox = new ComboBox();
        aDCOMOBox.setId(DeliverablesCompoundLayoutIds.LST_DELC_DCOMO.toString());
        aDCOMOBox.addItem(Decision.No);
        aDCOMOBox.addItem(Decision.IfApplies);
        aDCOMOBox.select(Decision.IfApplies);
        layout.addComponent(aDCOMOBox, DeliverablesCompoundLayoutIds.LST_DELC_DCOMO.toString());
        
        final ComboBox aIPPBox = new ComboBox();
        aIPPBox.setId(DeliverablesCompoundLayoutIds.LST_DELC_IPP.toString());
        aIPPBox.addItems((Object[]) Decision.values());
        aIPPBox.select(Decision.IfApplies);
        layout.addComponent(aIPPBox, DeliverablesCompoundLayoutIds.LST_DELC_IPP.toString());
        
        final ComboBox aCorrectionSheet = new ComboBox();
        aCorrectionSheet.setId(DeliverablesCompoundLayoutIds.LST_DELC_CS.toString());
        aCorrectionSheet.addItem(Decision.IfApplies);
        aCorrectionSheet.select(Decision.IfApplies);
        layout.addComponent(aCorrectionSheet, DeliverablesCompoundLayoutIds.LST_DELC_CS.toString());
        
        final Link delDisObjRemovelink = new Link();
        delDisObjRemovelink.setId(DeliverablesCompoundLayoutIds.LNK_DELC_REM.toString());
        delDisObjRemovelink.setCaption(PackageGeneratorConstants.REMOVE_FROM_JS);
        layout.addComponent(delDisObjRemovelink,
                DeliverablesCompoundLayoutIds.LNK_DELC_REM.toString());
        
        final Button btndelDisObjAddJobSheetDetails = new Button();
        btndelDisObjAddJobSheetDetails.setId(DeliverablesCompoundLayoutIds.BTN_DELC_ADD.toString());
        
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
                DeliverablesCompoundLayoutIds.BTN_DELC_ADD.toString());
        
    }
}
