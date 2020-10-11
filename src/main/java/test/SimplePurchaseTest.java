package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import core.BaseTest;
import page.HomePage;
import page.OrderPage;
import page.ProductDetailPage;
import page.SignInPage;

public class SimplePurchaseTest extends BaseTest {
	private HomePage home = new HomePage();
	private ProductDetailPage detail = new ProductDetailPage();
	private OrderPage order = new OrderPage();
	private SignInPage signIn = new SignInPage();

	private String username = "zenviaproject@email.com";
	private String password = "P@ssw0rd";

	@Test
	public void createAccountAndBuyOneItem() {
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
}
