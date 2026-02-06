package com.dz.prism.productionbug;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;
				//Production Bug_6379_TC_981 - Production Bug_6379_TC_989
public class Bug_6379 {
	public static void commission_BreakUp_And_Month_Over_comparison_Verification() throws Exception {
		SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_6379 commission_BreakUp_And_Month_Over_comparison_Verification");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_1_id_DashBoardModule"), "xpath");
		//click on Dashboard module
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_1_id_DashBoardModule"), "xpath");
		 SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		 SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_1_id_TopClientsTable"), "xpath");
		 SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_1_id_TableHeaders"), "xpath");
		 //get the header list
		 List<WebElement> AccHeadersList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_1_id_TableHeaders")));
		 WebElement AccNameHeadPos = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_1_id_AccountNamePosition")));
		 //get the index position of the account
		 int AccNameIndex = AccHeadersList.indexOf(AccNameHeadPos);
		 //get the size of the account present
		 int size = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_1_id_AccountNameList").replace("index", ""+ (AccNameIndex+1) +""))).size();
		 for (int i = 1; i <= size; i++) {
			//get the account name
			String AccName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_1_id_AccountNameSelect").replace("index", ""+ (AccNameIndex+1) +"").replace("temp", "" + i + ""), "xpath");
			//click on particular account
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_1_id_AccountNameSelect").replace("index", ""+ (AccNameIndex+1) +"").replace("temp", "" + i + ""), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_1_id_RevenueTab"), "xpath");
			//click on revenue tab
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_1_id_RevenueTab"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			//check whether the commission breakup chart present or not
			boolean commBreakUp = SeleniumUtils.isElementExist("xpath", ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_1_id_CommissionBreakupNoData"), 2);
			if (commBreakUp!=true) {
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(AccName+" - has Commission Break up chart",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_WebElements"),0));
			} else {
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(AccName+" - doesn't have Commission Break up chart",ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_WebElements"),1));
			}
			//check whether the month over month comparison chart present or not
			boolean monthOverMonthComp = SeleniumUtils.isElementExist("xpath", ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_1_id_monthOverMonthCommNoData"), 2);
			if (monthOverMonthComp!=true) {
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(AccName+" - has Month over comparison chart",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_WebElements"),0));
			} else {
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(AccName+" - doesn't have Month over comparison chart",ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_WebElements"),1));
			}
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_1_id_AccountTearSheetCloseButton"), "xpath");
			//click on account tear sheet close button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6379_1_id_AccountTearSheetCloseButton"), "xpath");
		}
	}
}
