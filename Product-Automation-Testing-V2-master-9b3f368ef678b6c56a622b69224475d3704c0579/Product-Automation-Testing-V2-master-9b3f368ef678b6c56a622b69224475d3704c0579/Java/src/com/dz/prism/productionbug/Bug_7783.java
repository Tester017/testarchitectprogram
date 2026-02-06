package com.dz.prism.productionbug;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.module.accountcreation.AccountCreationMain;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_7783 {
	public static void coverage_Name_Verification() {
		SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_7783 Employee Coverage Name Format Verification");
		//Production Bug_7783_TC_1
		try {
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7783_1_id_DashBoardModule"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7783_1_id_DashboardPage"), "xpath");
			//To select the account displayed
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7783_1_id_top_clients_By_comm_tab"), "xpath");
			Thread.sleep(3000);
			List<WebElement> HeadersList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7783_1_id_TableHeaders")));
			 WebElement AccNameHeadPos = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7783_1_id_AccountNamePosition")));
			 int AccNameIndex = HeadersList.indexOf(AccNameHeadPos);
			List<WebElement> accountList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7783_1_id_top_clientLists_By_comm_displayed_in_table").replace("temp", ""+(AccNameIndex+1) +"")));
			for (int i = 0; i < accountList.size(); i++) {
				String accName = accountList.get(i).getText();
				accountList.get(i).click();
				
				//click on coverage tab
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7783_1_id_Coverage_tab"), "xpath");
				if (!(SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7783_1_id_NoData"), "xpath")).contains("No Data Available")) {
					int nameListCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7783_1_id_PersonName"))).size();
				//To get accNameList
				for (int j = 1; j <=nameListCount ; j++) {
					String covNameList = SeleniumUtils.getTextfromField("(" + ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7783_1_id_PersonName") + ")[" + j + "]", "xpath");
					if (!covNameList.contains(",")) {
					//System.out.println("pass");	
					SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accName+" -Coverage-Employee name format "+ covNameList +" is displaying correctly",ExtentColor.GREEN));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7783_WebElements"),0));
					} else {
						//System.out.println("fail");
						SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(accName+" -Coverage-Employee name format " + covNameList +" is not displaying correctly",ExtentColor.RED));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7783_WebElements"),1));
					}
				}
			
				}
			else {
					//System.out.println("No table");
					SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel(accName+" this Account doesn't have Employee Coverage",ExtentColor.TEAL));
				}
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7783_1_id_id_close_button_1"), "xpath");
			}
			
			
}catch (Exception e) {
	e.printStackTrace();
}
	}

}
