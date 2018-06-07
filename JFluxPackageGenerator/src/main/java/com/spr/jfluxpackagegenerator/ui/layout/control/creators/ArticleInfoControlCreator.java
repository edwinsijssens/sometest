package com.spr.jfluxpackagegenerator.ui.layout.control.creators;

import com.spr.jfluxpackagegenerator.model.enums.ArticleType;
import com.spr.jfluxpackagegenerator.model.enums.CrossLinking;
import com.spr.jfluxpackagegenerator.model.enums.Decision;
import com.spr.jfluxpackagegenerator.model.enums.Language;
import com.spr.jfluxpackagegenerator.model.enums.NumberingStyle;
import com.spr.jfluxpackagegenerator.model.enums.OutputMedium;
import com.spr.jfluxpackagegenerator.ui.layout.MainUIController;
import com.spr.jfluxpackagegenerator.ui.layout.id.ArticleInfoLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.validator.NotEmptyAttributeValidator;
import com.spr.packagegenerator.constants.PackageGeneratorConstants;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;

/**
 * The creator fills UI elements for article info, article history values.
 * 
 * @author Alexey Dergalev
 */
public class ArticleInfoControlCreator implements LayoutControlCreator {
    
    
    @Override
    public void createComponents(final CustomLayout layout, final MainUIController controller) {
        
        // Article Information
        final ComboBox aArticleType = new ComboBox();
        aArticleType.setId(ArticleInfoLayoutIds.LST_ART_TYPE.toString());
        aArticleType.setPageLength(30);
        aArticleType.addItems((Object[]) ArticleType.values());
        layout.addComponent(aArticleType, ArticleInfoLayoutIds.LST_ART_TYPE.toString());
        
        final TextField articleSeqNum = new TextField();
        articleSeqNum.setId(ArticleInfoLayoutIds.TXT_ART_SEQ.toString());
        layout.addComponent(articleSeqNum, ArticleInfoLayoutIds.TXT_ART_SEQ.toString());
        
        final TextField aArticleCitation = new TextField();
        aArticleCitation.setId(ArticleInfoLayoutIds.LST_ART_CITE.toString());
        layout.addComponent(aArticleCitation, ArticleInfoLayoutIds.LST_ART_CITE.toString());
        
        final ComboBox aContainsESM = new ComboBox();
        aContainsESM.setId(ArticleInfoLayoutIds.LST_CON_ESM.toString());
        aContainsESM.addItems((Object[]) Decision.getBooleanValues());
        layout.addComponent(aContainsESM, ArticleInfoLayoutIds.LST_CON_ESM.toString());
        
        final ComboBox aArticleLanguage = new ComboBox();
        aArticleLanguage.setId(ArticleInfoLayoutIds.LST_ART_LANG.toString());
        aArticleLanguage.setPageLength(30);
        aArticleLanguage.addItems((Object[]) Language.values());
        layout.addComponent(aArticleLanguage, ArticleInfoLayoutIds.LST_ART_LANG.toString());
        
        final ComboBox aArticleNumberStyle = new ComboBox();
        aArticleNumberStyle.setId(ArticleInfoLayoutIds.LST_ART_NUM.toString());
        aArticleNumberStyle.addItems((Object[]) NumberingStyle.values());
        layout.addComponent(aArticleNumberStyle, ArticleInfoLayoutIds.LST_ART_NUM.toString());
        
        final ComboBox aArticleOutputMedium = new ComboBox();
        aArticleOutputMedium.setId(ArticleInfoLayoutIds.LST_ART_OUT.toString());
        aArticleOutputMedium.addItems((Object[]) OutputMedium.values());
        layout.addComponent(aArticleOutputMedium, ArticleInfoLayoutIds.LST_ART_OUT.toString());
        
        final TextField articleToc = new TextField();
        articleToc.setId(ArticleInfoLayoutIds.TXT_ART_TOC.toString());
        layout.addComponent(articleToc, ArticleInfoLayoutIds.TXT_ART_TOC.toString());
        
        final TextField articleId = new TextField();
        articleId.setId(ArticleInfoLayoutIds.TXT_ART_ID.toString());
        articleId.addValidator(new NotEmptyAttributeValidator("Article ID must not be empty"));
        articleId.setValidationVisible(false);
        layout.addComponent(articleId, ArticleInfoLayoutIds.TXT_ART_ID.toString());
        
        final TextField articleExtID = new TextField();
        articleExtID.setId(ArticleInfoLayoutIds.TXT_ART_EXT_ID.toString());
        layout.addComponent(articleExtID, ArticleInfoLayoutIds.TXT_ART_EXT_ID.toString());
        
        final TextField articleDOI = new TextField();
        articleDOI.setId(ArticleInfoLayoutIds.TXT_ART_DOI.toString());
        articleDOI.addValidator(new NotEmptyAttributeValidator("Article DOI must not be empty"));
        articleDOI.setValidationVisible(false);
        layout.addComponent(articleDOI, ArticleInfoLayoutIds.TXT_ART_DOI.toString());
        
        final TextField articleTitle = new TextField();
        articleTitle.setId(ArticleInfoLayoutIds.TXT_ART_TITLE.toString());
        layout.addComponent(articleTitle, ArticleInfoLayoutIds.TXT_ART_TITLE.toString());
        
        final ComboBox aArticleTitleLanguage = new ComboBox();
        aArticleTitleLanguage.setId(ArticleInfoLayoutIds.LST_ART_TIT_LANG.toString());
        aArticleTitleLanguage.setPageLength(30);
        aArticleTitleLanguage.addItems((Object[]) Language.values());
        layout.addComponent(aArticleTitleLanguage,
                ArticleInfoLayoutIds.LST_ART_TIT_LANG.toString());
        
        final ComboBox aArticleTitleOutputMedium = new ComboBox();
        aArticleTitleOutputMedium.setId(ArticleInfoLayoutIds.LST_ART_TIT_OUT.toString());
        aArticleTitleOutputMedium.addItems((Object[]) OutputMedium.values());
        layout.addComponent(aArticleTitleOutputMedium,
                ArticleInfoLayoutIds.LST_ART_TIT_OUT.toString());
        
        final TextField articleCategory = new TextField();
        articleCategory.setId(ArticleInfoLayoutIds.TXT_ART_CAT.toString());
        layout.addComponent(articleCategory, ArticleInfoLayoutIds.TXT_ART_CAT.toString());
        
        final TextField articleSubCategory = new TextField();
        articleSubCategory.setId(ArticleInfoLayoutIds.TXT_ART_SUB_CAT.toString());
        layout.addComponent(articleSubCategory, ArticleInfoLayoutIds.TXT_ART_SUB_CAT.toString());
        
        final TextField articleFirstPage = new TextField();
        articleFirstPage.setId(ArticleInfoLayoutIds.TXT_ART_FPG.toString());
        layout.addComponent(articleFirstPage, ArticleInfoLayoutIds.TXT_ART_FPG.toString());
        
        final TextField articleLastPage = new TextField();
        articleLastPage.setId(ArticleInfoLayoutIds.TXT_ART_LPG.toString());
        layout.addComponent(articleLastPage, ArticleInfoLayoutIds.TXT_ART_LPG.toString());
        
        final TextField articleEditor = new TextField();
        articleEditor.setId(ArticleInfoLayoutIds.TXT_ART_EDIT.toString());
        layout.addComponent(articleEditor, ArticleInfoLayoutIds.TXT_ART_EDIT.toString());
        
        final TextField errFirstPage = new TextField();
        errFirstPage.setId(ArticleInfoLayoutIds.TXT_ART_ERRFP.toString());
        layout.addComponent(errFirstPage, ArticleInfoLayoutIds.TXT_ART_ERRFP.toString());
        
        final TextField errLastPage = new TextField();
        errLastPage.setId(ArticleInfoLayoutIds.TXT_ART_ERRLP.toString());
        layout.addComponent(errLastPage, ArticleInfoLayoutIds.TXT_ART_ERRLP.toString());
        
        final TextField articleCopHolName = new TextField();
        articleCopHolName.setId(ArticleInfoLayoutIds.TXT_COPY_NAME.toString());
        layout.addComponent(articleCopHolName, ArticleInfoLayoutIds.TXT_COPY_NAME.toString());
        
        final TextField articleCopYear = new TextField();
        articleCopYear.setId(ArticleInfoLayoutIds.TXT_COPY_YR.toString());
        articleCopYear
                .addValidator(new NotEmptyAttributeValidator("Copyright Year must not be empty"));
        articleCopYear.addValidator(new RegexpValidator("^\\d{4}",
                "Copyright Year does not match the required format"));
        articleCopYear.setValidationVisible(false);
        layout.addComponent(articleCopYear, ArticleInfoLayoutIds.TXT_COPY_YR.toString());
        
        final Link artRemovelink = new Link();
        artRemovelink.setCaption(PackageGeneratorConstants.REMOVE_FROM_JS);
        layout.addComponent(artRemovelink, ArticleInfoLayoutIds.LNK_ART_INFO_REM.toString());
        artRemovelink.setId(ArticleInfoLayoutIds.LNK_ART_INFO_REM.toString());
        
        final Button btnArtAddJobSheetDetails = new Button();
        btnArtAddJobSheetDetails.setId(ArticleInfoLayoutIds.BTN_ART_INFO_ADD.toString());
        btnArtAddJobSheetDetails.setCaption(PackageGeneratorConstants.ADD_TO_JS);
        
        btnArtAddJobSheetDetails.addClickListener(new Button.ClickListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = 7150499254272920054L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                controller.createJobSheet();
                controller.showJobsheet();
                
            }
        });
        //
        layout.addComponent(btnArtAddJobSheetDetails,
                ArticleInfoLayoutIds.BTN_ART_INFO_ADD.toString());
        
        // Adding Article Sub Title Widgets
        
        final TextField articleSubTitle = new TextField();
        articleSubTitle.setId(ArticleInfoLayoutIds.TXT_ART_SUB_TIT.toString());
        layout.addComponent(articleSubTitle, ArticleInfoLayoutIds.TXT_ART_SUB_TIT.toString());
        
        final ComboBox aArtSubTitleLngBox = new ComboBox();
        aArtSubTitleLngBox.setId(ArticleInfoLayoutIds.LST_ART_SUB_LANG.toString());
        aArtSubTitleLngBox.setPageLength(30);
        aArtSubTitleLngBox.addItems((Object[]) Language.values());
        layout.addComponent(aArtSubTitleLngBox, ArticleInfoLayoutIds.LST_ART_SUB_LANG.toString());
        
        final ComboBox aArtSubTitleOpMedBox = new ComboBox();
        aArtSubTitleOpMedBox.setId(ArticleInfoLayoutIds.LST_REL_SUB_OBJ_TYP.toString());
        aArtSubTitleOpMedBox.addItems((Object[]) OutputMedium.values());
        layout.addComponent(aArtSubTitleOpMedBox,
                ArticleInfoLayoutIds.LST_REL_SUB_OBJ_TYP.toString());
        
        final Link artSubRemovelink = new Link();
        artSubRemovelink.setId(ArticleInfoLayoutIds.LNK_ART_SUB_REM.toString());
        artSubRemovelink.setCaption(PackageGeneratorConstants.REMOVE_FROM_JS);
        layout.addComponent(artSubRemovelink, ArticleInfoLayoutIds.LNK_ART_SUB_REM.toString());
        
        final Button btnArtSubAddJobSheetDetails = new Button();
        btnArtSubAddJobSheetDetails.setId(ArticleInfoLayoutIds.BTN_ART_SUB_ADD.toString());
        btnArtSubAddJobSheetDetails.setCaption(PackageGeneratorConstants.ADD_TO_JS);
        
        btnArtSubAddJobSheetDetails.addClickListener(new Button.ClickListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = 2291125717830383905L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                controller.createJobSheet();
                controller.showJobsheet();
                
            }
        });
        //
        layout.addComponent(btnArtSubAddJobSheetDetails,
                ArticleInfoLayoutIds.BTN_ART_SUB_ADD.toString());
        
        // Adding Article History
        final TextField artHistoryRecDate = new TextField();
        artHistoryRecDate.setId(ArticleInfoLayoutIds.TXT_ART_REC_DT.toString());
        layout.addComponent(artHistoryRecDate, ArticleInfoLayoutIds.TXT_ART_REC_DT.toString());
        
        final TextField artHistoryRevDate = new TextField();
        artHistoryRevDate.setId(ArticleInfoLayoutIds.TXT_ART_REV_DT.toString());
        layout.addComponent(artHistoryRevDate, ArticleInfoLayoutIds.TXT_ART_REV_DT.toString());
        
        final TextField artHistoryAccDate = new TextField();
        artHistoryAccDate.setId(ArticleInfoLayoutIds.TXT_ART_ACC_DT.toString());
        layout.addComponent(artHistoryAccDate, ArticleInfoLayoutIds.TXT_ART_ACC_DT.toString());
        
        final TextField artHistoryRegDate = new TextField();
        artHistoryRegDate.setId(ArticleInfoLayoutIds.TXT_ART_REG_DT.toString());
        layout.addComponent(artHistoryRegDate, ArticleInfoLayoutIds.TXT_ART_REG_DT.toString());
        
        final TextField artHistoryOnlDate = new TextField();
        artHistoryOnlDate.setId(ArticleInfoLayoutIds.TXT_ART_ON_DT.toString());
        layout.addComponent(artHistoryOnlDate, ArticleInfoLayoutIds.TXT_ART_ON_DT.toString());
        
        final Link artHistoryRemovelink = new Link();
        artHistoryRemovelink.setId(ArticleInfoLayoutIds.LNK_ART_HIS_REM.toString());
        artHistoryRemovelink.setCaption(PackageGeneratorConstants.REMOVE_FROM_JS);
        layout.addComponent(artHistoryRemovelink, ArticleInfoLayoutIds.LNK_ART_HIS_REM.toString());
        
        final Button btnArtHistoryAddJobSheetDetails = new Button();
        btnArtHistoryAddJobSheetDetails.setId(ArticleInfoLayoutIds.BTN_ART_HIS_ADD.toString());
        
        btnArtHistoryAddJobSheetDetails.setCaption(PackageGeneratorConstants.ADD_TO_JS);
        
        btnArtHistoryAddJobSheetDetails.addClickListener(new Button.ClickListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = -1652682885607011610L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                controller.createJobSheet();
                controller.showJobsheet();
                
            }
        });
        layout.addComponent(btnArtHistoryAddJobSheetDetails,
                ArticleInfoLayoutIds.BTN_ART_HIS_ADD.toString());
        
        // Adding control for Article Related Object
        final ComboBox aRelatedObjectBox = new ComboBox();
        aRelatedObjectBox.setId(ArticleInfoLayoutIds.LST_REL_OBJ_TYP.toString());
        aRelatedObjectBox.addItems((Object[]) CrossLinking.values());
        layout.addComponent(aRelatedObjectBox, ArticleInfoLayoutIds.LST_REL_OBJ_TYP.toString());
        
        final TextField relObjectDOI = new TextField();
        relObjectDOI.setId(ArticleInfoLayoutIds.TXT_REL_OBJ_DOI.toString());
        layout.addComponent(relObjectDOI, ArticleInfoLayoutIds.TXT_REL_OBJ_DOI.toString());
        
        final Link artRelObjRemovelink = new Link();
        artRelObjRemovelink.setId(ArticleInfoLayoutIds.LNK_ART_REL_REM.toString());
        artRelObjRemovelink.setCaption(PackageGeneratorConstants.REMOVE_FROM_JS);
        layout.addComponent(artRelObjRemovelink, ArticleInfoLayoutIds.LNK_ART_REL_REM.toString());
        
        final Button btnArtRelObjAddJobSheetDetails = new Button();
        btnArtRelObjAddJobSheetDetails.setId(ArticleInfoLayoutIds.BTN_ART_REL_ADD.toString());
        
        btnArtRelObjAddJobSheetDetails.setCaption(PackageGeneratorConstants.ADD_TO_JS);
        btnArtRelObjAddJobSheetDetails.addClickListener(new Button.ClickListener() {
            
            
            /**
             * 
             */
            private static final long serialVersionUID = 5127331194152683014L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                controller.createJobSheet();
                controller.showJobsheet();
                
            }
        });
        
        layout.addComponent(btnArtRelObjAddJobSheetDetails,
                ArticleInfoLayoutIds.BTN_ART_REL_ADD.toString());
        
        // Generate Package Button details
        final Button btnGenPackage = new Button();
        btnGenPackage.setId(ArticleInfoLayoutIds.BTN_GEN_PACK.toString());
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
                    controller.saveToDb();
                    
                }
                
            }
        });
        
        layout.addComponent(btnGenPackage, ArticleInfoLayoutIds.BTN_GEN_PACK.toString());
        
    }
}
