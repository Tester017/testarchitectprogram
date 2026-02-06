package com.dz.prism.productionbug;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;
		//Production Bug_6471_TC_956  - Production Bug_6471_TC_959
public class Bug_6471 {
	public static void research_Readership_Data_Verification() {
		SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_6471 Readership data Verification");
		try {
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			//110 url
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6471_1_id_ActivityModule"), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6471_1_id_ActivityModule"), "xpath");
			WebElement ActivityModule = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6471_1_id_ActivityModule")));
			//28 url
			/*SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6471_1_id_ResearchModule"), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6471_1_id_ResearchModule"), "xpath");
			WebElement ResearchModule = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6471_1_id_ResearchModule")));*/
			Actions a=new Actions(SeleniumUtils.webDriver);
			a.moveToElement(ActivityModule).perform();
			//for 28 url
			//a.moveToElement(ResearchModule).perform();
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6471_1_id_ReadershipSubModule"), "xpath");
			//click on readership module
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6471_1_id_ReadershipSubModule"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6471_1_id_ReadershipDatas"), "xpath");
			boolean elementExist = SeleniumUtils.isElementExist("xpath", ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6471_1_id_ErrorMessage"), 3);
			if (elementExist!=true) {
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Readership datas is displayed",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6471_WebElements"),0));
			} else {
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Readership datas is Not displayed",ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6471_WebElements"),1));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
