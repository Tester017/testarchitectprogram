package com.dz.prism.module.mylist;

import java.io.IOException;
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
import com.dz.prism.module.employmentchange.EmploymentChangeMain;
import com.dz.prism.utils.SeleniumUtils;

public class MyList_ML_1_12 {
	
	public static ExtentTest insertData;
	public static ExtentTest verifyData;
	public static ExtentTest clearAndUpdate;
	public static boolean clearStatus;
	public static boolean updateStatus; 
	public static boolean accountVerifyStatus;
	public static boolean contactVerifyStatus;	 
	public static boolean categoryVerifyStatus;	
	public static boolean accuntFilterStatus ;
	public static boolean contactFilterStatus;
	public static boolean categoryStatus;
	
	/**
	 * Method to verification of accountFilter and countactFilter
	 */
	public static void contactSearch(){

		Map<String, List<Map<String, String>>> testdat;
		try {
					// Expand Tab section
			    SeleniumUtils.setTimeOut(3);
			    SeleniumUtils.closeToastMessage();
				String SideMenuStatus = SeleniumUtils.getAttributefromField(getPropValue("ML_1_1_id_list_expand"),"class","id");
				if (SideMenuStatus.contains("fa-angle-double-left")) {
					SeleniumUtils.ClickOnItems(getPropValue("ML_1_1_id_list_expand"),"id");
				}
				SeleniumUtils.setTimeOut(2);
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				SeleniumUtils.setTimeoutUntilVisibility(getPropValue("ML_1_12_id_search_tab"), "xpath");
				SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_search_tab"), "xpath"); //click search tab		
				testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+getPropValue("ML_1_12_v_testCasePath"));
				SeleniumUtils.testCase = SeleniumUtils.extendReports
		                .createTest(getPropValue("Search_Contact") + "_Search Contact");
				SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Verify Contact_Filter and Account_Filter");
				SeleniumUtils.childTest = SeleniumUtils.parentTest.createNode("Tab Selection");
				for(Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()){
					List<Map<String, String>> innerRows = testRows.getValue();	
				   if(testRows.getKey().equals("Dynamiclist_Create_Search")){			 
					  for(Map<String,String> values : innerRows){	
						SeleniumUtils.childTest = SeleniumUtils.parentTest.createNode("Contact Search - "+getPropValue("ML_1_12_v_row_id"));  
						String clearMsg = "Verifying the ClearAll button in both Account and Contact filter section";
						String updatMsg = "Update all the fields in the Account and Contacts Filter Section";
						SeleniumUtils.webDriver.findElement(By.xpath(getPropValue("ML_1_12_id_search_tab")));
						clearStatus = true;
						updateStatus = true;					 					 
						contactFilterStatus =true;
						accuntFilterStatus = true;
						categoryStatus = true;
						accountVerifyStatus  = true;
						contactVerifyStatus = true;				
						setCategory(values);			        
						setAccountfilterValue(values,"");
						String msgac = "Entering the input values in the Account Filter texbox-";
				     	reportGenerate(SeleniumUtils.childTest, msgac, accuntFilterStatus, values); 
						setContactFilterValue(values,"");
						String conmsg = "Entering the input values in the Contact Filter texbox -";
				     	 reportGenerate(SeleniumUtils.childTest, conmsg, contactFilterStatus, values);
						verifyContactFilter(values);
						verifyAccountFilter(values);
						reportGenerate(SeleniumUtils.childTest, clearMsg, clearStatus, values);
				    	reportGenerate(SeleniumUtils.childTest, updatMsg, updateStatus, values);
						clickSearchButton(values);
				    		  
					 }
				   }
			}
			SeleniumUtils.setTimeOut(5);
			closeTab();
			SeleniumUtils.setTimeOut(5);
			String SideMenuStatus2 = SeleniumUtils.getAttributefromField(getPropValue("ML_1_1_id_list_expand"),"class","id");
			if (SideMenuStatus2.contains("fa-angle-double-left")) {
				SeleniumUtils.ClickOnItems(getPropValue("ML_1_1_id_list_expand"),"id");
			}
		    SeleniumUtils.ClickOnItems(getPropValue("ML_1_3_mylist_tab"), "xpath");
		} catch (IOException e) {
			SeleniumUtils.childTest.log(Status.FAIL,
                    MarkupHelper.createLabel( "Search contact test case are not execute",
                            ExtentColor.RED));    
			e.printStackTrace();
		}  	 		 
	}
	/**
     * Method to click search Button
     * @param values
     */
    private static void clickSearchButton(Map<String, String> values) {
        String toastMsg = "";
        SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_search_buttton"), "xpath");
        SeleniumUtils.waitUntilElementHide("loading_screen", "id");
        try{
        toastMsg = SeleniumUtils.getToastMessage(); 
        SeleniumUtils.closeToastMessage();
        }
        catch (Exception e) {
            
        }
        if(!toastMsg.isEmpty()){
                    reportGenerate(SeleniumUtils.childTest, toastMsg ,false, values);
                    SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_clear_button"), "xpath");
        }
        else{
        	reportGenerate(SeleniumUtils.childTest, "Click the Search button in Contact search section", true, values);
        	DynamicListCreation_ML_1_6.excel_values= values;
        	DynamicListCreation_ML_1_6.keywords=checkValue(values, getPropValue("ML_1_12_v_keywords"));
        	DynamicListCreation_ML_1_6.category=checkValue(values, getPropValue("ML_1_12_v_category"));
        	DynamicListCreation_ML_1_6.insertData=SeleniumUtils.childTest; 
        	DynamicListCreation_ML_1_6.verifyfilter("Search Results");
        	SeleniumUtils.childTest =DynamicListCreation_ML_1_6.insertData;
            verifySearchResult(SeleniumUtils.childTest,values);          
            SeleniumUtils.waitUntilElementHide("loading_screen", "id");
            SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_clear_button"), "xpath");
          
        }
       
    }
	 
