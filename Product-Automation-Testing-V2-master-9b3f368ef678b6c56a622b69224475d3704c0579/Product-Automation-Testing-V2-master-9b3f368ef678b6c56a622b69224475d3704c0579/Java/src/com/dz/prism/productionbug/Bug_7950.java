package com.dz.prism.productionbug;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_7950 {
	public static void ticker_Black_Screen_Verification() {
		SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_7950 Research sales person name trades and action ticker background colour Verification");
		try {
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_DashBoardModule"), "xpath");
			//SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_DashBoardPage"), "xpath");
			//To select the account displayed
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_top_research_sales_person"), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_top_list"), "xpath");
			List<WebElement> accountList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_top_research_sales_dispalyed")));
			for (int i = 0; i < accountList.size(); i++) {
				String accName = accountList.get(i).getText();
				accountList.get(i).click();
				//Click on Summary tab
				Thread.sleep(4000);
				//SeleniumUtils.scriptWaitingTime(4);
				//SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_CommissionChart"), "xpath");
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_summary_tab"), "xpath");
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_ytd_filter"), "xpath");
				int size = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_year_filter_year"))).size();
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_year_select").replace("temp", ""+size+""), "xpath");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_TradeTickerName"),"xpath");
				Thread.sleep(3000);
				List<WebElement> tradeTickerList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_TradeTickerName")));			
				int tradeTicSize = tradeTickerList.size();
				//Thread.sleep(3000);
				
				for (int j = 1; j <= tradeTicSize; j++) {
					//WebElement w = tradeTickerList.get(j);
					SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_TradeTickerName")+"[" + j +"]", "xpath");
					String text = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_TradeTickerName")+"[" + j +"]", "xpath");
					String tradeTickerName = text.replace(",", "").replaceAll("[0-9]", "");
					//System.out.println("Tra ticker name----->"+tradeTickerName);
					WebElement w = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_TradeTickerName")+"[" + j +"]"));
					//SeleniumUtils.setTimeOut(20);
					//Thread.sleep(2000);
					String cssValue = w.getAttribute("fill");
					//System.out.println("css value--->"+cssValue);
					if(!cssValue.equals("#000000")) {
						//System.out.println(accName+"pass");
						SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accName+" for ticker "+tradeTickerName+ " is displaying correctly",ExtentColor.GREEN));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_WebElements"),0));
					}else {
						//System.out.println("fail");
						SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(accName+" for ticker "+tradeTickerName+ " is not displaying correctly",ExtentColor.RED));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_WebElements"),1));
					}	
				}
				Thread.sleep(4000);
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_action_tab"), "xpath");
				String text = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_Action_tabList"), "xpath");
				
				if (!text.equalsIgnoreCase("No Data Available")) {
					SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_ActionTickerName"), "xpath");
					List<WebElement> ActionTickerList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_ActionTickerName")));			
					int actionTicSize = ActionTickerList.size();
					//Thread.sleep(2000);
					for (int j = 1; j <= actionTicSize; j++) {
						//WebElement w = tradeTickerList.get(j);
						SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_ActionTickerName")+"[" + j +"]", "xpath");
						String text1 = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_ActionName")+"[" + j +"]", "xpath");
						String actionTickerName = text1.replace(",", "");
						//System.out.println("action ticker name----->"+actionTickerName);
						WebElement w1 = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_ActionTickerName")+"[" + j +"]"));
						String cssValue = w1.getAttribute("fill");
						//System.out.println("css value--->"+cssValue);
						if(!cssValue.equals("#000000")) {
							//System.out.println(accName+"pass");
							SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accName+" for ticker "+actionTickerName+ " is displaying correctly",ExtentColor.GREEN));
							SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_WebElements"),0));
						}else {
							//System.out.println("fail");
							SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(accName+" for ticker "+actionTickerName+ " is not displaying correctly",ExtentColor.RED));
							SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_WebElements"),1));
						}	
					}
				} else {
					//System.out.println("Action tab has no data");
					SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel(accName+" for Action have no ticker data",ExtentColor.CYAN));
				}
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7950_1_id_close_button_1"), "xpath");
			}
}catch (Exception e) {
	e.printStackTrace();
}
	}	

}
