/**
 * My List Sorting list and it's validation
 * @author ilayaraja
 * @date 18/12/2020
 */
package com.dz.prism.module.mylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class SortList_ML_1_9 {
	public static List<WebElement> initialListElements = null; 
	public static ArrayList<String> currentListNames = new ArrayList<String>();
	public static ArrayList<String> myListNames = new ArrayList<String>();
	public static ArrayList<String> mySharedListNames = new ArrayList<String>();
	/**
	 * To add Test result to the Report
	 * @param parentName
	 * @param Message
	 * @param status
	 * @throws InterruptedException 
	 */
	public static void logToReport(String parentName, String Message,Boolean status) throws InterruptedException{
    	try {
    		if(!parentName.equals("")){
    			SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode(parentName);
    		}    		
			SeleniumUtils.childTest.log(status?Status.PASS:Status.FAIL,MarkupHelper.createLabel(Message,status?ExtentColor.GREEN:ExtentColor.RED));
			SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_sort"),0));
		}catch (Exception e) {
			//Thread.sleep(2000);
		}
	}
	/**
	 * To click sort by droDown and select the sort based on SortingType
	 * @param SortingType
	 */
	public static void selectSortingType(String SortingType){
		try {
			SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_9_sortByDropDown"), "xpath");// click on sort drop down
			SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_9_sortByButtonValue")+"[@data-value='"+SortingType+"']", "xpath"); // select specified value
			waitForLoadingScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
		
	/**
	 * wait until loading screen to be hide 
	 */
	public static void waitForLoadingScreen(){
		try{
			SeleniumUtils.waitUntilElementHide(SeleniumUtils.commonProps.getProperty("CommonLoadingScreen"), "id");
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	/**
	 * it will return all the list name without "My Covered Contacts" list
	 * @param listPathbyClass
	 * @return List of names
	 */
	public static ArrayList<String> getCurrentListNames(List<WebElement> elementList,String dataFor){
		ArrayList<String> listNames = new ArrayList<String>();
		try {
			if(dataFor.equals("")){
				elementList.remove(0);// to remove My Covered Contacts  from the list
				int idx=0,idx2=0,idx3=0;
				for (WebElement element : elementList) {
					if(element.getAttribute("data-owner").toString().equals("1")){
						myListNames.add(idx2,element.getAttribute("data-listname").toLowerCase());
						idx2++;
					}
					if(element.getAttribute("data-shared").toString().equals("1")){
						mySharedListNames.add(idx3,element.getAttribute("data-listname").toLowerCase());
						idx3++;
					}
					listNames.add(idx,element.getAttribute("data-listname").toLowerCase());
					idx++;
				}
			}else{
				elementList.remove(0);// to remove My Covered Contacts  from the list
				int idx=0;
				for (WebElement element : elementList) {
					listNames.add(idx,element.getAttribute("data-listname").toLowerCase());
					idx++;
				}
			}
		} catch (Exception e) {
			
		}
		return listNames;
	}
	/**
	 * Return all List Elements
	 * @param listPathbyClass
	 * @return
	 */
	public static List<WebElement> getCurrentListElements(String listPathbyClass,String byVal){
		List<WebElement> elems = null;
		try {
			waitForLoadingScreen();
			Thread.sleep(10000);
			if(byVal.equals("css")){
				elems = SeleniumUtils.webDriver.findElements(By.cssSelector(listPathbyClass));
			}else{
				elems = SeleniumUtils.webDriver.findElements(By.className(listPathbyClass));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return elems;
	}
	/**
	 * Sort the list bases on given sorting type in byorder
	 * @param byorder -> type of sort
	 * @param currentList -> initial list for verification
	 */
	public static void sortListBy(String byorder){
		Boolean isSorted,checkLength;		
		try {			
			if(!byorder.equals("") && !byorder.isEmpty()){
				switch(byorder){
					case "A-Z":
						ArrayList<String> currentListNamesAtoZ =currentListNames;
						selectSortingType(byorder);
						ArrayList<String> sortedListNamesAtoZ = getCurrentListNames(getCurrentListElements(MyListMain.MYLISTPROP.getProperty("ML_1_9_my_listmgmt"),""),byorder);
						Collections.sort(currentListNamesAtoZ,String.CASE_INSENSITIVE_ORDER);
						isSorted	= currentListNamesAtoZ.equals(sortedListNamesAtoZ);
						checkLength	= currentListNamesAtoZ.size() == sortedListNamesAtoZ.size();
						logToReport("A-Z List sort verification", isSorted ? "List Sort for A-Z Passed with Collection Sorting order" : "List Sort for A-Z failed with Collection Sorting order",isSorted?true:false);
						logToReport("", checkLength ? "List Sort for A-Z Data count("+sortedListNamesAtoZ.size()+") is same as previous("+currentListNamesAtoZ.size()+")" : "List Sort for A-Z Data count("+sortedListNamesAtoZ.size()+") is not matched with previous("+currentListNamesAtoZ.size()+")",checkLength?true:false);
						break;
					case "Z-A":
						ArrayList<String> currentListNamesZtoA = currentListNames;
						selectSortingType(byorder);
						ArrayList<String> sortedListZtoA = getCurrentListNames(getCurrentListElements(MyListMain.MYLISTPROP.getProperty("ML_1_9_my_listmgmt"),""),byorder);
						Collections.sort(currentListNamesZtoA,Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));						
						isSorted	= currentListNamesZtoA.equals(sortedListZtoA);
						checkLength	= currentListNamesZtoA.size() == sortedListZtoA.size();
						logToReport("Z-A List sort verification", isSorted ? "List Sort for Z-A Passed with Collection Sorting order" : "List Sort for A-Z failed with Collection Sorting order",isSorted?true:false);
						logToReport("", checkLength ? "List Sort for Z-A Data count("+sortedListZtoA.size()+") is same as previous("+currentListNamesZtoA.size()+")" : "List Sort for Z-A Data count("+sortedListZtoA.size()+") is not matched with previous("+currentListNamesZtoA.size()+")",checkLength?true:false);
						break;
					case "A-Z-my":
						ArrayList<String> currentListNamesAtoZmy = myListNames;
						selectSortingType(byorder);
						ArrayList<String> sortedListAtoZmy = getCurrentListNames(getCurrentListElements("."+MyListMain.MYLISTPROP.getProperty("ML_1_9_my_listmgmt")+"[data-owner='1']","css"),byorder);
						Collections.sort(currentListNamesAtoZmy,String.CASE_INSENSITIVE_ORDER);						
						isSorted	= currentListNamesAtoZmy.equals(sortedListAtoZmy);
						checkLength	= currentListNamesAtoZmy.size() == sortedListAtoZmy.size();
						logToReport("A-Z My Own List sort verification", isSorted ? "List Sort for A-Z My Own List Passed with Collection Sorting order" : "List Sort for A-Z My Own List failed with Collection Sorting order",isSorted?true:false);
						logToReport("", checkLength ? "List Sort for A-Z My Own List Data count("+sortedListAtoZmy.size()+") is same as previous("+currentListNamesAtoZmy.size()+")" : "List Sort for A-Z My Own List Data count("+sortedListAtoZmy.size()+") is not matched with previous("+currentListNamesAtoZmy.size()+")",checkLength?true:false);
						break;
					case "Z-A-my":
						ArrayList<String> currentListNamesZtoAmy = myListNames;
						selectSortingType(byorder);
						ArrayList<String> sortedListZtoAmy = getCurrentListNames(getCurrentListElements("."+MyListMain.MYLISTPROP.getProperty("ML_1_9_my_listmgmt")+"[data-owner='1']","css"),byorder);
						Collections.sort(currentListNamesZtoAmy,Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
						isSorted	= currentListNamesZtoAmy.equals(sortedListZtoAmy);
						checkLength	= currentListNamesZtoAmy.size() == sortedListZtoAmy.size();
						logToReport("Z-A My Own List sort verification", isSorted ? "List Sort for Z-A My Own List Passed with Collection Sorting order" : "List Sort for Z-A My Own List failed with Collection Sorting order",isSorted?true:false);
						logToReport("", checkLength ? "List Sort for Z-A My Own List Data count("+sortedListZtoAmy.size()+") is same as previous("+currentListNamesZtoAmy.size()+")" : "List Sort for Z-A My Own List Data count("+sortedListZtoAmy.size()+") is not matched with previous("+currentListNamesZtoAmy.size()+")",checkLength?true:false);
						break;
					case "A-Z-shared":
						ArrayList<String> currentListNamesAtoZmyShared = mySharedListNames;
						selectSortingType(byorder);
						ArrayList<String> sortedListAtoZmyShared = getCurrentListNames(getCurrentListElements("."+MyListMain.MYLISTPROP.getProperty("ML_1_9_my_listmgmt")+"[data-shared='1']","css"),byorder);
						Collections.sort(currentListNamesAtoZmyShared,String.CASE_INSENSITIVE_ORDER);
						isSorted	= currentListNamesAtoZmyShared.equals(sortedListAtoZmyShared);
						checkLength	= currentListNamesAtoZmyShared.size() == sortedListAtoZmyShared.size();
						logToReport("A-Z My shared List sort verification", isSorted ? "List Sort for A-Z My shared List Passed with Collection Sorting order" : "List Sort for A-Z My shared List failed with Collection Sorting order",isSorted?true:false);
						logToReport("", checkLength ? "List Sort for A-Z My shared List Data count("+sortedListAtoZmyShared.size()+") is same as previous("+currentListNamesAtoZmyShared.size()+")" : "List Sort for A-Z My shared List Data count("+sortedListAtoZmyShared.size()+") is not matched with previous("+currentListNamesAtoZmyShared.size()+")",checkLength?true:false);
						break;
					case "Z-A-shared":
						ArrayList<String> currentListNamesZtoAmyShared = mySharedListNames;
						selectSortingType(byorder);
						ArrayList<String> sortedListZtoAmyShared = getCurrentListNames(getCurrentListElements("."+MyListMain.MYLISTPROP.getProperty("ML_1_9_my_listmgmt")+"[data-shared='1']","css"),byorder);
						Collections.sort(currentListNamesZtoAmyShared,Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
						isSorted	= currentListNamesZtoAmyShared.equals(sortedListZtoAmyShared);
						checkLength	= currentListNamesZtoAmyShared.size() == sortedListZtoAmyShared.size();
						logToReport("Z-A My shared List sort verification", isSorted ? "List Sort for Z-A My shared List Passed with Collection Sorting order" : "List Sort for Z-A My shared List failed with Collection Sorting order",isSorted?true:false);
						logToReport("", checkLength ? "List Sort for Z-A My shared List Data count("+sortedListZtoAmyShared.size()+") is same as previous("+currentListNamesZtoAmyShared.size()+")" : "List Sort for Z-A My shared List Data count("+sortedListZtoAmyShared.size()+") is not matched with previous("+currentListNamesZtoAmyShared.size()+")",checkLength?true:false);
						break;					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Initiate sorting functionality of my List
	 * @param MYLISTPROP
	 * @throws Exception
	 */
	public static void myListSorting(Properties MYLISTPROP) throws Exception{
		try{
			SeleniumUtils.testCase       = SeleniumUtils.extendReports.createTest(MyListMain.MYLISTPROP.getProperty("List_sort")+"_Sorting in ListManagement");
			SeleniumUtils.parentTest	 = SeleniumUtils.testCase.createNode("Sorting verification in list management");
			initialListElements = getCurrentListElements(MyListMain.MYLISTPROP.getProperty("ML_1_9_my_listmgmt"),"");
			currentListNames = getCurrentListNames(initialListElements,"");
			String typesOfSorts			 = MyListMain.MYLISTPROP.getProperty("ML_1_9_SortTypeDataValues");
			String[] typesOfSortsArray   = typesOfSorts.split("\\|");
		    for (String sortBy:typesOfSortsArray)
		    {
		    	sortListBy(sortBy);
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}