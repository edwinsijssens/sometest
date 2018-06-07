package com.spr.jfluxpackagegenerator.jobsheet;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.spr.jfluxpackagegenerator.jobsheet.ArticleJobSheet;
import com.spr.jfluxpackagegenerator.jobsheet.IssueJobSheet;
import com.spr.jfluxpackagegenerator.jobsheet.ObjectFactory;
import com.spr.jfluxpackagegenerator.jobsheet.builder.JobSheetDirectChild;

public class JobSheetDirectChildTest {
    
    @Test
    public void testArticleJobSheetElement() {
        final ObjectFactory obj = new ObjectFactory();
        final ArticleJobSheet jobsheet = obj.createArticleJobSheet();
        assertTrue(jobsheet instanceof JobSheetDirectChild);
    }
    
    @Test
    public void testIssueJobSheetElement() {
        final ObjectFactory obj = new ObjectFactory();
        final IssueJobSheet jobsheet = obj.createIssueJobSheet();
        assertTrue(jobsheet instanceof JobSheetDirectChild);
    }
    
}
