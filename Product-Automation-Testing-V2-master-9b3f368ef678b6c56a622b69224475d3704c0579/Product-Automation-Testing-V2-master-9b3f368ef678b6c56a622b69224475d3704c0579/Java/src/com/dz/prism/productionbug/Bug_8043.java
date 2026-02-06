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

public class Bug_8043 {
	static String currDate = 	SeleniumUtils.getCurrDate();
	public static void verify_Contact_With_Singlequotes(Properties PRODUCTIONBUGPROP) throws Exception {
		SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_8043 Event Request verification for Contact With Single Quotes");
		// Read excel file
		Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+ ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_v_test_case_path"));
		for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
			List<Map<String, String>> innerRows = testRows.getValue();
			for (Map<String, String> values : innerRows) {
				event_Request_For_Contact_With_Singlequotes(values);
				
			}
		}
	}
	public static void event_Request_For_Contact_With_Singlequotes(Map<String, String> values) throws Exception {
		try {
			SeleniumUtils.webDriver.navigate().refresh();
		//Click on Event manager tab
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_EventManagerModule"), "xpath");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_CreateEventButton"), "id");
		//click on create event button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_CreateEventButton"), "id");
		//click on event type dropdown
		String EventType = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_v_EventType"));
		SeleniumUtils.selectOptGroupDropdownValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_EventTypeSelection"), EventType , "xpath");
		//Set Event title
		SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_EventTitle"), "xpath");
		String EventTitle = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_v_EventTitle"));
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_EventTitle"), EventTitle+currDate, "xpath");
		//Select the date of the event
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_StartDateSelectIcon"), "xpath");
		String date = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_v_StartDate"));
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_StartDateSelect").replace("temp", ""+date+""), "xpath");
		//click on submit button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_SubmitButton"), "xpath");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_MeetingEditButton"), "xpath");
		//click on meeting edit button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_MeetingEditButton"), "xpath");
		//select the city from from dropdown
		SeleniumUtils.selectOptGroupDropdownValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_CitySelection"), values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8062_1_v_City")), "xpath");
		//click on save button
		Thread.sleep(2000);
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_SaveButton"), "xpath");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		//select the meeting sub type from dropdown
		SeleniumUtils.selectOptGroupDropdownValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_MeetingSubType"), values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8062_1_v_MeetingType")), "xpath");
		//click generate button to generate meeting
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_GenerateButton"), "xpath");
		//Click on meeting maxmize button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_MaximizeButton"), "xpath");
		SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_OpenRequestSearchBox"), "xpath");
		String contactName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_v_ContactName"));
		//set value to open request box
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_OpenRequestSearchBox"), contactName, "xpath");
		String contactMail = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_v_ContactEmailId"));
		//click on the required contact
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_ContactList11").replace("temp", ""+contactMail+" "), "xpath");
		//click on request edit button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_RequestEditButton"), "xpath");
		//click on Denied button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_DeniedButton"), "xpath");
		Robot r1=new Robot();
		r1.keyPress(KeyEvent.VK_PAGE_UP);
		r1.keyRelease(KeyEvent.VK_PAGE_UP);
		r1.keyPress(KeyEvent.VK_PAGE_UP);
		r1.keyRelease(KeyEvent.VK_PAGE_UP);
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_EventCloseButton"), "xpath");
		//click on calender tab
		SeleniumUtils.tabSelection("Calendar", "Calendar");
		String Date = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_v_Date"));
		String[] split = Date.split("/");
		String s=split[2]+"-"+split[0]+"-"+split[1];
		Thread.sleep(4000);
		SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_CalDate").replace("temp", ""+s+""), "xpath");
		String EventTit = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_v_EventTitle"));
		//click on the meeting needed
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_MeetingSelect").replace("temp", ""+ EventTit+currDate+""), "xpath");
		//Set value to search box
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_SearchBox"), contactName, "xpath");
		String conAcc = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_v_ContactAccount"));
		//click on the required contact
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_ContactList").replace("temp", ""+conAcc+""), "xpath");
		//Click on request button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_RequestButton"), "xpath");
		//click on event close button
		Thread.sleep(4000);
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_EventClose"), "xpath");
		//confirmation for the requested contact
		confirmation_Check(values);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
public static void confirmation_Check(Map<String, String> values) throws Exception {
	try {
	//click on event manager module
	SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_EventManagerModule"), "xpath");
	String Date1 = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_v_Date"));
	String[] split1 = Date1.split("/");
	String s1=split1[2]+"-"+split1[0]+"-"+split1[1];
	Thread.sleep(2500);
	SeleniumUtils.waitUntilElementHide("loading_screen", "id");
	SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_EventDate").replace("temp", ""+s1+""), "xpath");
	String EventTitle1 = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_v_EventTitle"));
	//click on meeting select
	SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_MeetingSelect").replace("temp", ""+EventTitle1+currDate+""), "xpath");
	SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_MeetingSelect").replace("temp", ""+EventTitle1+currDate+""), "xpath");
	//click on meeting maxmize button
	Thread.sleep(2000);
	SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_MaximizeButton"), "xpath");
	SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_OpenRequestSearchBox"), "xpath");
	//click on request edit button
	SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_RequestEditButton"), "xpath");
	//click on confirm button
	SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_ConfirmedButton"), "xpath");
	Thread.sleep(2000);
	String contactEmail1 = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_v_ContactEmailId"));
	String ConfirmText = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_ConfirmedText"), "xpath");
	String Email = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_ConfirmedNameList"), "xpath");
	if (Email.contains(contactEmail1) && ConfirmText.equalsIgnoreCase("CONFIRMED")) {
		//System.out.println("pass");
		SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(Email+" -This Email Contact with single quotes is selected Confirmed for Meeting event",ExtentColor.GREEN));
		SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_WebElements"),0));
	} else {
		//System.out.println("fail");
		SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(Email+" -This Email Contact with single quotes Can't able to Confirm for Meeting event",ExtentColor.RED));
		SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_WebElements"),1));
	}
	SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_EventCloseButton"), "xpath");
	Robot r=new Robot();
	r.keyPress(KeyEvent.VK_PAGE_UP);
	r.keyRelease(KeyEvent.VK_PAGE_UP);
	r.keyPress(KeyEvent.VK_PAGE_UP);
	r.keyRelease(KeyEvent.VK_PAGE_UP);
	SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8043_1_id_EventCloseButton"), "xpath");
}catch (Exception e) {
e.printStackTrace();
}
}
}
