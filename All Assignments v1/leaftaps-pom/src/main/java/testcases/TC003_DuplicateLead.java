package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.FindLeadPage;
import pages.LeadsPage;

public class TC003_DuplicateLead extends BasePage {

	@BeforeTest
	public void setData() {
		dataSheetName = "DuplicateLead";
	}

	@Test(dataProvider = "fetchData")
	public void runDuplicateLead(String ph) throws InterruptedException {
		
		String firstLead = new LeadsPage()
			.clickFindLeadsLink()
			.clickPhoneTab()
			.enterPhoneNumber(ph)
			.clickFindLeadButton()
			.getFirstLead();
		
			new FindLeadPage()
			.clickFirstLead()
			.clickDuplicateButton()
			.clickCreateDuplicate()
			.verifyLead(firstLead);

	}
}
