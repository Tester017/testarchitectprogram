package test;

import org.testng.annotations.Test;

import annotation.Annotations;
import bo.PeriodFilterBo;
import constant.ProductConstant;
import pages.contactTearSheet.CTSActivityTabPage;

public class ContactTearSheet  extends Annotations {

	CTSActivityTabPage ctsActivityTab= new CTSActivityTabPage();
	
	@Test(groups= {"author=kowsalya", "jira=PST-9608"}, description="Verify Contact TearSheet Activity Table - Include and Exclude filter Search]", dataProvider = "period")
	public void ContactTearSheetActivityTabShowAndHideMultiFilterSearch(PeriodFilterBo period) {
		if(ProductConstant.productionMode==false) {
			ctsActivityTab.checkincludeandExcludeMultiFilterSearch(period);
			System.out.println("00000/");
		}
		else {
			reportStep("Production Env - Cannot filter the include and exclude", "info");
		}
	}
	
	@Test(groups= {"author=kowsalya", "jira=PST-9608"}, description="Verify Contact TearSheet Activity Table -Single Include FilterSearch ]", dataProvider = "period")
	public void ContactTearSheetActivityTabShowAndHideSingleIncludeFilterSearch(PeriodFilterBo period) {
		if(ProductConstant.productionMode==false) {
			ctsActivityTab.checkSingleincludeFilter(period);
			System.out.println("99/");
		}
		else {
			reportStep("Production Env - Cannot filter the include and exclude", "info");
		}
	}
	
	@Test(groups= {"author=kowsalya", "jira=PST-9608"}, description="Verify Contact TearSheet Activity Table -Single Exclude FilterSearch ]", dataProvider = "period")
	public void ContactTearSheetActivityTabShowAndHideSingleExcludeFilterSearch(PeriodFilterBo period) {
		if(ProductConstant.productionMode==false) {
			ctsActivityTab.checkSingleExcludeFilter(period);
			System.out.println("98/");
		}
		else {
			reportStep("Production Env - Cannot filter the include and exclude", "info");
		}
	}
	
	
	
	
	
}
