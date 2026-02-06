package com.dz.prism.productionbug;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Map.Entry;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_10066 {
	public static void Read_Excel(Properties PRODUCTIONBUGPROP) throws Exception {
		SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_10066 Research Sales Tearsheet Commission Tiles Visibility Verification");
		// Read excel file
		Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+ ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_v_test_case_path"));
		for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
			List<Map<String, String>> innerRows = testRows.getValue();
			for (Map<String, String> values : innerRows) {
				commission_Summary_Tiles_Verification(values);
				
			}
		}
	}
	public static void commission_Summary_Tiles_Verification(Map<String, String> values) {
		try {
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_DashBoardModule"), "xpath");
		//click on Dashboard module
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_DashBoardModule"), "xpath");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_ResearchSalesTab"), "xpath");
		//SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_ResearchSalesTab"), "xpath");
		//Click on research sales tab
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_ResearchSalesTab"), "xpath");
		//Table header list
		List<WebElement> HeaderList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_TableHeaderList")));
		//Sales head 
		WebElement Saleshead = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_SalesPersonHead")));
		//Sales Head Position
		int SalesHeadPosition = HeaderList.indexOf(Saleshead);
		//Top Sales Count
		int TopSalesCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_SalesPersonName").replace("index", "" + (SalesHeadPosition+1) + ""))).size();
		for (int i = 1; i <= TopSalesCount; i++) {
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_SalesPersonName").replace("index", "" + (SalesHeadPosition+1) + "")+ "[" + i + "]", "xpath");
			//SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_SalesPersonName").replace("index", "" + (SalesHeadPosition+1) + "") + "[" + i + "]", "xpath");
			//To get the Sales Person name
			String SalesPersonName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_SalesPersonName").replace("index", "" + (SalesHeadPosition+1) + "") + "[" + i + "]", "xpath");
			//To click on Particular Sales Person
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_SalesPersonName").replace("index", "" + (SalesHeadPosition+1) + "") + "[" + i + "]", "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_YTDFilterButton"), "xpath");
			//To click YTD filter button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_YTDFilterButton"), "xpath");
			int YtdFilterCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_YtdFilterCount"))).size();
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_CustomRangeButton").replace("temp", "" + (YtdFilterCount-2) + ""), "xpath");
			int startDateCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_StartDate"))).size();
			SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_StartDate")+"["+(startDateCount-2)+"]", "xpath");
			String StartDate = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_v_start_Date"));
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_StartDate")+"["+(startDateCount-2)+"]", StartDate, "xpath");
			int EndDateCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_EndDate"))).size();
			SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_EndDate")+"["+(EndDateCount-2)+"]", "xpath");
			String EndDate = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_v_end_Date"));
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_EndDate")+"["+(EndDateCount-2)+"]", EndDate, "xpath");
			int ApplyCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_ApplyButton"))).size();
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_ApplyButton")+"["+ApplyCount+"]", "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_ApplyConfirmButton"), "id");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			boolean errorMessage = SeleniumUtils.isElementExist("xpath", ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_ErrorMessage"), 3);
			if (errorMessage!=true) {
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(SalesPersonName+" - This Research sales person has the tiles Table",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_WebElements"),0));
			} else {
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(SalesPersonName+" - This Research sales person doesn't has the tiles Table",ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_WebElements"),1));
			}
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_TearSheetClose"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10066_1_id_TearSheetClose"), "xpath");
			
		}
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
