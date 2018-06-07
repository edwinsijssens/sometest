package com.spr.jfluxpackagegenerator.jobsheet.builder;

import java.text.DateFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.spr.jfluxpackagegenerator.jobsheet.Affiliation;
import com.spr.jfluxpackagegenerator.jobsheet.ArticleCopyright;
import com.spr.jfluxpackagegenerator.jobsheet.ArticleExternalID;
import com.spr.jfluxpackagegenerator.jobsheet.ArticleHistory;
import com.spr.jfluxpackagegenerator.jobsheet.ArticleInfo;
import com.spr.jfluxpackagegenerator.jobsheet.ArticleJobSheet;
import com.spr.jfluxpackagegenerator.jobsheet.ArticleRelatedObject;
import com.spr.jfluxpackagegenerator.jobsheet.ArticleSubTitle;
import com.spr.jfluxpackagegenerator.jobsheet.ArticleTitle;
import com.spr.jfluxpackagegenerator.jobsheet.Author;
import com.spr.jfluxpackagegenerator.jobsheet.AuthorGroup;
import com.spr.jfluxpackagegenerator.jobsheet.AuthorName;
import com.spr.jfluxpackagegenerator.jobsheet.Contact;
import com.spr.jfluxpackagegenerator.jobsheet.ContactPerson;
import com.spr.jfluxpackagegenerator.jobsheet.ContactPersonName;
import com.spr.jfluxpackagegenerator.jobsheet.Country;
import com.spr.jfluxpackagegenerator.jobsheet.ExternalPublisher;
import com.spr.jfluxpackagegenerator.jobsheet.IssueJobSheet;
import com.spr.jfluxpackagegenerator.jobsheet.JobSheet;
import com.spr.jfluxpackagegenerator.jobsheet.JobSheetName;
import com.spr.jfluxpackagegenerator.jobsheet.JournalInfo;
import com.spr.jfluxpackagegenerator.jobsheet.JournalSubject;
import com.spr.jfluxpackagegenerator.jobsheet.JournalSubjectGroup;
import com.spr.jfluxpackagegenerator.jobsheet.ObjectFactory;
import com.spr.jfluxpackagegenerator.jobsheet.OrgAddress;
import com.spr.jfluxpackagegenerator.jobsheet.Priority;
import com.spr.jfluxpackagegenerator.jobsheet.ProductionInfo;
import com.spr.jfluxpackagegenerator.jobsheet.PublisherInfo;
import com.spr.jfluxpackagegenerator.jobsheet.SuppInfo;
import com.spr.jfluxpackagegenerator.jobsheet.Supplier;
import com.spr.jfluxpackagegenerator.jobsheet.WorkflowInfo;
import com.spr.jfluxpackagegenerator.model.ArticleFilesPublisherModel;
import com.spr.jfluxpackagegenerator.model.AuthorContact;
import com.spr.jfluxpackagegenerator.model.AuthorNameModel;
import com.spr.jfluxpackagegenerator.model.ContactModel;
import com.spr.jfluxpackagegenerator.model.ExternalPublisherContact;
import com.spr.jfluxpackagegenerator.model.ExternalPublisherContactPersonContact;
import com.spr.jfluxpackagegenerator.model.ExternalPublisherContactPersonNameModel;
import com.spr.jfluxpackagegenerator.model.FilesToPublisherModel;
import com.spr.jfluxpackagegenerator.model.NameModel;
import com.spr.jfluxpackagegenerator.model.data.ArticleDataStorage;
import com.spr.jfluxpackagegenerator.model.data.DataStorage;
import com.spr.jfluxpackagegenerator.model.enums.ArticleType;
import com.spr.jfluxpackagegenerator.model.enums.IssueType;
import com.spr.jfluxpackagegenerator.ui.layout.id.ArticleInfoLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.AuthorGroupLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.IssueInfoLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.JournalInfoLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.ProductionInfoLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.PublisherInfoLayoutIds;

/**
 * The class is designed to implement Builder pattern to create different parts of jobsheet.
 * 
 * @author Alexey Dergalev
 */
public class JobSheetBuilder {
    
    
    /** JObSheet Date format. */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd_HH-mm-ss";
    
    private static final Format ARTICLE_FILE_PREFIX = new MessageFormat("{0}_{1}_{2}");
    
    /** factory. */
    private final ObjectFactory obj = new ObjectFactory();
    
    /** Jobsheet. */
    private JobSheet jobSheetObj;
    
    private JobSheetDirectChild jobsheetChild;
    
    private ProductionInfo productionInfo;
    
    private ExternalPublisher prodExPub;
    
    private final List<DataStorage> dataStorages = new ArrayList<DataStorage>();
    
    private final List<ArticleDataStorage> articleDataStorages =
            new ArrayList<ArticleDataStorage>();
    
    JobSheetBuilder(final List<DataStorage> storages) {
        for (final DataStorage storage : storages) {
            if (storage instanceof ArticleDataStorage) {
                articleDataStorages.add((ArticleDataStorage) storage);
            } else {
                dataStorages.add(storage);
            }
        }
    }
    
    List<DataStorage> getDataStorages() {
        return dataStorages;
    }
    
    List<ArticleDataStorage> getArticleDataStorages() {
        return articleDataStorages;
    }
    
