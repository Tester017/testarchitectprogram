package com.dz.prism.productionbug;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_8045_and_10076{
	static String currDate = 	SeleniumUtils.getCurrDate();
	public static void verify_Duplicate_Contact(Properties PRODUCTIONBUGPROP) throws Exception {
		SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_8045_and_10076 Event Request Duplicate contact verification");
		// Read excel file
		Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+ ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_v_test_case_path"));
		for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
			List<Map<String, String>> innerRows = testRows.getValue();
			for (Map<String, String> values : innerRows) {
				duplicate_Event_Request_Contact_Verification(values);
				
			}
		}
	}
	
	public static void duplicate_Event_Request_Contact_Verification(Map<String, String> values) throws Exception {
			try {
				SeleniumUtils.webDriver.navigate().refresh();
		//Click on Event manager tab
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_EventManagerModule"), "xpath");
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_CreateEventButton"), "id");
			//click on create event button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_CreateEventButton"), "id");
			//click on event type dropdown
				String EventType = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_v_EventType"));
				Thread.sleep(3000);
				SeleniumUtils.selectOptGroupDropdownValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_EventTypeSelection"),EventType, "xpath");
			//Set Event title
				SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_EventTitle"), "xpath");
				String EventTitle = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_v_EventTitle"));
				SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_EventTitle"), EventTitle+currDate, "xpath");
			//Select the date of the event
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_StartDateSelectIcon"), "xpath");
				String date = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_v_StartDate"));
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_StartDateSelect").replace("temp", ""+date+""), "xpath");
			//click on submit button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_SubmitButton"), "xpath");
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_MeetingEditButton"), "xpath");
			//click on meeting edit button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_MeetingEditButton"), "xpath");
			//select the city from from dropdown
				SeleniumUtils.selectOptGroupDropdownValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_CitySelection"), values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_v_City")), "xpath");
			//click on save button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_SaveButton"), "xpath");
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				//click generate button to generate meeting
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_GenerateButton"), "xpath");
			//Click on meeting maxmize button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_MaximizeButton"), "xpath");
				SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_OpenRequestSearchBox"), "xpath");
				String contactName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_v_ContactName"));
				Robot r2=new Robot();
				for (int i = 0; i < 12; i++) {
					r2.keyPress(KeyEvent.VK_DOWN);
					r2.keyRelease(KeyEvent.VK_DOWN);
				}
				//set value to open request box
				SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_OpenRequestSearchBox"), contactName, "xpath");
				Thread.sleep(2000);
				int size = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_EmailList"))).size();
				//System.out.println(size);
				List<String> l=new ArrayList<String>();
				for (int i = 1; i <= size; i=i+2) {
					String EmailList = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_and_10076_1_id_EmailList")+"["+i+"]", "xpath");
					//System.out.println("email list---->"+EmailList);
					//System.out.println(EmailList.length());
					if (EmailList.contains("@")) {
						//System.out.println(EmailList.substring(EmailList.lastIndexOf(")")+2));
						l.add(EmailList.substring(EmailList.lastIndexOf(")")+2));
					} else {
						//System.out.println("No email id");
						SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel(EmailList+"-This Contact doesn't have Email id",ExtentColor.TEAL));
					}
					
				}
				//System.out.println(l);
				Map<String,Integer>m=new LinkedHashMap<String, Integer>();
				for (String x : l) {
					if(m.containsKey(x)) {
						Integer v = m.get(x);
						m.put(x, v+1);
						//System.out.println("Duplicate--->"+x);
						SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Contact Email id-"+ x +" is displaying multiple times",ExtentColor.RED));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_WebElements"),1));				
					}else {
						m.put(x, 1);
						//System.out.println("first match--->"+x);
						SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Contact Email id-"+ x +" is not displaying multiple times",ExtentColor.GREEN));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8045_WebElements"),0));
					}
					
				}
				//System.out.println("Map "+m);
				SeleniumUtils.webDriver.navigate().refresh();
				}catch (Exception e) {
					e.printStackTrace();
				}		
	}
}

