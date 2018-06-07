package com.spr.jfluxpackagegenerator.ui.layout.control.creators;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.spr.jfluxpackagegenerator.model.enums.CopyEditingCategory;
import com.spr.jfluxpackagegenerator.model.enums.Decision;
import com.spr.jfluxpackagegenerator.model.enums.Language;
import com.spr.jfluxpackagegenerator.model.enums.ManuFiletype;
import com.spr.jfluxpackagegenerator.model.enums.SupportingFileType;
import com.spr.jfluxpackagegenerator.model.files.FilesPath;
import com.spr.jfluxpackagegenerator.ui.layout.MainUIController;
import com.spr.jfluxpackagegenerator.ui.layout.id.AcdcLayoutIds;
import com.spr.jfluxpackagegenerator.util.RawManuscriptZipCreator;
import com.spr.packagegenerator.constants.PackageGeneratorConstants;
import com.spr.packagegenerator.controller.PGDataController;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.ProgressListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.StartedListener;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import de.steinwedel.messagebox.ButtonId;
import de.steinwedel.messagebox.Icon;
import de.steinwedel.messagebox.MessageBox;

/**
 * @author cjn6673
 */
public class AcdcControlCreator implements LayoutControlCreator {
    
    
    private static final Logger LOG = Logger.getLogger(AcdcControlCreator.class);
    
    private final List<String> suppFiles = new ArrayList<String>();
    
    private final List<String> otherFilesArray = new ArrayList<String>();
    
    private final RawManuscriptZipCreator rmzipcreator = new RawManuscriptZipCreator();
    
    private String articleId = null;
    
    private String jID;
    
    private String cpediting;
    
    String envtVal;
    
    String jidrm;
    
    String filename = null;
    
    private String esm;
    
    public File file;
    
    Button but1;
    
    Label label1;
    
    private static final String CloseButtonCSS = "closeButton";
    
    private static final String CloseButtonOnlyCSS = "closeButtonOnly";
    
    CssLayout cssLayout = new CssLayout();
    
    private Upload upload;
    
    private final Map<String, String> map = new HashMap<String, String>();
    
    PGDataController dataController = new PGDataController();
    
    private static final String PROJECT_NAME = "ACDC";
    
    private static final String PROCESS_ACDC = "S200";
    
    private static final String UPLOADIMG = "Uploaded Image";
    
    private static final String slash = "/";
    
    private static final String uploadedTemplate = "/home/das21/Templates/UploadedTemplates";
    
    File folder = new File(uploadedTemplate);
    
    String fileType = null;
    
    private final List<String> suppFileType = new ArrayList<String>();
    
