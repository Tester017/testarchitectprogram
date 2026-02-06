package com.dz.prism.productionbug;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_8075 {
	public static void clock_View_Verification() throws Exception {
		try {
		SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_8075 clock view Verification");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		//click on my list tab
		Actions a =new Actions(SeleniumUtils.webDriver);
		WebElement myListModule = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_MyListModule")));
		a.moveToElement(myListModule).build().perform();
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_MyListV3Module"), "xpath");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_Lists").replace("[temp]", ""), "xpath");
		
		for (int i = 1; i <= 10; i++) {
		//To get the list name
		String ListName = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_Lists").replace("temp", ""+ i +""), "data-listname", "xpath");	
		//click on the list
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_Lists").replace("temp", ""+ i +""), "xpath");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_page"), "xpath");
		Thread.sleep(4500);
		//To select all contact list check box
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_AllSelectListCheckBox"), "xpath");
		//click on bulk email tab
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_BulkMailTab"), "xpath");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_Bulk"), "id");
		//To select previously used draft
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_PreviouslyUsedDraft"), "id");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		//To select draft message button
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_DraftSelect"), "xpath");
		Thread.sleep(1500);
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_DraftSelect"), "xpath");
		// click on preview button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_PreviewButton"), "xpath");
		//SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_PreviewConfirm"), "id");
		//SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_PreviewConfirm"), "id");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		//click on send later button
		Thread.sleep(1500);
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_SendLaterButton"), "xpath");
		//click on time input
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_TimeInput"), "xpath");
		//click on am button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_TimeAMbutton"), "xpath");
		//click on pm button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_TimePMbutton"), "xpath");
		//check whether the clock is displayed or not
		boolean clock = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_Clock"))).isDisplayed();
		if (clock==true) {
			//System.out.println("pass");
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(ListName+" this List displaying clock correctly",ExtentColor.GREEN));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_WebElements"),0));
		} else {
			//System.out.println("fail");
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(ListName+" this List doesn't displaying clock correctly",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_WebElements"),1));
		}
		//click on schedule close button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_ScheduleCloseButton"), "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_Bulk"), "id");
		//click on bulk mail close button
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_BulkMailCloseButton"), "xpath");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_BulkMailCloseButton"), "xpath");
		//click on bull mail close confirm button
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_CloseButtoncnfm"), "id");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_CloseButtoncnfm"), "id");
		//click on contact close button
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_CloseContactListBtn"), "xpath");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_CloseContactListBtn"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_Toggle"), "id");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8075_1_id_Toggle"), "id");
		
		
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
