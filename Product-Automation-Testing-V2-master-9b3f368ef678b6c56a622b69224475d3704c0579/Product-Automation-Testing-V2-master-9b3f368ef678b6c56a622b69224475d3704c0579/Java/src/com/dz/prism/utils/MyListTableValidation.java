package com.dz.prism.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import com.dz.prism.bo.MyListTableBO;
import com.dz.prism.module.mylist.MyListMain;

public class MyListTableValidation extends MyListMain {

	public static void mapTableHeaderWithValue(LinkedHashMap<String, Integer> tableHeaderWithPosition,
			MyListTableBO myListBO) {
		LinkedList<LinkedHashMap<String, String>> tableDataMap = new LinkedList<>();
		try{
			
			 
			String tableRows = getPropValue("mylist_table_first_row");
			String tableRowsVisibleElement = getPropValue("Mylist_table_total_visible_row");
			int totalVisibleRow = TableUtils.countOfTableBodyRows(tableRowsVisibleElement.replace("#listid", myListBO.getList_ID()), "xpath");
			
			
			String tableXpath = myListBO.getTableBodyXpath() ;
			if(SeleniumUtils.checkElementDisplayedProp(tableRows.replace("#listid", myListBO.getList_ID()), "xpath")){
				reportWrite(SeleniumUtils.childTest, myListBO.getTableName()+" in table row is displayed", true);
			}else{
				MyListMain.reportWriteSkip(SeleniumUtils.childTest, myListBO.getTableName()+" in table row is empty");
				return;
			}
			for(int i=Integer.parseInt(getPropValue("mylist_row_position"));i<=(totalVisibleRow/2)-1;i++){
				if(myListBO.getNumberOfRowCheck()>0 && myListBO.getNumberOfRowCheck()==(i)){
					break;
				}
				LinkedHashMap<String, String> headerWithValue = new LinkedHashMap<>();
				for(Entry<String, Integer> header : tableHeaderWithPosition.entrySet()){
					tableXpath = myListBO.getTableBodyXpath().replace("#rowNo", String.valueOf(i));
					tableXpath = tableXpath.replaceAll("#colNo",String.valueOf(header.getValue()-1));
					SeleniumUtils.scrollUntilElementView(tableXpath, "xpath");
					if(SeleniumUtils.checkElementDisplayedProp(tableXpath, "xpath")){
						headerWithValue.put(header.getKey(), SeleniumUtils.getTextfromField(tableXpath, "xpath"));
					}else{
						MyListMain.reportWrite(SeleniumUtils.childTest, header.getKey()+" column value is not displayed in table", false);
					}
				}
				//scroll back
				for(int col=tableHeaderWithPosition.size() ; col!=1 ; col--){
					tableXpath = myListBO.getTableBodyXpath().replace("#rowNo", String.valueOf(i));
					tableXpath =tableXpath.replaceAll("#colNo",String.valueOf(col));
					SeleniumUtils.scrollUntilElementView(tableXpath, "xpath");
				}
				//store row data map in list
				tableDataMap.add(headerWithValue);
				
				//click Account Name 
				String accountName = myListBO.getTableBodyXpath();
		 		accountName = myListBO.getTableBodyXpath().replace("#rowNo", String.valueOf(i));
				accountName = accountName.replaceAll("#colNo",String.valueOf(getColumnPostion(tableHeaderWithPosition, "Mylist_Account_Name")));
				if(SeleniumUtils.waitUntilElementDisplayed(accountName, 15, "xpath")){
					SeleniumUtils.createTestNode(myListBO.getModuleName() +" "+myListBO.getTableName()+"  Account tear sheet '"+getColumnValue(getPropValue("Mylist_Account_Name"), headerWithValue)+"' ", "verify account tear sheet values in "+getColumnValue(getPropValue("Mylist_Account_Name"), headerWithValue)+"");
					SeleniumUtils.ClickOnItems(accountName, "xpath");
					ValidateAccountTearSheet(headerWithValue,myListBO);
					CloseAllTearSheetTab();
				}else{
					MyListMain.reportWrite(SeleniumUtils.childTest, MyListMain.getPropValue("Mylist_Account_Name")+" is not displayed", false);
				}
				//click contactName 
				String contactName = myListBO.getTableBodyXpath();
				contactName = myListBO.getTableBodyXpath().replace("#rowNo", String.valueOf(i));
				contactName = contactName.replaceAll("#colNo",String.valueOf(getColumnPostion(tableHeaderWithPosition, "Mylist_Contact_Name")));
				System.out.println("cont  "+contactName);
				if(SeleniumUtils.waitUntilElementDisplayed(contactName, 15, "xpath")){
					SeleniumUtils.createTestNode(myListBO.getModuleName() +" "+myListBO.getTableName()+"  Contact tear sheet '"+getColumnValue(getPropValue("Mylist_Contact_Name"), headerWithValue)+"' ", "verify contact tear sheet values in "+getColumnValue(getPropValue("Mylist_Contact_Name"), headerWithValue)+"");
					SeleniumUtils.ClickOnItems(contactName+"//a", "xpath");
					ValidateContactTearSheet(headerWithValue,myListBO);
					CloseAllTearSheetTab();
				}else{
					MyListMain.reportWrite(SeleniumUtils.childTest, MyListMain.getPropValue("Mylist_Contact_Name")+" is not displayed", false);
				}
			}
			ValidateActivtyTableData(tableDataMap,myListBO);
			TabSelectionUtils.tabSelection("My_Lists")	;
		 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void ValidateActivtyTableData(LinkedList<LinkedHashMap<String, String>> tableDataMap,
			MyListTableBO myListBO) {
		LinkedHashMap<String, Integer>  activityTableHeaderWithPosition = new LinkedHashMap<String, Integer>();
		try{
			//select activity tab
			if(TabSelectionUtils.tabSelection("Activity")){
				reportWrite(SeleniumUtils.childTest, "Activity tab is displayed", true);
			}else{
				reportWrite(SeleniumUtils.childTest, "Activity tab is not displayed", false);
				return;
			}
			SeleniumUtils.createTestNode(myListBO.getModuleName() +" "+myListBO.getTableName()+" table column column validation in Activity module ", "Table column value validation in activity module");
			//check activity table search field 
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("Activity_Table_Search_Field"), 10, "xpath")){
				reportWrite(SeleniumUtils.childTest, "Activity tab in search field is displayed", true);
			}else{
				reportWrite(SeleniumUtils.childTest, "Activity tab in search field is not displayed", false);
				return;
			}
			//column visibility
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("Activity_Table_Column_Visibility"), 5, "xpath")){
				SeleniumUtils.ClickOnItems(getPropValue("Activity_Table_Column_Visibility"), "xpath");
				reportWrite(SeleniumUtils.childTest, "Activity table in column visibility button is displayed", true);
			}else{
				reportWrite(SeleniumUtils.childTest, "Activity table in column visibility button is displayed", false);
				return;
			}
			//Select all column 
			List<WebElement> columnVisibility = SeleniumUtils.webDriver.findElements(By.xpath(getPropValue("Activity_Table_Column_Visibility_drop_down")));
			for(WebElement columnChanges : columnVisibility){
				if(!columnChanges.isSelected()){
					columnChanges.click();
					Thread.sleep(1000);
				}
			}
			SeleniumUtils.ClickOnItems(getPropValue("Activity_Table_Search_Field"), "xpath");
			
			
			//get header value with position
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("Activity_Table_Header"), 10, "xpath")){
				reportWrite(SeleniumUtils.childTest, "Activity tab in table header is displayed", true);
				Thread.sleep(10000);
				List<WebElement> activityHeader = SeleniumUtils.webDriver.findElements(By.xpath(getPropValue("Activity_Table_Header")));
				for(int i=0;i<activityHeader.size();i++){	
					 SeleniumUtils.js.executeScript("arguments[0].scrollIntoView(true);",activityHeader.get(i));
					activityTableHeaderWithPosition.put(activityHeader.get(i).getText(), i);
				}
				//scroll back
				for(int col=activityHeader.size()-2 ; col!=0 ; col--){
					 SeleniumUtils.js.executeScript("arguments[0].scrollIntoView(true);",activityHeader.get(col));
				}
				
			}else{
				reportWrite(SeleniumUtils.childTest, "Activity tab in table header is not displayed", false);
				return;
			}
			for(LinkedHashMap<String, String> allTableData : tableDataMap){
				 SeleniumUtils.setValueToField(getPropValue("Activity_Table_Search_Field"), getColumnValue(getPropValue("Mylist_Contact_Name"), allTableData), "xpath");
				 Thread.sleep(5000);
				 SeleniumUtils.childTest =  SeleniumUtils.testCase.createNode("Contact name "+getColumnValue(getPropValue("Mylist_Contact_Name"), allTableData));
				 int datePosition = getColumnPostion(activityTableHeaderWithPosition,"Activity_Date");
				 int accountIDPosition = getColumnPostion(activityTableHeaderWithPosition,"Mylist_Account_id");
				 int interactedModePosition = getColumnPostion(activityTableHeaderWithPosition,"Activity_mode");
				 int last_interacted_emp_name = getColumnPostion(activityTableHeaderWithPosition,"Activity_internal_addtendes");
				 
				 String firtRowDate =  getPropValue("Activity_Table_Body").replace("#col", String.valueOf(datePosition+2));
				 String allDate = "//*[contains(@id,'dataTable_activitylist')]//tbody/tr//td["+(datePosition+2)+"]";
				 if(SeleniumUtils.waitUntilElementDisplayed(firtRowDate, 5, "xpath")){
					 //check column is sorted or not
					 sortbycolumn(allDate,getPropValue("Activity_Table_Header")+"["+(datePosition+2)+"]");
					 reportWrite(SeleniumUtils.childTest, "Activity in search contact name '"+getColumnValue(getPropValue("Mylist_Contact_Name"), allTableData)+"' in table show result", true);
					 compareTableValue(getPropValue("Mylist_last_interaction_date"), firtRowDate, allTableData);
					 compareTableValue(getPropValue("Mylist_last_interacted_date_by_me"), firtRowDate, allTableData);
					 compareTableValue(getPropValue("Mylist_Account_id"), getPropValue("Activity_Table_Body").replace("#col", String.valueOf(accountIDPosition+2)), allTableData);
					 compareTableValue(getPropValue("Mylist_last_interaction_mode"), getPropValue("Activity_Table_Body").replace("#col", String.valueOf(interactedModePosition+2)), allTableData);
					 compareTableValue(getPropValue("Mylist_last_interacted_mode_by_me"), getPropValue("Activity_Table_Body").replace("#col", String.valueOf(interactedModePosition+2)), allTableData);
					 compareTableValue(getPropValue("Mylist_last_interacted_by_emp_name"), getPropValue("Activity_Table_Body").replace("#col", String.valueOf(last_interacted_emp_name+2)), allTableData);
				 }else{
					 reportWrite(SeleniumUtils.childTest, "Activity in search contact name '"+getColumnValue(getPropValue("Mylist_Contact_Name"), allTableData)+"' in table no records found", false);
				 }
				 SeleniumUtils.ClearFieldValue(getPropValue("Activity_Table_Search_Field"), "xpath");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void sortbycolumn(String columnsData, String dateHeader) {
		try{
			 List<WebElement> allData = SeleniumUtils.webDriver.findElements(By.xpath(columnsData));
			 List< String> dateList = new LinkedList<String>();
			 for(WebElement date : allData){
				 try{
					 dateList.add(date.getText());
				 }catch (StaleElementReferenceException e) {
					e.printStackTrace();
				 }
			 }
			 //check sort date
		 
			 if(isCollectionSorted(dateList)){
				 System.out.println("sort");
				 SeleniumUtils.ClickOnItems(dateHeader, "xpath");
			 }else{
				 System.out.println("not sort");
			 }
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void ValidateContactTearSheet(LinkedHashMap<String, String> headerWithValue,
			MyListTableBO myListBO) {
		try{
			compareTableValue(getPropValue("Mylist_contact_name"), getPropValue("CT_contact_name"), headerWithValue);
			compareTableValue(getPropValue("Mylist_phone_no"), getPropValue("CT_phone_no"), headerWithValue);
			compareTableValue(getPropValue("Mylist_preferred_name"), getPropValue("CT_preferred_name"), headerWithValue);
			compareTableValue(getPropValue("Mylist_status"), getPropValue("CT_status"), headerWithValue);
			compareTableValue(getPropValue("Mylist_accout_aum"), getPropValue("CT_account_aum"), headerWithValue);
			compareTableValue(getPropValue("Mylist_contact_notes"), getPropValue("CT_contact_notes"), headerWithValue);
			compareTableValue(getPropValue("Mylist_phone_no"),getPropValue("CT_desk_no"), headerWithValue);
			get_last_interactiondate(headerWithValue, myListBO);
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("acc_con_action"), 5, "xpath")){
				reportWrite(SeleniumUtils.childTest, "Contact tear sheet in edit Action button in displayed", true);
				SeleniumUtils.ClickOnItems(getPropValue("acc_con_action"), "xpath");
			}else{
				reportWrite(SeleniumUtils.childTest, "Contact tear sheet in edit Action button in not displayed", false);
				return;
			}
			//action edit contact
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("CT_edit_contact"), 5, "xpath")){
				reportWrite(SeleniumUtils.childTest, "Contact tear sheet in Action bar in edit contact drop down button is displayed", true);
				SeleniumUtils.ClickOnItems(getPropValue("CT_edit_contact"), "xpath");
			}else{
				reportWrite(SeleniumUtils.childTest, "Contact tear sheet in Action bar in edit contact drop down is not displayed", false);
				return;
			}
			// edit contact page
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("CT_close_edit_contact"), 5, "xpath")){
				reportWrite(SeleniumUtils.childTest, "Contact tear sheet in  edit contact page is displayed", true);
			}else{
				reportWrite(SeleniumUtils.childTest, "Contact tear sheet in  edit contact page is not displayed", false);
				return;
			}
			getDropDownText(getPropValue("Mylist_designation"),getPropValue("CT_designation"),headerWithValue);
			getDropDownText(getPropValue("Mylist_acc_type"),getPropValue("CT_acc_type"),headerWithValue);
			compareTableValue(getPropValue("Mylist_contact_first_name"), getPropValue("CT_first_name"), headerWithValue);
			compareTableValue(getPropValue("Mylist_contact_last_name"), getPropValue("CT_last_name"), headerWithValue);
			compareTableValue(getPropValue("Mylist_title"), getPropValue("CT_title"), headerWithValue);
			compareTableValue(getPropValue("Mylist_address_1"), getPropValue("CT_address"), headerWithValue);
			compareTableValue(getPropValue("Mylist_contact_city"), getPropValue("CT_city"), headerWithValue);
			compareTableValue(getPropValue("Mylist_contact_state"), getPropValue("CT_state"), headerWithValue);
			compareTableValue(getPropValue("Mylist_contact_pin"), getPropValue("CT_pincode"), headerWithValue);
			compareTableValue(getPropValue("Mylist_email_id"), getPropValue("CT_mail_id"), headerWithValue);
			compareCheckBox(getPropValue("Mylist_email_opt_out"), getPropValue("CT_email_checkBox"), headerWithValue);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("CT_close_edit_contact"), 5, "xpath")){
				SeleniumUtils.ClickOnItems(getPropValue("CT_close_edit_contact"), "xpath");
			}
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("CT_contact_close_yes"), 5, "xpath")){
				SeleniumUtils.ClickOnItems(getPropValue("CT_contact_close_yes"), "xpath");
			}
		}
		
	}

	private static void compareCheckBox(String columnName, String Element,
			LinkedHashMap<String, String> headerWithValue) {
		try{
			String columnValue  = getColumnValue(columnName,headerWithValue);
			//check column name is present in headerWithValue map
			if(columnValue.isEmpty()){
				reportWrite(SeleniumUtils.childTest, columnName+" column name not present in table", false);
				return;
			}
			//check column value is empty or not
			if(columnValue.equalsIgnoreCase("-")){
				reportWriteSkip(SeleniumUtils.childTest, columnName+" column is empty in table");
				return;
			}
			SeleniumUtils.scrollUntilElementView(Element, "xpath");
			//check element is displayed or not
			if(SeleniumUtils.waitUntilElementDisplayed(Element+"//parent::*", 5, "xpath")){
				reportWrite(SeleniumUtils.childTest, columnName+" field is displayed", true);
				boolean checkBoxStatus = SeleniumUtils.checkBoxIsSelect(Element, "xpath");
				if(columnName.equalsIgnoreCase("yes")){
					if(checkBoxStatus){
						reportWrite(SeleniumUtils.childTest, columnName+" in mylist table value '"+columnValue+"' and check box is checked", true);
					}else{
						reportWrite(SeleniumUtils.childTest, columnName+" in mylist table value '"+columnValue+"' and check box is not checked", false);
					}
				}else{
					if(checkBoxStatus){
						reportWrite(SeleniumUtils.childTest, columnName+" in mylist table value '"+columnValue+"' and check box is checked", false);
					}else{
						reportWrite(SeleniumUtils.childTest, columnName+" in mylist table value '"+columnValue+"' and check box is not checked", true);
					}
				}
			}else {
				reportWrite(SeleniumUtils.childTest, columnName+" field is not displayed", false);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void ValidateAccountTearSheet(LinkedHashMap<String, String> headerWithValue,
			MyListTableBO myListBO) {
		try{
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("Account_info_div"),5, "xpath")){
				reportWrite(SeleniumUtils.childTest, "Account info drop down is dispalyed", true);
				SeleniumUtils.ClickOnItems(getPropValue("Account_info_div"), "xpath");
				compareTableValue(getPropValue("Mylist_Account_id"),getPropValue("AC_account_id"),headerWithValue);
				compareTableValue(getPropValue("Mylist_Tier"),getPropValue("AC_tier"),headerWithValue);
				SeleniumUtils.ClickOnItems(getPropValue("Account_info_div"), "xpath");
				getSalesCoverage(headerWithValue, myListBO);
				getAccountSubType(headerWithValue,myListBO);
			}else{
				reportWrite(SeleniumUtils.childTest, "Account info drop down is not dispalyed", false);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void getAccountSubType(LinkedHashMap<String, String> headerWithValue, MyListTableBO myListBO) {
		try{
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("acc_con_action"), 5, "xpath")){
				SeleniumUtils.ClickOnItems(getPropValue("acc_con_action"), "xpath");
				reportWrite(SeleniumUtils.childTest, "Account tear sheet in edit action bar is displayed ", true);
			}else{
				reportWrite(SeleniumUtils.childTest, "Account tear sheet in edit action bar is not displayed ", false);
				return;
			}
			//check account edit
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("AC_edit_account"), 5, "xpath")){
				SeleniumUtils.ClickOnItems(getPropValue("AC_edit_account"), "xpath");
				reportWrite(SeleniumUtils.childTest, "Account tear sheet in action bar drop down in edit account is displayed ", true);
			}else{
				reportWrite(SeleniumUtils.childTest, "Account tear sheet in action bar drop down in edit account is not displayed ", false);
				return;
			}
			//account page show or not
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("AC_account_close"), 5, "xpath")){
				reportWrite(SeleniumUtils.childTest, "Account tear sheet in edit page is displayed ", true);
			}else{
				reportWrite(SeleniumUtils.childTest, "Account tear sheet in edit page is not displayed ", false);
				return;
			}
			SeleniumUtils.scrollUntilElementView(getPropValue("AC_sub_type"), "xpath");
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("AC_sub_type"), 5, "xpath")){
				reportWrite(SeleniumUtils.childTest, "Account edit page in account sub type field is displayed", true);
			}else{
				reportWrite(SeleniumUtils.childTest, "Account edit page in account sub type field is not displayed", false);
				return;
			}
			getDropDownText(getPropValue("Mylist_sub_acc_type"), getPropValue("AC_sub_type"), headerWithValue);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("AC_account_close"), 5, "xpath")){
				SeleniumUtils.ClickOnItems(getPropValue("AC_account_close"), "xpath");
			}
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("AC_account_close_yes"), 5, "xpath")){
				SeleniumUtils.ClickOnItems(getPropValue("AC_account_close_yes"), "xpath");
			}
		}
		
	}

	public static void getDropDownText(String columnName, String dropdownElement, LinkedHashMap<String, String> headerWithValue){
		String defaultItem ="";
		try{
			Thread.sleep(3000);
			String columnValue  = getColumnValue(columnName,headerWithValue);
			//check column name is present in headerWithValue map
			if(columnValue.isEmpty()){
				reportWrite(SeleniumUtils.childTest, columnName+" column name not present in table", false);
				return;
			}
			//check column value is empty or not
			if(columnValue.equalsIgnoreCase("-")){
				reportWriteSkip(SeleniumUtils.childTest, columnName+" column is empty in table");
				return;
			}
			SeleniumUtils.scrollUntilElementView(dropdownElement, "xpath");
			Select select = new Select(SeleniumUtils.webDriver.findElement(By.xpath(dropdownElement)));
			WebElement option = select.getFirstSelectedOption();
		    defaultItem = option.getText();
		    if(defaultItem.equalsIgnoreCase(columnValue)){
				reportWrite(SeleniumUtils.childTest, columnName+" column table value '"+columnValue+"' and Ui value '"+defaultItem+"' is matched", true);
			}else{
				reportWrite(SeleniumUtils.childTest, columnName+" column table value '"+columnValue+"' and Ui value '"+defaultItem+"' is not matched", false);
			}
		    
		}catch (Exception e) {
		    e.printStackTrace();
		}
		
	}
	private static void compareTableValue(String columnName, String Element,
			LinkedHashMap<String, String> headerWithValue) {
		String columnValue  = getColumnValue(columnName,headerWithValue);
		//check column name is present in headerWithValue map
		if(columnValue.isEmpty()){
			reportWrite(SeleniumUtils.childTest, columnName+" column name not present in table", false);
			return;
		}
		//check column value is empty or not
		if(columnValue.equalsIgnoreCase("-")){
			reportWriteSkip(SeleniumUtils.childTest, columnName+" column is empty in table");
			return;
		}
		SeleniumUtils.scrollUntilElementView(Element, "xpath");
		//check element is displayed or not
		if(SeleniumUtils.waitUntilElementDisplayed(Element, 5, "xpath")){
			reportWrite(SeleniumUtils.childTest, columnName+" field is displayed", true);
			String UiValue  = SeleniumUtils.getTextfromField(Element, "xpath").trim();
			if(UiValue.isEmpty()){
				UiValue= SeleniumUtils.getAttributefromField(Element, "value", "xpath").trim();
			}
			if(UiValue.equalsIgnoreCase(columnValue) | UiValue.contains(columnValue)){
				reportWrite(SeleniumUtils.childTest, columnName+" column table value '"+columnValue+"' and Ui value '"+UiValue+"' is matched", true);
			}else{
				reportWrite(SeleniumUtils.childTest, columnName+" column table value '"+columnValue+"' and Ui value '"+UiValue+"' is not matched", false);
			}
		}else{
			reportWrite(SeleniumUtils.childTest, columnName+" field is not displayed", false);
		}
		
		
	}

	private static String getColumnValue(String columnName, LinkedHashMap<String, String> headerWithValue) {
		try{
			String value = headerWithValue.get(columnName).trim();
			return value;
		}catch (Exception e) {
			return "";
		}
		 
	}

	private static void get_last_interactiondate(LinkedHashMap<String, String> headerWithValue, MyListTableBO myListBO) {
		String lastdate ="";
		String lastmode ="";
		LinkedHashMap<String, Integer>  activityTableHeaderWithPosition = new LinkedHashMap<String, Integer>();
		try{
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("AC_activty_tab"), 5, "xpath")){
				SeleniumUtils.ClickOnItems(getPropValue("AC_activty_tab"), "xpath");
				Thread.sleep(5000);
				reportWrite(SeleniumUtils.childTest, "Account Tear sheet in Activity tab is dispalyed ", true);
			}else{
				reportWrite(SeleniumUtils.childTest, "Account Tear sheet in Activity tab is not dispalyed ", false);
				return;
			}
			//column visibility
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("Activity_Table_Column_Visibility"), 5, "xpath")){
				SeleniumUtils.ClickOnItems(getPropValue("Activity_Table_Column_Visibility"), "xpath");
				reportWrite(SeleniumUtils.childTest, "contact tear sheet Activity table in column visibility button is displayed", true);
			}else{
				reportWrite(SeleniumUtils.childTest, "contact tear sheet Activity table in column visibility button is displayed", false);
				return;
			}
			//Select all column 
			List<WebElement> columnVisibility = SeleniumUtils.webDriver.findElements(By.xpath(getPropValue("Activity_Table_Column_Visibility_drop_down")));
			for(WebElement columnChanges : columnVisibility){
				if(!columnChanges.isSelected()){
					columnChanges.click();
					Thread.sleep(1000);
				}
			}
			SeleniumUtils.ClickOnItems(getPropValue("Activity_Table_Search_Field"), "xpath");
			//get header value with position
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("Activity_Table_Header"), 10, "xpath")){
				reportWrite(SeleniumUtils.childTest, "Contact tear sheet in Activity tab in table header is displayed", true);
				Thread.sleep(10000);
				List<WebElement> activityHeader = SeleniumUtils.webDriver.findElements(By.xpath(getPropValue("Activity_Table_Header")));
				for(int i=0;i<activityHeader.size();i++){	
					 SeleniumUtils.js.executeScript("arguments[0].scrollIntoView(true);",activityHeader.get(i));
					activityTableHeaderWithPosition.put(activityHeader.get(i).getText(), i);
				}
				//scroll back
				for(int col=activityHeader.size()-2 ; col!=0 ; col--){
					 SeleniumUtils.js.executeScript("arguments[0].scrollIntoView(true);",activityHeader.get(col));
				}
				
			}else{
				reportWrite(SeleniumUtils.childTest, "contact tear sheet Activity tab in table header is not displayed", false);
				return;
			}
			 int datePosition = getColumnPostion(activityTableHeaderWithPosition,"Activity_Date");
			 int accountIDPosition = getColumnPostion(activityTableHeaderWithPosition,"Mylist_Account_id");
			 int interactedModePosition = getColumnPostion(activityTableHeaderWithPosition,"Activity_mode");
			 int last_interacted_emp_name = getColumnPostion(activityTableHeaderWithPosition,"Activity_internal_addtendes");
			 
			 String firtRowDate =  getPropValue("Activity_Table_Body").replace("#col", String.valueOf(datePosition+2));
			 String allDate = "//*[contains(@id,'dataTable_activitylist')]//tbody/tr//td["+(datePosition+2)+"]";
			 if(SeleniumUtils.waitUntilElementDisplayed(firtRowDate, 5, "xpath")){
				 //check column is sorted or not
				 sortbycolumn(allDate,getPropValue("Activity_Table_Header")+"["+(datePosition+2)+"]");
				 reportWrite(SeleniumUtils.childTest, "Activity in search contact name '"+getColumnValue(getPropValue("Mylist_Contact_Name"), headerWithValue)+"' in table show result", true);
				 compareTableValue(getPropValue("Mylist_last_interaction_date"), firtRowDate, headerWithValue);
				 compareTableValue(getPropValue("Mylist_last_interacted_date_by_me"), firtRowDate, headerWithValue);
				 compareTableValue(getPropValue("Mylist_Account_id"), getPropValue("Activity_Table_Body").replace("#col", String.valueOf(accountIDPosition+2)), headerWithValue);
				 compareTableValue(getPropValue("Mylist_last_interaction_mode"), getPropValue("Activity_Table_Body").replace("#col", String.valueOf(interactedModePosition+2)), headerWithValue);
				 compareTableValue(getPropValue("Mylist_last_interacted_mode_by_me"), getPropValue("Activity_Table_Body").replace("#col", String.valueOf(interactedModePosition+2)), headerWithValue);
				 compareTableValue(getPropValue("Mylist_last_interacted_by_emp_name"), getPropValue("Activity_Table_Body").replace("#col", String.valueOf(last_interacted_emp_name+2)), headerWithValue);
			 }else{
				 reportWrite(SeleniumUtils.childTest, "Activity in search contact name '"+getColumnValue(getPropValue("Mylist_Contact_Name"), headerWithValue)+"' in table no records found", false);
			 }
			  
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static boolean isCollectionSorted(List list) {
	    List copy = new ArrayList(list);
	    Collections.sort(copy);
	    return copy.equals(list);
	}

	private static void getSalesCoverage(LinkedHashMap<String, String> headerWithValue, MyListTableBO myListBO) {
		try{
			//check coverage tab in account tear sheet
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("AC_coverage_tab"), 5, "xpath")){
				reportWrite(SeleniumUtils.childTest, "Account Tear sheet in sales coverage tab is displayed", true);
			}else{
				reportWrite(SeleniumUtils.childTest, "Account Tear sheet in sales coverage tab is not displayed", false);
				return;
			}
			
			if(getColumnValue(getPropValue("Mylist_sales_coverage"), headerWithValue).isEmpty()){
				reportWrite(SeleniumUtils.childTest, getPropValue("Mylist_sales_coverage")+" column name not present in table", false);
				return;
			}
			
			if(getColumnValue(getPropValue("Mylist_sales_coverage"), headerWithValue).equalsIgnoreCase("-")){
				reportWriteSkip(SeleniumUtils.childTest, getPropValue("Mylist_sales_coverage")+" column is empty in table");
				return;
			}
			List<WebElement> header = SeleniumUtils.webDriver.findElements(By.xpath(getPropValue("AC_Coverage_header")));
			 int i;
			 for(i=0;i<header.size();i++){			
				 if("Person Name".equalsIgnoreCase( header.get(i).getText())){
					 break;
				 }
			}	
			String person = MyListMain.getPropValue("AC_Coverage_person");
			person = person.replace("?",Integer.toString(i+1));
			boolean coverage =true;
			String missName = "";
			List<WebElement> personName = SeleniumUtils.webDriver.findElements(By.xpath(person));
			for(WebElement name:personName){
				if(!name.getText().isEmpty()){
					if(getColumnValue(getPropValue("Mylist_sales_coverage"), headerWithValue).contains(name.getText())){
						coverage =true;
					}else{
						coverage =false;
						missName=name.getText();
					}
				}
			}
			if(coverage){
				MyListMain.reportWrite(SeleniumUtils.childTest, "sales coverage in mylist table is match with  sales coverager is displaying in contact tear sheet coverage tab", true);
			}else{
				MyListMain.reportWrite(SeleniumUtils.childTest, "sales coverage is not verified table value "+getColumnValue(getPropValue("Mylist_sales_coverage"), headerWithValue)+" tear sheet value "+missName, false);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void CloseAllTearSheetTab() {
		try{
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.js.executeScript("window.scrollTo(0, 0)");
			SeleniumUtils.scrollUntilElementView("//body/div", "xpath");
			List<WebElement> closeTab =SeleniumUtils.webDriver.findElements(By.xpath(MyListMain.getPropValue("Close_All_Tear_Sheet")));
			for(WebElement close : closeTab){
			   close.click();
			}
						 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * get value from map
	 * @param tableHeaderWithPosition
	 * @param key
	 * @return
	 */
		public static Integer getColumnPostion(LinkedHashMap<String, Integer> tableHeaderWithPosition, String key) {
			Integer result;
			 try{
				 result= tableHeaderWithPosition.get(MyListMain.getPropValue(key.trim()));
			 }
			 catch (Exception e) {
				 System.out.println("miss excel column ---->"+key);
				 result = null;
				 e.printStackTrace();
			}
				
			return (result-1);
		}
	 
	 

}
