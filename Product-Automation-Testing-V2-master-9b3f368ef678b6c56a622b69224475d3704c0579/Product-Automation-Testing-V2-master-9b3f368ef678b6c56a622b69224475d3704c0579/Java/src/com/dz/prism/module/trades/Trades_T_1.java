package com.dz.prism.module.trades;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper; 
import com.dz.prism.module.employmentchange.EmploymentChangeMain;
import com.dz.prism.utils.SeleniumUtils;

public class Trades_T_1 {
	public static ExtentTest lableTest;	
	public static Double grossComm = 0.0;
	public static int quantity = 0;
	public static Double netValue = 0.0;
	public static Map<String, String> filterData= new HashMap<String, String>(); 
	public static void readExcelData() {
		try {
			SeleniumUtils.testCase = SeleniumUtils.extendReports.createTest(
					getPropValue("trades_id") + "_Trades");
			SeleniumUtils.parentTest = SeleniumUtils.testCase
					.createNode("Trades - verify the show & hide filter");
			 
			Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(
					SeleniumUtils.UserDirVar + getPropValue("T_1_v_testCasePath"));
			lableTest=SeleniumUtils.parentTest.createNode("Trades_T_1");
			SeleniumUtils.childTest = lableTest.createNode("Selection Tab ");
			if(SeleniumUtils.tabSelection("Trades")){
				reportGenerate(SeleniumUtils.childTest, "Trades tab is Displayed", true);
			}else{
				reportGenerate(SeleniumUtils.childTest, "Trades tab is not Displayed", false);
				return;
			}
			for(Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
				List<Map<String, String>> innerRows = testRows.getValue();
				for (Map<String, String> values : innerRows) {
				    doFilters(values);
				}
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
/**
 * Check show and hide button
 * filter table	
 * @param values
 */
    private static void doFilters(Map<String, String> values) {
    	try{
    		
    		//show or hide filter 
    		if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("T_1_id_filter_on_off"), 10, "xpath")){
    			reportGenerate(SeleniumUtils.childTest, "Trades in show or hide Filter is displayed", true);
    			SeleniumUtils.ClickOnItems(getPropValue("T_1_id_filter_on_off"), "xpath");
    		}else{
    			reportGenerate(SeleniumUtils.childTest, "Trades in show or hide Filter is not displayed", false);
    			return;
    		}
    		//check employment name 
    		SeleniumUtils.childTest = lableTest.createNode("Employee Name Filter "+getExcelValue(values, "T_1_v_row_id"));
    		SeleniumUtils.scrollUntilElementView(getPropValue("T_1_id_filter_Employee_name"), "xpath");
    		if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("T_1_id_filter_Employee_name"), 20, "xpath")){
    			reportGenerate(SeleniumUtils.childTest, "Trades in emp name filters field is displayed", true);
    			SetTokensValues(getPropValue("T_1_v_employment_name"),getPropValue("T_1_id_filter_Employee_name"),getExcelValue(values, "T_1_v_employment_name"));
    			verifyFilterText(values);
    			if(!getExcelValue(filterData,"T_1_v_employment_name").isEmpty()){
    				verifyTableFilterData(values);
    			}
    		}else{
    			reportGenerate(SeleniumUtils.childTest, "Trades in emp name filters field is not displayed", false);
    		}
    		//check ticker
    		SeleniumUtils.childTest = lableTest.createNode("Ticker Filter "+getExcelValue(values, "T_1_v_row_id"));
    		SeleniumUtils.scrollUntilElementView(getPropValue("T_1_id_filter_ticker"), "xpath");
    		if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("T_1_id_filter_ticker"), 20, "xpath")){
    			reportGenerate(SeleniumUtils.childTest, "Trades in ticker filter field is displayed", true);
    			SetTokensValues(getPropValue("T_1_v_ticker"),getPropValue("T_1_id_filter_ticker"),getExcelValue(values, "T_1_v_ticker"));
    			verifyFilterText(values);
    			if(!getExcelValue(filterData,"T_1_v_ticker").isEmpty()){
    				verifyTableFilterData(values);
    			}
    		}else{
    			reportGenerate(SeleniumUtils.childTest, "Trades in ticker filters field is not displayed", false);
    		}
    		
    		//check  product
    		SeleniumUtils.childTest = lableTest.createNode("product Filter "+getExcelValue(values, "T_1_v_row_id"));
    		SeleniumUtils.scrollUntilElementView(getPropValue("T_1_id_filter_product"), "xpath");
    		if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("T_1_id_filter_product"), 20, "xpath")){
    			reportGenerate(SeleniumUtils.childTest, "Trades in product filter field is displayed", true);
    			SetTokensValues(getPropValue("T_1_v_product"),getPropValue("T_1_id_filter_product"),getExcelValue(values, "T_1_v_product"));
    			verifyFilterText(values);
    			if(!getExcelValue(filterData,"T_1_v_product").isEmpty()){
    				verifyTableFilterData(values);
    			}
    		}else{
    			reportGenerate(SeleniumUtils.childTest, "Trades in product filters field is not displayed", false);
    		}
    		//check  desk
    		SeleniumUtils.childTest = lableTest.createNode("Desk Filter "+getExcelValue(values, "T_1_v_row_id"));
    		SeleniumUtils.scrollUntilElementView("desk", "id");
    		if(SeleniumUtils.waitUntilElementDisplayed("desk", 20, "id")){
    			reportGenerate(SeleniumUtils.childTest, "Trades in desk filter field is displayed", true);
    			SetTokensValues(getPropValue("T_1_v_Desk"),getPropValue("T_1_id_filter_desk"),getExcelValue(values, "T_1_v_Desk"));
    			verifyFilterText(values);
    			if(!getExcelValue(filterData,"T_1_v_Desk").isEmpty()){
    				verifyTableFilterData(values);
    			}
    		}else{
    			reportGenerate(SeleniumUtils.childTest, "Trades in desk filters field is not displayed", false);
    		}
    		//check Account name 
    		SeleniumUtils.childTest = lableTest.createNode("Account Name Filter "+getExcelValue(values, "T_1_v_row_id"));
    		SeleniumUtils.scrollUntilElementView("tranid", "id");
    		if(SeleniumUtils.waitUntilElementDisplayed("tranid", 20, "id")){
    			reportGenerate(SeleniumUtils.childTest, "Trades in Account name filter field is displayed", true);
    			SetTokensValues(getPropValue("T_1_v_account_name"),getPropValue("T_1_id_filter_account_name"),getExcelValue(values, "T_1_v_account_name"));
    			verifyFilterText(values);
    			if(!getExcelValue(filterData,"T_1_v_account_name").isEmpty()){
    				verifyTableFilterData(values);
    			}
    		}else{
    			reportGenerate(SeleniumUtils.childTest, "Trades in Account name filters field is not displayed", false);
    		}
    		clearAllFilter(values);
    	}catch (Exception e) {
			e.printStackTrace(); 
		}
		
	}
