package com.spr.jfluxpackagegenerator.jobsheet.builder;

import java.text.Format;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.spr.jfluxpackagegenerator.jobsheet.ArticleContext;
import com.spr.jfluxpackagegenerator.jobsheet.ArticleInfo;
import com.spr.jfluxpackagegenerator.jobsheet.AuthorGroup;
import com.spr.jfluxpackagegenerator.jobsheet.ContentFiles;
import com.spr.jfluxpackagegenerator.jobsheet.CoverDate;
import com.spr.jfluxpackagegenerator.jobsheet.CoverInfo;
import com.spr.jfluxpackagegenerator.jobsheet.Deliverables;
import com.spr.jfluxpackagegenerator.jobsheet.DeliverablesForCompoundObjects;
import com.spr.jfluxpackagegenerator.jobsheet.DiscreteIssueObjectInfo;
import com.spr.jfluxpackagegenerator.jobsheet.ExternalPublisher;
import com.spr.jfluxpackagegenerator.jobsheet.FilesToPublisher;
import com.spr.jfluxpackagegenerator.jobsheet.IssueBackmatterInfo;
import com.spr.jfluxpackagegenerator.jobsheet.IssueCopyright;
import com.spr.jfluxpackagegenerator.jobsheet.IssueFrontmatterInfo;
import com.spr.jfluxpackagegenerator.jobsheet.IssueHistory;
import com.spr.jfluxpackagegenerator.jobsheet.IssueInfo;
import com.spr.jfluxpackagegenerator.jobsheet.IssueJobSheet;
import com.spr.jfluxpackagegenerator.jobsheet.IssueTitle;
import com.spr.jfluxpackagegenerator.jobsheet.JobSheet;
import com.spr.jfluxpackagegenerator.jobsheet.OnlineDate;
import com.spr.jfluxpackagegenerator.jobsheet.ProductionInfo;
import com.spr.jfluxpackagegenerator.jobsheet.VolumeInfo;
import com.spr.jfluxpackagegenerator.jobsheet.WorkflowInfo;
import com.spr.jfluxpackagegenerator.model.IssueFilesToPublisherModel;
import com.spr.jfluxpackagegenerator.model.data.DataStorage;
import com.spr.jfluxpackagegenerator.model.enums.Decision;
import com.spr.jfluxpackagegenerator.model.enums.IssueType;
import com.spr.jfluxpackagegenerator.ui.layout.id.DeliverablesCompoundLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.IssueInfoLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.JournalInfoLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.MainControlLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.VolumeInfoLayoutIds;

/**
 * The class is designed to implement Builder pattern to create different parts of issue jobsheet.
 * 
 * @author Alexey Dergalev
 */
public class IssueJobSheetBuilder implements CommonJobSheetBuilder {
    
    
    private static final Format ISSUE_JOBSHET_NAME =
            new MessageFormat("{0}_{1}_{2}{3}_JobSheet_500.xml");
    
    private final JobSheetBuilder jobsheetBuilder;
    
    private int articleCount;
    
    private final Map<String, String> templatesMap = new HashMap<String, String>();
    
    public IssueJobSheetBuilder(final List<DataStorage> storages) {
        jobsheetBuilder = new JobSheetBuilder(storages);
    }
    
    @Override
    public JobSheet getProduct() {
        return jobsheetBuilder.getJobSheet();
    }
    
    @Override
    public void build() {
        templatesMap.clear();
        jobsheetBuilder.buildJobsheet();
        jobsheetBuilder.buildIssueJobSheet();
        
        jobsheetBuilder.buildPublisherInfo();
        jobsheetBuilder.buildJournalInfo();
        buildVolumeInfo();
        buildIssueInfo();
        jobsheetBuilder.buildProductionRelatedInfo();
        buildDiscreteIssueObjectInfos();
        buildDeliverablesForCompoundObjects();
        buildFilesToPublisher();
    }
    
