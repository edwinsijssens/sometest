package com.spr.jfluxpackagegenerator.jobsheet;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.spr.jfluxpackagegenerator.jobsheet.AuthorName;
import com.spr.jfluxpackagegenerator.jobsheet.ContactPersonName;
import com.spr.jfluxpackagegenerator.jobsheet.JobSheetName;
import com.spr.jfluxpackagegenerator.jobsheet.ObjectFactory;

public class JobSheetNameTest {
    
    @Test
    public void testAuthorName() {
        final ObjectFactory obj = new ObjectFactory();
        final AuthorName name = obj.createAuthorName();
        assertTrue(name instanceof JobSheetName);
    }
    
    @Test
    public void testContactPersonName() {
        final ObjectFactory obj = new ObjectFactory();
        final ContactPersonName name = obj.createContactPersonName();
        assertTrue(name instanceof JobSheetName);
    }
    
}
