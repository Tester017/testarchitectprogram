package com.dz.prism.productionbug;

import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ddf.EscherColorRef.SysIndexSource;

import com.dz.prism.main.AutomationDriver;
import com.dz.prism.utils.SeleniumUtils;

public class ProductionBugMain {
	public static Properties PRODUCTIONBUGPROP = null;
	public static final String moduleName ="ProductionBugMain";
	
	static {
		try {
			PRODUCTIONBUGPROP = SeleniumUtils.getConfigProprty("\\ModuleConfigurations\\productionbug.properties");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public ProductionBugMain() {
		if(PRODUCTIONBUGPROP!=null) {
			SeleniumUtils.testCase=  SeleniumUtils.extendReports.createTest("Production Bugs");
			callTestCases();
		}else {
			System.out.println("production Bug properties Not loaded please verify");
		}
	}
	
	public void callTestCases() {
		try {
			/*if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				SID_72.check_ytd_Dropdown_List();
				System.out.println("SID_72 - Account tearsheet - NaN Verification in Date Filter");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				SID_87.activity();
				System.out.println("SID_87 - Interaction-Activity type Comparison");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				SID_55.verify_Holdings_And_Fund(PRODUCTIONBUGPROP);
				System.out.println("SID_55 - Holdings and fund verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_8100.Previous_YTD_Verification();
				System.out.println("Bug_8100 YTD vs.Prev.Year and Previous full year value verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_8360.verification_Of_Service_Account(PRODUCTIONBUGPROP);
				System.out.println("Bug_8360 Verification of Service Account Displayed in Add Contact Page");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				SID_79.contact_History(PRODUCTIONBUGPROP);
				System.out.println("SID_79 Contact History Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				SID_62.interaction_History(PRODUCTIONBUGPROP);
				System.out.println("SID_62 Interaction History Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Production_Bug1_7391.ReadExcel();
				System.out.println("7391-Research Email Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_7800.verify_Account_Coverage();
				System.out.println("Bug_7800 Employee Duplicate verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_7783.coverage_Name_Verification();
				System.out.println("Bug_7783 Employee Coverage Name Format Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_7950.ticker_Black_Screen_Verification();
				System.out.println("Bug_7950 Research sales person name trades and action ticker background colour Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_8019_and_9247.revenue_By_Person_Filter_Verification();
				System.out.println("Bug_8019 and 9247 Revenue Module table verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_8062.verify_Service_contact(PRODUCTIONBUGPROP);
				System.out.println("Bug_8062 Event Request verification for Service Contact");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_8045_and_10076.verify_Duplicate_Contact(PRODUCTIONBUGPROP);
				System.out.println("Bug_8045 Event Request Duplicate contact verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_8043.verify_Contact_With_Singlequotes(PRODUCTIONBUGPROP);
				System.out.println("Bug_8043 Event Request verification for Contact With Single Quotes");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_8074.time_Popup_Verification();
				System.out.println("Bug_8074 Time Popup Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_8075.clock_View_Verification();
				System.out.println("Bug_8075 clock view Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {	
				Bug_8759.verification_Of_Revenue_YTD_Custom_Range(PRODUCTIONBUGPROP);
				System.out.println("Bug_8759 Verification of Revenue By Product and person in YTD custom range");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_8616.analyst_Coverage_Name_Verification();
				System.out.println("Bug_8616 Verification of Primany Analyst Name Displayed in Ticker Tear sheet Page");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_8422.verify_Event_Creation(PRODUCTIONBUGPROP);
				System.out.println("Bug_8422 Event verification in CRS Report page");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_8490.sales_Traders_Account_Verification();
				System.out.println("Bug_8490 Verifying Traders and sales person List displaying correctly");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				EventManagerFillPercentage.fill_Rate_Percentage_Verification();
				System.out.println("Event Manager fill percentage verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_7785.research_Readership_Verification();
				System.out.println("Bug_7785 Verification of Research ReaderShip datas");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_8751.account_TearSheet_Verification();
				System.out.println("Bug_8751 Verification of Account Opening its Own Tear Sheet format");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_9057.ReadExcel();
				System.out.println("Bug_9057-Contact Tear Sheet-->InActive Account Verification in- Move to and Employment change- Account Search ");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_9149.revenue_Account_Gross_Comm_Verification();
				System.out.println("Bug_9149-Revenue Account Gross Commission Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_8978.verify_Ticker_And_Account_TableData();
				System.out.println("Bug_8978-Ticker and Account table data verification for position traders");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_9239.ReadExcel();
				System.out.println("Bug_9239-Ticker Tear Sheet-->Fund Holding data verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_9259.analyst_Coverage_Verification();
				System.out.println("Bug_9259 Verification of Account Coverage table has Status Value");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_9656.revenue_By_Account_Verification();
				System.out.println("Bug_9656 Revenue by Account table verification for Equity manager");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_9820.readership_Hits_Count_Verification();
				System.out.println("Bug_9820 Readership Hits Count Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_9772.ReadExcel();
				System.out.println("Bug_9772 Account Coverage status value verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_9996.ReadExcel();
				System.out.println("Bug_9996 '&' symbol name search verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_9840.ReadExcel();
				System.out.println("Bug_9840 German Phone Number Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_10173.verify_Duplicate_Selector(PRODUCTIONBUGPROP);
				System.out.println("Bug_10173 Duplicate Selector Investor verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_10096.verify_Duplicate_Openrequest(PRODUCTIONBUGPROP);
				System.out.println("Bug_10096 Duplicate Open Request verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_10234.ReadExcel();
				System.out.println("Bug_10234 InActive Account verification In Contact Creation");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_10272.meeting_Verification(PRODUCTIONBUGPROP);
				System.out.println("Bug_10272 Deleted Meeting Displaying in Calender Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_10101_and_10194.research_Library_Datas_Verification();
				System.out.println("Bug_10101_and_10194 Research Library Table Datas Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_10354.Read_Excel(PRODUCTIONBUGPROP);
				System.out.println("Bug_10354 Email Subject Name Search for '&' symbol Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_10066.Read_Excel(PRODUCTIONBUGPROP);
				System.out.println("Bug_10066 Research Sales Tearsheet Commission Tiles Visibility Verification");
			}*/
			/*if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_9720.contact_Tearsheet_Title_Verification();
				System.out.println("Bug_9720 Contact Tear Sheet Title Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_10554.Read_Excel(PRODUCTIONBUGPROP);
				System.out.println("Bug_10554 Email Subject Name Search for Single quotes Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_10576.revenue_Module_TableDatas_Verification();
				System.out.println("Bug_10576 Revenue By Product and Revenue By Account (2019)Year Filter Range Datas Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_10575.product_Duplicates_Verify_In_Rev_By_Product_Table();
				System.out.println("Bug_10575 Verification of Product Duplicates In Revenue By Product Table");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_10827.my_List_Page_Refresh_Verification();
				System.out.println("Page Refresh Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_9378.ReadExcel();
				System.out.println("Account Coverage Percentage value verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_7691.Read_Excel(PRODUCTIONBUGPROP);
				System.out.println("Bug_7691 Keyword Search for Analyst category Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_7611.Read_Excel(PRODUCTIONBUGPROP);
				System.out.println("Bug_7611 Account search in Contant move Verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_6685.Read_Excel(PRODUCTIONBUGPROP);
				System.out.println("Bug_6685 Page Load check for Contact Tear Sheet");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_10957.Read_Excel(PRODUCTIONBUGPROP);
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_11064.ReadExcel();
				System.out.println("Bug_11064 Account Coverage Buisness line and Product verification");
			}
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_6471.research_Readership_Data_Verification();
				System.out.println("Bug_6471 Readership data Verification");
			}*/
			if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_6379.commission_BreakUp_And_Month_Over_comparison_Verification();
				System.out.println("Bug_6379 commission_BreakUp_And_Month_Over_comparison_Verification");
			}
			/*if (AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()) {
				Bug_7798.research_Subcription();
				System.out.println("Bug_7798 Research Subcription contact verification");
			}*/
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