    private void buildIssueInfo() {
        final IssueJobSheet issueJobSheet = jobsheetBuilder.getJobSheet().getIssueJobSheet();
        final IssueInfo iInfo = jobsheetBuilder.getObjectFactory().createIssueInfo();
        if (!StringUtils.isBlank(getValueById(IssueInfoLayoutIds.LST_ISS_TYPE.toString()))) {
            iInfo.setIssueType(getValueById(IssueInfoLayoutIds.LST_ISS_TYPE.toString()));
        }
        // check TocLevels
        iInfo.setTocLevels("0");
        if (!StringUtils.isBlank(getValueById(IssueInfoLayoutIds.LST_ISS_OM.toString()))) {
            iInfo.setOutputMedium(getValueById(IssueInfoLayoutIds.LST_ISS_OM.toString()));
        }
        
        iInfo.setIssueIDStart(getValueById(IssueInfoLayoutIds.TXT_ISS_ID_ST.toString()));
        iInfo.setIssueIDEnd(getValueById(IssueInfoLayoutIds.TXT_ISS_ID_END.toString()));
        
        // issue history
        final String onlineYear = (getValueById(IssueInfoLayoutIds.TXT_ISS_ONL_YEAR.toString()));
        final String coverYear = (getValueById(IssueInfoLayoutIds.TXT_ISS_COV_YEAR.toString()));
        
        if (!StringUtils.isBlank(onlineYear) || !StringUtils.isBlank(coverYear)) {
            final IssueHistory history = jobsheetBuilder.getObjectFactory().createIssueHistory();
            if (!StringUtils.isBlank(onlineYear)) {
                final String onlineMonth =
                        (getValueById(IssueInfoLayoutIds.TXT_ISS_ONL_MONTH.toString()));
                final String onlineDay =
                        (getValueById(IssueInfoLayoutIds.TXT_ISS_ONL_DAY.toString()));
                final OnlineDate onlineDate = jobsheetBuilder.getObjectFactory().createOnlineDate();
                onlineDate.setYear(coverYear);
                onlineDate.setMonth(onlineMonth);
                onlineDate.setDay(onlineDay);
                history.setOnlineDate(onlineDate);
                
            }
            if (!StringUtils.isBlank(coverYear)) {
                final String coverMonth =
                        (getValueById(IssueInfoLayoutIds.TXT_ISS_COV_MONTH.toString()));
                final CoverDate coverDate = jobsheetBuilder.getObjectFactory().createCoverDate();
                coverDate.setYear(coverYear);
                coverDate.setMonth(coverMonth);
                history.setCoverDate(coverDate);
                
            }
            iInfo.setIssueHistory(history);
        }
        //
        
        final IssueCopyright iCopyRight = jobsheetBuilder.getObjectFactory().createIssueCopyright();
        
        iCopyRight.setCopyrightHolderName(getValueById(IssueInfoLayoutIds.TXT_ISS_CHN.toString()));
        iCopyRight.setCopyrightYear(getValueById(IssueInfoLayoutIds.TXT_ISS_CY.toString()));
        
        iInfo.setIssueCopyright(iCopyRight);
        final String issueTitle = getValueById(IssueInfoLayoutIds.TXT_ISS_TITLE.toString());
        if (!StringUtils.isBlank(issueTitle)) {
            final IssueTitle title = jobsheetBuilder.getObjectFactory().createIssueTitle();
            title.setValue(issueTitle);
            title.setLanguage(getValueById(IssueInfoLayoutIds.LST_ISS_TIT_LANG.toString()));
            iInfo.setIssueTitle(title);
        }
        
        iInfo.setIssueArticleCount(Integer.toString(getArticleCount()));
        
        issueJobSheet.setIssueInfo(iInfo);
    }
    
