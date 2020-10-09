package core;

import static java.lang.System.getProperty;
import static java.lang.System.setProperty;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest extends DriverFactory {

	@BeforeMethod
	@Parameters({ "browser" })
	public static void setUpBaseTest(String browser) {
		setProperty("BROWSER", browser);
		createDriver(getProperty("BROWSER"));
	}

	@AfterMethod
	public static void tearDown() {
		killDriver();
	}

	public static void waitFixed(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
}
