package page;

import org.openqa.selenium.By;

import core.BasePage;

public class OrderPage extends BasePage {
	public void clickProceedToCheckout() {
		click(By.cssSelector("a.standard-checkout[title='Proceed to checkout']"));
	}

	public void clickProceedAdress() {
		click(By.cssSelector("button[name='processAddress']"));
	}

	public void acceptTermsOfService() {
		click(By.id("uniform-cgv"));
	}

	public void clickProceedShipping() {
		click(By.cssSelector("button[name='processCarrier']"));
	}

	public void selectPaymentByBank() {
		click(By.className("bankwire"));
	}
	
	public void selectPaymentByCheck() {
		click(By.className("cheque"));
	}

	public void confirmMyOrder() {
		click(By.cssSelector("button.button.btn.btn-default.button-medium"));
	}

	public void removeItemFromCart() {
		click(By.cssSelector("i.icon-trash"));
	}
}
