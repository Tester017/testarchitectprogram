package annotation;

import org.testng.annotations.AfterMethod;

import common.Report;

public class Annotation extends Report {
	
	@AfterMethod
	public void afterMethod() {
		playwrightThread.get().close();
		
	}
	
	

}
