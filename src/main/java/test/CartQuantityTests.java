package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import core.BasePage;
import core.BaseTest;
import page.HomePage;
import page.OrderPage;
import page.ProductDetailPage;

public class CartQuantityTests extends BaseTest {
	private HomePage home = new HomePage();
	private ProductDetailPage detail = new ProductDetailPage();
	private OrderPage order = new OrderPage();

	@Test
	public void verifyQuantityOfCartWhenAddSecondUnitOfSameItem() {
		assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
		home.performSearchQuery("t-shirt");
		home.accessProductByTitle("Faded Short Sleeve T-shirts");
		detail.addItemToCart();
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.clickContinueShopping();
		detail.clickOnIconPlus();
		detail.addItemToCart();
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.clickProceedToCheckout();
		assertTrue(home.existElement(By.cssSelector("li.step_current.first")));
		assertEquals(home.getText(By.cssSelector(".ajax_cart_quantity")), "3");
		assertEquals(home.getAtributeFromHtml(By.cssSelector("input.cart_quantity_input.form-control.grey"), "value"),
				"3");
	}

	@Test
	public void verifyAddItemAndSubtractQuantityInOrderPage() {
		assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
		home.performSearchQuery("t-shirt");
		home.accessProductByTitle("Faded Short Sleeve T-shirts");
		detail.clickOnIconPlus();
		detail.clickOnIconPlus();
		detail.addItemToCart();
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.clickProceedToCheckout();
		assertTrue(home.existElement(By.cssSelector("li.step_current.first")));
		assertEquals(home.getText(By.cssSelector(".ajax_cart_quantity")), "3");
		assertEquals(home.getAtributeFromHtml(By.cssSelector("input.cart_quantity_input.form-control.grey"), "value"),
				"3");
		detail.clickOnIconMinus();
		BasePage.stop(2000);
		assertTrue(home.existElement(By.cssSelector("li.step_current.first")));
		assertEquals(home.getText(By.cssSelector(".ajax_cart_quantity")), "2");
		assertEquals(home.getAtributeFromHtml(By.cssSelector("input.cart_quantity_input.form-control.grey"), "value"),
				"2");
	}

	@Test
	public void verifyQuantityOfDifferentProducsInCart() {
		assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
		home.performSearchQuery("t-shirt");
		home.accessProductByTitle("Faded Short Sleeve T-shirts");
		detail.addItemToCart();
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.clickContinueShopping();
		home.performSearchQuery("Printed Chiffon Dress");
		home.accessProductByTitle("Printed Chiffon Dress");
		detail.clickOnIconPlus();
		detail.addItemToCart();
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.clickProceedToCheckout();
		assertTrue(home.existElement(By.cssSelector("li.step_current.first")));
		assertEquals(home.getText(By.cssSelector(".ajax_cart_quantity")), "3");
	}

	@Test
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