    /**
     * Build "JobSheet" element and its attributes.
     */
    void buildJobsheet() {
        Date jobsheetDate;
        
        jobSheetObj = obj.createJobSheet();
        jobsheetDate = new Date();
        
        final DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        jobSheetObj.setJobSheetDate(dateFormat.format(jobsheetDate));
        jobSheetObj.setSupplier("ExternalPublisher");
        jobSheetObj.setVersion("2.4.1");
        
    }
    
    protected void checkParentObject(final Object parentObject) {
        // maybe to replace by sequential calls
        if (parentObject == null) {
            throw new IllegalStateException("Parent object has not been created yet!");
        }
    }
    
    void buildArticleJobSheet() {
        checkParentObject(jobSheetObj);
        final ArticleJobSheet articleObj = obj.createArticleJobSheet();
        jobSheetObj.setArticleJobSheet(articleObj);
        jobsheetChild = articleObj;
        
    }
    
    void buildIssueJobSheet() {
        checkParentObject(jobSheetObj);
        final IssueJobSheet articleObj = obj.createIssueJobSheet();
        jobSheetObj.setIssueJobSheet(articleObj);
        jobsheetChild = articleObj;
    }
    
    /**
     * Build "PublisherInfo" element and its sub-elements and attributes.
     */
    void buildPublisherInfo() {
        
        checkParentObject(jobsheetChild);
        final PublisherInfo pubInfo = obj.createPublisherInfo();
        pubInfo.setPublisherName(getValueById(PublisherInfoLayoutIds.TXT_PUB_NAME.toString()));
        if (!StringUtils.isBlank(getValueById(PublisherInfoLayoutIds.TXT_IMP_NM.toString()))) {
            pubInfo.setPublisherImprintName(
                    getValueById(PublisherInfoLayoutIds.TXT_IMP_NM.toString()));
        }
        pubInfo.setPublisherLocation(getValueById(PublisherInfoLayoutIds.TXT_PUB_LOC.toString()));
        if (!StringUtils.isBlank(getValueById(PublisherInfoLayoutIds.TXT_PUB_URL.toString()))) {
            pubInfo.setPublisherURL(getValueById(PublisherInfoLayoutIds.TXT_PUB_URL.toString()));
        }
        jobsheetChild.setPublisherInfo(pubInfo);
    }
    
    /**
     * Build "JournalInfo" element and its sub-elements and attributes.
     */
    public String getJournalId() {
        final String jrnlId = getValueById(JournalInfoLayoutIds.TXT_JPROR_ID.toString());
        return jrnlId;
    }
    
    void buildJournalInfo() {
        checkParentObject(jobsheetChild);
        final JournalInfo jouObj = obj.createJournalInfo();
        jouObj.setJournalID(getValueById(JournalInfoLayoutIds.TXT_JPROR_ID.toString()));
        if (!StringUtils.isBlank(getValueById(JournalInfoLayoutIds.TXT_JPROR_PISSN.toString()))) {
            jouObj.setJournalPrintISSN(
                    getValueById(JournalInfoLayoutIds.TXT_JPROR_PISSN.toString()));
        }
        if (!StringUtils.isBlank(getValueById(JournalInfoLayoutIds.TXT_JPROR_EISSN.toString()))) {
            jouObj.setJournalElectronicISSN(
                    getValueById(JournalInfoLayoutIds.TXT_JPROR_EISSN.toString()));
        }
        jouObj.setJournalTitle(getValueById(JournalInfoLayoutIds.TXT_JPROR_TITLE.toString()));
        if (!StringUtils.isBlank(getValueById(JournalInfoLayoutIds.TXT_JPROR_STITLE.toString()))) {
            jouObj.setJournalSubTitle(
                    getValueById(JournalInfoLayoutIds.TXT_JPROR_STITLE.toString()));
        }
        jouObj.setJournalAbbreviatedTitle(
                getValueById(JournalInfoLayoutIds.TXT_JPROR_ATITLE.toString()));
        jouObj.setJournalProductType(getValueById(JournalInfoLayoutIds.LST_JPROD_TYPE.toString()));
        if (!StringUtils.isBlank(getValueById(JournalInfoLayoutIds.LST_JPROR_NUM.toString()))) {
            jouObj.setNumberingStyle(getValueById(JournalInfoLayoutIds.LST_JPROR_NUM.toString()));
        }
        if (!StringUtils.isBlank(getValueById(JournalInfoLayoutIds.LST_JPROR_OUT.toString()))) {
            jouObj.setOutputMedium(getValueById(JournalInfoLayoutIds.LST_JPROR_OUT.toString()));
        }
        
        final JournalSubjectGroup jouSubGrpObj = obj.createJournalSubjectGroup();
        final JournalSubject jouPriSubObj = obj.createJournalSubject();
        final JournalSubject jouSecSubObj = obj.createJournalSubject();
        
        final String prSub = getValueById(JournalInfoLayoutIds.TXT_JPROR_PSUB.toString());
        
        if (!(StringUtils.isBlank(prSub))) {
            jouPriSubObj.setType("Primary");
            jouPriSubObj.setValue(prSub);
            jouSubGrpObj.getJournalSubject().add(jouPriSubObj);
        }
        final String scSub = getValueById(JournalInfoLayoutIds.TXT_JPROR_SSUB.toString());
        if (!(StringUtils.isBlank(scSub))) {
            jouSecSubObj.setType("Secondary");
            jouSecSubObj.setValue(scSub);
            jouSubGrpObj.getJournalSubject().add(jouSecSubObj);
        }
        
        if (!(StringUtils.isBlank(scSub)) || !(StringUtils.isBlank(prSub))) {
            jouObj.setJournalSubjectGroup(jouSubGrpObj);
        }
        jobsheetChild.setJournalInfo(jouObj);
        
    }
    