private static void verifySearchResult(ExtentTest report, Map<String, String> values) {
	try{	
		int searchselectedCount = 0;
		String listTabel =getPropValue("ML_1_3_select_all_Rows");
		String selectAllRows =listTabel.replace("?","SearchResults");				 				
		int searchTotalCount =CopySelectedToNewList_ML_1_3.getTotalCount("SearchResults");	
		CopySelectedToNewList_ML_1_3.selectedCount=searchTotalCount;
		String searchTitle = SeleniumUtils.getAttributefromField(getPropValue("ML_1_3_Search_Tabe"), "title", "xpath");
		String search_Tab_save_Button = SeleniumUtils.getAttributefromField(getPropValue("ML_1_3_search_Tab_save_Button"), "title", "xpath");
		String listType = SeleniumUtils.getAttributefromField(getPropValue("ML_1_3_list_type"), "title", "xpath");
		if(searchTitle.trim().equalsIgnoreCase("Search Results")){
			reportGenerate(report, "Verifying the new tab name called Search Results." , true,values);
		}else {
			reportGenerate(report, "Verifying the new tab name called Search Results." , false,values);
		}
		if(listType.trim().equalsIgnoreCase("Dynamic List")){
			reportGenerate(report, "The newly opened tab should be in Dynamic List." , true,values);
		}else {
			reportGenerate(report, "The newly opened tab should be in Dynamic List."  , false,values);
		}
		if(search_Tab_save_Button.trim().equalsIgnoreCase("save")){				 
		 reportGenerate(report, "The newly opened tab should contains Save button." , true,values);
		}else {
			reportGenerate(report, " The newly opened tab should contains Save button." , false,values);
		}			 
		if(searchTotalCount>0){
			 SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			 SeleniumUtils.ClickOnItems(selectAllRows, "xpath");		 
		     searchselectedCount = CopySelectedToNewList_ML_1_3.selectCount("SearchResults");
	    }
	    if(searchselectedCount>0){
	     if(!SeleniumUtils.isproduction){
		  CopySelectedToNewList_ML_1_3.saveList(report);
	     }else{
	    	  MyListMain.reportWriteSkip(SeleniumUtils.childTest, "We are runing in production mode so list save is skiped");
	     }
		  String SideMenuStatus = SeleniumUtils.getAttributefromField(getPropValue("ML_1_1_id_list_expand"),"class","id");
			if (SideMenuStatus.contains("fa-angle-double-left")) {
				SeleniumUtils.ClickOnItems(getPropValue("ML_1_1_id_list_expand"),"id");
			}
		  SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_search_tab"), "xpath"); //click search tab	
	     }			  
	}
	catch (Exception e) {
		e.printStackTrace();
	}finally {
		closeTab();
	}
	
		
	}
/**
 * Method to set catehory in field
 * @param values
 */
