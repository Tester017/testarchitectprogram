package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LeadsPage;

public class TC001_CreateLead extends BasePage {

    @BeforeTest
    public void setData() {
        dataSheetName = "CreateLead";
    }

    @Test(dataProvider = "fetchData")
    public void runCreateLead(String cName, String fName, String lName, String ph) {
        
    		new LeadsPage()
            .clickCreateLeadLink()
            .enterCompanyName(cName)
            .enterFirstName(fName)
            .enterLastName(lName)
            .enterPhno(ph)
            .clickSubmit();
    }
}