    /**
     * *Build "Supplement Information " element and its sub-elements and attributes.
     */
    
    void buildSupplementInfo() {
        
        checkParentObject(jobsheetChild);
        final String issueType = getValueById(IssueInfoLayoutIds.LST_ISS_TYPE.toString());
        if (IssueType.Supplement.toString().equals(issueType)) {
            final SuppInfo suppInfo = obj.createSuppInfo();
            
            if (!StringUtils
                    .isEmpty(getValueById(IssueInfoLayoutIds.TXT_CONFF_EV_NME.toString()))) {
                suppInfo.setConfEvtName(
                        getValueById(IssueInfoLayoutIds.TXT_CONFF_EV_NME.toString()));
            }
            
            if (!StringUtils
                    .isEmpty(getValueById(IssueInfoLayoutIds.TXT_CONFF_EV_ABBR.toString()))) {
                suppInfo.setConfEvtAbbr(
                        getValueById(IssueInfoLayoutIds.TXT_CONFF_EV_ABBR.toString()));
            }
            if (!StringUtils
                    .isEmpty(getValueById(IssueInfoLayoutIds.TXT_CONFF_EV_LOC.toString()))) {
                suppInfo.setConfEvtLoc(
                        getValueById(IssueInfoLayoutIds.TXT_CONFF_EV_LOC.toString()));
            }
            if (!StringUtils
                    .isEmpty(getValueById(IssueInfoLayoutIds.DT_CONFF_EV_DATE.toString()))) {
                suppInfo.setConfEvtDate(
                        getValueById(IssueInfoLayoutIds.DT_CONFF_EV_DATE.toString()));
            }
            if (!StringUtils
                    .isEmpty(getValueById(IssueInfoLayoutIds.TXT_CONFF_EV_URL.toString()))) {
                suppInfo.setConfEvtUrl(
                        getValueById(IssueInfoLayoutIds.TXT_CONFF_EV_URL.toString()));
            }
            if (!StringUtils
                    .isEmpty(getValueById(IssueInfoLayoutIds.TXT_CONFF_SPO_NT.toString()))) {
                suppInfo.setSponNote(getValueById(IssueInfoLayoutIds.TXT_CONFF_SPO_NT.toString()));
            }
            if (!StringUtils
                    .isEmpty(getValueById(IssueInfoLayoutIds.TXT_CONFF_SUPL_NT.toString()))) {
                suppInfo.setSuppNote(getValueById(IssueInfoLayoutIds.TXT_CONFF_SUPL_NT.toString()));
            }
            
            jobsheetChild.setSuppInfo(suppInfo);
            
        }
    }
    
    /**
     * Build "ProductionInfo", "WorkflowInfo", "Supplier", "ExternalPublisher", "Contact" and
     * "ContactPerson". It DOESN'T CREATE "DiscretIssueObjectInfo", "Deliverables" and
     * "FilesToPublisher".
     */
    void buildProductionRelatedInfo() {
        checkParentObject(jobsheetChild);
        
        productionInfo = obj.createProductionInfo();
        final Supplier prodInfoSupp = obj.createSupplier();
        prodExPub = obj.createExternalPublisher();
        
        final WorkflowInfo prodWorklfowInfo = obj.createWorkflowInfo();
        final Priority prodInfoPrior = obj.createPriority();
        
        prodInfoPrior.setLevel(getValueById(ProductionInfoLayoutIds.LST_PRI_LVL.toString()));
        prodWorklfowInfo.setPriority(prodInfoPrior);
        
        prodExPub.setCompanyName(getValueById(ProductionInfoLayoutIds.TXT_SUP_NM.toString()));
        
        // create external supplier contact and fill its data
        final ExternalPublisherContact exContact = new ExternalPublisherContact();
        if (!StringUtils.isBlank(getValueById(exContact.getOrgNameId()))) {
            final Contact prodExPubCon = buildContact(exContact, 0);
            prodExPub.setContact(prodExPubCon);
        }
        
        final ContactPerson prodExPubConPer = obj.createContactPerson();
        
        // fill data for ContactPersonName
        final ExternalPublisherContactPersonNameModel exConPNModel =
                new ExternalPublisherContactPersonNameModel();
        if (!StringUtils.isBlank(getValueById(exConPNModel.getFamilyNameId()))
                && !StringUtils.isBlank(getValueById(exConPNModel.getGivenName1Id()))) {
            final ContactPersonName prodExPubConPerDetails = obj.createContactPersonName();
            buildName(prodExPubConPerDetails, exConPNModel, 0);
            prodExPubConPer.setContactPersonName(prodExPubConPerDetails);
            
        }
        
        // create external supplier contact and fill its data
        final ExternalPublisherContactPersonContact exConPModel =
                new ExternalPublisherContactPersonContact();
        if (!StringUtils.isBlank(getValueById(exConPModel.getOrgNameId()))) {
            final Contact prodExPubConPersonContact = buildContact(exConPModel, 0);
            prodExPubConPer.setContact(prodExPubConPersonContact);
            
        }
        
        if (!StringUtils.isBlank(getValueById(exConPNModel.getFamilyNameId()))
                && !StringUtils.isBlank(getValueById(exConPNModel.getGivenName1Id()))) {
            prodExPub.setContactPerson(prodExPubConPer);
        }
        
        prodInfoSupp.setExternalPublisher(prodExPub);
        prodWorklfowInfo.setSupplier(prodInfoSupp);
        productionInfo.setWorkflowInfo(prodWorklfowInfo);
        jobsheetChild.setProductionInfo(productionInfo);
        
    }
    
