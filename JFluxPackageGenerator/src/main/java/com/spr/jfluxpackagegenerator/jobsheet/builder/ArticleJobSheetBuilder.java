package com.spr.jfluxpackagegenerator.jobsheet.builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spr.jfluxpackagegenerator.jobsheet.ArticleContext;
import com.spr.jfluxpackagegenerator.jobsheet.ArticleInfo;
import com.spr.jfluxpackagegenerator.jobsheet.ArticleJobSheet;
import com.spr.jfluxpackagegenerator.jobsheet.AuthorGroup;
import com.spr.jfluxpackagegenerator.jobsheet.ContentFiles;
import com.spr.jfluxpackagegenerator.jobsheet.Deliverables;
import com.spr.jfluxpackagegenerator.jobsheet.DeliverablesForDiscreteObjects;
import com.spr.jfluxpackagegenerator.jobsheet.ExternalPublisher;
import com.spr.jfluxpackagegenerator.jobsheet.FilesToPublisher;
import com.spr.jfluxpackagegenerator.jobsheet.JobSheet;
import com.spr.jfluxpackagegenerator.jobsheet.WorkflowInfo;
import com.spr.jfluxpackagegenerator.model.data.DataStorage;
import com.spr.jfluxpackagegenerator.model.enums.Decision;
import com.spr.jfluxpackagegenerator.ui.layout.id.DeliverablesDiscreteLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.JournalInfoLayoutIds;

/**
 * The class is designed to implement Builder pattern to create different parts of article jobsheet.
 * 
 * @author Alexey Dergalev
 */
public class ArticleJobSheetBuilder implements CommonJobSheetBuilder {
    
    
    private final JobSheetBuilder jobsheetBuilder;
    
    private final Map<String, String> templatesMap = new HashMap<String, String>();
    
    public ArticleJobSheetBuilder(final List<DataStorage> storages) {
        jobsheetBuilder = new JobSheetBuilder(storages);
    }
    
    @Override
    public void build() {
        templatesMap.clear();
        jobsheetBuilder.buildJobsheet();
        jobsheetBuilder.buildArticleJobSheet();
        jobsheetBuilder.buildPublisherInfo();
        jobsheetBuilder.buildJournalInfo();
        buildArticleInfo();
        buildAuthorGroup();
        jobsheetBuilder.buildProductionRelatedInfo();
        buildDeliverablesForDiscreteObjects();
        buildFilesToPublisher();
    }
    
    private void buildAuthorGroup() {
        final ArticleJobSheet artJS = jobsheetBuilder.getJobSheet().getArticleJobSheet();
        final AuthorGroup group = jobsheetBuilder.buildAuthorGroup(1);
        artJS.setAuthorGroup(group);
    }
    
    private void buildFilesToPublisher() {
        final FilesElementBuilder builder =
                new FilesElementBuilder(jobsheetBuilder.getObjectFactory(),
                        jobsheetBuilder.getArticleFilesToPublisherModel(1), templatesMap,
                        jobsheetBuilder.getArticleObjectPrefix(1));
        final ExternalPublisher ext = jobsheetBuilder.getProductionInfo().getWorkflowInfo()
                .getSupplier().getExternalPublisher();
        final FilesToPublisher filesTP =
                jobsheetBuilder.getObjectFactory().createFilesToPublisher();
        final ContentFiles cFiles = jobsheetBuilder.getObjectFactory().createContentFiles();
        
        cFiles.getFile().addAll(builder.buildFiles());
        
        filesTP.setContentFiles(cFiles);
        ext.setFilesToPublisher(filesTP);
        
    }
    
