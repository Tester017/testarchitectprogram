package pages;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.ProjectHooks;

public class BasePage extends ProjectHooks{
	
	@BeforeMethod
	public void init() {
		new LoginPage()
        .enterUsername("demosalesmanager")
        .enterPassword("crmsfa")
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
