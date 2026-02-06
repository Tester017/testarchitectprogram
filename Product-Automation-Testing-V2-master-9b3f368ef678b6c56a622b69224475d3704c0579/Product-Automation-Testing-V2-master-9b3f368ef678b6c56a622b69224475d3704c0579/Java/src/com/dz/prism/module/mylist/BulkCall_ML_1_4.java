package com.dz.prism.module.mylist;

import java.util.ArrayList; 
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class BulkCall_ML_1_4 {
	public static boolean allContactZero=true;
	public static int  count;
	public static void bulkCall(){
		Map<String, List<Map<String, String>>> testdat;
		SeleniumUtils.testCase = SeleniumUtils.extendReports
                .createTest( getPropValue("Bulk_Call")+"_Bulk Call");
		ExtentTest reportList=null;	
		ExtentTest reportDynamicList=null;
		ExtentTest simple_share_reportList=null;
		ExtentTest dynamic_share_reportList=null;
		
		try {
			
			testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+getPropValue("ML_1_simple_list_create_v_testCasePath"));
			for(Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()){
				List<Map<String, String>> innerRows = testRows.getValue();			 
				for(Map<String,String> values : innerRows){	
					reportList = SeleniumUtils.testCase.createNode("Simple List");
					reportDynamicList = SeleniumUtils.testCase.createNode("Dynamic List ");
					simple_share_reportList = SeleniumUtils.testCase.createNode("Simple share List ");
					dynamic_share_reportList  = SeleniumUtils.testCase.createNode("Dynamic share List ");
					SeleniumUtils.webDriver.findElement(By.xpath(getPropValue("ML_1_3_mylist_tab")));					
					String listName =checkValue(values,"List Name")+SeleniumUtils.getCurrDate();
					values.replace("List Name", listName);
					DeleteContact_ML_1_14.Simplelistcreation(listName, reportList);				     
					AddQuickContact_ML_1_5.selectList(values, reportList);
					selectContact(listName,reportList);
					DeleteList_ML_1_13.deleteList(listName.trim(), reportList);				
					allContactZero =false;					
					String SideMenuStatus = SeleniumUtils.getAttributefromField(getPropValue("ML_1_1_id_list_expand"),"class","id");
					if (SideMenuStatus.contains("fa-angle-double-left")) {
						SeleniumUtils.ClickOnItems(getPropValue("ML_1_1_id_list_expand"),"id");
					}
					List<WebElement> dynamic =SeleniumUtils.webDriver.findElements(By.xpath(getPropValue("ML_1_3_all_dynamic_list")));	 
					for(WebElement dynamicList: dynamic){								 
						if(selectContact(dynamicList.getAttribute("data-listname").trim(),reportDynamicList)){
							break;
						}
						 
					}
					if(dynamic.size()==0){
						reportDynamicList.log(Status.PASS,MarkupHelper.createLabel( "Dynamic list not displaying in my list section",ExtentColor.GREY));    
					 }
					 else if(!allContactZero){
						 
						 reportDynamicList.log(Status.PASS,MarkupHelper.createLabel( "All the dynamic lists in My list section are Empty, not able to find the Contacts.",ExtentColor.GREY));    
					 }
					
					//get static view only email id
					 allContactZero = false ;
					 List<WebElement> staticViewOnly =SeleniumUtils.webDriver.findElements(By.xpath(getPropValue("ML_1_14_id_static_viewonly_list")));	 
					 for(WebElement simpleViewOnly: staticViewOnly){						  
						if(selectContact(simpleViewOnly.getAttribute("data-listname").trim(),simple_share_reportList)){
							break;
						}	
						 
					 }
					 if(staticViewOnly.size()==0){
						 simple_share_reportList.log(Status.PASS,MarkupHelper.createLabel( "Static (view only) list not displaying in my list section",ExtentColor.GREY));    
					 }
					 else if(!allContactZero){
						 simple_share_reportList.log(Status.PASS,MarkupHelper.createLabel( "All the static(view only) lists in My list section are Empty, not able to find the Contacts.",ExtentColor.GREY)); 
					 }
					 
					//get dynamic share
					 allContactZero = false ;
					 List<WebElement> dynamicShare =SeleniumUtils.webDriver.findElements(By.xpath(getPropValue("ML_1_3_share_dynamic_list")));	 
					 for(WebElement dshare: dynamicShare){						 
						if(selectContact(dshare.getAttribute("data-listname").trim(),dynamic_share_reportList)){
							break;
						}	
						 
					 }
					 if(dynamicShare.size()==0){
						 dynamic_share_reportList.log(Status.PASS,MarkupHelper.createLabel( " Dynamic share list not displaying in my list section",ExtentColor.GREY));    
					 }
					 else if(!allContactZero){
						 dynamic_share_reportList.log(Status.PASS,MarkupHelper.createLabel( "All the Dynamic share lists in My list section are Empty, not able to find the Contacts.",ExtentColor.GREY)); 
					 }
					 
					
				}
			}
		}catch (Exception e) {
			reportGenerate(reportList, "Bulk call not execute ", false);
		}
	}
	
	/**
	 * This method for select the list name
	 * @param listName
	 * @param reportList
	 * @return
	 */
	private static boolean selectContact(String listName, ExtentTest reportList) {
		try{
			String SideMenuStatus = SeleniumUtils.getAttributefromField(getPropValue("ML_1_1_id_list_expand"),"class","id");
			if (SideMenuStatus.contains("fa-angle-double-left")) {
				SeleniumUtils.ClickOnItems(getPropValue("ML_1_1_id_list_expand"),"id");
			}
			String listIdByName = getPropValue("ML_1_3_my_list_get_id_by_name");
			listIdByName = listIdByName.replace("?",listName);
			String listId = SeleniumUtils.getAttributefromField(listIdByName, "data-list-id", "xpath");	
			if(SeleniumUtils.waitUntilElementDisplayed(listIdByName, 10, "xpath")){
				SeleniumUtils.ClickOnItems(listIdByName, "xpath");
			}
			count=0;
			List<String> contact = new ArrayList<>();
			 
			  if(getTotalCount(listId, reportList)>0){
				  selectContact(contact,count,listId,listName,reportList);				  
		          if(selectCount(listId)>0){
					verifyBulkCallOption(contact,count,"bulkcall",reportList); 
                  }else {
                	closeTab(reportList);  
      				return false;
				  }
			  }else {
				closeTab(reportList);  
				return false;
			}
			 
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {

			//closeTab(reportList);
		}
		return allContactZero;
		
	}
/**
 * Check bulk call option
 * @param contact
 * @param count
 * @param listName
 * @param reportList
 */
	public static void verifyBulkCallOption(List<String> contact, int count, String type, ExtentTest reportList) {
		try{
			 if(type.equalsIgnoreCase("bulkcall")){
			    if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("ML_1_4_id_bulk_call_button"),10, "xpath")){
			    	 SeleniumUtils.ClickOnItems(getPropValue("ML_1_4_id_bulk_call_button"), "xpath");
			    }
			 }
			  SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			  SeleniumUtils.scriptWaitingTime(30);
			 if( SeleniumUtils.checkElementDisplayedProp(getPropValue("ML_1_4_id_existing_vm_dropdown"), "xpath")){
				 reportGenerate(reportList, "Call - Existing VM drop down is dispalying", true);
				 if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("ML_1_4_id_existing_vm_dropdown"),10, "xpath")){
					 SeleniumUtils.ClickOnItems(getPropValue("ML_1_4_id_existing_vm_dropdown"), "xpath");
				 }
				 if(SeleniumUtils.getCountOfDropdownList(getPropValue("ML_1_4_id_existing_vm_dropdown")+"/option", "xpath")>0){
					 reportGenerate(reportList, "Call - Existing VM's are displying drop down", true);
				 }else {
					 reportGenerate(reportList, "Call - Existing VM's are not displying drop down", false);
				 }
				 if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("ML_1_4_id_existing_vm_dropdown"),10, "xpath")){
					 SeleniumUtils.ClickOnItems(getPropValue("ML_1_4_id_existing_vm_dropdown"), "xpath");
				 }
			 }
			 else{
				 reportGenerate(reportList, "Call - Existing vm drop down not dispalying", false);
			 }
			 
			 //create new vm
			 if(SeleniumUtils.checkElementDisplayedProp(getPropValue("ML_1_4_id_create_new_vm"), "xpath")){
				 reportGenerate(reportList, "Call - create new VM link  avaliable", true);
				 WebElement p=SeleniumUtils.webDriver.findElement(By.linkText("Create New VM"));
				 p.click();
				 //SeleniumUtils.ClickOnItems("ML_1_4_id_create_new_vm", "xpath");
				 //voic msg title
				 if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("ML_1_4_id_new_voice_msg_title"),10, "xpath")){
					 reportGenerate(reportList, "Call - voice message title field is displaying", true);
					 SeleniumUtils.setValueToField(getPropValue("ML_1_4_id_new_voice_msg_title"), "test the title", "xpath");
					 SeleniumUtils.ClearFieldValue(getPropValue("ML_1_4_id_new_voice_msg_title"), "xpath");
					 closeVoiceMsg(reportList);
				 }else{
					 reportGenerate(reportList, "Call - voice message title field is not displaying", false);
				 }
			 }else{
				 reportGenerate(reportList, "Call - create new VM link are not avaliable", false);
			 }
			 
			 //check start call
			 if(SeleniumUtils.checkElementDisplayedProp(getPropValue("ML_1_4_id_start_button"), "xpath")){
				 reportGenerate(reportList, " Call - start call button is displaying", true);
			 }else{
				 reportGenerate(reportList, " Call - start call button is not displaying", false);
			 }
			 if(type.equalsIgnoreCase("bulkcall")){
			  verifyCallList(contact,count,reportList);
			 }
			  //get table contact
			  
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
/**
 * This Method for verify call list tab in contact
 * @param contact
 * @param count
 * @param reportList
 */

	private static void verifyCallList(List<String> contact, int count, ExtentTest reportList) {
		try{
			int page =0;
			  boolean skip=false; 
			  boolean btnEnab =false;
			  do{
				  ++page;
				  SeleniumUtils.ClickOnItems(getPropValue("ML_1_4_id_skip_checkbox"), "xpath");
				  if(SeleniumUtils.getAttributefromField(getPropValue("ML_1_4_id_skip_checkbox").replace("/td/div/input", ""), "data-skip", "xpath").equalsIgnoreCase("true")){
					  skip=true;
				  }
				  String showCount = SeleniumUtils.getTextfromField(getPropValue("ML_1_4_id_showing_count"), "xpath");
				  String show[]= showCount.split(" ");
				  int fromCount = Integer.parseInt(show[1].replaceAll("[^0-9]", ""));
				  int toCount = Integer.parseInt(show[3].replaceAll("[^0-9]", ""));
				  String accountname = getPropValue("ML_1_4_id_list_tab_contact");
				  accountname = accountname.replace("?", Integer.toString(getHeaderPosition("Account Name")));
				  List<WebElement> listContact = SeleniumUtils.webDriver.findElements(By.xpath(accountname));				  
				  for(WebElement con: listContact){
					  contact.remove(con.getText().trim());  
				  }
				  if((toCount-fromCount)+1==listContact.size()){
					  reportGenerate(reportList,"Bulk Call - "+page +" page in "+fromCount+" to "+toCount+" contact are avaliable", true);
				  }
				  if(SeleniumUtils.getAttributefromField(getPropValue("ML_1_4_id_next_enable"), "class", "xpath").contains("disable")){
					  //SeleniumUtils.selectOptGroupDropdownValue(getPropValue("ML_1_4_id_call_list_dropdown"), "all", "xpath");
					  SeleniumUtils.dropDownItemSelect(getPropValue("ML_1_4_id_call_list_dropdown"), "-1", "value", "xpath");
					  List<WebElement> allListContact = SeleniumUtils.webDriver.findElements(By.xpath(accountname));
					 reportGenerate(reportList, "Bulk Call - Select showing All contact option", true);
					 if(count== allListContact.size()){
						 reportGenerate(reportList, "Bulk Call - All contacts showing option in all the "+count+" contact are avaliable", true);
					 }
					  break;
				  }else{
					  SeleniumUtils.ClickOnItems(getPropValue("ML_1_4_id_next_button"), "xpath");
					  reportGenerate(reportList, "Bulk Call - Click next button for check remaining contacts",true);
					  btnEnab=true;
				  }
				  
			  }while (btnEnab) ;
			  if(skip){
				  reportGenerate(reportList, "Bulk Call - Skip contact ", true);  
			  }else{
				  reportGenerate(reportList, "Bulk Call - Skip contact ", false); 
			  }
			  if(contact.size()==0){
				  reportGenerate(reportList, "Bulk Call - All the contact are avaliable in the call list tab", true);
			  }else{
				  reportGenerate(reportList,"Bulk Call - "+contact.size()+ " contact are not avaliable in call list tab -"+contact.toString(), false);
			  }
			SeleniumUtils.ClickOnItems(getPropValue("ML_1_4_id_close_popup"), "xpath");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeTab(reportList);
		}
		
	}