    ProductionInfo getProductionInfo() {
        return productionInfo;
    }
    
    ExternalPublisher getExternalPublisher() {
        return prodExPub;
    }
    
    ArticleInfo buildArticleInfo(final int articleSequenceNumber) {
        checkParentObject(jobsheetChild);
        final ArticleInfo artObj = obj.createArticleInfo();
        
        // ARTICLE TYPE
        final String artTypeValue =
                getValueById(ArticleInfoLayoutIds.LST_ART_TYPE.toString(), articleSequenceNumber);
        
        artObj.setArticleType(artTypeValue);
        // ARTICLE CITATION
        if (!StringUtils.isBlank(getValueById(ArticleInfoLayoutIds.LST_ART_CITE.toString(),
                articleSequenceNumber))) {
            artObj.setArticleCitationID(getValueById(ArticleInfoLayoutIds.LST_ART_CITE.toString(),
                    articleSequenceNumber));
        }
        // ARTICLE ESM
        if (!StringUtils.isBlank(
                getValueById(ArticleInfoLayoutIds.LST_CON_ESM.toString(), articleSequenceNumber))) {
            artObj.setContainsESM(getValueById(ArticleInfoLayoutIds.LST_CON_ESM.toString(),
                    articleSequenceNumber));
        }
        // ARTICLE LANGUAGE
        artObj.setLanguage(
                getValueById(ArticleInfoLayoutIds.LST_ART_LANG.toString(), articleSequenceNumber));
        // ARTICLE NUMBER STYLE
        if (!StringUtils.isBlank(
                getValueById(ArticleInfoLayoutIds.LST_ART_NUM.toString(), articleSequenceNumber))) {
            artObj.setNumberingStyle(getValueById(ArticleInfoLayoutIds.LST_ART_NUM.toString(),
                    articleSequenceNumber));
        }
        // CHECK FOR ARTICLE ID CONDITION TO ADD TOC TO XML FILE
        final String tocLevel =
                getValueById(ArticleInfoLayoutIds.TXT_ART_TOC.toString(), articleSequenceNumber);
        artObj.setTocLevels(StringUtils.isBlank(tocLevel) ? "0" : tocLevel);
        // ARTICLE OUTPUT MEDIUM
        if (!StringUtils.isBlank(
                getValueById(ArticleInfoLayoutIds.LST_ART_OUT.toString(), articleSequenceNumber))) {
            artObj.setOutputMedium(getValueById(ArticleInfoLayoutIds.LST_ART_OUT.toString(),
                    articleSequenceNumber));
        }
        // ARTICLE ID
        artObj.setArticleID(
                getValueById(ArticleInfoLayoutIds.TXT_ART_ID.toString(), articleSequenceNumber));
        // Article EXTERNAL ID
        if (!StringUtils.isBlank(getValueById(ArticleInfoLayoutIds.TXT_ART_EXT_ID.toString(),
                articleSequenceNumber))) {
            final ArticleExternalID artArticleExternalID = obj.createArticleExternalID();
            artArticleExternalID.setType("arXiv");
            artArticleExternalID.setValue(getValueById(
                    ArticleInfoLayoutIds.TXT_ART_EXT_ID.toString(), articleSequenceNumber));
            artObj.setArticleExternalID(artArticleExternalID);
            
        }
        // Article DOI
        if (!StringUtils.isBlank(
                getValueById(ArticleInfoLayoutIds.TXT_ART_DOI.toString(), articleSequenceNumber))) {
            artObj.setArticleDOI(getValueById(ArticleInfoLayoutIds.TXT_ART_DOI.toString(),
                    articleSequenceNumber));
        }
        
        // check element for article sequence number on UI
        final String artSeq =
                getValueById(ArticleInfoLayoutIds.TXT_ART_SEQ.toString(), articleSequenceNumber);
        artObj.setArticleSequenceNumber(
                StringUtils.isBlank(artSeq) ? Integer.toString(articleSequenceNumber) : artSeq);
        
        // Article Title
        final ArticleTitle artArtTitleObj = obj.createArticleTitle();
        artArtTitleObj.setValue(
                getValueById(ArticleInfoLayoutIds.TXT_ART_TITLE.toString(), articleSequenceNumber));
        // Article Title Language
        artArtTitleObj.setLanguage(getValueById(ArticleInfoLayoutIds.LST_ART_TIT_LANG.toString(),
                articleSequenceNumber));
        
        artObj.setArticleTitle(artArtTitleObj);
        
        // Article Category
        if (!StringUtils.isBlank(
                getValueById(ArticleInfoLayoutIds.TXT_ART_CAT.toString(), articleSequenceNumber))) {
            artObj.setArticleCategory(getValueById(ArticleInfoLayoutIds.TXT_ART_CAT.toString(),
                    articleSequenceNumber));
        }
        // Article Sub-Category
        if (!StringUtils.isBlank(getValueById(ArticleInfoLayoutIds.TXT_ART_SUB_CAT.toString(),
                articleSequenceNumber))) {
            artObj.setArticleSubCategory(getValueById(
                    ArticleInfoLayoutIds.TXT_ART_SUB_CAT.toString(), articleSequenceNumber));
        }
        // Article First Page
        if (!StringUtils.isBlank(
                getValueById(ArticleInfoLayoutIds.TXT_ART_FPG.toString(), articleSequenceNumber))) {
            artObj.setArticleFirstPage(getValueById(ArticleInfoLayoutIds.TXT_ART_FPG.toString(),
                    articleSequenceNumber));
        }
        // Article Last Page
        if (!StringUtils.isBlank(
                getValueById(ArticleInfoLayoutIds.TXT_ART_LPG.toString(), articleSequenceNumber))) {
            artObj.setArticleLastPage(getValueById(ArticleInfoLayoutIds.TXT_ART_LPG.toString(),
                    articleSequenceNumber));
        }
        // Article Editor
        if (!StringUtils.isBlank(getValueById(ArticleInfoLayoutIds.TXT_ART_EDIT.toString(),
                articleSequenceNumber))) {
            artObj.setArticleEditorialResponsibility(getValueById(
                    ArticleInfoLayoutIds.TXT_ART_EDIT.toString(), articleSequenceNumber));
        }
        // ARTICLE COPYRIGHT HOLDER NAME
        final ArticleCopyright artArtCopObj = obj.createArticleCopyright();
        artArtCopObj.setCopyrightHolderName(
                getValueById(ArticleInfoLayoutIds.TXT_COPY_NAME.toString(), articleSequenceNumber));
        // ARTICLE COPYRIGHT YEAR
        artArtCopObj.setCopyrightYear(
                getValueById(ArticleInfoLayoutIds.TXT_COPY_YR.toString(), articleSequenceNumber));
        
        // Article Erroneous First Page
        if (!StringUtils.isBlank(getValueById(ArticleInfoLayoutIds.TXT_ART_ERRFP.toString(),
                articleSequenceNumber))) {
            artObj.setErroneousArticleFirstPage(getValueById(
                    ArticleInfoLayoutIds.TXT_ART_ERRFP.toString(), articleSequenceNumber));
        }
        
        // Article Erroneous First Page
        if (!StringUtils.isBlank(getValueById(ArticleInfoLayoutIds.TXT_ART_ERRLP.toString(),
                articleSequenceNumber))) {
            artObj.setErroneousArticleLastPage(getValueById(
                    ArticleInfoLayoutIds.TXT_ART_ERRLP.toString(), articleSequenceNumber));
        }
        // Checking if information is added to Article element
        artObj.setArticleCopyright(artArtCopObj);
        
        // END OF SECTION ARTICLE INFORMATION
        
        // -- START ARTICLE SUB TITLE SECTION --- //
        
        // Article Sub Title
        if (!StringUtils.isBlank(getValueById(ArticleInfoLayoutIds.TXT_ART_SUB_TIT.toString(),
                articleSequenceNumber))) {
            final ArticleSubTitle artArtSubTitleObj = obj.createArticleSubTitle();
            artArtSubTitleObj.setValue(getValueById(ArticleInfoLayoutIds.TXT_ART_SUB_TIT.toString(),
                    articleSequenceNumber));
            // Article Sub Title Language
            artArtSubTitleObj.setLanguage(getValueById(
                    ArticleInfoLayoutIds.LST_ART_SUB_LANG.toString(), articleSequenceNumber));
            // Article Sub title Output Medium
            if (!StringUtils.isBlank(getValueById(
                    ArticleInfoLayoutIds.LST_REL_SUB_OBJ_TYP.toString(), articleSequenceNumber))) {
                
            }
            artArtSubTitleObj.setOutputMedium(getValueById(
                    ArticleInfoLayoutIds.LST_REL_SUB_OBJ_TYP.toString(), articleSequenceNumber));
            artObj.setArticleSubTitle(artArtSubTitleObj);
            
        }
        
        // Article Related Object Details will be added if Article Type is
        // Erratum
        final ArticleRelatedObject artArtReleatedObj = obj.createArticleRelatedObject();
        if (!(StringUtils.isBlank(artTypeValue))) {
            if (ArticleType.Erratum.toString().equalsIgnoreCase(artTypeValue)) {
                artArtReleatedObj.setRelatedObjectType(getValueById(
                        ArticleInfoLayoutIds.LST_REL_OBJ_TYP.toString(), articleSequenceNumber));
                artArtReleatedObj.setRelatedObjectDOI(getValueById(
                        ArticleInfoLayoutIds.TXT_REL_OBJ_DOI.toString(), articleSequenceNumber));
                artObj.setArticleRelatedObject(artArtReleatedObj);
            }
        }
        
        // -- START OF Article Journal history SECTION -- //
        
        boolean bArticleDate = false;
        // Article Received date
        final ArticleHistory artArticleHistory = obj.createArticleHistory();
        final String artHistoryRecDate =
                getValueById(ArticleInfoLayoutIds.TXT_ART_REC_DT.toString(), articleSequenceNumber);
        if (!(StringUtils.isBlank(artHistoryRecDate))) {
            final ArticleHistory.Received artArticleHisRecDate = obj.createArticleHistoryReceived();
            final String[] dateParts = artHistoryRecDate.split("-");
            final String recDate = dateParts[0]; // 004
            final String recMonth = dateParts[1];
            final String recYear = dateParts[2];
            artArticleHisRecDate.setYear(recYear);
            artArticleHisRecDate.setMonth(recMonth);
            artArticleHisRecDate.setDay(recDate);
            artArticleHistory.setReceived(artArticleHisRecDate);
            bArticleDate = true;
        }
        // Article Revised date
        final String artHistoryRevDate =
                getValueById(ArticleInfoLayoutIds.TXT_ART_REV_DT.toString(), articleSequenceNumber);
        if (!(StringUtils.isBlank(artHistoryRevDate))) {
            final ArticleHistory.Revised artArticleHisRevDate = obj.createArticleHistoryRevised();
            final String[] dateParts = artHistoryRevDate.split("-");
            final String revDate = dateParts[0]; // 004
            final String revMonth = dateParts[1];
            final String revYear = dateParts[2];
            artArticleHisRevDate.setYear(revYear);
            artArticleHisRevDate.setMonth(revMonth);
            artArticleHisRevDate.setDay(revDate);
            artArticleHistory.setRevised(artArticleHisRevDate);
            bArticleDate = true;
        }
        final String artHistoryAccDate =
                getValueById(ArticleInfoLayoutIds.TXT_ART_ACC_DT.toString(), articleSequenceNumber);
        if (!(StringUtils.isBlank(artHistoryAccDate))) {
            final ArticleHistory.Accepted artArticleHisAccDate = obj.createArticleHistoryAccepted();
            final String[] dateParts = artHistoryAccDate.split("-");
            final String accDate = dateParts[0]; // 004
            final String accMonth = dateParts[1];
            final String accYear = dateParts[2];
            artArticleHisAccDate.setYear(accYear);
            artArticleHisAccDate.setMonth(accMonth);
            artArticleHisAccDate.setDay(accDate);
            artArticleHistory.setAccepted(artArticleHisAccDate);
            bArticleDate = true;
        }
        // Article Registration Date
        final String artHistoryRegDate =
                getValueById(ArticleInfoLayoutIds.TXT_ART_REG_DT.toString(), articleSequenceNumber);
        if (!(StringUtils.isBlank(artHistoryRegDate))) {
            final ArticleHistory.RegistrationDate artArticleHisRegDate =
                    obj.createArticleHistoryRegistrationDate();
            final String[] dateParts = artHistoryRegDate.split("-");
            final String regDate = dateParts[0]; // 004
            final String regMonth = dateParts[1];
            final String regYear = dateParts[2];
            artArticleHisRegDate.setYear(regYear);
            artArticleHisRegDate.setMonth(regMonth);
            artArticleHisRegDate.setDay(regDate);
            artArticleHistory.setRegistrationDate(artArticleHisRegDate);
            bArticleDate = true;
        }
        // Article Online Date
        final String artHistoryOnlDate =
                getValueById(ArticleInfoLayoutIds.TXT_ART_ON_DT.toString(), articleSequenceNumber);
        if (!(StringUtils.isBlank(artHistoryOnlDate))) {
            final ArticleHistory.OnlineDate artArticleHisOnlineDate =
                    obj.createArticleHistoryOnlineDate();
            final String[] dateParts = artHistoryOnlDate.split("-");
            final String onlDate = dateParts[0]; // 004
            final String onlMonth = dateParts[1];
            final String onlYear = dateParts[2];
            artArticleHisOnlineDate.setYear(onlYear);
            artArticleHisOnlineDate.setMonth(onlMonth);
            artArticleHisOnlineDate.setDay(onlDate);
            artArticleHistory.setOnlineDate(artArticleHisOnlineDate);
            bArticleDate = true;
        }
        if (bArticleDate) {
            artObj.setArticleHistory(artArticleHistory);
        }
        
        return artObj;
    }
    
