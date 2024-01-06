package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	//Locators
	@FindBy(id = "input-email")
	WebElement txtUsername;
	
	@FindBy(id = "input-password")
	WebElement txtPassword;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement btnLogin;
	
	@FindBy(xpath = "//title[normalize-space()='Account Login']")
	WebElement titleName;
	
	//Action methods.
	public void setUsername(String username) {
		txtUsername.sendKeys(username);
	}
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	public void btnLogin() {
		btnLogin.click();
	}
	public boolean isTitleDisplayed() {
		try {
			return titleName.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}		
	}
}
