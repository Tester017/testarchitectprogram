package pages;

import org.openqa.selenium.By;

import base.ProjectSpecificMethod;

public class CreateLeadPage extends ProjectSpecificMethod {

	public CreateLeadPage enterCompanyName(String cName) {
		getDriver().findElement(By.id("createLeadForm_companyName")).sendKeys(cName);

		return this;
	}

	public CreateLeadPage enterFirstName(String fName) {
		getDriver().findElement(By.id("createLeadForm_firstName")).sendKeys(fName);

		return this;
	}

	public CreateLeadPage enterLastName(String lName) {
		getDriver().findElement(By.id("createLeadForm_lastName")).sendKeys(lName);

		return this;
	}

	public CreateLeadPage enterPhno(String ph) {
		getDriver().findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(ph);

		return this;
	}

	public ViewLeadPage clickSubmit() {
		getDriver().findElement(By.name("submitButton")).click();
		return new ViewLeadPage();
	}

}
