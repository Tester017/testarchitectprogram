package com.dz.prism.productionbug;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_8616 {
	public static void analyst_Coverage_Name_Verification() throws Exception {
		try {
		SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_8616 Verification of Primany Analyst Name Displayed in Ticker Tear sheet Page");
		//click on dashboard module
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_DashBoardModule"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_DashBoardPage"), "id");
		SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_TableValue"), "xpath");
		Thread.sleep(3000);
		//click on top ticker tab
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_TopTickerTabSelect"), "xpath");
		for (int i = 1; i <=10; i++) {
			//get the ticker name
			String TickerName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_TickerList").replace("temp", "" + i + ""), "xpath");
			//click on particular ticker
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_TickerList").replace("temp", "" + i + ""), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_TickerTearHeadPanel"), "xpath");
			//get the inner tags
			String tags = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_TickerSliderHead"), "innerHTML", "id");
			if (tags.contains("li")) {
				//get the analyst name
				String AnalystName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_PrimaryAnalystName"), "xpath");
				String AnalystNames = AnalystName.replace(": ", "").replace("-", "");
				List<String> l=new ArrayList<String>();
				if (AnalystNames.contains(",")) {
					String[] Name = AnalystNames.split(",");
					
					for (String x:Name) {
						l.add(x);
					}
				} else {
					l.add(AnalystNames);
				}
				Thread.sleep(1500);
				//click on ticker burger button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_TickerBurgerButton"), "xpath");
				//click on edit button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_TickerEditButton"), "xpath");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_EditPage"), "xpath");
				//click on account coverage tab
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_AccountCoverageTab"), "xpath");
				SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_AccountCoverageTable"), "xpath");
				Thread.sleep(1500);
				//get the table values
				String TableValues = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_AccountCoverageTableValues"), "xpath");
				List<String>l1=new ArrayList<String>();
				if (!TableValues.equalsIgnoreCase("No Data Available")) {
					//account list dispalyed count
					int size = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_AnalystTableListName"))).size();
					//to get the active account status
					for (int j = 1; j <= size; j++) {
						String statusValue = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_AccountCoverageStatus").replace("temp", "" + j + ""), "xpath");
					if (statusValue.equalsIgnoreCase("Active")) {
						String CoverageName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_AnalystTableListName")+ "[" + j + "]", "xpath");
						l1.add(CoverageName);
					} else {
						//inactive account list
						//String CoverageName1 = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_AnalystTableListName")+ "[" + j + "]", "xpath");
						//System.out.println(TickerName+"-ticker-"+CoverageName1+" Coverage is inactive");
					}
						
					}
					if (l.containsAll(l1)) {
						SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(TickerName+"-ticker primary analyst value-"+ l + " coverage value-" + l1 +" primary analyst name and account coverage name is matched and displaying correctly",ExtentColor.GREEN));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_WebElements"),0));
					} else {
						SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(TickerName+"- ticker primary analyst value"+ l + " coverage value" + l1 + "primary analyst name and account coverage name is not matched and displaying incorrectly",ExtentColor.RED));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_WebElements"),1));
					}
				}else if(TableValues.equalsIgnoreCase("No Data Available") && l.contains("")) {
					SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(TickerName+"-ticker primary analyst value-"+ l + " coverage value-" + l1 +" primary analyst name and account coverage name is matched and displaying correctly",ExtentColor.GREEN));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_WebElements"),0));
				}
				else {
					SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel(TickerName+"- ticker primary analyst value" + l + " coverage value" + l1 +"has no account coverage data",ExtentColor.TEAL));
				}
			//click on edit page close button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_EditPageClose"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_EditConfirmPage"), "xpath");
			//click on edit page close confirm button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_EditPageCloseConfirmButton"), "id");
			
			} else {
				SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel(TickerName+"- this ticker dont have any primary analyst",ExtentColor.TEAL));
			}
			//tearsheet close button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8616_1_id_TearSheetCloseButton"), "xpath");
			
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}

}
