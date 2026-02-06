package com.dz.prism.module.mylist;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class ShareList_ML_1_10 {
	public static void shareList(Properties MYLISTPROP) throws Exception{
		try{
			SeleniumUtils.testCase=  SeleniumUtils.extendReports.createTest(MyListMain.MYLISTPROP.getProperty("List_sort")+"_Share List in My List v3");
			SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_3_mylist_tab")));
			Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+MyListMain.MYLISTPROP.getProperty("ML_1_10_v_testCasePath"));
			for(java.util.Map.Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()){
				List<Map<String, String>> innerRows = testRows.getValue();
				for(Map<String,String> values : innerRows){
					sharelistValues(values);
				}
			}
			}catch(Exception e){
				SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Share List Test Case");
				SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Share List Login verification");
				SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Share List Failed to be Excecuted",ExtentColor.RED));
			}
		}
	
	public static void sharelistValues(Map<String, String> values) throws Exception {
		try{
		String List_Name=values.get(MyListMain.MYLISTPROP.getProperty("ML_1_10_v_listname"));
		String listname = List_Name.trim();
		String manageaccess = values.get(MyListMain.MYLISTPROP.getProperty("ML_1_10_v_manageaccess"));
		String employee=values.get(MyListMain.MYLISTPROP.getProperty("ML_1_10_v_employee"));
		String access = values.get(MyListMain.MYLISTPROP.getProperty("ML_1_10_v_access"));
		String sharewithEveryone = SeleniumUtils.webDriver.findElement(By.xpath("//div[@class='checkbox checkbox-primary custom-checkbox']//label")).getAttribute("innerText");

		if(sharewithEveryone.trim().equalsIgnoreCase(access.trim())){
			shareWithEveryone(manageaccess,listname,values);
		}
		else{
			shareWithEmployee(manageaccess,employee,listname,values);
		}
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	/**
	 * Given List should share with every user for view only  and view edit access
	 */
	public static void shareWithEveryone(String manageaccess,String listname,Map<String, String> values){

		try{
			String listtype = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_type")+listname+"']", "list-type", "xpath");
			String mail = values.get(MyListMain.MYLISTPROP.getProperty("ML_1_10_v_email"));
			SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Share With Everyone - "+manageaccess+ "-for"+listname+"("+listtype+")");
			SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Share With Everyone for sharing from one user to other user");
			switch(manageaccess){
			case "View only":
				if(SeleniumUtils.checkElementDisplayedProp(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']","xpath")){	
					SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']/span","xpath");
					SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_share")+listname+"']//ul//a[@class='sharelist']","xpath");	
					
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Given listname input from excel is verified for Share with Everyone(View only) - "+listname+"("+listtype+")",ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));
				}
				else{
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("List Name Failed to be Excecuted - "+listname,ExtentColor.GREY));
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
				}
				//SeleniumUtils.ClickOnItems("sharechkBox", "id");
			//	SeleniumUtils.ClickOnItems("viewonly", "id");
				Boolean Isselected1 = SeleniumUtils.checkBoxIsSelect("sharechkBox", "id");
				if(Isselected1 == true){
					SeleniumUtils.ClickOnItems("viewonly", "id");
				}
				else{
					SeleniumUtils.ClickOnItems("sharechkBox", "id");
					SeleniumUtils.ClickOnItems("viewonly", "id");
				}
				String chooseemployeeonly = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_usersearch"), "class", "id");
				if(chooseemployeeonly.contains("TokensContainer ui-sortable disable")){
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("While Selecting Share with Everyone ("+manageaccess+") choose employee field get disable verified successfully for - "+listname+"("+listtype+")",ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));

				}
				else{
					SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("While Selecting Share with Everyone ("+manageaccess+") choose employee field get disable failed for - "+listname+"("+listtype+")",ExtentColor.RED));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
				}
				SeleniumUtils.ClickOnItems("shareMultiList", "id");
				SeleniumUtils.ClickOnItems("modal_share_send_yes", "id");
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				String submitmessage = SeleniumUtils.getTextfromField(SeleniumUtils.toastMessageElement,"xpath");
				if(submitmessage.equalsIgnoreCase(MyListMain.MYLISTPROP.getProperty("ML_1_10_v_toastmsgSucess"))){
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("List Shared Successfully for - "+listname+"("+listtype+")",ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));	
				}
				else{
					SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("List Shared Failed for - "+listname+"("+listtype+")",ExtentColor.RED));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
				}
				existaccessTableVerify(values);
				ListHistory_ML_1_11.ShareListHistory(listname, manageaccess,mail,SeleniumUtils.childTest);
				break;
			case "View and Edit":
				if(SeleniumUtils.checkElementDisplayedProp(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']","xpath")){	
					SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']/span","xpath");
					SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_share")+listname+"']//ul//a[@class='sharelist']","xpath");	

					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Given listname input from excel is verified for Share with Everyone(View and Edit) - "+listname+"("+listtype+")",ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));
				}
				else{
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("List Name Failed to be Excecuted -  "+listname,ExtentColor.GREY));
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
				}
				Boolean Isselected = SeleniumUtils.checkBoxIsSelect("sharechkBox", "id");
				if(Isselected == true){
					SeleniumUtils.ClickOnItems("viewandedit", "id");
				}
				else{
					SeleniumUtils.ClickOnItems("sharechkBox", "id");
					SeleniumUtils.ClickOnItems("viewandedit", "id");
				}
				String chooseemployee = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_usersearch"), "class", "id");
				if(chooseemployee.contains("TokensContainer ui-sortable disable")){
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("While Selecting Share with Everyone ("+manageaccess+") choose employee field get disable verified successfully for - "+listname+"("+listtype+")",ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));

				}
				else{
					SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("While Selecting Share with Everyone ("+manageaccess+") choose employee field get disable failed for - "+listname+"("+listtype+")",ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
				}
				SeleniumUtils.ClickOnItems("shareMultiList", "id");
				SeleniumUtils.ClickOnItems("modal_share_send_yes", "id");
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");
				String submitmessage1 = SeleniumUtils.getTextfromField(SeleniumUtils.toastMessageElement,"xpath");
				if(submitmessage1.equalsIgnoreCase(MyListMain.MYLISTPROP.getProperty("ML_1_10_v_toastmsgSucess"))){
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("List Shared Successfully for - "+listname+"("+listtype+")" ,ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));	
				}
				else{
					SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("List Shared Failed for - "+listname+"("+listtype+")" ,ExtentColor.RED));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
				}
				existaccessTableVerify(values);
				ListHistory_ML_1_11.ShareListHistory(listname, manageaccess,mail,SeleniumUtils.childTest);
				break;	
			}
		}catch(Exception e){

		}
	}
	
	/**
	 * Given List should share with particular user for view only  and view edit access
	 */
	public static void shareWithEmployee(String manageaccess,String employee,String listname,Map<String, String> values){
		try{
			String listtype = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_type")+listname+"']", "list-type", "xpath");
			String mail = values.get(MyListMain.MYLISTPROP.getProperty("ML_1_10_v_email"));
			SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode(manageaccess+ "-for"+listname+"("+listtype+")");
			SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Sharing List from one to particular user");
			switch(manageaccess){
			case "View only":
				if(SeleniumUtils.checkElementDisplayedProp(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']","xpath")){	
					SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']/span","xpath");
					SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_share")+listname+"']//ul//a[@class='sharelist']","xpath");	

					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Given listname input from excel is verified for View Only - "+listname+"("+listtype+")",ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));
				}
				else{
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("List Name Failed to be Excecuted -  "+listname,ExtentColor.GREY));
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
				}
//				SeleniumUtils.ClickOnItems("sharechkBox", "id");
//				SeleniumUtils.ClickOnItems("viewonly", "id");
				Boolean Isselected = SeleniumUtils.checkBoxIsSelect("sharechkBox", "id");
				if(Isselected == true){
					SeleniumUtils.ClickOnItems("sharechkBox", "id");
					SeleniumUtils.ClickOnItems("viewonly", "id");
				}
				else{
					SeleniumUtils.ClickOnItems("viewonly", "id");
				}
				SeleniumUtils.ClickOnItems("selectUsersearch", "id");
				
				String chooseemployeeonly = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_usersearch"), "class", "id");
				if(chooseemployeeonly.equalsIgnoreCase("TokensContainer ui-sortable Focused")){
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("While Selecting "+manageaccess+" choose employee field get enable verified successfully for - "+listname+"("+listtype+")",ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));

				}
				else{
					SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("While Selecting "+manageaccess+" choose employee field get enabled failed for - "+listname+"("+listtype+")",ExtentColor.RED));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
				}

				SeleniumUtils.dropDownInputTokenize(MyListMain.MYLISTPROP.getProperty("ML_1_10_employeelist"),employee, "xpath");
				SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_employeedropdown"), "xpath");
				String employeemail = SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_employeemail"), "xpath");
				
				 Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(employeemail);
			     while(m.find()) {
			      if(employee.equalsIgnoreCase(m.group(1))){
			    	  SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Given employee email input from excel verified successfully for - "+listname+"("+listtype+")"+"-"+manageaccess,ExtentColor.GREEN));			 
			    	  SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));
			      }else{
			    	  SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Given employee email input from excel Failed for - "+listname+"("+listtype+")"+"-"+manageaccess,ExtentColor.RED));			 
			    	  SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
			      }
			     }
				SeleniumUtils.ClickOnItems("shareMultiList", "id");
				SeleniumUtils.ClickOnItems("modal_share_send_yes", "id");
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");

				String submitmessage = SeleniumUtils.getTextfromField(SeleniumUtils.toastMessageElement,"xpath");
				if(submitmessage.equalsIgnoreCase(MyListMain.MYLISTPROP.getProperty("ML_1_10_v_toastmsgSucess"))){
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("List Shared Successfully for - "+listname+"("+listtype+")" ,ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));	
				}
				else{
					SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("List Shared Failed for - "+listname+"("+listtype+")" ,ExtentColor.RED));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
				}
				existaccessTableVerify(values);
				ListHistory_ML_1_11.ShareListHistory(listname, manageaccess,mail,SeleniumUtils.childTest);
				break;
			case "View and Edit":
				if(SeleniumUtils.checkElementDisplayedProp(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']","xpath")){	
					SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']/span","xpath");
					SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_share")+listname+"']//ul//a[@class='sharelist']","xpath");	

					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Given listname input from excel is verified for View and Edit - "+listname+"("+listtype+")",ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));
				}
				else{
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("List Name Failed to be Excecuted for View and Edit -  "+listname,ExtentColor.GREY));
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
				}

				//SeleniumUtils.ClickOnItems("viewandedit", "id");
				Boolean Isselected1 = SeleniumUtils.checkBoxIsSelect("sharechkBox", "id");

				if(Isselected1 == true){
					SeleniumUtils.ClickOnItems("sharechkBox", "id");
					SeleniumUtils.ClickOnItems("viewonly", "id");
				}
				else{
					SeleniumUtils.ClickOnItems("viewonly", "id");
				}
				SeleniumUtils.ClickOnItems("selectUsersearch", "id");
				String chooseemployee = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_usersearch"), "class", "id");
				if(chooseemployee.equalsIgnoreCase("TokensContainer ui-sortable Focused")){
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("While Selecting "+manageaccess+" choose employee field get enable verified successfully for - "+listname+"("+listtype+")",ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));

				}
				else{
					SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Choose Employee(s) Field Failed for -" +listname+"("+listtype+")"  +" "+"list in View and Edit access",ExtentColor.RED));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
				}

				SeleniumUtils.dropDownInputTokenize(MyListMain.MYLISTPROP.getProperty("ML_1_10_employeelist"),employee, "xpath");
				SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_employeedropdown"), "xpath");
				String employeemail1 = SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_employeemail"), "xpath");
				
				 Matcher m1 = Pattern.compile("\\(([^)]+)\\)").matcher(employeemail1);
			     while(m1.find()) {
			    	 if(employee.equalsIgnoreCase(m1.group(1))){
			    	  SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Given employee email input from excel verified successfully for - "+listname+"("+listtype+")"+"-"+manageaccess,ExtentColor.GREEN));			 
			    	  SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));
			      }else{
			    	  SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Given employee email input from excel Failed for - "+listname+"("+listtype+")"+"-"+manageaccess,ExtentColor.RED));			 
			    	  SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
			      }
			     }
				SeleniumUtils.ClickOnItems("shareMultiList", "id");
				SeleniumUtils.ClickOnItems("modal_share_send_yes", "id");
				SeleniumUtils.waitUntilElementHide("loading_screen", "id");

				String submitmessage1 = SeleniumUtils.getTextfromField(SeleniumUtils.toastMessageElement,"xpath");
				if(submitmessage1.equalsIgnoreCase(MyListMain.MYLISTPROP.getProperty("ML_1_10_v_toastmsgSucess"))){
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("List Shared Successfully for - "+listname+"("+listtype+")" ,ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));	
				}
				else{
					SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("List Shared Failed for - "+listname+"("+listtype+")" ,ExtentColor.RED));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
				}
				existaccessTableVerify(values);
				ListHistory_ML_1_11.ShareListHistory(listname, manageaccess,mail,SeleniumUtils.childTest);
				break;	
			}
		}catch(Exception e){

		}
	}
	/**
	 * View access verify in existing table.
	 */
	public static void existaccessTableVerify(Map<String, String> values) throws Exception {
		String List_Name=values.get(MyListMain.MYLISTPROP.getProperty("ML_1_10_v_listname"));
		String listname = List_Name.trim();
		
		String employee=values.get(MyListMain.MYLISTPROP.getProperty("ML_1_10_v_employee"));
		String access = values.get(MyListMain.MYLISTPROP.getProperty("ML_1_10_v_access"));
		String manageaccess = values.get(MyListMain.MYLISTPROP.getProperty("ML_1_10_v_manageaccess"));
		SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']/span","xpath");

		SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_share")+listname+"']//ul//a[@class='sharelist']","xpath");
		int count = SeleniumUtils.countOfTableBodyRows(MyListMain.MYLISTPROP.getProperty("ML_1_10_existingaccesstable"),"xpath");
		for(int i =1;i<count;i++){
			String check_employeename=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_access_getname").replace("temp1",String.valueOf(i) ),"xpath");
			String check_emailid=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_access_getemail").replace("temp2",String.valueOf(i) ),"xpath");
			String check_existaccess=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_access_getexistaccess").replace("temp3",String.valueOf(i) ),"xpath");

			if(manageaccess.equalsIgnoreCase(check_existaccess) && employee.equalsIgnoreCase(check_emailid) ){
				SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Share List - Existing access Table verified for Share with - "+check_employeename +"-" +check_existaccess,ExtentColor.GREEN));			 
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));

			}else{
				SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Share List - Existing access Table Failed for Share with - "+check_employeename +"-" +check_existaccess,ExtentColor.RED));			 
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
			}
		}
		if(access.equalsIgnoreCase("Share with Everyone")){
			SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_sharecancelbttn"),"xpath");
			SeleniumUtils.ClickOnItems("modal_share_yes", "id");
			shareWithLogin(listname,manageaccess);
			
		}
		else{
			SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_sharecancelbttn"),"xpath");
			shareWithEmployeeUserVerify(listname,manageaccess,employee);
		}
	}
	/**
	 * Shared List verification for all login user
	 */
	public static void shareWithLogin(String listname,String manageaccess){
		try{
			
			SeleniumUtils.loginroleWithoutTabSwitch(2);
			SeleniumUtils.tabSelection("My Lists","My Lists V3");
			SeleniumUtils.setTimeoutUntilVisibility(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_my_list_toggle_visible"), "id");
			try{
			SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_3_mylist_tab")));
			
			
			
				SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Shared List Verification for other user - Sales"+"  "+manageaccess);
				SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("username and password for sales user",ExtentColor.GREEN));
				shareWithEveryoneUserVerify(listname,manageaccess);
				
			}catch(Exception e){
				SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Shared List Verification for other user - Sales"+"  "+manageaccess);
				SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Invalid username and password for sales login",ExtentColor.RED));
				SeleniumUtils.pageRefresh();
			}
			
				SeleniumUtils.loginroleWithoutTabSwitch(3);
				Thread.sleep(5000);
				SeleniumUtils.tabSelection("My Lists","My Lists V3");
				SeleniumUtils.setTimeoutUntilVisibility(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_my_list_toggle_visible"), "id");
				try{
				SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_3_mylist_tab")));
				
				
				
					SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Shared List Verification for other user - Analyst"+"  "+manageaccess);
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("username and password for Aanlyst user",ExtentColor.GREEN));
					shareWithEveryoneUserVerify(listname,manageaccess);
					
				}catch(Exception e){
					SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Shared List Verification for other user - Analyst"+"  "+manageaccess);
					SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Invalid username and password for Analyst user",ExtentColor.RED));
					SeleniumUtils.pageRefresh();
				}
				
				SeleniumUtils.loginroleWithoutTabSwitch(4);
				SeleniumUtils.tabSelection("My Lists","My Lists V3");
				SeleniumUtils.setTimeoutUntilVisibility(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_my_list_toggle_visible"), "id");
				try{
				SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_3_mylist_tab")));
				
				
				
					SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Shared List Verification for other user - Sales Assistant"+"  "+manageaccess);
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("username and password for sales assistant user",ExtentColor.GREEN));
					shareWithEveryoneUserVerify(listname,manageaccess);
					
				}catch(Exception e){
					SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Shared List Verification for other user - Sales Assistant"+"  "+manageaccess);
					SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Invalid username and password for  sales assistant user",ExtentColor.RED));
					SeleniumUtils.pageRefresh();
				}
				//SeleniumUtils.loginroleWithoutTabSwitch(3);
		}
		catch(Exception e){
			
		}
		finally{

			SeleniumUtils.loginroleWithoutTabSwitch(1);
			SeleniumUtils.tabSelection("My Lists","My Lists V3");
			SeleniumUtils.setTimeoutUntilVisibility(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_my_list_toggle_visible"), "id");
		}
}
	
	/**
	 * Shared List user Verification for particular login user
	 */
	public static void shareWithEmployeeUserVerify(String listname,String manageaccess,String employee){
		try{
			switch(employee){
			case "Jack.Lyons@firm.com":
				SeleniumUtils.loginroleWithoutTabSwitch(3);
				SeleniumUtils.tabSelection("My Lists","My Lists V3");
				SeleniumUtils.setTimeoutUntilVisibility(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_my_list_toggle_visible"), "id");
				try{
				SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_3_mylist_tab")));
				
				
				
					SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Shared List Verification for other user - Analyst"+"  "+manageaccess);
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("username and password for Analyst user",ExtentColor.GREEN));
					shareWithEveryoneUserVerify(listname,manageaccess);
					
				}catch(Exception e){
					SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Shared List Verification for other user - Analyst"+"  "+manageaccess);
					SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Invalid username and password for Analyst login",ExtentColor.RED));
					SeleniumUtils.pageRefresh();
				}	
						
				break;
			case "aberam@datazoic.com":
				SeleniumUtils.loginroleWithoutTabSwitch(2);
				SeleniumUtils.tabSelection("My Lists","My Lists V3");
				SeleniumUtils.setTimeoutUntilVisibility(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_my_list_toggle_visible"), "id");
				try{
				SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_3_mylist_tab")));
				
				
				
					SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Shared List Verification for other user - Sales"+"  "+manageaccess);
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("username and password for sales user",ExtentColor.GREEN));
					shareWithEveryoneUserVerify(listname,manageaccess);
					
				}catch(Exception e){
					SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Shared List Verification for other user - Sales"+"  "+manageaccess);
					SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Invalid username and password for sales login",ExtentColor.RED));
					SeleniumUtils.pageRefresh();
				}	
							
				break;
			case "Billy.Midwinter@firm.com":
				SeleniumUtils.loginroleWithoutTabSwitch(4);
				SeleniumUtils.tabSelection("My Lists","My Lists V3");
				SeleniumUtils.setTimeoutUntilVisibility(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_my_list_toggle_visible"), "id");
				try{
				SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_3_mylist_tab")));
				
				
				
					SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Shared List Verification for other user - Sales Assistant"+"  "+manageaccess);
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("username and password for sales assistant user",ExtentColor.GREEN));
					shareWithEveryoneUserVerify(listname,manageaccess);
					
				}catch(Exception e){
					SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Shared List Verification for other user - Sales Assistant"+"  "+manageaccess);
					SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Invalid username and password for sales assistant login",ExtentColor.RED));
					SeleniumUtils.pageRefresh();
				}	
								
				break;
			 default:
				 
			}
		} 
catch(Exception e){
			
		}
		finally{
			
		SeleniumUtils.loginroleWithoutTabSwitch(1);
		SeleniumUtils.tabSelection("My Lists","My Lists V3");
		SeleniumUtils.setTimeoutUntilVisibility(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_my_list_toggle_visible"), "id");
		}
	}
	
	/**
	 * After Logged in user To verify View access for that shared list.
	 */
	public static void shareWithEveryoneUserVerify(String listname,String manageaccess){
		try{
			String loginusername = SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_loginUsername"), "xpath");
			String listtype = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_type")+listname+"']", "list-type", "xpath");
			String list_id = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_total_contact_count")+listname+"']", "data-list-id", "xpath");
			//SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Shared List Verification for other user - "+loginusername+"  "+"for - "+manageaccess);
			switch(manageaccess){
			case "View only":
				if(SeleniumUtils.checkElementDisplayedProp(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']","xpath")){	
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Shared List is displaying in - "+loginusername+"- verified for"+listname+"("+listtype+")",ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));
				}
				else{
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("List Name Failed to be Excecuted -  "+listname,ExtentColor.GREY));
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
				}
				SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']/span","xpath");
				boolean shareaccess = SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_share")+listname+"']//ul//a[@class='sharelist sharenoclick']")).isDisplayed();
				boolean editaccess = SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_share")+listname+"']//ul//a[@class='list-editlist edit_no_click']")).isDisplayed();
				boolean deleteaccess = SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_share")+listname+"']//ul//a[@class='deletelist delnoclick']")).isDisplayed();
				if(shareaccess == false && editaccess == false && deleteaccess == false){
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Dropdown access in shared list verified for view only -"+listname+"("+listtype+")",ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));
				}else{
					SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Dropdown access in shared list Failed for view only -"+listname+"("+listtype+")",ExtentColor.RED));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));

				}
				if(listtype.equalsIgnoreCase("Static")){
					SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']/span","xpath");
					SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']","xpath");
					Thread.sleep(2000);
					if(loginusername.equalsIgnoreCase("Analyst")){
						ListContactCountVerify(listname,listtype,loginusername);
					}
					else{
						if(!SeleniumUtils.webDriver.findElement(By.xpath("//*[@class='covered_acc']//input")).isSelected()){
							ListContactCountVerify(listname,listtype,loginusername);
						}
						else{
							SeleniumUtils.ClickOnItems("//*[@class='covered_acc']//input", "xpath");
							//SeleniumUtils.waitUntilElementHide("loading_screen", "id");
							ListContactCountVerify(listname,listtype,loginusername);
						}
					}
					listCommonVerify(listname,listtype,loginusername,manageaccess);
				}
				else{
					SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']/span","xpath");
					SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']","xpath");
					Thread.sleep(2000);
					if(loginusername.equalsIgnoreCase("Analyst")){
						ListContactCountVerify(listname,listtype,loginusername);
					}
					else{
						if(!SeleniumUtils.webDriver.findElement(By.xpath("//*[@class='covered_acc']//input")).isSelected()){
							ListContactCountVerify(listname,listtype,loginusername);
						}
						else{
							SeleniumUtils.ClickOnItems("//*[@class='covered_acc']//input", "xpath");
							SeleniumUtils.waitUntilElementHide("loading_screen", "id");
							ListContactCountVerify(listname,listtype,loginusername);
						}
					}
			}
				break;
			case "View and Edit":
				if(SeleniumUtils.checkElementDisplayedProp(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']","xpath")){
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Shared List is displaying in - "+loginusername+"- verified for"+listname+"("+listtype+")",ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));
				}
				else{
					SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("List Name Failed to be Excecuted -  "+listname,ExtentColor.GREY));
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));
				}
					SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']/span","xpath");
					boolean deleteaccess1 = SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_share")+listname+"']//ul//a[@class='deletelist delnoclick']")).isDisplayed();
					if(deleteaccess1 == false){
						SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Dropdown access in shared list verified for view and edit -"+listname+"("+listtype+")",ExtentColor.GREEN));			 
						SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),0));
					}else{
						SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Dropdown access in shared list Failed for view and edit -"+listname+"("+listtype+")",ExtentColor.RED));			 
						SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_share"),1));

					}
					if(listtype.equalsIgnoreCase("Static")){
						SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']/span","xpath");
						SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']","xpath");
						Thread.sleep(2000);
						SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_select_all_checkbox").replace("listid", list_id),"xpath");
						if(loginusername.equalsIgnoreCase("Analyst")){
							ListContactCountVerify(listname,listtype,loginusername);
						}
						else{
							if(!SeleniumUtils.webDriver.findElement(By.xpath("//*[@class='covered_acc']//input")).isSelected()){
								ListContactCountVerify(listname,listtype,loginusername);
							}
							else{
								SeleniumUtils.ClickOnItems("//*[@class='covered_acc']//input", "xpath");
								ListContactCountVerify(listname,listtype,loginusername);
							}
						}
						listCommonVerify(listname,listtype,loginusername,manageaccess);
					}else{
						SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']/span","xpath");
						SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_list_option")+listname+"']","xpath");
						Thread.sleep(2000);
						if(loginusername.equalsIgnoreCase("Analyst")){
							ListContactCountVerify(listname,listtype,loginusername);
						}
						else{
							if(!SeleniumUtils.webDriver.findElement(By.xpath("//*[@class='covered_acc']//input")).isSelected()){
								ListContactCountVerify(listname,listtype,loginusername);
							}
							else{
								SeleniumUtils.ClickOnItems("//*[@class='covered_acc']//input", "xpath");
								SeleniumUtils.waitUntilElementHide("loading_screen", "id");
								ListContactCountVerify(listname,listtype,loginusername);
							}
						}
					}
					break;	
			}
		}catch(Exception e){
			
		}
		
	}
	
	/**
	 * Contact Count Verification for shared list
	 */
	private static void ListContactCountVerify(String listname,String listtype,String loginusername){
		try{
			String list_id = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_total_contact_count")+listname+"']", "data-list-id", "xpath");	
			String total_showing_contact=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_total_contact_open_list").replace("listid", list_id),"xpath").replace(",","");
			SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_select_all_checkbox").replace("listid", list_id),"xpath");
	     	//total selected contact
	        String total_selected_contact=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_all_checkbox_count").replace("listid", list_id),"xpath").replace("-","").replace("Selected","").replace(",","").trim();
			if(total_showing_contact.equalsIgnoreCase(total_selected_contact)){
				SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Shared List Contact Count Verified for - "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.GREEN));
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_Count"),1));	
			}else{
				SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Shared List Contact Count Failed for - "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.RED));
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_Count"),1));	
			}
		}catch(Exception e){
			
		}
	}
	
	/**
	 * Given List should share with every user for view only  and view edit access
	 */
	private static void  listCommonVerify(String listname,String listtype,String loginusername,String manageaccess){
		SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_bulk_interaction_button"))).click();
		boolean bulkloginteraction = SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_bulk_interaction_button"))).isSelected();
		if(!bulkloginteraction){
			SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Bulk Log Interaction Button Verified for - "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.GREEN));
			}
		else{
				SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Bulk Log Interaction Button failed for  - "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.RED));
				}
		SeleniumUtils.webDriver.findElement(By.id("interactionPopupClose")).click();
		
		//bulkcall
		boolean bulkcall = SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_bulk_call_button"))).isSelected();
		if(!bulkcall){
			SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Bulk Call Button Verified for - "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.GREEN));
			}
		else{
				SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Bulk Call Button failed for  - "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.RED));
			}
		//bulkmail
		boolean bulkmail = SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_bulk_mail_button"))).isSelected();
		if(!bulkmail){
			SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Bulk Mail Button Verified for - "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.GREEN));
			}
		else{
				SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Bulk Mail Button failed for  - "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.RED));
			}
		
		//copyheader
		boolean copyheader = SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_copyheader_button"))).isSelected();
		if(!copyheader){
			SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Copy Selected to New List Button Verified for - "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.GREEN));
			}
		else{
				SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Copy Selected to New List Button failed for  - "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.RED));
				}
		
		//copyselectedto
		boolean copyexist = SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_copyseltoexist_button"))).isSelected();
		if(!copyexist){
			SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Copy Selected to verified for- "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.GREEN));
			}
		else{
				SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Copy Selected failed for - "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.RED));
				}
		
		boolean quickAdd = checkQuickAddIsEnable();
		if(manageaccess.equalsIgnoreCase("View Only")){
			 if(quickAdd){	
				 SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Quick Add Contact Failed for- "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.RED));
			 }else{
				 SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Quick Add Contact Passed for- "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.GREEN));
			 }
		}else{
			if(quickAdd){	
				SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Quick Add Contact Passed for- "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.GREEN));
			 }else{
				 
				 SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Quick Add Contact Failed for- "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.RED));
			 }
		}
		
		
		boolean bulkAdd = checkBulkContactIsEnable();
		if(manageaccess.equalsIgnoreCase("View Only")){
			 if(bulkAdd){	
				 SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Bulk Add Contact Failed for- "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.RED));
			 }else{
				 SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Bulk Add Contact Passed for- "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.GREEN));
			 }
		}else{
			if(bulkAdd){	
				SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Bulk Add Contact Passed for- "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.GREEN));
			 }else{
				 
				 SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Bulk Add Contact Failed for- "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.RED));
			 }
		}
		boolean deleteContact = checkDeleteContactIsEnable();
		if(manageaccess.equalsIgnoreCase("View Only")){
			 if(deleteContact){	
				 SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Delete Contact Failed for- "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.RED));
			 }else{
				 SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Delete Contact Passed for- "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.GREEN));
			 }
		}else{
			if(bulkAdd){	
				SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Delete Contact Passed for- "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.GREEN));
			 }else{
				 
				 SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Delete Add Contact Failed for- "+listname+"("+listtype+")"+"-"+loginusername,ExtentColor.RED));
			 }
		}
		
	}
	
	private static boolean checkQuickAddIsEnable() {
		boolean status = false;
		try{
		  @SuppressWarnings("unused")
		WebElement quickAdd = SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_quick_add_button")));
		  status=true;
		}
		catch (Exception e) {
		 status=false	; 
		}
		return status;
	}
	
	private static boolean checkBulkContactIsEnable() {
		boolean status = false;
		try{
		  @SuppressWarnings("unused")
		WebElement quickAdd = SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_bulk_add_button")));
		  status=true;
		}
		catch (Exception e) {
		 status=false	; 
		}
		return status;
	}
	
	private static boolean checkDeleteContactIsEnable() {
		boolean status = false;
		try{
		  @SuppressWarnings("unused")
		WebElement quickAdd = SeleniumUtils.webDriver.findElement(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_10_id_delete_button")));
		  status=true;
		}
		catch (Exception e) {
		 status=false	; 
		}
		return status;
	}
}
		
		

