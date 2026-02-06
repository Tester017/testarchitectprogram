package common.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

import constant.ProductConstant;
import utils.ApiUtils;

public class Reporter extends ApiUtils {
	
	 public static Playwright playwright;
	// public static APIRequestContext request;
	 public static String authToken = "";
	 public static String folderName = "";
	 private String pattern = "dd-MMM-yyyy_HH-mm-ss";
	 private static ExtentReports extent;
	 private String fileName = "prismresults.html";
	 private static final ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	 private static final ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	 private static final ThreadLocal<String> testName = new ThreadLocal<String>();
	 public static final ThreadLocal<Playwright> playwrightThread = new ThreadLocal<Playwright>();
	 public static final ThreadLocal<APIRequestContext> requests = new ThreadLocal<APIRequestContext>();
	 public static Properties tableHeader = null;
	 public static Properties periodFilter = null;
	 public static Properties activityModulePeriodFilter = null;
	 public static Properties readershipModulePeriodFilter = null;
	 public static Properties envroinmentProperty = null;
	 public static Properties tradesModulePeriodFilter = null;
	 
	 public void startReport() {
		 String date = new SimpleDateFormat(pattern).format(new Date());
			folderName = "reports/"+date;

			File folder = new File("./" + folderName);
			if (!folder.exists()) {
				folder.mkdir();
			}
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./" + folderName + "/" + ProductConstant.environmentName+".html");
			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config().setDocumentTitle("Prism");
			
			htmlReporter.config().setEncoding("utf-8");
			htmlReporter.config().setReportName("Prism");
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
	 }
	  
	 
	 public void endReport() {
			extent.flush();
		}


	 public static void reportStep(String desc, String status, boolean bSnap) {

			if (status.equalsIgnoreCase("pass")) {
				getTest().pass(desc);
			} else if (status.equalsIgnoreCase("fail")) { // additional steps to manage alert pop-up
				getTest().fail(desc);

			} else if (status.equalsIgnoreCase("warning")) {
				getTest().warning(desc);
			} else if (status.equalsIgnoreCase("skipped")) {
				getTest().skip(desc);
			} else if (status.equalsIgnoreCase("INFO")) {
				getTest().info(desc);
			}else {
				System.err.println("Please input correct report status  -  "+ status);
				getTest().info("Please input correct report status  -  "+ status);
			}

		}

		public synchronized static void reportStep(String desc, String status) {
			try {
				reportStep(desc, status, true);
			}catch (Exception e) {
				 
			}
		}
	 
	 
	 
	 public static void createReportNode(String description) {
			//setChildTest(getTest().createNode(description));
			setChildTest(getParentTest().createNode(description));
			
			
		}  
	 
	 
	 public static void createParentNode(String Description) {
		 ExtentTest parent = getExtentReports().createTest(Description+" - ", Description);
			setParentTest(parent);
			createReportNode(Description);
	 }
	 
	 
	 
	 
	 public String getTestName() {
			return testName.get();
		}
		
		public void setTestName(String testcaseName) {
			testName.set(testcaseName);
		}

		public Status getTestStatus() {
			return parentTest.get().getModel().getStatus();
		}

		public static ExtentReports getExtentReports() {
			return extent;
		}

		public static void setParentTest(ExtentTest parent) {
			parentTest.set(parent);
		}

		public static ExtentTest getParentTest() {
			return parentTest.get();
		}

		public static void setChildTest(ExtentTest child) {
			test.set(child);
		}

		public static ExtentTest getTest() {
			return test.get();
		}



	public void  createAPIRequestContext() {
		    playwrightThread.set(Playwright.create()); 
		    Map<String, String> headers = new HashMap<>();
		    headers.put("auth_token", authToken);
		    // We set this header per GitHub guidelines.
		    // Add authorization token to all requests.
		    // Assuming personal access token available in the environment. 
		  
		    APIRequestContext req = playwrightThread.get().request().newContext(new APIRequest.NewContextOptions()
		      // All requests we send go to this API endpoint.
		      .setBaseURL(ProductConstant.apiBaseUrl)
		      .setTimeout(80000)
		      .setExtraHTTPHeaders(headers)
		      .setIgnoreHTTPSErrors(true)
		       
		    );
		    requests.set(req);
	  }
	 

	  public static String convertToUrlCa(String path, Map<String, String> data) {
		     for(java.util.Map.Entry<String, String> params : data.entrySet() ) {
		    	 if(!path.contains("?")) {
		    		 path +="?";
		    	 }
		    	 path +=params.getKey()+"="+params.getValue()+"&"; 
		     }
		     System.out.println(path);
		return path;
	}

		/**
		 * This method used to get environment property file value
		 * 
		 * @param key - properties file key
		 * @return - properties file value
		 * @author bhuvaneswaran
		 */

		public static String getPropertyValue(Properties props,String key) {
			try {
				String value = props.getProperty(key).trim();
				return value;
			} catch (Exception e) {
				System.err.println("This " + key + "  missing in property file . Kindly check EnvironmentConfig");
				reportStep("This " + key + "  missing in property file . Kindly check EnvironmentConfig", "fail");
				e.printStackTrace();
				return null;
			}

		}
	
		
		public static String convertToEncodedUrlCall(String path, Map<String, String> data) {
			Set<String> keys = data.keySet();
			int index = 0;
			for (String key : keys) {
				if (index == 0) {
					path += "?";
				}
				if(index > 0) {
					path += "&";
				}
				
				try {
					String encodedValue = URLEncoder.encode(data.get(key), "UTF-8");
					path += key + "=" + encodedValue;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				index++;
			}
			System.out.println(path);
			return path;
		}
}
