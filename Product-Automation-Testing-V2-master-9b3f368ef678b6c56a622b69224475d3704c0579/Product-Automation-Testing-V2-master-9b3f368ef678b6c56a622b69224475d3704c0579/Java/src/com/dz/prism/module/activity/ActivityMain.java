package com.dz.prism.module.activity;

import java.io.IOException;
import java.util.Properties;

import com.dz.prism.main.AutomationDriver;
import com.dz.prism.utils.SeleniumUtils;

public class ActivityMain {
	public static Properties ACTIVITYPROP  = null;
	public static final String moduleName = "Activity";
	static{
		try {
			ACTIVITYPROP = SeleniumUtils.getConfigProprty("\\ModuleConfigurations\\activity.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public ActivityMain(){
		if(ACTIVITYPROP != null){
			callTestCases();
		} else {
			System.out.println("Activity properties file not loadded please verify");
		}
	}
	private void callTestCases(){
		try{
			if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
				AddInteraction_AI_1.initiateFunctionality();
				System.out.println("Add Interaction Executed Successfully ");
			}
			 
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
