package pages;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.ProjectHooks;

public class BasePage extends ProjectHooks{
	
	@BeforeMethod
	public void setup() {
		new LoginPage()
        .enterUsername("majay3574@gmail.com")
        .enterPassword("Ajaymichael@123")
        .clickLogin()
        .clickAppLauncher()
        .clickViewAllApps();
	}
	
	@AfterMethod
	public void tearDown() {
		
	}

}
