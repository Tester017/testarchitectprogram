package com.dz.prism.productionbug;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_10554 {
	public static void Read_Excel(Properties PRODUCTIONBUGPROP) throws Exception {
		SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_10554 Email Subject Name Search for Single quotes Verification");
		// Read excel file
		Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+ ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_1_v_test_case_path"));
		for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
			List<Map<String, String>> innerRows = testRows.getValue();
			for (Map<String, String> values : innerRows) {
				email_Subject_Name_Search_Verification(values);	
			}
		}
	}
	
	public static void email_Subject_Name_Search_Verification(Map<String, String> values) {
		try {
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			//110 url
			/*SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_1_id_MyListV3Module"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_1_id_MyListV3Module"), "xpath");*/
			//28 url
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_1_id_MyListModule"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_1_id_MyListModule"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_1_id_SearchTab"), "xpath");
			//Click on search tab
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_1_id_SearchTab"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_1_id_CategorySelect"), "id");
			//To get the Category values from excel
			String Category = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_1_v_category"));
			//To select the values from dropdown
			SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_1_id_CategorySelect"), Category, "visibiletext", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_1_id_InputSearch"), "xpath");
			//To get the input value from dropdown
			String Input = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_1_v_InputSearch"));
			//Set the input value to the input search
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_1_id_InputSearch"), Input, "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_1_id_SearchList"), "xpath");
			//Search result for the given input
			String InputResult = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_1_id_SearchList"), "title", "xpath");
			if (!InputResult.equals("Record not found")) {
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Email Subject Name displayed for the given search input",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_WebElements"),0));
			} else {
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Email Subject Name is not displayed for the given search input",ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10554_WebElements"),1));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
