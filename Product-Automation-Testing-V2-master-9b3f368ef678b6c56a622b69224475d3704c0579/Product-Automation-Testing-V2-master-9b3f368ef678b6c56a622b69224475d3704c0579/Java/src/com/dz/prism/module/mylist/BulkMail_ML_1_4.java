package com.dz.prism.module.mylist;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.dz.prism.module.activity.ActivityMain;
import com.dz.prism.module.activity.IsInteractionLogged_AI_1;
import com.dz.prism.utils.SeleniumUtils;

public class BulkMail_ML_1_4 {
	static String List_type_con="Static";
	static int count;
	static String random_num="";
	public static void Readexcel() throws IOException{
		SeleniumUtils.testCase=  SeleniumUtils.extendReports.createTest(MyListMain.MYLISTPROP.getProperty("Bulk_mail")+"_Bulk mail verification");
		SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Bulk mail and draft verification");
		SeleniumUtils.pageRefresh();
    	SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		//Read excel file
		Map<String, List<Map<String, String>>> tempdata = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+MyListMain.MYLISTPROP.getProperty("ML_1_6_v_testCasePath"));
		for(Entry<String, List<Map<String, String>>> testRows : tempdata.entrySet()){
			List<Map<String, String>> innerRows = testRows.getValue();
			if(testRows.getKey().equals("BulkMail")){
				for(Map<String,String> values : innerRows){
					bulkmail(values);
				}
				
			}
		}
	}
	/**
	 * Save draft and bulk mail send
	 * @param values
	 * @param List_name
	 * @param total_selected_contact
	 * @param list_id
	 */
	public static void bulkmail(Map<String, String> values){
		try{
			
			SeleniumUtils.childTest =  SeleniumUtils.parentTest.createNode("Bulk mail and draft verification "+values.get(MyListMain.MYLISTPROP.getProperty("ML_1_1_v_test_case_id")));
			String SideMenuStatus = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_list_expand"),"class","id");
	        if (SideMenuStatus.contains("fa-angle-double-left")) {
	        	SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_list_expand"),"id");
	        }
	        String List_name=values.get(MyListMain.MYLISTPROP.getProperty("ML_1_4_v_list_name"));
	        SeleniumUtils.setTimeoutUntilVisibility("//*[@id='myList']//ul[@class='list-mgt-listdtls m-t-xs']//li", "xpath");
	        SeleniumUtils.setTimeOut(2);
			if(SeleniumUtils.checkElementDisplayedProp(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_list_option")+List_name+"']","xpath")){
				SeleniumUtils.setTimeOut(20);
		        //Specific list click
		        SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_list_option")+List_name+"']","xpath");
		        SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		        String list_id = SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_6_id_list_id").replace("liname", List_name), "data-list-id", "xpath");
		        
		        SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		        Thread.sleep(6000);
		        String total_showing_contact=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_total_contact_open_list").replace("listid", list_id),"xpath").replace(",","");
		       
		        int total_cnt=Integer.valueOf(total_showing_contact);
		        if(total_cnt==0){
		        	SeleniumUtils.childTest.log(Status.FAIL,"List Contact count is zero");			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
		        }else{
		        	if(total_cnt<=10){
		        		//select all checkbox
				        SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_select_all_checkbox").replace("listid", list_id),"xpath");
		        	}else{
		        		//Select specific count of checkbox
		        		String selectContact = MyListMain.MYLISTPROP.getProperty("ML_1_3_select_table_checkbox");
		                selectContact = selectContact.replace("?", list_id);
		                List<WebElement> selectCheckbox =  SeleniumUtils.webDriver.findElements(By.xpath(selectContact));
		                for(WebElement checkbox: selectCheckbox){
		                    count++;
		                    Thread.sleep(1000);
		                    checkbox.click();
		                    if(count == 10){                   
		                        break;
		                    }
		                }
		        	}
			        String total_selected_contact=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_2_id_all_checkbox_count").replace("listid", list_id),"xpath").replace("-","").replace("Selected","").trim();
			        //Get contact name and account name in list table
			        List<String> con_name=get_table_data(list_id,values);
			        //Click bulk mail button
			        SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_bulk_mail_button"),"xpath");
			        String Bulk_mail_header = SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_bulk_mail_header"),"xpath");
			        if(!Bulk_mail_header.equals("Bulk Email")){
			        	SeleniumUtils.childTest.log(Status.FAIL, "Select / Create a draft page - Bulk mail header not matched");			 
						SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
			        }
			        //Verify to address (i.e. listname(con_count))
			        String to_address = SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_bulk_mail_to").replace("listid", list_id),"xpath");
			        String total_con_cnt=to_address.replace(List_name, "").trim().replace("(", "").replace(")", "");
			        if(to_address.contains(List_name) && total_con_cnt.equals(total_selected_contact)){
			        	SeleniumUtils.childTest.log(Status.PASS,"Select / Create a draft page - List name and contact count matched in draft list page"+"["+to_address+"]");	
						SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));	
			        }else{
		        		SeleniumUtils.childTest.log(Status.FAIL, "Select / Create a draft page - List name and contact count not matched in draft list page");			 
						SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
			        }
		        	//Click create new draft button
			        SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_create_new_draft"),"xpath");
			        //Click web editor
			        SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_web_editor_btn"),"xpath");
			        //common
			        //Thread.sleep(2000);
			        SeleniumUtils.setTimeoutUntilVisibility(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_new_draft_content_name"), "xpath");
			        String new_draft_sub=SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_new_draft_sub"),"value","xpath");
			        String new_draft_content_name=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_new_draft_content_name"),"xpath");
			        String new_draft_content_msg_body=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_new_draft_content_msg_body"),"xpath");
			        if(new_draft_sub.equals(MyListMain.MYLISTPROP.getProperty("ML_1_4_v_subject")) && new_draft_content_name.equals(MyListMain.MYLISTPROP.getProperty("ML_1_4_v_subject")+",") && new_draft_content_msg_body.equals(MyListMain.MYLISTPROP.getProperty("ML_1_4_v_msg_body"))){
			        	SeleniumUtils.childTest.log(Status.PASS,"Create a draft page - Subject, Content format verified in create bulk email page");	
						SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));
			        	
			        }else{
			        	SeleniumUtils.childTest.log(Status.FAIL, "Create a draft page - Subject, Content format not matched in create bulk email page");			 
						SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
			        }
			        //Set subject
			        String subject=values.get(MyListMain.MYLISTPROP.getProperty("ML_1_4_v_subject_xl"));
			        if(!subject.isEmpty()){
			        	 SeleniumUtils.setValueToField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_new_draft_sub"),subject,"xpath");
			        	 subject=new_draft_sub+subject;
			        }else
			        	subject=new_draft_sub;
			        
			        String msg_body=values.get(MyListMain.MYLISTPROP.getProperty("ML_1_4_v_content_xl"));
			        if(!msg_body.isEmpty()){
			        	 SeleniumUtils.setValueToField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_new_draft_content_msg_body"),msg_body ,"xpath");
			        	 msg_body=new_draft_content_msg_body+msg_body;
			        }else
			        	subject=new_draft_sub;
			        String research_link=values.get(MyListMain.MYLISTPROP.getProperty("ML_1_4_v_re_link_xl"));
			        if(!research_link.isEmpty()){
			        	 String research_linkArr[] =research_link.split("~");
						 for(int i=0;i<research_linkArr.length;i++){
							 dropDownInputTokenizeEndwith(MyList_ML_1_12.getPropValue("ML_1_4_id_new_draft_research_link"),research_linkArr[i], "xpath");
						 }
			        }
			        SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_new_draft_save_goback"),"xpath");
			        String toastMsg = SeleniumUtils.getToastMessage();
			        if(toastMsg.equals(MyListMain.MYLISTPROP.getProperty("ML_1_4_v_draft_save"))){
			        	SeleniumUtils.childTest.log(Status.PASS,"Create a draft page - Draft saved successfully");	
						SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));
			        	
			        }else{
			        	SeleniumUtils.childTest.log(Status.FAIL, "Create a draft page - Draft not saved successfully");			 
						SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
			        }
			        //SeleniumUtils.waitUntilElementHide(SeleniumUtils.toastMessageElement, "xapth");
			        Thread.sleep(6000);
			        //Verify draft details in the draft table
			        draftmailVerifyTable(subject,"New");
			        //Draft page edit
			        editdraftmail(subject,msg_body,research_link,"updated");
			        //click draft in table
			        if(SeleniumUtils.waitUntilElementDisplayed(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_draft_tbl_button"), 10, "xpath")){
					  SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_draft_tbl_button"),"xpath");
			        }
					//preview page back and send mail
					preview_customize_email(values,con_name,"add");
					preview_customize_email(values,con_name,"edit");
					//Thread.sleep(5000);
					bulk_mail_history(String.valueOf(con_name.size()));
					ListHistory_ML_1_11.BulkHistoryVerify(List_name, con_name.size(),List_type_con,SeleniumUtils.childTest);
					String tag=values.get(MyListMain.MYLISTPROP.getProperty("ML_1_4_v_ticker_xl"));
					values.replace(MyListMain.MYLISTPROP.getProperty("ML_1_4_v_ticker_xl"), tag.replace("~", "|"));
					//Check account,contact,ticker tear sheet
					IsInteractionLogged_AI_1.checkContactTearSheet(values);
					IsInteractionLogged_AI_1.checkTrickerTearSheet(values);
					IsInteractionLogged_AI_1.checkAccountTearSheet(values);
		        }
		        SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_list_close"),"xpath");
			}else{
				SeleniumUtils.setTimeOut(20);
	        	SeleniumUtils.childTest.log(Status.PASS, "List name is invalid - "+List_name);			 
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Verify bulk mail details in bulk mail history page
	 * @param total_selected_contact 
	 */
	private static void bulk_mail_history(String total_selected_contact) {
		try{
			SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("Ml_1_4_id_bulk_mail_history_btn"),"xpath");
			Thread.sleep(2000);
			SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("Ml_1_4_id_bulk_mail_history_referesh"),"id");
			String update_subject=MyListMain.MYLISTPROP.getProperty("ML_1_4_v_subject")+random_num;
			SeleniumUtils.setValueToField(MyListMain.MYLISTPROP.getProperty("Ml_1_4_id_bulk_mail_history_search"), update_subject, "xpath");
			String his_subject=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("Ml_1_4_id_bulk_mail_history_tbl").replace("pos","1"),"xpath");
			String his_date=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("Ml_1_4_id_bulk_mail_history_tbl").replace("pos","2"),"xpath");
			String his_contact_cnt=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("Ml_1_4_id_bulk_mail_history_tbl").replace("pos","3"),"xpath");
			String datearr[]=his_date.split(" ");
			if(his_subject.equals(update_subject) && datearr[0].equals(getCurrDate()) && his_contact_cnt.equals(total_selected_contact)){
				SeleniumUtils.childTest.log(Status.PASS,"Bulk mail history page - Subject, Date, Contact count are verified in bulk mail history page");	
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));
			}else{
				SeleniumUtils.childTest.log(Status.FAIL, "Bulk mail history page - Subject, Date, Contact count are not matched in bulk mail history page");			 
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
			}
			SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("Ml_1_4_id_bulk_mail_history_close"),"xpath");
		}catch(Exception e){
			//e.printStackTrace();
		}
		
	}
	/**
	 * New draft and edit draft details verify that draft table
	 * @param subject
	 * @param action
	 */
	public static void draftmailVerifyTable(String subject,String action){
		try{
			SeleniumUtils.setTimeoutUntilVisibility(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_bulk_mail_header"), "xpath");
			String draft_date=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_draft_tbl_date"),"xpath");
			String draft_subject=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_draft_tbl_sub"),"xpath");
			String draft_edit_date=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_draft_tbl_edit_date"),"xpath");
			String draft_type=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_draft_tbl_draft_type"),"xpath");
			String cur_date=getCurrDate();
			if(!action.equals("Updated")){
				if(cur_date.equals(draft_date)){
					SeleniumUtils.childTest.log(Status.PASS,"Draft table - New Draft created date verfied in draft table");	
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));
					
				}else{
					SeleniumUtils.childTest.log(Status.FAIL, "Draft table - New Draft created date not matched in draft table");			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
				}
			}
			
			if(draft_subject.equals(subject)){
				SeleniumUtils.childTest.log(Status.PASS,"Draft table - "+action+" draft Subject verified in draft table");	
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));
				
			}else{
				SeleniumUtils.childTest.log(Status.FAIL,"Draft table - "+ action+" draft Subject not matched in draft table "+draft_subject+" - "+subject);			 
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
			}
			
			if(cur_date.equals(draft_edit_date)){
				SeleniumUtils.childTest.log(Status.PASS,"Draft table - "+action+" draft edited date verfied in draft table");	
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));
				
			}else{
				SeleniumUtils.childTest.log(Status.FAIL,"Draft table - "+ action+" draft edited date not matched in draft table");			 
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
			}
			if(!action.equals("Updated")){
				if(draft_type.equals("web")){
					SeleniumUtils.childTest.log(Status.PASS,"Draft table - New draft type verfied in draft saved table");	
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));
					
				}else{
					SeleniumUtils.childTest.log(Status.FAIL, "Draft table - New draft type not matched in draft saved table");			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
				}
				SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_draft_tbl_button"),"xpath");
			}
			if(action.equals("Updated")){
				
				Boolean action1=prview_edit_btn_verify("Edit");
				Boolean action2=prview_edit_btn_verify("Preview");
				if(action1 && action2){
					SeleniumUtils.childTest.log(Status.PASS,"Edit and preview button - verified toast message without click a draft option");	
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));
				}else{
					SeleniumUtils.childTest.log(Status.FAIL, "Edit and preview button - not matched toast message without click a draft option");			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//Update draft mail
	public static void editdraftmail(String subject,String msg_body, String Re_Link,String type)  {
		try{
			//Edit draft
			SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_draft_edit_button").replace("btn_type", "Edit"),"xpath");
			Thread.sleep(2000);
			String new_draft_sub=SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_new_draft_sub"),"value","xpath");
			 String new_draft_content_msg_body=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_new_draft_content_msg_body"),"xpath");
		    if(new_draft_sub.equals(subject) && new_draft_content_msg_body.equals(msg_body)){
		    	SeleniumUtils.childTest.log(Status.PASS,"Edit draft page - Subject and message body matched in "+type+" bulk email page");	
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));
		    	
		    }else{
		    	SeleniumUtils.childTest.log(Status.FAIL, "Edit draft page - Subject and message body not matched in "+type+" bulk email page");			 
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
		    }
		    if(!Re_Link.isEmpty()){
		    	 String Re_LinkArr[] =Re_Link.split("~");
		    	 int cnt=1;
		    	 int i=1;
		    	 int j=0;
				 for(i=1;i<=Re_LinkArr.length;i++){
					 String new_draft_research_link=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_edit_draft_con_research_link").replace("re_cnt", String.valueOf(i)),"xpath");
					    if(new_draft_research_link.equals(Re_LinkArr[j])){
					    	cnt++;
					    	j++;
					    }
				 }
			    if(cnt==i && cnt!=1){
			    	SeleniumUtils.childTest.log(Status.PASS,"Edit draft page - Research link matched in "+type+" bulk email page");	
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));
			    	
			    }else{
			    	SeleniumUtils.childTest.log(Status.FAIL, "Edit draft page - Research link  not matched in "+type+" bulk email page");			 
					SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
			    }
		    }
		    String edit_subject = null;
		    if(type.equals("updated")){
		    	random_num=SeleniumUtils.getCurrDate();
		    	edit_subject=MyListMain.MYLISTPROP.getProperty("ML_1_4_v_subject")+random_num;
		    	SeleniumUtils.ClearFieldValue(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_new_draft_sub"), "xpath");
		    	SeleniumUtils.setValueToField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_new_draft_sub"),edit_subject,"xpath");
		    }
		    //save edit button
		    SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_new_draft_save_goback"),"xpath");
	        String toastMsg = SeleniumUtils.getToastMessage();
	        if(toastMsg.equals(MyListMain.MYLISTPROP.getProperty("ML_1_4_v_draft_save"))){
	        	SeleniumUtils.childTest.log(Status.PASS,"Edit draft page - Draft updated successfully");	
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));
	        	
	        }else{
	        	SeleniumUtils.childTest.log(Status.FAIL, "Edit draft page - Draft not updated successfully");			 
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
	        }
	        //toast close
	        SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_toastmsg_close"),"xpath");
	        draftmailVerifyTable(edit_subject,"Updated");
		    
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//bulk mail button close
	public static void bulkmail_btn_close(){
		 try{
			 //Click bulk mail button
		     SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_bulk_mail_button"),"xpath");
		     //Click create new draft button
	         SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_create_new_draft"),"xpath");
	        //Click web editor
	        SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_web_editor_btn"),"xpath");
	        //close draft page
			SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_bulk_mail_close_btn"),"xpath");
			SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_bulk_mail_close_confirm"),"id");
			//Click bulk mail button
		    SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_bulk_mail_button"),"xpath");
		    //click draft
			SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_draft_tbl_button"),"xpath");
		    SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_draft_edit_button").replace("btn_type", "Preview"),"xpath");
		    //close bulk mail page
			SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_bulk_mail_close_btn"),"xpath");
			SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_bulk_mail_close_confirm"),"id");
			try{
				SeleniumUtils.setTimeoutUntilVisibility(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_bulk_mail_button"), "xpath");
				SeleniumUtils.childTest.log(Status.PASS,"Bulk mail close - Bulk mail page and new draft page closed successfully");	
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));
			}catch(Exception e){
				SeleniumUtils.childTest.log(Status.FAIL, "Bulk mail close - Bulk mail page and new draft page not closed successfully");			 
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
			}
		 }catch (Exception e) {
			 e.printStackTrace();
		 } 
	}
	/**
	 * Preview and customize email page
	 * @param values
	 * @param con_namearr
	 * @param action
	 */
	public static void preview_customize_email(Map<String, String> values, List<String> con_namearr, String action){
		try{
			SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_draft_edit_button").replace("btn_type", "Preview"),"xpath");
			if(action.equalsIgnoreCase("edit")){
				 Boolean contact_name_Status=true;
				 Boolean contact_content_Status=true;
				 ArrayList <String> al=new ArrayList <String>();
				 SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_preview_contact_click"),"xpath");
				 List<WebElement> dropDown = SeleniumUtils.webDriver.findElements(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_preview_contact_name")));
				 for(int i=1;i<=dropDown.size();i++){
					 SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_preview_contact_name_multiple").replace("pos", String.valueOf(i)),"xpath");
					 String con_name=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_preview_contact_click"),"xpath");
					 al.add(con_name);
					 if(!con_namearr.contains(con_name)){
						 contact_name_Status=false;
					 }
					 String preview_sub=SeleniumUtils.getAttributefromField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_preview_subject"),"value","id");
					 String preview_content_body=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_preview_content_body"),"xpath");
					 String preview_content_sub=SeleniumUtils.getTextfromField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_preview_content_sub"),"xpath");
			
					 preview_sub=preview_sub.replace("Hi", "").replace(random_num, "").trim();
					 if(!con_name.contains(preview_sub)){
						 contact_content_Status=false;
					 }
					 if(!preview_content_sub.contains(preview_sub)){
						 contact_content_Status=false;
					 }
					 preview_content_body=preview_content_body.replace(values.get(MyList_ML_1_12.getPropValue("ML_1_4_v_content_xl")), "");
					 if(!preview_content_body.equalsIgnoreCase(MyList_ML_1_12.getPropValue("ML_1_4_v_msg_body"))){
						 contact_content_Status=false;
						
					 }
					 if(i!=dropDown.size())
					 SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_preview_contact_click"),"xpath");
				 }
			     values.replace(ActivityMain.ACTIVITYPROP.getProperty("AI_1_v_subject"), random_num);
				 if(al.size()==con_namearr.size()){
					if(contact_name_Status){
						SeleniumUtils.childTest.log(Status.PASS,"preview and customize emil page - Contact name and count matched in preview and customize emil page");	
						SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));
					}else{
						SeleniumUtils.childTest.log(Status.FAIL, "preview and customize emil page - Contact count matched but name not matched in preview and customize emil page");			 
						SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
					}
				 }else{
					 SeleniumUtils.childTest.log(Status.FAIL, "preview and customize emil page - Contact name and count not matched in preview and customize emil page");			 
					 SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
				 }
				 if(contact_content_Status){
					 SeleniumUtils.childTest.log(Status.PASS,"preview and customize emil page - Subject and content verified in preview and customize emil page");	
					 SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));
				 }else{
					 SeleniumUtils.childTest.log(Status.FAIL, "preview and customize emil page - Subject and content not matched in preview and customize emil page");			 
					 SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
				 }
			 
			}
			if(!action.equalsIgnoreCase("edit")){
			 SeleniumUtils.setValueToField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_preview_subject"),"Preview","id");
			 SeleniumUtils.setValueToField(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_preview_content_body"),"Preview content body","xpath");
			}
			SeleniumUtils.scrollUntilElementView(MyListMain.MYLISTPROP.getProperty("Ml_1_4_id_bulk_mail_interaction"),"id");
			SeleniumUtils.setTimeOut(2);
			if(SeleniumUtils.checkElementDisplayedProp(MyList_ML_1_12.getPropValue("Ml_1_4_id_preview_ticker_verify").replace("field","bm_ticker"),"xpath") && SeleniumUtils.checkElementDisplayedProp(MyList_ML_1_12.getPropValue("Ml_1_4_id_preview_ticker_verify").replace("field","bm_sector"),"xpath")){
				SeleniumUtils.setTimeOut(20);
				SeleniumUtils.childTest.log(Status.FAIL, "preview and customize emil page - Ticker and Sector not clear successfully");			 
				SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),1));
			}
			SeleniumUtils.setTimeOut(20);
			 String Ticker=values.get(MyList_ML_1_12.getPropValue("ML_1_4_v_ticker_xl"));
			 String Sector=values.get(MyList_ML_1_12.getPropValue("ML_1_4_v_sector_xl"));
			 String Re_document=values.get(MyList_ML_1_12.getPropValue("ML_1_4_v_re_document_xl"));
			 preview_customize_email_dropdown_set(Ticker.trim(),"bm_ticker");
			 preview_customize_email_dropdown_set(Sector.trim(),"bm_sector"); 
			 if(!action.equalsIgnoreCase("edit")){
				 //Back button
				 preview_customize_email_dropdown_set(Re_document.trim(),"bm_attach_doc");
				 SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_preview_back_btn").replace("btn_type", "Back"),"xpath");
				 SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_preview_back_confirm"),"id");
			 }else{
				 //Interaction log
				 SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("Ml_1_4_id_bulk_mail_interaction"),"id");
				 SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("Ml_1_4_id_bulk_mail_interaction_click").replace("int_type",values.get(MyListMain.MYLISTPROP.getProperty("ML_1_4_v_interaction_xl"))),"xpath");
				 //bulk mail send
				 SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_preview_back_btn").replace("btn_type", "Send"),"xpath");
				 SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_bulkmail_send_confirm"),"id");
				 String toastMsg = SeleniumUtils.getToastMessage();
				 if(toastMsg.equalsIgnoreCase(MyListMain.MYLISTPROP.getProperty("ML_1_4_v_mail_send_toast"))){
					 SeleniumUtils.childTest.log(Status.PASS,"preview and customize emil page - Bulk Mail send successfully");	
					 SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));
				 }else{
					 SeleniumUtils.childTest.log(Status.PASS,"preview and customize emil page - Bulk Mail not send successfully");	
					 SeleniumUtils.childTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(MyListMain.moduleName,MyListMain.MYLISTPROP.getProperty("Bulk_mail"),0));
				 }
				 //toast close
			     SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_toastmsg_close"),"xpath");
			     bulkmail_btn_close();
			 }
		}catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	/**
	 * Dropdown value set for Ticker, Sector, Research document
	 * @param Ticker
	 * @param Field
	 */
	public static void preview_customize_email_dropdown_set(String Ticker, String Field){
		 if(!Ticker.isEmpty() && Ticker!=null){
			 SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_preview_interest_click").replace("field", Field),"xpath");
			 String Tickerarr[]=Ticker.split("~");
			 for(String Tic:Tickerarr){
				 dropDownInputTokenizeEndwith(MyList_ML_1_12.getPropValue("ML_1_4_id_preview_interest").replace("field",Field),Tic, "xpath");
			 }
		 }
	}
	/**
	 * Get contact name in table
	 * @param listId
	 * @param values 
	 * @return
	 */
	public static List<String> get_table_data(String listId, Map<String, String> values){
		List<String> con_name=new ArrayList<String>();
		try{
	         List<WebElement> header = SeleniumUtils.webDriver.findElements(By.xpath(MyListMain.MYLISTPROP.getProperty("ML_1_4_list_get_column_header").replace("?", listId)));
	         int i;
	         int j;
	         for(i=0;i<header.size();i++){
	             if("Contact Name".equalsIgnoreCase( header.get(i).getAttribute("title"))){
	                break;
	             }
	         }
	         for(j=0;j<header.size();j++){
	             if("Client Name".equalsIgnoreCase( header.get(j).getAttribute("title"))){
	                break;
	             }
	         }
	         //contact name
	         String contact_name = MyListMain.MYLISTPROP.getProperty("Ml_1_4_id_table_row");
	         contact_name= contact_name.replace("id?",listId);
	         contact_name = contact_name.replace("?", Integer.toString(i+1));
			 List<WebElement> rowConId = SeleniumUtils.webDriver.findElements(By.xpath(contact_name));
			 //Account name
			 String account_name = MyListMain.MYLISTPROP.getProperty("Ml_1_4_id_table_row");
			 account_name= account_name.replace("id?",listId);
			 account_name = account_name.replace("?", Integer.toString(j+1));
			 List<WebElement> rowAcId = SeleniumUtils.webDriver.findElements(By.xpath(account_name));
			 String contact="";
			 for(int k=0;k<rowConId.size();k++){
				 String con_names=rowConId.get(k).getText();
				 con_name.add(con_names);
	             contact = contact+rowConId.get(k).getText()+" ("+rowAcId.get(k).getText()+")|";
	         }
	        if(contact.length()!=0){
	          contact = contact.substring(0, contact.length() - 1);
	        }
	        values.replace(ActivityMain.ACTIVITYPROP.getProperty("AI_1_v_contacts"), contact); 
	     }catch (Exception e) {
	        
	    }
		 return con_name;
	}
	/**
	 * Preview and edit button verify(without click any draft)
	 * @param btn_type
	 * @return
	 */
	public static Boolean prview_edit_btn_verify(String btn_type){
		//Edit draft
		Boolean Valid=false;
		SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_4_id_draft_edit_button").replace("btn_type", btn_type),"xpath");
		String toastMsg = SeleniumUtils.getToastMessage();
		if(!toastMsg.isEmpty()){
			Valid=true;
		}
		//toast close
        SeleniumUtils.ClickOnItems(MyListMain.MYLISTPROP.getProperty("ML_1_1_id_toastmsg_close"),"xpath");
        return Valid;
	}
	/**
	 * Get current data for this format
	 * @return
	 */
	public static String getCurrDate()  {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
          return dateFormat.format(new Date());
    }
	/**
	 * Select the DropDown List end with condition
	 * @param searchInput
	 * @param inputValue
	 * @param byVal
	 * @return
	 */
	public static boolean dropDownInputTokenizeEndwith(String searchInput,String inputValue,String byVal){
		SeleniumUtils.setValueToField(searchInput, inputValue, byVal);
        String elementSelctor= searchInput.replace("/li/input","/following-sibling::ul/li"); //get input dropdown list
        boolean status =false;
        try{	
	         List<WebElement> dropDown = SeleniumUtils.webDriver.findElements(By.xpath(elementSelctor));         
	         String firstValue = dropDown.get(0).getText().trim();
	         if(!firstValue.contains("Record")){
	           for (WebElement list : dropDown) {
	             String result = list.getText().toLowerCase().trim();
	             if(result.endsWith(inputValue.toLowerCase().trim())){
	            	list.click();
	            	status =true;
	            	break;
	             }
	           }
	         }
	         else{
	    	   System.out.println(inputValue+": Record does not exist");
	         }
	         
        }catch (Exception e) {
			 e.printStackTrace();
		}    
      return status;  
    }
}
