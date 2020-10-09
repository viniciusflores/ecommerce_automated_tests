package test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import core.BaseTest;
import page.HomePage;
import page.MyAccountPage;
import page.SignInPage;

public class SimplePurchaseTest extends BaseTest {
	private HomePage home = new HomePage();
	private SignInPage signIn = new SignInPage();
	private MyAccountPage myAccount = new MyAccountPage();

	@Test
	public void createAccount() {
		assertEquals(home.getText(By.className("ajax_cart_no_product")), "(empty)");
		home.clickSignInButton();
		signIn.performNewUserRegister();
		assertEquals(home.getText(By.className("logout")), "Sign out");
		myAccount.returnToHome();
		assertEquals(home.verifyEmptyCart(), "(empty)");
		home.showMenuWomenCategory();
		home.accessWomanCategoryBlouses();
		assertEquals(home.getText(By.className("category-name")), "Blouses");
	}
}
