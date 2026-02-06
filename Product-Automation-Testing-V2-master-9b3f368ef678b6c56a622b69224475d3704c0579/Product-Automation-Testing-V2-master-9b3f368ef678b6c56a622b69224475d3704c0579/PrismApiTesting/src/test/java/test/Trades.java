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
import constant.ProductConstant;
import pages.trades.TradesHomePage;

public class Trades extends Annotations {
	
	TradesHomePage tradeHomePage = new TradesHomePage();
	
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
        	 JsonArray periodFilterArray = (JsonArray) parser.parse(new FileReader("./src/test/resources/EnvironmentConfig/"+ProductConstant.environmentName+"/TradesModulePeriodJosn.json"));
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
	
	
	@Test( groups= {"author=Bhuvanes", "trades"}, description="Calculate net value in trades module")
	public void tradesNetValueCommissionCalculated() {
		
		 tradeHomePage.NetValueCommissionVerification();
	
	}
	 
	
	@Test( groups= {"author=Bhuvanes", "trades"}, description="Verify Add Sponsor Companies in Ticker Tear Sheet")
	public void verifyTradeSummaryInAccountTS() {
		
		tradeHomePage.verifyAccountTearSheet();
		
	}
	 
	@Test(dataProvider ="period",groups = {"author = Lakshmi", "Trades"}, description = "Verify Trades Summary in Ticker Tear Sheet")
	public void tradesValidationInTickerTearSheet(PeriodFilterBo period) {
		tradeHomePage.verifyInTickerTearSheet(period);
	}
	
	@Test(dataProvider ="period",groups = {"author = Lakshmi", "Trades - TradesRollUp Toggle On"}, description = "Verify Trades Summary - TradesRollUp Toggle On - in Ticker Tear Sheet")
	public void tradesValidationInTickerTearSheetForTradesRollUpToggleOn(PeriodFilterBo period) {
		tradeHomePage.verifyInTickerTearSheetForTradesRollUpToggleOn(period);
	} 
	
	
	@Test(dataProvider ="period",groups = {"author = Lakshmi", "Trades - TradesRollUp Toggle On"}, description = "Verify Trades Summary - TradesRollUp Toggle On - in Account Tear Sheet")
	public void tradesValidationInAccTearSheetForTradesRollUpToggleOn(PeriodFilterBo period) {
		tradeHomePage.verifyInAccountTearSheetForTradesRollUpToggleOn(period);
	} 
	
	
	@Test(dataProvider ="period",groups = {"author = Lakshmi", "Trades - TradesRollUp Toggle On"}, description = "Verify Trades Summary Net Value Comm - TradesRollUp Toggle On")
	public void tradesNetValueValidationForTradesRollUpToggleOn(PeriodFilterBo period) {
		tradeHomePage.verifyNetValueCommissionForTradesRollUpToggle(period);
	} 
	
	@Test(dataProvider ="period",groups = {"author = Lakshmi", "Trades - Dashboard Tile Validation"}, description = "Verify Trades - Dashboard Tile Validation")
	public void verifyTradesWithDashboardTile(PeriodFilterBo period) {
		tradeHomePage.verifyProductCommInDashboardTiles(period);
	} 
	
	
	@Test(dataProvider ="period",groups = {"author = Lakshmi", "Trades - Dashboard Tile Validation"}, description = "Verify Trades - Acc tear sheet Tile Validation")
	public void verifyTradesWithAccTearSheet(PeriodFilterBo period) {
		tradeHomePage.verifyTradesAccCommInAccTearSheet(period);
	} 
	
	
	@Test(dataProvider ="period",groups = {"author = Lakshmi", "Trades - Sales Person Tearsheet Validation"}, description = "Verify Trades - Sales Person tear sheet Validation")
	public void verifyTradesWithSalesPersonTearSheet(PeriodFilterBo period) {
		tradeHomePage.verifyEmpNameInSalesTraderSheet(period);
	} 
	
	


}
