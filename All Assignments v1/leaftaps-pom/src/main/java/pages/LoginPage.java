package pages;

import com.testleaf.constants.LocatorType;

import base.ProjectHooks;

public class LoginPage extends ProjectHooks {

    public LoginPage enterUsername(String uname) {
    	browser.locateEdit(LocatorType.ID, "username").type(uname);
        return this;
    }

    public LoginPage enterPassword(String pass) {
    	browser.locateEdit(LocatorType.ID, "password").type(pass);
        return this;
    }

    public WelcomePage clickLogin() {
    	browser.locateButton(LocatorType.CLASS, "decorativeSubmit").click();
        return new WelcomePage();
    }
}
