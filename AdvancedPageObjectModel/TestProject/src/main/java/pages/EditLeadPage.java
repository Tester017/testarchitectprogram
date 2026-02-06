package pages;

import org.openqa.selenium.By;

import base.ProjectSpecificMethod;

public class EditLeadPage extends ProjectSpecificMethod {

	public EditLeadPage updateCompanyName(String UdateCName) {

		getDriver().findElement(By.id("updateLeadForm_companyName")).sendKeys(UdateCName);
		return this;
	}

	public ViewLeadPage clickUpdate() {
		getDriver().findElement(By.name("submitButton")).click();

		return new ViewLeadPage();
	}

}
