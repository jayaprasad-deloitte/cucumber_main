package Step;

import Step.Factory.DriverFactory;
import Utilities.ExcelReader;
import io.cucumber.java.an.E;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.*;



public class FlipkartTravel {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(FlipkartTravel.class);


    @Given(": I Launch Flipkart website")
    public void i_launch_flipkart_website() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.flipkart.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("*********Scenario1 launching Flipkart website**********");
    }

    @When(": I Navigate to Travel section")
    public void i_navigate_to_travel_section() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            WebElement popup = driver.findElement(By.xpath("//button[@class=\"_2KpZ6l _2doB4z\"]"));
            boolean popup_present = popup.isDisplayed();
            popup.click();
            driver.findElement(By.xpath("//*[text()=\"Travel\"]")).click();
            logger.info("********In travel page********");
        } catch (Exception e) {

            driver.findElement(By.xpath("//*[text()=\"Travel\"]")).click();
            logger.info("********In travel page********");
        }
        driver.findElement(By.xpath("//*[text()=\"Travel\"]")).click();
        logger.info("********In travel page********");


    }


    @When(": Fill all required details and From city {string} and To city {int}")
    public void fill_all_required_details_and_from_city_and_to_city(String sheetName, Integer rowNumber) throws IOException, InvalidFormatException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        logger.info("**********taking data from excel file*****");
        //reading data from excel file
        ExcelReader reader = new ExcelReader();
        String path = "C:\\Users\\masprasad\\eclipse-workspace\\Flipkart_cucumber_Automation\\src\\test\\Flipkart_cucumber_Automation.xlsx";
        List<Map<String, String>> testdata = reader.getData(path, "Sheet1");
        String FROM = testdata.get(rowNumber).get("From");
        String TO = testdata.get(rowNumber).get("To");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);




        WebElement round_trip = driver.findElement(By.xpath("(//input[@class=\"_3DAmyP\"])[2]//following-sibling::div[1]"));
        round_trip.click();
        WebElement From = driver.findElement(By.xpath("(//div[@class=\"_3qBKP_ _1Jqgld\"]/input)[1]"));
        From.sendKeys(FROM);
        driver.findElement(By.xpath("//div[text()=\"MAA\"]")).click();
        WebElement To = driver.findElement(By.xpath("(//div[@class=\"_3qBKP_ _1Jqgld\"]/input)[2]"));
        To.sendKeys(TO);
        ;
        WebElement To_click = driver.findElement(By.xpath("(//div[text()=\"BOM\"])[2]"));
        To_click.click();

        WebElement From_date=driver.findElement(By.xpath("//button[@class='jkj0H4 _2v-mAi _3vGgRD']//div[1]"));
        From_date.click();
        WebElement To_Date=driver.findElement(By.xpath("//button[@class='jkj0H4 _2v-mAi _3vGgRD']//div[1]"));
        To_Date.click();
        WebElement Travel_class=driver.findElement(By.xpath("(//button[@class=\"_2KpZ6l _34K0qG _37Ieie\"])[1]"));
        Travel_class.click();
        WebElement cabin_classes=driver.findElement(By.xpath("(//div[@class=\"_1XFPmK\"])[3]"));
        cabin_classes.click();
        WebElement Done=driver.findElement(By.xpath("//button[@class=\"aC49_F _14Me7y\"]"));
        Done.click();
        WebElement Search=driver.findElement(By.xpath("//button[@class=\"_2KpZ6l _1QYQF8 _3dESVI\"]"));
        Search.click();

        logger.info("***********enter all required flight details**************");
    }

    @Then(": Verify Total price with sum on individual flight price")
    public void verify_total_price_with_sum_on_individual_flight_price() {
        try {
            String from_price_ = driver.findElement(By.xpath("(//div[@class=\"_1YM4IL _2xRto2\"]//div[2]//div[@class=\"_1AhVAh\"])[1]")).getText();
            String from_price = from_price_.replaceAll("[^\\d]", "");
            int int_from_price = Integer.parseInt(from_price);

            String to_price_ = driver.findElement(By.xpath("(//div[@class=\"_1YM4IL _2xRto2\"]//div[2]//div[@class=\"_1AhVAh\"])[2]")).getText();
            String to_price = to_price_.replaceAll("[^\\d]", "");
            int int_to_price = Integer.parseInt(to_price);

            String Total_price_ = driver.findElement(By.xpath("//span[@class=\"_1YryzD\"]")).getText();
            String total_price = Total_price_.replaceAll("[^\\d]", "");
            int int_total_price = Integer.parseInt(total_price);

            int check_failure = 7;

            //assert int_total_price == int_from_price + int_to_price; for positive test
            // assert int_total_price=check_failure+int_to_price; for negative case
            if (int_total_price == int_from_price + int_to_price) {
                assert true;
                System.out.println("Test Passed");
                logger.info("************Test case passed payment verified*******");
            } else {
                logger.error("**************Test Case failed ********");
                File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File("C:\\Users\\masprasad\\eclipse-workspace\\Flipkart_cucumber_Automation\\src\\test\\Screenshot\\screenshot.png"));
                System.out.println("Test Failed");
                assert false;

            }

        } catch (Exception E) {
            String flight_not_available = driver.findElement(By.xpath("'//div[@class=\"_2Tpdn3 _35R-El\"]")).getText();
            System.out.println(flight_not_available);
            logger.debug("*******Flight are not available************");

        }

    }
}