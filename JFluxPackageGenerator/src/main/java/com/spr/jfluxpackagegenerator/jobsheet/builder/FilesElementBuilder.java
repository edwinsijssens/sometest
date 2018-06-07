package com.spr.jfluxpackagegenerator.jobsheet.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.spr.jfluxpackagegenerator.jobsheet.File;
import com.spr.jfluxpackagegenerator.jobsheet.ObjectFactory;
import com.spr.jfluxpackagegenerator.jobsheet.builder.file.APageCreator;
import com.spr.jfluxpackagegenerator.jobsheet.builder.file.ARootCreator;
import com.spr.jfluxpackagegenerator.jobsheet.builder.file.ArchiveCreator;
import com.spr.jfluxpackagegenerator.jobsheet.builder.file.AudioESMCreator;
import com.spr.jfluxpackagegenerator.jobsheet.builder.file.CoverCreator;
import com.spr.jfluxpackagegenerator.jobsheet.builder.file.DataESMCreator;
import com.spr.jfluxpackagegenerator.jobsheet.builder.file.FileESMElementCreator;
import com.spr.jfluxpackagegenerator.jobsheet.builder.file.FileElementCreator;
import com.spr.jfluxpackagegenerator.jobsheet.builder.file.ImageObjectCreator;
import com.spr.jfluxpackagegenerator.jobsheet.builder.file.IssuePitStopReportCreator;
import com.spr.jfluxpackagegenerator.jobsheet.builder.file.RenditionItemCreator;
import com.spr.jfluxpackagegenerator.jobsheet.builder.file.VideoESMCreator;
import com.spr.jfluxpackagegenerator.jobsheet.builder.file.WorkItemCreator;
import com.spr.jfluxpackagegenerator.model.FilesToPublisherModel;
import com.spr.jfluxpackagegenerator.model.enums.APageType;
import com.spr.jfluxpackagegenerator.model.enums.ArchiveContent;
import com.spr.jfluxpackagegenerator.model.enums.ItemType;
import com.spr.jfluxpackagegenerator.model.enums.TargetType;
import com.spr.jfluxpackagegenerator.model.files.FileEntry;
import com.spr.jfluxpackagegenerator.model.files.JobSheetFileNames;
import com.spr.jfluxpackagegenerator.model.files.TemplateFileNames;

/**
 * The class is
 * 
 * @author Alexey Dergalev
 */
public class FilesElementBuilder {
    
    
    private final ObjectFactory objectFactory;
    
    private final FilesToPublisherModel filesModel;
    
    private final String fileNamePrefix;
    
    private final Map<String, String> fileRefTemplateMap;
    
    private String discreteObjValue = "";
    
    public FilesElementBuilder(final ObjectFactory obj, final FilesToPublisherModel model,
                               final Map<String, String> templateMap, final String prefix) {
        objectFactory = obj;
        filesModel = model;
        fileRefTemplateMap = templateMap;
        fileNamePrefix = prefix;
    }
    
    public FilesElementBuilder(final ObjectFactory obj, final FilesToPublisherModel model,
                               final Map<String, String> templateMap, final String prefix,
                               final String discretObjId) {
        objectFactory = obj;
        filesModel = model;
        fileRefTemplateMap = templateMap;
        fileNamePrefix = prefix;
        discreteObjValue = discretObjId;
    }
    
    public List<File> buildFiles() {
        final List<File> files = new ArrayList<File>(32);
        int esmCount = 1;
        
        final List<FileEntry> entries = filesModel.getFilesEntries();
        final Iterator<FileEntry> iter = entries.iterator();
        while (iter.hasNext()) {
            final FileEntry entry = iter.next();
            if (FileEntry.isESM(entry)) {
                final FileESMElementCreator creator = FILE_ESM_CREATORS.get(entry);
                if (creator != null) {
                    files.add(creator.create(fileNamePrefix, fileRefTemplateMap, objectFactory,
                            esmCount));
                    esmCount++;
                }
            } else {
                final FileElementCreator creator = FILE_CREATORS.get(entry);
                if (creator != null) {
                    files.add(creator.create(fileNamePrefix, fileRefTemplateMap, objectFactory));
                }
                
            }
            
        }
        
        if (!StringUtils.isEmpty(discreteObjValue)) {
            for (int i = 0; i < files.size(); i++) {
                final File file = files.get(i);
                final String id = file.getDiscreteObjectID();
                if (StringUtils.isEmpty(id)) {
                    file.setDiscreteObjectID(discreteObjValue);
                }
            }
        }
        
        return files;
    }
    
    private final static Map<FileEntry, FileElementCreator> FILE_CREATORS =
            new HashMap<FileEntry, FileElementCreator>();
    
    private final static Map<FileEntry, FileESMElementCreator> FILE_ESM_CREATORS =
            new HashMap<FileEntry, FileESMElementCreator>();
    
