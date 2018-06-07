package com.spr.jfluxpackagegenerator.util;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

public class StreamHelper {
    
    
    /**
     * Closes an input stream ignoring exceptions. If <code>is</code> is <code>null</code> nothing
     * is done.
     * 
     * @param is The stream to close.
     */
    private static final Logger LOG = Logger.getLogger(StreamHelper.class);
    
    public static void close(final InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (final Exception exc) {
                LOG.error(
                        "Exception in close() while closing  input stream is " + exc.getMessage());
                
            }
        }
    }
    
    /**
     * Closes output stream.
     * 
     * @param os The output stream.
     */
    public static void close(final OutputStream os) {
        if (os != null) {
            try {
                os.flush();
                os.close();
            } catch (final Exception exc) {
                
                LOG.error(
                        "Exception in close() while closing output stream is " + exc.getMessage());
            }
        }
    }
    
}
