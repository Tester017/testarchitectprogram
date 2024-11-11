package browserfactory;


public class BrowserFactory {
	
	public static IBrowser launchBrowser( Browser browser) {
		

		switch (browser) {
		case CHROME:
			return new ChromeBrowser();
		case EDGE:
			return new EdgeBrowser();

		default:
			throw new IllegalArgumentException("Unknown Browser");
		}
		
		
	}

	

}