    @Override
    public void createComponents(final CustomLayout layout, final MainUIController controller) {
        // Components for Manuscript tab
        final ComboBox manuFile = new ComboBox();
        manuFile.setId(AcdcLayoutIds.LST_MANU_FILE.toString());
        manuFile.addItem(PackageGeneratorConstants.DEFAULT_VALUE_DROPDOWN);
        manuFile.setValue(PackageGeneratorConstants.DEFAULT_VALUE_DROPDOWN);
        manuFile.addItems((Object[]) ManuFiletype.values());
        manuFile.setNullSelectionAllowed(false);
        layout.addComponent(manuFile, AcdcLayoutIds.LST_MANU_FILE.toString());
        
        manuFile.addValueChangeListener(new ValueChangeListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                fileType = manuFile.getValue().toString();
                map.put("manuFiletype", fileType);
            }
        });
        
        final CheckBox esmAudio = new CheckBox("ESM-Audio");
        esmAudio.setId(AcdcLayoutIds.CBOX_ESM_AUDIO.toString());
        layout.addComponent(esmAudio, AcdcLayoutIds.CBOX_ESM_AUDIO.toString());
        esmAudio.addValueChangeListener(new ValueChangeListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                if (esmAudio.getValue()) {
                    suppFileType.add(SupportingFileType.EsmAudio.toString());
                    suppFiles.add(FilesPath.esmAudio);
                } else {
                    suppFileType.remove(SupportingFileType.EsmAudio.toString());
                    suppFiles.remove(FilesPath.esmAudio);
                }
            }
        });
        
        final CheckBox esmVdo = new CheckBox("ESM-Video");
        esmVdo.setId(AcdcLayoutIds.CBOX_ESM_VDO.toString());
        layout.addComponent(esmVdo, AcdcLayoutIds.CBOX_ESM_VDO.toString());
        esmVdo.addValueChangeListener(new ValueChangeListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                if (esmVdo.getValue()) {
                    suppFileType.add(SupportingFileType.EsmVideo.toString());
                    suppFiles.add(FilesPath.esmVdo1);
                    suppFiles.add(FilesPath.esmVdo2);
                    suppFiles.add(FilesPath.esmVdo3);
                    suppFiles.add(FilesPath.esmVdo4);
                    suppFiles.add(FilesPath.esmVdo5);
                    suppFiles.add(FilesPath.esmVdo6);
                    suppFiles.add(FilesPath.esmVdo7);
                    suppFiles.add(FilesPath.esmVdo8);
                    suppFiles.add(FilesPath.esmVdo9);
                } else {
                    suppFileType.remove(SupportingFileType.EsmVideo.toString());
                    suppFiles.remove(FilesPath.esmVdo1);
                    suppFiles.remove(FilesPath.esmVdo2);
                    suppFiles.remove(FilesPath.esmVdo3);
                    suppFiles.remove(FilesPath.esmVdo4);
                    suppFiles.remove(FilesPath.esmVdo5);
                    suppFiles.remove(FilesPath.esmVdo6);
                    suppFiles.remove(FilesPath.esmVdo7);
                    suppFiles.remove(FilesPath.esmVdo8);
                    suppFiles.remove(FilesPath.esmVdo9);
                }
            }
        });
        
        final CheckBox esmPdf = new CheckBox("ESM-PDF");
        esmPdf.setId(AcdcLayoutIds.CBOX_ESM_PDF.toString());
        layout.addComponent(esmPdf, AcdcLayoutIds.CBOX_ESM_PDF.toString());
        esmPdf.addValueChangeListener(new ValueChangeListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                if (esmPdf.getValue()) {
                    suppFileType.add(SupportingFileType.EsmPdf.toString());
                    suppFiles.add(FilesPath.esmPDF);
                } else {
                    suppFileType.remove(SupportingFileType.EsmPdf.toString());
                    suppFiles.remove(FilesPath.esmPDF);
                }
            }
        });
        
        final CheckBox imgEps = new CheckBox("Image-EPS");
        imgEps.setId(AcdcLayoutIds.CBOX_IMG_EPS.toString());
        layout.addComponent(imgEps, AcdcLayoutIds.CBOX_IMG_EPS.toString());
        imgEps.addValueChangeListener(new ValueChangeListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                if (imgEps.getValue()) {
                    suppFileType.add(SupportingFileType.ImgEps.toString());
                    suppFiles.add(FilesPath.imgEPS);
                } else {
                    suppFileType.remove(SupportingFileType.ImgEps.toString());
                    suppFiles.remove(FilesPath.imgEPS);
                }
            }
        });
        
        final CheckBox zip = new CheckBox("ZIP");
        zip.setId(AcdcLayoutIds.CBOX_ZIP.toString());
        layout.addComponent(zip, AcdcLayoutIds.CBOX_ZIP.toString());
        zip.addValueChangeListener(new ValueChangeListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                if (zip.getValue()) {
                    suppFileType.add(SupportingFileType.Zip.toString());
                    suppFiles.add(FilesPath.zip);
                } else {
                    suppFileType.remove(SupportingFileType.Zip.toString());
                    suppFiles.remove(FilesPath.zip);
                }
            }
        });
        
        final CheckBox imgTif = new CheckBox("Image-Tif");
        imgTif.setId(AcdcLayoutIds.CBOX_IMG_TIF.toString());
        layout.addComponent(imgTif, AcdcLayoutIds.CBOX_IMG_TIF.toString());
        imgTif.addValueChangeListener(new ValueChangeListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                if (imgTif.getValue()) {
                    suppFileType.add(SupportingFileType.ImgTif.toString());
                    suppFiles.add(FilesPath.imgTif);
                } else {
                    suppFileType.remove(SupportingFileType.ImgTif.toString());
                    suppFiles.remove(FilesPath.imgTif);
                }
            }
        });
        
        final CheckBox tex = new CheckBox("TEX");
        tex.setId(AcdcLayoutIds.CBOX_TEX.toString());
        layout.addComponent(tex, AcdcLayoutIds.CBOX_TEX.toString());
        tex.addValueChangeListener(new ValueChangeListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                if (tex.getValue()) {
                    suppFileType.add(SupportingFileType.Tex.toString());
                    suppFiles.add(FilesPath.tex);
                } else {
                    suppFileType.remove(SupportingFileType.Tex.toString());
                    suppFiles.remove(FilesPath.tex);
                }
            }
        });
        
        final CheckBox fundingInfoxml = new CheckBox("FundingInfo XML");
        fundingInfoxml.setId(AcdcLayoutIds.CBOX_FUNDINGINFO_XML.toString());
        layout.addComponent(fundingInfoxml, AcdcLayoutIds.CBOX_FUNDINGINFO_XML.toString());
        fundingInfoxml.addValueChangeListener(new ValueChangeListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                if (fundingInfoxml.getValue()) {
                    suppFiles.add(FilesPath.fundingInfo);
                    suppFileType.add(SupportingFileType.FundingInfoXml.toString());
                } else {
                    suppFileType.remove(SupportingFileType.FundingInfoXml.toString());
                    suppFiles.remove(FilesPath.fundingInfo);
                }
            }
        });
        
        cssLayout.setId(AcdcLayoutIds.HRLAYOUT.toString());
        cssLayout.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        layout.addComponent(cssLayout, AcdcLayoutIds.HRLAYOUT.toString());
        
        final CheckBox otherFiles = new CheckBox("Other");
        otherFiles.setId(AcdcLayoutIds.CBOX_OTHERFILES.toString());
        layout.addComponent(otherFiles, AcdcLayoutIds.CBOX_OTHERFILES.toString());
        otherFiles.addValueChangeListener(new ValueChangeListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                if (otherFiles.getValue()) {
                    upload.setEnabled(true);
                    
                } else {
                    upload.setEnabled(false);
                    cssLayout.removeAllComponents();
                    otherFilesArray.clear();
                    suppFiles.removeAll(otherFilesArray);
                    suppFileType.remove(SupportingFileType.Other.toString());
                    try {
                        FileUtils.cleanDirectory(folder);
                    } catch (IOException e) {
                        LOG.error("Exception in removing files from folder is " + e.getMessage());
                    }
                    
                }
            }
        });
        
        // componets for s200 tab for acdc
        final TextField jrnlId = new TextField();
        jrnlId.setId(AcdcLayoutIds.BTN_UPLOAD.toString());
        jrnlId.addValueChangeListener(new ValueChangeListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                jID = jrnlId.getValue();
                map.put("JournalID", jID);
                
            }
        });
        layout.addComponent(jrnlId, AcdcLayoutIds.TXT_JRNLID.toString());
        final TextField artId = new TextField();
        artId.setId(AcdcLayoutIds.TXT_ARTID.toString());
        layout.addComponent(artId, AcdcLayoutIds.TXT_ARTID.toString());
        artId.addValueChangeListener(new ValueChangeListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                articleId = artId.getValue();
                map.put("ArticleID", articleId);
                
            }
        });
        
        final ComboBox artLang = new ComboBox();
        artLang.setId(AcdcLayoutIds.LST_ART_LANG.toString());
        artLang.setPageLength(30);
        artLang.addItems((Object[]) Language.values());
        artLang.setNullSelectionAllowed(false);
        artLang.setPageLength(10);
        layout.addComponent(artLang, AcdcLayoutIds.LST_ART_LANG.toString());
        artLang.addValueChangeListener(new ValueChangeListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                final String artlng = artLang.getValue().toString();
                map.put("ArticleTitle", artlng);
                
            }
        });
        
        final ComboBox copyEditCat = new ComboBox();
        copyEditCat.setId(AcdcLayoutIds.LST_COPY_EDIT_CATEGORY.toString());
        copyEditCat.addItems((Object[]) CopyEditingCategory.values());
        layout.addComponent(copyEditCat, AcdcLayoutIds.LST_COPY_EDIT_CATEGORY.toString());
        copyEditCat.setValue("0");
        copyEditCat.setNullSelectionAllowed(false);
        copyEditCat.addValueChangeListener(new ValueChangeListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                cpediting = copyEditCat.getValue().toString();
                map.put("DeliverablesForDiscreteObjects", cpediting);
                
            }
        });
        
        final ComboBox contEsm = new ComboBox();
        contEsm.setId(AcdcLayoutIds.LST_CONT_ESM.toString());
        contEsm.addItems((Object[]) Decision.getBooleanValues());
        layout.addComponent(contEsm, AcdcLayoutIds.LST_CONT_ESM.toString());
        contEsm.setNullSelectionAllowed(false);
        contEsm.addValueChangeListener(new ValueChangeListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void valueChange(final ValueChangeEvent event) {
                esm = contEsm.getValue().toString();
                map.put("ContainsESM", esm);
                
            }
        });
        
        // Generate Package Button details
        final Button btnGenPackage = new Button();
        btnGenPackage.setId(AcdcLayoutIds.BTN_GENPACKS.toString());
        btnGenPackage.setCaption("Generate Package");
        layout.addComponent(btnGenPackage, AcdcLayoutIds.BTN_GENPACKS.toString());
        
        envtVal = MainControlCreator.getEnvVal();
        
        final Image image = new Image(UPLOADIMG);
        image.setVisible(false);
        
        // Implement both receiver that saves upload in a file and
        // listener for successful upload
        class ImageReceiver implements Receiver, SucceededListener {
            
            
            private static final long serialVersionUID = -1276759102490466761L;
            
            @Override
            public OutputStream receiveUpload(String filename, String mimeType) {
                // Create upload stream
                FileOutputStream fos = null; // Stream to write to
                
                try {
                    if (filename.isEmpty()) {
                        Notification.show("Please upload file from System",
                                Notification.Type.ERROR_MESSAGE);
                        return null;
                    }
                    
                    // Open the file for writing.
                    file = new File(uploadedTemplate + slash + filename);
                    
                    fos = new FileOutputStream(file);
                    
                } catch (final java.io.FileNotFoundException e) {
                    new Notification("Could not open file<br/>", e.getMessage(),
                            Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
                    
                    return null;
                }
                return fos; // Return the output stream to write to
                
            }
            
            @Override
            public void uploadSucceeded(SucceededEvent event) {
                // Show the uploaded file in the image viewer
                final File uploadedFile = new File(uploadedTemplate + slash + event.getFilename());
                otherFilesArray.add(uploadedFile.getAbsolutePath());
                suppFileType.add(SupportingFileType.Other.toString());
                image.setVisible(true);
                image.setSource(new FileResource(uploadedFile));
                image.setCaption(UPLOADIMG + uploadedFile.getName());
                
                final Button closeButton = new Button();
                final Button fileLabel = new Button();
                closeButton.setIcon(FontAwesome.TIMES);
                
                fileLabel.setCaption(event.getFilename());
                fileLabel.setVisible(true);
                
                closeButton.addStyleName(CloseButtonCSS);
                closeButton.addStyleName(CloseButtonOnlyCSS);
                fileLabel.addStyleName(CloseButtonCSS);
                cssLayout.addComponents(fileLabel, closeButton);
                
                try {
                    Set<String> hashSet = new HashSet<String>();
                    
                    for (String name : otherFilesArray) {
                        if (hashSet.add(name) == false) {
                            closeButton.setVisible(false);
                            fileLabel.setVisible(false);
                            MessageBox.showPlain(Icon.INFO, "",
                                    "file is already added " + file.getName(), ButtonId.OK);
                            
                            otherFilesArray.remove(uploadedFile.getAbsolutePath());
                        }
                        
                    }
                } catch (Exception e) {
                    LOG.error("Exception in checking duplicate entry is" + e.getMessage());
                }
                closeButton.addClickListener(new ClickListener() {
                    
                    
                    private static final long serialVersionUID = 1L;
                    
                    @Override
                    public void buttonClick(ClickEvent event) {
                        if (uploadedFile.delete()) {
                            otherFilesArray.remove(uploadedFile.getAbsolutePath());
                            suppFiles.removeAll(otherFilesArray);
                            Notification.show("Uploaded File is Deleted successfully");
                        }
                        closeButton.setVisible(false);
                        fileLabel.setVisible(false);
                    }
                });
            }
            
        }
        
        ImageReceiver receiver = new ImageReceiver();
        
        // Create the upload with a caption and set receiver later
        upload = new Upload(" ", receiver);
        upload.setEnabled(false);
        upload.setImmediate(true);
        upload.setButtonCaption("Select");
        upload.setId(AcdcLayoutIds.BTN_UPLOAD.toString());
        upload.addSucceededListener(receiver);
        layout.addComponent(upload, AcdcLayoutIds.BTN_UPLOAD.toString());
        // Prevent too big downloads
        final long uploadLimit = 100000000l;
        upload.addStartedListener(new StartedListener() {
            
            
            private static final long serialVersionUID = 4728847902678459488L;
            
            @Override
            public void uploadStarted(StartedEvent event) {
                
                if (event.getContentLength() > uploadLimit) {
                    Notification.show("Too big file", Notification.Type.ERROR_MESSAGE);
                    upload.interruptUpload();
                }
            }
        });
        
        // Check the size also during progress
        upload.addProgressListener(new ProgressListener() {
            
            
            private static final long serialVersionUID = 8587352676703174995L;
            
            @Override
            public void updateProgress(long readBytes, long contentLength) {
                
                if (readBytes > uploadLimit) {
                    Notification.show("Too big file", Notification.Type.ERROR_MESSAGE);
                    upload.interruptUpload();
                }
            }
        });
        
        // Put the components in a panel
        Panel panel = new Panel("");
        VerticalLayout panelContent = new VerticalLayout();
        panelContent.setMargin(true);
        panelContent.addComponent(image);
        panel.setContent(panelContent);
        // END-EXAMPLE: component.upload.basic
        
        // Create uploads directory
        File uploads = new File(uploadedTemplate + slash);
        if (!uploads.exists() && !uploads.mkdir()) layout
                .addComponent(new Label("ERROR: Could not create upload dir"));
        
        ((VerticalLayout) panel.getContent()).setSpacing(true);
        panel.setWidth("-1");
        
        btnGenPackage.addClickListener(new ClickListener() {
            
            
            private static final long serialVersionUID = 1L;
            
            @Override
            public void buttonClick(final ClickEvent event) {
                
                try {
                    
                    if (jrnlId.isEmpty() || artId.isEmpty() || contEsm.isEmpty()
                            || copyEditCat.isEmpty()
                            || manuFile.isSelected(PackageGeneratorConstants.DEFAULT_VALUE_DROPDOWN)
                            || artLang.isEmpty()) {
                        MessageBox.showPlain(Icon.ERROR, PackageGeneratorConstants.PKG_CREATION,
                                "Please fill the mandatory fields", ButtonId.OK);
                    } else {
                        if (otherFiles.isEnabled()) {
                            
                            suppFiles.addAll(otherFilesArray);
                            
                        } else {
                            suppFiles.removeAll(otherFilesArray);
                            suppFileType.remove(SupportingFileType.Other.toString());
                        }
                        
                        try {
                            filename = dataController.saveAcdcPgInfo(PROJECT_NAME, PROCESS_ACDC,
                                    envtVal, articleId, jID, map, fileType, suppFiles,
                                    suppFileType);
                            rmzipcreator.sftpUploader(filename, MainControlCreator.getEnvVal());
                        } catch (final Exception e) {
                            
                            LOG.error("Exception in uploading file to ftp" + e.getMessage());
                        }
                        
                        MessageBox.showPlain(Icon.INFO, PackageGeneratorConstants.PKG_CREATION,
                                "Package Successfully created ", ButtonId.OK);
                        layout.removeComponent(cssLayout);
                        otherFilesArray.remove(file.getAbsolutePath());
                        
                        suppFiles.removeAll(otherFilesArray);
                        otherFilesArray.clear();
                        suppFiles.clear();
                        FileUtils.cleanDirectory(folder);
                        
                    }
                } catch (final Exception e) {
                    
                    LOG.error("Exception in creating package" + e.getMessage());
                }
                
            }
        });
        
        layout.addComponent(panel);
        
    }
    
}
