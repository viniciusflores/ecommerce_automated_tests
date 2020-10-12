package core;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

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
				case CHROME:
					chromeOptions.setCapability("os", "Windows");
					chromeOptions.setCapability("os_version", "10");
					chromeOptions.setCapability("browser", "Chrome");
					chromeOptions.setCapability("browser_version", "80");
					driver = new RemoteWebDriver(new URL(Constants.BROWSERSTACK_REMOTE_URL), chromeOptions);
					break;
				case CHROME_LOCAL:
					WebDriverManager.chromedriver().setup();
					//chromeOptions.addArguments("--window-size=1024,768");
					chromeOptions.addArguments("--start-maximized");
					chromeOptions.addArguments("--headless");
					driver = new ChromeDriver(chromeOptions);
					//driver.manage().window().fullscreen();
					break;
				case FIREFOX:
					WebDriverManager.firefoxdriver().setup();
					firefoxOptions.addArguments("--window-size=1024,768");
					firefoxOptions.addArguments("--start-maximized");
					firefoxOptions.addArguments("--headless");
					driver = new FirefoxDriver(firefoxOptions);
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
				e.printStackTrace();
			}
		}
		return driver;
	}

}
