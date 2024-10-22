import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
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
                .setNewCommandTimeout(Duration.ofSeconds(30));
        options.setCapability("browserName", "Chrome");

        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void test() {
        driver.navigate().to("https://magento.softwaretestingboard.com/push-it-messenger-bag.html");
        WebElement addToCartButton = driver.findElement(AppiumBy.cssSelector("button#product-addtocart-button"));
        driver.executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        addToCartButton.click();
        Assert.assertTrue(driver.findElement(AppiumBy.xpath("//div[@data-block='minicart']")).isDisplayed());
        driver.findElement(AppiumBy.xpath("//div[@data-block='minicart']")).click();
    }
}
