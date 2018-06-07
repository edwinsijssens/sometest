package com.spr.jfluxpackagegenerator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.spr.jfluxpackagegenerator.model.data.ArticleDataStorage;
import com.spr.jfluxpackagegenerator.model.enums.Decision;
import com.spr.jfluxpackagegenerator.model.files.FileEntriesCreator;
import com.spr.jfluxpackagegenerator.model.files.FileEntry;
import com.spr.jfluxpackagegenerator.ui.layout.id.FilesToPublisherLayoutIds;

public class ArticleFilesPublisherModel implements FilesToPublisherModel {
    
    
    private final ArticleDataStorage articleDataStorage;
    
    /**
     * The set stores only control names which contain "Yes" value.
     */
    private final Set<FilesToPublisherLayoutIds> keys = new HashSet<FilesToPublisherLayoutIds>();
    
    public ArticleFilesPublisherModel(final ArticleDataStorage storage) {
        articleDataStorage = storage;
        
        final FilesToPublisherLayoutIds[] ids = FilesToPublisherLayoutIds.values();
        for (int i = 0; i < ids.length; i++) {
            final String value = getValueById(ids[i].toString());
            if (!StringUtils.isEmpty(value) && !Decision.No.toString().equalsIgnoreCase(value)) {
                keys.add(ids[i]);
            }
        }
        
    }
    
    private String getValueById(final String id) {
        final String value = articleDataStorage.getDataValues().get(id);
        if (value != null) {
            return value;
        }
        return "";
        
    }
    
    @Override
    public boolean isValidPitStopReport() {
        
        return true;
    }
    
    @Override
    public List<FileEntry> getFilesEntries() {
        final List<FileEntry> list = new ArrayList<FileEntry>();
        final Iterator<FilesToPublisherLayoutIds> iter = keys.iterator();
        while (iter.hasNext()) {
            final FilesToPublisherLayoutIds key = iter.next();
            final FileEntry entry = CONTROL_NAME_TO_FILE_ENTRY.get(key);
            if (entry != null) {
                list.add(entry);
            }
            
            // get special handling entries
            final FileEntriesCreator<FilesToPublisherLayoutIds> creator =
                    SPECIAL_ENTRIES_CREATORS.get(key);
            if (creator != null) {
                final List<FileEntry> entries = creator.createEntries(keys);
                list.addAll(entries);
            }
            
        }
        return list;
    }
    
    private static final Map<FilesToPublisherLayoutIds, FileEntry> CONTROL_NAME_TO_FILE_ENTRY =
            new HashMap<FilesToPublisherLayoutIds, FileEntry>();
    
    private static final Map<FilesToPublisherLayoutIds, FileEntriesCreator<FilesToPublisherLayoutIds>> SPECIAL_ENTRIES_CREATORS =
            new HashMap<FilesToPublisherLayoutIds, FileEntriesCreator<FilesToPublisherLayoutIds>>();
    
    static {
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_ARF, FileEntry.APlusPlus);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_VO1, FileEntry.VideoESM);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_VO2, FileEntry.VideoESM);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_AO1, FileEntry.AudioESM);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_AO2, FileEntry.AudioESM);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_IO1, FileEntry.ImageESM);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_IO2, FileEntry.ImageESM);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_DO1, FileEntry.DataESM);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_DO2, FileEntry.DataESM);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_OOPDF,
                FileEntry.OffprintOrder);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_AFPDF,
                FileEntry.AuthorFeedback);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_CPDF, FileEntry.Checklist);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_CTPDF,
                FileEntry.CopyrightTransfer);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_DPDF, FileEntry.DeltaPDF);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_EPDF,
                FileEntry.EpsilonPDF);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_OASPDF,
                FileEntry.OpenAccessStatement);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_PRSM,
                FileEntry.PRSMetadata);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_RPDF,
                FileEntry.ReferencePDF);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_CSXML,
                FileEntry.CorrectionSheet);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_EPUB, FileEntry.EPUB);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_MAN, FileEntry.Manuscript);
        CONTROL_NAME_TO_FILE_ENTRY.put(FilesToPublisherLayoutIds.LST_FTP_TEX, FileEntry.Tex);
        
        // entries for OnlinePDF
        SPECIAL_ENTRIES_CREATORS.put(FilesToPublisherLayoutIds.LST_FTP_OPDF,
                new FileEntriesCreator<FilesToPublisherLayoutIds>() {
                    
                    
                    @Override
                    public List<FileEntry> createEntries(
                            final Set<FilesToPublisherLayoutIds> keys) {
                        final List<FileEntry> entries = new ArrayList<FileEntry>();
                        entries.add(FileEntry.ArticleOnlineRenditionItem);
                        if (keys.contains(FilesToPublisherLayoutIds.LST_FTP_PSRPDF)) {
                            entries.add(FileEntry.ArticleOnlinePDFPitStopReport);
                        }
                        return entries;
                    }
                });
        // entries for PrintPDF
        SPECIAL_ENTRIES_CREATORS.put(FilesToPublisherLayoutIds.LST_FTP_PPDF,
                new FileEntriesCreator<FilesToPublisherLayoutIds>() {
                    
                    
                    @Override
                    public List<FileEntry> createEntries(
                            final Set<FilesToPublisherLayoutIds> keys) {
                        final List<FileEntry> entries = new ArrayList<FileEntry>();
                        entries.add(FileEntry.ArticlePrintRenditionItem);
                        if (keys.contains(FilesToPublisherLayoutIds.LST_FTP_PSRPDF)) {
                            entries.add(FileEntry.ArticlePrintPDFPitStopReport);
                        }
                        return entries;
                    }
                });
    }
    
}
