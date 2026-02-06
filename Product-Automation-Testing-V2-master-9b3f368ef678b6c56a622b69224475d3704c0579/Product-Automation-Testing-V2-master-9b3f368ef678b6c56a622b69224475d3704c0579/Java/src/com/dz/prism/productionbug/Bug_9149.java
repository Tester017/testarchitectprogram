package com.dz.prism.productionbug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;
import com.google.common.primitives.Doubles;

public class Bug_9149 {
	public static void revenue_Account_Gross_Comm_Verification() {
		try {
			SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_9149-Revenue Account Gross Commission Verification");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9149_1_id_DashBoardPage"), "xpath");
			//click on revenue sub tab in revenue module
			WebElement rev = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9149_1_id_RevenueModule")));
			Actions s= new Actions(SeleniumUtils.webDriver);
			s.moveToElement(rev).perform();
			WebElement subrev = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9149_1_id_RevenueSubModule")));
			subrev.click();
			SeleniumUtils.webDriver.navigate().refresh();
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9149_1_id_RevenuePage"), "xpath");
			//To the count of account displayed in revenue by account tab
			int size = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9149_1_id_GrossCommissionList"))).size();
			List<Double>l=new ArrayList<Double>();
			for (int i =size; i >0 ; i--) {
				//To get the gross commission value
				String attributefromField = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9149_1_id_GrossCommissionList")+"[" + i + "]", "data-order", "xpath");
				Double valueOf = Double.valueOf(attributefromField);
				l.add(valueOf);
			}
			double[] array = Doubles.toArray(l);
			Arrays.sort(array);
			for (int i = 0; i < size; i++) {
				if (l.get(i).equals(array[i])) {
					//System.out.println("pass");
					SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Gross Commission Values in Revenue by Account table Displaying Correctly -"+(l.get(i)),ExtentColor.GREEN));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9149_WebElements"),0));
				} else {
					//System.out.println("fail");
					SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Gross Commission Values in Revenue by Account table Not Displaying Correctly -"+(l.get(i))+" & "+array[i],ExtentColor.RED));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9149_WebElements"),1));
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