/**
 * Click clear all button
 * validate all filter field is clear or not
 * @param values
 */
    private static void clearAllFilter(Map<String, String> values) {
		try{
			SeleniumUtils.js.executeScript("window.scrollTo(0, 0)");
			SeleniumUtils.childTest = lableTest.createNode("Clear All Filter "+getExcelValue(values, "T_1_v_row_id"));
			SeleniumUtils.ClickOnItems(getPropValue("T_1_id_filter_clear_all"), "xpath");
			Thread.sleep(5000);
			verifyFilterClearOrNot(getPropValue("T_1_v_employment_name"),getPropValue("T_1_id_filter_Employee_name"));
			verifyFilterClearOrNot(getPropValue("T_1_v_ticker"),getPropValue("T_1_id_filter_ticker"));
			verifyFilterClearOrNot(getPropValue("T_1_v_product"),getPropValue("T_1_id_filter_product"));
			verifyFilterClearOrNot(getPropValue("T_1_v_Desk"),getPropValue("T_1_id_filter_desk"));
			verifyFilterClearOrNot(getPropValue("T_1_v_account_name"),getPropValue("T_1_id_filter_account_name"));
			SeleniumUtils.js.executeScript("window.scrollTo(0, 0)");
			SeleniumUtils.ClickOnItems(getPropValue("T_1_id_filter_on_off"), "xpath");
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("T_1_id_filter_container"), 10, "xpath")){
				reportGenerate(SeleniumUtils.childTest, "When Click hide filter button filters is not hide",false);	
			}else{
				reportGenerate(SeleniumUtils.childTest, "When Click hide filter button filters fields is hide",true);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
/**
 * scroll until filter Element visible
 * validate filter field have any searched data
 * @param filterName
 * @param filterElement
 */
	private static void verifyFilterClearOrNot(String filterName, String filterElement) {
		try{
			SeleniumUtils.scrollUntilElementView(filterElement, "xpath");
			if(SeleniumUtils.checkElementDisplayedProp(filterElement+"/li[@class='Token']", "xpath")){
				reportGenerate(SeleniumUtils.childTest, filterName+" filter field in filter value is not clear when click Clear all button ", false);
			}else{
				reportGenerate(SeleniumUtils.childTest, filterName+" filter field in filter value is  clear when click Clear all button ", true);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
/**
 * compare total count value and table count value for quantity,gross comm,net value
 * @param headerWithPosition
 */
	private static void VerifyTotalCount(Map<Integer, String> headerWithPosition) {
		try{
			for(Map.Entry<Integer,String> tvalue : headerWithPosition.entrySet()){
				if(tvalue.getValue().trim().equalsIgnoreCase(getPropValue("T_1_v_gross_comm"))){
				      int tableGrossComm = convertInterger(SeleniumUtils.getTextfromField(getPropValue("T_1_id_table_header_total_value")+"["+(tvalue.getKey()+1)+"]","xpath"));
				      if(tableGrossComm==Math.round(grossComm)){
				    	  reportGenerate(SeleniumUtils.childTest, "Table gross comm values '"+grossComm+"' and show total gross commm value '"+tableGrossComm+"' count is is matched", true);
				      }else{
				    	  reportGenerate(SeleniumUtils.childTest, "Table gross comm values '"+grossComm+"' and show total gross commm value '"+tableGrossComm+"' count is missmatched", false);
				      }
				}
				if(tvalue.getValue().trim().equalsIgnoreCase(getPropValue("T_1_v_quantity"))){
					  int tableQuantity = convertInterger(SeleniumUtils.getTextfromField(getPropValue("T_1_id_table_header_total_value")+"["+(tvalue.getKey()+1)+"]","xpath"));
					  if(tableQuantity==Math.round(quantity)){
				    	  reportGenerate(SeleniumUtils.childTest, " quantity count from Table  '"+quantity+"' and showing total quantity count '"+tableQuantity+"' count is matched", true);
				      }else{
				    	  reportGenerate(SeleniumUtils.childTest, "quantity count from Table '"+quantity+"' and showing total quantity count '"+tableQuantity+"' count is missmatched", false);
				      }
				}
				if(tvalue.getValue().trim().equalsIgnoreCase(getPropValue("T_1_v_net_value"))){
					 int tableNetValue = convertInterger(SeleniumUtils.getTextfromField(getPropValue("T_1_id_table_header_total_value")+"["+(tvalue.getKey()+1)+"]","xpath"));
					 if(tableNetValue==Math.round(netValue)){
				    	  reportGenerate(SeleniumUtils.childTest, " Netvalue count from table '"+netValue+"' and showing total Netvalue count '"+tableNetValue+"' count is matched", true);
				      }else{
				    	  reportGenerate(SeleniumUtils.childTest, "Netvalue count from table '"+netValue+"' and showing total Netvalue count '"+tableNetValue+"' count is missmatched", false);
				      }
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
/**
 * Get table header name map with position
 * Get table page count and verify filter data is available or not 
 * @param values
 */
	private static void verifyTableFilterData(Map<String, String> values) {
		try{
			Map<Integer,String> headerWithPosition = new LinkedHashMap<>();
			headerWithPosition = getTableHeaderName(values);
			grossComm =0.0;
			netValue =0.0;
			quantity =0;
			verifyTableRowData(headerWithPosition);
			//move to page end
			java.awt.Robot robot = new java.awt.Robot();
			robot.keyPress(KeyEvent.VK_END);
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("T_1_id_last_page_table"), 10, "xpath")){
				int pageCount  =convertInterger(SeleniumUtils.getTextfromField(getPropValue("T_1_id_last_page_table"), "xpath"));
				for(int i=1 ;i<pageCount ;i++){
					robot.keyPress(KeyEvent.VK_END);
					if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("T_1_id_page_next_button"), 10, "xpath")){
						SeleniumUtils.ClickOnItems(getPropValue("T_1_id_page_next_button"), "xpath");
						verifyTableRowData(headerWithPosition);
					}else{
						reportGenerate(SeleniumUtils.childTest, "Trades table in "+i+" page next button is not displayed", false);
					}
				}
			}
			//check table total count value for gross com , quantity, net value
			VerifyTotalCount(headerWithPosition);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			 SeleniumUtils.js.executeScript("window.scrollTo(0, 0)");
		}
		
	}
/**
 * Convert String to Integer 
 * @param pageCount
 * @return
 */
	private static int convertInterger(String pageCount) {
		int count = 0;
		try{
			count = Integer.valueOf(pageCount.replaceAll("[^0-9]", ""));
		}catch (Exception e) {
			return 0;
		}
		return count;
	}
/**
 * Convert String to Double	
 * @param pageCount
 * @return
 */
	private static Double convertDouble(String pageCount) {
		Double count = 0.0;
		try{
			count = Double.valueOf(pageCount.replaceAll("[^0-9.]", ""));
		}catch (Exception e) {
			return 0.0;
		}
		return count;
	}
/**
 * Verify Table row data contain filter result or not
 * @param headerWithPosition
 */
	private static void verifyTableRowData(Map<Integer, String> headerWithPosition) {
		 try{
			SeleniumUtils.scrollUntilElementView(getPropValue("T_1_id_table_body"), "xpath");
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("T_1_id_table_body"), 10, "xpath")){
				List<WebElement> InterestTableBody =SeleniumUtils.webDriver.findElements(By.xpath(getPropValue("T_1_id_table_body")));
				SeleniumUtils.js.executeScript("window.scrollTo(0, 0)");
				if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("T_1_id_table_body")+"[1]//td[2]", 20, "xpath")){
					for(int i=0 ;i<InterestTableBody.size();i++){
						Map<String, String> InterestTableDetails = new LinkedHashMap<>();
						for(Map.Entry<Integer,String> tvalue : headerWithPosition.entrySet()){
							String element = getPropValue("T_1_id_table_body")+"["+(i+1)+"]//td["+(tvalue.getKey()+1)+"]";
							if(tvalue.getValue().trim().equalsIgnoreCase(getPropValue("T_1_v_gross_comm"))){
								grossComm += convertDouble(SeleniumUtils.getAttributefromField(element,"data-order", "xpath"));
							}
							if(tvalue.getValue().trim().equalsIgnoreCase(getPropValue("T_1_v_quantity"))){
								quantity += convertDouble(SeleniumUtils.getAttributefromField(element,"data-order", "xpath"));
							}
							if(tvalue.getValue().trim().equalsIgnoreCase(getPropValue("T_1_v_net_value"))){
								netValue += convertDouble(SeleniumUtils.getAttributefromField(element,"data-order", "xpath"));
							}
							InterestTableDetails.put(tvalue.getValue(), SeleniumUtils.getTextfromField(element, "xpath"));
						}
						validateRowData(InterestTableDetails);
					}
					for(int j=headerWithPosition.size()-1;j!=0;j--){
						String element = getPropValue("T_1_id_table_body")+"//td["+(j)+"]";
						 SeleniumUtils.scrollUntilElementView(element, "xpath");
					 }
				} 
			}
		 }catch (Exception e) {
			 e.printStackTrace();
		 }finally {
			 SeleniumUtils.js.executeScript("window.scrollTo(0, 0)");
		}
		 
		 
	}
