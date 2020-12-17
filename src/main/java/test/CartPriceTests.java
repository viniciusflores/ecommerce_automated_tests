package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import core.BasePage;
import core.BaseTest;
import page.HomePage;
import page.ProductDetailPage;

public class CartPriceTests extends BaseTest {
    private HomePage home = new HomePage();
    private ProductDetailPage detail = new ProductDetailPage();

    @Test
    public void verifyValueWithDifferenceQuantitiesOfSameItem() {
	assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
	home.performSearchQuery("t-shirt");
	home.accessProductByTitle("Faded Short Sleeve T-shirts");
	detail.addItemToCart();
	BasePage.stop(2000);
	assertTrue(detail.verifySuccessModalAddToCard());
	assertEquals(home.getText(By.cssSelector("span#layer_cart_product_price")), "$16.51");
	detail.clickContinueShopping();
	detail.addItemToCart();
	BasePage.stop(2000);
	assertTrue(detail.verifySuccessModalAddToCard());
	assertEquals(home.getText(By.cssSelector("span#layer_cart_product_price")), "$33.02");
	detail.clickContinueShopping();
	detail.sendWantedQuantityToItem("2");
	detail.addItemToCart();
	BasePage.stop(2000);
	assertTrue(detail.verifySuccessModalAddToCard());
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
	BasePage.stop(2000);
	assertTrue(detail.verifySuccessModalAddToCard());
	assertEquals(home.getText(By.cssSelector("span#layer_cart_product_price")), "$16.51");
	detail.clickContinueShopping();

	home.performSearchQuery("Printed Chiffon Dress");
	home.accessProductByTitle("Printed Chiffon Dress");
	detail.clickIconAddUnityItem();
	detail.addItemToCart();
	BasePage.stop(2000);
	assertTrue(detail.verifySuccessModalAddToCard());
	assertEquals(home.getText(By.cssSelector("span#layer_cart_product_price")), "$32.80");

	home.performSearchQuery("Blouse");
	home.accessProductByTitle("Blouse");
	detail.sendWantedQuantityToItem("8");
	detail.addItemToCart();
	BasePage.stop(2000);
	assertTrue(detail.verifySuccessModalAddToCard());
	assertEquals(home.getText(By.cssSelector("span#layer_cart_product_price")), "$216.00");

	detail.clickProceedToCheckout();
	assertTrue(home.existElement(By.cssSelector("li.step_current.first")));
	assertEquals(home.getText(By.cssSelector("td#total_product")), "$265.31");
	assertEquals(home.getText(By.cssSelector("td#total_shipping")), "$2.00");
	assertEquals(home.getText(By.cssSelector("td#total_price_without_tax")), "$267.31");
	assertEquals(home.getText(By.cssSelector("td#total_tax")), "$0.00");
	assertEquals(home.getText(By.cssSelector("span#total_price")), "$267.31");
    }
}
