package com.dz.prism.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.dz.core.testng.listeners.DriverListener;
import com.dz.prism.annotations.Annotations;
import com.dz.prism.functions.AccountCreationSteps;
import com.dz.prism.functions.ContactCreationSteps;
import com.dz.prism.utils.ExcelDataReader;
import com.dz.prism.utils.SeleniumUtils;

public class ContactCreation extends Annotations {
	public  int reportCount=1; 
	@DataProvider(name = "ContactCreationInputs", parallel = false)
	public Object[] getExcelData() throws FilloException {
		DriverListener.mainWorkbook.set("./src/test/resources/testdata/" + SeleniumUtils.environmentName
				+ "/contactcreation/contactcreation_Main.xlsx");
		return ExcelDataReader.getControlData("ControlSheet", "testScenarioID");
	}

	@Test(dataProvider = "ContactCreationInputs")
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
			ContactCreationSteps mls = new ContactCreationSteps();
			Class cls = mls.getClass();
			Method method = cls.getDeclaredMethod(lifeCycle, null);
			method.invoke(mls, null);
		}
	}
}
