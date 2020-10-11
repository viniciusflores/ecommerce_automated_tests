package core;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import project_constants.Constants;

public class BasePage extends DriverFactory {
	private JavascriptExecutor js = (JavascriptExecutor) driver;

	public Boolean checkPageTitle(String title) {
		try {
			return driver.getTitle().equalsIgnoreCase(title);
		} catch (Exception e) {
			driver.get(Constants.URL);
			throw new SkipException("PAGE TITLE INCORRECTED: " + e.getMessage());
		}
	}

	public Boolean waitElementToBeClickable(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.elementToBeClickable(by));
			List<WebElement> elements = driver.findElements(by);
			return elements.size() > 0;
		} catch (Exception e) {
			driver.get(Constants.URL);
			throw new SkipException("ELEMENT IS NOT CLICKABLE: " + e.getMessage());
		}
	}

	public Boolean waitElementToBeEnable(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			List<WebElement> elements = driver.findElements(by);
			return elements.size() > 0;
		} catch (Exception e) {
			driver.get(Constants.URL);
			throw new SkipException("ELEMENT IS NOT VISIBLE: " + e.getMessage());
		}
	}

	public void write(By by, String text) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			driver.findElement(by).sendKeys(text);
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public void writeAndSendKey(By by, String text, Keys key) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			driver.findElement(by).sendKeys(text, key);
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public String getText(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			return driver.findElement(by).getText();
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public void clearTextField(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			driver.findElement(by).clear();
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public void click(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			driver.findElement(by).click();
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public void clickByText(String text) {
		click(By.xpath("//*[contains(.,'" + text + "')]"));
	}

	public void clickByTextWithTag(String tagHTML, String text) {
		click(By.xpath("//" + tagHTML + "[contains(.,'" + text + "')]"));
	}

	public void selectComboByValue(By by, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			Select select = new Select(driver.findElement(by));
			select.selectByValue(value);
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public void selectComboByVisibleText(By by, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			Select combo = new Select(driver.findElement(by));
			combo.selectByVisibleText(value);
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public void deselectComboByVisibleText(By by, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			Select combo = new Select(driver.findElement(by));
			combo.deselectByVisibleText(value);
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public List<String> returnAllValuesFromCombo(By by, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			Select combo = new Select(driver.findElement(by));
			List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
			List<String> values = new ArrayList<String>();
			for (WebElement option : allSelectedOptions) {
				values.add(option.getText());
			}
			return values;
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public boolean existElement(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			List<WebElement> elements = driver.findElements(by);
			return elements.size() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean existElementByText(String text) {
		return existElement(By.xpath("//*[contains(.,'" + text + "')]"));
	}

	public void scrollToElement(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(by));
			actions.perform();
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public void scrollToElementByText(String text) {
		scrollToElement(By.xpath("//*[contains(.,'" + text + "')]"));
	}

	public void scroolToElementWithJS(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			WebElement element = driver.findElement(by);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}

	}

	public String getAtributeFromHtml(By by, String atribute) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			return driver.findElement(by).getAttribute(atribute);
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public String getCssAtributeFromHtml(By by, String atribute) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			return driver.findElement(by).getCssValue(atribute);
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}
	}

	public static void stop(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}

	public int returnOnlyNumbers(String word) {
		return Integer.valueOf(word.replaceAll("[^\\d.]", ""));
	}

	public void sendKeyTab(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(by));
			driver.findElement(by).sendKeys(Keys.TAB);
		} catch (Exception e) {
			throw new NoSuchElementException("Element not found: " + e.getMessage());
		}

	}

	public void commandJS(String command) {
		js.executeScript(command);
	}

	public void commandJS(String command, String argument) {
		js.executeScript(command, argument);
	}

}
