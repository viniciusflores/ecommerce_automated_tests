package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import core.BaseTest;
import page.HomePage;
import page.SignInPage;

public class RegisterAndAuthenticationRulesTest extends BaseTest {
	private HomePage home = new HomePage();
	private SignInPage signIn = new SignInPage();

	@Test
	public void verifyInvalidEmailToCreate() {
		home.clickSignInButton();
		signIn.writeAndSendKey(By.id("email_create"), "error-mail", Keys.TAB);
		Assert.assertFalse(signIn.verifyFieldCSSValidationIsGreen(By.id("email_create")));
		signIn.click(By.id("SubmitCreate"));
		Assert.assertEquals(signIn.getText(By.cssSelector("#create_account_error>ol>li")), "Invalid email address.");
	}

	@Test
	public void verifyRequiredEmailToCreate() {
		home.clickSignInButton();
		signIn.click(By.id("SubmitCreate"));
		Assert.assertEquals(signIn.getText(By.cssSelector("#create_account_error>ol>li")), "Invalid email address.");
	}

	@Test
	public void verifyInvalidEmailToLogin() {
		home.clickSignInButton();
		signIn.writeAndSendKey(By.id("email"), "error-mail", Keys.TAB);
		Assert.assertFalse(signIn.verifyFieldCSSValidationIsGreen(By.id("email")));
		signIn.click(By.id("SubmitLogin"));
		Assert.assertEquals(signIn.getText(By.cssSelector("#center_column>div.alert.alert-danger>ol>li")),
				"Invalid email address.");
	}

	@Test
	public void verifyRequiredEmailToLogin() {
		home.clickSignInButton();
		signIn.click(By.id("SubmitLogin"));
		Assert.assertEquals(signIn.getText(By.cssSelector("#center_column>div.alert.alert-danger>ol>li")),
				"An email address required.");
	}

	@Test
	public void verifyInvalidPasswordToLogin() {
		home.clickSignInButton();
		signIn.writeAndSendKey(By.id("email"), "zenviaproject@email.com", Keys.TAB);
		Assert.assertTrue(signIn.verifyFieldCSSValidationIsGreen(By.id("email")));
		signIn.write(By.id("passwd"), "invalid");
		signIn.click(By.id("SubmitLogin"));
		Assert.assertEquals(signIn.getText(By.cssSelector("#center_column>div.alert.alert-danger>ol>li")),
				"Authentication failed.");
	}

	@Test
	public void verifyRequiredPasswordToLogin() {
		home.clickSignInButton();
		signIn.writeAndSendKey(By.id("email"), "zenviaproject@email.com", Keys.TAB);
		Assert.assertTrue(signIn.verifyFieldCSSValidationIsGreen(By.id("email")));
		signIn.click(By.id("SubmitLogin"));
		Assert.assertEquals(signIn.getText(By.cssSelector("#center_column>div.alert.alert-danger>ol>li")),
				"Password is required.");
	}
}
