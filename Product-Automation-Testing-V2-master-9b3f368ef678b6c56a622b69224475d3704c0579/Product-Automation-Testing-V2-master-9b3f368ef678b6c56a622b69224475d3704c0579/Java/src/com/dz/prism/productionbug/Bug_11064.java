package com.dz.prism.productionbug;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_11064 {
public static void ReadExcel() throws Exception {
    	
        Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(
                SeleniumUtils.UserDirVar + ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_v_test_case_path"));
        SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_11064 Account Coverage Buisness line and Product verification");

        for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
            List<Map<String, String>> innerRows = testRows.getValue();
            for (Map<String, String> values : innerRows) {
                buisness_And_Product_Verification(values);
            }
        }
    }			//Production Bug_11064_TC_960 -  Production Bug_11064_TC_980
public static void buisness_And_Product_Verification(Map<String, String> values) throws Exception {
	//click on dashboard module
	 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_DashBoardModule"), "xpath");
	 SeleniumUtils.waitUntilElementHide("loading_screen", "id");
	 SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_TopClientsTable"), "xpath");
	 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_TableHeaders"), "xpath");
	 List<WebElement> AccHeadersList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_TableHeaders")));
	 WebElement AccNameHeadPos = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_AccountNamePosition")));
	 int AccNameIndex = AccHeadersList.indexOf(AccNameHeadPos);
	 //click on particular account
	 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_AccountName").replace("temp", ""+(AccNameIndex+1) +""), "xpath");
	 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_AccountName").replace("temp", ""+(AccNameIndex+1) +""), "xpath");
	 SeleniumUtils.waitUntilElementHide("loading_screen", "id");
	 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_AccountTearSheetHead"), "xpath");
	 //click on burger button
	 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_BurgerButton"), "xpath");
	 //click on edit account button
	 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_EditAccountButton"), "xpath");
	 SeleniumUtils.waitUntilElementHide("loading_screen", "id");
	 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_AccCoverageTab"), "xpath");
	 //click on account coverage tab
	 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_AccCoverageTab"), "xpath");
	 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_EmployeeName"), "id");
	 //get the employee name from excel
	 String employeeName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_v_Employee_Name"));
	 //Set the value to the employee name field
	 SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_EmployeeName"), employeeName, "id");
	 //get the employee email from excel
	 String employeeEmail = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_v_Employee_Email"));
	 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_EmployeeNameSelect").replace("temp", "" + employeeEmail + ""), "xpath");
	 SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_EmployeeNameSelect").replace("temp", "" + employeeEmail + ""), "xpath");
	 //click on required email
	 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_EmployeeNameSelect").replace("temp", "" + employeeEmail + ""), "xpath");
	 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_BuisnessLine"), "id");
	 String buisnessLine = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_BuisnessLine"), "value", "id");
	 if (!buisnessLine.isEmpty()) {
		SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Buisness line is auto populated for the given Employee",ExtentColor.GREEN));
		SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_WebElements"),0));
		//get the product name from excel
		 String productName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_v_Product"));
		 //select the product dropdown value from drop down
		 SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_Product"), productName, "value", "id");
		 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_Product"), "id");
		 String product = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_Product"), "value", "id");
		 if (!product.isEmpty()) {
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Product value is populated for the given Employee",ExtentColor.GREEN));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_WebElements"),0));
		} else {
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Product value is populated for the given Employee",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_WebElements"),1));
		}
	} else {
		SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Buisness line is not populated Automatically for the given Employee",ExtentColor.RED));
		SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_WebElements"),1));
	}
	//click on edit close button
	 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_EditButtonClose"), "xpath");
	 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_EditButtonClose"), "xpath");
	 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_EditCloseCnfmButton"), "id");
	 //click on edit close confirm button
	 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_EditCloseCnfmButton"), "id");
	 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_AccountCloseButton"), "xpath");
	 //click on account tear sheet close button
	 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_11064_1_id_AccountCloseButton"), "xpath");
}
}
