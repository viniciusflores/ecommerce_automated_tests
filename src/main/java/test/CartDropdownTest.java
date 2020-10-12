package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import core.BasePage;
import core.BaseTest;
import page.HomePage;
import page.ProductDetailPage;

public class CartDropdownTest extends BaseTest {
	private HomePage home = new HomePage();
	private ProductDetailPage detail = new ProductDetailPage();

	@Test
	public void verifyQuantityAndPriceInModalCartFromOneItem() {
		assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
		home.performSearchQuery("t-shirt");
		home.accessProductByTitle("Faded Short Sleeve T-shirts");
		detail.clickIconAddUnityItem();
		detail.addItemToCart();
		BasePage.stop(2000);
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.closeModalItemAddedToCart();
		assertEquals(home.getText(By.cssSelector("div.shopping_cart>a>span.ajax_cart_quantity")), "2");
		home.expandCartModal();
		assertEquals(home.getText(By.cssSelector("div.product-name>span>span.quantity")), "2");
		assertEquals(home.getAtributeFromHtml(By.cssSelector("a.cart_block_product_name"), "title"),
				"Faded Short Sleeve T-shirts");
		assertEquals(home.getText(By.cssSelector("dl.products>dt.first_item>div>span.price")), "$33.02");
		assertEquals(home.getText(By.cssSelector("span.price.cart_block_shipping_cost.ajax_cart_shipping_cost")),
				"$2.00");
		assertEquals(home.getText(By.cssSelector("span.price.cart_block_total.ajax_block_cart_total")), "$35.02");
		assertTrue(home.waitElementToBeClickable(By.id("button_order_cart")));
	}

	@Test
	public void verifyQuantityAndPriceInModalCartFromManyItems() {
		assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
		home.performSearchQuery("t-shirt");
		home.accessProductByTitle("Faded Short Sleeve T-shirts");
		detail.addItemToCart();
		BasePage.stop(2000);
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.closeModalItemAddedToCart();

		home.performSearchQuery("Printed Chiffon Dress");
		home.accessProductByTitle("Printed Chiffon Dress");
		detail.clickIconAddUnityItem();
		detail.addItemToCart();
		BasePage.stop(2000);
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.closeModalItemAddedToCart();

		home.performSearchQuery("Blouse");
		home.accessProductByTitle("Blouse");
		detail.sendWantedQuantityToItem("8");
		detail.addItemToCart();
		BasePage.stop(2000);
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.closeModalItemAddedToCart();

		assertEquals(home.getText(By.cssSelector("div.shopping_cart>a>span.ajax_cart_quantity")), "11");
		home.expandCartModal();
		assertEquals(home.getText(By.cssSelector("dt.first_item>div>span.price")), "$16.51");
		assertEquals(home.getText(By.cssSelector("dt.item>div>span.price")), "$32.80");
		assertEquals(home.getText(By.cssSelector("dt.last_item>div>span.price")), "$216.00");
		assertEquals(home.getText(By.cssSelector("span.price.cart_block_total.ajax_block_cart_total")), "$267.31");
		assertTrue(home.waitElementToBeClickable(By.id("button_order_cart")));
	}

	@Test
	public void verifyRemoveFromModalCart() {
		assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
		home.performSearchQuery("t-shirt");
		home.accessProductByTitle("Faded Short Sleeve T-shirts");
		detail.addItemToCart();
		BasePage.stop(2000);
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.closeModalItemAddedToCart();

		home.performSearchQuery("Printed Chiffon Dress");
		home.accessProductByTitle("Printed Chiffon Dress");
		detail.clickIconAddUnityItem();
		detail.addItemToCart();
		BasePage.stop(2000);
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.closeModalItemAddedToCart();

		home.expandCartModal();
		home.click(By.cssSelector("dt.first_item>span.remove_link"));

		home.clickOnMainLogo();
		home.expandCartModal();
		home.click(By.cssSelector("dt.first_item>span.remove_link"));

		home.clickOnMainLogo();
		home.expandCartModal();

		BasePage.stop(2000);
		assertEquals(home.getText(By.cssSelector(".ajax_cart_no_product")), "(empty)");

	}

}