    static {
        FILE_CREATORS.put(FileEntry.APlusPlus, new ARootCreator());
        FILE_CREATORS.put(FileEntry.ArticleOnlinePDFPitStopReport,
                new WorkItemCreator(JobSheetFileNames.PSR_ONLINE_PDF,
                        TemplateFileNames.PSR_ONLINE_PDF, ItemType.PITSTOREPORT));
        FILE_CREATORS.put(FileEntry.ArticlePrintPDFPitStopReport,
                new WorkItemCreator(JobSheetFileNames.PSR_PRINT_PDF,
                        TemplateFileNames.PSR_PRINT_PDF, ItemType.PITSTOREPORT));
        FILE_CREATORS.put(FileEntry.ArticleOnlineRenditionItem, new RenditionItemCreator(
                JobSheetFileNames.ONLINE_PDF, TemplateFileNames.ONLINE_PDF, TargetType.OnlinePDF));
        
        FILE_CREATORS.put(FileEntry.ArticlePrintRenditionItem, new RenditionItemCreator(
                JobSheetFileNames.PRINT_PDF, TemplateFileNames.PRINT_PDF, TargetType.PrintPDF));
        FILE_CREATORS.put(FileEntry.AuthorFeedback,
                new WorkItemCreator(JobSheetFileNames.AUTHOR_FEED_BACK,
                        TemplateFileNames.AUTHOR_FEED_BACK, ItemType.AUTHORFEEDBACK));
        FILE_CREATORS.put(FileEntry.BackMatterPDFPitStopReport,
                new IssuePitStopReportCreator(TemplateFileNames.ISSUE_PSR_BM, ItemType.PITSTOREPORT,
                        "IssueBackmatter", APageType.CompleteBackmatter.toString()));
        FILE_CREATORS.put(FileEntry.Checklist, new WorkItemCreator(JobSheetFileNames.CHECKLIST,
                TemplateFileNames.CHECKLIST, ItemType.CHECKLIST));
        FILE_CREATORS.put(FileEntry.CopyrightTransfer, new WorkItemCreator(JobSheetFileNames.CTS,
                TemplateFileNames.CTS, ItemType.COPYRIGHTTRANSFER));
        FILE_CREATORS.put(FileEntry.CorrectionSheet,
                new WorkItemCreator(JobSheetFileNames.CORRECTION_SHEET,
                        TemplateFileNames.CORRECTION_SHEET, ItemType.CORRECTIONSHEET));
        FILE_CREATORS.put(FileEntry.CoverPDF, new CoverCreator());
        FILE_CREATORS.put(FileEntry.CoverPDFPitStopReport, new IssuePitStopReportCreator(
                TemplateFileNames.COVER_PSR, ItemType.PITSTOREPORT, "Cover", "Cover"));
        FILE_CREATORS.put(FileEntry.DeltaPDF, new WorkItemCreator(JobSheetFileNames.DELTA_PDF,
                TemplateFileNames.DELTA_PDF, ItemType.DELTAPDF));
        FILE_CREATORS.put(FileEntry.EpsilonPDF, new WorkItemCreator(JobSheetFileNames.EPSILON_PDF,
                TemplateFileNames.EPSILON_PDF, ItemType.EPSILONPDF));
        FILE_CREATORS.put(FileEntry.EPUB,
                new WorkItemCreator(JobSheetFileNames.EPUB, TemplateFileNames.EPUB, ItemType.EPUB));
        FILE_CREATORS.put(FileEntry.FrontMatterPDFPitStopReport,
                new IssuePitStopReportCreator(TemplateFileNames.ISSUE_PSR_FM, ItemType.PITSTOREPORT,
                        "IssueFrontmatter", APageType.CompleteFrontmatter.toString()));
        FILE_CREATORS.put(FileEntry.IssueBackMatter, new APageCreator(TemplateFileNames.ISSUE_BM,
                APageType.CompleteBackmatter, "A3", "Backmatter"));
        
        FILE_CREATORS.put(FileEntry.IssueFrontMatter, new APageCreator(TemplateFileNames.ISSUE_FM,
                APageType.CompleteFrontmatter, "A2", "Frontmatter"));
        FILE_CREATORS.put(FileEntry.Manuscript, new ArchiveCreator(JobSheetFileNames.MANUSCRIPT,
                TemplateFileNames.MANUSCRIPT, ArchiveContent.Manuscript));
        FILE_CREATORS.put(FileEntry.OffprintOrder,
                new WorkItemCreator(JobSheetFileNames.OFFPRINT_ORDER,
                        TemplateFileNames.OFFPRINT_ORDER, ItemType.OFFPRINTORDER));
        FILE_CREATORS.put(FileEntry.OpenAccessStatement,
                new WorkItemCreator(JobSheetFileNames.OPEN_ACCESS_STATEMENT,
                        TemplateFileNames.OPEN_ACCESS_STATEMENT, ItemType.OPENACCESSSTATEMENT));
        FILE_CREATORS.put(FileEntry.PRSMetadata, new WorkItemCreator(JobSheetFileNames.PRS_METADATA,
                TemplateFileNames.PRS_METADATA, ItemType.PRSMETADATA));
        FILE_CREATORS.put(FileEntry.ReferencePDF,
                new WorkItemCreator(JobSheetFileNames.REFERENCE_PDF,
                        TemplateFileNames.REFERENCE_PDF, ItemType.REFERENCEPDF));
        FILE_CREATORS.put(FileEntry.Tex, new ArchiveCreator(JobSheetFileNames.TEX,
                TemplateFileNames.TEX, ArchiveContent.TEX));
        FILE_ESM_CREATORS.put(FileEntry.VideoESM, new VideoESMCreator());
        FILE_ESM_CREATORS.put(FileEntry.AudioESM, new AudioESMCreator());
        FILE_ESM_CREATORS.put(FileEntry.DataESM, new DataESMCreator());
        FILE_ESM_CREATORS.put(FileEntry.ImageESM, new ImageObjectCreator());
    }
    
}