/**
 * This method for select contact in list
 * @param contact
 * @param count
 * @param listId
 * @param listName
 * @param reportList
 */
	@SuppressWarnings("null")
	private static void selectContact(List<String> contact, int count, String listId, String listName, ExtentTest reportList) {
	    try{
	    	JavascriptExecutor js = null;	
	    	String selectContact = getPropValue("ML_1_3_select_table_checkbox");
			  selectContact = selectContact.replace("?", listId);
			  List<WebElement> selectCheckbox =  SeleniumUtils.webDriver.findElements(By.xpath(selectContact));
			  for(WebElement checkbox: selectCheckbox){
				  String cont = checkbox.toString().substring(checkbox.toString().indexOf("th:")+4).trim();
				  cont =cont.substring(0, (cont.length()-1));
				  int phonePosition = SingleContactVerify_ML_1_17.getHeaderPosition(listId, "Phone No."); 
				  WebElement element = SeleniumUtils.webDriver.findElement(By.xpath(cont+"//parent::div/following-sibling::div[contains(@class,'accountName')]"));
				  WebElement phoneNo = SeleniumUtils.webDriver.findElement(By.xpath(cont.replace("left", "right").replace("/div/input", "/div[contains(@class,'slick-cell l"+phonePosition+" r"+phonePosition+"')]")));
				  //select 15 contact with phone no
				  if(!phoneNo.getText().equalsIgnoreCase("-")){					  
					  count++;						 
					  if(count == 15){					
						  break;
					  }
					  contact.add(element.getText().trim());
				  }
				  try{
				   checkbox.click();
				  }catch (Exception e) {
					  try {
							WebDriverWait wait = new WebDriverWait(SeleniumUtils.webDriver, 30);
							wait.until(ExpectedConditions.elementToBeClickable(checkbox));
						}catch (Exception e2) {
							js.executeScript("arguments[0].click()",checkbox);
							e.printStackTrace();
						  }

				  }
				  
			  }
            allContactZero =true;
            reportGenerate(reportList,"Bulk Call - "+ count+" Contacts are selected - "+listName, true);
	    }catch (Exception e) {
			e.printStackTrace();
		}
		
	}