    Contact buildContact(final ContactModel model, final int articleSequenceNumber) {
        final Contact contact = obj.createContact();
        contact.setOrgName(getValueById(model.getOrgNameId(), articleSequenceNumber));
        if (!StringUtils.isBlank(getValueById(model.getOrgDivisionId(), articleSequenceNumber))) {
            contact.setOrgDivision(getValueById(model.getOrgDivisionId(), articleSequenceNumber));
        }
        if (!StringUtils.isBlank(
                getValueById(model.getAddressModel().getStreetId(), articleSequenceNumber))) {
            contact.getStreet().add(
                    getValueById(model.getAddressModel().getStreetId(), articleSequenceNumber));
        }
        
        if (!StringUtils.isBlank(
                getValueById(model.getAddressModel().getPostboxId(), articleSequenceNumber))) {
            contact.getPostbox().add(
                    getValueById(model.getAddressModel().getPostboxId(), articleSequenceNumber));
        }
        if (!StringUtils.isBlank(
                getValueById(model.getAddressModel().getPostcodeId(), articleSequenceNumber))) {
            contact.getPostcode().add(
                    getValueById(model.getAddressModel().getPostcodeId(), articleSequenceNumber));
        }
        if (!StringUtils.isBlank(
                getValueById(model.getAddressModel().getCityId(), articleSequenceNumber))) {
            contact.getCity()
                    .add(getValueById(model.getAddressModel().getCityId(), articleSequenceNumber));
        }
        if (!StringUtils.isBlank(
                getValueById(model.getAddressModel().getStateId(), articleSequenceNumber))) {
            contact.getState()
                    .add(getValueById(model.getAddressModel().getStateId(), articleSequenceNumber));
        }
        
        if (!StringUtils.isBlank(
                getValueById(model.getAddressModel().getCountryId(), articleSequenceNumber))) {
            final Country country = obj.createCountry();
            country.setValue(
                    getValueById(model.getAddressModel().getCountryId(), articleSequenceNumber));
            final String countryCode =
                    getValueById(model.getAddressModel().getCountryCodeId(), articleSequenceNumber);
            country.setCode(!StringUtils.isBlank(countryCode) ? countryCode : "En");
            contact.getCountry().add(country);
        }
        
        return contact;
    }
    
