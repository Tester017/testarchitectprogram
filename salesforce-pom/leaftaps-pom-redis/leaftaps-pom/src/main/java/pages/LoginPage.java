package pages;

import com.testleaf.constants.LocatorType;

import base.ProjectHooks;

public class LoginPage extends ProjectHooks {

    public LoginPage enterUsername(String uname) {
        getBrowser().locateEdit(LocatorType.ID, "username").type(uname);
        return this;
    }

    public LoginPage enterPassword(String pass) {
        getBrowser().locateEdit(LocatorType.ID, "password").type(pass);
        return this;
    }

    public WelcomePage clickLogin() {
        getBrowser().locateButton(LocatorType.CLASS, "decorativeSubmit").click();
        return new WelcomePage();
    }
}
