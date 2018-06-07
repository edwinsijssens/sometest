package com.spr.packagegenerator.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.spr.packagegenerator.constants.PackageGeneratorConstants;
import com.spr.packagegenerator.domain.PackageInfo;

public class PackageInfoDaoImpl implements PackageInfoDao {
    
    
    private static final Logger LOG = Logger.getLogger(PackageInfoDaoImpl.class);
    
    private static final String COLLECTION = "PKG_GEN_DATA";
    
    private static final String DB_NAME = "viv";
    
    public PackageInfoDaoImpl() {
        
    }
    
    @Override
    public List<PackageInfo> getPackageInfo() {
        
        return null;
    }
    
    @Override
    public boolean save(final PackageInfo packageInfo) {
        
        final Map<String, String> pkgInfoMap = new HashMap<String, String>();
        
        LOG.info("Data entry in DB --> PackgInfoDAO Implmentation");
        pkgInfoMap.put(PackageGeneratorConstants.PROJECT_NAME, packageInfo.getProjectName());
        pkgInfoMap.put(PackageGeneratorConstants.PROCESS_NAME, packageInfo.getProcessName());
        pkgInfoMap.put(PackageGeneratorConstants.ENVIRONMENT, packageInfo.getEnvironment());
        pkgInfoMap.put(PackageGeneratorConstants.FUNC_SCOPE, packageInfo.getFuncScope());
        pkgInfoMap.put(PackageGeneratorConstants.PACKAGE_NAME, packageInfo.getPkgName());
        pkgInfoMap.put(PackageGeneratorConstants.PACKAGE_SIZE, packageInfo.getPkgSize());
        pkgInfoMap.put(PackageGeneratorConstants.VENDOR, packageInfo.getVendor());
        pkgInfoMap.put(PackageGeneratorConstants.FILE_TYPES, packageInfo.getFileType());
        pkgInfoMap.put(PackageGeneratorConstants.CREATED_DATE, packageInfo.getCreatedDate());
        
        try {
            DBUtility.saveToDatabase(pkgInfoMap, COLLECTION, DB_NAME);
        } catch (final Exception e) {
            LOG.error("Exception in save() is " + e.getMessage());
            
            return false;
        }
        
        return true;
    }
    
}