    void buildName(final JobSheetName conPName, final NameModel model,
            final int articleSequenceNumber) {
        conPName.setFamilyName(getValueById(model.getFamilyNameId(), articleSequenceNumber));
        conPName.getGivenName().add(getValueById(model.getGivenName1Id(), articleSequenceNumber));
        if (!StringUtils.isBlank(getValueById(model.getGivenName2Id(), articleSequenceNumber))) {
            conPName.getGivenName()
                    .add(getValueById(model.getGivenName2Id(), articleSequenceNumber));
        }
        if (!StringUtils.isBlank(getValueById(model.getGivenName3Id(), articleSequenceNumber))) {
            conPName.getGivenName()
                    .add(getValueById(model.getGivenName3Id(), articleSequenceNumber));
        }
        if (!StringUtils.isBlank(getValueById(model.getParticleId(), articleSequenceNumber))) {
            conPName.setParticle(getValueById(model.getParticleId(), articleSequenceNumber));
            
        }
        if (!StringUtils.isBlank(getValueById(model.getPrefixId(), articleSequenceNumber))) {
            conPName.setPrefix(getValueById(model.getPrefixId(), articleSequenceNumber));
        }
        if (!StringUtils.isBlank(getValueById(model.getSuffixId(), articleSequenceNumber))) {
            conPName.setSuffix(getValueById(model.getSuffixId(), articleSequenceNumber));
        }
    }
    
