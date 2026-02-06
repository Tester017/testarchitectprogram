package testcases;

import org.testng.annotations.Test;

import data.RedisDataEngine;
import pages.BasePage;
import pages.LeadsPage;

public class TC001_CreateLead extends BasePage {

   
    @Test(invocationCount = 3)
    public void runCreateLead() {
        
    		String leadId = new LeadsPage()
            .clickCreateLeadLink()
            .enterCompanyName(leadInfo.getCompanyName())
            .enterFirstName(leadInfo.getFirstName())
            .enterLastName(leadInfo.getLastName())
            .enterPhno(leadInfo.getPhoneNumber())
            .clickSubmit()
            .getLeadId();
    		
    		// Set the leadId
    		leadInfo.setLeadId(leadId);
    		
    		// Store in Redis
    		RedisDataEngine.saveLeadRedis(leadInfo);
    		
    }
}
