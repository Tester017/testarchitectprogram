package pages;


import com.testleaf.constants.LocatorType;
import base.ProjectHooks;

public class HomePage extends ProjectHooks {

	public LeadsPage clickLeadsTab() {
		getBrowser().locateLink(LocatorType.XPATH, "//a[contains(text(),'Leads')]").click();
		return new LeadsPage(); 
	}
	
	public WelcomePage clickOpentaps() {
    	getBrowser().locateLink(LocatorType.LINK_TEXT, "opentaps").click();
        return new WelcomePage();
    }
	
	
}
