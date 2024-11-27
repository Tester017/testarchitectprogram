package base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import utility.ReadExcel;

public class ProjectSpecificMethod {

	public String leadID;

	public String dataSheetName;

	private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

	public static RemoteWebDriver getDriver() {
		return driver.get();
	}

	@BeforeMethod
	public void preCondition() {
		driver.set(new ChromeDriver());
		getDriver().get("http://leaftaps.com/opentaps/");
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@AfterMethod
	public void postCondition() {

		getDriver().close();

	}

	@DataProvider(name = "fetchData")
	public String[][] fetchData() throws IOException {
		return ReadExcel.readExcel(dataSheetName);
	}

}
