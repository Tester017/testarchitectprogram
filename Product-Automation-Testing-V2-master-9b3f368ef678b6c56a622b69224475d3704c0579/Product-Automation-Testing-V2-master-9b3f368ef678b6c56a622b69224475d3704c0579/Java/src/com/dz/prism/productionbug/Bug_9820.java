package com.dz.prism.productionbug;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_9820 {
	public static void readership_Hits_Count_Verification() {
		try {
			SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_9820 Readership Hits Count Verification");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			Actions a = new Actions(SeleniumUtils.webDriver);
			//for 28 url
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9820_1_id_ResearchModule"), "xpath");
			WebElement ResearchModule = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9820_1_id_ResearchModule")));
			a.moveToElement(ResearchModule).perform();
			//for 110 url
			/*SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9820_1_id_ActivtyModule"), "xpath");
			WebElement ActivityModule = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9820_1_id_ActivtyModule")));
			a.moveToElement(ActivityModule).perform();*/
			//click on Readership sub tab
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9820_1_id_ReadershipSubTab"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9820_1_id_HitsTable"), "xpath");
			//To get the Hits Count
			String HitsCountTable = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9820_1_id_HitsCountDisplayed"), "xpath");
			Robot r=new Robot();
			r.keyPress(KeyEvent.VK_PAGE_DOWN);
			r.keyRelease(KeyEvent.VK_PAGE_DOWN);
			String TotalHitsCount = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9820_1_id_HitsTotalCountList"), "xpath");
			String[] split = TotalHitsCount.split(" ");
			String TotalHits = split[5];
			if (HitsCountTable.equals(TotalHits)) {
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Hits value is matched and displaying correctly",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9820_WebElements"),0));
			} else {
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Hits value is not matched and not displaying correctly",ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9820_WebElements"),1));
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
