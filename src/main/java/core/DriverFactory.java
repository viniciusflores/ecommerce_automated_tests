package core;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.TestNGException;

import io.github.bonigarcia.wdm.WebDriverManager;
import project_constants.AutomationTypeEnum;
import project_constants.Constants;

public class DriverFactory {
	protected static WebDriver driver;

	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static WebDriver createDriver(String browser) {
		if (driver == null) {
			try {
				ChromeOptions chromeOptions = new ChromeOptions();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				switch (AutomationTypeEnum.valueOf(browser)) {
				case GRID:
					chromeOptions.addArguments("--start-maximized");
					chromeOptions.addArguments("--headless");
					driver = new RemoteWebDriver(new URL(Constants.SELENIUM_GRID_URL), chromeOptions);
					break;
				case CHROME_LOCAL:
					WebDriverManager.chromedriver().setup();
					chromeOptions.addArguments("--start-maximized");
					chromeOptions.addArguments("--headless");
					driver = new ChromeDriver(chromeOptions);
					break;
				case CHROME:
					chromeOptions.setCapability("browserName", "Chrome");
					chromeOptions.setCapability("browserVersion", "80");
					chromeOptions.setCapability("os", "Windows");
					chromeOptions.setCapability("os_version", "10");
					chromeOptions.setCapability("resolution", "1920x1200");
					firefoxOptions.setCapability("local", "false");
					firefoxOptions.setCapability("seleniumVersion", "3.14.0");
					driver = new RemoteWebDriver(new URL(Constants.BROWSERSTACK_REMOTE_URL), chromeOptions);
					break;
				case FIREFOX:
					firefoxOptions.setCapability("browserName", "Firefox");
					firefoxOptions.setCapability("browserVersion", "47.0");
					firefoxOptions.setCapability("OS", "Windows");
					firefoxOptions.setCapability("osVersion", "XP");
					firefoxOptions.setCapability("local", "false");
					driver = new RemoteWebDriver(new URL(Constants.BROWSERSTACK_REMOTE_URL), firefoxOptions);
					break;
				case ANDROID:
					chromeOptions.setCapability("device", "Samsung Galaxy S10");
					chromeOptions.setCapability("os_version", "9.0");
					chromeOptions.setCapability("realMobile", "true");
					chromeOptions.setCapability("browserstack.video", "true");
					chromeOptions.setCapability("browserstack.debug", "true");
					chromeOptions.setCapability("browserName", "Chrome");
					driver = new RemoteWebDriver(new URL(Constants.BROWSERSTACK_REMOTE_URL), chromeOptions);
					break;
				case IPHONE:
					chromeOptions.setCapability("device", "iPhone 8 Plus");
					chromeOptions.setCapability("os_version", "11");
					chromeOptions.setCapability("realMobile", "true");
					chromeOptions.setCapability("browserstack.video", "true");
					chromeOptions.setCapability("browserstack.debug", "true");
					chromeOptions.setCapability("browserName", "Chrome");
					driver = new RemoteWebDriver(new URL(Constants.BROWSERSTACK_REMOTE_URL), chromeOptions);
					break;
				default:
					break;
				}
				driver.get(Constants.URL);
			} catch (Exception e) {
				throw new TestNGException("Not possible to initialize the driver to test: " + e.getMessage());
			}
		}
		return driver;
	}

}
