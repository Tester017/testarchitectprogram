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

public class Bug_7611 {
	public static void Read_Excel(Properties PRODUCTIONBUGPROP) throws Exception {
		SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_7611 Account search in Contant move Verification");
		// Read excel file
		Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+ ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_v_test_case_path"));
		for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
			List<Map<String, String>> innerRows = testRows.getValue();
			for (Map<String, String> values : innerRows) {
				account_Search_In_Contact_move(values);	
			}
		}
	}	//Production Bug_7611_TC_907 - Production Bug_7611_TC_925
	public static void account_Search_In_Contact_move(Map<String, String> values) throws Exception {
		try {
		String contactName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_v_Contact_Name"));
		String contactEmail = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_v_Contact_Email"));
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_global_Search"), "id");
		//Set the value to the global search
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_global_Search"), contactName, "id");
		int searchListFilterCount = SeleniumUtils.getCountOfDropdownList(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_FilterCountList"), "xpath");
        //deselecting selected filter
		for(int i=1;i<=searchListFilterCount ;i++){
			Thread.sleep(500);
            boolean isSelected = SeleniumUtils.checkBoxIsSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_FilterSelectedCheck").replace("temp","" + i + ""), "xpath");
            if(isSelected==true){
            	Thread.sleep(500);
            	SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_FilterDeselect").replace("temp","" + i + ""), "xpath");
                SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_FilterDeselect").replace("temp","" + i + ""),"xpath");
            }
        }
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_ContactFilter"), "xpath");
		//click on contact check box
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_ContactFilter"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_SearchList"), "xpath");
		Thread.sleep(500);
		//get the search list
		String searchList = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_SearchList"), "xpath");
		if (!searchList.equals("No results found for '"+contactName+"'")) {
			int size = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_SearchListDisplayed"))).size();
			List<String>l=new ArrayList<String>();
			for (int i = 1; i <= size; i++) {
				String emailId = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_SearchListDisplayed")+"["+ i + "]", "xpath");
				l.add(emailId);
			}
			if(l.contains(contactEmail)) {
				SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_ContactSelect").replace("temp", "" + contactEmail + ""), "xpath");
				//click on required contact
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_ContactSelect").replace("temp", "" + contactEmail + ""), "xpath");
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_BurgerButton"), "xpath");
				//click on burger button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_BurgerButton"), "xpath");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_MoveContactButton"), "xpath");
				//click on move contact button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_MoveContactButton"), "xpath");
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				String accountName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_v_Account_Name"));
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_MoveToAccInputSearch"), "id");
				//set the value in account search
				SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_MoveToAccInputSearch"), accountName, "id");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_SearchResult"), "xpath");
				String searchedAccountList = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_SearchResult"), "xpath");
				if (!searchedAccountList.equals("No results found for '"+accountName+"'")) {
					int size2 = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_SearchedAccountList"))).size();
					List<String>l1=new ArrayList<String>();
					for (int i = 1; i <= size2; i++) {
						String accList = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_SearchedAccountList")+"["+ i + "]", "xpath");
						l1.add(accList.trim());
					}
					if (l1.contains(accountName)) {
						SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Account Name displayed for the given search input",ExtentColor.GREEN));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_WebElements"),0));
					} else {
						SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Account Name not displayed for the given search input",ExtentColor.RED));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_WebElements"),1));
					}
				} else if(searchedAccountList.equals("No results found for '"+accountName+"'")){
					SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("No Account Name displayed for the given search input",ExtentColor.TEAL));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_WebElements"),1));
				}
				//click on move contact page close button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_MoveContactPageClose"), "id");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_ContactTearSheetCloseButton"), "xpath");
				//click on contact tearsheet close button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_1_id_ContactTearSheetCloseButton"), "xpath");
			}else {
				SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("No contact with the given mail is displayed for the given search input",ExtentColor.TEAL));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_WebElements"),1));
			}
		} else if(searchList.equals("No results found for '"+contactName+"'")){
			SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("No result for the given contact is displayed for the given search input",ExtentColor.TEAL));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7611_WebElements"),1));
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
}
