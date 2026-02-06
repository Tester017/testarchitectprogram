package com.dz.prism.module.mylist;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class ListCount_ML_1_2 {
	static int count;
	public static void contactListCount(){
		try{
			
		// Total List Count 
		SeleniumUtils.testCase=  SeleniumUtils.extendReports.createTest(MyListMain.MYLISTPROP.getProperty("List_Count")+"_Contact count in list page");
		SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Contact count verify in list page");
		//count = SeleniumUtils.getCountOfDropdownList(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_total_list_count"),"xpath");
		count=Integer.valueOf(MyListMain.MYLISTPROP.getProperty("ML_1_2_v_listcount"));
		if(count==0){
			SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("List is empty",ExtentColor.GREEN)); 
			SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_Count"),0));
		}
		SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Simple List Contact Count Verify");
		for(int i=1;i<=count;i++){
			// Expand Tab section
			String SideMenuStatus = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_list_expand"),"class","id");
	        if (SideMenuStatus.contains("fa-angle-double-left")) {
	        	SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_list_expand"),"id");
	        }
	        String listtype = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_total_contact_count")+i+"]", "list-type", "xpath");
	       
	        String list_id = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_total_contact_count")+i+"]", "data-list-id", "xpath");
	        String list_name = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_total_contact_count")+i+"]", "data-listname", "xpath");
	        
	        //Simple list
	        if(listtype.equalsIgnoreCase(MyListMain.MYLISTPROP.getProperty("ML_1_2_v_listtype"))){
	        	
	        	String sm_list_cnt_mylist=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_total_contact_count")+i+MyListMain.MYLISTPROP.getProperty("ML_1_2_id_total_contact_count_rem"), "xpath").replace("(","").replace(")","");
	        	// click simple list
		        SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_total_contact_count")+i+"]","xpath");
		        SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		        Thread.sleep(8000);
		        SeleniumUtils.ClickOnItems("common_filter", "id");
		        String total_showing_contact=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_total_contact_open_list").replace("listid", list_id),"xpath").replace(",","");
		        SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_select_all_checkbox"),"xpath");
		     	//total selected contact
		        String total_selected_contact=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_all_checkbox_count").replace("listid", list_id),"xpath").replace("-","").replace("Selected","").replace(",","").trim();
		        if(sm_list_cnt_mylist.equalsIgnoreCase(total_showing_contact) && sm_list_cnt_mylist.equalsIgnoreCase(total_selected_contact)){
		     		 SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Simple list contact count successfully verified ("+list_name+") - "+sm_list_cnt_mylist,ExtentColor.GREEN));			 
					 SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_Count"),0));
		     	}else{
		     		SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Simple list contact count failed ("+list_name+") - "+sm_list_cnt_mylist +" "+total_showing_contact+" "+total_selected_contact,ExtentColor.RED));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_Count"),1));
		     	}
		     	SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_list_close"),"xpath");
	        }
		}
		SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Dynamic List Contact Count Verify");
		for(int i=1;i<=count;i++){
			// Expand Tab section
			String SideMenuStatus = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_list_expand"),"class","id");
	        if (SideMenuStatus.contains("fa-angle-double-left")) {
	        	SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_list_expand"),"id");
	        }
	        String listtype = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_total_contact_count")+i+"]", "list-type", "xpath");
	       
	        String list_id = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_total_contact_count")+i+"]", "data-list-id", "xpath");
	        String list_name = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_total_contact_count")+i+"]", "data-listname", "xpath");
	        
	        //Dynamic list
	        if(listtype.equalsIgnoreCase(MyListMain.MYLISTPROP.getProperty("ML_1_2_v_listtypedynamic"))){
	        	// click dynamic list
		        SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_total_contact_count")+i+"]","xpath");
		        SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		        Thread.sleep(2000);
		        SeleniumUtils.setTimeOut(2);
		        if(SeleniumUtils.checkElementDisplayedProp(MyListMain.MYLISTPROP.getProperty("ML_1_6_id_info_criteria_mainpage").replace("liid", list_id),"xpath")){
		        	 SeleniumUtils.setTimeOut(2);
					 SeleniumUtils.waitUntilElementHide(MyListMain.MYLISTPROP.getProperty("ML_1_6_id_info_criteria_mainpage1").replace("liid", list_id),"xpath");
					 SeleniumUtils.setTimeOut(20);
		        }
		        else{
		        	Thread.sleep(6000);
		        }
		        SeleniumUtils.setTimeOut(20);
		        String total_showing_contact=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_total_contact_open_list").replace("listid", list_id),"xpath").replace(",","");
    		    SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_select_all_checkbox").replace("listid", list_id),"xpath");
		     	//total selected contact
		        String total_selected_contact=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_all_checkbox_count").replace("listid", list_id),"xpath").replace("-","").replace("Selected","").replace(",","").trim();
		        
		        if(total_showing_contact.equalsIgnoreCase(total_selected_contact)){
		        	SeleniumUtils.childTest.log(Status.PASS,MarkupHelper.createLabel("Dynamic list contact count successfully verified ("+list_name+") - "+total_showing_contact,ExtentColor.GREEN));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_Count"),0));
		     	}else{
		     		SeleniumUtils.childTest.log(Status.FAIL,MarkupHelper.createLabel("Dynamic list contact count failed ("+list_name+") - "+total_showing_contact+" "+total_selected_contact,ExtentColor.RED));			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("List_Count"),1));	
		     	}
		        SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_list_close"),"xpath");
	        }
	        if(count==i){
	        	SeleniumUtils.pageRefresh();
	        	SeleniumUtils.waitUntilElementHide("loading_screen", "id");
	        }
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	}
}
