package stepDefinitions;

import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {
	public WebDriver driver;
	public Properties p;
	
	@Before
	public void setup() throws Exception {
		driver = BaseClass.initializeBrowser();
		p = BaseClass.getProperties();
		driver.manage().window().maximize();
		driver.get(p.getProperty("appURL"));
	}
	
	@After
	public void tearDown(Scenario scenario) {
		driver.quit();
	}
	
	@AfterStep
	public void captureScreenshot(Scenario scenario) {
		System.out.println("Scenario status ====> "+scenario.getStatus());
		if(scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot,"image/png", scenario.getName());
		}
	}
}
