package com.dz.prism.productionbug;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_10575 {
	public static void product_Duplicates_Verify_In_Rev_By_Product_Table() {
		try {
			SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_10575 Verification of Product Duplicates In Revenue By Product Table");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			//click on revenue module
			//110 URL
			/*SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_1_id_RevenueModule"), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_1_id_RevenueModule"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_1_id_RevenueModule"), "xpath");*/
			//28 URL
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_1_id_RevenueModule"), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_1_id_RevenueModule"), "xpath");
			WebElement RevenueModule = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_1_id_RevenueModule")));
			Actions a=new Actions(SeleniumUtils.webDriver);
			a.moveToElement(RevenueModule).perform();
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_1_id_RevenueSubModule"), "xpath");
			//click on revenue sub module
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_1_id_RevenueSubModule"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			boolean errorMessage = SeleniumUtils.isElementExist("xpath", ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_1_id_UnableToFetchData"), 3);
			if (errorMessage!=true) {
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_1_id_RevenueByProductTab"), "xpath");
				//click on Revenue by product tab
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_1_id_RevenueByProductTab"), "xpath");
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				boolean proList = SeleniumUtils.isElementExist("xpath", ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_1_id_ProductLists"), 3);
				if (proList==true) {
					int ProductsCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_1_id_ProductLists"))).size();
					List<String> ProductList=new ArrayList<String>();
					//To get the product list
					for (int i = 1; i <= ProductsCount; i++) {
						String ProductName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_1_id_ProductLists")+"["+ i +"]", "xpath");
						ProductList.add(ProductName);
					}
					Map<String, Integer> m=new LinkedHashMap<String,Integer>();
					for (String x : ProductList) {
						if(m.containsKey(x)) {
							Integer v = m.get(x);
							m.put(x, v+1);
							SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Product - "+ x +" in Revenue By Product Table is displaying multiple times",ExtentColor.RED));
							SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_WebElements"),1));
						}else {
							m.put(x, 1);
							SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Product - "+ x +" in Revenue By Product Table is not displaying multiple times",ExtentColor.GREEN));
							SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_WebElements"),0));
						}
					}
				} else {
					SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("No Products Available",ExtentColor.TEAL));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_10575_WebElements"),1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
