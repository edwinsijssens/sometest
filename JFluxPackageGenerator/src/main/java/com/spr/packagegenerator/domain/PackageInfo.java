package com.spr.packagegenerator.domain;

public class PackageInfo {
    
    
    private String projectName;
    
    private String processName;
    
    private String environment;
    
    private String functionalScope;
    
    private String packageName;
    
    private String packageSize;
    
    private String vendor;
    
    private String fileTypes;
    
    private String createdDate;
    
    public PackageInfo(final String projectName, final String procName, final String environment,
                       final String funcScope, final String pkgName, final String pkgSize,
                       final String vendor, final String fileType, final String createdDate) {
        
    }
    
    public String getProjectName() {
        return projectName;
    }
    
    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }
    
    public String getProcessName() {
        return processName;
    }
    
    public void setProcessName(final String procName) {
        this.processName = procName;
    }
    
    public String getEnvironment() {
        return environment;
    }
    
    public void setEnvironment(final String environment) {
        this.environment = environment;
    }
    
    public String getFuncScope() {
        return functionalScope;
    }
    
    public void setFuncScope(final String funcScope) {
        this.functionalScope = funcScope;
    }
    
    public String getPkgName() {
        return packageName;
    }
    
    public void setPkgName(final String pkgName) {
        this.packageName = pkgName;
    }
    
    public String getPkgSize() {
        return packageSize;
    }
    
    public void setPkgSize(final String pkgSize) {
        this.packageSize = pkgSize;
    }
    
    public String getVendor() {
        return vendor;
    }
    
    public void setVendor(final String vendor) {
        this.vendor = vendor;
    }
    
    public String getFileType() {
        return fileTypes;
    }
    
    public void setFileType(final String fileType) {
        this.fileTypes = fileType;
    }
    
    public String getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(final String createdDate) {
        this.createdDate = createdDate;
    }
    
}