    private void buildVolumeInfo() {
        final IssueJobSheet issueJobSheet = jobsheetBuilder.getJobSheet().getIssueJobSheet();
        final VolumeInfo volInfo = jobsheetBuilder.getObjectFactory().createVolumeInfo();
        volInfo.setVolumeType(getValueById(VolumeInfoLayoutIds.LST_VOL_TYPE.toString()));
        // check TocLevels
        volInfo.setTocLevels("0");
        if (!StringUtils.isBlank(getValueById(VolumeInfoLayoutIds.LST_VOL_OM.toString()))) {
            volInfo.setOutputMedium(getValueById(VolumeInfoLayoutIds.LST_VOL_OM.toString()));
        }
        
        volInfo.setVolumeIDStart(getValueById(VolumeInfoLayoutIds.TXT_VOL_ID_ST.toString()));
        volInfo.setVolumeIDEnd(getValueById(VolumeInfoLayoutIds.TXT_VOL_ID_END.toString()));
        // check issue count later
        volInfo.setVolumeIssueCount(getValueById(VolumeInfoLayoutIds.TXT_VOL_ISSUE_COUNT.toString()));
        
        issueJobSheet.setVolumeInfo(volInfo);
    }
    
    private void buildFilesToPublisher() {
        final ExternalPublisher ext = jobsheetBuilder.getProductionInfo().getWorkflowInfo()
                .getSupplier().getExternalPublisher();
        final FilesToPublisher filesTP =
                jobsheetBuilder.getObjectFactory().createFilesToPublisher();
        final ContentFiles cFiles = jobsheetBuilder.getObjectFactory().createContentFiles();
        
        final String issuePrefix = getIssuePrefix();
        final FilesElementBuilder issueFilesBuilder =
                new FilesElementBuilder(jobsheetBuilder.getObjectFactory(),
                        new IssueFilesToPublisherModel(jobsheetBuilder.getDataStorages()),
                        templatesMap, issuePrefix);
        
        cFiles.getFile().addAll(issueFilesBuilder.buildFiles());
        
        for (int i = 1; i <= getArticleCount(); i++) {
            final String artPrefix = getArticleObjectPrefix(i);
            final FilesElementBuilder builder =
                    new FilesElementBuilder(jobsheetBuilder.getObjectFactory(),
                            jobsheetBuilder.getArticleFilesToPublisherModel(i), templatesMap,
                            artPrefix, "Art_" + Integer.toString(i));
            
            cFiles.getFile().addAll(builder.buildFiles());
        }
        
        filesTP.setContentFiles(cFiles);
        ext.setFilesToPublisher(filesTP);
        
    }
    
    private String getArticleObjectPrefix(final int i) {
        final String prefix = jobsheetBuilder.getArticleObjectPrefix(i);
        return prefix + "_Article/" + prefix;
    }
    
    private void buildDeliverablesForCompoundObjects() {
        final DeliverablesForCompoundObjects prodExPubDelObj =
                jobsheetBuilder.getObjectFactory().createDeliverablesForCompoundObjects();
        
        prodExPubDelObj.setCoverPrintPDF(
                getValueById(DeliverablesCompoundLayoutIds.LST_DELC_CPP.toString()));
        prodExPubDelObj
                .setCoverFigure(getValueById(DeliverablesCompoundLayoutIds.LST_DELC_CF.toString()));
        prodExPubDelObj.setFrontmatterPrintPDF(
                getValueById(DeliverablesCompoundLayoutIds.LST_DELC_FPP.toString()));
        prodExPubDelObj.setBackmatterPrintPDF(
                getValueById(DeliverablesCompoundLayoutIds.LST_DELC_BPP.toString()));
        prodExPubDelObj.setAdvertisementPrintPDF(
                getValueById(DeliverablesCompoundLayoutIds.LST_DELC_APP.toString()));
        prodExPubDelObj.setDiscreteContentObjectOnlinePDF(
                getValueById(DeliverablesCompoundLayoutIds.LST_DELC_DCO.toString()));
        prodExPubDelObj.setDiscreteContentObjectPrintPDF(
                getValueById(DeliverablesCompoundLayoutIds.LST_DELC_DCP.toString()));
        prodExPubDelObj.setPitStopReports(
                getValueById(DeliverablesCompoundLayoutIds.LST_DELC_PSR.toString()));
        prodExPubDelObj.setDiscreteContentObjectXMLWithBody(
                getValueById(DeliverablesCompoundLayoutIds.LST_DELC_DCXB.toString()));
        prodExPubDelObj.setDiscreteContentObjectXMLWithBodyRefsOnly(
                getValueById(DeliverablesCompoundLayoutIds.LST_DELC_DCXR.toString()));
        prodExPubDelObj.setDiscreteContentObjectOnlineMediaObjects(
                getValueById(DeliverablesCompoundLayoutIds.LST_DELC_DCOMO.toString()));
        prodExPubDelObj.setCorrectionSheet(
                getValueById(DeliverablesCompoundLayoutIds.LST_DELC_CS.toString()));
        
        prodExPubDelObj.setIssuePrintPDF(
                getValueById(DeliverablesCompoundLayoutIds.LST_DELC_IPP.toString()));
        
        final Deliverables prodExPubDel = jobsheetBuilder.getObjectFactory().createDeliverables();
        prodExPubDel.setDeliverablesForCompoundObjects(prodExPubDelObj);
        
        // we need a check for null?
        final WorkflowInfo prodWorkflowInfo = jobsheetBuilder.getJobSheet().getIssueJobSheet()
                .getProductionInfo().getWorkflowInfo();
        prodWorkflowInfo.setTaskType("DeliverCompoundObject");
        
        prodWorkflowInfo.getSupplier().getExternalPublisher().setDeliverables(prodExPubDel);
        
    }
    
