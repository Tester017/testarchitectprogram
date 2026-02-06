package testcases;

import org.testng.annotations.Test;

import data.RedisDataEngine;
import pages.BasePage;
import pages.LeadsPage;

public class TC004_DeleteLead extends BasePage {
	

	@Test()
	public void runDeleteLead() throws InterruptedException {
		
		// Delete from redis
		RedisDataEngine.deleteLeadRedis(leadInfo.getLeadId());
		
		new LeadsPage()
				.clickFindLeadsLink()
				.enterLeadID(leadInfo.getLeadId())
				.clickFindLeadButton()
				.clickFirstLead()
				.clickDeleteButton()
				.clickFindLeadsLink()
				.clickLeadIDTab()
				.enterLeadID(leadInfo.getLeadId())
				.clickFindLeadButton()
				.verifyDeletedLeadID();
		
		// if test case fails >> save it back
		
		
	}
}
