package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import annotation.Annotations;
import bo.PeriodFilterBo;
import common.utils.Reporter;
import constant.ProductConstant;
import pages.revenueAdvance.RegionSummary;
import pages.revenueAdvance.RegionSummaryPopupPage;
import pages.revenueAdvance.RevenueByAccount;
import pages.revenueAdvance.RevenueByResearchSales;
import pages.revenueAdvance.RevenueBySalseTrader;

public class RevenueAdvance extends Annotations {
	
	RevenueByAccount revByAcc = new RevenueByAccount();
	RevenueBySalseTrader salesTrader = new RevenueBySalseTrader();
	RevenueByResearchSales revByResearchSales=new RevenueByResearchSales();
	RegionSummary regionsummary = new RegionSummary();
	RegionSummaryPopupPage regionSummaryPopupPage = new RegionSummaryPopupPage();
	
	/**
	 * user period as custom | ytd ,prev year
	 * if use custom must give start and end date
	 * @return
	 */
	@DataProvider(name = "period" ,parallel = false )
	public Object[] periodFilter() {
		List<PeriodFilterBo> periodList  = new LinkedList<>();
		
        try {
        	 JsonParser parser = new JsonParser();
        	 JsonArray periodFilterArray = (JsonArray) parser.parse(new FileReader("./src/test/resources/EnvironmentConfig/"+ProductConstant.environmentName+"/RevenuePeriodFilterJson.json"));
        	 for(int i=0;i<periodFilterArray.size();i++) {
        		 JsonObject filter = periodFilterArray.get(i).getAsJsonObject();
        		 PeriodFilterBo period = new PeriodFilterBo();
        		 period.setConds(getObjectValueAsString(filter, "conds"));
        		 period.setPeriod(getObjectValueAsString(filter, "period"));
        		 period.setYear(getObjectValueAsString(filter, "year") );
        		 period.setStartdate(getObjectValueAsString(filter, "startdate") );
        		 period.setEnddate(getObjectValueAsString(filter, "enddate") );
        		 period.setType(getObjectValueAsString(filter, "type"));
        		 periodList.add(period);
        	 }
        	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	 
		return periodList.toArray();
		
	}
	
	
	
	/**
	 * user period as  ytd ,prev year
	 * @return
	 */
	@DataProvider(name = "accperiod" ,parallel = false )
	public Object[] accperiodFilter() {
		List<PeriodFilterBo> periodList  = new LinkedList<>();
		
        try {
        	 JsonParser parser = new JsonParser();
        	 JsonArray periodFilterArray = (JsonArray) parser.parse(new FileReader("./src/test/resources/EnvironmentConfig/"+ProductConstant.environmentName+"/AccountTearSheetPeriodFilterJson2.json"));
        	 for(int i=0;i<periodFilterArray.size();i++) {
        		 JsonObject filter = periodFilterArray.get(i).getAsJsonObject();
        		 PeriodFilterBo period = new PeriodFilterBo();
        		 period.setConds(getObjectValueAsString(filter, "conds"));
        		 period.setPeriod(getObjectValueAsString(filter, "period"));
        		 period.setYear(getObjectValueAsString(filter, "year") );
        		 period.setStartdate(getObjectValueAsString(filter, "startdate") );
        		 period.setEnddate(getObjectValueAsString(filter, "enddate") );
        		 period.setType(getObjectValueAsString(filter, "type"));
        		 periodList.add(period);
        	 }
        	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	 
		return periodList.toArray();
		
	}
	
	
	
	
	//===============================================================
	//Revenue By Account tab
	
	@Test(dataProvider ="period", groups= {"author=bhuvanes", "jira=PST-7979"}, description="Revenue by Account - Header sum - validation")
	public void RevenuByAccountHearderSum(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revByAcc.calculateRevByAccHeaderSum(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=bhuvanes", "jira=PST-7979"}, description="Revenue by Account -Table - validation")
	public void RevenuByAccountTableCalculation(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revByAcc.calculateRevByAccTableData(period);
	}
	
	
	@Test(dataProvider ="period", groups= {"author=bhuvanes", "jira=PST-7979"}, description="Revenue by Account -TableValue - validation in Acc tear sheet")
	public void RevenuByAccountVerifyValueInAccountTearsheet(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revByAcc.VerifyValueInAccountTearsheet(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=bhuvanes", "jira=PST-7979"}, description="Revenue by Account -TableValue - validation in Acc tear sheet")
	public void RevenuByAccountVerifyCoverage(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revByAcc.verifyCoverage(period);
	}
	
	
	//=========================================================================
	//Revenue by Sales Trader tab
	
	@Test(dataProvider ="period")
	public void RevenuBySalesTraderCalculateHeaderSum(PeriodFilterBo period) {
		salesTrader.calculateRevBySalesTraderHeader(period);
	}
	
	@Test(dataProvider ="period")
	public void RevenuBySalesTraderTableDataCalculation(PeriodFilterBo period) {
		salesTrader.calculateRevBySalesTraderTableData(period);
	}
	
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Total Account Comm - Growth , Growth %")
	public void RevenuBySalesTraderTotalAccCommCalculation(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString() , "info");
		salesTrader.calculateRevBySalesTraderTotalAccComm(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Resource Consumption - #Val. Actions column")
	public void RevenuBySalesTraderResourceConsumption(PeriodFilterBo period) {

		salesTrader.calculateRevBySalesTraderResourceComm(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Overall Sum value's Growth and Growth % of column Trader Total Conmission")
	public void RevBySalesTraderHeaderGowthValue(PeriodFilterBo period) {

		salesTrader.calculateRevBySalesTraderHeaderGowthValue(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Overall Sum value's Growth and Growth % of column Total Account Conmission")
	public void RevBySalesTraderAccCommHeaderGowthValue(PeriodFilterBo period) {

		salesTrader.calculateRevBySalesTraderAccCommHeaderGowthValue(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Overall Sum value of column Val Actions")
	public void RevBySalesTraderValActionHeaderValue(PeriodFilterBo period) {

		salesTrader.calculateRevBySalesTraderValActionHeaderValue(period);
	}

	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data calculation in Accts Covered Popup")
	public void RevBySalesTraderAccountCoveredPopup(PeriodFilterBo period) {

		salesTrader.calcRevBySalesTraderAccCoverPopupDelta(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Accts Covered's column Popup with Dashboard - Sales Trader Accts Covered Popup")
	public void RevBySalesTraderAcctCoveredWithDashboard(PeriodFilterBo period) {

		salesTrader.checkDashboardAcctsCoveredWithRevBySalesTrader(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data calculation in Non Split Accts Popup")
	public void RevBySalesTraderNonSplitAcctPopup(PeriodFilterBo period) {

		salesTrader.calcRevBySalesTraderNonSplitAcctsPopupDelta(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Non Split Accts's column Popup with Dashboard - Sales Trader Accts Covered Popup")
	public void RevBySalesTraderNonSplitAcctPopupWithDashboard(PeriodFilterBo period) {

		salesTrader.checkRevBySalesTraderNonSplitAcctsWithDashboard(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Non Split Accts's column Popup with Account Tear Sheet - Mclagan Widget")
	public void RevBySalesTraderNonSplitAcctPopupWithMclaganWidget(PeriodFilterBo period) {

		salesTrader.checkNonSplitAcctsPopupWithATSMclaganWidget(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Non Split Accts's column Popup with Account Tear Sheet - Revenue Tab")
	public void RevBySalesTraderNonSplitAcctPopupWithRevenueTab(PeriodFilterBo period) {

		salesTrader.checkNonSplitAcctsPopupWithATSRevenueTab(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Non Split Accts's column Popup with Account Tear Sheet - Mclagan Tab")
	public void RevBySalesTraderNonSplitAcctPopupWithMclaganTab(PeriodFilterBo period) {

		salesTrader.checkNonSplitAcctsPopupWithATSMclaganTab(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Non Split Accts's column Popup with Account Tear Sheet - Coverage Tab")
	public void RevBySalesTraderNonSplitAcctPopupWithCoverageTab(PeriodFilterBo period) {

		salesTrader.checkNonSplitAcctsPopupWithATSCoverageTab(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data calculation in Split Accts Popup")
	public void RevBySalesTraderSplitAcctPopup(PeriodFilterBo period) {

		salesTrader.calcRevBySalesTraderSplitAcctsPopupDelta(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Split Accts's column Popup with Dashboard - Sales Trader Accts Covered Popup")
	public void RevBySalesTraderSplitAcctPopupWithDashboard(PeriodFilterBo period) {

		salesTrader.checkRevBySalesTraderSplitAcctsWithDashboard(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Split Accts's column Popup with Account Tear Sheet - Mclagan Widget")
	public void RevBySalesTraderSplitAcctPopupWithMclaganWidget(PeriodFilterBo period) {

		salesTrader.checkSplitAcctsPopupWithATSMclaganWidget(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Split Accts's column Popup with Account Tear Sheet - Revenue Tab")
	public void RevBySalesTraderSplitAcctPopupWithRevenueTab(PeriodFilterBo period) {

		salesTrader.checkSplitAcctsPopupWithATSRevenueTab(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Split Accts's column Popup with Account Tear Sheet - Mclagan Tab")
	public void RevBySalesTraderSplitAcctPopupWithMclaganTab(PeriodFilterBo period) {

		salesTrader.checkSplitAcctsPopupWithATSMclaganTab(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Split Accts's column Popup with Account Tear Sheet - Coverage Tab")
	public void RevBySalesTraderSplitAcctPopupWithCoverageTab(PeriodFilterBo period) {

		salesTrader.checkSplitAcctsPopupWithATSCoverageTab(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Accts's Covered column Popup with Account Tear Sheet - Mclagan Widget")
	public void RevBySalesTraderAcctsCovPopupWithMclaganWidget(PeriodFilterBo period) {

		salesTrader.checkAcctsCoveredPopupWithATSMclaganWidget(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Accts's Covered column Popup with Account Tear Sheet - Revenue Tab")
	public void RevBySalesTraderAcctsCovPopupWithRevenueTab(PeriodFilterBo period) {

		salesTrader.checkAcctsCoveredPopupWithATSRevenueTab(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Accts's Covered column Popup with Account Tear Sheet - Mclagan Tab")
	public void RevBySalesTraderAcctsCovPopupWithMclaganTab(PeriodFilterBo period) {

		salesTrader.checkAcctsCoveredPopupWithATSMclaganTab(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Accts's Covered column Popup with Account Tear Sheet - Coverage Tab")
	public void RevBySalesTraderAcctsCovPopupWithCoverageTab(PeriodFilterBo period) {

		salesTrader.checkAcctsCoveredPopupWithATSCoverageTab(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data calculation in Accts below threshold Popup")
	public void RevBySalesTraderAcctsBelowThrsPopup(PeriodFilterBo period) {

		salesTrader.calcRevBySalesTraderBelowThrsPopupDelta(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Accts below threshold's column Popup with Dashboard - Sales Trader Accts Covered Popup")
	public void RevBySalesTraderAcctsBelowThrsPopupWithDashboard(PeriodFilterBo period) {

		salesTrader.checkRevBySalesTraderBelowThrsWithDashboard(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Accts below threshold column Popup with Account Tear Sheet - Mclagan Widget")
	public void RevBySalesTraderAcctsBelowThrsPopupWithMclaganWidget(PeriodFilterBo period) {

		salesTrader.checkBelowThrsPopupWithATSMclaganWidget(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Accts below threshold column Popup with Account Tear Sheet - Revenue Tab")
	public void RevBySalesTraderAcctsBelowThrsPopupWithRevenueTab(PeriodFilterBo period) {

		salesTrader.checkBelowThrsPopupWithATSRevenueTab(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Accts below threshold column Popup with Account Tear Sheet - Mclagan Tab")
	public void RevBySalesTraderAcctsBelowThrsPopupWithMclaganTab(PeriodFilterBo period) {

		salesTrader.checkBelowThrsPopupWithATSMclaganTab(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of Accts below threshold column Popup with Account Tear Sheet - Coverage Tab")
	public void RevBySalesTraderAcctsBelowThrsPopupWithCoverageTab(PeriodFilterBo period) {

		salesTrader.checkBelowThrsPopupWithATSCoverageTab(period);
	}
	
	//====================================================================
	
	//Revenue by Research Sales Tab

	@Test(dataProvider ="period")
	public void RevenuByResearchSalesTableCalculateHeaderSum(PeriodFilterBo period) {
		reportStep("Period : "  + period.toString(),"info");
		revByResearchSales.calculateRevByResearchSalesHeader(period);
	}
	
	
	@Test(dataProvider ="period")
	public void RevenuByResearchSalesTableCalculateTableData(PeriodFilterBo period) {
		reportStep("Period : "  + period.toString(),"info");
		revByResearchSales.calculateRevByResearchSalesTableData(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerAcctsBelowThrsPopup(PeriodFilterBo period) {
		
		revByResearchSales.calcRevByResearchSalesBelowThrsPopupDelta(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerAcctsBelowThrsPopupWithDashboard(PeriodFilterBo period) {
		
		revByResearchSales.checkRevByResearchSalesBelowThrsWithDashboard(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerAcctsBelowThrsPopupWithMclaganWidget(PeriodFilterBo period) {
		
		revByResearchSales.checkBelowThrsPopupWithATSMclaganWidget(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerAcctsBelowThrsPopupWithRevenueTab(PeriodFilterBo period) {
		
		revByResearchSales.checkBelowThrsPopupWithATSRevenueTab(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerAcctsBelowThrsPopupWithMclaganTab(PeriodFilterBo period) {
		
		revByResearchSales.checkBelowThrsPopupWithATSMclaganTab(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerAcctsBelowThrsPopupWithCoverageTab(PeriodFilterBo period) {
		
		revByResearchSales.checkBelowThrsPopupWithATSCoverageTab(period);
	}
	
	//
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerNonSplitAcctPopup(PeriodFilterBo period) {
		
		revByResearchSales.calcRevByResearchSalesNonSplitAcctsPopupDelta(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerNonSplitAcctPopupWithDashboard(PeriodFilterBo period) {
		
		revByResearchSales.checkRevByResearchSalesNonSplitAcctsWithDashboard(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerNonSplitAcctPopupWithMclaganWidget(PeriodFilterBo period) {
		
		revByResearchSales.checkNonSplitAcctsPopupWithATSMclaganWidget(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerNonSplitAcctPopupWithRevenueTab(PeriodFilterBo period) {
		
		revByResearchSales.checkNonSplitAcctsPopupWithATSRevenueTab(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerNonSplitAcctPopupWithMclaganTab(PeriodFilterBo period) {
		
		revByResearchSales.checkNonSplitAcctsPopupWithATSMclaganTab(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerNonSplitAcctPopupWithCoverageTab(PeriodFilterBo period) {
		
		revByResearchSales.checkNonSplitAcctsPopupWithATSCoverageTab(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerSplitAcctPopup(PeriodFilterBo period) {
		
		revByResearchSales.calcRevByResearchSalesSplitAcctsPopupDelta(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerSplitAcctPopupWithDashboard(PeriodFilterBo period) {
		
		revByResearchSales.checkRevByResearchSalesSplitAcctsWithDashboard(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerSplitAcctPopupWithMclaganWidget(PeriodFilterBo period) {
		
		revByResearchSales.checkSplitAcctsPopupWithATSMclaganWidget(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerSplitAcctPopupWithRevenueTab(PeriodFilterBo period) {
		
		revByResearchSales.checkSplitAcctsPopupWithATSRevenueTab(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerSplitAcctPopupWithMclaganTab(PeriodFilterBo period) {
		
		revByResearchSales.checkSplitAcctsPopupWithATSMclaganTab(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerSplitAcctPopupWithCoverageTab(PeriodFilterBo period) {
		
		revByResearchSales.checkSplitAcctsPopupWithATSCoverageTab(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerAcctsCoveredPopup(PeriodFilterBo period) {
		
		revByResearchSales.calcRevByResearchSalesAcctsCoveredPopupDelta(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerAcctsCoveredPopupWithDashboard(PeriodFilterBo period) {
		
		revByResearchSales.checkRevByResearchSalesAcctsCoveredWithDashboard(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerAcctsCoveredPopupWithMclaganWidget(PeriodFilterBo period) {
		
		revByResearchSales.checkAcctsCoveredPopupWithATSMclaganWidget(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerAcctsCoveredPopupWithRevenueTab(PeriodFilterBo period) {
		
		revByResearchSales.checkAcctsCoveredPopupWithATSRevenueTab(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerAcctsCoveredPopupWithMclaganTab(PeriodFilterBo period) {
		
		revByResearchSales.checkAcctsCoveredPopupWithATSMclaganTab(period);
	}
	
	@Test(dataProvider ="period")
	public void RevByResearchSalesPerAcctsCoveredPopupWithCoverageTab(PeriodFilterBo period) {
		
		revByResearchSales.checkAcctsCoveredPopupWithATSCoverageTab(period);
	}
	
	//====================================================================
	
	//Region Summary tab account_person
	
	@Test(dataProvider ="period")
	public void RegionSummaryCalculateTableData(PeriodFilterBo period) {
		reportStep("Period : "  + period.toString(),"info");
		regionsummary.checkALLTableData(period);
		 
	}
	
	@Test(dataProvider ="period")
	public void RegionSummaryCalculateRegionWiseTableData(PeriodFilterBo period) {
		reportStep("Period : "  + period.toString(),"info");
		regionsummary.verifySubRegionTotalTableData(period);
	}
	
	@Test(dataProvider ="accperiod")
	public void RegionSummaryCalculateRegionInAccTearSheet(PeriodFilterBo period) {
		reportStep("Period : "  + period.toString(),"info");
		regionsummary.verifyAllRegionPopUpTableData(period);
	}
		
	
	@Test(dataProvider ="accperiod")
	public void RegionSummaryCalculateSubRegionInAccTearSheet(PeriodFilterBo period) {
		reportStep("Period : "  + period.toString(),"info");
		regionsummary.verifySubRegionsPopUpTableData(period);
	}
		
	
	
	@Test(dataProvider ="period")
	public void RegionSummaryPopupDataInATSMclaganWidget(PeriodFilterBo period) {
		reportStep("Period : "  + period.toString(),"info");
		regionSummaryPopupPage.checkMarkShareAndRank(period);
	}
	
	@Test(dataProvider ="period")
	public void RegionSummaryPopupColVisibility(PeriodFilterBo period) {
		reportStep("Period : "  + period.toString(),"info");
		regionSummaryPopupPage.checkRegionwiseColVisibilityHeader(period);
	}
	
	@Test(dataProvider ="period")
	public void RegionSummaryPopupYTD(PeriodFilterBo period) {
		reportStep("Period : "  + period.toString(),"info");
		regionSummaryPopupPage.checkYTDWithATSRevTab(period);
	}
	
	//Sales Trader
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Accts Covered Count Value")
	public void RevBySalesTraderAcctsCoveredCalc(PeriodFilterBo period) {

		salesTrader.calculateRevBySalesTraderAcctsCoveredValue(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Val Actions Count Value")
	public void RevBySalesTraderValActionsCalc(PeriodFilterBo period) {

		salesTrader.calculateRevBySalesTraderValActionValue(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Accts Covered Count Value")
	public void RevByResSalesAcctsCoveredCalc(PeriodFilterBo period) {

		revByResearchSales.calculateRevByResearchSalesAcctsCoveredValue(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Val Actions Count Value")
	public void RevByResSalesValActionsCalc(PeriodFilterBo period) {

		revByResearchSales.calculateRevByResearchSalesValActionValue(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Val Actions Count Value")
	public void RevByAccountValActionsCalc(PeriodFilterBo period) {

		revByAcc.calculateRevByAccountValActionValue(period);
	}
	
}
