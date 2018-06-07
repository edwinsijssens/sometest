package com.spr.jfluxpackagegenerator.ui.layout.control.creators;

import com.spr.jfluxpackagegenerator.model.enums.Suffix;
import com.spr.jfluxpackagegenerator.ui.layout.MainUIController;
import com.spr.jfluxpackagegenerator.ui.layout.id.ProductionInfoLayoutIds;
import com.spr.packagegenerator.constants.PackageGeneratorConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;

/**
 * The class
 * 
 * @author Alexey Dergalev
 */
public class ProductionInfoControlCreator implements LayoutControlCreator {
    
    
    @Override
    public void createComponents(final CustomLayout layout, final MainUIController controller) {
        
        // Start of Production Information
        final ComboBox prdPriorityLevel = new ComboBox();
        prdPriorityLevel.setId(ProductionInfoLayoutIds.LST_PRI_LVL.toString());
        prdPriorityLevel.addItem("Standard");
        prdPriorityLevel.addItem("High");
        layout.addComponent(prdPriorityLevel, ProductionInfoLayoutIds.LST_PRI_LVL.toString());
        
        final TextField prdSupplName = new TextField();
        prdSupplName.setId(ProductionInfoLayoutIds.TXT_SUP_NM.toString());
        layout.addComponent(prdSupplName, ProductionInfoLayoutIds.TXT_SUP_NM.toString());
        
        final Link prdInfoRemovelink = new Link();
        prdInfoRemovelink.setId(ProductionInfoLayoutIds.LNK_PINFO_REM.toString());
        prdInfoRemovelink.setCaption(PackageGeneratorConstants.REMOVE_FROM_JS);
        layout.addComponent(prdInfoRemovelink, ProductionInfoLayoutIds.LNK_PINFO_REM.toString());
        
        final Button btnPrdInfoAddJobSheetDetails = new Button();
        btnPrdInfoAddJobSheetDetails.setId(ProductionInfoLayoutIds.BTN_PINFO_ADD.toString());
        
        btnPrdInfoAddJobSheetDetails.setCaption(PackageGeneratorConstants.ADD_TO_JS);
        
        btnPrdInfoAddJobSheetDetails.addClickListener(new Button.ClickListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = 5416966271250901647L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                controller.createJobSheet();
                controller.showJobsheet();
                
            }
        });
        layout.addComponent(btnPrdInfoAddJobSheetDetails,
                ProductionInfoLayoutIds.BTN_PINFO_ADD.toString());
        
        final TextField prdStreet = new TextField();
        prdStreet.setId(ProductionInfoLayoutIds.TXT_SUP_STR.toString());
        layout.addComponent(prdStreet, ProductionInfoLayoutIds.TXT_SUP_STR.toString());
        
        final TextField prdContOrgName = new TextField();
        prdContOrgName.setId(ProductionInfoLayoutIds.TXT_SUP_ONM.toString());
        layout.addComponent(prdContOrgName, ProductionInfoLayoutIds.TXT_SUP_ONM.toString());
        
        final TextField prdContOrgDivision = new TextField();
        prdContOrgDivision.setId(ProductionInfoLayoutIds.TXT_SUP_ODIV.toString());
        layout.addComponent(prdContOrgDivision, ProductionInfoLayoutIds.TXT_SUP_ODIV.toString());
        
        final TextField prdPostbox = new TextField();
        prdPostbox.setId(ProductionInfoLayoutIds.TXT_SUP_PBX.toString());
        layout.addComponent(prdPostbox, ProductionInfoLayoutIds.TXT_SUP_PBX.toString());
        
        final TextField prdPostCode = new TextField();
        prdPostCode.setId(ProductionInfoLayoutIds.TXT_SUP_PCD.toString());
        layout.addComponent(prdPostCode, ProductionInfoLayoutIds.TXT_SUP_PCD.toString());
        
        final TextField prdCity = new TextField();
        prdCity.setId(ProductionInfoLayoutIds.TXT_SUP_CTY.toString());
        layout.addComponent(prdCity, ProductionInfoLayoutIds.TXT_SUP_CTY.toString());
        
        final TextField prdState = new TextField();
        prdState.setId(ProductionInfoLayoutIds.TXT_SUP_STA.toString());
        layout.addComponent(prdState, ProductionInfoLayoutIds.TXT_SUP_STA.toString());
        
        final TextField prdCountry = new TextField();
        prdCountry.setId(ProductionInfoLayoutIds.TXT_SUP_CNT.toString());
        layout.addComponent(prdCountry, ProductionInfoLayoutIds.TXT_SUP_CNT.toString());
        
        final TextField prdCountryCode = new TextField();
        prdCountryCode.setId(ProductionInfoLayoutIds.TXT_SUP_CNT_CODE.toString());
        layout.addComponent(prdCountryCode, ProductionInfoLayoutIds.TXT_SUP_CNT_CODE.toString());
        
        final TextField prdPhone1 = new TextField();
        prdPhone1.setId(ProductionInfoLayoutIds.TXT_SUP_PH1.toString());
        layout.addComponent(prdPhone1, ProductionInfoLayoutIds.TXT_SUP_PH1.toString());
        
        final TextField prdPhone2 = new TextField();
        prdPhone2.setId(ProductionInfoLayoutIds.TXT_SUP_PH2.toString());
        layout.addComponent(prdPhone2, ProductionInfoLayoutIds.TXT_SUP_PH2.toString());
        
        final TextField prdFax1 = new TextField();
        prdFax1.setId(ProductionInfoLayoutIds.TXT_SUP_FX1.toString());
        layout.addComponent(prdFax1, ProductionInfoLayoutIds.TXT_SUP_FX1.toString());
        
        final TextField prdFax2 = new TextField();
        prdFax2.setId(ProductionInfoLayoutIds.TXT_SUP_FX2.toString());
        layout.addComponent(prdFax2, ProductionInfoLayoutIds.TXT_SUP_FX2.toString());
        
        final TextField prdEmail1 = new TextField();
        prdEmail1.setId(ProductionInfoLayoutIds.TXT_SUP_EM1.toString());
        layout.addComponent(prdEmail1, ProductionInfoLayoutIds.TXT_SUP_EM1.toString());
        
        final TextField prdEmail2 = new TextField();
        prdEmail2.setId(ProductionInfoLayoutIds.TXT_SUP_EM2.toString());
        layout.addComponent(prdEmail2, ProductionInfoLayoutIds.TXT_SUP_EM2.toString());
        
        final TextField prdURL1 = new TextField();
        prdURL1.setId(ProductionInfoLayoutIds.TXT_SUP_URL1.toString());
        layout.addComponent(prdURL1, ProductionInfoLayoutIds.TXT_SUP_URL1.toString());
        
        final TextField prdURL2 = new TextField();
        prdURL2.setId(ProductionInfoLayoutIds.TXT_SUP_URL2.toString());
        layout.addComponent(prdURL2, ProductionInfoLayoutIds.TXT_SUP_URL2.toString());
        
        final Link prdRemovelink = new Link();
        prdRemovelink.setId(ProductionInfoLayoutIds.LNK_SUP_REM.toString());
        prdRemovelink.setCaption(PackageGeneratorConstants.REMOVE_FROM_JS);
        layout.addComponent(prdRemovelink, "lnk_sup_rem");
        
        final Button btnPrdAddJobSheetDetails = new Button();
        btnPrdAddJobSheetDetails.setId(ProductionInfoLayoutIds.BTN_SUP_ADD.toString());
        
        btnPrdAddJobSheetDetails.setCaption(PackageGeneratorConstants.ADD_TO_JS);
        
        btnPrdAddJobSheetDetails.addClickListener(new Button.ClickListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = 7270491342373292455L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                controller.createJobSheet();
                controller.showJobsheet();
            }
        });
        layout.addComponent(btnPrdAddJobSheetDetails,
                ProductionInfoLayoutIds.BTN_SUP_ADD.toString());
        // Start of Supplier Contact Person Information
        
        final TextField prdSupContPrefix = new TextField();
        prdSupContPrefix.setId(ProductionInfoLayoutIds.TXT_CONP_PRE.toString());
        layout.addComponent(prdSupContPrefix, ProductionInfoLayoutIds.TXT_CONP_PRE.toString());
        
        final TextField prdSupContGivenName1 = new TextField();
        prdSupContGivenName1.setId(ProductionInfoLayoutIds.TXT_CONP_GNM1.toString());
        layout.addComponent(prdSupContGivenName1, ProductionInfoLayoutIds.TXT_CONP_GNM1.toString());
        
        final TextField prdSupContGivenName2 = new TextField();
        prdSupContGivenName2.setId(ProductionInfoLayoutIds.TXT_CONP_GNM2.toString());
        layout.addComponent(prdSupContGivenName2, ProductionInfoLayoutIds.TXT_CONP_GNM2.toString());
        
        final TextField prdSupContGivenName3 = new TextField();
        prdSupContGivenName3.setId(ProductionInfoLayoutIds.TXT_CONP_GNM3.toString());
        layout.addComponent(prdSupContGivenName3, ProductionInfoLayoutIds.TXT_CONP_GNM3.toString());
        
        final TextField prdSupContParticle = new TextField();
        prdSupContParticle.setId(ProductionInfoLayoutIds.TXT_CONP_PAR.toString());
        layout.addComponent(prdSupContParticle, ProductionInfoLayoutIds.TXT_CONP_PAR.toString());
        
        final TextField prdSupContFamilyName = new TextField();
        prdSupContFamilyName.setId(ProductionInfoLayoutIds.TXT_CONP_FNM.toString());
        layout.addComponent(prdSupContFamilyName, ProductionInfoLayoutIds.TXT_CONP_FNM.toString());
        
        final ComboBox prdSupContSuffix = new ComboBox();
        prdSupContSuffix.setId(ProductionInfoLayoutIds.LST_CONP_SUF.toString());
        prdSupContSuffix.addItems((Object[]) Suffix.values());
        prdSupContSuffix.setPageLength(30);
        layout.addComponent(prdSupContSuffix, ProductionInfoLayoutIds.LST_CONP_SUF.toString());
        
        final TextField prdSupContOrgName = new TextField();
        prdSupContOrgName.setId(ProductionInfoLayoutIds.TXT_CONP_ONM.toString());
        layout.addComponent(prdSupContOrgName, ProductionInfoLayoutIds.TXT_CONP_ONM.toString());
        
        final TextField prdSupContOrgDivision = new TextField();
        prdSupContOrgDivision.setId(ProductionInfoLayoutIds.TXT_CONP_ODIV.toString());
        layout.addComponent(prdSupContOrgDivision,
                ProductionInfoLayoutIds.TXT_CONP_ODIV.toString());
        
        final TextField prdSupContStreet = new TextField();
        prdSupContStreet.setId(ProductionInfoLayoutIds.TXT_CONP_STR.toString());
        layout.addComponent(prdSupContStreet, ProductionInfoLayoutIds.TXT_CONP_STR.toString());
        
        final TextField prdSupContPostbox = new TextField();
        prdSupContPostbox.setId(ProductionInfoLayoutIds.TXT_CONP_PBX.toString());
        layout.addComponent(prdSupContPostbox, ProductionInfoLayoutIds.TXT_CONP_PBX.toString());
        
        final TextField prdSupContPostCode = new TextField();
        prdSupContPostCode.setId(ProductionInfoLayoutIds.TXT_CONP_PCD.toString());
        layout.addComponent(prdSupContPostCode, ProductionInfoLayoutIds.TXT_CONP_PCD.toString());
        
        final TextField prdSupContCity = new TextField();
        prdSupContCity.setId(ProductionInfoLayoutIds.TXT_CONP_CTY.toString());
        layout.addComponent(prdSupContCity, ProductionInfoLayoutIds.TXT_CONP_CTY.toString());
        
        final TextField prdSupContState = new TextField();
        prdSupContState.setId(ProductionInfoLayoutIds.TXT_CONP_STA.toString());
        layout.addComponent(prdSupContState, ProductionInfoLayoutIds.TXT_CONP_STA.toString());
        
        final TextField prdSupContCountry = new TextField();
        prdSupContCountry.setId(ProductionInfoLayoutIds.TXT_CONP_CNT.toString());
        layout.addComponent(prdSupContCountry, ProductionInfoLayoutIds.TXT_CONP_CNT.toString());
        
        final TextField prdSupContCountryCode = new TextField();
        prdSupContCountryCode.setId(ProductionInfoLayoutIds.TXT_CONP_CNT_CODE.toString());
        layout.addComponent(prdSupContCountryCode,
                ProductionInfoLayoutIds.TXT_CONP_CNT_CODE.toString());
        
        final TextField prdSupContPhone1 = new TextField();
        prdSupContPhone1.setId(ProductionInfoLayoutIds.TXT_CONP_PH1.toString());
        layout.addComponent(prdSupContPhone1, ProductionInfoLayoutIds.TXT_CONP_PH1.toString());
        
        final TextField prdSupContPhone2 = new TextField();
        prdSupContPhone2.setId(ProductionInfoLayoutIds.TXT_CONP_PH2.toString());
        layout.addComponent(prdSupContPhone2, ProductionInfoLayoutIds.TXT_CONP_PH2.toString());
        
        final TextField prdSupContFax1 = new TextField();
        prdSupContFax1.setId(ProductionInfoLayoutIds.TXT_CONP_FX1.toString());
        layout.addComponent(prdSupContFax1, ProductionInfoLayoutIds.TXT_CONP_FX1.toString());
        
        final TextField prdSupContFax2 = new TextField();
        prdSupContFax2.setId(ProductionInfoLayoutIds.TXT_CONP_FX2.toString());
        layout.addComponent(prdSupContFax2, ProductionInfoLayoutIds.TXT_CONP_FX2.toString());
        
        final TextField prdSupContEmail1 = new TextField();
        prdSupContEmail1.setId(ProductionInfoLayoutIds.TXT_CONP_EM1.toString());
        layout.addComponent(prdSupContEmail1, ProductionInfoLayoutIds.TXT_CONP_EM1.toString());
        
        final TextField prdSupContEmail2 = new TextField();
        prdSupContEmail2.setId(ProductionInfoLayoutIds.TXT_CONP_EM2.toString());
        layout.addComponent(prdSupContEmail2, ProductionInfoLayoutIds.TXT_CONP_EM2.toString());
        
        final TextField prdSupContURL1 = new TextField();
        prdSupContURL1.setId(ProductionInfoLayoutIds.TXT_CONP_URL1.toString());
        layout.addComponent(prdSupContURL1, ProductionInfoLayoutIds.TXT_CONP_URL1.toString());
        
        final TextField prdSupContURL2 = new TextField();
        prdSupContURL2.setId(ProductionInfoLayoutIds.TXT_CONP_URL2.toString());
        layout.addComponent(prdSupContURL2, ProductionInfoLayoutIds.TXT_CONP_URL2.toString());
        // End of Supplier Contact Person Information
        
        final Link prdSupContRemovelink = new Link();
        prdSupContRemovelink.setId(ProductionInfoLayoutIds.LNK_CONP_REM.toString());
        prdSupContRemovelink.setCaption(PackageGeneratorConstants.REMOVE_FROM_JS);
        layout.addComponent(prdSupContRemovelink, "lnk_conp_rem");
        
        final Button btnPrdSupContAddJobSheetDetails = new Button();
        btnPrdSupContAddJobSheetDetails.setId(ProductionInfoLayoutIds.BTN_CONP_ADD.toString());
        
        btnPrdSupContAddJobSheetDetails.setCaption(PackageGeneratorConstants.ADD_TO_JS);
        
        btnPrdSupContAddJobSheetDetails.addClickListener(new Button.ClickListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = -4567314671840540108L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                controller.createJobSheet();
                controller.showJobsheet();
                
            }
        });
        layout.addComponent(btnPrdSupContAddJobSheetDetails,
                ProductionInfoLayoutIds.BTN_CONP_ADD.toString());
        // End of Production Information
        
    }
    
}
