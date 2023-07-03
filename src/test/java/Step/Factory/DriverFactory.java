package Step.Factory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class DriverFactory {
    private static WebDriver driver;



    public static WebDriver initializeDriver()

    {   ChromeOptions options=new ChromeOptions();
        options.addArguments("--disable-notifivations");
        options.addArguments("--disable-popup-blocking");
        driver = new ChromeDriver(options);
        //driver.get("https://www.flipkart.com/");
        driver.manage().window().maximize();
        return driver;
    }
    public static WebDriver getDriver(){
        return driver;
    }

}
