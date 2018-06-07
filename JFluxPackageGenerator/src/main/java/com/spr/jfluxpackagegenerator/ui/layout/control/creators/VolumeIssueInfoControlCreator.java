package com.spr.jfluxpackagegenerator.ui.layout.control.creators;

import com.spr.jfluxpackagegenerator.model.enums.Decision;
import com.spr.jfluxpackagegenerator.model.enums.IssueType;
import com.spr.jfluxpackagegenerator.model.enums.Language;
import com.spr.jfluxpackagegenerator.model.enums.OutputMedium;
import com.spr.jfluxpackagegenerator.ui.layout.MainUIController;
import com.spr.jfluxpackagegenerator.ui.layout.id.IssueInfoLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.id.VolumeInfoLayoutIds;
import com.spr.jfluxpackagegenerator.ui.layout.validator.NotEmptyAttributeValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.TextField;

public class VolumeIssueInfoControlCreator implements LayoutControlCreator {

	@Override
	public void createComponents(final CustomLayout layout, final MainUIController controller) {

		// Volume Information section widgets
		final TextField vStartId = new TextField();
		vStartId.setId(VolumeInfoLayoutIds.TXT_VOL_ID_ST.toString());
		vStartId.addValidator(new NotEmptyAttributeValidator("Volume ID Start must not be empty"));
		vStartId.setValidationVisible(false);
		layout.addComponent(vStartId, VolumeInfoLayoutIds.TXT_VOL_ID_ST.toString());

		final TextField vEndId = new TextField();
		vEndId.setId(VolumeInfoLayoutIds.TXT_VOL_ID_END.toString());
		vEndId.addValidator(new NotEmptyAttributeValidator("Volume ID End must not be empty"));
		vEndId.setValidationVisible(false);
		layout.addComponent(vEndId, VolumeInfoLayoutIds.TXT_VOL_ID_END.toString());

		final ComboBox vType = new ComboBox();
		vType.setId(VolumeInfoLayoutIds.LST_VOL_TYPE.toString());
		vType.addItem(IssueType.Regular);
		vType.addItem(IssueType.Combined);
		vType.select(IssueType.Regular);
		layout.addComponent(vType, VolumeInfoLayoutIds.LST_VOL_TYPE.toString());

		final ComboBox vOM = new ComboBox();
		vOM.setId(VolumeInfoLayoutIds.LST_VOL_OM.toString());
		vOM.addItems((Object[]) OutputMedium.values());
		layout.addComponent(vOM, VolumeInfoLayoutIds.LST_VOL_OM.toString());
		
		final TextField vIssueCount = new TextField();
		vIssueCount.setId(VolumeInfoLayoutIds.TXT_VOL_ISSUE_COUNT.toString());
		vIssueCount.addValidator(new NotEmptyAttributeValidator("Volume Issue Count must not be empty"));
		vIssueCount.setValidationVisible(false);
        layout.addComponent(vIssueCount, VolumeInfoLayoutIds.TXT_VOL_ISSUE_COUNT.toString());

		// Issue Information section widgets
		final TextField iStartId = new TextField();
		iStartId.setId(IssueInfoLayoutIds.TXT_ISS_ID_ST.toString());
		iStartId.addValidator(new NotEmptyAttributeValidator("Issue ID Start must not be empty"));
		iStartId.setValidationVisible(false);
		layout.addComponent(iStartId, IssueInfoLayoutIds.TXT_ISS_ID_ST.toString());

		final TextField iEndId = new TextField();
		iEndId.setId(IssueInfoLayoutIds.TXT_ISS_ID_END.toString());
		iEndId.addValidator(new NotEmptyAttributeValidator("Issue ID End must not be empty"));
		iEndId.setValidationVisible(false);
		layout.addComponent(iEndId, IssueInfoLayoutIds.TXT_ISS_ID_END.toString());

		final ComboBox iType = new ComboBox();
		iType.setId(IssueInfoLayoutIds.LST_ISS_TYPE.toString());
		iType.addItems((Object[]) IssueType.values());
		iType.select(IssueType.Regular);
		layout.addComponent(iType, IssueInfoLayoutIds.LST_ISS_TYPE.toString());

		final ComboBox iOM = new ComboBox();
		iOM.setId(IssueInfoLayoutIds.LST_ISS_OM.toString());
		iOM.addItems((Object[]) OutputMedium.values());
		layout.addComponent(iOM, IssueInfoLayoutIds.LST_ISS_OM.toString());

		final TextField iCopyRightHolderName = new TextField();
		iCopyRightHolderName.setId(IssueInfoLayoutIds.TXT_ISS_CHN.toString());
		layout.addComponent(iCopyRightHolderName, IssueInfoLayoutIds.TXT_ISS_CHN.toString());

		final TextField iCopyRightYear = new TextField();
		iCopyRightYear.setId(IssueInfoLayoutIds.TXT_ISS_CY.toString());
		layout.addComponent(iCopyRightYear, IssueInfoLayoutIds.TXT_ISS_CY.toString());

		final TextField iTitle = new TextField();
		iTitle.setId(IssueInfoLayoutIds.TXT_ISS_TITLE.toString());
		layout.addComponent(iTitle, IssueInfoLayoutIds.TXT_ISS_TITLE.toString());

		final ComboBox iTitleLang = new ComboBox();
		iTitleLang.setId(IssueInfoLayoutIds.LST_ISS_TIT_LANG.toString());
		iTitleLang.addItems((Object[]) Language.values());
		iTitleLang.select(Language.En);
		layout.addComponent(iTitleLang, IssueInfoLayoutIds.LST_ISS_TIT_LANG.toString());

		// section for FilesToPublisher entries
		final ComboBox covBox = new ComboBox();
		covBox.setId(IssueInfoLayoutIds.LST_FTP_COV_PDF.toString());
		covBox.addItems((Object[]) Decision.getBooleanValues());
		covBox.select(Decision.No);
		layout.addComponent(covBox, IssueInfoLayoutIds.LST_FTP_COV_PDF.toString());

		final ComboBox fmBox = new ComboBox();
		fmBox.setId(IssueInfoLayoutIds.LST_FTP_FM_PDF.toString());
		fmBox.addItems((Object[]) Decision.getBooleanValues());
		fmBox.select(Decision.No);
		layout.addComponent(fmBox, IssueInfoLayoutIds.LST_FTP_FM_PDF.toString());

		final ComboBox bmBox = new ComboBox();
		bmBox.setId(IssueInfoLayoutIds.LST_FTP_BM_PDF.toString());
		bmBox.addItems((Object[]) Decision.getBooleanValues());
		bmBox.select(Decision.No);
		layout.addComponent(bmBox, IssueInfoLayoutIds.LST_FTP_BM_PDF.toString());

		final ComboBox psrBox = new ComboBox();
		psrBox.setId(IssueInfoLayoutIds.LST_FTP_ISS_PSR_PDF.toString());
		psrBox.addItems((Object[]) Decision.getBooleanValues());
		psrBox.select(Decision.No);
		layout.addComponent(psrBox, IssueInfoLayoutIds.LST_FTP_ISS_PSR_PDF.toString());

		// IssueHistory controls
		final TextField onlineYear = new TextField();
		onlineYear.setId(IssueInfoLayoutIds.TXT_ISS_ONL_YEAR.toString());
		layout.addComponent(onlineYear, IssueInfoLayoutIds.TXT_ISS_ONL_YEAR.toString());

		final TextField onlineMonth = new TextField();
		onlineMonth.setId(IssueInfoLayoutIds.TXT_ISS_ONL_MONTH.toString());
		layout.addComponent(onlineMonth, IssueInfoLayoutIds.TXT_ISS_ONL_MONTH.toString());

		final TextField onlineDay = new TextField();
		onlineDay.setId(IssueInfoLayoutIds.TXT_ISS_ONL_DAY.toString());
		layout.addComponent(onlineDay, IssueInfoLayoutIds.TXT_ISS_ONL_DAY.toString());

		final TextField coverYear = new TextField();
		coverYear.setId(IssueInfoLayoutIds.TXT_ISS_COV_YEAR.toString());
		layout.addComponent(coverYear, IssueInfoLayoutIds.TXT_ISS_COV_YEAR.toString());

		final TextField coverMonth = new TextField();
		coverMonth.setId(IssueInfoLayoutIds.TXT_ISS_COV_MONTH.toString());
		layout.addComponent(coverMonth, IssueInfoLayoutIds.TXT_ISS_COV_MONTH.toString());

		/* for supplement information */
		final TextField confEvName = new TextField();
		confEvName.setId(IssueInfoLayoutIds.TXT_CONFF_EV_NME.toString());
		layout.addComponent(confEvName, IssueInfoLayoutIds.TXT_CONFF_EV_NME.toString());

		final TextField confEvAbr = new TextField();
		confEvAbr.setId(IssueInfoLayoutIds.TXT_CONFF_EV_ABBR.toString());
		layout.addComponent(confEvAbr, IssueInfoLayoutIds.TXT_CONFF_EV_ABBR.toString());

		final TextField confEvLoc = new TextField();
		confEvLoc.setId(IssueInfoLayoutIds.TXT_CONFF_EV_LOC.toString());
		layout.addComponent(confEvLoc, IssueInfoLayoutIds.TXT_CONFF_EV_LOC.toString());

		final TextField confEvUrl = new TextField();
		confEvUrl.setId(IssueInfoLayoutIds.TXT_CONFF_EV_URL.toString());
		layout.addComponent(confEvUrl, IssueInfoLayoutIds.TXT_CONFF_EV_URL.toString());

		final TextField confEvdate = new TextField();
		confEvdate.setId(IssueInfoLayoutIds.DT_CONFF_EV_DATE.toString());
		layout.addComponent(confEvdate, IssueInfoLayoutIds.DT_CONFF_EV_DATE.toString());

		final TextField confSpoEvNte = new TextField();
		confSpoEvNte.setId(IssueInfoLayoutIds.TXT_CONFF_SPO_NT.toString());
		layout.addComponent(confSpoEvNte, IssueInfoLayoutIds.TXT_CONFF_SPO_NT.toString());

		final TextField confSuppEvNte = new TextField();
		confSuppEvNte.setId(IssueInfoLayoutIds.TXT_CONFF_SUPL_NT.toString());
		layout.addComponent(confSuppEvNte, IssueInfoLayoutIds.TXT_CONFF_SUPL_NT.toString());
	}
}
