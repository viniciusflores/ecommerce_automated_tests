package page;

import org.openqa.selenium.By;

import core.BasePage;

public class ProductDetailPage extends BasePage {
	public void addItemToCart() {
		waitElementToBeClickable(By.id("add_to_cart"));
		click(By.id("add_to_cart"));
	}

	public Boolean verifySuccessModalAddToCard() {
		return existElement(By.cssSelector(".icon-ok"));
	}

	public void clickProceedToCheckout() {
		waitElementToBeClickable(By.xpath("//a[@title='Proceed to checkout']"));
		click(By.xpath("//a[@title='Proceed to checkout']"));
	}

}
