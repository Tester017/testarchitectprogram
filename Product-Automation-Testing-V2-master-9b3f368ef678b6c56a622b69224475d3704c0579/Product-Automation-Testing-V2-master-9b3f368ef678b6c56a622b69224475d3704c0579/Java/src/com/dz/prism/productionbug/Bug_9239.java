package com.dz.prism.productionbug;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.utils.SeleniumUtils;

public class Bug_9239 {
	static List<String>CN,FN,Typ,ShareHeld,ShareChange,ValueHeld,ValueChange,Aum,FillDate,City,State,Country,OutStandPer,PortFolio;
	static Set<String> s,f;
public static void ReadExcel() throws Exception {
    	
        Map<String, List<Map<String, String>>> testdat = SeleniumUtils.readExcelData(
                SeleniumUtils.UserDirVar + ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_v_test_case_path"));
        for (Entry<String, List<Map<String, String>>> testRows : testdat.entrySet()) {
            List<Map<String, String>> innerRows = testRows.getValue();
            for (Map<String, String> values : innerRows) {
            	ticker_Fund_Table_Data_Verification(values);
            	
            }
        }
    }

	public static void ticker_Fund_Table_Data_Verification(Map<String, String> values) {
		try {
		SeleniumUtils.parentTest = SeleniumUtils.testCase.createNode("Bug_9239-Ticker Tear Sheet-->Fund Holding data verification");
		//To get the ticker name from excel
		String TickerName = values.get(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_v_Ticker_Name"));
		//Thread.sleep(2000);
		SeleniumUtils.waitUntilElementHide("loading_screen", "id");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_globalSearch"), "id");
		//Set the ticker name to the global search
		SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_globalSearch"), TickerName, "id");
		int searchListFilterCount = SeleniumUtils.getCountOfDropdownList(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_FilterCountList"), "xpath");
        //deselecting selected filter
		for(int i=1;i<=searchListFilterCount ;i++){
            boolean isSelected = SeleniumUtils.checkBoxIsSelect(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_FilterSelectedCheck").replace("temp","" + i + ""), "xpath");
            if(isSelected==true){
            	SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_FilterDeselect").replace("temp","" + i + ""), "xpath");
                SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_FilterDeselect").replace("temp","" + i + ""),"xpath");
            }
        }
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TickerCheakBox"), "xpath");
		//Click on ticker check box
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TickerCheakBox"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_SearchList"), "xpath");
		//Ticker search list
		String textfromField = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_SearchList"), "xpath");
		if(!textfromField.equalsIgnoreCase("No results found for '"+TickerName+"'")) {
		//Click on reqyired ticker
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TickerSelect").replace("temp", "" + TickerName + ""), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_FundHoldingsTab"), "xpath");
		//click on fund holdings tab
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_FundHoldingsTab"), "xpath");
		SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableValue"), "xpath");
		Thread.sleep(3000);
		//Get the table value
		String TableValue = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableValue"), "xpath");
		List<WebElement> AllHeader = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_AllHeader")));
		//To get Manage co index position
		int ManageCoIndex = AllHeader.indexOf(SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_ManagementPosition"))));
		//To get fund name index position
		int FundNameIndex = AllHeader.indexOf(SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_FundsNamePosition"))));
		//To get Type index position
		int TypeIndex = AllHeader.indexOf(SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TypePosition"))));
		//To get share held index position
		int ShareHeldIndex = AllHeader.indexOf(SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_SharesHeldPosition"))));
		//To get sharechange index position
		int ShareChangeIndex = AllHeader.indexOf(SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_SharesChangedPosition"))));
		//To get value held index position
		int ValueHeldIndex = AllHeader.indexOf(SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_ValueHeldPosition"))));
		//To get value changed index position
		int ValueChangedIndex = AllHeader.indexOf(SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_ValueChangedPosition"))));
		//To get Aum index position
		int AumIndex = AllHeader.indexOf(SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_AumPosition"))));
		//To get fill date index position
		int FillDateIndex = AllHeader.indexOf(SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_FillingDatePosition"))));
		//To get city index position
		int CityIndex = AllHeader.indexOf(SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_CityPosition"))));
		//To get state index position
		int StateIndex = AllHeader.indexOf(SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_StatePosition"))));
		//To get country index position
		int CountryIndex = AllHeader.indexOf(SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_CountryPosition"))));
		//To get the OutstandingPer index position
		int OutstandingPerIndex = AllHeader.indexOf(SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_OutstandingSharePrcntPosition"))));
		//To get PortFolioPer index position
		int PortFolioIndex = AllHeader.indexOf(SeleniumUtils.webDriver.findElement(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_PortFolioPrcntPosition"))));
		
		if(!TableValue.equalsIgnoreCase("No data available in table")) {
			int size = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDataCount").replace("temp", "" + (ManageCoIndex+1) + ""))).size();
			Set<String>ManagementName=new LinkedHashSet<String>();
			for (int i = 1; i <= size; i++) {
				String manageCoName = SeleniumUtils.getTextfromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDatas").replace("temp", ""+ i +"").replace("index", "" + (ManageCoIndex+1) + ""), "xpath");
				ManagementName.add(manageCoName);
			}
			List<String> MngName=new ArrayList<String>();
			MngName.addAll(ManagementName);
			for (int i = 0; i < MngName.size(); i++) {
				String name = MngName.get(i);
				SeleniumUtils.setValueToField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableFilterSearch"), name, "xpath");
				SeleniumUtils.setTimeoutUntilVisibility(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableValue"), "xpath");
				Thread.sleep(1500);
				int RowCount = SeleniumUtils.webDriver.findElements(By.xpath(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDataCount").replace("temp", "" + (ManageCoIndex+1) + ""))).size();
				if (RowCount!=1) {
					CN=new ArrayList<String>();
					FN=new ArrayList<String>();
					Typ=new ArrayList<String>();
					ShareChange=new ArrayList<String>();
					ShareHeld=new ArrayList<String>();
					ValueHeld=new ArrayList<String>();
					ValueChange=new ArrayList<String>();
					FillDate=new ArrayList<String>();
					Aum=new ArrayList<String>();
					City=new ArrayList<String>();
					State=new ArrayList<String>();
					Country=new ArrayList<String>();
					OutStandPer=new ArrayList<String>();
					PortFolio=new ArrayList<String>();
				for (int j = 1; j <= RowCount; j++) {
					//To get Management Co name
					CN.add(SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDatas").replace("temp", ""+ j +"").replace("index", "" + (ManageCoIndex+1) + ""),"title", "xpath"));
					//To get Funds name
					FN.add(SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDatas").replace("temp", ""+ j +"").replace("index", "" + (FundNameIndex+1) + ""),"title", "xpath"));
					//To get type name
					Typ.add(SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDatas").replace("temp", ""+ j +"").replace("index", "" + (TypeIndex+1) + ""),"title", "xpath"));
					//To get shareheld
					ShareHeld.add(SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDatas").replace("temp", ""+ j +"").replace("index", "" + (ShareHeldIndex+1) + ""),"title", "xpath"));
					//To get Share change
					ShareChange.add(SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDatas").replace("temp", ""+ j +"").replace("index", "" + (ShareChangeIndex+1) + ""),"title", "xpath"));
					//To get value held
					ValueHeld.add(SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDatas").replace("temp", ""+ j +"").replace("index", "" + (ValueHeldIndex+1) + ""),"title", "xpath"));
					//To get value change
					ValueChange.add(SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDatas").replace("temp", ""+ j +"").replace("index", "" + (ValueChangedIndex+1) + ""),"title", "xpath"));
					//To get Aum
					Aum.add(SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDatas").replace("temp", ""+ j +"").replace("index", "" + (AumIndex+1) + ""),"title", "xpath"));
					//To get the fill date
					FillDate.add(SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDatas").replace("temp", ""+ j +"").replace("index", "" + (FillDateIndex+1) + ""),"title", "xpath"));
					//To get the city
					City.add(SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDatas").replace("temp", ""+ j +"").replace("index", "" + (CityIndex+1) + ""),"title", "xpath"));
					//To get the state
					State.add(SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDatas").replace("temp", ""+ j +"").replace("index", "" + (StateIndex+1) + ""),"title", "xpath"));
					//To get the country
					Country.add(SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDatas").replace("temp", ""+ j +"").replace("index", "" + (CountryIndex+1) + ""),"title", "xpath"));
					//To get the OutstandPer
					OutStandPer.add(SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDatas").replace("temp", ""+ j +"").replace("index", "" + (OutstandingPerIndex+1) + ""),"title", "xpath"));
					//To get PortFolio
					PortFolio.add(SeleniumUtils.getAttributefromField(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableDatas").replace("temp", ""+ j +"").replace("index", "" + (PortFolioIndex+1) + ""),"title", "xpath"));
				
				} s=new LinkedHashSet<String>();
				f=new LinkedHashSet<String>();
				for (int g = 0; g < CN.size(); g++) {
					for (int j = 0; j < CN.size(); j++) {
						if (g==j) {
							continue;
						} else if(g!=j && CN.get(g).equals(CN.get(j)) && FN.get(g).equals(FN.get(j)) && Typ.get(g).equals(Typ.get(j)) && ShareHeld.get(g).equals(ShareHeld.get(j)) && ShareChange.get(g).equals(ShareChange.get(j)) && ValueHeld.get(g).equals(ValueHeld.get(j)) && ValueChange.get(g).equals(ValueChange.get(j)) && Aum.get(g).equals(Aum.get(j)) && FillDate.get(g).equals(FillDate.get(j)) && City.get(g).equals(City.get(j)) && State.get(g).equals(State.get(j)) && Country.get(g).equals(Country.get(j)) && OutStandPer.get(g).equals(OutStandPer.get(j)) && PortFolio.get(g).equals(PortFolio.get(j))){
							//System.out.println(CN.get(g)+"&"+CN.get(j)+"Fail "+"Row--"+g+"&"+j);
							SeleniumUtils.parentTest.log(Status.FAIL,MarkupHelper.createLabel("The Management Co - "+CN.get(g)+" -Fund name - "+FN.get(g)+" in row"+g+" has the duplicate row data in the table-with row"+j,ExtentColor.RED));
							SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_WebElements"),1));
						}
						else {
							//System.out.println(CN.get(g)+"&"+CN.get(j)+"Pass"+"Row--"+g+"&"+j);
							s.add(CN.get(j));
							f.add(FN.get(j));
						}
					}
				}if(!s.isEmpty()) {
				SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Management Co List- doesn't have the duplicate row data in the table for "+name+" - Management co name-"+s+" -Fund name- "+f,ExtentColor.GREEN));
				SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_WebElements"),0));
				}
				}else {
					//System.out.println("Only one Row");
					SeleniumUtils.parentTest.log(Status.PASS,MarkupHelper.createLabel("The Management Co List- doesn't have the duplicate row data in the table for Management co - "+name,ExtentColor.GREEN));
					SeleniumUtils.parentTest.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(ProductionBugMain.moduleName,ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_WebElements"),0));
				}Thread.sleep(1500);
				//To clear the Search filter
				SeleniumUtils.ClearFieldValue(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TableFilterSearch"), "xpath");
				
			}
			
		}else {
			System.out.println("No fund Holdings data");
		}
		SeleniumUtils.ClickOnItems(ProductionBugMain.PRODUCTIONBUGPROP.getProperty("Bug_9239_1_id_TickerCloseButton"), "xpath");
		}else {
			System.out.println("No Ticker found");
		}
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