    AuthorGroup buildAuthorGroup(final int articleSequenceNumber) {
        final AuthorGroup group = obj.createAuthorGroup();
        
        // Create Author
        final Author author = obj.createAuthor();
        final String affId =
                getValueById(AuthorGroupLayoutIds.TXT_AUTH1_AFF.toString(), articleSequenceNumber);
        if (!StringUtils.isBlank(affId)) {
            author.setAffiliationIDS(affId);
            
        }
        final String corAffId =
                getValueById(AuthorGroupLayoutIds.TXT_AUTH1_CAFF.toString(), articleSequenceNumber);
        if (!StringUtils.isBlank(corAffId)) {
            author.setCorrespondingAffiliationID(corAffId);
        }
        final String id = getValueById(AuthorGroupLayoutIds.TXT_AUTH1_SAP_ID.toString(),
                articleSequenceNumber);
        if (!StringUtils.isBlank(id)) {
            author.setAffiliationIDS(id);
        }
        
        // Create AuthorName
        final AuthorName authorName = obj.createAuthorName();
        buildName(authorName, new AuthorNameModel(), articleSequenceNumber);
        authorName.setDisplayOrder("Western");
        author.setAuthorName(authorName);
        
        // Create Contact
        final Contact authorContact = buildContact(new AuthorContact(), articleSequenceNumber);
        author.setContact(authorContact);
        
        // Create Affiliation
        // create separated method?
        final Affiliation aff = obj.createAffiliation();
        aff.setID(getValueById(AuthorGroupLayoutIds.TXT_AFF1_ID.toString(), articleSequenceNumber));
        
        // create address
        // create separated method based on AddressModel handling?
        final OrgAddress address = obj.createOrgAddress();
        if (!StringUtils.isBlank(getValueById(AuthorGroupLayoutIds.TXT_AFF1_STR.toString(),
                articleSequenceNumber))) {
            address.getStreet().add(getValueById(AuthorGroupLayoutIds.TXT_AFF1_STR.toString(),
                    articleSequenceNumber));
        }
        
        if (!StringUtils.isBlank(getValueById(AuthorGroupLayoutIds.TXT_AFF1_PBX.toString(),
                articleSequenceNumber))) {
            address.getPostbox().add(getValueById(AuthorGroupLayoutIds.TXT_AFF1_PBX.toString(),
                    articleSequenceNumber));
        }
        if (!StringUtils.isBlank(getValueById(AuthorGroupLayoutIds.TXT_AFF1_PCD.toString(),
                articleSequenceNumber))) {
            address.getPostcode().add(getValueById(AuthorGroupLayoutIds.TXT_AFF1_PCD.toString(),
                    articleSequenceNumber));
        }
        if (!StringUtils.isBlank(getValueById(AuthorGroupLayoutIds.TXT_AFF1_CTY.toString(),
                articleSequenceNumber))) {
            address.getCity().add(getValueById(AuthorGroupLayoutIds.TXT_AFF1_CTY.toString(),
                    articleSequenceNumber));
        }
        if (!StringUtils.isBlank(getValueById(AuthorGroupLayoutIds.TXT_AFF1_STA.toString(),
                articleSequenceNumber))) {
            address.getState().add(getValueById(AuthorGroupLayoutIds.TXT_AFF1_STA.toString(),
                    articleSequenceNumber));
        }
        
        if (!StringUtils.isBlank(getValueById(AuthorGroupLayoutIds.TXT_AFF1_CNT.toString(),
                articleSequenceNumber))) {
            final Country country = obj.createCountry();
            country.setValue(getValueById(AuthorGroupLayoutIds.TXT_AFF1_CNT.toString(),
                    articleSequenceNumber));
            final String countryCode = getValueById(
                    AuthorGroupLayoutIds.TXT_AFF1_CNT_CODE.toString(), articleSequenceNumber);
            country.setCode(!StringUtils.isBlank(countryCode) ? countryCode : "En");
            address.getCountry().add(country);
        }
        aff.setOrgAddress(address);
        
        final String orgDiv = getValueById(AuthorGroupLayoutIds.TXT_AFF1_ORG_DIV.toString(),
                articleSequenceNumber);
        if (!StringUtils.isBlank(orgDiv)) {
            aff.setOrgDivision(orgDiv);
            
        }
        aff.setOrgName(getValueById(AuthorGroupLayoutIds.TXT_AFF1_ORG_NM.toString(),
                articleSequenceNumber));
        
        //
        group.getAuthor().add(author);
        group.getAffiliation().add(aff);
        
        return group;
    }
    
