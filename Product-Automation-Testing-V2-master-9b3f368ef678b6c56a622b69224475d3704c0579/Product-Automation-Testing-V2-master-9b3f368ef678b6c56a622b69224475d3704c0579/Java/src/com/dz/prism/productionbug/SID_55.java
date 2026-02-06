package com.dz.prism.productionbug;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.GlobalSearch;
import com.dz.prism.utils.SeleniumUtils;

public class SID_55 {
	public static void verify_Holdings_And_Fund(Properties PRODUCTIONBUGPROP) throws Exception {
		SeleniumUtils.parentTest= SeleniumUtils.testCase.createNode("SID_55 - Holdings and fund verification");
		// Read excel file
		Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+ ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_v_test_case_path"));
		for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
			List<Map<String, String>> innerRows = testRows.getValue();
			for (Map<String, String> values : innerRows) {
				verify_Holdings_And_Fund_using_Account(values);
				
			}
		}
	}
	public static void verify_Holdings_And_Fund_using_Account(Map<String, String> values) throws Exception {
	try {
	//get excel values
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
	String accountName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_v_From_Account_Name"));
	SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("PB_7391_2_id_top_search"), "id");
	SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("PB_7391_2_id_top_search"), accountName, "id");
	SeleniumUtils.webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	//deslecting if any list of filter is selected
	List<WebElement> filterSearchBoxes = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("PB_7391_2_id_filter_count_List")));
	
	   for(int i=1;i<=filterSearchBoxes.size() ;i++){
           boolean isSelected = SeleniumUtils.checkBoxIsSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("PB_7391_2_id_filter_Selected_check").replace("temp","" + i + ""), "xpath");
           if(isSelected==true){
        	   //Thread.sleep(1500);
        	   SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("PB_7391_2_id_filter_deselect").replace("temp","" + i + ""), "xpath");
               SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("PB_7391_2_id_filter_deselect").replace("temp","" + i + ""),"xpath");
           }
       }
	   
	   //particular filter is selected
	 Thread.sleep(2000);
	   SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("PB_7391_2_id_covAcc_filter"), "xpath");
	   SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("PB_7391_2_id_covAcc_filter"), "xpath");
	   Thread.sleep(2000);
	   String SearchList = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_account_search_list_check"), "xpath");
	if(!SearchList.equalsIgnoreCase("No results found for '"+accountName+"'")) {
	  List<WebElement> account_List = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_account_search_list")));
	//selecting particular account
	  List<String>acc=new ArrayList<String>();
	for (int i = 0; i < account_List.size();i++) {
		String accountNameList = account_List.get(i).getText();
		acc.add(accountNameList);
			if (accountNameList.equals(accountName)) {
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_account_search_list"), "xpath");
				SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_account_search_list"), "xpath");
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_account_search_list"), "xpath");             
	
		boolean arrow = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_arrow_header_slide"))).isDisplayed();
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_arrow_header_slide"), "xpath");
		if (arrow==true) {
		String attributefromField = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_arrow_header_slide"), "class", "xpath");
		if(attributefromField.contains("desc")) {
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_arrow_header_slide"), "xpath");
		}
	Thread.sleep(2000);
	WebElement tooltip_Box = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_tooltip_Box")));
	Actions a= new Actions(SeleniumUtils.webDriver);
	a.moveToElement(tooltip_Box).perform();
	int countOfDropdownList = SeleniumUtils.getCountOfDropdownList(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_tooltip_Box_list"), "xpath");
	//To find Thomson Reuters ID
	boolean TR_ID = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_tooltip_Box_list")+"["+countOfDropdownList+"]", "xpath").isEmpty();
	if (TR_ID==false) {
		
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_Holdings_tab"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_holding_by_sector"), "xpath");
		//To find Holding By sector Data
		String holBySecText = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_holding_by_sector"), "xpath");
		
		if (!holBySecText.contains("No Data Available")) {
			
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accountName+" account gets opened and have holding data",ExtentColor.GREEN));			 
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_55_webElements"),0));
		} else {
			
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(accountName+" account gets opened and doesn't have holding data",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_55_webElements"),1));
		}
		//To find Trade data
		String tradeHoldindText = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_Trades_holdings"), "xpath");
		
		if (!tradeHoldindText.contains("No Data Available")) {
			
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accountName+" account gets opened and have trade data",ExtentColor.GREEN));			 
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_55_webElements"),0));
		} else {
			
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(accountName+" account gets opened and doesn't have trade data",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_55_webElements"),1));
			
		}
		//To find inner holding data
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_inner_hoding_tab"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_holdings_positions"), "xpath");
		String innerholdingText = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_holdings_positions"), "xpath");
		
		if (!innerholdingText.contains("No Data Available")) {
			
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accountName+" account gets opened and have inner holding data",ExtentColor.GREEN));			 
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_55_webElements"),0));
			
		} else {
			
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(accountName+" account gets opened and doesn't have inner holding data",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_55_webElements"),1));
		}
		//To find equity holding data
		String equityHolText = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_equity_holding"), "xpath");
		
		if (!equityHolText.contains("No Data Available")) {
		
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accountName+" account gets opened and have equity data",ExtentColor.GREEN));			 
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_55_webElements"),0));
		} else {
			
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(accountName+" account gets opened and doesn't have equity data",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_55_webElements"),1));
		}
		//clicking on fund tab
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_Funds_tab"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_funds_list"), "xpath");
		//To find Fund data
		//Thread.sleep(3000);
		String fundListText = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_55_1_id_funds_list"), "xpath");
		
		if (!fundListText.contains("No data available in table")) {
			
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accountName+" account gets opened and have fund data",ExtentColor.GREEN));			 
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_55_webElements"),0));
		} else {
			
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(accountName+" account gets opened and doesn't have fund data",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_55_webElements"),1));
		}
	} else {
     
     SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel(accountName+" account gets opened and doesn't have Thomson Reutor value",ExtentColor.TEAL));
		SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_55_webElements"),1));
	}
		}
		else {
			//System.out.println("account is not a valid account");
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("account is not a valid account",ExtentColor.RED));
		}
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("PB_7391_2_id_close_button_1"), "xpath");
	}
	}if(!acc.contains(accountName)) {
		SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("No Account found - "+accountName,ExtentColor.TEAL));
	}
	}else {
		SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("No result for this account - "+accountName,ExtentColor.TEAL));
	}
	}catch(Exception e){
		e.printStackTrace();
	
	
	
	
	
	}
	}
}
