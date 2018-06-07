package com.spr.jfluxpackagegenerator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.spr.jfluxpackagegenerator.model.data.DataStorage;
import com.spr.jfluxpackagegenerator.model.enums.Decision;
import com.spr.jfluxpackagegenerator.model.files.FileEntriesCreator;
import com.spr.jfluxpackagegenerator.model.files.FileEntry;
import com.spr.jfluxpackagegenerator.ui.layout.id.IssueInfoLayoutIds;

public class IssueFilesToPublisherModel implements FilesToPublisherModel {
    
    
    private final List<DataStorage> dataStorages;
    
    /**
     * The set stores only control names which contain "Yes" value.
     */
    private final Set<IssueInfoLayoutIds> keys = new HashSet<IssueInfoLayoutIds>();
    
    public IssueFilesToPublisherModel(final List<DataStorage> storages) {
        dataStorages = storages;
        
        final IssueInfoLayoutIds[] ids = IssueInfoLayoutIds.values();
        for (int i = 0; i < ids.length; i++) {
            final String value = getValueById(ids[i].toString());
            if (!StringUtils.isEmpty(value) && !Decision.No.toString().equalsIgnoreCase(value)) {
                keys.add(ids[i]);
            }
        }
        
    }
    
    private String getValueById(final String id) {
        final Iterator<DataStorage> iter = dataStorages.iterator();
        while (iter.hasNext()) {
            final DataStorage dataStorage = iter.next();
            final String value = dataStorage.getDataValues().get(id);
            if (value != null) {
                return value;
            }
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
        final Iterator<IssueInfoLayoutIds> iter = keys.iterator();
        while (iter.hasNext()) {
            final IssueInfoLayoutIds key = iter.next();
            // get special handling entries
            final FileEntriesCreator<IssueInfoLayoutIds> creator =
                    SPECIAL_ENTRIES_CREATORS.get(key);
            if (creator != null) {
                final List<FileEntry> entries = creator.createEntries(keys);
                list.addAll(entries);
            }
            
        }
        return list;
    }
    
    private static final Map<IssueInfoLayoutIds, FileEntriesCreator<IssueInfoLayoutIds>> SPECIAL_ENTRIES_CREATORS =
            new HashMap<IssueInfoLayoutIds, FileEntriesCreator<IssueInfoLayoutIds>>();
    
    static {
        
        // entries for Cover
        SPECIAL_ENTRIES_CREATORS.put(IssueInfoLayoutIds.LST_FTP_COV_PDF,
                new FileEntriesCreator<IssueInfoLayoutIds>() {
                    
                    
                    @Override
                    public List<FileEntry> createEntries(final Set<IssueInfoLayoutIds> keys) {
                        final List<FileEntry> entries = new ArrayList<FileEntry>();
                        entries.add(FileEntry.CoverPDF);
                        if (keys.contains(IssueInfoLayoutIds.LST_FTP_ISS_PSR_PDF)) {
                            entries.add(FileEntry.CoverPDFPitStopReport);
                        }
                        return entries;
                    }
                });
        // entries for FrontMatter
        SPECIAL_ENTRIES_CREATORS.put(IssueInfoLayoutIds.LST_FTP_FM_PDF,
                new FileEntriesCreator<IssueInfoLayoutIds>() {
                    
                    
                    @Override
                    public List<FileEntry> createEntries(final Set<IssueInfoLayoutIds> keys) {
                        final List<FileEntry> entries = new ArrayList<FileEntry>();
                        entries.add(FileEntry.IssueFrontMatter);
                        if (keys.contains(IssueInfoLayoutIds.LST_FTP_ISS_PSR_PDF)) {
                            entries.add(FileEntry.FrontMatterPDFPitStopReport);
                        }
                        return entries;
                    }
                });
        // entries for BackMatter
        SPECIAL_ENTRIES_CREATORS.put(IssueInfoLayoutIds.LST_FTP_BM_PDF,
                new FileEntriesCreator<IssueInfoLayoutIds>() {
                    
                    
                    @Override
                    public List<FileEntry> createEntries(final Set<IssueInfoLayoutIds> keys) {
                        final List<FileEntry> entries = new ArrayList<FileEntry>();
                        entries.add(FileEntry.IssueBackMatter);
                        if (keys.contains(IssueInfoLayoutIds.LST_FTP_ISS_PSR_PDF)) {
                            entries.add(FileEntry.BackMatterPDFPitStopReport);
                        }
                        return entries;
                    }
                });
    }
    
}
