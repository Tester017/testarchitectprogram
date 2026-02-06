package com.dz.prism.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.dz.core.testng.listeners.DriverListener;
import com.dz.prism.annotations.Annotations;
import com.dz.prism.functions.SponsorsSteps;
import com.dz.prism.utils.ExcelDataReader;
import com.dz.prism.utils.SeleniumUtils;


public class Sponsors extends Annotations{

	public int reportCount=1;
	@DataProvider(name = "SponsorsInputs", parallel = false)
	public Object[] getExcelData() throws FilloException {
		DriverListener.mainWorkbook
				.set("./src/test/resources/testdata/" + SeleniumUtils.environmentName + "/Sponsors/sponsors.xlsx");
		return ExcelDataReader.getControlData("ControlSheet", "testScenarioID");
	}

	@Test(dataProvider = "SponsorsInputs")
	public void activityKeywordTest(String testScenarioID,ITestContext test)
			throws FilloException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		 if(reportCount==1) {
	        	startReport(this.getClass().getSimpleName());
	        }
	     ++reportCount;
		DriverListener.testScenarioID.set(testScenarioID);
		String testSheetName = ExcelDataReader.getData("ControlSheet", testScenarioID, "testSheetName");
		DriverListener.testSheetName.set(testSheetName);

		eTestScenario.set(extent.get().createTest(testScenarioID, SeleniumUtils.getData("testCaseDescription")));
		String[] lifeCycles = ExcelDataReader.getData(testSheetName, testScenarioID, "lifecycle").split(";");
		System.out.println("life :"+lifeCycles.toString());
		for (String lifeCycle : lifeCycles) {
			test.setAttribute("lifeCycle", lifeCycle);
			System.out.println(lifeCycle);
			SponsorsSteps mls = new SponsorsSteps();
			Class cls = mls.getClass();
			Method method = cls.getDeclaredMethod(lifeCycle, null);
			method.invoke(mls, null);
		}

	}

}
