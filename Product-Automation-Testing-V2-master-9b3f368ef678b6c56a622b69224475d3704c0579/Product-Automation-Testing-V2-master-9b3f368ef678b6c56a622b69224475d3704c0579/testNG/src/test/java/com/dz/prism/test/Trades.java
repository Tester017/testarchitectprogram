package com.dz.prism.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.codoid.products.exception.FilloException;
import com.dz.core.testng.listeners.DriverListener;
import com.dz.prism.annotations.Annotations;
import com.dz.prism.functions.TickerTearSheetSteps;
import com.dz.prism.functions.TradesSteps;
import com.dz.prism.utils.ExcelDataReader;
import com.dz.prism.utils.SeleniumUtils;

public class Trades extends Annotations {
	public  int reportCount=1; 
	@DataProvider(name = "TickerTearSheetInputs", parallel = false)
	public Object[] getExcelData() throws FilloException {
		DriverListener.mainWorkbook.set("./src/test/resources/testdata/" + SeleniumUtils.environmentName
				+ "/Trades/trades.xlsx");
		return ExcelDataReader.getControlData("ControlSheet", "testScenarioID");
	}

	@Test(dataProvider = "TickerTearSheetInputs")
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
			TradesSteps mls = new TradesSteps();
			Class cls = mls.getClass();
			Method method = cls.getDeclaredMethod(lifeCycle, null);
			method.invoke(mls, null);
		}
	}
}
