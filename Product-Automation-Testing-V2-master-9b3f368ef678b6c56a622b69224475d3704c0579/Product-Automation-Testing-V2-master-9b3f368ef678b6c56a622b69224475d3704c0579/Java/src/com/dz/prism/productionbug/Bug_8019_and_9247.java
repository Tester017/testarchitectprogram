package com.dz.prism.productionbug;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_8019_and_9247 {
	static String Date1;
	public static void revenue_By_Person_Filter_Verification() throws Exception {
		try {
		SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_8019 and 9247 Revenue Module table verification");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		//SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_RevenueModule"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_RevenueModuleOnly"), "xpath");
		//28 url
		//move on revenue and click on revenue tab 
		/*WebElement rev = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_RevenueModule")));
		Actions s= new Actions(SeleniumUtils.webDriver);
		s.moveToElement(rev).build().perform();
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_RevenueSubModule"), "xpath");*/
		//110 url
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_RevenueModuleOnly"), "xpath");
		SeleniumUtils.webDriver.navigate().refresh();
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_revByAccTableValues"), "xpath");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		//click on ytdfilter
		//Thread.sleep(3000);
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_ytdfilterIcon"), "xpath");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_ytdfilterIcon"), "xpath");
		String LastDate = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_LastDate"), "xpath");
		String[] split = LastDate.split("/");
		String Date2 = split[1];
		 char c = Date2.charAt(0);
		 if (c=='0') {
			 Date1 = Date2.replace("0", "");
		}else {
			Date1=Date2;
		}
		//click on custom range
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_customRangeIcon"), "xpath");
		//clear the start value and set start date
		SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_startDate"), "xpath");
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_startDate"), "01/01/2021", "xpath");
		SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_endDate"), "xpath");
		for (int i = 0; i <100; i++) {
		//SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_NextMonthShiftButton"))).isDisplayed();
		String innerTag = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_NextMonthButton"), "innerHTML", "xpath");
		if (innerTag.contains("i")) {
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_NextMonthShiftButton"), "xpath");
		} else {
			break;
		}
		}
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_endDateSel").replace("temp",""+ Date1 +""), "xpath");
		//click on apply button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_applyButton"), "xpath");
		//click on apply confirm button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_applyCnfmBtn"), "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_revByAccTableValues"), "xpath");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		String RevAccTableValue = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_revByAccTableValues"), "xpath");
		if (!RevAccTableValue.equalsIgnoreCase("No data available in table")) {
			//System.out.println("Revenue by account has table data");
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Revenue by account has table data",ExtentColor.GREEN));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_WebElements"),0));
		} else {
			//System.out.println("Revenue by account has no table data");
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Revenue by account has no table data",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_WebElements"),1));
		}
		//Thread.sleep(7000);
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_revByAccTableValues"), "xpath");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_RevenueByPersonTab"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_revByPerTableValues"), "xpath");
		String RevPerTableValue = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_revByPerTableValues"), "xpath");
		if (!RevPerTableValue.equalsIgnoreCase("No data available in table")) {
			//System.out.println("Revenue by person has table value");
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Revenue by person has table value",ExtentColor.GREEN));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_WebElements"),0));
		} else {
			//System.out.println("Revenue by person has no table value");
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Revenue by person has no table value",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_WebElements"),1));
		}
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_RevenueByProductTab"), "xpath");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_RevenueByProductTab"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_revByProTableValues"), "xpath");
		String RevPdtTableValue = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_revByProTableValues"), "xpath");
		if (!RevPdtTableValue.equalsIgnoreCase("$0")) {
			//System.out.println("Revenue by product has table value");
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Revenue by product has table value",ExtentColor.GREEN));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_WebElements"),0));
		} else {
			//System.out.println("Revenue by product has no table value");
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Revenue by product has no table value",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_WebElements"),1));
		}
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_RevenueByRegionTab"), "xpath");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_RevenueByRegionTab"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_revByRegTableValues"), "xpath");
		String RevRegTableValue = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_revByRegTableValues"), "xpath");
		if (!RevRegTableValue.equalsIgnoreCase("-")) {
			//System.out.println("Revenue by region has table value");
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Revenue by region has table value",ExtentColor.GREEN));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_WebElements"),0));
		} else {
			//System.out.println("Revenue by region has no table value");
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Revenue by region has no table value",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_WebElements"),1));
		}
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_RevenueByTeamTab"), "xpath");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_RevenueByTeamTab"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_revByTeamTableValues"), "xpath");
		String RevTeamTableValues = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_1_id_revByTeamTableValues"), "xpath");
		if (!RevTeamTableValues.equalsIgnoreCase("No data available in table")) {
			//System.out.println("Revenue by team has table value");
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Revenue by team has table value",ExtentColor.GREEN));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_WebElements"),0));
		} else {
			//System.out.println("Revenue by team has no table value");
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Revenue by team has no table value",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8019_and_9247_WebElements"),1));
		}

		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
