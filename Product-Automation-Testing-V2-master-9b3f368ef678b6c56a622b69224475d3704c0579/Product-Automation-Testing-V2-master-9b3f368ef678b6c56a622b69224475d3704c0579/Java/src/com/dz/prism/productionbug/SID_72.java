package com.dz.prism.productionbug;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class SID_72 {
	public static void check_ytd_Dropdown_List() {
		SeleniumUtils.parentTest= SeleniumUtils.testCase.createNode("SID_72 - Account tearsheet - NaN Verification in Date Filter");
		String date = 	SeleniumUtils.getCurrDate();
		//System.out.println(date);
		String[] split = date.split("_");
		for (String x : split) {
			//System.out.println(x);	
		}
		String[] dateSplit = split[0].split("-");
		
		
		try {
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_72_1_id_AppPage"), "xpath");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_72_1_id_DashBoardModule"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_72_1_id_AppPage"), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("PB_7391_2_id_top_clients_By_comm_tab"), "xpath");
			Thread.sleep(3000);
			List<WebElement> HeadersList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_72_1_id_TableHeaders")));
			 WebElement AccNameHeadPos = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_72_1_id_AccountNamePosition")));
			 int AccNameIndex = HeadersList.indexOf(AccNameHeadPos);
			List<WebElement> accountList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("PB_7391_2_id_top_clientLists_By_comm_displayed_in_table").replace("temp", ""+(AccNameIndex+1) +"")));
			for (int i = 0; i < accountList.size(); i++) {
				String accName = accountList.get(i).getText();
				accountList.get(i).click();
				//clicking on YTD filter
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_72_1_id_YTD_path"), "xpath");
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_72_1_id_YTD_path"), "xpath");
				List<WebElement> ytd_Filter= SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_72_1_id_YTD_Dropdown_path")));
				int size = ytd_Filter.size();
				//System.out.println(size);
				List<WebElement> calDropDownVal = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_72_1_id_YTD_Dropdown_path")+"["+size+"]/li"));
				for (int j = 0; j < calDropDownVal.size(); j++) {
					String text = calDropDownVal.get(j).getText();
					//System.out.println(text);
											
					if(text.equals((String.valueOf((Integer.parseInt(dateSplit[1]))-1))+"/"+dateSplit[2]+"/"+dateSplit[0])) {
						SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accName+" account got opened and checked for particular date and Date Filter is matched",ExtentColor.GREEN));			 
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_72_webElements"),0));
					
					}
					
					else if(text.equals("YTD "+dateSplit[0])) {
						SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accName+ " account got opened and checked for YTD year Date Filter YTD year is matched",ExtentColor.GREEN));			 
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_72_webElements"),0));
					
					 }
					 
					else if(text.equals(String.valueOf((Integer.parseInt(dateSplit[0])-1)))) {
						SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accName+" account got opened and checked for previous Year and Date Filter is matched",ExtentColor.GREEN));			 
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_72_webElements"),0));
					
					 }
					else if(text.equals("MTD")) {
						SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accName+" account got opened and checked for MTD value and Date Filter is matched",ExtentColor.GREEN));			 
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_72_webElements"),0));
					
					 }
					
					else{
						SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(accName+" account gets opened and Date Filter is mismatched - "+text,ExtentColor.RED));
						SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_72_webElements"),1));
					
					}
				}
				
				
				
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("PB_7391_2_id_close_button_1"), "xpath");
	}}
			catch (Exception e) {
				e.printStackTrace();
			}
}
}