private static void setCategory(Map<String, String> values) {
		 String category = checkValue(values,getPropValue("ML_1_12_v_category"));
		 String keywords = checkValue(values,getPropValue("ML_1_12_v_keywords"));
		 String period_Filter = checkValue(values,getPropValue("ML_1_12_v_period_filter"));
		  
		 if(!category.isEmpty()){
			 String webElement = getPropValue("ML_1_12_id_category");
			 try{
				 List<WebElement> category_dropdown = SeleniumUtils.webDriver.findElements(By.xpath(webElement));
				 for(WebElement dropdown: category_dropdown){
					 String result = dropdown.getText().trim();
					 if(result.equalsIgnoreCase(category)){
						 dropdown.click();						  						 
						 break;
					 }
				 }
			 }catch (Exception e) {
				 categoryStatus=false;
			}	 
		 }		 		 
		 if(!keywords.isEmpty()){
			 String keywordsArr[] =keywords.split("~");
			 for(int i=0;i<keywordsArr.length;i++){
				 if(!SeleniumUtils.dropDownInputTokenize(getPropValue("ML_1_12_id_keywords"),keywordsArr[i], "xpath")){
					 categoryStatus=false;
				 }
			 }
		 }		 		 
		 if(!period_Filter.isEmpty()){
			 
			 String webElement = getPropValue("ML_1_12_id_period_filter_list");
			 try{
				 SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_period_filter"), "xpath");
				 List<WebElement> period = SeleniumUtils.webDriver.findElements(By.xpath(webElement));
				 for(WebElement list: period){
					 String result = list.getText().trim();
					 if(result.equalsIgnoreCase(period_Filter)){
						 list.click();
						 break;
					 }
				 }
			 }catch (Exception e) {
				 categoryStatus=false;
			 }	 
		 }		 
		 SeleniumUtils.setTimeOut(2); 
		 String  criteriaCheckBoxElement = getPropValue("ML_1_12_id_criteria_checkbox");
		 try{
			 List<WebElement> checkBox = SeleniumUtils.webDriver.findElements(By.xpath(criteriaCheckBoxElement));
			 if(checkBox.size()>0){
				 for(WebElement list: checkBox){					 				  
						 list.click();						 			 
				 }
			 }
			 List<String> category_name = new ArrayList<String>();
			 String  criteriaCheckBoxElement1 = MyList_ML_1_12.getPropValue("ML_1_12_id_criteria_checkbox_newlist_text");			     
				 List<WebElement> checkBox1 = SeleniumUtils.webDriver.findElements(By.xpath(criteriaCheckBoxElement1));
				 if(checkBox.size()>0){
					 for(WebElement list: checkBox1){
						 category_name.add(list.getText());	
						 
					 }
				 }
			 
			 DynamicListCreation_ML_1_6.category_name=(ArrayList<String>) category_name;
			 
			 SeleniumUtils.setTimeOut(5); 
		 }catch (Exception e) {
			 categoryStatus=false;
		 }	 		 			 
		 reportGenerate(SeleniumUtils.childTest, "The given input value is entered in the Category, Keyword and Criteria field", categoryStatus, values);
	} 
