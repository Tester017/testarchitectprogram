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
import pages.Readership.ReadershipHomePage;
import pages.Readership.ReadershipModule;
import pages.contactTearSheet.CTSReaderShipTabPage;

public class Readership extends Annotations {
	ReadershipHomePage readership = new ReadershipHomePage();
	ReadershipModule readershipmodule=new ReadershipModule();
	
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
		
	@Test(groups= {"author=Lakshmi", "jira=PST"}, description="Verify Readership Table data with Account Tear Sheet - Readership Tab")
	public void ReadershipPageWithAccTearSheet() {
		readership.verifyReaderShipDataWithAccTearsheet();
	}
	
	
	@Test(groups= {"author=Lakshmi", "jira=PST"}, description="Verify Readership Table data with Ticker Tear Sheet - Readership Tab")
	public void ReadershipPageWithTickerTearSheet() {
		readership.verifyReaderShipDataWithTickerTearsheet();
	}
	
	
	@Test(groups= {"author=Lakshmi", "jira=PST"}, description="Verify Readership Table data with MyList - Search")
	public void ReadershipPageWithMyList() {
		readership.checkReadershipDataInMylist();
	}
	
	@Test(groups= {"author=hussain", "jira=PST - "}, description="ReadershipPageWithContactTearSheet - verifyReadershipDataWithCTSReaderShipData")
	public void verifyReadershipDataWithCTSReaderShipData() {
		CTSReaderShipTabPage ctsReaderShipTabPage=new CTSReaderShipTabPage();
		Reporter.createReportNode("*****Verify Readership Data With CTS Reader Ship Data*****");
		ctsReaderShipTabPage.verifyReadershipDataWithCTSReaderShipData();
		
	}
	
	@Test(groups= {"author=hussain", "jira=PST - "}, description="ReadershipPageWithContactTearSheet - verifySalesCoverageInReaderShipData")
	public void verifySalesCoverageInReaderShipData() {
		CTSReaderShipTabPage ctsReaderShipTabPage=new CTSReaderShipTabPage();
		Reporter.createReportNode("*****Verify Sales Coverage In ReaderShip Data*****");
		ctsReaderShipTabPage.verifySalesCoverageInReaderShipData();
	}
	
	@Test(groups= {"author=hussain", "jira=PST - "}, description="ReadershipPageWithContactTearSheet - verifyTilesCount")
	public void verifyTilesCount() {
		CTSReaderShipTabPage ctsReaderShipTabPage=new CTSReaderShipTabPage();
		Reporter.createReportNode("*****Verify TilesCount for Accounts,Ticker,Contact and Hits*****");
		ctsReaderShipTabPage.verifyTilesCount();
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST"}, description="Verify Readership Table data with Account Tear Sheet - Readership Tab")
	public void ReadershipPageWithAccountTearSheet(PeriodFilterBo periodFilterBo) {
		readership.verifyReaderShipDataWithAccTearsheet(periodFilterBo);
	}
	
	
	@Test(groups= {"author=kowsalya", "jira=PST-9608"}, description="Verify ReaderShipTable - Include and Exclude filter Search]", dataProvider = "period")
	public void ReaderShipPageShowAndHideIncludeandExcludeFilterWithMultiFilterSearch(PeriodFilterBo period) {
		if(ProductConstant.productionMode==false) {
			readershipmodule.checkincludeandExcludeFilter(period);
			System.out.println("00000/");
		}
		else {
			reportStep("Production Env - Cannot filter the include and exclude", "info");
		}
	}
	
	@Test(groups= {"author=kowsalya", "jira=PST-9608"}, description="Verify ReaderShip Table -Single Include FilterSearch ]", dataProvider = "period")
	public void ReaderShipPageShowAndHideIncludeFilterWithSingleFilterSearch(PeriodFilterBo period) {
		if(ProductConstant.productionMode==false) {
			readershipmodule.checkincludeFilter(period);
			System.out.println("99/");
		}
		else {
			reportStep("Production Env - Cannot filter the include", "info");
		}
	}
	
	@Test(groups= {"author=kowsalya", "jira=PST-9608"}, description="Verify ReaderShip Table -Single Exclude FilterSearch ]", dataProvider = "period")
	public void ReaderShipPageShowAndHideExcludeFilterWithSingleFilterSearch(PeriodFilterBo period) {
		if(ProductConstant.productionMode==false) {
			readershipmodule.checkExcludeFilter(period);
			System.out.println("98/");
		}
		else {
			reportStep("Production Env - Cannot filter the exclude", "info");
		}
	}
	
	
}