    private void buildDeliverablesForDiscreteObjects() {
        final DeliverablesForDiscreteObjects prodExPubDelObj =
                jobsheetBuilder.getObjectFactory().createDeliverablesForDiscreteObjects();
        
        // eBookPDF
        prodExPubDelObj.setEBookPDF(Decision.No.toString());
        
        // XMLWithBody
        prodExPubDelObj
                .setXMLWithBody(getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_XWB.toString()));
        // XMLWithBodyRefsOnly
        prodExPubDelObj.setXMLWithBodyRefsOnly(
                getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_XWBRO.toString()));
        // OnlinePDF
        prodExPubDelObj
                .setOnlinePDF(getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_OPDF.toString()));
        // PrintPDF
        prodExPubDelObj
                .setPrintPDF(getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_PPDF.toString()));
        // Tex
        prodExPubDelObj.setTEX(getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_TEX.toString()));
        // OnlineMediaObjects
        prodExPubDelObj.setOnlineMediaObjects(
                getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_OMO.toString()));
        // PrintMediaObjects
        prodExPubDelObj.setPrintMediaObjects(
                getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_PMO.toString()));
        // ReferencePDF
        prodExPubDelObj.setReferencePDF(
                getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_RPDF.toString()));
        // AuthorFeedbackPDF
        prodExPubDelObj.setAuthorFeedbackPDF(
                getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_AFPDF.toString()));
        // DeltaPDF
        prodExPubDelObj
                .setDeltaPDF(getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_DPDF.toString()));
        // CopyrightTransfer
        prodExPubDelObj.setCopyrightTransfer(
                getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_CPYT.toString()));
        // OpenAcessStatement
        prodExPubDelObj.setOpenAccessStatement(
                getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_OAS.toString()));
        // OffprintOrder
        prodExPubDelObj.setOffprintOrder(
                getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_OPO.toString()));
        // Pit-Stop-Reports
        prodExPubDelObj.setPitStopReports(
                getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_PSR.toString()));
        // PRS-Metadata
        prodExPubDelObj.setPRSMetadata(
                getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_PRSM.toString()));
        // Manuscript
        prodExPubDelObj
                .setManuscript(getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_MAN.toString()));
        // EpsilonPDF
        prodExPubDelObj
                .setEpsilonPDF(getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_EPDF.toString()));
        // CorrectionSheet
        prodExPubDelObj.setCorrectionSheet(
                getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_CORS.toString()));
        // PeerReviewPDFs
        prodExPubDelObj.setPeerReviewPDFs(
                getValueById(DeliverablesDiscreteLayoutIds.LST_DEL_PRP.toString()));
        Deliverables prodExPubDel = jobsheetBuilder.getObjectFactory().createDeliverables();
        prodExPubDel.setDeliverablesForDiscreteObjects(prodExPubDelObj);
        
        // we need a check for null?
        final WorkflowInfo prodWorkflowInfo = jobsheetBuilder.getJobSheet().getArticleJobSheet()
                .getProductionInfo().getWorkflowInfo();
        prodWorkflowInfo.setTaskType("DeliverDiscreteObject");
        
        prodWorkflowInfo.getSupplier().getExternalPublisher().setDeliverables(prodExPubDel);
        
    }
    
    private void buildArticleInfo() {
        final ArticleJobSheet artJS = jobsheetBuilder.getJobSheet().getArticleJobSheet();
        final ArticleInfo artInfo = jobsheetBuilder.buildArticleInfo(1);
        buildArticleContext(artInfo);
        artJS.setArticleInfo(artInfo);
    }
    
    private void buildArticleContext(final ArticleInfo artInfo) {
        // Article Context Journal ID
        final ArticleContext artArticleContext =
                jobsheetBuilder.getObjectFactory().createArticleContext();
        artArticleContext.setJournalID(getValueById(JournalInfoLayoutIds.TXT_JPROR_ID.toString()));
        artInfo.setArticleContext(artArticleContext);
        
    }
    
    @Override
    public JobSheet getProduct() {
        return jobsheetBuilder.getJobSheet();
    }
    
    /**
     * Get value form data model based on layout control id.
     * 
     * @param id layout control id
     * @return value
     */
    private String getValueById(final String id) {
        return jobsheetBuilder.getValueById(id);
    }
    
    @Override
    public Map<String, String> getTemplatesForFiles() {
        return templatesMap;
    }
    
    @Override
    public String getJobSheetName() {
        return jobsheetBuilder.getArticleObjectPrefix(1) + "_JobSheet_100.xml";
    }
    
}
