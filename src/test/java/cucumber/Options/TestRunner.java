package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",  // ✅ Correct path
        glue = "stepDefinitions", 
        tags = "@Getasinglecart",
        plugin = {"pretty", "html:target/cucumber-reports/html", "json:target/cucumber-reports/cucumber.json","rerun:target/failed_scenarios.txt"}
)
public class TestRunner {
    // Empty class - used to run Cucumber tests
}
