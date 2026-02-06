package com.dz.prism.productionbug;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_8360 {
	
	public static void verification_Of_Service_Account(Properties PRODUCTIONBUGPROP) throws Exception {
		SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_8360 Verification of Service Account Displayed in Add Contact Page");
		// Read excel file
		Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+ ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_v_test_case_path"));
		for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
			List<Map<String, String>> innerRows = testRows.getValue();
			for (Map<String, String> values : innerRows) {
			verification_Of_Service_Account_Displayed(values);	
				
			}
		}
	}

	public static void verification_Of_Service_Account_Displayed(Map<String, String> values) {
		try {
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		//click on header icon
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_id_HeaderIcon"), "id");
		//click on Add contact button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_id_AddContactButton"), "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_id_AddContactPage"), "id");
		//get contact type value from excel
		String ContactType = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_v_Contact_type"));
		//Count of Contact Type List
		int contactTypeListCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_id_ContactTypeList"))).size();
		List<String> a=new ArrayList<String>();
		for (int i = 2; i <= contactTypeListCount; i++) {	
		String contactTypeList = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_id_ContactTypeList")+"["+i+"]", "value", "xpath");
		a.add(contactTypeList);
		}
		if (a.contains("Service")) {
			//select the contact type
			SeleniumUtils.selectOptGroupDropdownValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_id_ContactType"), ContactType, "id");
			//get suggestion name from excel
			String SuggestionName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_v_Suggestion_name"));
			//Set value to the Account search box
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_id_AccountNameSearchBox"), SuggestionName, "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_id_SearchList"), "xpath");
			String textfromField = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_id_SearchList"), "xpath");
			if(!textfromField.equalsIgnoreCase("No results found for '" + SuggestionName + "'")) {
			//To get the size of account displayed
			int accountListCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_id_SearchedAccountList"))).size();
			//get Account name from excel
			String AccountName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_v_Account_name"));
			List<String> l=new ArrayList<>();
			for (int j = 1; j <= accountListCount; j++) {
				String AccNameList = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_id_SearchedAccountList")+"["+j+"]", "xpath");
				l.add(AccNameList);
				if (AccountName.equalsIgnoreCase(AccNameList)) {
					//click on required account
					SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_id_SearchedAccountList")+ "[" + j +"]", "xpath");
					SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_id_SearchedAccountList")+"["+ j +"]", "xpath");
					//get the selected account name
					String attributefromField = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_id_AccountNameSearchBox"), "value", "id").replace(" ", "");
					if (AccountName.equalsIgnoreCase(attributefromField)) {
						
						SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(AccountName+"- Service Account Displayed and selected",ExtentColor.GREEN));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_WebElements"),0));
						break;
					} else {
						SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(AccountName+"- Service Account not Displayed and selected",ExtentColor.RED));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_WebElements"),1));
					}
					
				}
				}
			if(!l.contains(AccountName)) {
				SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("No Account matched",ExtentColor.TEAL));
			}
			}else {
				SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("No result found for this name",ExtentColor.TEAL));
			}
			
		} else {
			
			SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("Service Account Type Not Displayed",ExtentColor.TEAL));
		}
		
	//Add Contact page close button
	SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_id_AddContactPageCloseButton"), "id");
	//Close confirm button
	SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8360_1_id_ConfirmButton"), "id");
		}catch (Exception e) {
			e.printStackTrace();
		}
		}
}
