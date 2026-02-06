package com.dz.prism.module.mylist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.main.AutomationDriver;
import com.dz.prism.utils.SeleniumUtils;
import com.dz.prism.utils.TabSelectionUtils;

public class MyListMain {

	public static Properties MYLISTPROP  = null;
	public static final String moduleName = "MyList";
	public static List<String> dynamicList =new ArrayList<String>();
	public static ExtentTest dynamicReport;
		static{
			try {
				MYLISTPROP = SeleniumUtils.getConfigProprty("\\ModuleConfigurations\\mylist.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		public MyListMain(){
			if(MYLISTPROP != null)
			{
				SeleniumUtils.setTimeoutUntilVisibility(MYLISTPROP.getProperty("ML_1_1_id_my_list_toggle_visible"), "id");
				TabSelectionUtils.tabSelection("My_Lists");
				callTestCases();
			} else {
				System.out.println("MyList Module properties file not loaded please verify");
			}
		}
		public static void tabSelection(){
			TabSelectionUtils.tabSelection("My_Lists");
		}
		private void callTestCases(){
			try{
				
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					MyList_ML_1_12.contactSearch();
					System.out.println("Contact Search");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					   QuickcallVerification_ML_1_19.Readexcel();
					   System.out.println("quick call  is ececuted ");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					   TickerWithCriteriaSearch_ML_22.readExcel();
					   System.out.println("Ticker With Criteria is executed ");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					   DynamicList_ML_1_21.Readexcel();
					   System.out.println("Dynamic list  is executed ");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					   MyList_StaticList_ML_1_20.StaticList();
					   System.out.println("Dynamic list  is executed ");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					   Search_SectorCategory_ML_1_24.readExcel();
					   System.out.println("SectorCategory search  is executed ");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					   AnalystWithCriteria_Serch_ML_1_23.readExcel();
					   System.out.println("AnalystWithCriteria search  is executed ");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					   AccountnameWithCriteria_ML_1_25.readExcel();
					   System.out.println("AccountnameWithCriteria list  is executed ");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					   AccountnameWithCriteria_ML_1_25.readExcel();
					   System.out.println("AccountnameWithCriteria list  is executed ");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					   Search_Event_ML_1_28.readExcel();
					   System.out.println("Event Search list  is executed ");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					   Search_SectorCategory_ML_1_24.readExcel();
					   System.out.println("Sector Search list  is executed ");
				}
				/*
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					SimpleListCreation_ML_1_1.ListMethod(MYLISTPROP);
					System.out.println("Simple list create");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					DynamicListCreation_ML_1_6.ListMethod();
					System.out.println("Dynamic list create");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					ListCount_ML_1_2.contactListCount();
					System.out.println("Contact count in list verify");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					SortList_ML_1_9.myListSorting(MYLISTPROP);
					System.out.println("List sorted");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
				    // CopySelectedToOtherList_ML_1_3.otherList();
					//System.out.println("Copy Selected exist List");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
				    CopySelectedToNewList_ML_1_3.selectList();
					System.out.println("Copy Select New List");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
				    AddQuickContact_ML_1_5.quickContact();
					System.out.println("Add Quick Contact");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					DeleteContact_ML_1_14.deleteContact();
					System.out.println("delete contact list");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					DeleteList_ML_1_13.selectList();
					System.out.println("delete list");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					//BulkMail_ML_1_4.Readexcel();
					//System.out.println("Bulk mail draft verify");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					//BulkCall_ML_1_4.bulkCall();
					//System.out.println("bulk call");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					//BulkLogInteraction_ML_1_15.bulkLog();
					//System.out.println("bulk log");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){ 
				  for(String dynamic: dynamicList){
					DeleteList_ML_1_13.deleteList(dynamic, dynamicReport);
				  }
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
						//ShareList_ML_1_10.shareList(MYLISTPROP);
						//System.out.println("Share List");
				}
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					ListContactVerification_ML_1_16.getContact();
					System.out.println("List contact verify");
			    }
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
				   // SingleContactVerify_ML_1_17.selectList();
				   //System.out.println("Single Contact mail, log interaction , quick call");
			    }
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					   //MyCoveredContact_ML_1_18.Mylist_Page();
					   //System.out.println("MyCovered contact is ececuted ");
				}
				*/
				if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
					   EmailHistoryWithCriteriaML_1_27.readExcel();
					   System.out.println("Dynamic list  is executed ");
				}
				 
				 
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		@SuppressWarnings("null")
		public static void closeTab(ExtentTest reportList) {
			try{
				JavascriptExecutor js = null;		 
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				List<WebElement> closeTab =SeleniumUtils.webDriver.findElements(By.xpath(getPropValue("ML_1_3_close_tab")));
				for(WebElement close : closeTab){
					try{ 
						if(close.getAttribute("data-list-id").equalsIgnoreCase("SearchResults")){
							close.click();
							SeleniumUtils.ClickOnItems(getPropValue("ML_1_3_tab_close_yes"),"xpath");
						}else{
							close.click();
						}	
					}catch (Exception e) {
						try {
							WebDriverWait wait = new WebDriverWait(SeleniumUtils.webDriver, 30);
							wait.until(ExpectedConditions.elementToBeClickable(close));
						}catch (Exception e2) {
							js.executeScript("arguments[0].click()",close);
							e.printStackTrace();
						  }
					}
				}
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			}
			catch (Exception e) {
				 e.printStackTrace();
			}
		}
		/**
		 * get the prop Value
		 * @param key
		 * @return
		 */
			public static String getPropValue(String key) {
				
				String result = "";
				try{
				result =  MyListMain.MYLISTPROP.getProperty(key).trim();
				}
				catch (Exception e) {
					 
				}
				return result;
			}
			/**
			 * generate a report
			 * @param insertData
			 * @param msg
			 * @param status
			 * @param values
			 */
				public static void reportWrite(ExtentTest extent, String msg, boolean status  ) {
					try{
						if(status){
							extent.log(Status.PASS,MarkupHelper.createLabel( msg+" ",ExtentColor.GREEN));            
				        }else {
				        	extent.log(Status.FAIL,
				                    MarkupHelper.createLabel(msg ,
				                            ExtentColor.RED));    
				        	 SeleniumUtils.testCase.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot("Tab Selection","tab" ,1));
				        }	
					}catch (Exception e) {
						e.printStackTrace();
					}
				} 
				/**
				 * generate a report
				 * @param insertData
				 * @param msg
				 * @param status
				 * @param values
				 */
					public static void reportWriteSkip(ExtentTest extent, String msg) {
						try{
							 
						   extent.log(Status.SKIP,MarkupHelper.createLabel( msg+" ",ExtentColor.GREY));            
					       SeleniumUtils.testCase.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot("Tab Selection","tab" ,1));
						}catch (Exception e) {
							e.printStackTrace();
						}
					} 
}
