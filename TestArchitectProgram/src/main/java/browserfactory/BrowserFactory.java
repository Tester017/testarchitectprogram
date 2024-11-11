package browserfactory;


public class BrowserFactory {
	
	public static IBrowser launchBrowser( Browser browser) {
		

		switch (browser) {
		case Chrome:
			return new ChromeBrowser();
		case Edge:
			return new EdgeBrowser();

		default:
			throw new IllegalArgumentException("Unknown Browser");
		}
		
		
	}

	

}
