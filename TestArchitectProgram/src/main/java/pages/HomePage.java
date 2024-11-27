package pages;

import org.openqa.selenium.By;

import base.ProjectSpecificMethod;

public class HomePage extends ProjectSpecificMethod {

	public LeadsPage clickLeadsTab() {
		getDriver() .findElement(By.linkText("Leads")).click();
		return new LeadsPage(); 
	}
	
	
	
	
}
