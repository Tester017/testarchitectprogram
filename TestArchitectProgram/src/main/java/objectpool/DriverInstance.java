package objectpool;

import java.util.LinkedList;
import java.util.Queue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverInstance {

	private static Queue<WebDriver> driverQueue;

	public DriverInstance() {

		driverQueue = new LinkedList<WebDriver>();

	}

	public static WebDriver createDriver() {

		return new ChromeDriver();
	}

	public static WebDriver getDriver() {

		if (!driverQueue.isEmpty()) {
			return driverQueue.poll();
		} else
			return createDriver();
	}

	public static void releaseDriver(WebDriver driver) {

		if (driver != null)
			driverQueue.offer(driver);

	}

}
