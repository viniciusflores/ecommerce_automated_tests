package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

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

	public void clickOnMainLogo() {
		click(By.id("header_logo"));
	}

	public void performSearchQuery(String query) {
		clearTextField(By.id("search_query_top"));
		writeAndSendKey(By.id("search_query_top"), query, Keys.ENTER);
	}
	
	public void accessProductByTitle(String title) {
		click(By.cssSelector("a.product-name[title='"+title+"']"));
	}
}
