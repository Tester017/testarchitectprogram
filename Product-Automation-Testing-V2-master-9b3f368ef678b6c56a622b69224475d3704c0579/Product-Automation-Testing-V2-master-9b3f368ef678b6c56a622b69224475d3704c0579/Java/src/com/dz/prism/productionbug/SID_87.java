package com.dz.prism.productionbug;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class SID_87 {
	public static void activity() throws Exception {
		SeleniumUtils.parentTest= SeleniumUtils.testCase.createNode("SID_87 - Interaction-Activity type Comparison");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		//click on add icon
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_87_1_id_add_button"), "xpath");
		//click on add interaction tab
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_87_1_id_add_interaction_button"), "xpath");
		
		//To get dropdown values from activity type 
			//To find the count of Add Interaction dropdown
		int count_Of_Addinteraction_DropdownList = SeleniumUtils.getCountOfDropdownList(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_87_1_id_activity_dropdown"), "xpath");
		//System.out.println("add interaction----"+count_Of_Addinteraction_DropdownList);
	
		//Storing the dropdown values in list
		List<String> addIntDropdownVal = new ArrayList<>();
		
		for (int i = 1; i<=count_Of_Addinteraction_DropdownList; i++) {
			String addIntraction_text = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_87_1_id_activity_dropdown")+"["+i+"]", "xpath");
			addIntDropdownVal.add(addIntraction_text);
			
		}
		//closing Add Interaction dialog 
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_87_1_id_add_interaction_close_button"), "xpath");
		//click on Activity module
		//SeleniumUtils.tabSelection("Activity", "Activity");
		WebElement Activity = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_87_1_id_ActivityModule")));
		Actions a=new Actions(SeleniumUtils.webDriver);
		a.moveToElement(Activity).build().perform();
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_87_1_id_ActivitySubModule"), "xpath");
		//clicking on filter button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_87_1_id_activity_filter_button"), "xpath");
		SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_87_1_id_activity_List"), "xpath");
		//clicking on activity mode
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_87_1_id_activity_list_typebox") ,"xpath");
	  
	  //Row Count 
	  int count_Of_Activity_Mode_DropdownList = SeleniumUtils.getCountOfDropdownList(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_87_1_id_activity_list_dropdown"), "xpath");
	  //System.out.println("Activity count--->"+count_Of_Activity_Mode_DropdownList);
	  List<String> activityDropdownVal = new ArrayList<>();
	  
	  for (int i = 0; i <= count_Of_Activity_Mode_DropdownList; i++) {
		String activity_text = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("SID_87_1_id_activity_list_dropdown")+"["+i+"]", "xpath");
		
		activityDropdownVal.add(activity_text);
			
	}
	  
	 /* if(count_Of_Addinteraction_DropdownList==count_Of_Activity_Mode_DropdownList) {
		  SeleniumUtils.testCase.log(Status.PASS,MarkupHelper.createLabel("Activity type count in Add Interaction Page is matched with show filter in Activity tab",ExtentColor.GREEN));			 
			SeleniumUtils.testCase.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_87_webElements"),0));  
	  }else {
		  SeleniumUtils.testCase.log(Status.FAIL,MarkupHelper.createLabel("Activity type count in Add Interaction Page is mismatched with show filter in Activity tab",ExtentColor.RED));
			SeleniumUtils.testCase.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_87_webElements"),1)); 
	  }*/
	  
	  if(activityDropdownVal.containsAll(addIntDropdownVal)) {
		  //System.out.println("pass");
		  SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Activity type values in Add Interaction Page is matched with show filter in Activity tab",ExtentColor.GREEN));			 
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_87_webElements"),0));
	  }else {
		  //System.out.println("fail");
			SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Activity type values in Add Interaction Page is mismatched with show filter in Activity tab",ExtentColor.RED));
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Sidoti_87_webElements"),1));
	  }
	  
	}

}
