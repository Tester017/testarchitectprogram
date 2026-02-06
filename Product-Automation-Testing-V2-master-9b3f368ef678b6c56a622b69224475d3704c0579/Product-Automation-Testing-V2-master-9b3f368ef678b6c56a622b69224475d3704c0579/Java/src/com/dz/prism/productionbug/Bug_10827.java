package com.dz.prism.productionbug;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_10827 {
	public static void my_List_Page_Refresh_Verification() {
		try {
			//Production Bug_10827_TC_894  -  Production Bug_10827_TC_900
			SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_10827 Page Refresh Verification");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			//110 url
			//click on my list module
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10827_1_id_MyListModule"), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10827_1_id_MyListModule"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10827_1_id_MyListModule"), "xpath");
			//28 URL
			/*SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10827_1_id_MyListModule28"), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10827_1_id_MyListModule28"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10827_1_id_MyListModule28"), "xpath");*/
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			//get the text available in the page
			String pageText = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10827_1_id_MyListPage"), "xpath");
			//get the window ID
			String windowID = SeleniumUtils.webDriver.getWindowHandle();
			Thread.sleep(10000);
			SeleniumUtils.webDriver.navigate().refresh();
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			Thread.sleep(5000);
			//get the text after refreshing the page
			String refreshPageText = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10827_1_id_MyListPage"), "xpath");
			//get the window ID after refreshing
			String refreshWindowID = SeleniumUtils.webDriver.getWindowHandle();
			if (pageText.equals(refreshPageText) && windowID.equals(refreshWindowID)) {
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Page is in same Module after refreshing the page",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10827_WebElements"),0));
			} else {
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Page is not in the Same Module after refreshing the page",ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10827_WebElements"),1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
}