/**
 * 
 * @param listId
 * @return
 */
	public static int getHeaderPosition(String headerName) {
		int i=1;
		try{
				 List<WebElement> header = SeleniumUtils.webDriver.findElements(By.xpath(getPropValue("ML_1_4_id_list_tab_header")));
				  
				 //get header contact name column position
				 for(i=0;i<header.size();i++){				 
					 if(headerName.equalsIgnoreCase( header.get(i).getText())){
						 break;
					 }
					 
				 }
			}catch (Exception e) {
				 i=0;
			}
		return i+1;
			
		}


	private static void closeVoiceMsg(ExtentTest reportList) {
		 try{
			 SeleniumUtils.ClickOnItems(getPropValue("ML_1_4_id_new_voice_msg_close"), "xpath");
			 if(SeleniumUtils.checkElementDisplayedProp(getPropValue("ML_1_4_id_new_voice_msg_title"), "xpath")){				 
			 }else{
				 
			 }
		 }catch (Exception e) {
			// TODO: handle exception
		}
		
	}


	/**
	 * Get DataTable Selected Count 
	 * @param listId
	 * @return
	 */
	private static int selectCount(String listId) {
		String listPageSelectedCount = getPropValue("ML_1_3_select_count");
		int value =0;
		try{
			Thread.sleep(2000);
			listPageSelectedCount = listPageSelectedCount.replace("?", listId);
			SeleniumUtils.checkElementDisplayedProp(listPageSelectedCount, "xpath");			 
			listPageSelectedCount = SeleniumUtils.getTextfromField(listPageSelectedCount,"xpath");				 
			value = Integer.parseInt(listPageSelectedCount.replaceAll("[^0-9]", ""));
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
			
		}		 
		return  value;
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
		 * method to close the tab
		 * @param reportList 
		 */
			@SuppressWarnings("null")
			private static void closeTab(ExtentTest reportList) {
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
					 reportGenerate(reportList, e.toString(), false);
				}
			}
	/**
	 * get value from map
	 * @param values
	 * @param key
	 * @return
	 */
		public static String checkValue(Map<String, String> values, String key) {
			String result;
			 try{
				 result= values.get(key.trim()).toString().trim();
			 }
			 catch (Exception e) {
				 result = "";			 
			}
				
			return result;
		}
	/**
	 * Get the total count of showing contact
	 * @param listId
	 * @param report
	 * @return
	 */
		private static int getTotalCount(String listId, ExtentTest report) {
			String listPageCount =getPropValue("ML_1_3_total_count");
			int value =0;
			try{
				Thread.sleep(2000);
				listPageCount= listPageCount.replace("?", listId);	 
				SeleniumUtils.checkElementDisplayedProp(listPageCount, "xpath"); 
				listPageCount = SeleniumUtils.getTextfromField(listPageCount, "xpath");
				value = Integer.parseInt(listPageCount.replaceAll("[^0-9]", ""));
			}catch (Exception e) {			 
				return 0;
			}	
			return  value;
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
