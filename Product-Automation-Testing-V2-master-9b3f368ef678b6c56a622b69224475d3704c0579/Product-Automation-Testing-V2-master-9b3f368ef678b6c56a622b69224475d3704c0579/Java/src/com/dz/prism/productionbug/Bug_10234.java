package com.dz.prism.productionbug;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_10234 {
	static List<String> l;
	static String AccNameSearch;
public static void ReadExcel() throws Exception {
    	
        Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(
                SeleniumUtils.UserDirVar + ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_v_Test_Case_Path"));
        for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
            List<Map<String, String>> innerRows = testRows.getValue();
            for (Map<String, String> values : innerRows) {
            	verification_Of_Inactive_Accounts_In_Contact_Creation(values);
            	
            }
        }
    }
	public static void verification_Of_Inactive_Accounts_In_Contact_Creation(Map<String, String> values) {
		try {
			SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_10234 InActive Account verification In Contact Creation");
			//To get Account name suggestion search from excel
			AccNameSearch = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_v_AccountNameSearch"));
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_TopSearch"), "id");
			//Set the value to global search
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_TopSearch"), AccNameSearch, "id");
			//Filter count
			int searchListFilterCount = SeleniumUtils.getCountOfDropdownList(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_FilterCountList"), "xpath");
	        //deselecting selected filter
			for(int i=1;i<=searchListFilterCount ;i++){
	            boolean isSelected = SeleniumUtils.checkBoxIsSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_FilterSelectedCheck").replace("temp","" + i + ""), "xpath");
	            if(isSelected==true){
	            	SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_FilterDeselect").replace("temp","" + i + ""), "xpath");
	                SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_FilterDeselect").replace("temp","" + i + ""),"xpath");
	            }
	        }
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_CoveredAccountCheckBox"), "xpath");
			//selecting Covered account filter
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_CoveredAccountCheckBox"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_InActiveAccountSearchList"), "xpath");
			Thread.sleep(1500);
			//InActive Account list displayed count
			int InActiveAccListCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_InActiveAccountSearchList"))).size();
			 l= new ArrayList<String>();
			for (int i = 1; i <=InActiveAccListCount; i++) {
				String InActiveAccountNames = SeleniumUtils.getTextfromField("("+ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_InActiveAccountSearchList")+")["+ i + "]", "xpath");
				l.add(InActiveAccountNames);
			}
			if (!l.isEmpty()) {
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_AddIcon"), "id");
				//Click on add icon button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_AddIcon"), "id");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_AddContactButton"), "id");
				//Click on Add contact button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_AddContactButton"), "id");
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				//To get the string type from excel
				String ContactType = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_v_ContactType"));
				//Select Contact type from dropdown
				SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_ContactType"), ContactType, "value", "id");
				for (int i = 0; i < l.size(); i++) {
				//Set the Account name on Account search box
				SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_AccountNameSearch"), l.get(i), "id");
				Thread.sleep(1000);
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_AccountSearchList"), "xpath");
				String AccSearch = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_AccountSearchList"), "xpath");
				if (AccSearch.equals("No results found for '" + l.get(i) + "'")) {
					SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(l.get(i)+" - This InActive Account is not found in search list",ExtentColor.GREEN));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_WebElements"),0));
				} 
				else if (!AccSearch.equals("No results found for '" + l.get(i) + "'")) {
					int AccListCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_AccountSearchListDisplayed"))).size();
					List<String> AccNameList =new ArrayList<String>();
					for (int j = 1; j < AccListCount; j++) {
						String AccName = SeleniumUtils.getTextfromField("("+ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_AccountSearchListDisplayed")+")["+j+"]", "xpath");
						AccNameList.add(AccName);
					}
					if (!AccNameList.contains(l.get(i))) {
						SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(l.get(i)+" - This InActive Account is not found in search list",ExtentColor.GREEN));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_WebElements"),0));
					} else {
						SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(l.get(i)+" - This InActive Account is found in search list",ExtentColor.RED));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_WebElements"),1));
					}
				}
				SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_AccountNameSearch"), "id");
				}
				//Click on contact page close button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_AddContactPageCloseButton"), "id");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_CloseConfirmButton"), "id");
				//click on Contact page close button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10234_1_id_CloseConfirmButton"), "id");
			} else {
				SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("No InActive Account found in Global search",ExtentColor.TEAL));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
