package com.spr.jfluxpackagegenerator.model.files;

import java.text.Format;
import java.text.MessageFormat;

/**
 * The interface contains format string for FileRef attribute values in "ContentFiles/File" elements
 * 
 * @author Alexey Dergalev
 */
public interface JobSheetFileNames {
    
    
    Format AROOT = new MessageFormat("{0}_Article.xml");
    
    Format ESM = new MessageFormat("{0}_MOESM{1}_ESM.{2}");
    
    Format ARELATED = new MessageFormat("{0}_Article_Object.xml");
    
    Format ONLINE_PDF = new MessageFormat("{0}_OnlinePDF.pdf");
    
    Format PRINT_PDF = new MessageFormat("{0}_PrintPDF.pdf");
    
    Format PRS_METADATA = new MessageFormat("{0}_PRSMetadata.pdf"); // is it a xml?
    
    Format REFERENCE_PDF = new MessageFormat("{0}_ReferencePDF.pdf");
    
    Format MANUSCRIPT = new MessageFormat("{0}_Manuscript.zip");
    
    Format OPEN_ACCESS_STATEMENT = new MessageFormat("{0}_OpenAccessStatement.pdf");
    
    Format PSR_ONLINE_PDF = new MessageFormat("{0}_PitStopReport_OnlinePDF.pdf");
    
    Format PSR_PRINT_PDF = new MessageFormat("{0}_PitStopReport_PrintPDF.pdf");
    
    Format EPSILON_PDF = new MessageFormat("{0}_EpsilonPDF.pdf");
    
    Format DELTA_PDF = new MessageFormat("{0}_DeltaPDF.pdf");
    
    Format CTS = new MessageFormat("{0}_CTS.pdf");
    
    Format CHECKLIST = new MessageFormat("{0}_Checklist.pdf");
    
    Format AUTHOR_FEED_BACK = new MessageFormat("{0}_AuthorFeedback.pdf");
    
    Format OFFPRINT_ORDER = new MessageFormat("{0}_OffprintOrder.pdf"); // is it a xml?
    
    Format EPUB = new MessageFormat("{0}_EPUB.epub");
    
    Format TEX = new MessageFormat("{0}_TEX.zip");
    
    Format CORRECTION_SHEET = new MessageFormat("CorrectionSheet.xml");
    
    Format FRONTMATTER = new MessageFormat("{0}_IssueFrontmatter_PrintPDF.pdf");
    
    Format BACKMATTER = new MessageFormat("{0}_IssueBackmatter_PrintPDF.pdf");
    
    Format ISSUE_PRINT_PDF = new MessageFormat("{0}_{1}/{0}_{2}_PrintPDF.pdf");
    
    Format COVER_FIGURE = new MessageFormat("{0}_Cover/{0}_CoverFigure_Print.tif");
}
