package page;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.Color;
import org.testng.TestNGException;

import com.github.javafaker.Faker;

import core.BasePage;

public class SignInPage extends BasePage {
	protected Faker fake = new Faker(new Locale("en-US"));

	public void performLogin(String username, String password) {
		writeAndSendKey(By.id("email"), username, Keys.TAB);
		writeAndSendKey(By.id("passwd"), password, Keys.TAB);
		if (verifyFieldCSSValidationIsGreen(By.id("email")).equals(false)) {
			throw new TestNGException("Not possible validade the css animation of field");
		}
		click(By.id("SubmitLogin"));
	}

	public void performNewUserRegister() {
		accessCreateNewAccount();
		fillCreateUserPage();
	}

	public void accessCreateNewAccount() {
		writeAndSendKey(By.id("email_create"), fake.internet().safeEmailAddress(), Keys.TAB);
		if (verifyFieldCSSValidationIsGreen(By.id("email_create")).equals(false)) {
			throw new TestNGException("Not possible validade the css animation of field");
		}
		click(By.id("SubmitCreate"));
	}

	public Boolean verifyFieldCSSValidationIsGreen(By by) {
		return Color.fromString(getCssAtributeFromHtml(by, "color")).asHex().equalsIgnoreCase("#35b33f");
	}

	private void verifyCssAnimationFromSignInPage() {
		List<Boolean> fieldsCss = new ArrayList<>();
		fieldsCss.add(verifyFieldCSSValidationIsGreen(By.id("customer_firstname")));
		fieldsCss.add(verifyFieldCSSValidationIsGreen(By.id("customer_lastname")));
		fieldsCss.add(verifyFieldCSSValidationIsGreen(By.id("email")));
		fieldsCss.add(verifyFieldCSSValidationIsGreen(By.id("passwd")));
		for (int i = 0; i < fieldsCss.size(); i++) {
			if (fieldsCss.get(i).equals(false)) {
				throw new TestNGException("Not possible validade the css animation of field");
			}
		}
	}

	public void fillCreateUserPage() {
		selectSex();
		fillCustomerFirstName();
		fillCustomerLastName();
		fillEmail();
		fillPassword();
		verifyCssAnimationFromSignInPage();
		fillCompleteDateOfBirth();
		fillFirstName();
		fillLastName();
		fillCompanyName();
		fillAddress();
		fillAddressSecondLine();
		fillCity();
		fillState();
		fillZipCode();
		fillCountry();
		fillAdditionalInformation();
		fillHomePhone();
		fillMobilePhone();
		fillAddressReference();
		clickOnRegister();
	}

	public void selectSex() {
		Boolean sex = fake.bool().bool();
		if (sex.equals(true)) {
			click(By.id("id_gender2"));
		} else {
			click(By.id("id_gender1"));
		}
	}

	public void fillCustomerFirstName() {
		writeAndSendKey(By.id("customer_firstname"), fake.name().firstName(), Keys.TAB);
	}

	public void fillCustomerLastName() {
		writeAndSendKey(By.id("customer_lastname"), fake.name().lastName(), Keys.TAB);
	}

	public void fillEmail() {
		if (getText(By.id("email")).length() < 0) {
			write(By.id("email"), fake.internet().safeEmailAddress());
		}
		sendKeyTab(By.id("email"));
	}

	public void fillPassword() {
		writeAndSendKey(By.id("passwd"), fake.internet().password(), Keys.TAB);
	}

	public void fillCompleteDateOfBirth() {
		fillDayOfBirth();
		fillMonthOfBirth();
		fillYearOfBirth();
	}

	public void fillDayOfBirth() {
		selectComboByValue(By.id("days"), Integer.toString(fake.number().numberBetween(1, 28)));
	}

	public void fillMonthOfBirth() {
		selectComboByValue(By.id("months"), Integer.toString(fake.number().numberBetween(1, 12)));
	}

	public void fillYearOfBirth() {
		selectComboByValue(By.id("years"), Integer.toString(fake.number().numberBetween(1990, 2020)));
	}

	public void fillFirstName() {
		if (getText(By.id("firstname")).length() < 0) {
			write(By.id("firstname"), fake.name().firstName());
		}
	}

	public void fillLastName() {
		if (getText(By.id("lastname")).length() < 0) {
			write(By.id("lastname"), fake.name().lastName());
		}
	}

	public void fillCompanyName() {
		write(By.id("company"), fake.company().name());
	}

	public void fillAddress() {
		write(By.id("address1"), fake.address().streetAddress());
	}

	public void fillAddressSecondLine() {
		write(By.id("address2"), fake.address().secondaryAddress());
	}

	public void fillCity() {
		write(By.id("city"), fake.address().cityName());
	}

	public void fillState() {
		selectComboByValue(By.id("id_state"), Integer.toString(fake.number().numberBetween(1, 53)));
	}

	public void fillZipCode() {
		write(By.id("postcode"), Long.toString(fake.number().randomNumber(5, true)));
	}

	public void fillCountry() {
		selectComboByValue(By.id("id_country"), Integer.toString(fake.number().numberBetween(21, 21)));
	}

	public void fillAdditionalInformation() {
		write(By.id("other"), fake.lorem().paragraph(2));
	}

	public void fillHomePhone() {
		write(By.id("phone"), fake.phoneNumber().subscriberNumber());
	}

	public void fillMobilePhone() {
		write(By.id("phone_mobile"), fake.phoneNumber().cellPhone());
	}

	public void fillAddressReference() {
		clearTextField(By.id("alias"));
		write(By.id("alias"), fake.lorem().characters(32));
	}

	public void clickOnRegister() {
		click(By.id("submitAccount"));
	}
}
