package com.dz.prism.productionbug;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_10576 {
	public static void revenue_Module_TableDatas_Verification(){
		try {
			SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_10576 Revenue By Product and Revenue By Account (2019)Year Filter Range Datas Verification");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			//110 URL
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_RenenueModule"), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_RenenueModule"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_RenenueModule"), "xpath");
			//28 URL
			/*SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_RenenueModule"), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_RenenueModule"), "xpath");
			WebElement RevenueModule = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_RenenueModule")));
			Actions a=new Actions(SeleniumUtils.webDriver);
			a.moveToElement(RevenueModule).perform();
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_RevenueSubModule"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_RevenueSubModule"), "xpath");*/
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.webDriver.navigate().refresh();
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_PeriodFilter"), "xpath");
			//click on Period filter
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_PeriodFilter"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_CustomRangeButton"), "xpath");
			//click on custom range button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_CustomRangeButton"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_StartDatePicker"), "xpath");
			SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_StartDatePicker"), "xpath");
			//Set the start date in the field
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_StartDatePicker"), "01/01/2019", "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_EndDatePicker"), "xpath");
			SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_EndDatePicker"), "xpath");
			//Set the end date in the field
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_EndDatePicker"), "12/31/2019", "xpath");
			//click on apply button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_ApplyButton"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_ApplyCnfmButton"), "id");
			//click on Apply confirm button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_ApplyCnfmButton"), "id");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			boolean revByAccMessage = SeleniumUtils.isElementExist("xpath", ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_Message"), 3);
			if (revByAccMessage!=true) {
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Revenue By Account datas is displayed for the given year filter",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_WebElements"),0));
			} else {
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Revenue By Account datas is not displayed for the given year filter",ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_WebElements"),1));
			}
			boolean TabPresent = SeleniumUtils.isElementExist("xpath", ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_RevenueByProductTab"), 2);
			if (TabPresent==true) {
				//click on Revenue by product tab
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_RevenueByProductTab"), "xpath");
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				boolean revByProMessage = SeleniumUtils.isElementExist("xpath", ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_1_id_Message"), 3);
				if (revByProMessage!=true) {
					SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Revenue By Product datas is displayed for the given year filter",ExtentColor.GREEN));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_WebElements"),0));
				} else {
					SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Revenue By Product datas is not displayed for the given year filter",ExtentColor.RED));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10576_WebElements"),1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
