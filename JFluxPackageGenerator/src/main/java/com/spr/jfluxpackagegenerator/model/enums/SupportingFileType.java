package com.spr.jfluxpackagegenerator.model.enums;

public enum SupportingFileType {
    
    EsmAudio("ESM Audio"), EsmVideo("ESM Video"), EsmPdf("ESM PDF"), ImgEps("Image EPS"), ImgTif(
            "Image Tif"), Zip(
                    "ZIP"), Tex("TEX"), FundingInfoXml("FundingInfo XML"), Other("Other Files");
    
    private final String value;
    
    SupportingFileType(final String name) {
        value = name;
    }
    
    @Override
    public String toString() {
        return value;
    }
    
}