/**
 * verifyAccountFilter field value from excel
 * @param values
 */
	private static void verifyAccountFilter(Map<String, String> values) {
		try{
			
			
			SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_acc_filter"),"xpath"); //click accoutFilter button
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("ML_1_12_id_acc_fliter_clear_button"), 5, "xpath")){
				SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_acc_fliter_clear_button"), "xpath");
				 reportGenerate(SeleniumUtils.childTest, "Account filter page in clear button is displayed", true);
			}else{
				 reportGenerate(SeleniumUtils.childTest, "Account filter page in clear button is displayed", true);
			}
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("ML_1_12_id_acc_fliter_clear_confirm_button"), 5, "xpath")){
				SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_acc_fliter_clear_confirm_button"), "xpath");
			}
			
			SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_acc_filter"),"xpath"); //click accoutFilter button
			
			SeleniumUtils.createTestNode(getPropValue("Search_Contact")+" Mylist account filter field is clear or not  _"+getExcelValue(values, "ML_1_12_v_row_id"), "When click clear all button in account filter all fields are clear or not");
			searchFilterIsClearOrNot("ML_1_12_v_Investor", getPropValue("ML_1_12_id_accfilter_investor"), values);
			searchFilterIsClearOrNot("ML_1_12_v_inv_orient", getPropValue("ML_1_12_id_accfilter_invesment"), values);
			searchFilterIsClearOrNot("ML_1_12_v_acregion", getPropValue("ML_1_12_id_accfilter_accregion"), values);
			searchFilterIsClearOrNot("ML_1_12_v_state", getPropValue("ML_1_12_id_accfilter_state"), values); 
			searchFilterIsClearOrNot("ML_1_12_v_country", getPropValue("ML_1_12_id_accfilter_country"), values);
			searchFilterIsClearOrNot("ML_1_12_v_ps_code", getPropValue("ML_1_12_id_accfilter_ps_code"), values);  
			searchFilterIsClearOrNot("ML_1_12_v_inv_style", getPropValue("ML_1_12_id_accfilter_investmentstyle"), values);  
			searchFilterIsClearOrNot("ML_1_12_v_inv_sector", getPropValue("ML_1_12_id_accfilter_inv_sector"), values);
			searchFilterIsClearOrNot("ML_1_12_v_inv_sub_sector", getPropValue("ML_1_12_id_accfilter_inv_sub_sector"), values);  
			searchFilterIsClearOrNot("ML_1_12_v_inv_location", getPropValue("ML_1_12_id_accfilter_inv_location"), values);
			searchFilterIsClearOrNot("ML_1_12_v_market_cap", getPropValue("ML_1_12_id_accfilter_mktcap"), values);  
			searchFilterIsClearOrNot("ML_1_12_v_turnover_rate", getPropValue("ML_1_12_id_accfilter_turnover"), values);  
			  
			SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_acc_filter_submit"),"id");	 
			
		    setAccountfilterValue(values, "");
			 
			String msg = "Verifying the Account filter values with the given input data";
	    	reportGenerate(SeleniumUtils.childTest, msg, accountVerifyStatus, values);	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
private static void searchFilterIsClearOrNot(String propsValue, String filterElement, Map<String, String> values) {
	try{
		SeleniumUtils.scrollUntilElementView(filterElement, "xpath");
		if(SeleniumUtils.checkElementDisplayedProp(filterElement+"//li[@class='Token']", "xpath")){
			reportGenerate(SeleniumUtils.childTest, getPropValue("propsValue")+" field is not empty when click clear all field", false);
		}else{
			reportGenerate(SeleniumUtils.childTest, getPropValue("propsValue")+" field is  empty when click clear all field", true);
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	
}
/**
 * verifyContactFilter field value from excel
 * @param values
 */
	private static void verifyContactFilter(Map<String, String> values) {
		 SeleniumUtils.createTestNode(getPropValue("Search_Contact")+" Mylist contact filter field is clear or not  _"+getExcelValue(values, "ML_1_12_v_row_id"), "When click clear all button in contact filter all fields are clear or not");
		 SeleniumUtils.scrollUntilElementView(getPropValue("ML_1_12_id_contact_filter1"), "xpath");
		 
		 if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("ML_1_12_id_contact_filter1"), 10, "xpath")){
			 SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_contact_filter1"), "xpath");//click contact Filter button
		 }else{
			 reportGenerate(SeleniumUtils.childTest, "contact filter button is not displayed", false);
			 return;
		 }
		 
		 if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("ML_1_12_id_contact_filter_clear_button"), 5, "xpath")){
			 SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_contact_filter_clear_button"), "xpath");
			 reportGenerate(SeleniumUtils.childTest, "contact filter page in clear button is displayed", true);
		 }else{
			 reportGenerate(SeleniumUtils.childTest, "contact filter page in clear button is not displayed", false);
		 }
		 if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("ML_1_12_id_contact_filter_clear_confirm_button"), 5, "xpath")){
			 SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_contact_filter_clear_confirm_button"), "xpath");
		 }
		 //check contact filter button
		 if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("ML_1_12_id_contact_filter1"), 10, "xpath")){
			 SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_contact_filter1"), "xpath");//click contact Filter button
		 }else{
			 reportGenerate(SeleniumUtils.childTest, "contact filter button is not displayed", false);
			 return;
		 }
		 searchFilterIsClearOrNot("ML_1_12_v_corporate_title_value", getPropValue("ML_1_12_id_corporate_title"), values);
		 searchFilterIsClearOrNot("ML_1_12_v_job_function_value", getPropValue("ML_1_12_id_job_function"), values);
		 searchFilterIsClearOrNot("ML_1_12_v_industry_value", getPropValue("ML_1_12_id_industry"), values);
		 searchFilterIsClearOrNot("ML_1_12_v_macro_sector_value", getPropValue("ML_1_12_id_macro_sector"), values);
		 searchFilterIsClearOrNot("ML_1_12_v_tag_value", getPropValue("ML_1_12_id_tag"), values);
		 searchFilterIsClearOrNot("ML_1_12_v_sub_sector_value", getPropValue("ML_1_12_id_sub_sector"), values);
		 searchFilterIsClearOrNot("ML_1_12_v_funds_managed_value", getPropValue("ML_1_12_id_funds_managed"), values);  
		 SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_submit_button") , "xpath");
	     clearStatus=true;
	     setContactFilterValue(values,"");
	      
	     String msg = "Verifying the Contact filter values with the given input data ";
     	 reportGenerate(SeleniumUtils.childTest, msg, contactVerifyStatus, values);
	}
 
