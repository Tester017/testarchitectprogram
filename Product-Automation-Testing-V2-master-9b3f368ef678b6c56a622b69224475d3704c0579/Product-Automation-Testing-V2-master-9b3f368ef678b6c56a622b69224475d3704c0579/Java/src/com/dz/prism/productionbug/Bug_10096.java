package com.dz.prism.productionbug;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_10096 {
	static String currDate = 	SeleniumUtils.getCurrDate();
	public static void verify_Duplicate_Openrequest(Properties PRODUCTIONBUGPROP) throws Exception {
		SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_10096 Duplicate Open Request verification");
		// Read excel file
		Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+ ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_v_test_case_path"));
		for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
			List<Map<String, String>> innerRows = testRows.getValue();
			for (Map<String, String> values : innerRows) {
				duplicate_Open_Request_Verification(values);
				
			}
		}
	}
	public static void duplicate_Open_Request_Verification(Map<String, String> values) throws Exception {
		try {
			SeleniumUtils.webDriver.navigate().refresh();
		//Click on Event manager tab
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_EventManagerModule"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_CreateEventButton"), "id");
		//click on create event button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_CreateEventButton"), "id");
		//click on event type dropdown
			String EventType = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_v_EventType"));
			Thread.sleep(2500);
			SeleniumUtils.selectOptGroupDropdownValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_EventTypeSelection"),EventType, "xpath");
		//Set Event title
			SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_EventTitle"), "xpath");
			String EventTitle = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_v_EventTitle"));
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_EventTitle"), EventTitle+currDate, "xpath");
		//Select the date of the event
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_StartDateSelectIcon"), "xpath");
			String date = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_v_StartDate"));
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_StartDateSelect").replace("temp", ""+date+""), "xpath");
		//click on submit button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_SubmitButton"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_MeetingEditButton"), "xpath");
		//click on meeting edit button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_MeetingEditButton"), "xpath");
		//select the city from from dropdown
			SeleniumUtils.selectOptGroupDropdownValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_CitySelection"), values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_v_City")), "xpath");
		//click on save button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_SaveButton"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			//click generate button to generate meeting
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_GenerateButton"), "xpath");
		//Click on meeting maxmize button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_MaximizeButton"), "xpath");
			Robot r1=new Robot();
			r1.keyPress(KeyEvent.VK_PAGE_DOWN);
			r1.keyRelease(KeyEvent.VK_PAGE_DOWN);
			r1.keyPress(KeyEvent.VK_PAGE_DOWN);
			r1.keyRelease(KeyEvent.VK_PAGE_DOWN);
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_SelectedInvestor"), "xpath");
		//To get the contact name from excel
			String ContactName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_v_ContactName"));
		//To set the contact name to field
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_OpenRequestSearch"), ContactName, "xpath");
		//To get the contact email from excel
			String ContactEmail = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_v_ContactEmailId"));
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_ContactListSelect").replace("temp", "" + ContactEmail + ""), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_ContactListSelect").replace("temp", "" + ContactEmail + ""), "xpath");
		//click on required contact	
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_ContactListSelect").replace("temp", "" + ContactEmail + ""), "xpath");
			SeleniumUtils.closeToastMessage();
		//To set the contact name to field
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_OpenRequestSearch"), ContactName, "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_ContactListSelect").replace("temp", "" + ContactEmail + ""), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_ContactListSelect").replace("temp", "" + ContactEmail + ""), "xpath");
		//click on required contact
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_ContactListSelect").replace("temp", "" + ContactEmail + ""), "xpath");
		//To get the toast message
			String toastMessage = SeleniumUtils.getToastMessage();
			if (toastMessage.equals("Requested contact has already been added to the meeting")) {
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("No Open Request Duplicates added for Contact mail - "+ContactEmail,ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_WebElements"),0));
			} else {
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Open Request duplicates added for Contact mail - "+ContactEmail,ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_WebElements"),1));
			}
			Robot r=new Robot();
			r.keyPress(KeyEvent.VK_PAGE_UP);
			r.keyRelease(KeyEvent.VK_PAGE_UP);
			r.keyPress(KeyEvent.VK_PAGE_UP);
			r.keyRelease(KeyEvent.VK_PAGE_UP);
		//Click on Event close button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10096_1_id_EventCloseButton"), "xpath");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
