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
		waitElementToBeClickable(By.cssSelector("a.btn[title='Proceed to checkout']"));
		click(By.cssSelector("a.btn[title='Proceed to checkout']"));
	}

	public void clickContinueShopping() {
		waitElementToBeClickable(By.cssSelector("span.continue[title='Continue shopping']"));
		click(By.cssSelector("span.continue[title='Continue shopping']"));
	}
	
	public void clickIconAddUnityItem() {
		click(By.cssSelector("i.icon-plus"));
	}

	public void clickIconRemoveUnityItem() {
		click(By.cssSelector("i.icon-minus"));
	}

	public void sendWantedQuantityToItem(String quantity) {
		clearTextField(By.id("quantity_wanted"));
		write(By.id("quantity_wanted"),quantity);
		
	}

}
