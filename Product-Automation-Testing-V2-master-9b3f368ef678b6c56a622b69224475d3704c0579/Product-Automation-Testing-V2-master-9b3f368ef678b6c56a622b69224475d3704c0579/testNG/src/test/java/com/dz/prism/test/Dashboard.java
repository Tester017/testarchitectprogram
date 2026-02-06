package com.dz.prism.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Annotation;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.dz.core.testng.listeners.DriverListener;
import com.dz.prism.annotations.Annotations;
import com.dz.prism.functions.ContactTearsheetSteps;
import com.dz.prism.functions.DashboardSteps;
import com.dz.prism.utils.ExcelDataReader;
import com.dz.prism.utils.SeleniumUtils;

public class Dashboard extends Annotations{
	public  int reportCount=1; 
	@DataProvider(name = "dashboard", parallel = false)
	public Object[] getExcelData() throws FilloException {
		DriverListener.mainWorkbook.set("./src/test/resources/testdata/" + SeleniumUtils.environmentName
				+ "/dashboard/dashboard.xlsx");
		return ExcelDataReader.getControlData("ControlSheet", "testScenarioID");
	}

	@Test(dataProvider = "dashboard")
	public void activityKeywordTest(String testScenarioID,ITestContext test) throws FilloException, NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		if(reportCount==1) {
        	startReport(this.getClass().getSimpleName());
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
			DashboardSteps cts = new DashboardSteps();
			Class cls = cts.getClass();
			Method method = cls.getDeclaredMethod(lifeCycle, null);
			method.invoke(cts, null);
		}
	}

}
