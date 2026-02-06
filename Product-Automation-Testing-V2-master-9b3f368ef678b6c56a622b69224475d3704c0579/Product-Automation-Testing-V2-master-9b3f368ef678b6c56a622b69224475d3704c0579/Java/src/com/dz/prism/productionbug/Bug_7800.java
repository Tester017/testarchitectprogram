package com.dz.prism.productionbug;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.module.accountcreation.AccountCreationMain;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_7800 {
	public static void verify_Account_Coverage() {
		SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_7800 Employee Duplicate verification");
		try {
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_DashBoardModule"), "xpath");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_DashBoardPage"), "xpath");
			//To select the account displayed
			Thread.sleep(3000);
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_top_clients_By_comm_tab"), "xpath");
			List<WebElement> HeadersList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_TableHeaders")));
			 WebElement AccNameHeadPos = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_AccountNamePosition")));
			 int AccNameIndex = HeadersList.indexOf(AccNameHeadPos);
			List<WebElement> accountList = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_top_clientLists_By_comm_displayed_in_table").replace("temp", ""+(AccNameIndex+1) +"")));
			for (int i = 0; i < accountList.size(); i++) {
				String accName = accountList.get(i).getText();
				accountList.get(i).click();
				//click on coverage tab
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_Coverage_tab"), "xpath");
				
				if (!(SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_NoData"), "xpath")).contains("No Data Available")) {
					//List of email displayed
					int emailIdListCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_EmailId"))).size();
					int nameListCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_PersonName"))).size();
					//System.out.println("email count--->"+emailIdListCount);
					//System.out.println("Name count-->"+nameListCount);
					List<String> emailList =new ArrayList<String>();
					
					for (int j = 1; j <= emailIdListCount; j++) {	
					
					String EmailId = SeleniumUtils.getTextfromField("(" + ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_EmailId") + ")[" + j + "]", "xpath");
					//System.out.println("email--->"+EmailId);
					emailList.add(EmailId);
					String Name = SeleniumUtils.getTextfromField("(" + ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_PersonName") + ")[" + j + "]", "xpath");
					//System.out.println("name-->"+Name);
					}
					//System.out.println("emailList---->"+emailList);
					
					//To find the duplicate coverage
					Map<String, Integer> m=new LinkedHashMap<String,Integer>();
					for (String x:emailList) {
						if(m.containsKey(x)) {
							Integer v = m.get(x);
							m.put(x, v+1);
							//System.out.println("Duplicate--->"+x);
							SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel(accName+"-Coverage-Employee mail id-"+ x +" is displaying multiple times",ExtentColor.RED));
							SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_WebElements"),1));
							String text = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_CoverageValues"), "xpath");
							//System.out.println("Table---->"+text);
							if (text.contains(x)) {
								String EmpName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_EmpName").replace("temp", "" + x +""), "xpath");
								//System.out.println("Employee name--->"+EmpName);
								account_Coverage_Table(EmpName);	
							}							
						}else {
							m.put(x, 1);
							SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel(accName+"-Coverage-Employee mail id-"+ x +" is not displaying multiple times",ExtentColor.GREEN));
							SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_WebElements"),0));
						}
						
					}
					//System.out.println("Map values----->"+m);
					//SeleniumUtils.testCase.log(Status.PASS,MarkupHelper.createLabel("Employee email count",ExtentColor.BROWN));
					
				} else {
					//System.out.println("No table");
					SeleniumUtils.parentTest.log(Status.SKIP,MarkupHelper.createLabel(accName+" this Account doesn't have Employee Coverage",ExtentColor.TEAL));
				}
				SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_close_button_1"), "xpath");
			}
			
			
}catch (Exception e) {
	e.printStackTrace();
}

	}
	public static void account_Coverage_Table(String empName) throws Exception {
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_BurgerEditButton"), "xpath");
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_EditButton"), "xpath");
		
		//To find the account type
		int accountCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_AccountCount"))).size();
		//System.out.println(accountCount);
		for (int i = 1; i <=accountCount ; i++) {
		boolean isSelected = SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_AccountTypeSel").replace("temp","" + i + ""))).isSelected();
		if (isSelected==true) {
			String AccountType = SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_AccType").replace("temp","" + i + ""), "id", "xpath");
			//System.out.println("Acc Type--->"+AccountType);
			
			//Click on account coverage tab
			SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_AccountCovTab").replace("temp",""+ AccountType+""), "xpath");
			SeleniumUtils.scrollUntilElementView(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_AccCovTable").replace("temp",""+ AccountType+""), "id");
			SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_TableValues").replace("temp",""+ AccountType+""), "xpath");
			//To get the table values
			String TableValues = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_TableValues").replace("temp",""+ AccountType+""), "xpath");
			if (TableValues.contains(empName)) {
				int empCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_EmpCount").replace("temp",""+ AccountType+"" ).replace("name", ""+empName+""))).size();
				//System.out.println("Employee count--->"+empCount);
				for (int j = 1; j <=empCount ; j++) {
					String Status = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_EmpStatus").replace("temp", ""+ AccountType+"").replace("name", "" + empName +"").replace("index", "" + j + ""), "xpath");
					//To get the status of Employee coverage
					System.out.println("Duplicate Account "+empName+" Status is--->"+Status);
				}
				
			}
			
		}
		}
		
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_7800_1_id_EditCloseButton"), "xpath");
	}
	
}
