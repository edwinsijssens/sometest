package com.spr.jfluxpackagegenerator.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The utility class is designed to generate file names for FSV issue and article package zips.
 * 
 * @author Alexey Dergalev
 */
public class PackageFileNameGenerator {
    
    private PackageFileNameGenerator() {
        
    }
    
    public static String createArticleFileName(final String journalId, final String regYear,
            final String articleId) {
        return ARTICLE_NAME_FORMAT.format(new String[] { journalId, regYear, articleId })
                + ZIPFILE_TIMESTAMP_FORMAT.format(new Date()) + ".zip";
    }
    
    public static String createIssueFileName(final String journalId, final String volumeId,
            final String issueIdStart, final boolean isSupplement) {
        return ISSUE_NAME_FORMAT.format(new String[] {
                journalId,
                volumeId,
                isSupplement ? "s" : "",
                issueIdStart })
                + ZIPFILE_TIMESTAMP_FORMAT.format(new Date()) + ".zip";
    }
    
    /**
     * Format object ot create long names for articles. Parameters:
     * <ul>
     * <li>journal ID</li>
     * <li>year</li>
     * <li>article id</li>
     * </ul>
     */
    static final Format ARTICLE_NAME_FORMAT = new MessageFormat(//
            "JournalID={0}_Year={1}_ArticleID={2}");
    
    /**
     * Format object to create long names for issues. Parameters:
     * <ul>
     * <li>journal ID</li>
     * <li>volume start id</li>
     * <li><code>"s"</code> for supplements or an empty string for regular and combined isseu</li>
     * <li>issue start id</li>
     * </ul>
     */
    static final Format ISSUE_NAME_FORMAT = new MessageFormat(//
            "JournalID={0}_VolumeIDStart={1}_IssueIDStart={2}{3}");
    
    /**
     * Format of date to be added to file names as a suffix.
     */
    private static final DateFormat ZIPFILE_TIMESTAMP_FORMAT = new SimpleDateFormat(
            "_yyyy-MM-dd_kk-mm-ss");
    
}
