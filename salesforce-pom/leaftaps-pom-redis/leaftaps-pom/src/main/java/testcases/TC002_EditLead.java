package testcases;

import org.testng.annotations.Test;

import pages.BasePage;
import pages.LeadsPage;

public class TC002_EditLead extends BasePage {

	
	@Test()
	public void runEditLead() throws InterruptedException {
		
		new LeadsPage()
		.clickFindLeadsLink()
		.enterLeadID(leadInfo.getLeadId())
		.clickFindLeadButton()
		.clickFirstLead()
		.clickEditButton()
		.updateCompanyName(leadInfo.getCompanyName())
		.clickUpdate()
		.verifyCompanyName(leadInfo.getCompanyName());
	}
}
