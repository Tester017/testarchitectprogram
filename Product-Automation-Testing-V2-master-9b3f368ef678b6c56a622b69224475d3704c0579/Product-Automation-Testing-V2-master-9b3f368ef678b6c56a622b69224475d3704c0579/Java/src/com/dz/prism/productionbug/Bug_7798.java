package com.dz.prism.productionbug;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_7798 {
	
	public static void research_Subcription() throws Exception {
		SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_7798 Research Subcription contact verification");
		Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+ ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7798_1_v_test_case_path"));
		for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
			List<Map<String, String>> innerRows = testRows.getValue();
			
			for (Map<String, String> values : innerRows) {
				
				research_Subcription_Verification(values);
				
			}
		}
	}

	public static void research_Subcription_Verification(Map<String, String> values) throws Exception {
		try {
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.tabSelection("Research", "Research Subscription");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7798_1_id_ResearchPanel"), "id");
		//get the values from excel
		String Email = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7798_1_v_Contact_Email"));
		if(Email.contains("~")) {
		String[] split = Email.split("~");
		for (int i = 0; i < split.length; i++) {
			String SplitEmail = split[i];
			Thread.sleep(2000);
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7798_1_id_ContactSearch"), SplitEmail, "id");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7798_1_id_ContactSel").replace("temp", "" + SplitEmail +""), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		}
		//To select the checkbox
		for (int i = 0; i < split.length-1; i++) {
			String SplitEmail1 = split[i];
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7798_1_id_CheckBox").replace("temp", "" + SplitEmail1 +""), "xpath");
		}
		
		}else {
			
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7798_1_id_ContactSearch"), Email, "id");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7798_1_id_ContactSel").replace("temp", "" + Email +""), "xpath");
			
			
			
		}
		//count of dropdown
		int countOfDropdownList = SeleniumUtils.getCountOfDropdownList(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7798_1_id_InputcriteriaList"), "xpath");
		//System.out.println("Dropdown count--->"+countOfDropdownList);
		//dropdown values stored in list
		List<String>s=new ArrayList<String>();
	for (int i = 1; i <= countOfDropdownList; i++) {
		String textfromField = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7798_1_id_InputcriteriaList")+"["+i+"]", "xpath");
		//System.out.println(textfromField);
		s.add(textfromField);
	}
	//System.out.println(s);
		if (s.contains("Covered Ticker") && s.contains("Sector") && s.contains("Senior Analyst")) {
			//System.out.println("pass");
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(Email.replace("~", ",") +" this contact have the dropdown values under criteria",ExtentColor.GREEN));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7798_WebElements"),0));
		} else {
			//System.out.println("fail");
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(Email.replace("~", ",") +" this contact doesn't have the dropdown values under criteria",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7798_WebElements"),1));
		}
		//SeleniumUtils.webDriver.navigate().refresh();
		}catch (Exception e) {
		e.printStackTrace();	
		}
	}
	}

