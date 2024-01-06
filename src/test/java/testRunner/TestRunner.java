package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions( 
					features = {".//FeatureFiles/"},
							//".//FeatureFiles/LoginPage.feature",".//FeatureFiles/RegistrationPage.feature"},
							//".//FeatureFiles/LoginDDTExcel.feature"}, 
					glue = "stepDefinitions" ,
					plugin = {
								"pretty", 
								"html:reports/cucumberReport.html", 
								"json:reports/cucumberReport.json",
								 "rerun:target/rerun.txt",
								 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
							},
					dryRun = false,
					monochrome = true
					//publish = true
					//tags = "@sanity"
					//tags = "@sanity and @smoke"
					//tags = "@sanity or @regression"
					//tags = "@sanity and @smoke or @regression"
				)

public class TestRunner {

}
