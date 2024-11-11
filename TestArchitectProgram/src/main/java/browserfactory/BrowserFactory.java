package browserfactory;


public class BrowserFactory {
	
	public static IBrowser launchBrowser( Browser browser) {
		
		IBrowser oBrowser;
		
		switch (browser) {
		case Chrome:
			oBrowser = new ChromeBrowser();
			break;
		case Edge:
			oBrowser = new EdgeBrowser();
			break;

		default:
			throw new IllegalArgumentException("Unknown Browser");
		}
		
		postLaunchSteps();
		
		return oBrowser;
		
	}

	public static void postLaunchSteps() {

		BrowserHelper.maximise();
		BrowserHelper.implicitWait();
		BrowserHelper.loadUrl();
	}

}