    private void buildDiscreteIssueObjectInfos() {
        final ProductionInfo prodInfo =
                jobsheetBuilder.getJobSheet().getIssueJobSheet().getProductionInfo();
        // create cover DIOI
        final CoverInfo covInfo = buildCoverInfo();
        if (covInfo != null) {
            final DiscreteIssueObjectInfo coverDioInfo =
                    jobsheetBuilder.getObjectFactory().createDiscreteIssueObjectInfo();
            coverDioInfo.setCoverInfo(covInfo);
            coverDioInfo.setID("Cover");
            prodInfo.getDiscreteIssueObjectInfo().add(coverDioInfo);
        }
        
        // create front matter DIOI
        buildFMDIOI(prodInfo);
        
        final int artCount = getArticleCount();
        for (int i = 1; i <= artCount; i++) {
            final DiscreteIssueObjectInfo dioInfo =
                    jobsheetBuilder.getObjectFactory().createDiscreteIssueObjectInfo();
            dioInfo.setID("Art_" + i);
            final ArticleInfo artInfo = jobsheetBuilder.buildArticleInfo(i);
            buildArticleContext(artInfo);
            dioInfo.setArticleInfo(artInfo);
            dioInfo.setAuthorGroup(buildAuthorGroup(i));
            prodInfo.getDiscreteIssueObjectInfo().add(dioInfo);
        }
        
        // create back matter DIOI
        buildBMDIOI(prodInfo);
    }
    
    private CoverInfo buildCoverInfo() {
        // check whether not CoverPrintPDF="No"
        final boolean isCoverPrintPDF = !Decision.No.toString()
                .equals(getValueById(DeliverablesCompoundLayoutIds.LST_DELC_CPP.toString()));
        
        if (isCoverPrintPDF) {
            final CoverInfo covInfo = jobsheetBuilder.getObjectFactory().createCoverInfo();
            covInfo.setCoverFirstPage("A1");
            covInfo.setCoverLastPage("A4");
            return covInfo;
        }
        return null;
    }
    
    private void buildBMDIOI(final ProductionInfo prodInfo) {
        // check whether not BackmatterPrintPDF ="No"
        final boolean isBackmatterPrintPDF = !Decision.No.toString()
                .equals(getValueById(DeliverablesCompoundLayoutIds.LST_DELC_BPP.toString()));
        if (isBackmatterPrintPDF) {
            final DiscreteIssueObjectInfo dioInfo =
                    jobsheetBuilder.getObjectFactory().createDiscreteIssueObjectInfo();
            
            final IssueBackmatterInfo bmInfo =
                    jobsheetBuilder.getObjectFactory().createIssueBackmatterInfo();
            bmInfo.setIssueBackmatterFirstPage("A3");
            bmInfo.setIssueBackmatterLastPage("A3");
            
            dioInfo.setIssueBackmatterInfo(bmInfo);
            dioInfo.setID("CompleteBackmatter");
            prodInfo.getDiscreteIssueObjectInfo().add(dioInfo);
        }
    }
    
