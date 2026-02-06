package com.dz.prism.productionbug;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_9996 {
	public static void ReadExcel() throws IOException {
    	
        Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(
                SeleniumUtils.UserDirVar + ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9996_1_v_test_case_path"));
        SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_9996 '&' symbol name search verification");

        for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
            List<Map<String, String>> innerRows = testRows.getValue();
            for (Map<String, String> values : innerRows) {
            	special_Character_Search(values);
            }
        }
    }
	public static void special_Character_Search(Map<String, String> values) {
		try {
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9996_1_id_ReportsModule"), "xpath");
			//Click on Report module
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9996_1_id_ReportsModule"), "xpath");
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			//To get the report type from excel
			String ReportType = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9996_1_v_Report_Type"));
			//To select the report type
			SeleniumUtils.dropDownItemSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9996_1_id_ReportType"), ReportType, "visibiletext", "id");
			//get the name from excel
			String NameSearch = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9996_1_v_Name_Search"));
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9996_1_id_SectotInput"), "xpath");
			//set the excel name to the filed
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9996_1_id_SectotInput"), NameSearch, "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9996_1_id_DropdownValues"), "xpath");
			//To get the list names
			String NameList = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9996_1_id_DropdownValues"), "innerHTML", "xpath");
			if (NameList.contains("li")) {
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The field searched with Special character '&' and the List is displayed",ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9996_WebElements"),0));
			} else {
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The field searched with Special character '&' and the List is not displayed",ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9996_WebElements"),1));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
