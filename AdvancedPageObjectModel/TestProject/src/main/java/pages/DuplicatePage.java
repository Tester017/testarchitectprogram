package pages;

import org.openqa.selenium.By;

import base.ProjectSpecificMethod;

public class DuplicatePage extends ProjectSpecificMethod {

	
	public ViewLeadPage clickCreateDuplicate() {

		getDriver().findElement(By.name("submitButton")).click();		
		return new ViewLeadPage();
	}
	
	
}
