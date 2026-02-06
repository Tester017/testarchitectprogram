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

public class Bug_10272 {
	static String currDate = 	SeleniumUtils.getCurrDate();
	public static void meeting_Verification(Properties PRODUCTIONBUGPROP) throws Exception {
		SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_10272 Deleted Meeting Displaying in Calender Page Verification");
		// Read excel file
		Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+ ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_v_test_case_path"));
		for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
			List<Map<String, String>> innerRows = testRows.getValue();
			for (Map<String, String> values : innerRows) {
				deleted_Meeting_Verification(values);
				
			}
		}
	}
	public static void deleted_Meeting_Verification(Map<String, String> values) throws Exception {
		try {
			SeleniumUtils.webDriver.navigate().refresh();
		//Click on Event manager tab
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_EventManagerModule"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_CreateEventButton"), "id");
		//click on create event button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_CreateEventButton"), "id");
		//click on event type dropdown
			String EventType = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_v_EventType"));
			Thread.sleep(2500);
			SeleniumUtils.selectOptGroupDropdownValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_EventTypeSelection"),EventType, "xpath");
		//Set Event title
			SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_EventTitle"), "xpath");
			String EventTitle = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_v_EventTitle"));
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_EventTitle"), EventTitle+currDate, "xpath");
		//Select the date of the event
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_StartDateSelectIcon"), "xpath");
			String date = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_v_StartDate"));
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_StartDateSelect").replace("temp", ""+date+""), "xpath");
		//click on submit button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_SubmitButton"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_MeetingEditButton"), "xpath");
		//click on meeting edit button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_MeetingEditButton"), "xpath");
		//select the city from from dropdown
			SeleniumUtils.selectOptGroupDropdownValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_CitySelection"), values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_v_City")), "xpath");
		//click on save button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_SaveButton"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			//click generate button to generate meeting
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_GenerateButton"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			//To get the Event slot time and date
			String EventSlotTime = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_EventSlotTime"), "xpath");
			String[] split3 = EventSlotTime.split(" ");
			//To get event meeting date
			String EventMeetingDate=split3[1]+" "+split3[2];
			String[] split4 = split3[4].split("-");
			//To get event slot time
			String EventMeetingSlotTime=split3[3].replaceFirst("0", "")+split4[0]+" - "+split4[1].replaceFirst("0", "")+split3[5];
			//To get event location
			String EventLocation = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_EventLocation"), "xpath");
			String[] split2 = EventLocation.split(",");
			//To get event meeting place
			String MeetingPlace = split2[0];
			//To get the event type
			String EvenType = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_EventType"), "xpath");
			String[] split = EvenType.split(" ");
			String EventMeetingType=EventType+" - "+split[2];
			//To get Event status
			String EveStatus = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_EventStatus"), "xpath");
			//Click on event close button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_EventCloseButton"), "xpath");
			//Click on calendar module
			//28 url
			//SeleniumUtils.tabSelection("Calendar", "Calendar");
			//110 url
			SeleniumUtils.tabSelection("Calendar");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_MeetingSelect").replace("temp", EventTitle+currDate), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_MeetingSelect").replace("temp", EventTitle+currDate), "xpath");
			Robot r1=new Robot();
			r1.keyPress(KeyEvent.VK_UP);
			r1.keyRelease(KeyEvent.VK_UP);
			r1.keyPress(KeyEvent.VK_UP);
			r1.keyRelease(KeyEvent.VK_UP);
			//click on required meeting
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_MeetingSelect").replace("temp", EventTitle+currDate), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.closeToastMessage();
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_CalendarMeetingDate"), "xpath");
			//To get the Calendar meeting date
			String[] CalMeeTDate = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_CalendarMeetingDate"), "xpath").split(" ");
			String CalMeetMon = CalMeeTDate[0].substring(0, 3);
			String CalendarMeetingDate = CalMeetMon+" "+CalMeeTDate[1];
			//To get the Calendar slot time
			String CalendarSlotTime = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_CalendarSlotTime"), "xpath");
			//To get the calendar meeting type
			String CalendarMeetingType = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_CalendarMeetingType"), "xpath").trim();
			//To get calendar meeting status
			String CalendarMeetingStatus = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_CalendarMeetingStatus"), "xpath");
			//To get meeting location
			String CalendarmeetingLocation = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_CalendarMeetingLocation"), "xpath");
			if (CalendarMeetingDate.equals(EventMeetingDate) && CalendarSlotTime.equals(EventMeetingSlotTime) && CalendarMeetingType.equals(EventMeetingType) && CalendarMeetingStatus.equals(EveStatus) && CalendarmeetingLocation.equals(MeetingPlace)) {
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Created Meeting is displayed in the Calendar Page",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_WebElements"),0));
			} else {
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Created Meeting is not displayed in the Calendar Page",ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_WebElements"),1));
			}
			//Click on calendar event close button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_CalEventCloseButton"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			//click on Event manager module
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_EventManagerModule"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_EventManagerModule"), "xpath");
			SeleniumUtils.webDriver.navigate().refresh();
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			Thread.sleep(2000);
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_MeetingSelect").replace("temp", EventTitle+currDate), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_MeetingSelect").replace("temp", EventTitle+currDate), "xpath");
			Robot r2=new Robot();
			r2.keyPress(KeyEvent.VK_UP);
			r2.keyRelease(KeyEvent.VK_UP);
			r2.keyPress(KeyEvent.VK_UP);
			r2.keyRelease(KeyEvent.VK_UP);
			//click on required meeting
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_MeetingSelect").replace("temp", EventTitle+currDate), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_MeetingDeleteButton"), "xpath");
			//click on meeting delete button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_MeetingDeleteButton"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_MeetingDeleteCfrmButton"), "id");
			//click on delete meeting confirm button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_MeetingDeleteCfrmButton"), "id");
			//Click on event close button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_EventCloseButton"), "xpath");
			//28 url
			//SeleniumUtils.tabSelection("Calendar", "Calendar");
			//110 url
			SeleniumUtils.tabSelection("Calendar");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_MeetingSelect").replace("temp", EventTitle+currDate), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_MeetingSelect").replace("temp", EventTitle+currDate), "xpath");
			Robot r3=new Robot();
			r3.keyPress(KeyEvent.VK_UP);
			r3.keyRelease(KeyEvent.VK_UP);
			r3.keyPress(KeyEvent.VK_UP);
			r3.keyRelease(KeyEvent.VK_UP);
			//click on required meeting
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_MeetingSelect").replace("temp", EventTitle+currDate), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_CalenderMeetingDetails"), "xpath");
			String CalendarMeetingDetails = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_CalenderMeetingDetails"), "xpath");
			if (CalendarMeetingDetails.equals("No Data Available")) {
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Deleted Meeting is Not Displayed in the Calendar Page",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_WebElements"),0));
			} else {
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Deleted Meeting is Displayed in the Calendar Page",ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_WebElements"),1));
			}
			//click on calendar event close button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10272_1_id_CalEventCloseButton"), "xpath");
		}catch (Exception e) {
			e.printStackTrace();
		}
}
}