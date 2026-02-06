package com.dz.prism.productionbug;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_8490 {
	public static void sales_Traders_Account_Verification() {
		try {
			SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_8490 Verifying Traders and sales person List displaying correctly");
			//click on dashboard module
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_DashBoardModule"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_DashBoardPage"), "id");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_Table"), "xpath");
			//click on sales trader tab
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_TradersTab"), "xpath");
			for (int i = 1; i <=10; i++) {
				//Thread.sleep(1500);
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_SalesTable"), "xpath");
				//get the name of sales trader
				String SalesTraderName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_SalesTraderList").replace("temp", "" + i + ""), "xpath");
				//click on particular account
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_SalesTraderList").replace("temp", "" + i + ""), "xpath");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_PanelHead"), "xpath");
				//get the account type
				String panel = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_PanelName"), "data-fixedid", "xpath");
				if (panel.equals("ST")) {
					//System.out.println(SalesTraderName+"pass");
					SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(SalesTraderName+"-account viewed is sales trader account and displaying correctly",ExtentColor.GREEN));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_WebElements"),0));
				} else {
					//System.out.println(SalesTraderName+"fail");
					SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(SalesTraderName+"-account viewed is not a sales trader account and not displaying correctly",ExtentColor.RED));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_WebElements"),1));
				}//Thread.sleep(1500);
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_page"), "id");
				//click on account close button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_AccountCloseButton"), "xpath");
			}
			//click on research sales tab
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_ResearchSalesTab"), "xpath");
			for (int i = 1; i <=10; i++) {
				//Thread.sleep(1500);
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_ResearchTable"), "xpath");
				//get the research sales name
				String ResearchSalesName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_ResearchSalesList").replace("temp", "" + i + ""), "xpath");
				//click on particular account
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_ResearchSalesList").replace("temp", "" + i + ""), "xpath");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_PanelHead"), "xpath");
				//get the account type
				String panel = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_PanelName"), "data-fixedid", "xpath");
				if (panel.equals("RS")) {
					//System.out.println(ResearchSalesName+"pass");
					SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(ResearchSalesName+"-account viewed is Research sales account and displaying correctly",ExtentColor.GREEN));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_WebElements"),0));
				} else {
					//System.out.println(ResearchSalesName+"fail");
					SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(ResearchSalesName+"-account viewed is not a Research sales account and not displaying correctly",ExtentColor.RED));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_WebElements"),1));
				}//Thread.sleep(1500);
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_page"), "id");
				//click on account close button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8490_1_id_AccountCloseButton"), "xpath");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