/**
 * setCountFilter value in field
 * @param values
 * @param string 
 */
	public static void setContactFilterValue(Map<String, String> values,String dynamic) {
		try{	
		    if(dynamic.equalsIgnoreCase("dynamic")){
	            SeleniumUtils.js.executeScript("$('#contFilter .contfilterlabl').click();");
	        }
	        else{
	              SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_contact_filter1"), "xpath");
	        }
		    searchFilter("ML_1_12_v_corporate_title_value", getPropValue("ML_1_12_id_corporate_title"), values);
		    searchFilter("ML_1_12_v_job_function_value", getPropValue("ML_1_12_id_job_function"), values);
		    searchFilter("ML_1_12_v_industry_value", getPropValue("ML_1_12_id_industry"), values);
		    searchFilter("ML_1_12_v_macro_sector_value", getPropValue("ML_1_12_id_macro_sector"), values);
		    searchFilter("ML_1_12_v_tag_value", getPropValue("ML_1_12_id_tag"), values);
		    searchFilter("ML_1_12_v_sub_sector_value", getPropValue("ML_1_12_id_sub_sector"), values);
		    searchFilter("ML_1_12_v_funds_managed_value", getPropValue("ML_1_12_id_funds_managed"), values);   
		     
			SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_submit_button") , "xpath");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * set AccountFilter value
	 * @param values
	 */
	public static void setAccountfilterValue(Map<String, String> values,String dynamic){
		try{ 
			if(dynamic.equalsIgnoreCase("dynamic")){
                SeleniumUtils.js.executeScript("$('#acctFilter .accfilterlabl').click();");
            }
            else{
                SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_acc_filter"),"xpath");   
            }
	     	SeleniumUtils.setTimeoutUntilVisibility(getPropValue("ML_1_12_id_accfilter_investor_visible"), "id");		 
			 
			String Total_Equity_Assets_Value_From  = checkValue(values,getPropValue("ML_1_12_v_eq_from"));
			String Total_Equity_Assets_Value_To    = checkValue(values,getPropValue("ML_1_12_v_eq_to"));
			searchFilter("ML_1_12_v_Investor", getPropValue("ML_1_12_id_accfilter_investor"), values);
			searchFilter("ML_1_12_v_inv_orient", getPropValue("ML_1_12_id_accfilter_invesment"), values);
			searchFilter("ML_1_12_v_acregion", getPropValue("ML_1_12_id_accfilter_accregion"), values);
			searchFilter("ML_1_12_v_state", getPropValue("ML_1_12_id_accfilter_state"), values); 
			searchFilter("ML_1_12_v_country", getPropValue("ML_1_12_id_accfilter_country"), values);
			searchFilter("ML_1_12_v_ps_code", getPropValue("ML_1_12_id_accfilter_ps_code"), values);  
			searchFilter("ML_1_12_v_inv_style", getPropValue("ML_1_12_id_accfilter_investmentstyle"), values);  
			searchFilter("ML_1_12_v_inv_sector", getPropValue("ML_1_12_id_accfilter_inv_sector"), values);
			searchFilter("ML_1_12_v_inv_sub_sector", getPropValue("ML_1_12_id_accfilter_inv_sub_sector"), values);  
			searchFilter("ML_1_12_v_inv_location", getPropValue("ML_1_12_id_accfilter_inv_location"), values);
			searchFilter("ML_1_12_v_market_cap", getPropValue("ML_1_12_id_accfilter_mktcap"), values);  
			searchFilter("ML_1_12_v_turnover_rate", getPropValue("ML_1_12_id_accfilter_turnover"), values);  
			 
			if(!Total_Equity_Assets_Value_From.isEmpty()){
				SeleniumUtils.setValueToField(getPropValue("ML_1_12_id_acc_filter_equityfrom"), Total_Equity_Assets_Value_From,"xpath");
				  
			}
			if(!Total_Equity_Assets_Value_To.isEmpty()){
				SeleniumUtils.setValueToField(getPropValue("ML_1_12_id_acc_filter_equityto"), Total_Equity_Assets_Value_To,"xpath");
			}			 
	     	SeleniumUtils.ClickOnItems(getPropValue("ML_1_12_id_acc_filter_submit"),"id");	     	
	     	     	 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * generate a report
	 * @param SeleniumUtils.childTest
	 * @param msg
	 * @param status
	 * @param values
	 */
	public static void reportGenerate(ExtentTest insertData, String msg, boolean status, Map<String, String> values) {
		if(status){
			insertData.log(Status.PASS,MarkupHelper.createLabel( msg+" "+ values.get(getPropValue("ML_1_12_v_row_id")),ExtentColor.GREEN));            
        } else {
        	insertData.log(Status.FAIL,
                    MarkupHelper.createLabel(msg+ values.get(getPropValue("ML_1_12_v_row_id")),
                            ExtentColor.RED));          
        }		
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
		        	 SeleniumUtils.testCase.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(EmploymentChangeMain.moduleName,getPropValue("ExisitingVerifyInteraction"),1));
		        }	
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
	 * method to close the tab
	 * @param reportList 
	 */
		@SuppressWarnings("null")
		private static void closeTab() {
			try{
				JavascriptExecutor js = null;		 
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				List<WebElement> closeTab =SeleniumUtils.webDriver.findElements(By.xpath(getPropValue("ML_1_3_close_tab")));
				for(WebElement close : closeTab){
					try{ 
						if(close.getAttribute("data-list-id").equalsIgnoreCase("SearchResults")){
							close.click();
							if(SeleniumUtils.checkElementDisplayedProp(getPropValue("ML_1_3_tab_close_yes"),"xpath")){
							   SeleniumUtils.ClickOnItems(getPropValue("ML_1_3_tab_close_yes"),"xpath");
							}
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
		public static void searchFilter( String propsValue,String element, Map<String, String> values) {
			try{
				SeleniumUtils.createTestNode(getPropValue("Search_Contact")+" Mylist  "+getPropValue(propsValue) +" filter _"+getExcelValue(values, "ML_1_12_v_row_id"), "Set excel value into filters");
				SeleniumUtils.scrollUntilElementView(element, "xpath");
				//check filter search
				if(SeleniumUtils.waitUntilElementDisplayed(element, 10, "xpath")){
					reportGenerate(SeleniumUtils.childTest, getPropValue(propsValue)+" filter in search field is displayed ", true);
				}else{
					reportGenerate(SeleniumUtils.childTest, getPropValue(propsValue)+" filter in search field is not displayed ", false);
					return;
				}
				if(getExcelValue(values, propsValue).isEmpty()){
					reportGenerate(SeleniumUtils.childTest, getPropValue(propsValue)+" filter in search field excel value is empty ", true);
				}
				//search filter
				String input[]= getExcelValue(values,propsValue).split("~");
				for(int i=0 ; i<input.length;i++){
					SeleniumUtils.setValueToField(element+"//child::input", input[i].trim(), "xpath");
					String dropDownList =element+"//following-sibling::ul/li["+SeleniumUtils.xpathContaintsText(input[i])+"]";
					if(SeleniumUtils.waitUntilElementDisplayed(dropDownList, 3, "xpath")){
						reportGenerate(SeleniumUtils.childTest, getPropValue(propsValue)+" filter in searched value '"+input[i]+"' is displayed in dropdown ", true);
						SeleniumUtils.ClickOnItems(dropDownList, "xpath");
					}else{
						reportGenerate(SeleniumUtils.childTest, getPropValue(propsValue)+" filter in searched value '"+input[i]+"' is not displayed in dropdown ", false);
					}
					SeleniumUtils.ClearFieldValue(element+"//child::input", "xpath");
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		/**
		 * get value from map
		 * @param values
		 * @param key
		 * @return
		 */
			public static String getExcelValue(Map<String, String> values, String key) {
				String result;
				 try{
					 result= values.get(getPropValue(key.trim())).trim();
				 }
				 catch (Exception e) {
					 result = "";
					 e.printStackTrace();
				}
					
				return result;
			}


}
