package page;

import org.openqa.selenium.By;

import core.BasePage;

public class HomePage extends BasePage {
	public void clickSignInButton() {
		click(By.className("login"));
	}

	public String verifyEmptyCart() {
		return getText(By.className("ajax_cart_no_product"));
	}

	public void showMenuWomenCategory() {
		scrollToElement(By.xpath("//a[@title='Women']"));
	}

	public void accessWomanCategoryBlouses() {
		click(By.xpath("//a[@title='Blouses']"));
	}

}
