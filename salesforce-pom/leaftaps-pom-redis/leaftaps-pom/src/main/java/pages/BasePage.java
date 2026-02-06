package pages;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.ProjectHooks;
import config.ConfigurationManager;
import data.CombinedDataEngine;
import data.LeadInfo;

public class BasePage extends ProjectHooks{
	
	protected LeadInfo leadInfo;
	
	@BeforeMethod
	public void setup() {
    	leadInfo = CombinedDataEngine.fetchData();

		new LoginPage()
        .enterUsername(ConfigurationManager.configuration().userName())
        .enterPassword(ConfigurationManager.configuration().password())
        .clickLogin()
        .clickCrmsfa()
        .clickLeadsTab();
	}
	
	@AfterMethod
	public void tearDown() {
		new HomePage()
		.clickOpentaps()
		.clickLogout();
	}

}
