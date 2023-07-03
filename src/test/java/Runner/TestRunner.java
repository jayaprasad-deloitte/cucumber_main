package Runner;


import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)@CucumberOptions(
        features = {"src/test/java/Feature/Flipkart.feature"}, glue={"Step","Hooks"},monochrome = true,
        plugin={"html:C:\\Users\\masprasad\\eclipse-workspace\\Flipkart_cucumber_Automation\\src\\test\\java\\Reports\\Flipkart_cucumber.html","json:target/cucumber.json"})
public class TestRunner {


}

