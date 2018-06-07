package com.spr.packagegenerator.controller;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.spr.jfluxpackagegenerator.util.AcdcXmlUpdator;
import com.spr.packagegenerator.db.PackageInfoDao;
import com.spr.packagegenerator.db.PackageInfoDaoImpl;
import com.spr.packagegenerator.domain.PackageInfo;

public class PGDataController {
    
    
    private static final Logger LOG = Logger.getLogger(PGDataController.class);
    
    private final PackageInfoDao packageInfoDao;
    
    private final AcdcXmlUpdator acdcxmlupdator = new AcdcXmlUpdator();
    
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    
    public PGDataController() {
        packageInfoDao = new PackageInfoDaoImpl();
    }
    
    public void saveJfluxPgInfo(final String prjctName, final String process,
            final String environment, final File packageFile, final String vendor) {
        
        final String proc = process.substring(0, 4);
        
        final String packName = packageFile.getName();
        final String packSize = calculateFileSizeInKB(packageFile);
        
        final String createdDateDb = new SimpleDateFormat(DATE_FORMAT).format(new Date());
        
        final PackageInfo jfluxPackage = new PackageInfo(prjctName, process, environment, null,
                packName, packSize, null, null, createdDateDb);
        
        jfluxPackage.setProjectName(prjctName);
        jfluxPackage.setProcessName(proc);
        jfluxPackage.setEnvironment(environment);
        jfluxPackage.setFuncScope(null);
        jfluxPackage.setPkgName(packName);
        jfluxPackage.setPkgSize(packSize);
        jfluxPackage.setVendor(vendor);
        jfluxPackage.setFileType(null);
        jfluxPackage.setCreatedDate(createdDateDb);
        
        packageInfoDao.save(jfluxPackage);
        
    }
    
    public String saveAcdcPgInfo(final String prjctName, final String process,
            final String environment, final String articleId, final String journalId,
            final Map<String, String> map, final String fileType, final List<String> suppFiles,
            final List<String> suppFilesType) {
        
        final File acdcZipFile =
                acdcxmlupdator.createfinalZip(articleId, journalId, map, fileType, suppFiles);
        
        final String packName = acdcZipFile.getName();
        final String packSize = calculateFileSizeInKB(acdcZipFile);
        String fileTypes = null;
        
        if (!suppFilesType.isEmpty()) {
            fileTypes = suppFilesType.toString().replace("[", "").replace("]", "");
        }
        
        final String createdDateDb = new SimpleDateFormat(DATE_FORMAT).format(new Date());
        
        final PackageInfo acdcPackage = new PackageInfo(prjctName, process, environment, null,
                packName, packSize, null, fileTypes, createdDateDb);
        
        acdcPackage.setProjectName(prjctName);
        acdcPackage.setProcessName(process);
        acdcPackage.setEnvironment(environment);
        acdcPackage.setFuncScope(null);
        acdcPackage.setPkgName(packName);
        acdcPackage.setPkgSize(packSize);
        acdcPackage.setVendor(null);
        acdcPackage.setFileType(fileTypes);
        acdcPackage.setCreatedDate(createdDateDb);
        
        packageInfoDao.save(acdcPackage);
        suppFilesType.clear();
        return acdcZipFile.getPath();
        
    }
    
    private static String calculateFileSizeInKB(final File file) {
        double size = 0;
        
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            size = fis.getChannel().size() / 1024;
            
        } catch (final Exception e) {
            LOG.error("Exception in calculateFileSizeInKB() is " + e.getMessage());
        }
        
        return size + " KB";
    }
}
