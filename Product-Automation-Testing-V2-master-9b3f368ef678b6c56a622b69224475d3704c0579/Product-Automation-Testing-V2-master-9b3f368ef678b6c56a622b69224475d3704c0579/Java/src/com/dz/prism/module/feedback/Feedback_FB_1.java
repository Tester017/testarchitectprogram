package com.dz.prism.module.feedback;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Feedback_FB_1 {

	public static void ReadExcel() throws IOException {
		Map<String, List<Map<String, String>>> testdat = SeleniumUtils
				.readExcelData(SeleniumUtils.UserDirVar + FeedbackMain.FEEDBACKPROP.getProperty("FB_1_v_testCasePath"));
		SeleniumUtils.testCase = SeleniumUtils.extendReports
				.createTest(FeedbackMain.FEEDBACKPROP.getProperty("FeedBack_Submit") + "_Feedback Submit");
		SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Feedback Creation");
		
		for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
			List<Map<String, String>> innerRows = testRows.getValue();
			for (Map<String, String> values : innerRows) {
				FeedbackCreation(values);

			}
		}
	}

	public static void FeedbackCreation(Map<String, String> values) {
		try {
			// Create a test Case
			SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("FeedBack Creation - "+ values.get(FeedbackMain.FEEDBACKPROP.getProperty("FB_1_v_rowid")));
			
			// Wait until Feedback button gets load in Dashboard page
			SeleniumUtils.setTimeoutUntilVisibility(FeedbackMain.FEEDBACKPROP.getProperty("FB_1_id_feedback_click"),"id");
			// Click Feedback button in Dashboard Page
			SeleniumUtils.ClickOnItems(FeedbackMain.FEEDBACKPROP.getProperty("FB_1_id_feedback_click"), "id");

			// Click Emoji Type
			String Emoji_Type = values.get(FeedbackMain.FEEDBACKPROP.getProperty("FB_1_v_emoji_type"));
			SeleniumUtils.ClickOnItems(FeedbackMain.FEEDBACKPROP.getProperty("FB_1_id_emoji_type").replace("tempValue", Emoji_Type),"xpath");
			// Click Category Type
			SeleniumUtils.ClickOnItems(values.get(FeedbackMain.FEEDBACKPROP.getProperty("FB_1_v_category_type")), "id");
			// Click Submit Button
			SeleniumUtils.ClickOnItems(FeedbackMain.FEEDBACKPROP.getProperty("FB_1_id_feedback_submit_button"),"xpath");
			String Error_Toast_Message = SeleniumUtils.getTextfromField(SeleniumUtils.toastMessageElement, "xpath");
			// Comparing Warning toast message
			if (Error_Toast_Message.equals(FeedbackMain.FEEDBACKPROP.getProperty("FB_1_v_error_toast_message"))) {
				SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Without Entering the Feedback and Verifying the warning Toast Message - "+ values.get(FeedbackMain.FEEDBACKPROP.getProperty("FB_1_v_rowid")),ExtentColor.GREEN));
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(FeedbackMain.moduleName,
						FeedbackMain.FEEDBACKPROP.getProperty("FeedBack_Submit"), 0));
			} else {
				SeleniumUtils.childTest.log(Status.FAIL,
						MarkupHelper.createLabel(
								"Without Entering the Feedback and Verifying the warning Toast Message - "
										+ values.get(FeedbackMain.FEEDBACKPROP.getProperty("FB_1_v_rowid")),
								ExtentColor.RED));
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(FeedbackMain.moduleName,
						FeedbackMain.FEEDBACKPROP.getProperty("FeedBack_Submit"), 1));
			}
			// Send a value to Feedback description textbox field
			SeleniumUtils.setValueToField(FeedbackMain.FEEDBACKPROP.getProperty("FB_1_id_feedback_textbox"),
					values.get(FeedbackMain.FEEDBACKPROP.getProperty("FB_1_v_feedback_textbox")), "xpath");
			SeleniumUtils.ClickOnItems(FeedbackMain.FEEDBACKPROP.getProperty("FB_1_id_feedback_submit_button"),
					"xpath");
			Thread.sleep(5000);
			String Success_Toast_Message = SeleniumUtils.getTextfromField(SeleniumUtils.toastMessageElement, "xpath");

			if (Success_Toast_Message.equals(FeedbackMain.FEEDBACKPROP.getProperty("FB_1_v_success_toast_message"))) {
				SeleniumUtils.childTest.log(Status.PASS,
						MarkupHelper.createLabel(
								"Feedback Added Scuuessfully and Verified Success Toast Message - "
										+ values.get(FeedbackMain.FEEDBACKPROP.getProperty("FB_1_v_rowid")),
								ExtentColor.GREEN));
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(FeedbackMain.moduleName,
						FeedbackMain.FEEDBACKPROP.getProperty("FeedBack_Submit"), 0));
				
			} else {
				SeleniumUtils.childTest.log(Status.FAIL,
						MarkupHelper.createLabel(
								"Feedback Added Scuuessfully and Verified Success Toast Message - "
										+ values.get(FeedbackMain.FEEDBACKPROP.getProperty("FB_1_v_rowid")),
								ExtentColor.RED));
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(FeedbackMain.moduleName,
						FeedbackMain.FEEDBACKPROP.getProperty("FeedBack_Submit"), 1));
			}
			SeleniumUtils.childTest.log(Status.INFO, MarkupHelper.createLabel(SeleniumUtils.createDownloadButton(SeleniumUtils.UserDirVar + FeedbackMain.FEEDBACKPROP.getProperty("FB_1_v_testCasePath")),ExtentColor.TRANSPARENT));	

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
