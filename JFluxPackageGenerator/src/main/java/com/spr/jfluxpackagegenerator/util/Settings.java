package com.spr.jfluxpackagegenerator.util;

import java.io.File;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.spr.jfluxpackagegenerator.config.Config;

/**
 * The class contain infomation about configuration settings for the application.
 * 
 * @author Alexey Dergalev
 */
public class Settings {
    
    private static final Logger log = Logger.getLogger(Settings.class.getName());
    
    private final Config cfg;
    
    public Settings() throws Exception {
        final JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
        final Unmarshaller unm = jaxbContext.createUnmarshaller();
        URL configFileUrl = Config.class.getResource("Config.xml");
        if (configFileUrl == null) {
            configFileUrl = Config.class.getResource("Config-default.xml");
        }
        log.warning("loading JFluxPackageGenerator configuration from " + configFileUrl);
        cfg = (Config) unm.unmarshal(configFileUrl);
    }
    
    /*
     * Alternatives listed in this answer: http://stackoverflow.com/a/27933918/447503
     */
    @WebListener
    public static class _WebappGlobals implements ServletContextListener {
        
        public static String workDir;
        
        @Override
        public void contextInitialized(final ServletContextEvent contextEvent) {
            final ServletContext context = contextEvent.getServletContext();
            final File absTmpDir = (File) context.getAttribute(ServletContext.TEMPDIR);
            try {
                workDir = mkdirThrow(absTmpDir, "pkgcreat_work");
                new Settings(); // fail early
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        }
        
        /* Create directory and tell me why it fails */
        private static String mkdirThrow(final File parentDir, final String newSubdir)
                throws Exception {
            final Path dir = FileSystems.getDefault().getPath(parentDir.getPath(), newSubdir);
            Files.createDirectories(dir);
            return dir.toString();
        }
        
        @Override
        public void contextDestroyed(final ServletContextEvent contextEvent) {}
    }
    
    /**
     * Get work folder path.
     * 
     * @return folder path
     */
    public String getWorkPath() {
        return _WebappGlobals.workDir;
    }
    
    /**
     * Get path to folder for FSV packages.
     * 
     * @return folder path
     */
    public String getDestinationPath() {
        return cfg.getDestinationDir();
    }
    
    public Config getConfig() {
        return cfg;
    }
}
