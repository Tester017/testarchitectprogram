package browserfactory;

import org.openqa.selenium.WebDriver;

public class Browsers {
	
	public static WebDriver driver;
	
	public static void postLaunchSteps() {

		BrowserHelper.maximise();
		BrowserHelper.implicitWait();
		BrowserHelper.loadUrl();
	}

}
