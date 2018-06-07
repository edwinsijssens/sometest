package com.spr.jfluxpackagegenerator.ui.layout.control.creators;

import com.spr.jfluxpackagegenerator.model.enums.Suffix;
import com.spr.jfluxpackagegenerator.ui.layout.MainUIController;
import com.spr.jfluxpackagegenerator.ui.layout.id.AuthorGroupLayoutIds;
import com.spr.packagegenerator.constants.PackageGeneratorConstants;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;

public class AuthorGroupControlCreator implements LayoutControlCreator {
    
    
    @Override
    public void createComponents(final CustomLayout layout, final MainUIController controller) {
        
        final TextField authPrefix1 = new TextField();
        authPrefix1.setId(AuthorGroupLayoutIds.TXT_AUTH1_PRE.toString());
        layout.addComponent(authPrefix1, AuthorGroupLayoutIds.TXT_AUTH1_PRE.toString());
        
        final TextField authGivenName1 = new TextField();
        authGivenName1.setId(AuthorGroupLayoutIds.TXT_AUTH1_GNM1.toString());
        layout.addComponent(authGivenName1, AuthorGroupLayoutIds.TXT_AUTH1_GNM1.toString());
        
        final TextField authGivenName2 = new TextField();
        authGivenName2.setId(AuthorGroupLayoutIds.TXT_AUTH1_GNM2.toString());
        layout.addComponent(authGivenName2, AuthorGroupLayoutIds.TXT_AUTH1_GNM2.toString());
        
        final TextField authGivenName3 = new TextField();
        authGivenName3.setId(AuthorGroupLayoutIds.TXT_AUTH1_GNM3.toString());
        layout.addComponent(authGivenName3, AuthorGroupLayoutIds.TXT_AUTH1_GNM3.toString());
        
        final TextField authFamilyName = new TextField();
        authFamilyName.setId(AuthorGroupLayoutIds.TXT_AUTH1_FNM.toString());
        layout.addComponent(authFamilyName, AuthorGroupLayoutIds.TXT_AUTH1_FNM.toString());
        
        final TextField authParticle = new TextField();
        authParticle.setId(AuthorGroupLayoutIds.TXT_AUTH1_PAR.toString());
        layout.addComponent(authParticle, AuthorGroupLayoutIds.TXT_AUTH1_PAR.toString());
        
        final ComboBox authSuffix = new ComboBox();
        authSuffix.setId(AuthorGroupLayoutIds.LST_AUTH1_SUF.toString());
        authSuffix.setPageLength(30);
        authSuffix.addItems((Object[]) Suffix.values());
        layout.addComponent(authSuffix, AuthorGroupLayoutIds.LST_AUTH1_SUF.toString());
        
        final TextField authAffiliation = new TextField();
        authAffiliation.setId(AuthorGroupLayoutIds.TXT_AUTH1_AFF.toString());
        layout.addComponent(authAffiliation, AuthorGroupLayoutIds.TXT_AUTH1_AFF.toString());
        
        final TextField authCorrAffiliation = new TextField();
        authCorrAffiliation.setId(AuthorGroupLayoutIds.TXT_AUTH1_CAFF.toString());
        layout.addComponent(authCorrAffiliation, AuthorGroupLayoutIds.TXT_AUTH1_CAFF.toString());
        
        final TextField authSAPID = new TextField();
        authSAPID.setId(AuthorGroupLayoutIds.TXT_AUTH1_SAP_ID.toString());
        layout.addComponent(authSAPID, AuthorGroupLayoutIds.TXT_AUTH1_SAP_ID.toString());
        
        final Link auth1Removelink = new Link();
        auth1Removelink.setId(AuthorGroupLayoutIds.LNK_AUTH1_REM.toString());
        auth1Removelink.setCaption(PackageGeneratorConstants.REMOVE_FROM_JS);
        layout.addComponent(auth1Removelink, AuthorGroupLayoutIds.LNK_AUTH1_REM.toString());
        
        final Button btnAuth1AddJobSheetDetails = new Button();
        btnAuth1AddJobSheetDetails.setId(AuthorGroupLayoutIds.BTN_AUTH1_ADD.toString());
        btnAuth1AddJobSheetDetails.setCaption(PackageGeneratorConstants.ADD_TO_JS);
        
        btnAuth1AddJobSheetDetails.addClickListener(new Button.ClickListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = 3094016868771999181L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                controller.createJobSheet();
                controller.showJobsheet();
                
            }
        });
        layout.addComponent(btnAuth1AddJobSheetDetails,
                AuthorGroupLayoutIds.BTN_AUTH1_ADD.toString());
        
        // Author Contact Information Block
        
        final TextField authContOrgName = new TextField();
        authContOrgName.setId(AuthorGroupLayoutIds.TXT_AU1_CON1_ONM.toString());
        layout.addComponent(authContOrgName, AuthorGroupLayoutIds.TXT_AU1_CON1_ONM.toString());
        
        final TextField authContOrgDivision = new TextField();
        authContOrgDivision.setId(AuthorGroupLayoutIds.TXT_AU1_CON1_ODIV.toString());
        layout.addComponent(authContOrgDivision, AuthorGroupLayoutIds.TXT_AU1_CON1_ODIV.toString());
        
        final TextField authContPhone1 = new TextField();
        authContPhone1.setId(AuthorGroupLayoutIds.TXT_AU1_CON1_PH1.toString());
        layout.addComponent(authContPhone1, AuthorGroupLayoutIds.TXT_AU1_CON1_PH1.toString());
        
        final TextField authContPhone2 = new TextField();
        authContPhone2.setId(AuthorGroupLayoutIds.TXT_AU1_CON1_PH2.toString());
        layout.addComponent(authContPhone2, AuthorGroupLayoutIds.TXT_AU1_CON1_PH2.toString());
        
        final TextField authContFax1 = new TextField();
        authContFax1.setId(AuthorGroupLayoutIds.TXT_AU1_CON1_FX1.toString());
        layout.addComponent(authContFax1, AuthorGroupLayoutIds.TXT_AU1_CON1_FX1.toString());
        
        final TextField authContFax2 = new TextField();
        authContFax2.setId(AuthorGroupLayoutIds.TXT_AU1_CON1_FX2.toString());
        layout.addComponent(authContFax2, AuthorGroupLayoutIds.TXT_AU1_CON1_FX2.toString());
        
        final TextField authContEmail1 = new TextField();
        authContEmail1.setId(AuthorGroupLayoutIds.TXT_AU1_CON1_EM1.toString());
        layout.addComponent(authContEmail1, AuthorGroupLayoutIds.TXT_AU1_CON1_EM1.toString());
        
        final TextField authContEmail2 = new TextField();
        authContEmail2.setId(AuthorGroupLayoutIds.TXT_AU1_CON1_EM2.toString());
        layout.addComponent(authContEmail2, AuthorGroupLayoutIds.TXT_AU1_CON1_EM2.toString());
        
        final TextField authContURL1 = new TextField();
        authContURL1.setId(AuthorGroupLayoutIds.TXT_AU1_CON1_URL1.toString());
        layout.addComponent(authContURL1, AuthorGroupLayoutIds.TXT_AU1_CON1_URL1.toString());
        
        final TextField authContURL2 = new TextField();
        authContURL2.setId(AuthorGroupLayoutIds.TXT_AU1_CON1_URL2.toString());
        layout.addComponent(authContURL2, AuthorGroupLayoutIds.TXT_AU1_CON1_URL2.toString());
        
        final Link authCon1Removelink = new Link();
        authCon1Removelink.setId(AuthorGroupLayoutIds.LNK_CON1_REM.toString());
        authCon1Removelink.setCaption(PackageGeneratorConstants.REMOVE_FROM_JS);
        layout.addComponent(authCon1Removelink, AuthorGroupLayoutIds.LNK_CON1_REM.toString());
        
        final Button btnAuthCon1AddJobSheetDetails = new Button();
        btnAuthCon1AddJobSheetDetails.setId(AuthorGroupLayoutIds.BTN_CON1_ADD.toString());
        
        btnAuthCon1AddJobSheetDetails.setCaption(PackageGeneratorConstants.ADD_TO_JS);
        
        btnAuthCon1AddJobSheetDetails.addClickListener(new Button.ClickListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = -5180107676854581166L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                controller.createJobSheet();
                controller.showJobsheet();
                
            }
        });
        layout.addComponent(btnAuthCon1AddJobSheetDetails,
                AuthorGroupLayoutIds.BTN_CON1_ADD.toString());
        
        final TextField affID1 = new TextField();
        affID1.setId(AuthorGroupLayoutIds.TXT_AFF1_ID.toString());
        layout.addComponent(affID1, AuthorGroupLayoutIds.TXT_AFF1_ID.toString());
        
        final TextField affOrgName1 = new TextField();
        affOrgName1.setId(AuthorGroupLayoutIds.TXT_AFF1_ORG_NM.toString());
        layout.addComponent(affOrgName1, AuthorGroupLayoutIds.TXT_AFF1_ORG_NM.toString());
        
        final TextField affOrgDivision1 = new TextField();
        affOrgDivision1.setId(AuthorGroupLayoutIds.TXT_AFF1_ORG_DIV.toString());
        layout.addComponent(affOrgDivision1, AuthorGroupLayoutIds.TXT_AFF1_ORG_DIV.toString());
        
        final TextField affStreet1 = new TextField();
        affStreet1.setId(AuthorGroupLayoutIds.TXT_AFF1_STR.toString());
        layout.addComponent(affStreet1, AuthorGroupLayoutIds.TXT_AFF1_STR.toString());
        
        final TextField affPostbox1 = new TextField();
        affPostbox1.setId(AuthorGroupLayoutIds.TXT_AFF1_PBX.toString());
        layout.addComponent(affPostbox1, AuthorGroupLayoutIds.TXT_AFF1_PBX.toString());
        
        final TextField affPostCode1 = new TextField();
        affPostCode1.setId(AuthorGroupLayoutIds.TXT_AFF1_PCD.toString());
        layout.addComponent(affPostCode1, AuthorGroupLayoutIds.TXT_AFF1_PCD.toString());
        
        final TextField affCity1 = new TextField();
        affCity1.setId(AuthorGroupLayoutIds.TXT_AFF1_CTY.toString());
        layout.addComponent(affCity1, AuthorGroupLayoutIds.TXT_AFF1_CTY.toString());
        
        final TextField affState1 = new TextField();
        affState1.setId(AuthorGroupLayoutIds.TXT_AFF1_STA.toString());
        layout.addComponent(affState1, AuthorGroupLayoutIds.TXT_AFF1_STA.toString());
        
        final TextField affCountry1 = new TextField();
        affCountry1.setId(AuthorGroupLayoutIds.TXT_AFF1_CNT.toString());
        layout.addComponent(affCountry1, AuthorGroupLayoutIds.TXT_AFF1_CNT.toString());
        
        final TextField affCountryCode1 = new TextField();
        affCountryCode1.setId(AuthorGroupLayoutIds.TXT_AFF1_CNT_CODE.toString());
        layout.addComponent(affCountryCode1, AuthorGroupLayoutIds.TXT_AFF1_CNT_CODE.toString());
        
        final Link aff1Removelink = new Link();
        aff1Removelink.setId(AuthorGroupLayoutIds.LNK_AFF1_REM.toString());
        aff1Removelink.setCaption(PackageGeneratorConstants.REMOVE_FROM_JS);
        layout.addComponent(aff1Removelink, AuthorGroupLayoutIds.LNK_AFF1_REM.toString());
        
        final Button btnAff1AddJobSheetDetails = new Button();
        btnAff1AddJobSheetDetails.setId(AuthorGroupLayoutIds.BTN_AFF1_ADD.toString());
        
        btnAff1AddJobSheetDetails.setCaption(PackageGeneratorConstants.ADD_TO_JS);
        
        btnAff1AddJobSheetDetails.addClickListener(new Button.ClickListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = 3992316655211564023L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                controller.createJobSheet();
                controller.showJobsheet();
                
            }
        });
        layout.addComponent(btnAff1AddJobSheetDetails,
                AuthorGroupLayoutIds.BTN_AFF1_ADD.toString());
        // End of Affiliation Information 1
        
    }
}
