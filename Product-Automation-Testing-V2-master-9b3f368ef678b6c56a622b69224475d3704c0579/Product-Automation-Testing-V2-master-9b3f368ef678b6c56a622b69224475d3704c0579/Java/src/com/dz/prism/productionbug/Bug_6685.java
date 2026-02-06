package com.dz.prism.productionbug;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_6685 {
	public static void Read_Excel(Properties PRODUCTIONBUGPROP) throws Exception {
		SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bug_6685 Page Load check for Contact Tear Sheet");
		// Read excel file
		Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+ ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_v_test_case_path"));
		for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
			List<Map<String, String>> innerRows = testRows.getValue();
			for (Map<String, String> values : innerRows) {
				page_Load_Check(values);	
			}
		}
	}	//Production Bug_6685_TC_926 - Production Bug_6685_TC_949
	public static void page_Load_Check(Map<String, String> values) throws Exception {
		try {
		String contactName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_v_Contact_Name"));
		String contactEmail = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_v_Contact_Email"));
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_global_Search"), "id");
		//Set the value to the global search
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_global_Search"), contactName, "id");
		int searchListFilterCount = SeleniumUtils.getCountOfDropdownList(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_FilterCountList"), "xpath");
        //deselecting selected filter
		for(int i=1;i<=searchListFilterCount ;i++){
			Thread.sleep(500);
            boolean isSelected = SeleniumUtils.checkBoxIsSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_FilterSelectedCheck").replace("temp","" + i + ""), "xpath");
            if(isSelected==true){
            	Thread.sleep(500);
            	SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_FilterDeselect").replace("temp","" + i + ""), "xpath");
                SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_FilterDeselect").replace("temp","" + i + ""),"xpath");
            }
        }
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_ContactFilter"), "xpath");
		//click on contact check box
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_ContactFilter"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_SearchList"), "xpath");
		Thread.sleep(500);
		//get the search list
		String searchList = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_SearchList"), "xpath");
		if (!searchList.equals("No results found for '"+contactName+"'")) {
			int size = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_SearchListDisplayed"))).size();
			List<String>l=new ArrayList<String>();
			for (int i = 1; i <= size; i++) {
				String emailId = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_SearchListDisplayed")+"["+ i + "]", "xpath");
				l.add(emailId);
			}
			if(l.contains(contactEmail)) {
				SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_ContactSelect").replace("temp", "" + contactEmail + ""), "xpath");
				//click on required contact
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_ContactSelect").replace("temp", "" + contactEmail + ""), "xpath");
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_BurgerButton"), "xpath");
				//click on burger button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_BurgerButton"), "xpath");
				//click on edit button
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_EditButton"), "xpath");
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_EditButton"), "xpath");
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_EditPage"), "xpath");
				//get the text from edit page
				String edit_Page_Text = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_EditPage"), "xpath");
				if (edit_Page_Text.equals("Edit Contact")) {
					SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Edit page opened",ExtentColor.GREEN));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_WebElements"),1));
				} else {
					SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Edit page not opened",ExtentColor.RED));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_WebElements"),1));
				}
				//click on edit close button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_EditCloseButton"), "id");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_EditCloseCnfmButton"), "id");
				//click on edit close confirm button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_EditCloseCnfmButton"), "id");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_BurgerButton"), "xpath");
				//click on burger button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_BurgerButton"), "xpath");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_SetCommunicationFrequencyBtn"), "xpath");
				//click on set communication frequency button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_SetCommunicationFrequencyBtn"), "xpath");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_SetCommFreqPage"), "xpath");
				//get the text from set communication page
				String set_Frequency_Page = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_SetCommFreqPage"), "xpath");
				if (set_Frequency_Page.equals("Set Communication Frequency")) {
					SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Set Communication Frequency page opened",ExtentColor.GREEN));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_WebElements"),0));
				} else {
					SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Set Communication Frequency page not opened",ExtentColor.RED));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_WebElements"),1));
				}
				//click on set communication close button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_SetCommFreqPageCloseButton"), "xpath");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_TearSheetCloseButton"), "xpath");
				//click on contact tear sheet close button
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_1_id_TearSheetCloseButton"), "xpath");
			}else {
				SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("No contact with the given mail is displayed for the given search input",ExtentColor.TEAL));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_WebElements"),1));
			}
		} else if(searchList.equals("No results found for '"+contactName+"'")){
			SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("No result for the given contact is displayed for the given search input",ExtentColor.TEAL));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_6685_WebElements"),1));
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}

}
