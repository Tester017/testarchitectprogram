package com.dz.prism.productionbug;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_8751 {
	static int indexOf;
	public static void account_TearSheet_Verification() {
		try {
			SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_8751 Verification of Account Opening its Own Tear Sheet format");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_ModuleList"), "id");
			//to select the revenue and revenue by sub module tab
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_RevenueModule"), "xpath");
			WebElement rev = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_RevenueModule")));
			Actions s= new Actions(SeleniumUtils.webDriver);
			s.moveToElement(rev).build().perform();
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_RevenueSubModule"), "xpath");
			//SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_RevenueModuleWithNoSubTab"), "xpath");
			Thread.sleep(5000);
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_RevenueDatas"), "xpath");
			//click on consolidated super account
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_ConsolidatedSuperAccount"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_ConsolidatedSuperAccount"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_RevenueDatas"), "xpath");
			Thread.sleep(4000);
			//SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_ActionButton").replace("/text()", ""), "xpath");
			/*WebElement element = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_ActionButton")));
			boolean displayed = element.isDisplayed();
			System.out.println(displayed);
			Actions ac=new Actions(SeleniumUtils.webDriver);
			ac.moveToElement(element).build().perform();
			element.click();*/
			//Tab List count
			List<WebElement> TabList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_TabList")));
			//Account name ctab
			WebElement AccountNameTab = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_AccountNameTab")));
			//To get Account name tab index position 
			indexOf = TabList.indexOf(AccountNameTab);
			//Three dot Button Count
			int ActionIconListsSize = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_ActionButtonList"))).size();
			List<String> a=new ArrayList<String>();
			for (int i = 1; i <= ActionIconListsSize; i++) {
				String AccountName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_AccountList").replace("temp", "" + i + "").replace("index", "" + indexOf + ""), "xpath");
				a.add(AccountName);
			}
			//SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_DashboardModule"), "xpath");
			//Click on Dashboard module
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_DashboardModule"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_DashboardPage"), "id");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_TopClientsTable"), "xpath");
			//Click on Top Clients Table Expand button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_ExpandButton"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_FullScreen"), "xpath");
			for (int i = 0; i <ActionIconListsSize ; i++) {
				String string = a.get(i);
				SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_SearchInput"), "xpath");
				//Set the value to Account filter search
				SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_SearchInput"), string, "xpath");
				//To get the table value
				String tableValue = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_SearchList"), "xpath");
				int size = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_SearchList"))).size();
				List<String> Acc =new ArrayList<String>();
					if (!tableValue.equalsIgnoreCase("No matching records found")) {
						for (int j = 1; j <=size ; j++) {
						String textfromField = SeleniumUtils.getTextfromField("("+ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_SearchList")+"/a)[" + j + "]", "xpath");
						Acc.add(textfromField);
					}}
				if (!tableValue.equalsIgnoreCase("No matching records found") && Acc.contains(string)) {
					//Click on Account name
					SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_AccountNameClick").replace("temp", "" + string + ""), "xpath");
					SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_PanelTab"), "id");
					//To get the Account head type
					String AccountHeadType = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_PanelHeadId"), "data-fixedid", "xpath");
					if (AccountHeadType.equals("SAC")) {
						//System.out.println("pass");
						SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Super Account open in its Normal Format - "+string,ExtentColor.GREEN));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_WebElements"),0));
					} else {
						//System.out.println("fail");
						SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Super Account doesn't open in its Normal Format - "+string,ExtentColor.RED));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_WebElements"),1));
					}
					//click on account close button
					SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_AccountCloseButton"), "xpath");
					SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8751_1_id_FullScreen"), "xpath");
					
				} else {
					//System.out.println("No Account found");
					SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("There is no super account found in search filter - "+string,ExtentColor.TEAL));
				}
			}
			SeleniumUtils.webDriver.navigate().refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