    private void buildFMDIOI(final ProductionInfo prodInfo) {
        // check whether not FrontmatterPrintPDF ="No"
        final boolean isFrontmatterPrintPDF = !Decision.No.toString()
                .equals(getValueById(DeliverablesCompoundLayoutIds.LST_DELC_FPP.toString()));
        if (isFrontmatterPrintPDF) {
            final DiscreteIssueObjectInfo dioInfo =
                    jobsheetBuilder.getObjectFactory().createDiscreteIssueObjectInfo();
            
            final IssueFrontmatterInfo fmInfo =
                    jobsheetBuilder.getObjectFactory().createIssueFrontmatterInfo();
            fmInfo.setIssueFrontmatterFirstPage("A2");
            fmInfo.setIssueFrontmatterLastPage("A2");
            
            dioInfo.setIssueFrontmatterInfo(fmInfo);
            dioInfo.setID("CompleteFrontmatter");
            prodInfo.getDiscreteIssueObjectInfo().add(dioInfo);
        }
    }
    
    private AuthorGroup buildAuthorGroup(final int i) {
        return jobsheetBuilder.buildAuthorGroup(i);
    }
    
    private int getArticleCount() {
        if (articleCount == 0) {
            final String artCount = getValueById(MainControlLayoutIds.LST_ART_COUNT.toString());
            articleCount = Integer.valueOf(artCount);
        }
        return articleCount;
    }
    
    private void buildArticleContext(final ArticleInfo artInfo) {
        // Article Context Journal ID
        final ArticleContext artArticleContext =
                jobsheetBuilder.getObjectFactory().createArticleContext();
        artArticleContext.setJournalID(getValueById(JournalInfoLayoutIds.TXT_JPROR_ID.toString()));
        artArticleContext
                .setIssueIDStart(getValueById(IssueInfoLayoutIds.TXT_ISS_ID_ST.toString()));
        artArticleContext.setIssueIDEnd(getValueById(IssueInfoLayoutIds.TXT_ISS_ID_END.toString()));
        artArticleContext
                .setVolumeIDEnd(getValueById(VolumeInfoLayoutIds.TXT_VOL_ID_END.toString()));
        artArticleContext
                .setVolumeIDStart(getValueById(VolumeInfoLayoutIds.TXT_VOL_ID_ST.toString()));
        artInfo.setArticleContext(artArticleContext);
        
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
        final String journalId = getValueById(JournalInfoLayoutIds.TXT_JPROR_ID.toString());
        final String volumeIdStart = getValueById(VolumeInfoLayoutIds.TXT_VOL_ID_END.toString());
        
        final String issueIdStart = getValueById(IssueInfoLayoutIds.TXT_ISS_ID_ST.toString());
        
        final String prefix = ISSUE_JOBSHET_NAME.format(
                new String[] { journalId, volumeIdStart, isSupplement() ? "s" : "", issueIdStart });
        
        return prefix;
    }
    
    private String getIssuePrefix() {
        final String journalId = getValueById(JournalInfoLayoutIds.TXT_JPROR_ID.toString());
        final String volumeIdStart = getValueById(VolumeInfoLayoutIds.TXT_VOL_ID_END.toString());
        
        final String issueIdStart = getValueById(IssueInfoLayoutIds.TXT_ISS_ID_ST.toString());
        
        return journalId + "_" + volumeIdStart + "_" + (isSupplement() ? "s" : "") + issueIdStart;
    }
    
    private boolean isSupplement() {
        final String issueType = getValueById(IssueInfoLayoutIds.LST_ISS_TYPE.toString());
        
        return IssueType.Supplement.toString().equalsIgnoreCase(issueType);
        
    }
    
}
