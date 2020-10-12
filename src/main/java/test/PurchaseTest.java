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
import page.SignInPage;

public class PurchaseTest extends BaseTest {
	private HomePage home = new HomePage();
	private ProductDetailPage detail = new ProductDetailPage();
	private OrderPage order = new OrderPage();
	private SignInPage signIn = new SignInPage();

	private String username = "zenviaproject@email.com";
	private String password = "P@ssw0rd";

	@Test
	public void createAccountAndBuyOneItem() {
		assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
		home.clickSignInButton();
		signIn.performNewUserRegister();
		assertEquals(home.getText(By.className("logout")), "Sign out");
		home.performSearchQuery("t-shirt");
		home.accessProductByTitle("Faded Short Sleeve T-shirts");
		assertEquals(home.getText(By.id("our_price_display")), "$16.51");
		detail.addItemToCart();
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.clickProceedToCheckout();
		assertTrue(home.existElement(By.cssSelector("li.step_current.first")));
		order.clickProceedToCheckout();
		assertTrue(home.existElement(By.cssSelector("li.step_current.third")));
		order.clickProceedAdress();
		assertTrue(home.existElement(By.cssSelector("li.step_current.four")));
		order.acceptTermsOfService();
		order.clickProceedShipping();
		assertTrue(home.existElement(By.cssSelector("li.step_current.last")));
		order.selectPaymentByBank();
		assertEquals(home.getText(By.cssSelector("h3.page-subheading")), "BANK-WIRE PAYMENT.");
		order.confirmMyOrder();
		assertEquals(home.getText(By.cssSelector("p.cheque-indent")), "Your order on My Store is complete.");
	}

	@Test
	public void useExistentUserToBuyOneItem() {
		assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
		home.performSearchQuery("t-shirt");
		home.accessProductByTitle("Faded Short Sleeve T-shirts");
		assertEquals(home.getText(By.id("our_price_display")), "$16.51");
		detail.addItemToCart();
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.clickProceedToCheckout();
		assertTrue(home.existElement(By.cssSelector("li.step_current.first")));
		order.clickProceedToCheckout();
		assertTrue(home.existElement(By.cssSelector("li.step_current.second")));
		signIn.performLogin(username, password);
		assertTrue(home.existElement(By.cssSelector("li.step_current.third")));
		order.clickProceedAdress();
		assertTrue(home.existElement(By.cssSelector("li.step_current.four")));
		order.acceptTermsOfService();
		order.clickProceedShipping();
		assertTrue(home.existElement(By.cssSelector("li.step_current.last")));
		order.selectPaymentByBank();
		assertEquals(home.getText(By.cssSelector("h3.page-subheading")), "BANK-WIRE PAYMENT.");
		order.confirmMyOrder();
		assertEquals(home.getText(By.cssSelector("p.cheque-indent")), "Your order on My Store is complete.");
	}

	@Test
	public void useExistentUserToBuyManyItems() {
		assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
		home.performSearchQuery("t-shirt");
		home.accessProductByTitle("Faded Short Sleeve T-shirts");
		assertEquals(home.getText(By.id("our_price_display")), "$16.51");
		detail.addItemToCart();
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.clickProceedToCheckout();
		home.performSearchQuery("Printed Chiffon Dress");
		home.accessProductByTitle("Printed Chiffon Dress");
		detail.clickIconAddUnityItem();
		detail.addItemToCart();
		BasePage.stop(2000);
		assertTrue(detail.verifySuccessModalAddToCard());
		home.performSearchQuery("Blouse");
		home.accessProductByTitle("Blouse");
		detail.sendWantedQuantityToItem("8");
		detail.addItemToCart();
		BasePage.stop(2000);
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.clickProceedToCheckout();
		assertTrue(home.existElement(By.cssSelector("li.step_current.first")));
		order.clickProceedToCheckout();
		assertTrue(home.existElement(By.cssSelector("li.step_current.second")));
		signIn.performLogin(username, password);
		assertTrue(home.existElement(By.cssSelector("li.step_current.third")));
		order.clickProceedAdress();
		assertTrue(home.existElement(By.cssSelector("li.step_current.four")));
		order.acceptTermsOfService();
		order.clickProceedShipping();
		assertTrue(home.existElement(By.cssSelector("li.step_current.last")));

		order.selectPaymentByCheck();
		assertEquals(home.getText(By.cssSelector("h3.page-subheading")), "CHECK PAYMENT");
		order.confirmMyOrder();
		assertEquals(home.getText(By.cssSelector("p.alert.alert-success")), "Your order on My Store is complete.");
	}

	@Test
	public void dontCompletePurchaseWithoutUserAcceptTerms() {
		assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
		home.performSearchQuery("t-shirt");
		home.accessProductByTitle("Faded Short Sleeve T-shirts");
		assertEquals(home.getText(By.id("our_price_display")), "$16.51");
		detail.addItemToCart();
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.clickProceedToCheckout();
		home.performSearchQuery("Printed Chiffon Dress");
		home.accessProductByTitle("Printed Chiffon Dress");
		detail.clickIconAddUnityItem();
		detail.addItemToCart();
		BasePage.stop(2000);
		assertTrue(detail.verifySuccessModalAddToCard());
		detail.clickProceedToCheckout();
		assertTrue(home.existElement(By.cssSelector("li.step_current.first")));
		order.clickProceedToCheckout();
		assertTrue(home.existElement(By.cssSelector("li.step_current.second")));
		signIn.performLogin(username, password);
		assertTrue(home.existElement(By.cssSelector("li.step_current.third")));
		order.clickProceedAdress();
		assertTrue(home.existElement(By.cssSelector("li.step_current.four")));
		order.clickProceedShipping();
		assertEquals(order.getText(By.cssSelector("p.fancybox-error")),
				"You must agree to the terms of service before continuing.");
	}

}
