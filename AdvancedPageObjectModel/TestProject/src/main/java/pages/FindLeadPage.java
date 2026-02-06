package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.ProjectSpecificMethod;

public class FindLeadPage extends ProjectSpecificMethod {

	public FindLeadPage clickPhoneTab() {
		getDriver().findElement(By.xpath("//span[text()='Phone']")).click();
		return this;
	}

	public FindLeadPage enterPhoneNumber(String ph) {

		getDriver().findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys(ph);

		return this;
	}

	public FindLeadPage clickLeadIDTab() {
		getDriver().findElement(By.xpath("//span[text()='Name and ID']")).click();
		return this;
	}

	public FindLeadPage enterLeadID(String firstResultingLead) {

		WebElement leadIdInput = getDriver().findElement(By.xpath("//input[@name='id']"));
		leadIdInput.sendKeys(firstResultingLead);

		return this;
	}

	public FindLeadPage clickFindLeadButton() {

		getDriver().findElement(By.xpath("//button[text()='Find Leads']")).click();

		return this;
	}

	public FindLeadPage searchFirstResultID() {

		getDriver().findElement(By.xpath("//input[@name='id']")).sendKeys(leadID);

		return this;
	}

	public ViewLeadPage getFirstLeadText() throws InterruptedException {
		Thread.sleep(500);

		leadID = getDriver().findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a"))
				.getText();

		return new ViewLeadPage();
	}

	
	public LeadsPage clickDeleteFirstLead() throws InterruptedException {
		Thread.sleep(1000);
		getDriver().findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		return new LeadsPage();
	}

	public ViewLeadPage clickFirstLead() throws InterruptedException {
		Thread.sleep(500);

		getDriver().findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();

		return new ViewLeadPage();
	}

	public String getFirstLead() throws InterruptedException {
		Thread.sleep(1000);
		leadID = getDriver().findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a"))
				.getText();

		return leadID;
	}

	public FindLeadPage verifydeletedLeadID() {
		String text = getDriver().findElement(By.className("x-paging-info")).getText();
		if (text.equals("No records to display")) {
			System.out.println("Text matched");
		} else {
			System.out.println("Text not matched");
		}
		return this;
	}

	public FindLeadPage verifyMergeLeadID() {
		String text = getDriver().findElement(By.className("x-paging-info")).getText();
		if (text.equals("No records to display")) {
			System.out.println("Text matched");
		} else {
			System.out.println("Text not matched");
		}

		return this;
	}

}