/**
 * Validate single row data contains filter result
 * @param rowData
 */
	private static void validateRowData(Map<String, String> rowData) {
		try{
			boolean status = false;
			for(Entry<String, String> filterValues : filterData.entrySet()){
				String Filter[] = filterValues.getValue().split("~") ;
				if(filterValues.getKey().trim().equalsIgnoreCase(getPropValue("T_1_v_employment_name"))){
    				status = false;
    				for(String empName : Filter){
    					if(empName.trim().equalsIgnoreCase(getExcelValue(rowData, "T_1_v_employment_name"))){
    						status = true;
    						break;
    					}
    				}
    				if(!status){
    					reportGenerate(SeleniumUtils.childTest, "Employent Name '"+getExcelValue(rowData, "T_1_v_employment_name") +"'", false);
    				}
    			 }
				if(filterValues.getKey().trim().equalsIgnoreCase(getPropValue("T_1_v_ticker"))){
    				status = false;
    				for(String tic : Filter){
    					if(tic.trim().equalsIgnoreCase(getExcelValue(rowData, "T_1_v_ticker"))){
    						status = true;
    						break;
    					}
    				}
    				if(!status){
    					reportGenerate(SeleniumUtils.childTest, "Ticker '"+getExcelValue(rowData, "T_1_v_ticker") +"'", false);
    				}
    			 }
				
				
				
    		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
/**
 * This Method used to map table header name with position
 * @param values
 * @return
 */
	private static Map<Integer,String> getTableHeaderName(Map<String, String> values) {
		Map<Integer,String> headerWithPosition = new LinkedHashMap<>();
		try{
			Thread.sleep(10000);
			 List<WebElement> head = SeleniumUtils.webDriver.findElements(By.xpath(getPropValue("T_1_id_table_header")));
			 for(int i = 0 ; i<head.size();i++){
				 SeleniumUtils.js.executeScript("arguments[0].scrollIntoView(true);",  head.get(i));
				  String value = head.get(i).getText();
				  headerWithPosition.put(i,value);
			 }
			 for(int j=head.size()-1;j!=0;j--){
				 SeleniumUtils.js.executeScript("arguments[0].scrollIntoView(true);",  head.get(j));
			 }
		}catch (Exception e) {
			 e.printStackTrace();
		}
		return headerWithPosition;
		
	}
/**
 * Verify the above table filter data 
 * @param values
 */
	private static void verifyFilterText(Map<String, String> values) {
    	try{
    		for(Entry<String, String> textData : filterData.entrySet()){
    			String key = "";
    			//employment name 
    			if(textData.getKey().trim().equalsIgnoreCase(getPropValue("T_1_v_employment_name"))){
						if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("T_1_id_emp_name_text"), 20, "xpath")){
							key = SeleniumUtils.getTextfromField(getPropValue("T_1_id_emp_name_text")+"//label", "xpath");
							reportGenerate(SeleniumUtils.childTest, "Search Filter Employment name is displayed", true);
						}else{
							reportGenerate(SeleniumUtils.childTest, "Search Filter Employment name is not displayed", false);
						}
    			}
    			//ticker
    			if(textData.getKey().trim().equalsIgnoreCase(getPropValue("T_1_v_ticker"))){
						if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("T_1_id_ticker_text"), 20, "xpath")){
							key = SeleniumUtils.getTextfromField(getPropValue("T_1_id_ticker_text")+"//label", "xpath");
							reportGenerate(SeleniumUtils.childTest, "Search Filter ticker name is displayed", true);
						}else{
							reportGenerate(SeleniumUtils.childTest, "Search Filter ticker is not displayed", false);
						}
						break;
				}
    			//product
    			if(textData.getKey().trim().equalsIgnoreCase(getPropValue("T_1_v_product"))){
						if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("T_1_id_product_text"), 20, "xpath")){
							key = SeleniumUtils.getTextfromField(getPropValue("T_1_id_product_text")+"//label", "xpath");
							reportGenerate(SeleniumUtils.childTest, "Search Filter product name is displayed", true);
						}else{
							reportGenerate(SeleniumUtils.childTest, "Search Filter product is not displayed", false);
						}
						break;
				}
    			//desk
    			if(textData.getKey().trim().equalsIgnoreCase(getPropValue("T_1_v_Desk"))){
						if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("T_1_id_desk_text"), 20, "xpath")){
							key = SeleniumUtils.getTextfromField(getPropValue("T_1_id_desk_text")+"//label", "xpath");
							reportGenerate(SeleniumUtils.childTest, "Search Filter desk name is displayed", true);
						}else{
							reportGenerate(SeleniumUtils.childTest, "Search Filter desk is not displayed", false);
						}
						break;
				}
    			//Account name
    			if(textData.getKey().trim().equalsIgnoreCase(getPropValue("T_1_v_account_name"))){
						if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("T_1_id_account_name_text"), 20, "xpath")){
							key = SeleniumUtils.getTextfromField(getPropValue("T_1_id_account_name_text")+"//label", "xpath");
							reportGenerate(SeleniumUtils.childTest, "Search Filter account name is displayed", true);
						}else{
							reportGenerate(SeleniumUtils.childTest, "Search filter account is not displayed", false);
						}
						break;
				}
    			compareFilterText(textData.getKey(),key,textData.getValue());
    		}
    	}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
