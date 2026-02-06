package com.dz.prism.test;

import com.codoid.products.exception.FilloException;
import com.dz.core.common.utils.Reporter;
import com.dz.core.testng.listeners.DriverListener;
import com.dz.prism.annotations.Annotations;
import com.dz.prism.functions.RevenueSteps;
import com.dz.prism.module.login.LoginMain;
import com.dz.prism.module.mylist.AccountnameWithCriteria_ML_1_25;
import com.dz.prism.utils.ExcelDataReader;
import com.dz.prism.utils.SeleniumUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Revenue extends Annotations {
	public  int reportCount=1; 
	@DataProvider(name = "RevenueInputs", parallel = false)
	public Object[] getExcelData() throws FilloException {
		DriverListener.mainWorkbook
				.set("./src/test/resources/testdata/" + SeleniumUtils.environmentName + "/revenue/Revenue_Main.xlsx");
		return ExcelDataReader.getControlData("ControlSheet", "testScenarioID");
	}

	@Test(dataProvider = "RevenueInputs")
	public void activityKeywordTest(String testScenarioID,ITestContext test) throws FilloException, NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		if(reportCount==1) {
        	startReport(this.getClass().getSimpleName());
        	eTestScenario.set(extent.get().createTest("EnvironmentDetails", ""));
        	Reporter.createNode("Environment Details");
			reportStep("pass", "The report generated is exceuted in environmet : " + SeleniumUtils.environmentName);
			reportStep("pass", "User Name : " + LoginMain.LOGINPROP.getProperty(environmentName+"_user"));
        }
        ++reportCount;

		DriverListener.testScenarioID.set(testScenarioID);
		String testSheetName = ExcelDataReader.getData("ControlSheet", testScenarioID, "testSheetName");
		DriverListener.testSheetName.set(testSheetName);

		eTestScenario.set(extent.get().createTest(testScenarioID, SeleniumUtils.getData("testCaseDescription")));
		
		String[] lifeCycles = ExcelDataReader.getData(testSheetName, testScenarioID, "lifecycle").split(";");
		for (String lifeCycle : lifeCycles) {
			test.setAttribute("lifeCycle", lifeCycle);
			System.out.println(lifeCycle);
			RevenueSteps mls = new RevenueSteps();
			Class cls = mls.getClass();
			Method method = cls.getDeclaredMethod(lifeCycle, null);
			method.invoke(mls, null);
		}
		
	}

}