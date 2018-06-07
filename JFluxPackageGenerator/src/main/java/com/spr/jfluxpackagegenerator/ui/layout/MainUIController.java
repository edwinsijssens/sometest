package com.spr.jfluxpackagegenerator.ui.layout;

import com.spr.jfluxpackagegenerator.model.enums.Stage;
import com.spr.jfluxpackagegenerator.model.enums.Vendor;
import com.spr.jfluxpackagegenerator.util.Settings;

/**
 * The interface describes main controller methods for the application UI.
 * 
 * @author Alexey Dergalev
 */
public interface MainUIController {
    
    
    /**
     * Create jobsheet xml document.
     */
    void createJobSheet();
    
    /**
     * Show Jobsheet on UI.
     */
    void showJobsheet();
    
    /**
     * Get data from UI layouts and save them to storages.
     */
    void refreshDataStorages();
    
    /**
     * Generate package with created jobsheet and files.
     */
    void generatePackage();
    
    /**
     * Send generated package to vendor.
     */
    void sendPackage();
    
    /**
     * Validate layout fields.
     */
    boolean validateFields();
    
    void setStage(Stage value);
    
    void setVendor(Vendor value);
    
    void setEnv(String value);
    
    void setArticleCount(Integer value);
    
    void reArrangeTabs();
    
    void showTabs();
    
    Settings getSettings();
    
    void saveToDb();
    
}
