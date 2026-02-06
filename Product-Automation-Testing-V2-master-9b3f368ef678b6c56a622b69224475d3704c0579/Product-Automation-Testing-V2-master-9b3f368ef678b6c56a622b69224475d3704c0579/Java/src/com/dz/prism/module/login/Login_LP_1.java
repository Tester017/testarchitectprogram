package com.dz.prism.module.login;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Login_LP_1 {
	public static void SignInMethod() throws Exception{
		try{
			SeleniumUtils.testCase = SeleniumUtils.extendReports.createTest(LoginMain.LOGINPROP.getProperty("Login_Submit") +"_Login test");
			SeleniumUtils.testCase =  SeleniumUtils.testCase.createNode("Verify Username and Password for login");
			SeleniumUtils.testCase.log(Status.INFO,MarkupHelper.createLabel(SeleniumUtils.createDownloadButton(SeleniumUtils.UserDirVar+LoginMain.LOGINPROP.getProperty("LP_1_v_testCasePath")),ExtentColor.TRANSPARENT));
			if(LoginMain.LOGINPROP.getProperty("LP_1_v_case_from_excel").equals("0")){
				commonLogin(LoginMain.LOGINPROP.getProperty("LP_1_v_default_Login_user"),LoginMain.LOGINPROP.getProperty("LP_1_v_default_Pass"));
			}else{
				Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+LoginMain.LOGINPROP.getProperty("LP_1_v_testCasePath"));
				for(Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()){
					List<Map<String, String>> innerRows = testRows.getValue();
					int rowCount = 1;
					for(Map<String,String> values : innerRows){
						testLoginUserandPass(values);
						if(rowCount != innerRows.size()){
							SeleniumUtils.ClearFieldValue(LoginMain.LOGINPROP.getProperty("LP_1_id_useremail"), "xpath");
							SeleniumUtils.ClearFieldValue(LoginMain.LOGINPROP.getProperty("LP_1_id_userpassword"), "xpath");
							rowCount++;
						}
					}
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}		 	 
	}
	public static void testLoginUserandPass(Map<String,String> rowValues){
		try{			
			SeleniumUtils.setValueToField(LoginMain.LOGINPROP.getProperty("LP_1_id_useremail"), rowValues.get(LoginMain.LOGINPROP.getProperty("LP_1_v_useremail")),"xpath");
			SeleniumUtils.setValueToField(LoginMain.LOGINPROP.getProperty("LP_1_id_userpassword"), rowValues.get(LoginMain.LOGINPROP.getProperty("LP_1_v_userpassword")),"xpath");
			SeleniumUtils.ClickOnItems(LoginMain.LOGINPROP.getProperty("LP_1_id_loginButton"),"xpath");
			SeleniumUtils.setTimeOut(20);
			Boolean DashboardLoaded= SeleniumUtils.webDriver.findElements(By.xpath(LoginMain.LOGINPROP.getProperty("LP_1_id_Loadedelement"))).size()!=0;
			if(DashboardLoaded){
				SeleniumUtils.testCase.log(Status.PASS,MarkupHelper.createLabel("Verifed Login with UserName and Password in RowID -"+rowValues.get(LoginMain.LOGINPROP.getProperty("LP_1_v_rowid")),ExtentColor.GREEN));			 
				SeleniumUtils.testCase.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(LoginMain.moduleName,LoginMain.LOGINPROP.getProperty("Login_Submit"),0));
			}else{
				SeleniumUtils.testCase.log(Status.FAIL,MarkupHelper.createLabel("Verifed Login with UserName and Password in RowID -"+rowValues.get(LoginMain.LOGINPROP.getProperty("LP_1_v_rowid")),ExtentColor.RED));
				SeleniumUtils.testCase.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(LoginMain.moduleName,LoginMain.LOGINPROP.getProperty("Login_Submit"),1));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Common login with given credentials
	 * @param uName
	 * @param passwd
	 */
	public static void commonLogin(String uName,String passwd){
		try{
			if(uName.equals("") || passwd.equals("")){
				throw new Exception();
			}else{
				if(!isLoggedIn()){
					logOutCurruntPage();
					SeleniumUtils.setTimeOut(20);
					SeleniumUtils.setValueToField(LoginMain.LOGINPROP.getProperty("LP_1_id_useremail"), uName,"xpath");
					SeleniumUtils.setValueToField(LoginMain.LOGINPROP.getProperty("LP_1_id_userpassword"), passwd,"xpath");
					SeleniumUtils.ClickOnItems(LoginMain.LOGINPROP.getProperty("LP_1_id_loginButton"),"xpath");
					SeleniumUtils.setTimeOut(20);
				}else{
					SeleniumUtils.setTimeOut(20);
					SeleniumUtils.setValueToField(LoginMain.LOGINPROP.getProperty("LP_1_id_useremail"), uName,"xpath");
					SeleniumUtils.setValueToField(LoginMain.LOGINPROP.getProperty("LP_1_id_userpassword"), passwd,"xpath");
					SeleniumUtils.ClickOnItems(LoginMain.LOGINPROP.getProperty("LP_1_id_loginButton"),"xpath");
					SeleniumUtils.setTimeOut(20);
				}
			}
		}catch(Exception e){
			commonLogin(LoginMain.LOGINPROP.getProperty("LP_1_v_default_Login_user"),LoginMain.LOGINPROP.getProperty("LP_1_v_default_Pass"));
			e.printStackTrace();
		}
	}
	
	/**
	 * return true if already logged in
	 * @return
	 */
	public static Boolean isLoggedIn(){
		try {
			if(SeleniumUtils.webDriver.findElements(By.xpath(LoginMain.LOGINPROP.getProperty("LP_1_id_useremail"))).size() != 0){
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}		
	}
	
	/**
	 * log out if already logged in and try login with  new user 
	 */
	public static void logOutCurruntPage(){
		try {
			SeleniumUtils.ClickOnItems(LoginMain.LOGINPROP.getProperty("LP_1_id_profileButton"), "xpath");
			SeleniumUtils.setTimeOut(20);
			SeleniumUtils.ClickOnItems(LoginMain.LOGINPROP.getProperty("LP_1_id_LogoutButton"), "xpath");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
