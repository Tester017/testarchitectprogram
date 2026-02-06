package com.dz.prism.productionbug;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.contactinterest.ContactInterestmain;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_9057 {
	static String AccName;
	static List<String> MVL;
	static List<String> l;
	public static void ReadExcel() throws Exception {
    	
        Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(
                SeleniumUtils.UserDirVar + ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_v_test_case_path"));
        for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
            List<Map<String, String>> innerRows = testRows.getValue();
            for (Map<String, String> values : innerRows) {
            	verification_Of_Inactive_Accounts(values);
            	
            }
        }
    }
	public static void verification_Of_Inactive_Accounts(Map<String, String> values) throws Exception {
		try {
		SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_9057-Contact Tear Sheet-->InActive Account Verification in- Move to and Employment change- Account Search ");
		//To get the Account name from excel
		AccName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_v_acc_name_search"));
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_AppPage"), "xpath");
		//set the values to global search
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_globalSearch"), AccName, "id");
		int searchListFilterCount = SeleniumUtils.getCountOfDropdownList(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_FilterCountList"), "xpath");
        //deselecting selected filter
		for(int i=1;i<=searchListFilterCount ;i++){
            boolean isSelected = SeleniumUtils.checkBoxIsSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_FilterSelectedCheck").replace("temp","" + i + ""), "xpath");
            if(isSelected==true){
            	SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_FilterDeselect").replace("temp","" + i + ""), "xpath");
                SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_FilterDeselect").replace("temp","" + i + ""),"xpath");
            }
        }
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_CoveredAccountCheckBox"), "xpath");
		//selecting Covered account filter
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_CoveredAccountCheckBox"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_InactiveAccountList"), "xpath");
		Thread.sleep(1500);
		int InActiveAccListCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_InactiveAccountList"))).size();
		 l= new ArrayList<String>();
		for (int i = 1; i <=InActiveAccListCount; i++) {
			String InActiveAccountNames = SeleniumUtils.getTextfromField("("+ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_InactiveAccountList")+")["+ i + "]", "xpath");
			l.add(InActiveAccountNames);
		}
		if (!l.isEmpty()) {
			SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_globalSearch"), "id");
			//get contact name from excel
			String ContactName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_v_contact_name"));
			//get contact mail id from excel
			String ContactMailId = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_v_contact_mail"));
			//Set the values to global search
			SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_globalSearch"), ContactName, "id");
			int searchListFilterCount1 = SeleniumUtils.getCountOfDropdownList(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_FilterCountList"), "xpath");
	        //deselecting selected filter
			for(int i=1;i<=searchListFilterCount1 ;i++){
	            boolean isSelected = SeleniumUtils.checkBoxIsSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_FilterSelectedCheck").replace("temp","" + i + ""), "xpath");
	            if(isSelected==true){
	            	SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_FilterDeselect").replace("temp","" + i + ""), "xpath");
	                SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_FilterDeselect").replace("temp","" + i + ""),"xpath");
	            }
	        }
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_ContactFilterCheckBox"), "xpath");
			//click on contact filter check box
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_ContactFilterCheckBox"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_ContactSearchListPresentornot"), "xpath");
	        //contact list displayed or not
	        Thread.sleep(2000);
			String contLists = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_ContactSearchListPresentornot"), "xpath");
	        if (!contLists.equals("No results found for "+"'"+ContactName+"'")) {
	        //No of contact list displayed
	        int SearchContactCount = SeleniumUtils.getCountOfDropdownList(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_ContactListCount"),"xpath");
	        List<String>Email=new ArrayList<String>();
	        for(int i=1;i<=SearchContactCount;i++){
	        	//Get all contact email id
	            String getMailId= SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_id_ContactEmailSearchList").replace("temp","" + i + ""), "xpath");
	            Email.add(getMailId);
	            //click on contact with required email id
	            if(getMailId.equalsIgnoreCase(ContactMailId)){
	            	//System.out.println("Email matched ");
	            	
	            	SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_RequiredContactMail").replace("temp","" + i + ""), "xpath");
	            	SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_RequiredContactMail").replace("temp","" + i + ""), "xpath");
	            	//click on required email contact
	            	SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_RequiredContactMail").replace("temp","" + i + ""), "xpath");
	            	//System.out.println("clicked on particular contact");
	            	contact_TearSheet_Page(values);
	            	break;
	            }
	        }
	        //email id not matched
	        if (!Email.contains(ContactMailId)) {
	        	//System.out.println(ContactEmailId+" emailID not matched");
	        	SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The entered email id is mis matched with the displayed Contact List - "+ContactMailId,ExtentColor.RED));
	        	SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_WebElements"),1));
			}
	        }//no contact list found
	        else {
	        	//System.out.println("No Contact found");
	        	SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("No Contact list found in search list"+ContactName,ExtentColor.RED));
	        	SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_WebElements"),1));
	        }
		} else {
			System.out.println("No account found");
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
		}
	
	public static void contact_TearSheet_Page(Map<String, String> values) throws Exception {
		try {
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_ContactTearSheetPage"), "xpath");
		//click on burger button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_BurgerButton"), "xpath");
		//click on move contact button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_MoveContactButton"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_MoveContactPage"), "xpath");
		//set the value to move to acc search
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_MoveToAccInputSearch"), AccName, "id");
		String searchList = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_MoveToAccNoSearch"), "xpath");
		if(!searchList.equalsIgnoreCase("No results found for '"+AccName+"'")) {
		//count of account displayed
		int moveAccList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_MoveToAccSearchList"))).size();
		List<String> MVL=new ArrayList<String>();
		for (int i = 1; i <=moveAccList ; i++) {
			//To get the account names
			String textfromField = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_MoveToAccSearchList")+"[" + i + "]", "xpath");
			String AccList = textfromField.trim();
			MVL.add(AccList);
		}
		for (int i = 0; i <l.size() ; i++) {
			boolean moveAcc = MVL.contains(l.get(i));
			if (moveAcc!=true) {
				//System.out.println("Move contact-pass");
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Contact Tear Sheet-The InActive Account is Not displaying in - Move to page- Account Search - "+l.get(i),ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_WebElements"),0));
			}else {
				//System.out.println("Move contact-fail");
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Contact Tear Sheet-The InActive Account is displaying in - Move to page- Account Search - "+l.get(i),ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_WebElements"),1));
			}
		}
		}else {
			SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("Contact Tear Sheet-The InActive Account is displaying in - Move to page- Account Search - is empty",ExtentColor.TEAL));
		}
		//click on move to contact close button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_MoveContactPageCloseBtn"), "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_ContactTearSheetPage"), "xpath");
		//click on burger button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_BurgerButton"), "xpath");
		//click on Employement change button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_EmploymentChangeButton"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_EmploymentChangePage"), "xpath");
		//Set the value to New Account Name input Search
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_NewAccNameInputSearch"), AccName, "id");
		String NewAccSearch = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_NewAccNameNoSearch"), "xpath");
		if(!NewAccSearch.equalsIgnoreCase("No results found for '" + AccName +"'")) {
		int EmpCngeAccList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_NewAccNameSearchList"))).size();
		List<String> ECA=new ArrayList<String>();
		for (int i = 1; i <=EmpCngeAccList ; i++) {
			//to get the account names
			String textfromField = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_NewAccNameSearchList")+"[" + i + "]", "xpath");	
			String EmpChgeAcc = textfromField.trim();
			ECA.add(EmpChgeAcc);
		}
		for (int i = 0; i <l.size() ; i++) {
			boolean EmpCngAc = ECA.contains(l.get(i));
			if (EmpCngAc!=true) {
				//System.out.println("Emp change-pass");
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Contact Tear Sheet-The InActive Account is Not displaying in -Employment change page-New Account Search - "+l.get(i),ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_WebElements"),0));
			}else {
				//System.out.println("Emp change-fail");
				SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Contact Tear Sheet-The InActive Account is displaying in - Employment change page -New Account Search - "+l.get(i),ExtentColor.RED));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_WebElements"),1));
			}
		}}else {
			SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel("Contact Tear Sheet-The InActive Account is displaying in - Employment change page - Account Search - is empty",ExtentColor.TEAL));
		}
		//To get Employment page close
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_EmploymentPageClose"), "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_ContactTearSheetPage"), "xpath");
		//Click on contact close button
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9057_1_id_ContactCloseButton"), "xpath");
	}catch (Exception e) {
		e.printStackTrace();
	}
		}
}
