package com.spr.packagegenerator.db;

import java.util.List;

import com.spr.packagegenerator.domain.PackageInfo;

public interface PackageInfoDao {
    
    
    public List<PackageInfo> getPackageInfo();
    
    public boolean save(PackageInfo pkgInfo);
}
