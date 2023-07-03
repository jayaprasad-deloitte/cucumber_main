package Hooks;

import Step.Factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Hooks {
    private WebDriver driver;


    @Before
    public void before()
    {

        driver= DriverFactory.initializeDriver();
    }

    @After
    public void captureScreenshot(Scenario Flipkart)
    {
         Flipkart.log("Pass");

        if(Flipkart.isFailed())
            Flipkart.log("Failed");
        {
            TakesScreenshot scrnshot=(TakesScreenshot)driver;
            byte[]data=scrnshot.getScreenshotAs(OutputType.BYTES);
            Flipkart.attach(data,"image/png","Failure_screenshot");
        }
        driver.close();
    }
}
