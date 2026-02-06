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
import pages.activity.ActivityPage;

public class Activity extends Annotations {
	
	@DataProvider(name = "period" ,parallel = false )
	public Object[] periodFilter() {
		List<PeriodFilterBo> periodList  = new LinkedList<>();
		
        try {
        	 JsonParser parser = new JsonParser();
        	 JsonArray periodFilterArray = (JsonArray) parser.parse(new FileReader("./src/test/resources/EnvironmentConfig/"+ProductConstant.environmentName+"/ActivityModulePeriodFilterJson.json"));
        	 for(int i=0;i<periodFilterArray.size();i++) {
        		 JsonObject filter = periodFilterArray.get(i).getAsJsonObject();
        		 PeriodFilterBo period = new PeriodFilterBo();
        		 period.setPeriod(getObjectValueAsString(filter, "period"));
        		 period.setStartdate(getObjectValueAsString(filter, "startdate") );
        		 period.setEnddate(getObjectValueAsString(filter, "enddate") );
        		 periodList.add(period);
        	 }
        	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return periodList.toArray();
		
	}
	
	ActivityPage activity = new ActivityPage();
		
	@Test(groups= {"author=Lakshmi", "jira=PST"}, description="Verify Activity Table data Contact Tear Sheet - Activity Tab", dataProvider = "period")
	public void ActivityPageWithContactTearSheet(PeriodFilterBo period) {
		activity.checkDataInContactTearSheet(period);
	}
	
	
	@Test(groups= {"author=Lakshmi", "jira=PST"}, description="Verify Activity Table Tile Data", dataProvider = "period")
	public void verifyActivityTileData(PeriodFilterBo period) {
		activity.checkActivityTileData(period);
	}
	
	@Test(groups= {"author=Lakshmi", "jira=PST"}, description="Verify Activity Table data Account Tear Sheet - Activity Tab", dataProvider = "period")
	public void ActivityPageWithAccTearSheet(PeriodFilterBo period) {
		activity.checkDataInAccTearSheet(period);
	}

	
	@Test(groups= {"author=Lakshmi", "jira=PST"}, description="Verify Activity Table - Edit Interaction ]", dataProvider = "period")
	public void ActivityPageEditInteractionVerifyHistory(PeriodFilterBo period) {
		if(!ProductConstant.productionMode) {
			activity.checkEditInteraction(period);
		}else {
			reportStep("Production Env - Cannot Edit Interaction", "info");
		}
		
	}
	
	
	@Test(groups= {"author=Lakshmi", "jira=PST"}, description="Verify Activity Table - Delete Interaction ]", dataProvider = "period")
	public void ActivityPageDeleteInteraction(PeriodFilterBo period) {
		if(!ProductConstant.productionMode) {
			activity.checkDeleteInteraction(period);
		}else {
			reportStep("Production Env - Cannot Delete Interaction", "info");
		}
		
	}
	
	
	@Test(groups= {"author=Shamily", "jira=PST"}, description="Verify Activity Table - Delete Interaction ]")
	public void ActivityPageShowAndHide() {
		if(!ProductConstant.productionMode) {
			activity.checkShowAndHide();
		}else {
			reportStep("Production Env - Cannot Delete Interaction", "info");
		}
		
	}
	
	@Test(groups= {"author=Lakshmi", "jira=PST"}, description="Verify Activity Table Tile Data", dataProvider = "period")
	public void checkColumnSorting(PeriodFilterBo period) {
		activity.validateSorting(period);
	}
	
	@Test(invocationCount = 100 ,groups= {"author=kowsalya", "jira=PST-9608"}, description="Verify Activity Table - Include and Exclude filter Search]", dataProvider = "period")
	public void ActivityPageShowAndHideIncludeandExcludeFilterWithMultiFilterSearch(PeriodFilterBo period) {
		reportStep(period.toString(), "info");
		if(!ProductConstant.productionMode) {
			activity.checkMultiincludeandExcludeFilter(period);
			//System.out.println("00000/");
		}
		else {
			reportStep("Production Env - Cannot filter the include and exclude", "info");
		}
	}
	
	@Test(groups= {"author=kowsalya", "jira=PST-9608"}, description="Verify Activity Table -Single Include FilterSearch ]", dataProvider = "period")
	public void ActivityPageShowAndHideIncludeFilterWithSingleFilterSearch(PeriodFilterBo period) {
		reportStep(period.toString(), "info");
		if(!ProductConstant.productionMode) {
			activity.checkSingleincludeFilter(period);
			System.out.println("99/");
		}
		else {
			reportStep("Production Env - Cannot filter the include", "info");
		}
	}
	
	@Test(invocationCount = 10 ,groups= {"author=kowsalya", "jira=PST-9608"}, description="Verify Activity Table -Single Exclude FilterSearch ]", dataProvider = "period")
	public void ActivityPageShowAndHideExcludeFilterWithSingleFilterSearch(PeriodFilterBo period) {
		reportStep(period.toString(), "info");
		if(!ProductConstant.productionMode) {
			activity.checkSingleExcludeFilter(period);
			System.out.println("98/");
		}
		else {
			reportStep("Production Env - Cannot filter the exclude", "info");
		}
	}
	
	@Test(groups= {"author=kowsalya", "jira=PST-9608"}, description="Verify Activity Table -Single Exclude FilterSearch ]", dataProvider = "period")
	public void ActivityPageShowAndHideExcludeRandomFilter(PeriodFilterBo period) {
		reportStep(period.toString(), "info");
		if(!ProductConstant.productionMode) {
			activity.checkBoththeFilterRandomly(period);
			System.out.println("98/");
		}
		else {
			reportStep("Production Env - Cannot filter the exclude", "info");
		}
	}

	
}
