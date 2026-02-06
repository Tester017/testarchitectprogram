package listeners;
 
import java.util.Properties;
 
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener; 
import org.testng.ITestResult;

import common.utils.LoginPage;
import common.utils.PlayWrightApiUtils;
import common.utils.Reporter; 
import constant.ProductConstant; 
 

public class DriverListener extends Reporter implements ITestListener, ISuiteListener{
 ThreadLocal<Integer> retryCount = new ThreadLocal<Integer>();
	
	@Override
	public void onStart(ISuite suite) {
		setProductConfig();
		createAPIRequestContext();
		startReport();
		setPropertyFile();
		 
	}
	
	
 

	@Override
	public void onFinish(ISuite suite) {
		 
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		 
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		  
	}

	@Override
	public void onTestFailure(ITestResult result) {
		 
		try {
			String exceptionCause = result.getThrowable().toString();
			exceptionCause = exceptionCause.replaceAll("<.*?>", "");
			 
			if(exceptionCause.trim().equalsIgnoreCase("ProductionMode")) {
				reportStep(exceptionCause,"skipped");
				 
				
			}
			else if(exceptionCause.contains("expected")) {
				 
				reportStep(exceptionCause, "fail");
				reportStep("<b>Reason :</b> "+result.getThrowable().getCause().getStackTrace().toString(), "fail"); 
			}
			else {
				 
				if(exceptionCause.length() <= 600) {
					reportStep("<b>Exception :</b> "+exceptionCause, "fail");
					reportStep("<b>Reason :</b> "+result.getThrowable().getCause().getStackTrace().toString(), "fail"); 
				} else {
					reportStep("<b>Exception :</b> "+exceptionCause.substring(0, 600), "fail");
					reportStep("<b>Reason :</b> "+result.getThrowable().getCause().getStackTrace().toString(), "fail"); 
				}
			}
		} catch (Exception e) {
			reportStep("<b>Exception occured in Test Name :</b> "+result.getMethod().getMethodName(), "fail"); 
			reportStep("<b>Reason is :</b> "+ result.getThrowable().getMessage(), "fail"); 
		}
 
		
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		 
		 
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		 
	}

	@Override
	public void onFinish(ITestContext context) {
		
	}


