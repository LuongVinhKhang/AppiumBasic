import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestWebIOS extends TestBaseline {

    @BeforeClass
    public void setUp() throws MalformedURLException {

        XCUITestOptions options = new XCUITestOptions()
                .setAutomationName("XCUITest")
                .setPlatformVersion("18.0")
                .setDeviceName("iPhone 16")
                .setUdid("8982FE73-D834-457D-A0F6-879475B63C5A")
                .setCommandTimeouts(Duration.ofSeconds(30))
                .setNewCommandTimeout(Duration.ofSeconds(30));
        options.setCapability("browserName", "Safari");

        driver = new SafariDriver(new URL(APPIUM_SERVER_URL), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void navigateToURL() {
        driver.navigate().to("https://magento.softwaretestingboard.com/push-it-messenger-bag.html");
    }

    @Test(dependsOnMethods = "navigateToURL")
    public void clickOnAddToCartButton() {
        driver.findElement(AppiumBy.cssSelector("button#product-addtocart-button")).click();
    }

    @Test(dependsOnMethods = "clickOnAddToCartButton")
    public void goToCart() {
        driver.findElement(AppiumBy.xpath("//div[@data-block='minicart']")).click();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
