package com.dz.prism.utils;
  
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.bo.MyListTableBO;
import com.dz.prism.module.mylist.MyListMain; 

public class MyListUtils {
	public static void validateTable(MyListTableBO myListBO) {
		try{
			LinkedHashMap<String, Integer> tableHeaderWithPosition = new LinkedHashMap<>();
			//wait 1 min for table loading
			SeleniumUtils.waitUntilElementHide("//*[@class='loader']", "xpath");
			colunmVisibility(myListBO);
			tableHeaderWithPosition =getTableHeaderWithPosition(myListBO); 
			//compare table header column name
			if(tableHeaderWithPosition!=null){
				compareTableHearderColumnValue(tableHeaderWithPosition,myListBO);
				checkHyperLinkColumn(tableHeaderWithPosition,myListBO);
				MyListTableValidation.mapTableHeaderWithValue(tableHeaderWithPosition,myListBO);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void checkHyperLinkColumn(LinkedHashMap<String, Integer> tableHeaderWithPosition, MyListTableBO myListBO) {
		try{
			SeleniumUtils.createTestNode(myListBO.getModuleName() +" "+myListBO.getTableName()+" table column hyper link column validation ", "Table column hyper link column validation");
			 
			String tableRows = getPropValue("mylist_table_first_row");
			String tableRowsVisible = getPropValue("Mylist_table_total_visible_row");
			int totalVisibleRow = 0;
			if(myListBO.getTableHyperLinkColumnName().isEmpty()){
				return;
			}
			if(myListBO.getTableBodyXpath().isEmpty()){
				return;
			}
			System.out.println(tableRows.replace("#listid", myListBO.getList_ID()));
			if(SeleniumUtils.checkElementDisplayedProp(tableRows.replace("#listid", myListBO.getList_ID()), "xpath")){
				reportWrite(SeleniumUtils.childTest, myListBO.getTableName()+" in table row is displayed", true);
				totalVisibleRow = TableUtils.countOfTableBodyRows(tableRowsVisible.replace("#listid", myListBO.getList_ID()), "xpath");
			}else{
				MyListMain.reportWriteSkip(SeleniumUtils.childTest, myListBO.getTableName()+" in table row is empty");
				return;
			}
			String hyperLinkColumn[] = myListBO.getTableHyperLinkColumnName().split(",");
			for(int i=0 ;i<hyperLinkColumn.length;i++){
				int position = getColumnPosition(hyperLinkColumn[i].trim(),tableHeaderWithPosition);
				if(position !=0){
					reportWrite(SeleniumUtils.childTest, hyperLinkColumn[i]+" column is present in table", true);
					hyperLinkValidation(hyperLinkColumn[i],totalVisibleRow,position,myListBO);
				}else{
					reportWrite(SeleniumUtils.childTest, hyperLinkColumn[i]+" column is not present in table", false);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void hyperLinkValidation(String hyperLinkColumnName, int totalVisibleRow, int columnPosition, MyListTableBO myListBO) {
		try{
			String tableXpath = myListBO.getTableBodyXpath() ;
			// scroll until the hyper link column
			for(int col=0 ; col<=columnPosition ; col++){
				// v3.9  ("#rowNo", String.valueOf(1) v3.10 ("#rowNo", String.valueOf(0)
				tableXpath = myListBO.getTableBodyXpath().replace("#rowNo", String.valueOf(getPropValue("mylist_row_position")));
				tableXpath = tableXpath.replaceAll("#colNo",String.valueOf(col));
				SeleniumUtils.scrollUntilElementView(tableXpath, "xpath");
			}
			for(int row = 0 ; row<totalVisibleRow/2 ; row++){
				
				if(myListBO.getNumberOfRowCheck()>0 && myListBO.getNumberOfRowCheck()==(row)){
					break;
				}
				//v 3.9 replace("#rowNo", String.valueOf(row+1) v3.10 replace("#rowNo", String.valueOf(row)
				tableXpath = myListBO.getTableBodyXpath().replace("#rowNo",String.valueOf(Integer.parseInt(getPropValue("mylist_row_position"))+row));
				tableXpath = tableXpath.replaceAll("#colNo",String.valueOf(columnPosition));
				SeleniumUtils.scrollUntilElementView(tableXpath, "xpath"); 
				if(SeleniumUtils.waitUntilElementDisplayed(tableXpath+"//a", 5, "xpath")){
					reportWrite(SeleniumUtils.childTest, hyperLinkColumnName+" column is hyper link", true);
				}else{
					reportWrite(SeleniumUtils.childTest, hyperLinkColumnName+" column is not  hyper link in row number:"+(row+1), false);
				}
			}
			
			// scroll scroll back
			for(int col=columnPosition ; col!=0 ; col--){
				// v3.1  ("#rowNo", String.valueOf(1) v3.10 ("#rowNo", String.valueOf(0)
				tableXpath = myListBO.getTableBodyXpath().replace("#rowNo", String.valueOf(getPropValue("mylist_row_position")));
				tableXpath =tableXpath.replaceAll("#colNo",String.valueOf(col));
				SeleniumUtils.scrollUntilElementView(tableXpath, "xpath");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static int getColumnPosition(String columnName, LinkedHashMap<String, Integer> tableHeaderWithPosition) {
		try{
			return tableHeaderWithPosition.get(columnName)-1;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}

	private static void colunmVisibility(MyListTableBO myListBO) {
		try{
			if(myListBO.getColumnVisiblityXpath().isEmpty()){
				return;
			}
			SeleniumUtils.createTestNode(myListBO.getModuleName() +" "+myListBO.getTableName()+" table column visiblity validation ", "Table column visiblity changes ");
			if(SeleniumUtils.waitUntilElementDisplayed(myListBO.getColumnVisiblityXpath(), 5, "xpath")){
				reportWrite(SeleniumUtils.childTest, "Column change visibility button is displayed", true);
				SeleniumUtils.ClickOnItems(myListBO.getColumnVisiblityXpath(), "xpath");
				Thread.sleep(2000);
			}else{
				reportWrite(SeleniumUtils.childTest, "Column change visibility button is not displayed", false);
				 
			}
			//column visibility drop down validate
			
			if(SeleniumUtils.checkElementDisplayedProp(getPropValue("Mylist_colum_visibility_dropdown"), "xpath")){
				reportWrite(SeleniumUtils.childTest, "Column change visibility drop down list is displayed", true);
			}else{
				reportWrite(SeleniumUtils.childTest, "Column change visibility drop down list is not displayed", false);
				 
			}
			List<WebElement> columnVisibility = SeleniumUtils.webDriver.findElements(By.xpath(getPropValue("Mylist_colum_visibility_dropdown")));
			System.out.println(columnVisibility.size());
			for(WebElement columnChanges : columnVisibility){
				if(!columnChanges.isSelected()){
					columnChanges.click();
					Thread.sleep(1000);
				}
			}
			SeleniumUtils.createTestNode(myListBO.getModuleName() +" "+myListBO.getTableName()+" table Search Field ", "Table Search field is displayed or not ");
			if(SeleniumUtils.waitUntilElementDisplayed(getPropValue("Mylist_table_search_field"), 5, "xpath")){
				reportWrite(SeleniumUtils.childTest, "Table Search Field is displayed", true);
				SeleniumUtils.ClickOnItems(getPropValue("Mylist_table_search_field"), "xpath");
			}else{
				reportWrite(SeleniumUtils.childTest, "Table Search Field is not displayed", false);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void compareTableHearderColumnValue(LinkedHashMap<String, Integer> tableHeaderWithPosition,
			MyListTableBO myListBO) {
		 SeleniumUtils.createTestNode(myListBO.getModuleName() +" "+myListBO.getTableName()+" table header column name validation ", "Compare table header column name ");
		 try{
			 String column[]  =  myListBO.getTableColumnName().split(",");
			 List<String> columnName = new LinkedList<String>(); 
			 for(String col : column){
				 columnName.add(col.trim());
			 }
			 System.out.println(columnName.toString());
			 for(Entry<String, Integer> compColumn : tableHeaderWithPosition.entrySet()){
				 boolean columnStatus = columnName.contains(compColumn.getKey().trim());
				 if(columnStatus){
					 reportWrite(SeleniumUtils.childTest,myListBO.getTableName()+ " table header in "+compColumn.getKey()+"  column is displyed", true);
					 columnName.remove(compColumn.getKey().trim());
				 }else{
					 reportWrite(SeleniumUtils.childTest,myListBO.getTableName()+ " table header in "+compColumn.getKey()+" column is not displyed", false);
				 }
				 //System.out.println(columnName.toString());
			 }
			 //removeRepeatedColumn(columnName,myListBO);
			 if(columnName.size()!=0){
				 reportWrite(SeleniumUtils.childTest,myListBO.getTableName()+ " table header in "+columnName.toString()+" columns is not displyed", false);
			 }
		 }catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	 

	private static LinkedHashMap<String, Integer> getTableHeaderWithPosition(MyListTableBO myListBO) {
		LinkedHashMap<String,Integer> headerWithPosition = new LinkedHashMap<>();
		try{
			SeleniumUtils.createTestNode(myListBO.getModuleName() +" "+myListBO.getTableName()+" header ", "Get all the table header values");
			//check table is visible or not
			if(SeleniumUtils.waitUntilElementDisplayed(myListBO.getTableHeaderXpath(), 5, "xpath")){
				 reportWrite(SeleniumUtils.childTest, "Table header row is displayed", true);
			}else{
				reportWrite(SeleniumUtils.childTest, "Table header row is not displayed", false);
				return null;
			}
			StringBuffer columnName = new StringBuffer();
			//store all the column name with position
			List<WebElement> header = SeleniumUtils.webDriver.findElements(By.xpath(myListBO.getTableHeaderXpath()));
		      for(int i = 1 ; i<header.size();i++){
		    		  SeleniumUtils.js.executeScript("arguments[0].scrollIntoView(true);", header.get(i));
		    		  String value = header.get(i).getText().trim();
		              headerWithPosition.put(value,(i+1));
		              columnName.append(value+" , ");
		      }
		      for(int j=header.size()-1 ; j!=0;--j){
		    	  SeleniumUtils.js.executeScript("arguments[0].scrollIntoView(true);", header.get(j));
		      }
			System.out.println(columnName.toString());
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return headerWithPosition;
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
		        	 SeleniumUtils.testCase.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot("Table Validation","Table" ,1));
		        }	
			}catch (Exception e) {
				e.printStackTrace();
			}
		} 
}
