package com.dz.prism.productionbug;

import java.awt.Robot;
import java.awt.event.KeyEvent;
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

public class Bug_9772 {
	
	 public static void ReadExcel() throws IOException {
	    	
	        Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(
	                SeleniumUtils.UserDirVar + ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_v_test_case_path"));
	        SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_9772 Account Coverage status value verification");

	        for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
	            List<Map<String, String>> innerRows = testRows.getValue();
	            for (Map<String, String> values : innerRows) {
	                account_Coverage_Status_Verification(values);
	            }
	        }
	    }
	 public static void account_Coverage_Status_Verification(Map<String, String> values) {
		 try {
			 SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			 //click on dashboard module
			 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_DashBoardModule"), "xpath");
			 SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			 SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_TopClientsTable"), "xpath");
			 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_TableHeaders"), "xpath");
			 List<WebElement> AccHeadersList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_TableHeaders")));
			 WebElement AccNameHeadPos = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_AccountNamePosition")));
			 int AccNameIndex = AccHeadersList.indexOf(AccNameHeadPos);
			 //click on particular account
			 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_AccountName").replace("temp", ""+(AccNameIndex+1) +""), "xpath");
			 SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_AccountTearSheetHead"), "xpath");
			 //click on burger button
			 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_BurgerButton"), "xpath");
			 //click on edit account button
			 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_EditAccountButton"), "xpath");
			 SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_AccCoverageTab"), "xpath");
			 //click on account coverage tab
			 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_AccCoverageTab"), "xpath");
			 //To get the employee name from excel
			 String EmployeeName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_v_Employee_Name"));
			 //set the employee name to field
			 SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_EmployeeName"), EmployeeName, "id");
			 //to get the employee emailid
			 String EmployeeEmail = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_v_Employee_Email"));
			 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_EmployeeNameSelect").replace("temp", "" + EmployeeEmail+ ""), "xpath");
			 SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_EmployeeNameSelect").replace("temp", "" + EmployeeEmail+ ""), "xpath");
			 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_EmployeeNameSelect").replace("temp", "" + EmployeeEmail+ ""), "xpath");
			 //click on particular employee id
			 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_EmployeeNameSelect").replace("temp", "" + EmployeeEmail+ ""), "xpath");
			 //To get the product value
			 String Product = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_v_Product"));
			 //Select the value in dropdown
			 SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_Product"), Product, "value", "id");
			 //To get the coverage type 
			 String CoverageType = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_v_Coverage_Type"));
			 //select the value in dropdown
			 SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_CoverageType"), CoverageType, "value", "id");
			 //Click on add button
			 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_AddButton"), "id");
			 Thread.sleep(3000);
			 Robot r=new Robot();
			 r.keyPress(KeyEvent.VK_PAGE_DOWN);
			 r.keyRelease(KeyEvent.VK_PAGE_DOWN);
			 String currDate = SeleniumUtils.getCurrDate();
			 String[] split = currDate.split("_");
			 String[] split2 = split[0].split("-");
			 String date = split2[2];
			 String month = split2[1];
			 String year = split2[0];
			 int CovDat=Integer.valueOf(date)+1;
			 String CovDate=month+"/"+String.valueOf(CovDat)+"/"+year;
			 List<WebElement> CovHeaderLists = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_HeaderLists")));
			 WebElement StatusWebelement = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_StatusPosition")));
			 WebElement CovWebelement = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_CoverageStartDatePosition")));
			 //To get the coverage position
			 int CovIndex = CovHeaderLists.indexOf(CovWebelement);
			 //To get the status position
			 int statusIndex = CovHeaderLists.indexOf(StatusWebelement);
			 //To get the row size
			 int rowSize = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_TableRowCount"))).size();
			 for (int i = 1; i <= rowSize; i++) {
				//To get the Coverage date
				 String CoverageDate = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_CoverageStartDate").replace("temp", "" + (CovIndex+1) + "").replace("rowIndex", "" + i + ""), "xpath");
				if(CoverageDate.equals(CovDate)) {
					//To get the Table Employee name
					String TableEmployeeName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_TableEmployeeName").replace("rowIndex", "" + i + ""), "xpath");
					//To get the Table Status value
					String TableStatus = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_Status").replace("temp", "" + (statusIndex+1) + "").replace("rowIndex", "" + i + ""), "xpath");
					if (TableStatus.equals("Active") || TableStatus.equals("Inactive")) {
						SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(TableEmployeeName+" status is "+TableStatus+" and displayed in table",ExtentColor.GREEN));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_WebElements"),0));
					} else {
						SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(TableEmployeeName+" status is "+TableStatus+" empty in the table",ExtentColor.RED));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_WebElements"),1));
					}
				}
			}
			 //To click the Edit close button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_EditAccountCloseButton"), "xpath");
			//SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_EditAccountCloseCnfmButton"), "id");
			//To click the edit confirm close button
			//SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_EditAccountCloseCnfmButton"), "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_TableHeaders"), "xpath");
			//To click on Account close button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9772_1_id_AccountCloseButton"), "xpath"); 
		 }catch (Exception e) {
			e.printStackTrace();
		}
	 }
}
