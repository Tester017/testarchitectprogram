package com.dz.prism.productionbug;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class SID_79 {
	static String currDate1 = SeleniumUtils.getCurrDate();
	public static void contact_History(Properties PRODUCTIONBUGPROP) throws Exception {
		SeleniumUtils.parentTest= SeleniumUtils.testCase.createNode("SID_79 Contact History Verification");
		//SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("History Verification for contact creation");
		// Read excel file
		Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+ ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_v_test_case_path"));
		for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
			List<Map<String, String>> innerRows = testRows.getValue();
			for (Map<String, String> values : innerRows) {
				contact_History_Verification(values);
				
			}
		}
	}
	
	public static void contact_History_Verification(Map<String, String> values) {
		try {
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_AddIcon"), "id");
			//click on add icon
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_AddIcon"), "id");
			//click on add contact button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_AddContactButton"), "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_AddContactPage"), "id");
			//To get the first name from excel
			String firstName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_v_contact_first_name"));
			//set the first name to the field
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_FirstName"), firstName, "id");
			//To get the last name from excel
			String lastName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_v_contact_last_name"));
			//set the last name to the field
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_LastName"), lastName, "id");
			//To get the contact type
			String contactType = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_v_contact_type"));
			//select the required contact type
			SeleniumUtils.selectOptGroupDropdownValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ContactType"), contactType, "id");
			//To get the suggestion name 
			String suggName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_v_account_sugg_name"));
			//set the suggestion name to the field
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_AccountSuggName"), suggName, "id");
			//get the account name
			String accountName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_v_account_name"));
			//click on required account name from list
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_AccountList"), "xpath");
			Thread.sleep(2000);
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_AccountNameSelect").replace("temp", "" + accountName  + ""), "xpath");
			//get the job function type
			String jobFuction = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_v_job_function"));
			//select the required job function type
			SeleniumUtils.selectOptGroupDropdownValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_JobFunction"), jobFuction, "id");
			//get the primary email from excel
			String primaryEmail = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_v_primary_email"));
			//set the email value to the field
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_PrimaryMail"), primaryEmail+currDate1+"@gmail.com", "id");
			//click on save button
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_SaveButton"), "id");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_SaveButton"), "id");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ContactTearSheet"), "xpath");
			//To get the toast message
			String toastMessage = SeleniumUtils.getToastMessage();
			//System.out.println("saved msg "+toastMessage);
			//click on Burger button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_BurgerButton"), "xpath");
			//click on contact history
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ContactHistory"), "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ContactHistoryPage"), "xpath");
			//To get the status of contact created
			String StatusValue = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ContactHistoryStatus")+"[1]", "xpath");
			//To check whether contact history created or not
			String AllTabTag = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_AllTag"), "innerHTML", "xpath");
			
				//To get the name in contact history
				String ContactHistName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ContactName"), "xpath");
				String contactName=firstName+" "+lastName;
				
			if (ContactHistName.equalsIgnoreCase(contactName)) {
					//System.out.println("Name matched");
					SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Contact history(All tabs) - Username verifies successfully and matched in Contact history page"+ContactHistName,ExtentColor.GREEN));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_WebElements"),0));
			} else {
					//System.out.println("Name not matched");
					SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Contact history(All tabs) - Username is not matched in Contact history page"+ContactHistName,ExtentColor.RED));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_WebElements"),1));
				}
			if (StatusValue.equalsIgnoreCase("CREATED") && AllTabTag.contains("div")) {
				//System.out.println("status created");
				SeleniumUtils.parentTest.log(Status.PASS, MarkupHelper.createLabel("Contact history(All tabs) -Status of Contact verifed successfully for"+"  "+ContactHistName+" "+"Contact"+" " +"in Contact history page "+" "+StatusValue, ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_WebElements"),0));
			}else {
					//System.out.println("Not created");
					SeleniumUtils.parentTest.log(Status.FAIL, MarkupHelper.createLabel("Log Interaction history(All tabs) -Status of Contact failed in Contact history page"+" "+StatusValue, ExtentColor.RED));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_WebElements"),1));
				}
				//To get the current Date
				String[] currDate = SeleniumUtils.getCurrDate().split("_");
				//System.out.println(currDate[0]);
				String[] split = currDate[0].split("-");
				String currentDate=split[1]+"/"+split[2]+"/"+split[0];
				//System.out.println(currentDate);
				String InteractionDate = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_InteractionDate"), "xpath");
				if (currentDate.contains(InteractionDate)) {
						//System.out.println("Date is matched");
						SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Contact history(All tabs) - Date verifies successfully in contact history page"+" "+InteractionDate,ExtentColor.GREEN));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_WebElements"),0));
				} else {
						//System.out.println("Date is not matched");
						SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Contact history(All tabs) - Date not matched in contact history page"+" "+InteractionDate,ExtentColor.RED));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_WebElements"),1));
					}
			//click on contact history close button
			Thread.sleep(2000);
			SeleniumUtils.closeToastMessage();
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ContactHistoryCloseButton"), "xpath");
			edited_Contact_History_Verification(values);
			 
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void edited_Contact_History_Verification(Map<String,String>values) throws Exception {
		try {
		//click on burger button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_BurgerButton"), "xpath");
		//click on contact edit button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ContactEditButton"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_AddContactPage"), "id");
		Thread.sleep(2000);
		//click on research access tab
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ResearchAccessTab"), "id");
		//click on primary mail check box
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_PrimaryMailSelectCheckBox"), "id");
		//click on update button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_UpdateButton"), "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ContactTearSheet"), "xpath");
		//get toast message
		String toastMessage = SeleniumUtils.getToastMessage();
		//System.out.println("update msg "+toastMessage);
		Thread.sleep(2000);
		//click on burger button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_BurgerButton"), "xpath");
		//click on contact history
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ContactHistory"), "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ContactHistoryPage"), "xpath");
		//get research Email
		String UpdatedResearchEmail = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ContactHistoryStatus")+"[2]", "xpath");
		String primaryEmail = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_v_primary_email"));
		if ((primaryEmail+currDate1+"@gmail.com").equalsIgnoreCase(UpdatedResearchEmail)) {
			//System.out.println("Contact updated and displayed in contact history");
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Added research email in contact and verified successfully in contact history page",ExtentColor.GREEN));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_WebElements"),0));
		} else {
			//System.out.println("Contact not updated in contact history");
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Added research email in contact is failed in contact history page",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_WebElements"),1));
		}
		//Thread.sleep(3000);
		SeleniumUtils.closeToastMessage();
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ContactHistoryCloseButton"), "xpath");
		//click on contact history close button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ContactHistoryCloseButton"), "xpath");
		//click on contact close button
		//Thread.sleep(3500);
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ContactCloseButton"), "xpath");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_79_1_id_ContactCloseButton"), "xpath");
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
}
