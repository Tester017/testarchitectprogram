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
import pages.revenue.RevenueByAccount;
import pages.revenue.RevenueByPerson;
import pages.revenue.RevenueByRegion;
import pages.revenue.RevenueByTeam;
 

public class Revenue extends Annotations {
	 
	RevenueByAccount revenueByAcc = new RevenueByAccount();
	RevenueByPerson revenueByPerson = new RevenueByPerson();
	RevenueByTeam revenueByTeam = new RevenueByTeam();
	RevenueByRegion revenueByRegion=new RevenueByRegion();
	
	
	/**
	 * user period as custom | ytd ,prev year
	 * if use custom must give start and end date
	 * @return
	 */
	@DataProvider(name = "period" ,parallel = true )
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
	
	 
	@Test(dataProvider ="period", groups= {"author=Bhuvanes", "jira=pst-id"}, description="Revenue by account Header sum validation")
	public void calculateRevenueByAccountHeaderSum(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString() , "info");
		revenueByAcc.CalcateRevenuByAccHeaderSum(period);
		System.out.println("test");
	}
	
	//Revenue By Person tab
	@Test(dataProvider ="period",groups= {"author=Bhuvanes", "jira=pst-id"}, description="revenue By Person")
	public void revenueByPerson(PeriodFilterBo period) {
		Reporter.reportStep("Period : "+period.toString() , "info");
		revenueByPerson.calcateRevenuByPersonHeaderSum(period);
	}
	
	
	
	@Test(dataProvider ="period",groups= {"author=Bhuvanes", "jira=pst-id"}, description="revenue By Person revenueByPersonTableValueCalculation")
	public void revenueByPersonTableValueCalculation(PeriodFilterBo period) {
		Reporter.reportStep("Period : "+period.toString() , "info");
		revenueByPerson.calcualateRevenueByPersonTableData(period);
	}
	
	
	
	//----------------------
	
	//Revenue By Team tab
	@Test(groups= {"author=Bhuvanes", "jira=pst-id"}, description=" calculate Revenue By Team Header Sum")
	public void revenueByTeam() {
		revenueByTeam.calculateRevenueByTeamHeaderSum();
	}
	
	@Test(groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Team Gross Comm validation")
	public void revenueByTeamGrossCommYTD() {

		revenueByTeam.verifyGrossCommOfProductsYTD();
	}
	
	@Test(groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Team Gross Comm validation")
	public void revenueByTeamGrossCommPrevYear() {

		revenueByTeam.verifyGrossCommOfProductsPrevYear();
	}
	
	
	@Test(groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Team Gross Comm Of Products validation")
	public void revenueByTeamGrossCommOfProductsYTD() {

		revenueByTeam.verifyGrossCommOfProductsYTD();
	}
	
	
	@Test(groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Team Gross Comm Of Products validation")
	public void revenueByTeamGrossCommOfProductsPrevYear() {

		revenueByTeam.verifyGrossCommOfProductsPrevYear();
	}
	
	@Test(groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Team -> Team Hyperink Pop Up-> Header Sum validation")
	public void revenueByTeamPopUpSumYTD() {

		revenueByTeam.calculateRevenueByTeamPopUpHeaderSumYTD();
	}
	
	@Test(groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Team -> Team Hyperink Pop Up-> Header Sum validation")
	public void revenueByTeamPopUpSumPrevYear() {

		revenueByTeam.calculateRevenueByTeamPopUpHeaderSumPrevYear();
	}
	
	 
	//----------------------
	
	//Revenue By Research Sales tab
	
	@Test(dataProvider ="period",groups= {"author=Bhuvanes", "jira=pst-id"}, description="revenue By Research Sales Table Value Calculation")
	public void revenueByResearchSalesTableValueCalculation(PeriodFilterBo period) {
		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByPerson.calcualateRevenueByPersonTableData(period);
	}
	
	
	//----------------------
	
	//Revenue By Account tab 
	
	@Test(dataProvider ="period",groups= {"author=Bhuvanes", "jira=pst-id"}, description="revenue By Account Verify In AccTearsheet")
	public void revenueByAccountVerifyInAccTearsheet(PeriodFilterBo period) {
		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.VerifyValueInAccountTearsheet(period);
	}
	
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account Gross Comm validation Inclusive of CSA")
	public void revenueByAccountCalcTotalCommInclusiveOfCSA(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.verifyAccountTotalCommissionInclusiveOfCSA(period);
	}
	
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account Gross Comm validation Exclusive of CSA")
	public void revenueByAccountCalcTotalCommExclusiveOfCSA(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.verifyAccountTotalCommissionExclusiveOfCSA(period);
	}

	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account Net Comm validation")
	public void revenueByAccountCalcNetComm(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.verifyNetCommValues(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=lakshmi", "jira=pst-id"}, description="Revenue by Account YOY validation")
	public void revenueByAccountYOYcomparison(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.verifyYOY_Comparison(period);
	}
	
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account Annualized Commission validation")
	public void revenueByAccountCalcAnnComm(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.verifyAnnualCommValues(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account #Val.Actions validation")
	public void revenueByAccountCalcValActions(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.verifyValactions(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account NetCommission validation")
	public void revenueByAccountCalcNetCommission(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.verifyNetCommissionCalc(period);
	}
	
	@Test(dataProvider ="period",groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account CSA validation")
	public void revenueByAccountCalcCSA(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.verifyCSA(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account Loss Ratio validation")
	public void revenueByAccountCalcLossRatio(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.verifyLossRatio(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account Goal Comparioson validation")
	public void revenueByAccountGoalComparison(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.verifyGoalComparison(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account - Coverage - validation")
	public void revenueByAccount_Coverage(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.verifyCoverage(period);
	}
	
	
	
	//====================================================================================
	
	//Revenue - Revenue By Account - Consolidated Super Accounts -> Toggle
	
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account - Consolidated Super Accounts -> Toggle - Gross Comm validation Inclusive of CSA")
	public void revByAccSuperAccTotalCommInclusiveOfCSA(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.revByAccSuperAccTotalCommissionInclusiveOfCSA(period);
	}
	
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account - Consolidated Super Accounts -> Toggle - Gross Comm validation Exclusive of CSA")
	public void revByAccSuperAccTotalCommExclusiveOfCSA(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.revByAccSuperAccTotalCommissionExclusiveOfCSA(period);
	}
	
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account - Consolidated Super Accounts -> Toggle - Net Comm validation")
	public void revByAccSuperAccCalcNetComm(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.revByAccSuperAccNetCommValues(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account  - Consolidated Super Accounts -> Toggle - YOY validation")
	public void revByAccSuperAccYOYcomparison(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.revByAccSuperAccYOYComparison(period);
	}
	
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account - Consolidated Super Accounts -> Toggle - Annualized Commission validation")
	public void revByAccSuperAccCalcAnnComm(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.revByAccSuperAccAnnualCommValues(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account - Consolidated Super Accounts -> Toggle - #Val.Actions validation")
	public void revByAccSuperAccCalcValActions(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.revByAccSuperAccValactions(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account  - Consolidated Super Accounts -> Toggle - NetCommission validation")
	public void revByAccSuperAccCalcNetCommission(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.revByAccSuperAccNetCommissionCalc(period);
	}
	
	@Test(dataProvider ="period",groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account - Consolidated Super Accounts -> Toggle - CSA validation")
	public void revByAccSuperAccCalcCSA(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.revByAccSuperAccCSA(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account - Consolidated Super Accounts -> Toggle - Loss Ratio validation")
	public void revByAccSuperAccCalcLossRatio(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.revByAccSuperAccLossRatio(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account - Consolidated Super Accounts -> Toggle - Goal Comparioson validation")
	public void revByAccSuperAccGoalComparison(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.revByAcSuperAccGoalComparison(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=lakshmi", "jira=pst-id"}, description="Revenue by Account - Consolidated Super Accounts -> Toggle - Coverage - validation")
	public void revByAccSuperAccCoverage(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.revByAccSuperAccCoverage(period);
	}
	
	
	@Test(dataProvider ="period", groups= {"author=Lakshmi", "jira=pst-id"}, description="Revenue by Account - Consolidated Super Accounts -> Toggle - Header - validation")
	public void revByAccSuperAccHeaderComparison(PeriodFilterBo period) {

		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.accSupeAccHeaderComparison(period);
	}
	
	
	//========================================================================
	
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Val Actions Count Value")
	public void RevByAccountValActionsCalc(PeriodFilterBo period) {
		Reporter.reportStep("Period : "+period.toString(), "info");
		revenueByAcc.calculateRevByAccountValActionValue(period);
	}
	
	//Revenue by Region Tab====================================================
	@Test(dataProvider ="period", groups= {"author=Hussain", "jira=pst-id"}, description="RevByRegion Tabl validation - verifyRevByRegNamesWithDashCommChrt")
	public void verifyRevByRegNamesWithDashCommChrt(PeriodFilterBo period) {
		Reporter.createReportNode("Verify : Rev by Region Names with DashBoard Rev by egion Names Period : "+period.getPeriod());
		revenueByRegion.verifyRevByRegNamesWithDashCommChrt(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Hussain", "jira=pst-id"}, description="RevByRegion Tabl validation - verifyRevByRegnCommVsRevByRegionDashBard_RegionPopUPPageCommVal")
	public void verifyRevByRegionCommVsCommByRegionWidget(PeriodFilterBo period) {
		Reporter.createReportNode("Verify : RevByRegn Comm Vs RevByRegion DashBard_RegionPopUPPage CommVal"+period.getPeriod());
		revenueByRegion.verifyRevByRegnCommVsRevByRegionDashBard_RegionPopUPPageCommVal(period);
	}
	@Test(dataProvider ="period", groups= {"author=Hussain", "jira=pst-id"}, description="Revenue by region - Commission popup Table compared with respective Accounttearsheet - Revenue Tab - Revenue by Person Table")
	public void verifyRevByRegionCommissionPopUpPg(PeriodFilterBo period) {
		Reporter.createReportNode("Verify : RevByRegion Commission PopupPage Table Validation"+period.getPeriod());
		revenueByRegion.verifyRevByRegionCommissionPopupPageTableValidation(period);
	}
	
}
