package com.dz.prism.productionbug;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_7785 {
	public static void research_Readership_Verification() {
		try {
			SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_7785 Verification of Research ReaderShip datas");
			//select the Readership sub tab in research module
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.tabSelection("Research", "Readership");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7785_1_id_ReadershipDataPage"), "xpath");
			//click on Readership period filter
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7785_1_id_ReadershipPeriodFilter"), "id");
			//click on Ytd year filter
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7785_1_id_YTDYearFilter"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7785_1_id_ReadershipDataPage"), "xpath");
			//get the table datas
			String TableDatas = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7785_1_id_TableDatas"), "xpath");
			if (!TableDatas.equalsIgnoreCase("No data available in table")) {
				//System.out.println("pass");
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Research ReaderShip page has the ReaderShip datas",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7785_WebElements"),0));
			} else {
				//System.out.println("fail");
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Research ReaderShip page Doesn't has the ReaderShip datas",ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7785_WebElements"),1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
