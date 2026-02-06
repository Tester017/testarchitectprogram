package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.testleaf.web.api.ResponseAPI;

import pages.BasePage;
import pages.HomePage;
import pages.LeadsPage;

public class TC001_CreateLead extends BasePage {

    @BeforeTest
    public void setData() {
        dataSheetName = "CreateLead";
    }

    @Test(dataProvider = "fetchData")
    public void runCreateLead(String cName, String fName, String lName, String ph) {
        
    	String firstName = "Yaazhinee";
        String lastName = "Babu";
        String companyName = "Testleaf";
        
        String body = "{\n"
        		+ "    \"FirstName\": \""+firstName+"\",\n"
        		+ "    \"LastName\": \""+lastName+"\",\n"
        		+ "    \"Company\": \""+companyName+"\"\n"
        		+ "}";
                
        ResponseAPI response = getAPI().post(apiEndPoint+"/Lead",token, body);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
    	
		String firstLead = new HomePage()
			.searchApps("Leads")
			.clickLeads()
			.getFirstLead();
			
		System.out.println(firstLead);
    }
}
