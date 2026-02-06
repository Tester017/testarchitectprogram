package com.dz.prism.module.feedback;

import java.io.IOException;
import java.util.Properties;

import com.dz.prism.main.AutomationDriver;
import com.dz.prism.utils.SeleniumUtils;

public class FeedbackMain {

	public static Properties FEEDBACKPROP = null;
	public static final String moduleName = "FeedbackMain";

	static {
		// Call for Commision tiles check
		try {
			FEEDBACKPROP = SeleniumUtils.getConfigProprty("\\ModuleConfigurations\\feedback.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public FeedbackMain() {
		if (FEEDBACKPROP != null) {
			callTestCases();
		} else {
			System.out.println("FeedBack properties file not loadded please verify");
		}
	}

	private void callTestCases() {
		try {
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Feedback_FB_1.ReadExcel();
				System.out.println("Executed Feedback Tests");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
