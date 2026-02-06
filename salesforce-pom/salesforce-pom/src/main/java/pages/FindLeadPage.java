package pages;

import com.testleaf.constants.LocatorType;

public class FindLeadPage extends BasePage {

    public FindLeadPage clickPhoneTab() {
        getBrowser().locateButton(LocatorType.XPATH, "//span[text()='Phone']").click();
        return this;
    }

    public FindLeadPage enterPhoneNumber(String phoneNumber) {
        getBrowser().locateEdit(LocatorType.XPATH, "(//input[@name='phoneNumber'])[1]").type(phoneNumber);
        return this;
    }

    public FindLeadPage clickLeadIDTab() {
        getBrowser().locateButton(LocatorType.XPATH, "//span[text()='Name and ID']").click();
        return this;
    }

    public FindLeadPage enterLeadID(String leadID) {
        getBrowser().locateEdit(LocatorType.XPATH, "//input[@name='id']").type(leadID);
        return this;
    }

    public FindLeadPage clickFindLeadButton() {
        getBrowser().locateButton(LocatorType.XPATH, "(//button[text()='Find Leads'])[1]").click();
        return this;
    }

    public FindLeadPage searchLeadID(String leadID) {
        getBrowser().locateEdit(LocatorType.XPATH, "//input[@name='id']").type(leadID);
        return this;
    }

    public ViewLeadPage clickFirstResultID() {
        getBrowser().locateLink(LocatorType.XPATH, "(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]").click();
        return new ViewLeadPage();
    }

    public String getFirstLeadText() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getBrowser().locateElement(LocatorType.XPATH, "(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]").getText();
    }

    public LeadsPage clickDeleteFirstLead() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        getBrowser().locateLink(LocatorType.XPATH, "(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]").click();
        return new LeadsPage();
    }

    public ViewLeadPage clickFirstLead() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        getBrowser().locateLink(LocatorType.XPATH, "(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]").click();
        return new ViewLeadPage();
    }

    public String getFirstLead() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getBrowser().locateElement(LocatorType.XPATH, "(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]").getText();
    }

    public FindLeadPage verifyDeletedLeadID() {
        String text = getBrowser().locateElement(LocatorType.CLASS, "x-paging-info").getText();
        if ("No records to display".equals(text)) {
            System.out.println("Text matched");
        } else {
            System.out.println("Text not matched");
        }
        return this;
    }


}