    FilesToPublisherModel getArticleFilesToPublisherModel(final int artSequenceNumber) {
        return new ArticleFilesPublisherModel(getArticleDataStorageBySeqNumber(artSequenceNumber));
    }
    
    private ArticleDataStorage getArticleDataStorageBySeqNumber(final int artSequenceNumber) {
        final Iterator<ArticleDataStorage> iter = getArticleDataStorages().iterator();
        while (iter.hasNext()) {
            final ArticleDataStorage storage = iter.next();
            if (storage.getSequenceNumber() == artSequenceNumber) {
                return storage;
            }
        }
        return null;
    }
    
    String getArticleObjectPrefix(final int artSeqNumber) {
        final String journalId = getValueById(JournalInfoLayoutIds.TXT_JPROR_ID.toString());
        // registration date instead Copyright Year needed?
        final String regYear =
                getValueById(ArticleInfoLayoutIds.TXT_COPY_YR.toString(), artSeqNumber);
        
        final String articleId =
                getValueById(ArticleInfoLayoutIds.TXT_ART_ID.toString(), artSeqNumber);
        final String prefix =
                ARTICLE_FILE_PREFIX.format(new String[] { journalId, regYear, articleId });
        
        return prefix;
    }
    
    /**
     * Get value form data model based on layout control id.
     * 
     * @param id layout control id
     * @return value
     */
    public String getValueById(final String id) {
        for (int i = 0; i < dataStorages.size(); i++) {
            final String value = dataStorages.get(i).getDataValues().get(id);
            if (value != null) {
                return value;
            }
        }
        for (int i = 0; i < articleDataStorages.size(); i++) {
            final String value = articleDataStorages.get(i).getDataValues().get(id);
            if (value != null) {
                return value;
            }
        }
        return "";
    }
    
    /**
     * Get value form data model based on layout control id.
     * 
     * @param id layout control id
     * @return value
     */
    String getValueById(final String id, final int artSequenceNumber) {
        for (int i = 0; i < articleDataStorages.size(); i++) {
            if (articleDataStorages.get(i).getSequenceNumber() != artSequenceNumber) {
                continue;
            }
            final String value = articleDataStorages.get(i).getDataValues().get(id);
            if (value != null) {
                return value;
            }
        }
        for (int i = 0; i < dataStorages.size(); i++) {
            final String value = dataStorages.get(i).getDataValues().get(id);
            if (value != null) {
                return value;
            }
        }
        return "";
    }
    
    /**
     * Get factory.
     * 
     * @return factory
     */
    protected ObjectFactory getObjectFactory() {
        return obj;
    }
    
    /**
     * Get built product.
     * 
     * @return jobsheet or null if no one method was invoked
     */
    JobSheet getJobSheet() {
        return jobSheetObj;
    }
    
}
