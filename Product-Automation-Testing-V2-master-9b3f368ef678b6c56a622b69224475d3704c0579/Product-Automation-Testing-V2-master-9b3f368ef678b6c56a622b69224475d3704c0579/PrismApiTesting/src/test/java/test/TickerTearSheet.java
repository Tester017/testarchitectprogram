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
import pages.gloablSearch.GlobalSearchPages;
import pages.tickerTearSheet.EditAccountPages;
import pages.tickerTearSheet.ReadershipTabPages;
import pages.tickerTearSheet.SponsorCompaniesTabPage;
import pages.tickerTearSheet.TickerTearSheetHomePage;

public class TickerTearSheet extends Annotations {

	TickerTearSheetHomePage ticker = new TickerTearSheetHomePage();
	/**
	 * user period as custom | ytd ,prev year
	 * if use custom must give start and end date
	 * @return
	 */
	@DataProvider(name = "period" ,parallel = true )
	public Object[] periodFilter() {
		List<PeriodFilterBo> periodList  = new LinkedList<>();
		
        try {
        	 JsonArray periodFilterArray = (JsonArray) JsonParser.parseReader(new FileReader("./src/test/resources/EnvironmentConfig/"+ProductConstant.environmentName+"/ReadershipPeriodFilterJson.json"));
        	 for(int i=0;i<periodFilterArray.size();i++) {
        		 JsonObject filter = periodFilterArray.get(i).getAsJsonObject();
        		 PeriodFilterBo period = new PeriodFilterBo();
        		 period.setConds(getObjectValueAsString(filter, "conds"));
        		 period.setPeriod(getObjectValueAsString(filter, "period"));
        		 period.setYear(getObjectValueAsString(filter, "year"));
        		 period.setStartdate(getObjectValueAsString(filter, "startdate"));
        		 period.setEnddate(getObjectValueAsString(filter, "enddate") );
        		 period.setType(getObjectValueAsString(filter, "type"));
        		 periodList.add(period);
        	 }
        	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return periodList.toArray();
	}
	
	@Test( groups= {"author=Shamily", "jira=PST-7990"}, description="Verify Add Sponsor Companies in Ticker Tear Sheet")
	public void addSponsorCompanies() {
		
		SponsorCompaniesTabPage sponsorCompaniesTabPage = new SponsorCompaniesTabPage();
		if (!ProductConstant.productionMode) {
			sponsorCompaniesTabPage.checkSponsorCompaniesAdded();
		} else {
			reportStep("Production Mode is running", "Info");
		}
	}
	
	@Test( groups= {"author=Shamily", "jira=PST-7990"}, description="Update Ticker Symbol in Ticker Tear Sheet")
	public void updateTickerSymbol() {
		
		EditAccountPages editAccountPages = new EditAccountPages();
		
		if (!ProductConstant.productionMode) {
			editAccountPages.updateTickerAndVerifyAllModules();
		} else {
			reportStep("Production Mode is running", "Info");
		}
		
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7990"}, description="Check Sales Coverage Column with Account Tear Sheet Coverage")
	public void checkSalesCoverageInRedership(PeriodFilterBo period) {
		
		ReadershipTabPages readershipTabPages = new ReadershipTabPages();
		Reporter.reportStep("Period : " + period.toString() , "info");
		JsonObject tickerDetails = GlobalSearchPages.getRandomTickerDetails("DMRC");
		String ticSymbol = getObjectValueAsString(tickerDetails, "id");
		readershipTabPages.checkSalesCoverageWithATS(period, ticSymbol);
		
	}
	
	@Test(groups= {"author=kowsalya", "jira=PST-9608"}, description="Verify Ticker TearSheet Activity Table - Include and Exclude filter Search]", dataProvider = "period")
	public void TickerTearSheetActivityTabShowAndHideMultiFilterSearch(PeriodFilterBo period) {
		if(ProductConstant.productionMode==false) {
			ticker.checkincludeandExcludeMultiFilterSearch(period);
			System.out.println("00000/");
		}
		else {
			reportStep("Production Env - Cannot filter the include and exclude", "info");
		}
	}
	
	@Test(groups= {"author=kowsalya", "jira=PST-9608"}, description="Verify Ticker TearSheet Activity Table -Single Include FilterSearch ]", dataProvider = "period")
	public void TickerTearSheetActivityTabShowAndHideSingleIncludeFilterSearch(PeriodFilterBo period) {
		if(ProductConstant.productionMode==false) {
			ticker.checkSingleincludeFilter(period);
			System.out.println("99/");
		}
		else {
			reportStep("Production Env - Cannot filter the include", "info");
		}
	}
	
	@Test(groups= {"author=kowsalya", "jira=PST-9608"}, description="Verify Ticker TearSheet Activity Table -Single Exclude FilterSearch ]", dataProvider = "period")
	public void TickerTearSheetActivityTabShowAndHideSingleExcludeFilterSearch(PeriodFilterBo period) {
		if(ProductConstant.productionMode==false) {
			ticker.checkSingleExcludeFilter(period);
			System.out.println("98/");
		}
		else {
			reportStep("Production Env - Cannot filter the exclude", "info");
		}
	}
	
	
	
}
