package stepDefinitions;



import factory.BaseClass;

import java.util.*;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReaderExcel;

public class LoginSteps {
	WebDriver driver;
	public HomePage hp;
	public LoginPage lp;
	public MyAccountPage ap;
	
	//Data driven with Excel
	List<HashMap<String,String>> excelData;
	
	@Given("user navigate to Login Page")
	public void user_navigate_to_Login_Page() {
	    BaseClass.getLogger().info("Clicked on Login Menu");
	    hp = new HomePage(BaseClass.getDriver());
	    hp.clickMyAccount();
	    hp.clickLogin();
	}

	@When("user enters credentials \\(email: {string}, password:{string})")
	public void user_enters_credentials_email_password(String uname, String pwd) {
		BaseClass.getLogger().info("Provided email and password");
	    lp = new LoginPage(BaseClass.getDriver());
	    lp.setUsername(uname);
	    lp.setPassword(pwd);
	    
	}

	@When("click on login button")
	public void click_on_login_button() {
		BaseClass.getLogger().info("Clicked on Login button ");
		lp.btnLogin();
	}

	@Then("user navigates to MyAccount Page")
	public void user_navigates_to_my_account_page() {
	    ap = new MyAccountPage(BaseClass.getDriver());
	    boolean status = ap.isMyAccountPageExists();
	    //Assert.assertEquals(status, true);
	    try {
		    if(status==true) {
		    	BaseClass.getLogger().info("Login Success");
		    	Assert.assertTrue(true);
		    }
	    }catch(AssertionError e) {
		    
		    	BaseClass.getLogger().info("Login Failure : "+e.getMessage());
		    	Assert.assertFalse(true);
		    }
	}
	
	@Then("user should be on the Login page and verify title page {string}")
	public void user_should_be_on_the_Login_page_and_verify_title_page(String title) {
		boolean status = lp.isTitleDisplayed();
		try {
			if(status) {
				BaseClass.getLogger().info("Title is displayed");
				Assert.assertTrue(true);
			}
				
		}catch(AssertionError e) {
			BaseClass.getLogger().info("Title is not displayed");
			Assert.assertTrue(false);
		}
	}
	
	//*************** Data Driven Test *************** //
	@Then ("user navigates to MyAccount Page by passing email and password with excel row {string}")
	public void user_navigates_to_MyAccount_Page_by_passing_email_and_password_with_excel_row(String rows) throws Exception {
	
		excelData = DataReaderExcel.data(System.getProperty("user.dir")+"\\testData\\Opencart_LoginData.xlsx", "Sheet1");
		
		//Find rows from feature file and extract the value from excel.
		int rowIndex = Integer.parseInt(rows)-1;
		String username = excelData.get(rowIndex).get("Username");
		String password = excelData.get(rowIndex).get("Password");
		String result = excelData.get(rowIndex).get("Result");
		
		//Providing details using login page class
		lp = new LoginPage(BaseClass.getDriver());
		BaseClass.getLogger().info("Provided username and password");
		lp.setUsername(username);
		lp.setPassword(password);
		lp.btnLogin();
		BaseClass.getLogger().info("Clicked Login button");
		
		ap =new  MyAccountPage(BaseClass.getDriver());
		try {
			boolean actual = ap.isMyAccountPageExists();
			System.out.println("Target Page is : "+actual);
			
			if(result.equals("VALID")) {
				if(actual==true) {
					BaseClass.getLogger().info("Logout the page----");
					ap.clickLogout();
					Assert.assertTrue(true);
				}
				else {
					BaseClass.getLogger().info("Unale to Login the page----");
				}
			}
			else if(result.equals("INVALID")) {
				if(actual == true) {
					BaseClass.getLogger().info("Logged in ----");
					ap.clickLogout();
					Assert.assertTrue(false);
				}
				else {
					BaseClass.getLogger().info("Unale to Login the page----");
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e) {
			Assert.fail();
		}
	}



}
