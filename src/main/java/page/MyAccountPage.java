package page;

import org.openqa.selenium.By;

import core.BasePage;

public class MyAccountPage extends BasePage {
	public void returnToHome() {
		click(By.xpath("a[@title=Home]"));
	}
}
