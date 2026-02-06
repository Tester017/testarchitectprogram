package com.dz.core.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.codoid.products.exception.FilloException;
import com.dz.core.driver.Driver;
import com.dz.core.testng.listeners.DriverListener;
import com.dz.prism.bo.NodeLevelResultData;
import com.dz.prism.utils.SeleniumUtils;
import com.google.common.io.Files;

public abstract class Reporter {
	
	public static ThreadLocal<String> htmlFile;
	public static ThreadLocal<ExtentHtmlReporter> reporter = new ThreadLocal<ExtentHtmlReporter>();
	public static ThreadLocal<ExtentReports> extent = new ThreadLocal<ExtentReports>();
	public static ThreadLocal<ExtentTest> eTestScenario = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> eTestCase= new ThreadLocal<ExtentTest>();
	public static ThreadLocal<Integer> nodePassCount= new ThreadLocal<Integer>();
	public static ThreadLocal<Integer> nodeFailCount= new ThreadLocal<Integer>();
	public static List<NodeLevelResultData> data = new ArrayList<NodeLevelResultData>();
	public static int pass=0;
	public static int fail=0;
	public static int info=0;
	public static int skip=0;
	
	/**
	 * Create Report generation object and attaching the Report
	 * @author daniel
	 * @throws FilloException 
	 */
	public void startReport(String htmlFileName) throws FilloException {
		reporter.set(new ExtentHtmlReporter(DriverListener.reportFolder + "/" + htmlFileName +"_"+SeleniumUtils.environmentName+ ".html"));
		extent.set(new ExtentReports());
		extent.get().attachReporter(reporter.get());
	}
	
	/**
	 * flush the Report
	 * @author daniel
	 */
	public static void flushReport(){
		try {
			extent.get().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void createNode(String testCase) {
		System.out.println("test #### "+eTestCase.get());
		if(eTestCase.get()!=null) {
			if(eTestCase.get().getStatus().equals(Status.PASS)) {
				 nodePassCount.set((nodePassCount.get()+1));
			}else if(eTestCase.get().getStatus().equals(Status.FAIL)) {
				 nodeFailCount.set((nodeFailCount.get()+1));
			}
		}
		eTestCase.set(eTestScenario.get().createNode(testCase));
	}
	
	public static String takeSnap(){
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		try {
			Files.copy(((TakesScreenshot) Driver.getWebDriver()).getScreenshotAs(OutputType.FILE) , new File("./reports/images/"+number+".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return "./reports/images/"+number+".jpg";
	}
	
	public static String getBase64(){
		return ((TakesScreenshot) Driver.getWebDriver()).getScreenshotAs(OutputType.BASE64);
	}

	public static void reportStep(String status, String desc,boolean bSnap ) {
    	if(status.equalsIgnoreCase("pass")) {
    		++pass;
    		eTestCase.get().pass(desc);
    		if(bSnap) eTestCase.get().addScreenCaptureFromBase64String(getBase64());
    	} else if(status.equalsIgnoreCase("fail")) {
    		++fail;
    		eTestCase.get().fail(desc); 
    		eTestCase.get().addScreenCaptureFromBase64String(getBase64());
    	} else if(status.equalsIgnoreCase("INFO")) {
    		++info;
    		eTestCase.get().info(desc); 
    	}else if(status.equalsIgnoreCase("SKIP")) {
    		++skip;
    		eTestCase.get().skip(desc); 
    	}
    }
    public static void reportStep( String status,String desc) {
		reportStep(status,desc, false);
	}


}
