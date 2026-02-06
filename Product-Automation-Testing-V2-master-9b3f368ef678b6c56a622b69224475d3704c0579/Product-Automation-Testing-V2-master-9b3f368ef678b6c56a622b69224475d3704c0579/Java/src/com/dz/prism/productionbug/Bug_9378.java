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

public class Bug_9378 {
	static String NextDay;
	public static void ReadExcel() throws IOException {
    	
        Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(
                SeleniumUtils.UserDirVar + ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_v_test_case_path"));
        SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_9378 Account Coverage Percentage value verification");

        for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
            List<Map<String, String>> innerRows = testRows.getValue();
            for (Map<String, String> values : innerRows) {
                account_Coverage_Status_Verification(values);
            }
        }
    }
 public static void account_Coverage_Status_Verification(Map<String, String> values) {
	 try {	//Production Bug_9378_TC_866  -  Production Bug_9378_TC_893
		 SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		 String currDate = SeleniumUtils.getCurrDate();
		 String[] split = currDate.split("_");
		 String DateOnly = split[0];
		 String[] split2 = DateOnly.split("-");
		 String presentDate=split2[1]+"/"+split2[2]+"/"+split2[0];
		 int D = Integer.parseInt(split2[2]);
		 int Date=(D+1);
		 if(Date<10) {
			  NextDay = "0"+String.valueOf(Date);
		 }else {
			  NextDay = String.valueOf(Date); 
		 }
		 String nextDayDate=split2[1]+"/"+NextDay+"/"+split2[0];
		 //click on dashboard module
		 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_DashBoardModule"), "xpath");
		 SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		 SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_TopClientsTable"), "xpath");
		 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_TableHeaders"), "xpath");
		 List<WebElement> AccHeadersList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_TableHeaders")));
		 WebElement AccNameHeadPos = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_AccountNamePosition")));
		 int AccNameIndex = AccHeadersList.indexOf(AccNameHeadPos);
		 //click on particular account
		 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_AccountName").replace("temp", ""+(AccNameIndex+1) +""), "xpath");
		 SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_AccountTearSheetHead"), "xpath");
		 //click on burger button
		 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_BurgerButton"), "xpath");
		 //click on edit account button
		 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EditAccountButton"), "xpath");
		 SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_AccCoverageTab"), "xpath");
		 //click on account coverage tab
		 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_AccCoverageTab"), "xpath");
		 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EmployeeName"), "id");
		 //get the employee name from excel
		 String employeeName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_v_Employee_Name"));
		 //Set the value to the employee name field
		 SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EmployeeName"), employeeName, "id");
		 //get the employee email from excel
		 String employeeEmail = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_v_Employee_Email"));
		 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EmployeeNameSelect").replace("temp", "" + employeeEmail + ""), "xpath");
		 SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EmployeeNameSelect").replace("temp", "" + employeeEmail + ""), "xpath");
		 //click on required email
		 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EmployeeNameSelect").replace("temp", "" + employeeEmail + ""), "xpath");
		 //get the product name from excel
		 String productName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_v_Product"));
		 //select the product dropdown value from drop down
		 SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_Product"), productName, "value", "id");
		 SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoverageStartDate"), "id");
		 //set the start date
		 SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoverageStartDate"), presentDate, "id");
		 SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoverageEndDate"), "id");
		 //set the end date
		 SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoverageEndDate"), presentDate, "id");
		 String CovType = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_v_Coverage_Type"));
		 SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoverageType"), CovType, "value", "id");
		 //select the coverage type
		 SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoveragePercent"), "60", "id");
		 String CovStatus = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_v_CoverageStatus"));
		 SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_Status"), CovStatus, "value", "id");
		 //click on add button
		 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_AddButton"), "id");
		 Thread.sleep(2000);
		 //set the employee name
		 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EmployeeName"), "id");
		 SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EmployeeName"), employeeName, "id");
		 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EmployeeNameSelect").replace("temp", "" + employeeEmail + ""), "xpath");
		 SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EmployeeNameSelect").replace("temp", "" + employeeEmail + ""), "xpath");
		 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EmployeeNameSelect").replace("temp", "" + employeeEmail + ""), "xpath");
		 SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_Product"), productName, "value", "id");
		 SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoverageStartDate"), "id");
		 SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoverageStartDate"), nextDayDate, "id");
		 SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoverageEndDate"), "id");
		 SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoverageEndDate"), nextDayDate, "id");
		 SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoverageType"), CovType, "value", "id");
		 SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoveragePercent"), "60", "id");
		 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_AddButton"), "id");
		 Thread.sleep(1000);
		 //get the error message
		 String toastMessage = SeleniumUtils.getToastMessage();
		 if (toastMessage.equals("Coverage percentage exceed")) {
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Got An error message - Coverage percentage exceed",ExtentColor.GREEN));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_WebElements"),0));
			SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoveragePercent"), "id");
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoveragePercent"), "40", "id");
			SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_Status"), CovStatus, "value", "id");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_AddButton"), "id");
		}else {
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("No error message - Coverage percentage exceed",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_WebElements"),0));
		}
		 SeleniumUtils.closeToastMessage();
		 Thread.sleep(2000);
		 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EmployeeName"), "id");
		 SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EmployeeName"), employeeName, "id");
		 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EmployeeNameSelect").replace("temp", "" + employeeEmail + ""), "xpath");
		 SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EmployeeNameSelect").replace("temp", "" + employeeEmail + ""), "xpath");
		 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EmployeeNameSelect").replace("temp", "" + employeeEmail + ""), "xpath");
		 SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_Product"), productName, "value", "id");
		 SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoverageStartDate"), "id");
		 SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoverageStartDate"), nextDayDate, "id");
		 SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoverageEndDate"), "id");
		 SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoverageEndDate"), nextDayDate, "id");
		 SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoverageType"), CovType, "value", "id");
		 SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_CoveragePercent"), "30", "id");
		 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_AddButton"), "id");
		 Thread.sleep(1000);
		 String toastMessage2 = SeleniumUtils.getToastMessage();
		 if (toastMessage2.equals("This coverage already exists.")) {
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Got An error message - This coverage already exists.",ExtentColor.GREEN));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_WebElements"),0));
			 SeleniumUtils.closeToastMessage();
			 //click on edit close button
			 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EditButtonClose"), "xpath");
			 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EditButtonClose"), "xpath");
			 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EditCloseCnfmButton"), "id");
			 //click on edit close confirm button
			 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_EditCloseCnfmButton"), "id");
			 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_AccountCloseButton"), "xpath");
			 //click on account tear sheet close button
			 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_1_id_AccountCloseButton"), "xpath");
		} else {
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("No error message - This coverage already exists.",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9378_WebElements"),0));
		}
}catch (Exception e) {
	e.printStackTrace();
}
}
 
}