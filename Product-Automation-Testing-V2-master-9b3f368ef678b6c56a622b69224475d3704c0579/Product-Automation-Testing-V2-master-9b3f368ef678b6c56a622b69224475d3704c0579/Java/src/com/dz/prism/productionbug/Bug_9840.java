package com.dz.prism.productionbug;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_9840 {
public static void ReadExcel() throws IOException {
    	
        Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(
                SeleniumUtils.UserDirVar + ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_v_test_case_path"));
        SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_9840 German Phone Number Verification");

        for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
            List<Map<String, String>> innerRows = testRows.getValue();
            for (Map<String, String> values : innerRows) {
            	phone_Number_Verification(values);
            }
        }
    }
	public static void phone_Number_Verification(Map<String, String> values) {
		try {
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		//to get the current date
		String currDate = SeleniumUtils.getCurrDate();
		//To get the values from excel sheet
		String firstName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_v_First_Name"));
		String lastName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_v_Last_Name"));
		String contactType = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_v_Contact_Type"));
		String accSuggName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_v_Account_Sugg_Search"));
		String accName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_v_Account_Name"));
		String jobFunction = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_v_Job_Function"));
		String primaryMail = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_v_Primary_Mail"));
		String primaryCountryName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_v_Primary_No_Country_Code"));
		String primaryNumber = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_v_Primary_No"));
		String secondaryCountryName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_v_Secondary_No_Country_Code"));
		String secondaryNumber = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_v_Secondary_No"));
		String personalCountryName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_v_Personal_No_Country_code"));
		String personalNumber = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_v_Personal_No"));
		String assistantCountryName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_v_Assistant_No_Country_Code"));
		String assistantNumber = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_v_Assistant_No"));
		//Click on Add Icon
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_AddIcon"), "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_AddContactButton"), "id");
		//click on Add contact button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_AddContactButton"), "id");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		//Set the first name in the field
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_FirstName"), firstName+currDate, "id");
		//Set the last name in the field
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_LastName"), lastName+currDate, "id");
		//Select the contact type
		SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_ContactType"), contactType, "value", "id");
		//Set the value to account search
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_AccountNameSearch"), accSuggName, "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_AccountNameSelect").replace("temp", "" + accName + ""), "xpath");
		SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_AccountNameSelect").replace("temp", "" + accName + ""), "xpath");
		//Click on required Account
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_AccountNameSelect").replace("temp", "" + accName + ""), "xpath");
		//Select the required job function
		SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_JobFunction"), jobFunction, "value", "id");
		//Set the primary email to the field
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_PrimaryEmail"), primaryMail+currDate+"@gmail.com", "id");
		//click on primary phone code
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_PrimaryPhnCode"), "xpath");
		SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_PrimaryPhnCodeSel").replace("temp", "" + primaryCountryName + ""), "xpath");
		//to get the code number of the country
		String PrimaryCodeNo = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_PrimaryPhnCodeNum").replace("temp", "" + primaryCountryName + ""), "xpath");
		//click on particular country code
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_PrimaryPhnCodeSel").replace("temp", "" + primaryCountryName + ""), "xpath");
		//Set the number to the field
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_PrimaryPhnNumber"), primaryNumber.replace("~", ""), "id");
		//click on secondary phone code
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_SecondaryPhnCode"), "xpath");
		SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_SecondaryPhnCodeSel").replace("temp", "" + secondaryCountryName + ""), "xpath");
		//to get the code number of the country
		String SecondaryCodeNo = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_SecondaryPhnCodeNum").replace("temp", "" + secondaryCountryName + ""), "xpath");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_SecondaryPhnCodeSel").replace("temp", "" + secondaryCountryName + ""), "xpath");
		//Set the number to the field
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_SecondaryPhnNumber"), secondaryNumber.replace("~", ""), "id");
		//click on Personal Phone code
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_PersonalPhnCode"), "xpath");
		SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_PersonalPhnCodeSel").replace("temp", "" + personalCountryName + ""), "xpath");
		//to get the code number of the country
		String PersonalCodeNo = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_PersonalPhnCodeNum").replace("temp", "" + personalCountryName + ""), "xpath");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_PersonalPhnCodeSel").replace("temp", "" + personalCountryName + ""), "xpath");
		//Set the number to the field
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_PersonalPhnNumber"), personalNumber.replace("~", ""), "id");
		SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_AssistantPhnCode"), "xpath");
		//click on Assistant phone code
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_AssistantPhnCode"), "xpath");
		SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_AssistantPhnCodeSel").replace("temp", "" + assistantCountryName + ""), "xpath");
		//to get the code number of the country
		String AssistantPhnCode = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_AssistantPhnCodeNum").replace("temp", "" + assistantCountryName + ""), "xpath");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_AssistantPhnCodeSel").replace("temp", "" + assistantCountryName + ""), "xpath");
		//Set the number to the field
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_AssistantPhnNumber"), assistantNumber.replace("~", ""), "id");
		//Click on Save button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_SaveButton"), "id");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_ContactPanelHead"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_WorkNum"), "xpath");
		//To get the Phone numbers present in Contact tear sheet
		String WorkNo = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_WorkNum"), "xpath");
		String MobileNo = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_MobileNum"), "xpath");
		String HomeNo = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_HomeNum"), "xpath");
		String AssistNo = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_AssistantNum"), "xpath");
		if (WorkNo.equals((PrimaryCodeNo)+" "+primaryNumber.replace("~", " "))) {
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Primary Phone Number for German country is filled and displaying correctly on Contact tear sheet",ExtentColor.GREEN));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_WebElements"),0));
		} else {
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Primary Phone Number for German country is filled and not displaying correctly on Contact tear sheet",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_WebElements"),1));
		}
		if (MobileNo.equals((SecondaryCodeNo)+" "+secondaryNumber.replace("~", " "))) {
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Secondary Phone Number for German country is filled and displaying correctly on Contact tear sheet",ExtentColor.GREEN));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_WebElements"),0));
		} else {
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Secondary Phone Number for German country is filled and not displaying correctly on Contact tear sheet",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_WebElements"),1));
		}
		if (HomeNo.equals((PersonalCodeNo)+" "+personalNumber.replace("~", " "))) {
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Personal Phone Number for German country is filled and displaying correctly on Contact tear sheet",ExtentColor.GREEN));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_WebElements"),0));
		} else {
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Personal Phone Number for German country is filled and not displaying correctly on Contact tear sheet",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_WebElements"),1));
		}
		if (AssistNo.equals((AssistantPhnCode)+" "+assistantNumber.replace("~", " "))) {
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Assistant Phone Number for German country is filled and displaying correctly on Contact tear sheet",ExtentColor.GREEN));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_WebElements"),0));
		} else {
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Assistant Phone Number for German country is filled and not displaying correctly on Contact tear sheet",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_WebElements"),1));
		}
		//Click on Contact Tear sheet close button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9840_1_id_ContactCloseButton"), "xpath");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
