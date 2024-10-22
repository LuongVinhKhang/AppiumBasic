import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.safari.SafariDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestWebIOS extends TestBaseline {

    @BeforeClass
    public void setUp() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions()
                .setUdid("8982FE73-D834-457D-A0F6-879475B63C5A")
                .setNewCommandTimeout(Duration.ofSeconds(30))
                .withBrowserName("Safari");

        driver = new SafariDriver(new URL(APPIUM_SERVER_URL), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void test() {
        driver.get("https://magento.softwaretestingboard.com/push-it-messenger-bag.html");

        driver.findElement(By.id("product-addtocart-button")).click();

        driver.findElement(By.cssSelector("div.messages a")).click();
        Assert.assertTrue(driver.findElement(AppiumBy.xpath(
                        "//table[@id='shopping-cart-table']" +
                                "//tr[@class='item-info' and " +
                                ".//strong[@class='product-item-name']" +
                                "//a[text()='Push It Messenger Bag']]")).isDisplayed(),
                "Product not found in cart");
    }
}
