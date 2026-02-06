package annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import common.utils.LoginPage;
import common.utils.Reporter;
import constant.ProductConstant;

public class Annotations extends Reporter {

	
@BeforeSuite(alwaysRun = true)	
	public void beforeSuite() {
		LoginPage login= new LoginPage();
		if(ProductConstant.environmentName.toUpperCase().contains("SIDOTI")) {
			authToken=getPropertyValue(envroinmentProperty, "authtoken");
		}
		else {
			LoginPage.loginApplication();
		}
	}
	@BeforeClass		
	public void beforeClass() {


	}

	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method,ITestContext c) {
		String testName = this.getClass().getSimpleName();
		ExtentTest parent = getExtentReports().createTest(this.getClass().getSimpleName()+" - "+ method.getName(), method.getName()+" - "+method.getAnnotation(Test.class).description());
		setParentTest(parent);
		createReportNode(method.getName());
		createAPIRequestContext();
	}

	@AfterClass		
	public void afterClass() {

	}
	
@AfterMethod(alwaysRun = true)
	public void afterMethod(Method method) {
		String author = "";
		ArrayList<String> jira = new ArrayList<String>();
		String version = "";
		Test testClass = method.getAnnotation(Test.class);
		for (String eachGroup:testClass.groups()) {
			if(eachGroup.contains("author")) author=eachGroup.split("=")[1];
			if(eachGroup.contains("jira")) jira.add(eachGroup.split("=")[1]);
			if(eachGroup.contains("Version")) version=eachGroup.split("=")[1];
		}
		setReportDetails(author, jira, version);
		endReport();
		playwrightThread.get().close();
	}
	
	@AfterSuite(alwaysRun = true)		
	public void afteSuite() {
		endReport();
	}



	public void setReportDetails(String author, ArrayList<String> jira, String version) {
		getParentTest().assignCategory(this.getClass().getSimpleName());
		getParentTest().assignAuthor(author);
		getParentTest().assignDevice("  Jira : "+jira+"  version : " +version);

	}



}
