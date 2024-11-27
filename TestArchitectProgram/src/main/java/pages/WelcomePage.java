package pages;

import org.openqa.selenium.By;

import base.ProjectSpecificMethod;

public class WelcomePage extends ProjectSpecificMethod {

	public WelcomePage verifyTitle() {
		System.out.println(getDriver().getTitle());
		return this;
	}

	public HomePage clickCrmsfa() {
		getDriver().findElement(By.linkText("CRM/SFA")).click();

		return new HomePage();
	}

}
