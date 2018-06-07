package com.spr.jfluxpackagegenerator.model.enums;

public enum ItemType {
    REFERENCEPDF("ReferencePDF"), DELTAPDF("DeltaPDF"), EPSILONPDF("EpsilonPDF"), COPYRIGHTTRANSFER(
            "CopyrightTransfer"), OPENACCESSSTATEMENT("OpenAccessStatement"), OFFPRINTORDER(
            "OffprintOrder"), AUTHORFEEDBACK("AuthorFeedback"), PITSTOREPORT("Pit-Stop-Report"), PRSMETADATA(
            "PRS-Metadata"), CHECKLIST("Checklist"), CORRECTIONSHEET("CorrectionSheet"), EPUB(
            "EPUB");
    
    private final String value;
    
    ItemType(final String name) {
        value = name;
    }
    
    @Override
    public String toString() {
        return value;
    }
    
}
