package com.dz.prism.module.mylist;

import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class DeleteList_ML_1_13 {

	static String  listName ="Test"+SeleniumUtils.getCurrDate();
	static ExtentTest reportList=null;
	public static void selectList(){
		try{
			SeleniumUtils.testCase = SeleniumUtils.extendReports
                .createTest( getPropValue("Delete_List")+"_Delete list");
			reportList = SeleniumUtils.testCase.createNode("Deleting List ");
			SeleniumUtils.webDriver.findElement(By.xpath(getPropValue("ML_1_3_mylist_tab")));
			DeleteContact_ML_1_14.Simplelistcreation(listName, reportList);
			deleteList(listName.trim(), reportList);
		}catch (Exception e) {
			reportGenerate(reportList, "delete list is not execute", false);
		} 
	
	}
	/**
	 * This Method is used to delete the list
	 * @param listName
	 * @param reportList
	 */
	public static void deleteList(String listName,ExtentTest reportList){
		try{
			Thread.sleep(2000);
			String SideMenuStatus = SeleniumUtils.getAttributefromField(getPropValue("ML_1_1_id_list_expand"),"class","id");
			if (SideMenuStatus.contains("fa-angle-double-left")) {				
				SeleniumUtils.ClickOnItems(getPropValue("ML_1_1_id_list_expand"),"id");
			}
			String listIdByName = getPropValue("ML_1_13_listname");
			listIdByName = listIdByName.replace("#listname",listName);
			try{
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				if(SeleniumUtils.waitUntilElementDisplayed(listIdByName, 20, "xpath")){
					SeleniumUtils.ClickOnItems(listIdByName, "xpath");
				}				
				listIdByName = listIdByName.replace("/span", "/ul/li/a[@class='deletelist'] ");
				if(SeleniumUtils.waitUntilElementDisplayed(listIdByName,10, "xpath")){
					reportGenerate(reportList, "Given List name are Displaying in my list Section", true);	
				}
				SeleniumUtils.closeToastMessage();
				SeleniumUtils.ClickOnItems(listIdByName, "xpath");
				SeleniumUtils.ClickOnItems(getPropValue("ML_1_13_delete_yes"), "xpath");
				if(SeleniumUtils.getToastMessage().equalsIgnoreCase("List deleted")){
					reportGenerate(reportList, "Given list is deleted-"+listName, true);	
				}else{
					reportGenerate(reportList, "Given list is deleted-"+listName, false);
				}
				verifyDeletedList(listName,reportList);
			}catch (Exception e) {
				e.printStackTrace();
				reportGenerate(reportList, "Given List name are not Displaying in my list Section - "+listName, false);
			}
		}catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	/**
	 * This Method is used to verify the delete list
	 * @param listName
	 * @param reportList
	 */
	private static void verifyDeletedList(String listName, ExtentTest reportList) {
		try{
			Thread.sleep(3000);
			String listIdByName = getPropValue("ML_1_13_listname");
			listIdByName = listIdByName.replace("#listname",listName);
			if(SeleniumUtils.waitUntilElementDisplayed(listIdByName,10, "xpath")){
				reportGenerate(reportList, "Deleted list name is available in My list section", false);
			}
			else{
				reportGenerate(reportList, "Deleted list name is not available in My list section", true);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	/**
	 * get the prop Value
	 * @param key
	 * @return
	 */
		private static String getPropValue(String key) {
			
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
		public static void reportGenerate(ExtentTest insertData, String msg, boolean status  ) {
		  try{	
				if(status){
					insertData.log(Status.PASS,MarkupHelper.createLabel( msg+" ",ExtentColor.GREEN));            
				    } else {
				    	insertData.log(Status.FAIL,
				                MarkupHelper.createLabel(msg ,
				                        ExtentColor.RED));          
				    }	
		   }catch (Exception e) {
			insertData.log(Status.FAIL,
				           MarkupHelper.createLabel(msg ,
				                   ExtentColor.RED));  
		    }
		 }
}
