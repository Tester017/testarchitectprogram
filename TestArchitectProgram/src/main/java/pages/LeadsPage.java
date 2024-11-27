package pages;

import org.openqa.selenium.By;

import base.ProjectSpecificMethod;

public class LeadsPage extends ProjectSpecificMethod {

	public CreateLeadPage clickCreateLeadLink() {
		getDriver().findElement(By.linkText("Create Lead")).click();

		return new CreateLeadPage();
	}

	public FindLeadPage clickfindLeadLink() {
		getDriver().findElement(By.linkText("Find Leads")).click();
		return new FindLeadPage();
	}

	public MergeLeadPage clickmergeLeadLink() {
		getDriver().findElement(By.linkText("Merge Leads")).click();
		return new MergeLeadPage();
	}

}
