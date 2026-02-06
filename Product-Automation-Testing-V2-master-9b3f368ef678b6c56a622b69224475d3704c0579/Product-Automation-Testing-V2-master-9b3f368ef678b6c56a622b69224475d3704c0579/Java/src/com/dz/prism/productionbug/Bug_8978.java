package com.dz.prism.productionbug;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_8978 {
	public static void verify_Ticker_And_Account_TableData() {
		try {
			SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_8978-Ticker and Account table data verification for position traders");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8978_1_id_AppPage"), "xpath");
			//click on dash board module
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8978_1_id_DashBoardModule"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8978_1_id_AppPage"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8978_1_id_TablePage"), "xpath");
			//To get the inner tag and text in the table
			String clientInnerTag = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8978_1_id_ClientDataTable"), "innerHTML", "id");
			String ClientTableText = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8978_1_id_ClientDataTable"), "id");
			if (clientInnerTag.contains("div") && !ClientTableText.equalsIgnoreCase("No Data Available")) {
				//System.out.println("Client table data available");
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Trader by Account table has the table datas",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8978_WebElements"),0));
			}else if(ClientTableText.equalsIgnoreCase("Unable to fetch data")){
				//System.out.println("Unable to fetch data");
				SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("The Table unable to fetch the datas",ExtentColor.TEAL));
			}
			else {
				//System.out.println("Client data not available");
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Client data not available in table",ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8978_WebElements"),1));
			}
			//click on Trades by ticker tab
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8978_1_id_TradesByTickerTab"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8978_1_id_TablePage"), "xpath");
			//To get the inner tag and text in the table
			String TickerInnerTag = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8978_1_id_TickerDataTable"), "innerHTML", "id");
			String TickerTableText = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8978_1_id_TickerDataTable"), "id");
			if (TickerInnerTag.contains("div") && !TickerTableText.equalsIgnoreCase("No Data Available")) {
				System.out.println("Ticker table data available");
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Trader by Account table has the table datas",ExtentColor.GREEN));
			}else if(TickerTableText.equalsIgnoreCase("Unable to fetch data")){
				System.out.println("Unable to fetch data");
				SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("The Table unable to fetch the datas",ExtentColor.TEAL));
			}
			else {
				System.out.println("Ticker data not available");
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Ticker data not available in table",ExtentColor.RED));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
