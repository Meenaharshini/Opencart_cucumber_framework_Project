package factory;

import java.io.FileReader;
import java.net.URI;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {
	static WebDriver driver;
	static Logger logger;
	static Properties p;

	public static WebDriver initializeBrowser() throws Exception {
		//Checking the environment -- remote or local.
		if (getProperties().getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			// checking os
			if (getProperties().getProperty("os").equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if (getProperties().getProperty("os").equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("No Matching OS------");
			}

			// checking browser
			switch (getProperties().getProperty("browser").toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("edge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("No Browser Matching-----");
			}

			driver = new RemoteWebDriver(new URI("http://localhost:4444/wd/hub").toURL(), capabilities);
		}

		else if (getProperties().getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (getProperties().getProperty("browser").toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("No Browser Matching-----");
			}
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		
		return driver;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static Properties getProperties() throws Exception {
		//C:\Users\Meena\eclipse-workspace\Cuumber_Opencart_Project\src\test\resources\config.properties
		String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";
		try(FileReader file = new FileReader(filepath)){
			p = new Properties();
			p.load(file);
		}catch(Exception e) {
			throw e;
		}
		return p;
	}

	public static Logger getLogger() {
		logger = LogManager.getLogger();
		return logger;
	}

	public static String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public static String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public static String randomAlphaNumeric() {
		String generatedAlphaNumeric = RandomStringUtils.randomAlphanumeric(15);
		return generatedAlphaNumeric;
	}
}
