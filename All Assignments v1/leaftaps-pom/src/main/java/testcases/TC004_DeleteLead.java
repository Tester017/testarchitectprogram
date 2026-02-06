package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.FindLeadPage;
import pages.LeadsPage;

public class TC004_DeleteLead extends BasePage {
	@BeforeTest
	public void setData() {
		dataSheetName = "DeleteLead";
	}

	@Test(dataProvider = "fetchData")
	public void runDeleteLead(String ph) throws InterruptedException {
		
		String firstLead = 
				new LeadsPage()
				.clickFindLeadsLink()
				.clickPhoneTab()
				.enterPhoneNumber(ph)
				.clickFindLeadButton()
				.clickPhoneTab()
				.enterPhoneNumber(ph)
				.clickFindLeadButton()
				.getFirstLead();
		
				new FindLeadPage()
				.clickDeleteFirstLead()
				.clickFindLeadsLink()
				.clickLeadIDTab()
				.enterLeadID(firstLead)
				.clickFindLeadButton()
				.verifyDeletedLeadID();

	}
}
