package com.spr.jfluxpackagegenerator.model.files;

/**
 * The enum is designed to define file entry types for FileToPublisher element of jobsheet.
 * 
 * @author Alexey Dergalev
 */
public enum FileEntry {
    /** . */
    APlusPlus,
    /** . */
    AudioESM,
    /** . */
    VideoESM,
    /** . */
    DataESM,
    /** . */
    ImageESM,
    /** . */
    ReferencePDF,
    /** . */
    DeltaPDF,
    /** . */
    EpsilonPDF,
    /** . */
    CopyrightTransfer,
    /** . */
    OpenAccessStatement,
    /** . */
    OffprintOrder,
    /** . */
    AuthorFeedback,
    /** . */
    ArticleOnlinePDFPitStopReport,
    /** . */
    ArticlePrintPDFPitStopReport,
    /** . */
    CoverPDFPitStopReport,
    /** . */
    FrontMatterPDFPitStopReport,
    /** . */
    BackMatterPDFPitStopReport,
    /** . */
    PRSMetadata,
    /** . */
    Checklist,
    /** . */
    Tex,
    /** . */
    Manuscript,
    /** . */
    CorrectionSheet,
    /** . */
    EPUB,
    /** . */
    ArticleOnlineRenditionItem,
    /** . */
    ArticlePrintRenditionItem,
    /** . */
    IssueFrontMatter,
    /** . */
    IssueBackMatter,
    /** . */
    CoverPDF;
    
    public static boolean isESM(final FileEntry entry) {
        return ImageESM.equals(entry) || AudioESM.equals(entry) || VideoESM.equals(entry)
                || DataESM.equals(entry);
    }
}
