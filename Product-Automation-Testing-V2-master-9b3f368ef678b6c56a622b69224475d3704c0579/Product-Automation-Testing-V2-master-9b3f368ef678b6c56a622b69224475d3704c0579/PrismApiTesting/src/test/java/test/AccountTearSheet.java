package test;

import java.text.Annotation;

import org.testng.annotations.Test;

import annotation.Annotations;
import bo.PeriodFilterBo;
import constant.ProductConstant;
import pages.accounttearsheet.ATSActivityTabPage;

public class AccountTearSheet extends Annotations{
	
	ATSActivityTabPage accountaTSActivityTab= new ATSActivityTabPage();
	

	@Test(groups= {"author=kowsalya", "jira=PST-9608"}, description="Verify Account TearSheet Activity Table - Include and Exclude filter Search]", dataProvider = "period")
	public void AccountTearSheetActivityTabShowAndHideMultiFilterSearch(PeriodFilterBo period) {
		if(ProductConstant.productionMode==false) {
			accountaTSActivityTab.AccountTearSheetActivityTabShowAndHideMultiFilterSearch(period);
			System.out.println("00000/");
		}
		else {
			reportStep("Production Env - Cannot filter the include and exclude", "info");
		}
	}

	@Test(groups= {"author=kowsalya", "jira=PST-9608"}, description="Verify Account TearSheet Activity Table -Single Include FilterSearch ]", dataProvider = "period")
	public void AccountTearSheetActivityTabShowAndHideSingleIncludeFilterSearch(PeriodFilterBo period) {
		if(ProductConstant.productionMode==false) {
			accountaTSActivityTab.checkSingleincludeFilter(period);
			System.out.println("99/");
		}
		else {
			reportStep("Production Env - Cannot filter the include and exclude", "info");
		}
	}
	
	@Test(groups= {"author=kowsalya", "jira=PST-9608"}, description="Verify Account TearSheet Activity Table -Single Exclude FilterSearch ]", dataProvider = "period")
	public void AccountTearSheetActivityTabShowAndHideSingleExcludeFilterSearch(PeriodFilterBo period) {
		if(ProductConstant.productionMode==false) {
			accountaTSActivityTab.checkSingleExcludeFilter(period);
			System.out.println("98/");
		}
		else {
			reportStep("Production Env - Cannot filter the include and exclude", "info");
		}
	}
	
}