/**
 * compare filter keywords show above table is correct or not
 * @param filterName
 * @param UiText - above table filter text
 * @param filterText - show and hide filter text
 */
	private static void compareFilterText(String filterName,String UiText, String filterText) {
		try{
			String Ui[] = UiText.split(",");
			String filter[] = filterText.split("~");
			if(Ui.length==filter.length){
				reportGenerate(SeleniumUtils.childTest, filterName+" Search filter is displayed above the table ", true);
			}else{
				reportGenerate(SeleniumUtils.childTest, filterName+" Search filter value is '"+filterText+"' mismatch in above table '"+UiText+"' ", false);
			}
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
/**
 * This Method used to set excel data into filter field 
 * And select drop down list values
 * @param fieldName
 * @param fieldElement
 * @param excelValue
 */
	private static void SetTokensValues(String fieldName,String fieldElement, String excelValue) {
		 try{
			String multipleVal[] =excelValue.split("~"); 
			String filterText = "";
			if(excelValue.isEmpty()){
				reportGenerateForEmpty(SeleniumUtils.childTest, fieldName+" filter value is empty in excel");
				return;
			}
			for(int i=0;i<multipleVal.length;i++){
				 SeleniumUtils.setValueToField(fieldElement+"//child::input", multipleVal[i], "xpath");
				 boolean selectDropDown =false;
				 
				 Thread.sleep(5000);
				 List<WebElement> dropdownList = SeleniumUtils.webDriver.findElements(By.xpath(fieldElement+"//following-sibling::ul/li"));
				 for(WebElement drop : dropdownList){
					 Thread.sleep(1000);
					 String dropdown= drop.getAttribute("title").trim();
					 if(dropdown.equalsIgnoreCase(multipleVal[i].trim())){
						 drop.click();
						 selectDropDown=true;
					 }
				 }
				 if(selectDropDown){
					 if(filterText.isEmpty()){
						 filterText +=multipleVal[i];
					 }else{
						 filterText +="~"+multipleVal[i];
					 }
					 reportGenerate(SeleniumUtils.childTest,fieldName+" in '"+multipleVal[i]+"' drop down value is  available", true);
				 }else{
					 reportGenerate(SeleniumUtils.childTest,fieldName+" in '"+multipleVal[i]+"' drop down value is not available", false);
					 SeleniumUtils.ClearFieldValue(fieldElement+"//child::input", "xpath");
				 } 
			 }
			 //Apply Filter
			 SeleniumUtils.ClickOnItems(getPropValue("T_1_id_filter_apply_all"), "xpath");
			 if(!filterText.isEmpty()){
				 filterData.put(fieldName, filterText.toString());
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
	private static String getPropValue(String key) {
		String result = "";
		try{
			result = TradesMain.TradesPROP.getProperty(key).trim();
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
	public static String getExcelValue(Map<String, String> values, String key) {
		String result;
		 try{
			 result= values.get(getPropValue(key.trim())).trim();
		 }
		 catch (Exception e) {
			 result = "";
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
	        	 SeleniumUtils.testCase.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(EmploymentChangeMain.moduleName,getPropValue("ExisitingVerifyInteraction"),1));
	        }	
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * generate empty report 		
 * @param insertData
 * @param msg
 */
	public static void reportGenerateForEmpty(ExtentTest insertData, String msg ) {
		 
			insertData.log(Status.PASS,MarkupHelper.createLabel( msg+" ",ExtentColor.GREY));            
         	
	}
}
