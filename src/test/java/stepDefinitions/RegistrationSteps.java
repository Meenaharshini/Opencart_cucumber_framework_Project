package stepDefinitions;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.*;
import pageObjects.*;

public class RegistrationSteps {
	WebDriver driver;
	public AccountRegistrationPage ap;
	public HomePage hp;
	
	@Given("user navigate to MyAccount page")
	public void user_navigate_to_my_account_page() {
		BaseClass.getLogger().info("Navigated to Account Page---");
		hp = new HomePage(BaseClass.getDriver());
		hp.clickMyAccount();
		hp.clickRegister();
	}

	@When("user enters details into below fields")
	public void user_enters_details_into_below_fields(io.cucumber.datatable.DataTable dataTable) {
	    BaseClass.getLogger().info("Provided user details");
	    ap = new AccountRegistrationPage(BaseClass.getDriver());
	    ap.setFirstName(dataTable.cell(0, 1));
	    ap.setLastName(dataTable.cell(1,1));
	    ap.setEmail(dataTable.cell(2, 1));
	    ap.setPassword(dataTable.cell(3, 1));
	}

	@When("click privacy policy")
	public void click_privacy_policy() {
	    ap.setPrivacyPolicy();
	}

	@When("click continue button")
	public void click_continue_button() {
	    BaseClass.getLogger().info("Clicked on continue button-----");
	    ap.clickContinue();
	}

	@Then("user should see the confirmation message {string}")
	public void user_should_see_the_confirmation_message(String expected) {
	    String actual = ap.getConfirmationMsg();
	    try {
		    if(actual.equals(expected)) {
		    	BaseClass.getLogger().info("Account Registration Successful-----");
		    	Assert.assertTrue(true);
		    }
	    }catch(Exception e) {
	    	BaseClass.getLogger().info("Account Registration Unsuccessful-----");
	    	Assert.assertTrue(false);
	    }
	}
	
	//Register account randomly.
	@When("user enters details \\(firstname: {string}, lastname: {string}, email: {string}, password:{string})")
	public void user_enters_details_firstname_lastname_email_password(String fname, String lname, String email, String password) {
		BaseClass.getLogger().info("Provided user details");
		ap = new AccountRegistrationPage(BaseClass.getDriver());
		
		fname = BaseClass.randomString();
		ap.setFirstName(fname);
		
		lname = BaseClass.randomString();
		ap.setLastName(lname);
		
		email = BaseClass.randomString()+"@gmail.com";
		ap.setEmail(email);
		
		password = BaseClass.randomAlphaNumeric();
		ap.setPassword(password);
		
		ap.setPrivacyPolicy();
		ap.clickContinue();
	}


}
