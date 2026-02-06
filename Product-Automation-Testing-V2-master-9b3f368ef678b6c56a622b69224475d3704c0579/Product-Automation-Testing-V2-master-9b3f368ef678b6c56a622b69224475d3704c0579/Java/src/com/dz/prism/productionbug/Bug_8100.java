package com.dz.prism.productionbug;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;
import com.google.common.collect.Range;

public class Bug_8100 {
	public static void Previous_YTD_Verification() throws Exception {
		try {
		SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_8100 YTD vs.Prev.Year and Previous full year value verification");
		//scroll to top 10 account displayed 
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_DashBoardModule"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_DashboardPage"), "xpath");
		SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_TopClientsByComm"), "xpath");
		Thread.sleep(3000);
		List<WebElement> HeadersList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_TableHeaders")));
		 WebElement AccNameHeadPos = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_AccountNamePosition")));
		 int AccNameIndex = HeadersList.indexOf(AccNameHeadPos);
		List<WebElement> accountList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_AccountList").replace("temp", ""+(AccNameIndex+1) +"")));
		int ProAnnVsPrevDiff;
		//Click on particular account
		for (int i = 0; i < accountList.size(); i++) {
			String accName = accountList.get(i).getText();
			accountList.get(i).click();
			
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_AccountTearSheet"), "xpath");
			//click on revenue tab
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_RevenueTab"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_ProductTab"), "xpath");
			//click on revenue by product tab
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_ProductTab"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_ProductTab"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			//click on revenue by product table
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_RevByProductTable"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_RevByProductTable"), "xpath");
			//To get the gross name
			String grossName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_GrossComm"), "xpath");
			//To get the total gross value
			String totGrossVal = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_TotalGross"), "xpath");
			//Removing special character 
			String gross = totGrossVal.replace("$", "").replace(",", "");
			//To get total previous ytd value
			String totPrevYr = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_TotalPrevYTD"), "xpath");
			//converting string to integer
			Integer PrevYTD = Integer.valueOf(totPrevYr.replace("$", "").replace(",", ""));
			//To change the value from -ve to +ve
			int abs = Math.abs(Integer.valueOf(gross)-PrevYTD);
			//To get the percentage value and rounding the values
			double per=((double)abs/PrevYTD)*100;
			int intPer=(int)Math.round(per);
			//fixing the range for percentage by increasing and decresing the value by 1
			Range<Integer> Perrange = Range.closed(intPer-1, intPer+1);
			//To get total year vs Previous year value
			String totYrVsPrevYr = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_TotalYTDvs.Prev"), "xpath");
			//converting string to integer
			Integer totYrvsPerVal = Integer.parseInt(totYrVsPrevYr.replace("$", "").replace(",", "").replace("(", "").replace(")", ""));
			//fixing the range for YTDvs.Prev by increasing and decresing the value by 5
			Range<Integer> range = Range.closed(totYrvsPerVal-5, totYrvsPerVal+5);
			//To get the percentage value from the table
			String totYrVsPrevYrPercent = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_TotalYTDvs.Prev%"), "xpath");
			//converting string to integer after removing the special character
			Integer totPer = Integer.parseInt(totYrVsPrevYrPercent.replace("(", "").replace(")", "").replace("%", ""));
			if (range.contains(abs) && Perrange.contains(totPer)) {
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accName+"-for "+grossName+"- YTDvs.Previous year value and percentage matched",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_WebElements"),0));
			} else {
                SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(accName+"-for "+grossName+"- YTDvs.Previous year value and percentage not matched",ExtentColor.RED));
                SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_WebElements"),1));
			}
			
			//To get the total Annualized commission value from table
			String totAnnComm = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_TotalAnnComm"), "xpath");
			//To get total previous full year value from table
			String totPrevFullYrComm = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_TotalPrevFullYrComm"), "xpath");
			//To get total previous full year vs Annualized comm value from table
			String totAnnCommVsPrevFullYrComm = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_AnnCommVsPrevFullYrComm"), "xpath");
			//Removing the special character and implementing the formula
			int abs1 = Math.abs(Integer.valueOf(totAnnComm.replace("$", "").replace(",", ""))-Integer.valueOf(totAnnCommVsPrevFullYrComm.replace("$", "").replace(",", "").replace("(", "").replace(")", "")));
			Integer prevYrFullVal = Integer.parseInt(totPrevFullYrComm.replace(",", "").replace("$", ""));
			//fixing the range for previous full year value
			Range<Integer> PrevFullYrRange = Range.closed(prevYrFullVal-5, prevYrFullVal+5);
			if (PrevFullYrRange.contains(abs1)) {
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accName+"-for "+grossName+"- Previous full year comm value matched",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_WebElements"),0));
			} else {
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(accName+"-for "+grossName+"- Previous full year comm value not matched",ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_WebElements"),1));
			}
			//To get the size of the product displayed in a account
			int productSize = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_ProductList"))).size();
			for (int j = 1; j <= productSize; j++) {
				//To get the product name
				String productName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_ProductList")+"[" + j + "]", "xpath");
				//To get the product gross value
				String productgross = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_ProductGross").replace("temp", "" + j +""), "xpath");
				//To get the product Previous YTD
				String proPrevYtd = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_ProductPrevYTD").replace("temp", "" + j + ""), "xpath");
				//Removing the special character
				String proPrevYTDVal = proPrevYtd.replace("$", "").replace(",", "");
				//Implementing the formula
				int Pro_abs = Math.abs(Integer.valueOf(productgross.replace("$", "").replace(",", ""))-Integer.valueOf(proPrevYTDVal));
				//To get the Percentage value by using the formula and rounding the value
				double Product_per=((double)Pro_abs/Integer.valueOf(proPrevYTDVal))*100;
				int Product_Percent=(int)Math.round(Product_per);
				//To get product percentage value from the table
				String ProductPercent = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_ProductYTDvs.Prev%").replace("temp", "" + j +""), "xpath");
				String pro_Per_Val = ProductPercent.replace("(", "").replace(")", "").replace("%", "");
				//Fixing the range for Product Previous Yearvs YTD percentage 
				Range<Integer> productPrevYrPerRange = Range.closed(Product_Percent-1, Product_Percent+1);
				//To get the Product previous vs Ytd value from table
				String Pro_Prev_Vs_Ytd = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_ProductYTDvs.Prev").replace("temp", "" + j +""), "xpath");
				//Converting String to integer
				Integer ProPrevsYtdVal = Integer.valueOf(Pro_Prev_Vs_Ytd.replace("$", "").replace(",", "").replace("(", "").replace(")", ""));
				//fixing the range for product previous year vs YTD
				Range<Integer> productPrevVsYtdRange = Range.closed(ProPrevsYtdVal-5, ProPrevsYtdVal+5);
				if (productPrevVsYtdRange.contains(Pro_abs) && productPrevYrPerRange.contains(Integer.valueOf(pro_Per_Val))) {
					SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accName+"-for "+productName+"- product YTD vs previous year value and percentage matched",ExtentColor.GREEN));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_WebElements"),0));
				} else {
					SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(accName+"-for "+productName+"- product YTD vs previous year value and percentage not matched",ExtentColor.RED));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_WebElements"),1));
				}
				//To get prduct Annualized commission
				String proAnn = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_ProductAnnComm").replace("temp", "" + j + ""), "xpath");
				//Removing special characters
				Integer ProAnnComm = Integer.valueOf(proAnn.replace("$", "").replace(",", ""));
				//To get the colour of the values whether it is black or red 
				String attributefromField = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_ProductAnnCommVsPrevFullYr").replace("temp", "" + j + ""), "class", "xpath");
				//To get the Product Annualized commission vs previous year value
				String ProAnnCommVsPrevYr = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_ProductAnnCommVsPrevFullYr").replace("temp", "" + j + ""), "xpath");
				//converting string to integer
				Integer ProAnnCommVsPrevYrVal = Integer.valueOf(ProAnnCommVsPrevYr.replace("$", "").replace(",", "").replace("(", "").replace(")", ""));
				//Add the values if table value is red in color format and minus the value if table value is not in red colour format 
				if (attributefromField.equalsIgnoreCase("numFormatRed")) {
					 ProAnnVsPrevDiff = Math.abs(ProAnnComm+ProAnnCommVsPrevYrVal);
				} else {
					 ProAnnVsPrevDiff = Math.abs(ProAnnComm-ProAnnCommVsPrevYrVal);
				}
				//To get Product previous full year commission
				String ProPrevYr = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_ProductPrevFullYrComm").replace("temp", "" + j + ""), "xpath");
				//Removing the special character and converting string to integer
				Integer ProPrevYrVal = Integer.valueOf(ProPrevYr.replace("$", "").replace(",", ""));
				//fixing the range for previous year value
				Range<Integer> ProPrevFullYrRange = Range.closed(ProPrevYrVal-5, ProPrevYrVal+5);
				if (ProPrevFullYrRange.contains(ProAnnVsPrevDiff)) {
					SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accName+"-for "+productName+"- Product Previous Year value matched",ExtentColor.GREEN));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_WebElements"),0));
				} else {
					SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(accName+"-for "+productName+"- Product Previous Year value not matched",ExtentColor.RED));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_WebElements"),1));
				}	
			}
			//scroll up to the panel page
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_PanelHead"), "id");
			Robot r=new Robot();
			r.keyPress(KeyEvent.VK_UP);
			r.keyRelease(KeyEvent.VK_UP);
			//click on account close button
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_8100_1_id_AccountCloseButton"), "xpath");
			
		}	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
