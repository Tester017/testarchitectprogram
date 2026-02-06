package com.dz.prism.productionbug;

import java.awt.Robot;
import java.awt.event.KeyEvent;
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

public class Bug_8759 {

	static String Name=null;
	static String revPerGrossComm=null;
	public static void verification_Of_Revenue_YTD_Custom_Range(Properties PRODUCTIONBUGPROP) throws Exception {
		
		// Read excel file
		Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+ ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_v_test_case_path"));
		for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
			List<Map<String, String>> innerRows = testRows.getValue();
			for (Map<String, String> values : innerRows) {
			revenue_YTD_Custom_Range_Verification(values);	
				
			}
		}
	}

	public static void revenue_YTD_Custom_Range_Verification(Map<String, String> values) {
		try {
			SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_8759 Verification of Revenue By Product and person in YTD custom range");
			Thread.sleep(2500);
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_DashboardPage"), "id");
			//to select the revenue and revenue by sub module tab
			WebElement rev = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_RevenueModule")));
			Actions s= new Actions(SeleniumUtils.webDriver);
			s.moveToElement(rev).build().perform();
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_RevenueSubModule"), "xpath");
			SeleniumUtils.webDriver.navigate().refresh();
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_revByAccTableValues"), "xpath");
			//click on revenue by person tab
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_RevenueByPersonTab"), "xpath");
			//click on ytdfilter
			Thread.sleep(3000);
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_ytdfilterIcon"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_ytdfilterIcon"), "xpath");
			//click on custom range
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_customRangeIcon"), "xpath");
			//clear the start value and set start date
			SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_startDate"), "xpath");
			String startDate = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_v_start_date"));
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_startDate"), startDate, "xpath");
			//clear the end value and set end value
			SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_endDate"), "xpath");
			String endDate = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_v_end_date"));
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_endDate"),endDate , "xpath");
			//click on apply button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_applyButton"), "xpath");
			//click on apply confirm button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_applyCnfmBtn"), "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_revByPerTableValues"), "xpath");
			//To select the particular sales and research account
			Thread.sleep(4000);
			for (int i = 1; i <=10; i++) {
				Thread.sleep(2000);
				 Name = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_revByPerNameListSelect").replace("temp", "" + i + ""), "xpath");
				 revPerGrossComm = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_revByPerGrossComm").replace("temp", "" + i + ""), "xpath");
				// SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_revByAccTableValues"), "xpath");
				 
				 SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_revByPerNameListSelect").replace("temp", "" + i + ""), "xpath");
				tearsheet_Verification(values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void tearsheet_Verification(Map<String, String> values) {
		try {
			//To get the account type Research sales or sales trader
			Thread.sleep(4500);
			String AccountType = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8579_1_id_TearSheeType"), "xpath");
			if (AccountType.equals("ST")) {
				//click on revenue tab
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_RevenueTab"), "xpath");	
			}
			Thread.sleep(4500);
			//To get YTD List count
			int size = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8579_1_id_TearSheetYtdListCount"))).size();
			//click on YTD 
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_TearsheetYtd"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_TearsheetYtd"), "xpath");
			//click on custom range
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8579_1_id_TearSheetCustumRangeBtn").replace("temp", "" + (size+1) + ""), "xpath");
			//clear the start value and set start date
			int size3 = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_TearSheetDateTableList"))).size();
			SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_TearstartDate").replace("temp", "" + size3 + ""), "xpath");
			String startDate = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_v_start_date"));
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_TearstartDate").replace("temp", "" + size3 + ""), startDate, "xpath");
			//clear the end value and set end value
			SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_TearendDate").replace("temp", "" + size3 + ""), "xpath");
			String endDate = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_v_end_date"));
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_TearendDate").replace("temp", "" + size3 + ""),endDate , "xpath");
			//To get apply button count list size and click on apply button
			int size2 = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_TearSheetYtdApply"))).size();
			if (AccountType.equals("ST")) {
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_TearSheetYtdApply")+"["+(size2-1)+"]", "xpath");
			}else {
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_TearSheetYtdApply")+"["+(size2-2)+"]", "xpath");
			}
			//click on apply confirm button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_TearSheetYtdApplyConfirm"), "id");
			Thread.sleep(3000);
			//to get the gross comm value
			String grossComm = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_grossCommValue"), "xpath");
			//to get the previous YTD value
			String prevYTD = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_prevYTDValue"), "xpath");
			//to get the annual comm value
			String annComm = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_annCommValue"), "xpath");
			//to get the net value comm value
			String netValueComm = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_netCommValue"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_revByAccountTab"), "xpath");
			Robot r1=new Robot();
			for (int i = 0; i < 5; i++) {
				r1.keyPress(KeyEvent.VK_DOWN);
				r1.keyRelease(KeyEvent.VK_DOWN);
			}
			//scroll to table value
			if (AccountType.equals("ST")) {
				SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8579_1_id_TearSheetTableST"), "xpath");
			} else {
				SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8579_1_id_TearSheetTableRS"), "xpath");
			}
			//To click the table value
			if (AccountType.equals("ST")) {
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8579_1_id_SSTableValueAccClick"), "xpath");
			} else {
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8579_1_id_RSTableValueAccClick"), "xpath");
			}
			//Thread.sleep(3000);
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_tableAccGrossComm"), "xpath");
			//To get the account gross comm value
			String accTabGrossComm = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_tableAccGrossComm"), "xpath");
			//click on revenue by product tab
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_revByProductTab"), "xpath");
			//to click the table value
			if (AccountType.equals("ST")) {
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8579_1_id_STTableValuePdtClick"), "xpath");
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8579_1_id_STTableValuePdtClick"), "xpath");
			} else {
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8579_1_id_RSTableValuePdtClick"), "xpath");
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8579_1_id_RSTableValuePdtClick"), "xpath");
			}
			//Thread.sleep(3000);
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_tableProductGrossComm"), "xpath");
			//to get the revenue by product gross comm
			String proTabGrossComm = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_tableProductGrossComm"), "xpath");
			//to get the revenue by product Previous YTD value
			String proTabPrevYtdVal = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_tablePrevYrVal"), "xpath");
			//to get revenue by product Net value
			String proTabNetVal = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_tableNetVal"), "xpath");
			//to get revenue by product Annual value
			String proTabAnnVal = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_tableAnnVal"), "xpath");
			if (revPerGrossComm.equals(accTabGrossComm)) {
				//System.out.println(Name+"-->account Tab gross value matched-->"+revPerGrossComm+" & "+accTabGrossComm);
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(Name+"-->account Tab gross value matched-->"+revPerGrossComm+" & "+accTabGrossComm,ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_WebElements"),0));
			} else {
				//System.out.println(Name+"-->account Tab gross value not matched-->"+revPerGrossComm+" & "+accTabGrossComm);
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(Name+"-->account Tab gross value not matched-->"+revPerGrossComm+" & "+accTabGrossComm,ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_WebElements"),1));
			}
			if (grossComm.equals(proTabGrossComm)) {
				//System.out.println(Name+"-->product Tab gross value matched-->"+grossComm+" & "+proTabGrossComm);
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(Name+"-->product Tab gross value matched-->"+grossComm+" & "+proTabGrossComm,ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_WebElements"),0));
			} else {
				//System.out.println(Name+"-->product Tab gross value not matched-->"+grossComm+" & "+proTabGrossComm);
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(Name+"-->product Tab gross value not matched-->"+grossComm+" & "+proTabGrossComm,ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_WebElements"),1));
			}
			if (prevYTD.equals(proTabPrevYtdVal)) {
				//System.out.println(Name+"-->product Tab previous Ytd value matched-->"+prevYTD+" & "+proTabPrevYtdVal);
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(Name+"-->product Tab previous Ytd value matched-->"+prevYTD+" & "+proTabPrevYtdVal,ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_WebElements"),0));
			} else {
				//System.out.println(Name+"-->product Tab previous Ytd value not matched-->"+prevYTD+" & "+proTabPrevYtdVal);
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(Name+"-->product Tab previous Ytd value not matched-->"+prevYTD+" & "+proTabPrevYtdVal,ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_WebElements"),1));
			}
			if (annComm.equals(proTabAnnVal)) {
				//System.out.println(Name+"-->product Tab Annual value matched-->"+annComm+" & "+proTabAnnVal);
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(Name+"-->product Tab Annual value matched-->"+annComm+" & "+proTabAnnVal,ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_WebElements"),0));
			} else {
				//System.out.println(Name+"-->product Tab Annual value not matched-->"+annComm+" & "+proTabAnnVal);
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(Name+"-->product Tab Annual value not matched-->"+annComm+" & "+proTabAnnVal,ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_WebElements"),1));
			}
			if (netValueComm.equals(proTabNetVal)) {
				//System.out.println(Name+"-->product Tab net value matched-->"+netValueComm+" & "+proTabNetVal);
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(Name+"-->product Tab net value matched-->"+netValueComm+" & "+proTabNetVal,ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_WebElements"),0));
			} else {
				//System.out.println(Name+"-->product Tab net value not matched-->"+netValueComm+" & "+proTabNetVal);
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(Name+"-->product Tab net value not matched-->"+netValueComm+" & "+proTabNetVal,ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_WebElements"),1));
			}
			//scroll to the account panel head
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_tearSheetCloseButton"), "xpath");
			Robot r=new Robot();
				for (int i = 0; i < 5; i++) {
					r.keyPress(KeyEvent.VK_UP);
					r.keyRelease(KeyEvent.VK_UP);
				}
			//click on account close button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8759_1_id_tearSheetCloseButton"), "xpath");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
}
