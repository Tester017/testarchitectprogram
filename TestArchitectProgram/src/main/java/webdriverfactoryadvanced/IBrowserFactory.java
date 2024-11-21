package webdriverfactoryadvanced;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public interface IBrowserFactory {
	
	public WebDriver launchBrowser(Capabilities cap);

}
