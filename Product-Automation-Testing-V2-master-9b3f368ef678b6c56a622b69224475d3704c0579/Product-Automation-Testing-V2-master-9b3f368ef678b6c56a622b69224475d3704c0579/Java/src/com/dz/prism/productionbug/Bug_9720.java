package com.dz.prism.productionbug;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_9720 {
	public static void contact_Tearsheet_Title_Verification(){
		try {
			SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_9720 Contact Tear Sheet Title Verification");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ActivityModule"), "xpath");
			//Click on activity module
			WebElement ActivityModule = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ActivityModule")));
			Actions a=new Actions(SeleniumUtils.webDriver);
			a.moveToElement(ActivityModule).perform();
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ActivitySubModule"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ColumnVisibilityButton"), "xpath");
			//click on column visibility button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ColumnVisibilityButton"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ColumnList"), "xpath");
			//column count
			int ColumnListCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ColumnList"))).size();
			//Deselect column
			for (int i = 1; i <=ColumnListCount; i++) {
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ColumnList")+"[" + i + "]", "xpath");
				boolean selected = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ColumnList")+"[" + i + "]")).isSelected();
				if (selected==true) {
					SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ColumnList")+"[" + i + "]", "xpath");
					SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ColumnList")+"[" + i + "]", "xpath");
					SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				}
			}
			//click on Contact name column
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactNameSel"), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactNameSel"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactNameSel"), "xpath");
			//click on Contact title column
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactTitle"), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactTitle"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactTitle"), "xpath");
			//click on column visibility button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ColumnVisibilityButton"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			//To get the table header list
			List<WebElement> HeaderList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_TableHeaderList")));
			//To get the Contact name in header
			WebElement HeadContactName = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_HeadContactName")));
			//to get the contact title in header
			WebElement HeadContactTitle = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_HeadContactTitle")));
			//Contact name index position
			int ContactNameIndex = HeaderList.indexOf(HeadContactName);
			//contact title index position
			int ContactTitleIndex = HeaderList.indexOf(HeadContactTitle);
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_TableData"), "xpath");
			//To get the text from the table
			String textfromField = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_TableData"), "xpath");
			if (!textfromField.equals("No data available in table")) {
			//To get the row size
			int size = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactDisplayedList").replace("index", "" + (ContactNameIndex+1) + ""))).size();
			if (size<=20) {
				for (int i = 1; i <= size; i++) {
					SeleniumUtils.waitUntilElementHide("loading_screen", "id");
					SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_TableData"), "xpath");
					SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactNameSelect").replace("index", "" + (ContactNameIndex+1) + "").replace("temp", ""+ i + ""), "xpath");
					//To get the contact name
					String ContactName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactNameSelect").replace("index", "" + (ContactNameIndex+1) + "").replace("temp", ""+ i + ""), "xpath");
					//to get the contact title
					String ContactTitle = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_TableContactTitle").replace("index", "" + (ContactTitleIndex+1) + "").replace("temp", ""+ i + ""), "title", "xpath");
					//click on contact name
					SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactNameSelect").replace("index", "" + (ContactNameIndex+1) + "").replace("temp", ""+ i + ""), "xpath");
					Thread.sleep(2000);
					SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactNameSelect").replace("index", "" + (ContactNameIndex+1) + "").replace("temp", ""+ i + ""), "xpath");
					SeleniumUtils.waitUntilElementHide("loading_screen", "id");
					SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactTearSheet"), "id");
					//To get whether the title is present or not
					boolean isTitlePanelExist = SeleniumUtils.isElementExist("xpath", "//div[@id='search_header_slide']//div[@class='titlepanel']", 2);
					if (isTitlePanelExist==true && !ContactTitle.equals("")) {
						String CTContactTitle = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_CTContactTitle"), "xpath");
						if (ContactTitle.equals(CTContactTitle)) {
							SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(ContactName+" - has Contact title - "+ContactTitle+" and the title is present in Contact Tear sheet",ExtentColor.GREEN));
							SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_WebElements"),0));
						} else {
							SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(ContactName+" - has Contact title and the title is not present in Contact Tear sheet",ExtentColor.RED));
							SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_WebElements"),1));
						}
					}else if (isTitlePanelExist==false && ContactTitle.equals("")) {
						SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(ContactName+" - has no Contact title and the title is not present in Contact Tear sheet",ExtentColor.GREEN));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_WebElements"),0));
					}else {
						SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(ContactName+" - has Contact title and the title is not present in Contact Tear sheet",ExtentColor.RED));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_WebElements"),1));
					}
					//click on contact close button
					SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactTearSheetClose"), "xpath");
					SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactTearSheetClose"), "xpath");
				}
			} //if size is more than 20 rows
			else {
				for (int i = 1; i <= 20; i++) {
					SeleniumUtils.waitUntilElementHide("loading_screen", "id");
					SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_TableData"), "xpath");
					SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactNameSelect").replace("index", "" + (ContactNameIndex+1) + "").replace("temp", ""+ i + ""), "xpath");
					//To get the contact name
					String ContactName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactNameSelect").replace("index", "" + (ContactNameIndex+1) + "").replace("temp", ""+ i + ""), "xpath");
					//to get the contact title
					String ContactTitle = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_TableContactTitle").replace("index", "" + (ContactTitleIndex+1) + "").replace("temp", ""+ i + ""), "title", "xpath");
					//click on contact name
					SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactNameSelect").replace("index", "" + (ContactNameIndex+1) + "").replace("temp", ""+ i + ""), "xpath");
					Thread.sleep(2000);
					SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactNameSelect").replace("index", "" + (ContactNameIndex+1) + "").replace("temp", ""+ i + ""), "xpath");
					SeleniumUtils.waitUntilElementHide("loading_screen", "id");
					SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactTearSheet"), "id");
					//To get whether the title is present or not
					boolean isTitlePanelExist = SeleniumUtils.isElementExist("xpath", ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_TitlePath"), 2);
					if (isTitlePanelExist==true && !ContactTitle.equals("")) {
						String CTContactTitle = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_CTContactTitle"), "xpath");
						if (ContactTitle.equals(CTContactTitle)) {
							SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(ContactName+" - has Contact title - "+ContactTitle+" and the title is present in Contact Tear sheet",ExtentColor.GREEN));
							SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_WebElements"),0));
						} else {
							SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(ContactName+" - has Contact title and the title is not present in Contact Tear sheet",ExtentColor.RED));
							SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_WebElements"),1));
						}
					}else if (isTitlePanelExist==false && ContactTitle.equals("")) {
						SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(ContactName+" - has no Contact title and the title is not present in Contact Tear sheet",ExtentColor.GREEN));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_WebElements"),0));
					}else {
						SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(ContactName+" - has Contact title and the title is not present in Contact Tear sheet",ExtentColor.RED));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_WebElements"),1));
					}
					//click on contact close button
					SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactTearSheetClose"), "xpath");
					SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_1_id_ContactTearSheetClose"), "xpath");
				}
			}
			}else {
				SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("No data available in Table",ExtentColor.TEAL));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9720_WebElements"),1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
