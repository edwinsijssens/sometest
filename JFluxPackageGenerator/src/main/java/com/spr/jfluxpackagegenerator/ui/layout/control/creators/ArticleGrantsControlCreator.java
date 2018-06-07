package com.spr.jfluxpackagegenerator.ui.layout.control.creators;

import com.spr.jfluxpackagegenerator.model.enums.Grant;
import com.spr.jfluxpackagegenerator.model.enums.GrantsType;
import com.spr.jfluxpackagegenerator.ui.layout.MainUIController;
import com.spr.jfluxpackagegenerator.ui.layout.id.ArticleGrantsLayoutIds;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Link;

public class ArticleGrantsControlCreator implements LayoutControlCreator {
    
    @Override
    public void createComponents(final CustomLayout layout, final MainUIController controller) {
        // Adding Article Grants Section
        
        final ComboBox artGrantType = new ComboBox();
        artGrantType.setId(ArticleGrantsLayoutIds.LST_GRANTS_TYPE.toString());
        artGrantType.addItems((Object[]) GrantsType.values());
        layout.addComponent(artGrantType, ArticleGrantsLayoutIds.LST_GRANTS_TYPE.toString());
        
        final ComboBox artGrantMetaData = new ComboBox();
        artGrantMetaData.setId(ArticleGrantsLayoutIds.LST_ART_GNT_MET.toString());
        artGrantMetaData.addItems((Object[]) Grant.values());
        layout.addComponent(artGrantMetaData, ArticleGrantsLayoutIds.LST_ART_GNT_MET.toString());
        
        final ComboBox artGrantAbstract = new ComboBox();
        artGrantAbstract.setId(ArticleGrantsLayoutIds.LST_ART_GNT_ABS.toString());
        artGrantAbstract.addItems((Object[]) Grant.values());
        layout.addComponent(artGrantAbstract, ArticleGrantsLayoutIds.LST_ART_GNT_ABS.toString());
        
        final ComboBox artGrantBodyPDF = new ComboBox();
        artGrantBodyPDF.setId(ArticleGrantsLayoutIds.LST_ART_GNT_PDF.toString());
        artGrantBodyPDF.addItems((Object[]) Grant.values());
        layout.addComponent(artGrantBodyPDF, ArticleGrantsLayoutIds.LST_ART_GNT_PDF.toString());
        
        final ComboBox artGrantHTML = new ComboBox();
        artGrantHTML.setId(ArticleGrantsLayoutIds.LST_ART_GNT_HTM.toString());
        artGrantHTML.addItems((Object[]) Grant.values());
        layout.addComponent(artGrantHTML, ArticleGrantsLayoutIds.LST_ART_GNT_HTM.toString());
        
        final ComboBox artGrantBiblo = new ComboBox();
        artGrantBiblo.setId(ArticleGrantsLayoutIds.LST_ART_GNT_BIB.toString());
        artGrantBiblo.addItems((Object[]) Grant.values());
        layout.addComponent(artGrantBiblo, ArticleGrantsLayoutIds.LST_ART_GNT_BIB.toString());
        
        final ComboBox artGrantESM = new ComboBox();
        artGrantESM.setId(ArticleGrantsLayoutIds.LST_ART_GNT_ESM.toString());
        artGrantESM.addItems((Object[]) Grant.values());
        layout.addComponent(artGrantESM, ArticleGrantsLayoutIds.LST_ART_GNT_ESM.toString());
        
        final Link artGrantRemovelink = new Link();
        artGrantRemovelink.setId(ArticleGrantsLayoutIds.LNK_ART_GNT_REM.toString());
        artGrantRemovelink.setCaption("Remove from JobSheet");
        layout.addComponent(artGrantRemovelink, ArticleGrantsLayoutIds.LNK_ART_GNT_REM.toString());
        
        final Button btnArtGrantAddJobSheetDetails = new Button();
        btnArtGrantAddJobSheetDetails.setId(ArticleGrantsLayoutIds.BTN_ART_GNT_ADD.toString());
        btnArtGrantAddJobSheetDetails.setCaption("Add to JobSheet");
        
        btnArtGrantAddJobSheetDetails.addClickListener(new Button.ClickListener() {
            
            /**
             * 
             */
            private static final long serialVersionUID = -2370398642907356536L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                controller.createJobSheet();
                controller.showJobsheet();
            }
        });
        layout.addComponent(btnArtGrantAddJobSheetDetails,
                ArticleGrantsLayoutIds.BTN_ART_GNT_ADD.toString());
        
    }
    
}
