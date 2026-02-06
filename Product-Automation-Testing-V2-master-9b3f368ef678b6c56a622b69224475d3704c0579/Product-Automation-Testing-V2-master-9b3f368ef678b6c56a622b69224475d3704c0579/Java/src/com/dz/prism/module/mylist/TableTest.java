package com.dz.prism.module.mylist;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.bo.MyListTableBO;
import com.dz.prism.module.checkspecialcharater.CheckSpecialCharaterMain;
import com.dz.prism.utils.MyListUtils;
import com.dz.prism.utils.SeleniumUtils;

public class TableTest {
	
	public static void start(){
		try{	
			String SideMenuStatus = SeleniumUtils.getAttributefromField(getPropValue("ML_1_1_id_list_expand"),"class","id");
			
			SeleniumUtils.setTimeOut(20);
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.createTestNode(getPropValue("ML_1_12_v_row_id") + "_Mylist Tab Selection ", "Select Mylist Tab selection");
			if(SeleniumUtils.tabSelection("My Lists V3")){
				reportGenerate(SeleniumUtils.childTest, "Tab Selection in given tab name is available ",true);
			}else{
				reportGenerate(SeleniumUtils.childTest, "Tab Selection in given tab name is not available ",false);
			}
			if (SideMenuStatus.contains("fa-angle-double-left")) {
				SeleniumUtils.ClickOnItems(getPropValue("ML_1_1_id_list_expand"),"id");
			}
			
			SeleniumUtils.createTestNode(getPropValue("mylist") + "_Mylist Tab in select list ", "Mylist Tab select list for special charater Verification");
			//mylist tab
			if(SeleniumUtils.waitUntilElementDisplayed("//*[@id='searchdiv']//preceding-sibling::*", 05, "xpath")){
				reportGenerate(SeleniumUtils.childTest,  "Mylist tab is displayed in list panal", true);
				SeleniumUtils.ClickOnItems("//*[@id='searchdiv']//preceding-sibling::*", "xpath");
			}else{
				reportGenerate(SeleniumUtils.childTest,  "Mylist tab is not displayed in list panal", false);
				return;
			}
			String name = "//*[@id='myList']//child::li[@data-listname='#listName']"; 
			String listElement  =name.replace("#listName","Testcase2021-08-03_12_14_31");
			String listId="";
			System.out.println(listElement);
			if(SeleniumUtils.waitUntilElementDisplayed(listElement, 5, "xpath")){
				reportGenerate(SeleniumUtils.childTest, getPropValue("CSC_17_v_list_name")+" list name  displayed in list panal", true);
				listId = SeleniumUtils.getAttributefromField(listElement, "data-list-id", "xpath");
				SeleniumUtils.ClickOnItems(listElement, "xpath");
			}else{
				reportGenerate(SeleniumUtils.childTest, getPropValue("CSC_17_v_list_name")+" list name not displayed in list panal", false);
			}
			String tableElement = getPropValue("CSC_17_id_list_table").replace("#listId", listId);
			commonFunction(listId);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void commonFunction(String listId) {
		try{
			MyListTableBO myListBO= new MyListTableBO();
			myListBO.setModuleName("Mylist static list table validation");
			myListBO.setTableName("Testcase2021-08-03_12_14_31 list ");
			myListBO.setTableColumnName(getPropValue("Mylist_colum_Name"));
			myListBO.setTableHeaderXpath(getPropValue("Mylist_table_header").replace("#listid", listId));
			myListBO.setColumnVisiblityXpath(getPropValue("Mylist_colum_visibility").replace("#listid",listId));
			myListBO.setTableBodyXpath(getPropValue("Mylist_table_body").replace("#listid", listId));
			myListBO.setTableHyperLinkColumnName(getPropValue("Mylist_Hyper_Link_Colum_Name"));
			myListBO.setList_ID(listId);
			myListBO.setNumberOfRowCheck(1);
			
			MyListUtils.validateTable(myListBO);
			Thread.sleep(10000);
			 
		}catch (Exception e) {
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
			public static void reportGenerate(ExtentTest insertData, String msg, boolean status  ) {
				try{
					if(status){
						insertData.log(Status.PASS,MarkupHelper.createLabel( msg+" ",ExtentColor.GREEN));            
			        } else {
			        	insertData.log(Status.FAIL,
			                    MarkupHelper.createLabel(msg ,
			                            ExtentColor.RED));    
			        	 SeleniumUtils.testCase.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(CheckSpecialCharaterMain.moduleName,getPropValue("ExisitingVerifyInteraction"),1));
			        }	
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
}
