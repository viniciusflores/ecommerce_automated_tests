package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import core.BasePage;
import core.BaseTest;
import page.HomePage;
import page.OrderPage;
import page.ProductDetailPage;

public class CartPriceTests extends BaseTest {
	private HomePage home = new HomePage();
	private ProductDetailPage detail = new ProductDetailPage();
	private OrderPage order = new OrderPage();

	@Ignore
	public void verifyValueWithDifferenceQuantitiesOfSameItem() {
		assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
		home.performSearchQuery("t-shirt");
		home.accessProductByTitle("Faded Short Sleeve T-shirts");
		detail.addItemToCart();
		assertTrue(detail.verifySuccessModalAddToCard());
		BasePage.stop(2000);
		assertEquals(home.getText(By.cssSelector("span#layer_cart_product_price")), "$16.51");
		detail.clickContinueShopping();
		detail.addItemToCart();
		assertTrue(detail.verifySuccessModalAddToCard());
		BasePage.stop(2000);
		assertEquals(home.getText(By.cssSelector("span#layer_cart_product_price")), "$33.02");
		detail.clickContinueShopping();
		detail.addItemToCart();
		detail.addItemToCart();
		assertTrue(detail.verifySuccessModalAddToCard());
		BasePage.stop(2000);
		assertEquals(home.getText(By.cssSelector("span#layer_cart_product_price")), "$66.04");
		detail.clickProceedToCheckout();
		assertTrue(home.existElement(By.cssSelector("li.step_current.first")));
		assertEquals(home.getText(By.cssSelector("td#total_product")), "$66.04");
		assertEquals(home.getText(By.cssSelector("td#total_shipping")), "$2.00");
		assertEquals(home.getText(By.cssSelector("td#total_price_without_tax")), "$68.04");
		assertEquals(home.getText(By.cssSelector("td#total_tax")), "$0.00");
		assertEquals(home.getText(By.cssSelector("span#total_price")), "$68.04");

	}

	@Test
	public void verifyValueWithDifferenceItens() {
		assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
		home.performSearchQuery("t-shirt");
		home.accessProductByTitle("Faded Short Sleeve T-shirts");
		detail.addItemToCart();
		assertTrue(detail.verifySuccessModalAddToCard());
		BasePage.stop(2000);
		assertEquals(home.getText(By.cssSelector("span#layer_cart_product_price")), "$16.51");
		detail.clickContinueShopping();
		
		home.performSearchQuery("Printed Chiffon Dress");
		home.accessProductByTitle("Printed Chiffon Dress");
		detail.clickIconAddUnityItem();
		detail.addItemToCart();
		BasePage.stop(2000);
		assertEquals(home.getText(By.cssSelector("span#layer_cart_product_price")), "$32.80");
	
		home.performSearchQuery("Blouse");
		home.accessProductByTitle("Blouse");
		detail.sendWantedQuantityToItem("8");
		detail.addItemToCart();
		BasePage.stop(2000);
		assertEquals(home.getText(By.cssSelector("span#layer_cart_product_price")), "$216.00");

		detail.clickProceedToCheckout();
		assertTrue(home.existElement(By.cssSelector("li.step_current.first")));
		assertEquals(home.getText(By.cssSelector("td#total_product")), "$265.31");
		assertEquals(home.getText(By.cssSelector("td#total_shipping")), "$2.00");
		assertEquals(home.getText(By.cssSelector("td#total_price_without_tax")), "$267.31");
		assertEquals(home.getText(By.cssSelector("td#total_tax")), "$0.00");
		assertEquals(home.getText(By.cssSelector("span#total_price")), "$267.31");
	}

	@Ignore
	public void verifyQuantityOfDifferentProducsInCart() {
		assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
		home.performSearchQuery("t-shirt");
		home.accessProductByTitle("Faded Short Sleeve T-shirts");
		detail.addItemToCart();
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.clickContinueShopping();
		home.performSearchQuery("Printed Chiffon Dress");
		home.accessProductByTitle("Printed Chiffon Dress");
		detail.clickIconAddUnityItem();
		detail.addItemToCart();
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.clickProceedToCheckout();
		assertTrue(home.existElement(By.cssSelector("li.step_current.first")));
		assertEquals(home.getText(By.cssSelector(".ajax_cart_quantity")), "3");
	}

	@Ignore
	public void removeItemFormCart() {
		assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
		home.performSearchQuery("t-shirt");
		home.accessProductByTitle("Faded Short Sleeve T-shirts");
		detail.addItemToCart();
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.clickProceedToCheckout();
		assertTrue(home.existElement(By.cssSelector("li.step_current.first")));
		assertEquals(home.getText(By.cssSelector(".ajax_cart_quantity")), "1");
		order.removeItemFromCart();
		BasePage.stop(2000);
		assertEquals(home.getText(By.cssSelector("p.alert.alert-warning")), "Your shopping cart is empty.");
		assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
	}

}
