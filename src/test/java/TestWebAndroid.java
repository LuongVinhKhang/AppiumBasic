import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestWebAndroid extends TestBaseline {

    @BeforeClass
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setAutomationName("UiAutomator2")
                .setPlatformVersion("15")
                .setDeviceName("emulator-5554")
                .setUdid("emulator-5554")
                .setNewCommandTimeout(Duration.ofSeconds(30));
        options.setCapability("browserName", "Chrome");

        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void navigateToURL() {
        driver.navigate().to("https://magento.softwaretestingboard.com/push-it-messenger-bag.html");
    }

    @Test(dependsOnMethods = "navigateToURL")
    public void clickOnAddToCartButton() {
        WebElement addToCartButton = driver.findElement(AppiumBy.cssSelector("button#product-addtocart-button"));
        driver.executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        addToCartButton.click();
    }

    @Test(dependsOnMethods = "clickOnAddToCartButton")
    public void goToCart() {
        driver.findElement(AppiumBy.xpath("//div[@data-block='minicart']")).click();
    }

    @AfterClass
    public void tearDown(ITestContext context) {
        for (ITestResult result : context.getPassedTests().getAllResults()) {
            if (result.getStatus() == ITestResult.FAILURE) {
                String testName = result.getName();
                ScreenshotUtil.takeScreenshot(driver, testName);
            }
        }
        // Quit the driver
        if (driver != null) {
            driver.quit();
        }
    }
}
