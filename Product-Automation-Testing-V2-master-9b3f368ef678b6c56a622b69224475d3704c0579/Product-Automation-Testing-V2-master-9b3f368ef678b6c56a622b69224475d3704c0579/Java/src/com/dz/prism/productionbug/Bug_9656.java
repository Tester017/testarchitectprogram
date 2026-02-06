package com.dz.prism.productionbug;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_9656 {

	public static void revenue_By_Account_Verification() {
		try {
			SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_9656 Revenue by Account table verification for Equity manager");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9656_1_id_RevenueModule"), "xpath");
			//click on revenue module
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9656_1_id_RevenueModule"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9656_1_id_RevenueByAccTab"), "xpath");
			//click on revenue by account sub module
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9656_1_id_RevenueByAccTab"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9656_1_id_RevenueByAccValues"), "xpath");
			String textfromField = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9656_1_id_RevenueByAccValues"), "xpath");
			if (!textfromField.equalsIgnoreCase("No Data Available")) {
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Revenue by account has table data",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9656_WebElements"),0));
			} else {
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Revenue by account has table data",ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9656_WebElements"),1));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}
