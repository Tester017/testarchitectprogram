package objectpoolwithbrowserfactory;

import org.openqa.selenium.WebDriver;

public interface Browser{
	
	public WebDriver prepareDriver();
	public void closeDriver(WebDriver driver);


}
