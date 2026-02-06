package com.dz.prism.test.ROI;

import org.testng.annotations.Test;

import com.dz.core.common.utils.Roles;
import com.dz.core.common.utils.SkipIf;
import com.dz.prism.annonation.Annotation;
import com.dz.prism.api.function.ROIAPIFunc;
import com.dz.prism.constant.ProductConstant;
import com.dz.prism.function.MyCoverageFunctions;
import com.dz.prism.function.ROIFunctions;
import com.dz.prism.module.config.RoiConfig;
import com.dz.prism.page.ROI.ROIPage;
import com.dz.prism.page.dashboard.DashBoardHomePage;
import com.dz.prism.page.dashboard.TabSelectionPage;

public class ROITest extends Annotation{

	ROIFunctions roiFunctions=new ROIFunctions();

	@Test(groups={"run","Smoke","author=Hussain","Version = V5" } ,description = "Verify ROI Module ")
	public void checkROITable() {
		ROIPage roiPage = new ROIPage();
		TabSelectionPage.gotoROIModule();
		createReportNode("verify ROI Table");
		if (RoiConfig.IS_ROI_Table_Btn) {
			roiPage.isDataDisplayedInROITable();
		}

		createReportNode("verify ROI Chart Page");
		if (RoiConfig.IS_ROI_Chart_Btn) {
			if (roiPage.clickChartIcon()) {

				if (RoiConfig.IS_ROI_Over_Table) {
					roiPage.isDataDisplayedInROIChartIcon_OverTable();
				}
				if (RoiConfig.IS_ROI_Under_Table) {
					roiPage.isDataDisplayedInROIChartIcon_UnderTable();
				}
				if (RoiConfig.IS_ROI_Ideal_Table) {
					roiPage.isDataDisplayedInROIChartIcon_IdealTable();
				} 
			}
		}

		createReportNode("verify ROI Count Page");
		if (RoiConfig.IS_ROI_Count_Btn) {
			if (roiPage.clickCountIcon()) {
				roiPage.isDataDisplayedInROICountChart();
			}
		}

		createReportNode("verify ROI Weights PopUp Page");
		if (RoiConfig.IS_ROI_PopUp_Btn) {
			if (roiPage.clickRoiPopupIcon()) {
				roiPage.isDataDisplayedInROIWeightsPopUpPageTable();
			}
		}
	}
	
	@Test(description = "ROI Table - Interaction Hyperlink", groups = { "All", "authors=Hussain", "jira = REN-1152",
	"Version =v5.5.1 - Bug" })
	public void checkInteractionHyperlink() {
		ROIAPIFunc roiAPIFunc = new ROIAPIFunc();
		TabSelectionPage.gotoROIModule();
		roiAPIFunc.checkInteractionCountWithInteractionPopUp();
	}

	@SkipIf(condition = ProductConstant.condition, applicableRoles = { Roles.Sales_Person,Roles.Sales_Assistant,Roles.Sales_Person_Team,Roles.Sales_Trader_Gross,Roles.Sales_Trader})
	@Test(description = "ROI - Check ROI V1, ROI V2 module is enabled for All Sales Roles after map the ROI Dashboard in admin console", groups = { "All", "authors=Salmankhan", "jira = PST-21388",
	"Version = v6.1" })
	public void checkROIModulesisDispInAllSalesRoles() {		
		roiFunctions.checkROIV1andROIV2EnabledInAllSalesRoles();
	}
	
	@SkipIf(condition = ProductConstant.condition, applicableRoles = { Roles.Sales_Person,Roles.Sales_Assistant,Roles.Sales_Person_Team,Roles.Sales_Trader_Gross,Roles.Sales_Trader})
	@Test(description = "Check sales role covered accounts only displayed in ROI V1,V2 modules", groups = { "All", "authors=Salmankhan", "jira = PST-21388",
	"Version = v6.1" })
	public void checkOnlySalesRoleCoveredAccDispInROIModules() {
		if(ProductConstant.role.equals(Roles.Sales_Person.name())){
			roiFunctions.checkSalesPersonRoleCoveredAccOnlyDispInROIV1V2Table();
		}else if(ProductConstant.role.equals(Roles.Sales_Assistant.name())) {
			roiFunctions.checkSalesAssistantRoleCoveredAccOnlyDispInROIV1V2Table();
		}else if(ProductConstant.role.equals(Roles.Sales_Person_Team.name())) {
			roiFunctions.checkSalesPersonTeamRoleCoveredAccOnlyDispInROIV1V2Table();
		}else if(ProductConstant.role.equals(Roles.Sales_Trader_Gross.name())) {
			roiFunctions.checkSalesTraderGrossRoleCoveredAccOnlyDispInROIV1V2Table();
		}else {
			roiFunctions.checkSalesTraderRoleCoveredAccOnlyDispInROIV1V2Table();
		}
	}
	
	@SkipIf(condition = ProductConstant.condition, applicableRoles = { Roles.Sales_Person,Roles.Sales_Assistant,Roles.Sales_Person_Team,Roles.Sales_Trader_Gross,Roles.Sales_Trader})
	@Test(description = "Inactive sales role covered account and check the Inactived employee client name is displayed in ROI V1,V2 modules", groups = { "All", "authors=Salmankhan", "jira = PST-21388",
	"Version = v6.1" })
	public void inActiveandCheckCoveredAccVisibilityInROIModules() {
		if(ProductConstant.role.equals(Roles.Sales_Person.name())){
			roiFunctions.checkInActiveSalesPersonRoleCoveredAccOnlyDispInROIV1V2Table();
		}else if(ProductConstant.role.equals(Roles.Sales_Assistant.name())) {
			roiFunctions.checkInActiveSalesAssistantRoleCoveredAccOnlyDispInROIV1V2Table();
		}else if(ProductConstant.role.equals(Roles.Sales_Person_Team.name())) {
			roiFunctions.checkInActiveSalesPersonTeamRoleCoveredAccOnlyDispInROIV1V2Table();
		}else if(ProductConstant.role.equals(Roles.Sales_Trader_Gross.name())) {
			roiFunctions.checkInActiveSalesTraderGrossRoleCoveredAccOnlyDispInROIV1V2Table();
		}else {
			roiFunctions.checkInActiveSalesTraderRoleCoveredAccOnlyDispInROIV1V2Table();
		}
	}
	
