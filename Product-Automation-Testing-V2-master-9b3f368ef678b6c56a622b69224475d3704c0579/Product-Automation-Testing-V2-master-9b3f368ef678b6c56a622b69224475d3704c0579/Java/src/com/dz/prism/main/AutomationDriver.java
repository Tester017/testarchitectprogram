package com.dz.prism.main;

import java.io.File;
import java.io.IOException;

import com.dz.prism.module.accountcontactverification.AccountVerification;
import com.dz.prism.module.accountcontactverification.ContactVerification;
import com.dz.prism.module.accountcoverage.AccountCoverageMain;
import com.aventstack.extentreports.model.Test;
import com.dz.prism.module.Con_Mylist.Contact_Mylist_SimpleMain;
import com.dz.prism.module.ExistingContactVerification.ExistingContactVerificationMain;
import com.dz.prism.module.ExistingEvent.EventTearsheetMain;
import com.dz.prism.module.ExistingInteractionVerification.ExistingInteractionMain;
import com.dz.prism.module.accountcreation.AccountCreationMain;
import com.dz.prism.module.activity.ActivityMain;
import com.dz.prism.module.checkspecialcharater.CheckSpecialCharaterMain;
import com.dz.prism.module.contactcreation.ContactCreationMain; 
import com.dz.prism.module.contactinterest.ContactInterestmain;
import com.dz.prism.module.contactmove.ContactMoveMain;
import com.dz.prism.module.employmentchange.EmploymentChangeMain;
import com.dz.prism.module.eventmanager.EventmanagerMain;
import com.dz.prism.module.feedback.FeedbackMain;
import com.dz.prism.module.login.LoginMain;
import com.dz.prism.module.mclagan.AccountTearsheetMclaganMain;
import com.dz.prism.module.mylist.MyCoveredContact_ML_1_18;
import com.dz.prism.module.mylist.MyListMain;
import com.dz.prism.module.mylist.QuickcallVerification_ML_1_19;
import com.dz.prism.module.mylist.TableTest;
import com.dz.prism.module.trades.TradesMain;
import com.dz.prism.productionbug.ProductionBugMain;
import com.dz.prism.utils.SeleniumUtils;

public class AutomationDriver {

	public static void main(String[] args) throws Exception{
		try{
			LoadDrivers();							
			CallModuleTestCases();			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {// Finally close the browser and flush the report
			SeleniumUtils.flushReport();
			System.out.println("Report Generated");
			SeleniumUtils.closeBrowser();
			SeleniumUtils.setBrowserDriverConfig(SeleniumUtils.SeleniumProps.getProperty("BrowserType")); // for showing output report in new tab
			SeleniumUtils.startBrowserDriver(SeleniumUtils.outputHtmlPath);
		}
	}
	public static void LoadDrivers(){
		try {
			SeleniumUtils.commonProps = SeleniumUtils.getConfigProprty("\\ModuleConfigurations\\common.properties");
			SeleniumUtils.SeleniumProps = SeleniumUtils.getConfigProprty("\\ModuleConfigurations\\seleniumconfig.properties");
			SeleniumUtils.outputFilePath = SeleniumUtils.SeleniumProps.getProperty("ReportFilePathWithName") + File.separator + SeleniumUtils.getCurrDate() +File.separator;
			SeleniumUtils.environmentName = SeleniumUtils.SeleniumProps.getProperty("EnviromentName");
			if(SeleniumUtils.SeleniumProps.getProperty("screenshottype").equals("3")){
				SeleniumUtils.enabledSuccesscreenshot =true;
				SeleniumUtils.enabledFailureScreenshot = true;
			}else if(SeleniumUtils.SeleniumProps.getProperty("screenshottype").equals("2")){
				SeleniumUtils.enabledSuccesscreenshot =false;
				SeleniumUtils.enabledFailureScreenshot = true;
			}else{
				SeleniumUtils.enabledSuccesscreenshot =true;
				SeleniumUtils.enabledFailureScreenshot = false;
			}

			SeleniumUtils.setBrowserDriverConfig(SeleniumUtils.SeleniumProps.getProperty("BrowserType"));
			SeleniumUtils.startBrowserDriver(SeleniumUtils.SeleniumProps.getProperty("ApplicationURL"));
			SeleniumUtils.createTestObject();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isProductionMode(){
		try {
			if(SeleniumUtils.SeleniumProps.getProperty("ProductionMode") != null && SeleniumUtils.SeleniumProps.getProperty("ProductionMode").equals("true")){
				SeleniumUtils.isproduction =true;
				return true;
			}else{
				SeleniumUtils.isproduction =false;
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	public static boolean isDevelopmentMode(){
		try {
			if(SeleniumUtils.SeleniumProps.getProperty("ProductionMode") != null && SeleniumUtils.SeleniumProps.getProperty("ProductionMode").equals("false")){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	/* return enabled or not
	 * give module name same as in config file - modulename 
	 * ex - mylist,feedback,eventmanager,activity,accountcontactverification
	 */
	public static boolean isModuleEnabled(String modulename){
		try {
			if(SeleniumUtils.SeleniumProps.getProperty(modulename).trim().equals("1")){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	public static void CallModuleTestCases(){
		new LoginMain();
		if(isModuleEnabled("mylist")){
			new MyListMain();
		}
		
		if(isModuleEnabled("contactcreation")) {
			new  ContactCreationMain();
		}
		if(isModuleEnabled("activity")){
			new ActivityMain();
		}
		if(isModuleEnabled("accountcreation")){
			new AccountCreationMain();
		}
		if(isModuleEnabled("ContactMove")){
			new ContactMoveMain();
		}
		if(isModuleEnabled("ProductionBugs")) {
			new ProductionBugMain();
		}
		if(isModuleEnabled("AccountCreation")) {
			new AccountCreationMain();
		}
		if(isModuleEnabled("ContactInterest")) {
			new ContactInterestmain();
		}
		if(isModuleEnabled("ContactVerification")){
			new ContactInterestmain();
		}
		if(isModuleEnabled("EmploymentChange")){
			new EmploymentChangeMain();
		}
		if(isModuleEnabled("ExistingInteractionverification")){
			new ExistingInteractionMain();
		}
		if(isModuleEnabled("ExistingContactverification")){
			new ExistingContactVerificationMain();
		}
		if(isModuleEnabled("Trades")){
			new TradesMain();
		}
		if(isModuleEnabled("SpecialCharater")){
			new CheckSpecialCharaterMain();
		} 
		if(isModuleEnabled("Revenue")) {
			new AccountCoverageMain();
		}
		if(isModuleEnabled("Dashboard")) {
			new AccountTearsheetMclaganMain();
		}
		if(isModuleEnabled("Mylist_SimpleList")){
			new Contact_Mylist_SimpleMain();		
		}
		if(isModuleEnabled("eventmanager")){
			new EventmanagerMain();
		}
		if(isModuleEnabled("eventmanager")){
			new EventTearsheetMain();
		}
		

	}		

}