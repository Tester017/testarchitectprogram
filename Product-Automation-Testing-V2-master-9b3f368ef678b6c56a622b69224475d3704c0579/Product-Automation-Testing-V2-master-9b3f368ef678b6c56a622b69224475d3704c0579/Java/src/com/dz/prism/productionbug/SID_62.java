package com.dz.prism.productionbug;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Map.Entry;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class SID_62 {
	
	public static void interaction_History(Properties PRODUCTIONBUGPROP) throws Exception {
		SeleniumUtils.parentTest= SeleniumUtils.testCase.createNode("SID_62 Interaction History Verification");
		
		// Read excel file
		Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+ ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_v_test_case_path"));
		for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
			List<Map<String, String>> innerRows = testRows.getValue();
			for (Map<String, String> values : innerRows) {
				interaction_History_Verification(values);
				
			}
		}
	}
	
	
	public static void interaction_History_Verification(Map<String, String> values) {
		try {
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		//Click on Add Icon
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_AddIcon"), "id");
		//click on Add interaction Button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_AddInteractionButton"), "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_AddInteractionPage"), "id");
		//get the contact search value from excel
		String contactSearch = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_v_contact_search"));
		//set the suggestion search
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_ContactSearch"), contactSearch, "xpath");
		//get the contact name 
		String contactName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_v_contact_name"));
		//click on the required contact
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_ContactSelect").replace("temp", "" + contactName + ""), "xpath");
		//get the activity type
		String activityType = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_v_activity_type"));
		//select the activity type dropdown value
		SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_ActivityType"), activityType, "value", "id");
		//get the duration time
		String duration = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_v_duration"));
		//set the duration value to the field
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_Duration"), duration, "id");
		//get the event subject name
		String eventSubject = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_v_event_subject"));
		//set the event subject to the value
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_EventSubject"), eventSubject, "id");
		//get the comments value
		String comments = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_v_comments"));
		//set the comments to the field
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_CommentsInput"), comments, "id");
		//ticker search sugg name
		String tickerSuggName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_v_ticker_suggestion_name"));
		//set suggestion search value to the field
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_TagsSearchBox"), tickerSuggName, "xpath");
		//get the ticker name
		String tickerName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_v_ticker_name"));
		//select the required ticker 
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_TickerSelect").replace("temp", "" + tickerName + ""), "xpath");
		//internal attendees search
		String internalAtt = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_v_internal_search"));
		//set the search value
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_InternalAttendeesSearch"), internalAtt, "xpath");
		//get the internal attendees name
		String attendeesName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_v_internal_attendees"));
		//click on required internal attendees
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_InternalAttendeesSelect").replace("temp", "" + attendeesName + ""), "xpath");
		//click on save button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_SaveButton"), "id");
		Actions a=new Actions(SeleniumUtils.webDriver);
		//SeleniumUtils.tabSelection("Activity","Activity");
		//click on activity module
		WebElement Activity = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_ActivityModule")));
		a.moveToElement(Activity).build().perform();
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_ActivitySubModule"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_ActivityPage"), "xpath");
		//get the event name from the activity table	
		SeleniumUtils.webDriver.navigate().refresh();
		Thread.sleep(4000);
		int Eventsize = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_TableEventSubjectNameList"))).size();
		List<String> Event=new ArrayList<String>();
		for (int i = 1; i <=Eventsize ; i++) {
			String TableEventName = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_TableEventSubjectNameList")+"[" + i + "]", "title", "xpath");	
			Event.add(TableEventName);
		}
			if (Event.contains(eventSubject)) {
				int indexOf = Event.indexOf(eventSubject);
				WebElement actionButton = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_ActionThreeDotButton")+"["+(indexOf+1)+"]"));
				
				a.moveToElement(actionButton).build().perform();
				//click on eye action button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_ActionEyeButton")+"["+(indexOf+1)+"]", "xpath");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_InteractionHistoryPage"), "xpath");
				//get the interaction event name
				String InteractionEventName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_InteractinEventName"), "xpath");
				if (eventSubject.equalsIgnoreCase(InteractionEventName)) {
					//System.out.println("Name matched");
					SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Log Interaction history(All tabs) - Username verifies successfully and matched in interaction history page"+InteractionEventName,ExtentColor.GREEN));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_WebElements"),0));
				} else {
					//System.out.println("Name not matched");
					SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Log Interaction history(All tabs) - Username not matched in interaction history page"+InteractionEventName,ExtentColor.RED));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_WebElements"),1));
				}
				//to get the interaction history status
				String StatusValue = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_InteractionStatus"), "xpath");
				if (StatusValue.equalsIgnoreCase("CREATED")) {
					//System.out.println("Created");
					SeleniumUtils.parentTest.log(Status.PASS, MarkupHelper.createLabel("Log Interaction history(All tabs) -Status of Interaction verifed successfully for"+"  "+InteractionEventName+" "+"interaction"+" " +"in interaction history page "+" "+StatusValue, ExtentColor.GREEN));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_WebElements"),0));
				} else {
					//System.out.println("Not found");
					SeleniumUtils.parentTest.log(Status.FAIL, MarkupHelper.createLabel("Log Interaction history(All tabs) -Status of Interaction failed in interaction history page"+" "+StatusValue, ExtentColor.RED));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_WebElements"),1));
				}
			//To get current date
					String[] currDate = SeleniumUtils.getCurrDate().split("_");
					//System.out.println(currDate[0]);
					String[] split = currDate[0].split("-");
					String currentDate=split[1]+"/"+split[2]+"/"+split[0];
					//System.out.println(currentDate);
			//to get the interaction date	
			String InteractionDate = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_InteractionDate"), "xpath");
				if (currentDate.contains(InteractionDate)) {
					//System.out.println("Date matched");
					SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Log Interaction history(All tabs) - Date verifies successfully in interaction history page"+" "+InteractionDate,ExtentColor.GREEN));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_WebElements"),0));
				} else {
					//System.out.println("Date not matched");
					SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Log Interaction history(All tabs) - Date not matched in interaction history page"+" "+InteractionDate,ExtentColor.RED));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_WebElements"),1));
				}
				//click on interaction close button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_InteractionCloseButton"), "xpath");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_ActivityPage"), "xpath");
				//to edit the ticker value
				edit_Interaction_verification(values);
			}else {
				//System.out.println("Interaction not found");
				SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("Interaction Not Found",ExtentColor.TEAL));
			}
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public static void edit_Interaction_verification(Map<String, String> values) {
		try {
			//To get the event subject value
			String eventSubjectName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_v_event_subject"));
			//to get the event name from activity table
			int Eventsize = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_TableEventSubjectNameList"))).size();
			List<String> Event1=new ArrayList<String>();
			for (int i = 1; i <=Eventsize ; i++) {
				String TableEventName1 = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_TableEventSubjectNameList")+"[" + i + "]", "title", "xpath");	
				Event1.add(TableEventName1);
			}
				if (Event1.contains(eventSubjectName)) {
					int indexOf = Event1.indexOf(eventSubjectName);
			WebElement actionButton = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_ActionThreeDotButton")+"["+(indexOf+1)+"]"));
			Actions a=new Actions(SeleniumUtils.webDriver);
			a.moveToElement(actionButton).build().perform();
			//click on action edit button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_ActionEditButton")+"["+(indexOf+1)+"]", "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_EditInteractionPage"), "id");
			//click on close button to Remove the tag
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_TagsRemoveButton"), "xpath");
			//click on save button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_SaveButton"), "id");
			WebElement actionButton1 = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_ActionThreeDotButton")+"["+(indexOf+1)+"]"));
			a.moveToElement(actionButton1).build().perform();
			//click on action eye button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_ActionEyeButton")+"["+(indexOf+1)+"]", "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_InteractionHistoryPage"), "xpath");
			String changedticker = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_InteractionStatus")+"[2]", "xpath");
			if (changedticker.equalsIgnoreCase("empty")) {
				//System.out.println("ticker name changed to empty");
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Log Interaction History - Removed Ticker from Interaction is verified successfully in interaction history page",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_WebElements"),0));
			} else {
				//System.out.println("ticker name not changed");
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Log Interaction History - Removed Ticker from Interaction is failed in interaction history page",ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_WebElements"),1));
			}
			//click on interaction close button
			SeleniumUtils.closeToastMessage();
			Thread.sleep(3000);
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_62_1_id_InteractionCloseButton"), "xpath");
			//SeleniumUtils.webDriver.navigate().refresh();
}
			
		}catch(Exception e){
			e.printStackTrace();
		}
}
	}
