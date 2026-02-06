package com.dz.prism.productionbug;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_8422 {
	public static void verify_Event_Creation(Properties PRODUCTIONBUGPROP) throws Exception {
		SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_8422 Event verification in CRS Report page");
		// Read excel file
		Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+ ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_v_test_case_path"));
		for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
			List<Map<String, String>> innerRows = testRows.getValue();
			for (Map<String, String> values : innerRows) {
				event_Verification_In_CRS_Report(values);
				
			}
		}
	}
	public static void event_Verification_In_CRS_Report(Map<String, String> values) {
		try {
		SeleniumUtils.webDriver.navigate().refresh();
		String currDate = SeleniumUtils.getCurrDate();
		//System.out.println(currDate);
		String[] split = currDate.split("_");
		String[] split2 = split[0].split("-");
		String Date=split2[1]+"/"+split2[2]+"/"+split2[0];
		//System.out.println(Date);
		//Click on Event manager tab
		//Thread.sleep(3000);
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_DashboardPage"), "id");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_EventManagerModule"), "xpath");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_EventManagerPage"), "xpath");
		//click on create event button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_CreateEventButton"), "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_CreateEventPage"), "xpath");
		//click on event type dropdown
		String EventType = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_v_EventType"));
		//Thread.sleep(3000);
		SeleniumUtils.selectOptGroupDropdownValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_EventType"),EventType, "xpath");
		//Set Event title
		SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_EventTitle"), "xpath");
		String EventTitle = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_v_EventTitle"));
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_EventTitle"), EventTitle, "xpath");
		//Select the date of the event
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_StartDate"), "xpath");
		//String date = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_v_StartDate"));
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_StartDateSelect").replace("temp", ""+split2[2].replace("0", "")+""), "xpath");
		//click on submit button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_SubmitButton"), "xpath");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_MeetingEditButton"), "xpath");
		//click on meeting edit button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_MeetingEditButton"), "xpath");
		//select the city from from dropdown
		SeleniumUtils.selectOptGroupDropdownValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_CitySelection"), values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_v_City")), "xpath");
		//click on save button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_SaveButton"), "xpath");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		//select the meeting sub type from dropdown
		SeleniumUtils.selectOptGroupDropdownValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_MeetingSubType"), values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_v_MeetingType")), "xpath");
		//click generate button to generate meeting
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_GenerateButton"), "xpath");
		//Click on meeting maxmize button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_MaximizeButton"), "xpath");
		SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_OpenRequestSearchBox"), "xpath");
		String contactName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_v_ContactName"));
		//set value to open request box
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_OpenRequestSearchBox"), contactName, "xpath");
		String contactMail = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_v_ContactEmailId"));
		//click on the required contact
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_ContactList").replace("temp", ""+contactMail+""), "xpath");
		//click on request edit button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_RequestEditButton"), "xpath");
		//click on confirm button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_ConfirmedButton"), "xpath");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_ReportModule"), "xpath");
		//click on report module
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_ReportModule"), "xpath");
		SeleniumUtils.webDriver.navigate().refresh();
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		//get the report type
		String ReportSelect = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_v_ReportSelect"));
		//click on required report 
		SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_ReportSelect"), ReportSelect, "visibiletext", "id");
		//click on date filter
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_DateFilter"), "xpath");
		//click on MTD select
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_MTDSelect"), "xpath");
		//event suggestion Event type name
		String EventSugg = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_v_EventSuggType"));
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_CategorySearch"), EventSugg, "xpath");
		//click on required name select
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_NameSelect").replace("temp", "" + EventType + ""), "xpath");
		//company suggestion search name
		String CompanySuggName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_v_CompanySuggName"));
		//company name
		String CompanyName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_v_CompanyName"));
		//set sugg value in field
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_CompanyName"), CompanySuggName, "xpath");
		//click on required company name
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_NameSelect").replace("temp", "" + CompanyName + ""), "xpath");
		//click on submit button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_SubmitButton1"), "id");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_CustomReports"), "xpath");
		Thread.sleep(2000);
		//to get the table datas
		String Tabledata = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_TableDatas"), "xpath");
		
		if (!Tabledata.equalsIgnoreCase("No data available in table")) {
			int size = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_TableDateList"))).size();
			for (int i = 1; i <=size; i++) {
				//Date in table
				String TableDate = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_TableDate").replace("temp", "" + i + ""), "xpath");
				if (TableDate.equals(Date)) {
					//Event name in table
					String TableEventName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_TableEventName").replace("temp", "" + i + ""), "xpath");
					//Company name in table
					String TableCompanyName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_1_id_TableCompanyName").replace("temp", "" + i + ""), "xpath");
					if (EventTitle.equalsIgnoreCase(TableEventName) && CompanyName.equalsIgnoreCase(TableCompanyName)) {
						//System.out.println("pass");
						SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(EventTitle+" event for "+CompanyName+" has been created and displaying correctly",ExtentColor.GREEN));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_WebElements"),0));
					} else {
						//System.out.println("fail");
						SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Event has not been created and not displaying correctly",ExtentColor.RED));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_WebElements"),1));
					}
				} else {
					//System.out.println("No event created on particular date");
					SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("No Event has not been created on that particular date",ExtentColor.RED));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8422_WebElements"),1));
				}
			}
		} else {
			//System.out.println("No data available");
			SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("No data available",ExtentColor.TEAL));
			
		}
		
}catch (Exception e) {
	e.printStackTrace();
}
}
}