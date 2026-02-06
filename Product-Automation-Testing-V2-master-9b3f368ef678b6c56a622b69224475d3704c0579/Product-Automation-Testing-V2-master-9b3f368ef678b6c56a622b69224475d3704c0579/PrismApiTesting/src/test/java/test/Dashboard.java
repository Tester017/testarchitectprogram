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
import pages.Dashboard.TopResearchSalesPerson;
import pages.Dashboard.TopSalesTraderPages;

public class Dashboard extends Annotations  {

	
	
	/**
	 * user period as custom | ytd ,prev year
	 * if use custom must give start and end date
	 * @return
	 */
	@DataProvider(name = "period" ,parallel = false )
	public Object[] periodFilter() {
		List<PeriodFilterBo> periodList  = new LinkedList<>();
		
        try {
        	 JsonArray periodFilterArray = (JsonArray) JsonParser.parseReader(new FileReader("./src/test/resources/EnvironmentConfig/"+ProductConstant.environmentName+"/DashBoardPeriodFilterJson.json"));
        	 for(int i=0;i<periodFilterArray.size();i++) {
        		 JsonObject filter = periodFilterArray.get(i).getAsJsonObject();
        		 PeriodFilterBo period = new PeriodFilterBo();
        		 period.setType(getObjectValueAsString(filter, "type"));
        		 period.setPeriod(getObjectValueAsString(filter, "filter"));
        		 period.setConds(getObjectValueAsString(filter, "conds"));
        		 period.setStartdate(getObjectValueAsString(filter, "startdate") );
        		 period.setEnddate(getObjectValueAsString(filter, "enddate") );
        		 
        		 periodList.add(period);
        	 }
        	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	 
		return periodList.toArray();
		
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of  Accts Covered's column Popup with Account Tear Sheet - Revenue Tab")
	public void TopSalesTraderAcctCovPopupWithRevenueTab(PeriodFilterBo period) {
		TopSalesTraderPages topSalesTraderPages = new TopSalesTraderPages();
		createReportNode("Period is : " + period.getPeriod() + "  Start and End date is : " + period.getStartdate() + " " + period.getEnddate());
		topSalesTraderPages.checkAcctsCovPopupWithATSRevenueTab(period);
	}
	
	@Test(dataProvider ="period", groups= {"author=Shamily", "jira=PST-7979"}, description="Verify Table data of  Accts Covered's column Popup with Account Tear Sheet - Revenue Tab")
	public void TopResearchSalesPerAcctCovPopupWithRevenueTab(PeriodFilterBo period) {
		TopResearchSalesPerson topResearchSalesPerson = new TopResearchSalesPerson();
		createReportNode("Period is : " + period.getPeriod() + "  Start and End date is : " + period.getStartdate() + " " + period.getEnddate());
		topResearchSalesPerson.checkAcctsCovPopupWithATSRevenueTab(period);
	}
}