	@SkipIf(condition = ProductConstant.condition, applicableRoles = { Roles.Sales_Person,Roles.Sales_Assistant,Roles.Sales_Person_Team,Roles.Sales_Trader_Gross,Roles.Sales_Trader})
	@Test(description = "Check consolidated super account toggle is displayed in All Sales Roles of ROI V1,V2 modules", groups = { "All", "authors=Salmankhan", "jira = PST-21388",
	"Version = v6.1" })
	public void checkConsolidatedSuperAccToggleDispInROIModulesforAllSales() {
		roiFunctions.checkConsolidatedSuperAccToggleDispInROIModulesforAllSales();		
	}
	
	@SkipIf(condition = ProductConstant.condition, applicableRoles = { Roles.Sales_Person,Roles.Sales_Assistant,Roles.Sales_Person_Team,Roles.Sales_Trader_Gross,Roles.Sales_Trader})
	@Test(description = "Check specific filter types alone is displayed in ROI V1,V2 modules chart & count page filters", groups = { "All", "authors=Salmankhan", "jira = PST-21388",
	"Version = v6.1" })
	public void checkFilterTypesInROIModulesforAllSales() {
		roiFunctions.checkFilterTypesInROIModulesChartCountPage();		
	}
	
	@SkipIf(condition = ProductConstant.condition, applicableRoles = { Roles.Equity_Head, Roles.Sales_Assistant,Roles.Sales_Person_Team,Roles.Sales_Trader})
	@Test(description = "Add Sales role employees as a covered person in equity roi accounts which has interaction count and check the account name with same interaction count is updated on Sales role roi modules", groups = { "All", "authors=Salmankhan", "jira = PST-21388",
	"Version = v6.1" })
	public void checkIntrCountInAllSales() {
		if(envProperty.get("ROLES").toString().contains(Roles.Sales_Assistant.name())) {
			roiFunctions.checkIntrCountInSalesAssistant();
		}
		if(envProperty.get("ROLES").toString().contains(Roles.Sales_Person_Team.name())) {
			roiFunctions.checkIntrCountInSalesPersonTeam();
		}
		if(envProperty.get("ROLES").toString().contains(Roles.Sales_Trader.name())) {
			roiFunctions.checkIntrCountInSalesTrader();
		}		
	}	
	
	@SkipIf(condition = ProductConstant.condition, applicableRoles = {  Roles.Sales_Person, Roles.Sales_Assistant,Roles.Sales_Person_Team,Roles.Sales_Trader_Gross,Roles.Sales_Trader})
	@Test(description = "Check filter types(By Client Type, By Region, By Size & By Tier) of both chart and count page is working properly for all sales roles in both ROI V1,V2 modules", groups = { "All", "authors=Salmankhan", "jira = PST-21388",
	"Version = v6.1" })
	public void checkFiltersOfChartCountPageAllSales() {
		roiFunctions.checkClientTypeFiltrInChartCountPageROIModulesAllSales();
	}
	
	@SkipIf(condition = ProductConstant.condition, applicableRoles = {  Roles.Sales_Person, Roles.Sales_Assistant,Roles.Sales_Person_Team,Roles.Sales_Trader_Gross,Roles.Sales_Trader})
	@Test(description = "Check YTD Commission for covered account in sales roles ROI tables with respective tearsheet revenue tab", groups = { "All", "authors=Salmankhan", "jira = PST-21388",
	"Version = v6.1" })
	public void checkYTDCommissionInROIAllSales() {
		roiFunctions.checkYTDCommissionROIModulesAllSales();
	}
	
	@SkipIf(condition = ProductConstant.condition, applicableRoles = {  Roles.Equity_Head,Roles.Sales_Person, Roles.Sales_Assistant,Roles.Sales_Person_Team,Roles.Sales_Trader_Gross,Roles.Sales_Trader})
	@Test(description = "Add coverage from coverage management module from equity role for non covered sales accounts and check the coverage added accounts is displayed in ROI tables", groups = { "All", "authors=Salmankhan", "jira = PST-21388",
	"Version = v6.1" })
	public void checkAddedCovDispInROITables() {	
		roiFunctions.addVerifyCoverageFromCovManagementEquityToSales();
	}
	
	@SkipIf(condition = ProductConstant.condition, applicableRoles = { Roles.Equity_Head,Roles.Sales_Person, Roles.Sales_Assistant,Roles.Sales_Person_Team})
	@Test(description = "Request coverage from mycoverage module from sales roles non covered account and check the coverage added accounts is displayed in ROI tables", groups = { "All", "authors=Salmankhan", "jira = PST-21388",
	"Version = v6.1" })
	public void checkReqCovAccDispInROITables() {	
		MyCoverageFunctions myCoverageFunctions = new MyCoverageFunctions();
		myCoverageFunctions.reqCovForSalesPersonMyCoverageVerifyInROI();
	}
}
