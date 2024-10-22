import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestWebAndroid extends TestBaseline {

    @BeforeClass
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("emulator-5554")
                .setNewCommandTimeout(Duration.ofSeconds(30))
                .withBrowserName("Chrome");

        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void test() {
        driver.get("https://magento.softwaretestingboard.com/push-it-messenger-bag.html");

        WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
        driver.executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        addToCartButton.click();

        driver.findElement(By.cssSelector("div.messages a")).click();
        Assert.assertTrue(driver.findElement(AppiumBy.xpath(
                        "//table[@id='shopping-cart-table']" +
                                "//tr[@class='item-info' and " +
                                ".//strong[@class='product-item-name']" +
                                "//a[text()='Push It Messenger Bag']]")).isDisplayed(),
                "Product not found in cart");
    }
}
