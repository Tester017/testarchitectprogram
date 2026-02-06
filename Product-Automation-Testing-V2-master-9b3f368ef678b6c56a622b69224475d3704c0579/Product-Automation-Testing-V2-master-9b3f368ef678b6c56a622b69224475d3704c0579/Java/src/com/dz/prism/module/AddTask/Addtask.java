package com.dz.prism.module.AddTask;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.dz.prism.module.ExistingEvent.EventTearsheetMain;
import com.dz.prism.utils.SeleniumUtils;
import com.dz.prism.utils.TableUtils;

public class Addtask {
	static List<String> AddTask_Tabledata = new ArrayList<String>(); 
	static List<String> AddTask_Formdetails = new ArrayList<String>(); 
	static String Assign_Name;
	static String Due_Date;
	static String Due_Time;
	static String Type;
	static String Status;
	static String Subject;
	public static void DashBoardpage() throws InterruptedException, IOException {
		report();
	}
	public static void report() throws IOException, InterruptedException {
		//report
		try{
			SeleniumUtils.testCase= SeleniumUtils.extendReports.createTest(AddTaskMain.Add_Task_PROP.getProperty("Prism_AddTask") +"Task Added:");
			//SeleniumUtils.testCase.log(Status.INFO,MarkupHelper.createLabel(SeleniumUtils.createDownloadButton(SeleniumUtils.UserDirVar+ AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_testCasePath")),ExtentColor.TRANSPARENT));
			SeleniumUtils.parentTest =  SeleniumUtils.testCase.createNode("Task Created On The Given Excel Row Id");
			Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(SeleniumUtils.UserDirVar+AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_testCasePath"));
			for(Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()){
				List<Map<String, String>> innerRows = testRows.getValue();
				for(Map<String,String> Xlvalue : innerRows){
					Addtask_Widget(Xlvalue);
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void Addtask_Widget(Map<String, String>Xlvalue) throws InterruptedException, IOException {

		//Task_Widget
		SeleniumUtils.scrollUntilElementView(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Task_Heading"),"xpath");
		SeleniumUtils.setTimeoutUntilVisibility(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Task_Add_Btn"),"xpath");
		SeleniumUtils.ClickOnItems(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Task_Add_Btn"),"xpath");
		SeleniumUtils.setTimeoutUntilVisibility(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Assgine_Name"),"xpath");
		//Assgine Name
		Thread.sleep(3000);
		System.out.println(Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_AssigneName").trim()));
		SeleniumUtils.setValueToField(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Assgine_Name"),Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_AssigneName").trim()),"xpath");
		SeleniumUtils.ClickOnItems(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Assgine_Dropdown_Click"),"xpath");
		//Due Date
		Thread.sleep(3000);
		String DueDate = Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_DueDate"));
		System.out.println("oooo"+DueDate);
		SeleniumUtils.addDatePickerValue(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_DueDate"), DueDate,"id");
		Thread.sleep(3000);
		//Due Time
		System.out.println("====>"+Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_DueTime")));
		SeleniumUtils.setValueToField(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_DueTime"), Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_DueTime")), "xpath");
		//SeleniumUtils.setValueToField(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_DueTime"),Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Time").trim()),"xpath");
		//Task Type
		SeleniumUtils.ClickOnItems(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Tasktype"),"xpath");
		//Task Status
		SeleniumUtils.ClickOnItems(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Task_Status"),"xpath");
		//Subject
		SeleniumUtils.setValueToField(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Subject"),Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Subject").trim()),"xpath");
		//Contacts Selection
		SeleniumUtils.setValueToField(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Contacts"),Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Contacts").trim()),"xpath");
		SeleniumUtils.ClickOnItems(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Contacts_Dropdown_Click"),"xpath");
		//AnotherContacts Selection
		SeleniumUtils.setValueToField(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Contacts"),Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Contacts1").trim()),"xpath");
		SeleniumUtils.ClickOnItems(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Contacts_Dropdown_Click"),"xpath");
		//Description
		SeleniumUtils.setValueToField(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Description"),Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Description").trim()),"xpath");
		//Save Button
		SeleniumUtils.ClickOnItems(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Task_Save_Btn"),"xpath");
		Verify_TaskDetails(Xlvalue);
		Verify_TaskTableDetails(Xlvalue);
		Excelsheet_TaskDetails(Xlvalue);
		Expanded_Addtask_Table(Xlvalue);

	}
	public static void Verify_TaskDetails(Map<String, String>Xlvalue) throws InterruptedException, IOException {

		Thread.sleep(3000);
		SeleniumUtils.setTimeoutUntilVisibility(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_BurgerBtn"),"xpath");
		SeleniumUtils.ClickOnItems(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_BurgerBtn"),"xpath");
		Thread.sleep(3000);
		SeleniumUtils.setTimeoutUntilVisibility(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Task_EditIcon"),"xpath");
		SeleniumUtils.ClickOnItems(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Task_EditIcon"),"xpath");
		Thread.sleep(2000);
		SeleniumUtils.setTimeoutUntilVisibility(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Assgine_Name"),"xpath");
		//Verify Assgine Name
		String GetAssgine_Name=SeleniumUtils.getTextfromField(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_SelAssgine_Name"),"xpath");
		System.out.println("Name-->"+GetAssgine_Name);
		String[] splitName=GetAssgine_Name.split(Pattern.quote("("));
		Assign_Name= splitName[0].trim();
		System.out.println("Assign_Name1"+Assign_Name);
		if(Assign_Name.equalsIgnoreCase(Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_AssigneName")))){
			System.out.println("==pass");
			//SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Assign_Name verifed "+Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Row")),ExtentColor.GREEN));
		}
		else{
			//SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Assign_Name not Verifed_"+Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Row")),ExtentColor.RED));
		}
		//Verify Due_Date
		Thread.sleep(2000);
		Due_Date=SeleniumUtils.getAttributefromField(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_SelDueDate"),"value","xpath");
		System.out.println("Date-->"+Due_Date);
		if(Due_Date.equalsIgnoreCase(Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_DueDate")))){

			System.out.println("{{pass");
			//SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Assign_Name verifed "+Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Row")),ExtentColor.GREEN));
		}
		else{
			//SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Assign_Name not Verifed_"+Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Row")),ExtentColor.RED));
		}
		//Verify Due_Time
		Due_Time=SeleniumUtils.getAttributefromField(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_DueTime"),"value","xpath");
		System.out.println("Ti-->"+Due_Time);
		String ContacateDateandTime=Due_Date +" "+Due_Time.trim();
		System.out.println("ContacateDateandTime"+ContacateDateandTime);
		if(Due_Time.equalsIgnoreCase(Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_DueTime")))){
			System.out.println("====+pass");
			//SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Assign_Name verifed "+Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Row")),ExtentColor.GREEN));
		}
		else{
			//SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Assign_Name not Verifed_"+Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Row")),ExtentColor.RED));
		}
		//Verify Type
		Type=SeleniumUtils.getSelectedDropdownValue(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_SelectedTasktype"),"xpath");
		System.out.println("type-->"+Type);
		//Verify Status
		Status=SeleniumUtils.getSelectedDropdownValue(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Selected_TaskStatus"),"xpath");
		System.out.println("Status-->"+Status);
		//Verify Subject
		Subject=SeleniumUtils.getAttributefromField(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Subject"),"value","xpath");
		System.out.println("Sub-->"+Subject);
		if(Subject.equalsIgnoreCase(Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Subject")))){
			System.out.println("}}}pass");
			//SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Assign_Name verifed "+Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Row")),ExtentColor.GREEN));
		}
		else{
			//SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Assign_Name not Verifed_"+Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Row")),ExtentColor.RED));
		}
		AddTask_Formdetails.add(Assign_Name);
		AddTask_Formdetails.add(Type);
		AddTask_Formdetails.add(Status);
		AddTask_Formdetails.add(ContacateDateandTime);
		System.out.println("FormDetails--->"+AddTask_Formdetails);

		Thread.sleep(2000);
		SeleniumUtils.scrollUntilElementView(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Taskpage_crossClose"),"xpath");
		SeleniumUtils.ClickOnItems(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Taskpage_crossClose"),"xpath");
		SeleniumUtils.setTimeoutUntilVisibility(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Confirm_Close"),"xpath");
		SeleniumUtils.ClickOnItems(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Confirm_Close"),"xpath");
	}
	public static void Verify_TaskTableDetails(Map<String, String>Xlvalue) throws InterruptedException, IOException {
		SeleniumUtils.scrollUntilElementView(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Task_Heading"),"xpath");
		try {
			Thread.sleep(2000);
			if(SeleniumUtils.checkElementDisplayedProp(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Task_verifyTable"),"xpath")){
				SeleniumUtils.setTimeOut(2);
				JSONArray Tabledata=TableUtils.getAllTableDataAsJSON("(//table[@class='table task-table no-margin'])[1]");
				if(Tabledata!=null && Tabledata.size()>0) {
					for(int i = 0; i < Tabledata.size() ; i++) {
						JSONObject tablerow = (JSONObject) Tabledata.get(i);
						System.out.println("===>"+tablerow);
						AddTask_Tabledata.add(tablerow.get("Assignee").toString().trim());
						AddTask_Tabledata.add(tablerow.get("Type").toString().trim());
						AddTask_Tabledata.add(tablerow.get("Status").toString().trim());
						AddTask_Tabledata.add(tablerow.get("Due Date/Time").toString().trim());
					}
					System.out.println("dataaassss1111++++"+AddTask_Tabledata);
				}
				else {
					//SeleniumUtils.parentTest.log(Status.Fail,MarkupHelper.createLabel("No data available in the AddedTasktable",ExtentColor.RED));
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void Excelsheet_TaskDetails(Map<String, String>Xlvalue) throws InterruptedException, IOException {

		if(CollectionUtils.isEqualCollection(AddTask_Tabledata, AddTask_Formdetails)) {
			System.out.println("pass");
			//SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Assign_Name verifed "+Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Row")),ExtentColor.GREEN));

		}
		else {
			System.out.println("fail");
			//SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("Assign_Name not Verifed_"+Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Row")),ExtentColor.RED));
		}
		//		String Assignename=Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_AssigneName"));
		//		String Timeval=Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_DueTime"));
		//		String Status=Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty(""));
		//		String Dateval=Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_DueDate"));
	}
	public static void Expanded_Addtask_Table(Map<String, String>Xlvalue) throws InterruptedException, IOException {

		//Task_Widget
		SeleniumUtils.scrollUntilElementView(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Task_Heading"),"xpath");
		SeleniumUtils.setTimeoutUntilVisibility(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Task_Add_Btn"),"xpath");
		SeleniumUtils.ClickOnItems(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Task_Add_Btn"),"xpath");
		SeleniumUtils.setTimeoutUntilVisibility(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Assgine_Name"),"xpath");
		//SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("Add Button Clicked and Task Page opened Successfully "+Xlvalue.get(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_v_Row")),ExtentColor.GREEN));
		//page_Close		
		Thread.sleep(2000);
		SeleniumUtils.scrollUntilElementView(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Taskpage_crossClose"),"xpath");
		SeleniumUtils.ClickOnItems(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Taskpage_crossClose"),"xpath");
		SeleniumUtils.setTimeoutUntilVisibility(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Confirm_Close"),"xpath");
		SeleniumUtils.ClickOnItems(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Confirm_Close"),"xpath");
		Thread.sleep(3000);
		SeleniumUtils.setTimeoutUntilVisibility(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_TaskExpand_Btn"),"xpath");
		SeleniumUtils.ClickOnItems(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_TaskExpand_Btn"),"xpath");
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		Thread.sleep(4000);
		
		//WebElements
		
		WebElement taskname =SeleniumUtils.webDriver.findElement(By.xpath(AddTaskMain.Add_Task_PROP.getProperty("")));
		WebElement acc_name =SeleniumUtils.webDriver.findElement(By.xpath(AddTaskMain.Add_Task_PROP.getProperty("")));
		WebElement contac_name =SeleniumUtils.webDriver.findElement(By.xpath(AddTaskMain.Add_Task_PROP.getProperty("")));
		
		
		
		//Header
		List<WebElement>TaskTableHeader=SeleniumUtils.webDriver.findElements(By.xpath(AddTaskMain.Add_Task_PROP.getProperty("A_T_1_id_Task_tableHeader")));
		int TaskName=TaskTableHeader.indexOf(taskname);
		int Accoun_Name=TaskTableHeader.indexOf(acc_name);
		int Con_Name=TaskTableHeader.indexOf(contac_name);
		
		
		
		
		JSONArray TableHeaders=TableUtils.getAllTableDataAsJSON("//div[@class='dataTables_scrollHeadInner']//table[@class='compact task-detail-table table-striped table-bordered theme-table table data-ellipsis custDataTable nowrap dataTable no-footer']//thead//th");
		System.out.println("TableHeaders"+TableHeaders);
		if(TableHeaders!=null && TableHeaders.size()>0) {
			for(int i = 0; i < TableHeaders.size() ; i++) {
				JSONObject Headers = (JSONObject) TableHeaders.get(i);
				System.out.println("===>"+Headers);
			}
		}
	
	
	}

}