	/**
	 * This method used to set Environment name , production mode , environment
	 * property file set value to
	 * "/PrismST_PWTAF/src/test/java/com/dz/prism/contant/ProductConstant.java"
	 * 
	 * @author bhuvaneswaran
	 */
	private void setProductConfig() {
		try {
			
			 
			Properties productProperty = PlayWrightApiUtils
					.getConfigProprty("./src/test/resources/productConfig.properties");
			// set Environment name
			
			if (System.getProperty("Client") != null) {
				ProductConstant.environmentName = System.getProperty("Client");
			} else if (productProperty.getProperty("EnviromentName") != null
					&& productProperty.getProperty("EnviromentName") != "") {
				ProductConstant.environmentName = productProperty.getProperty("EnviromentName");
			} else {
				System.out.print(
						"Product config property file in Environment Namne key is empty or null . please kindly check product config");
			}
			
			
			// set Environment name
			if (productProperty.getProperty("PassEncrypted") != null
					&& productProperty.getProperty("PassEncrypted") != "") {
				ProductConstant.passEncryption = Boolean.parseBoolean(productProperty.getProperty("PassEncrypted"));
			} else {
				System.out.print(
						"Product config property file in pass encryption key is empty or null . please kindly check product config");
			}
			
			if (System.getProperty("ProductionMode") != null) {
				if (System.getProperty("ProductionMode").equalsIgnoreCase("true")) {
					ProductConstant.productionMode = true;
				} else {
					ProductConstant.productionMode = false;
				}
				
			}
			if (productProperty.getProperty("ProductionMode") != null
					&& productProperty.getProperty("ProductionMode") != "") {
				
				if (productProperty.getProperty("ProductionMode").equalsIgnoreCase("false")) {
					ProductConstant.productionMode = false;
				}
				
			} else {
				System.out.print(
						"Product config property file in ProductionMode key is empty or null . please kindly check product config");
			}
			 
			envroinmentProperty = PlayWrightApiUtils
					.getConfigProprty("./src/test/resources/EnvironmentConfig/"+ProductConstant.environmentName+"/"+ProductConstant.environmentName+".properties");
			
			if (envroinmentProperty.getProperty("App_URL") != null
					&& envroinmentProperty.getProperty("App_URL") != "") {
				ProductConstant.PrismUrl = envroinmentProperty.getProperty("App_URL");
			} else {
				System.out.print(
						"Product config property file in App_URL key is empty or null . please kindly check product config");
			}
			
			
			if (envroinmentProperty.getProperty("api_base_Url") != null
					&& envroinmentProperty.getProperty("api_base_Url") != "") {
				ProductConstant.apiBaseUrl = envroinmentProperty.getProperty("api_base_Url");
			} else {
				System.out.print(
						"Product config property file in api_base_Url key is empty or null . please kindly check product config");
			}
			
			
			if (envroinmentProperty.getProperty("Equity_User_Name") != null
					&& envroinmentProperty.getProperty("Equity_User_Name") != "") {
				ProductConstant.userName = envroinmentProperty.getProperty("Equity_User_Name");
			} else {
				System.out.print(
						"Product config property file in Equity_User_Name key is empty or null . please kindly check product config");
			}
			
			
			if (envroinmentProperty.getProperty("Equity_Password") != null
					&& envroinmentProperty.getProperty("Equity_Password") != "") {
				ProductConstant.passWord = envroinmentProperty.getProperty("Equity_Password");
			} else {
				System.out.print(
						"Product config property file in Equity_Password key is empty or null . please kindly check product config");
			}
			if (envroinmentProperty.getProperty("Max_Account_Check") != null
					&& envroinmentProperty.getProperty("Max_Account_Check") != "") {
				ProductConstant.maxAccCheck =Integer.parseInt(envroinmentProperty.getProperty("Max_Account_Check"));
			} else {
				System.out.print(
						"Product config property file in Max_Account_Check key is empty or null . please kindly check product config");
			}
			if (envroinmentProperty.getProperty("Equity_Login_Type") != null
					&& envroinmentProperty.getProperty("Equity_Login_Type") != "") {
				ProductConstant.loginType =envroinmentProperty.getProperty("Equity_Login_Type");
			} else {
				System.out.print(
						"Product config property file in Max_Account_Check key is empty or null . please kindly check product config");
			}
			
			if (envroinmentProperty.getProperty("Max_Total_Diffrence") != null
					&& envroinmentProperty.getProperty("Max_Total_Diffrence") != "") {
				ProductConstant.maxTotalDifference =Integer.parseInt(envroinmentProperty.getProperty("Max_Total_Diffrence"));
			} else {
				System.out.print(
						"Product config property file in Max_Total_Diffrence key is empty or null . please kindly check product config");
			}
			
			 
			
		} catch (Exception e) {
			
			e.printStackTrace();

		}

	}

	private void setPropertyFile() {
		try {
		    //Environment property file
			tableHeader = PlayWrightApiUtils
				.getConfigProprty("./src/test/resources/EnvironmentConfig/"
						+ ProductConstant.environmentName + "/"+"TableHeader.properties");
			
			periodFilter = PlayWrightApiUtils
					.getConfigProprty("./src/test/resources/EnvironmentConfig/"
							+ ProductConstant.environmentName + "/"+"PeriodFilter.properties");
			activityModulePeriodFilter = PlayWrightApiUtils
					.getConfigProprty("./src/test/resources/EnvironmentConfig/"
							+ ProductConstant.environmentName + "/"+"ActivityModulePeriodFilter.properties");
			readershipModulePeriodFilter = PlayWrightApiUtils
					.getConfigProprty("./src/test/resources/EnvironmentConfig/"
							+ ProductConstant.environmentName + "/"+"ReadershipModulePeriodFilter.properties");
			tradesModulePeriodFilter = PlayWrightApiUtils
					.getConfigProprty("./src/test/resources/EnvironmentConfig/"
							+ ProductConstant.environmentName + "/"+"TradesPeriodFilter.properties");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	 
	 

 
 

}
