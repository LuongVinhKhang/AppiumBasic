import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.safari.SafariDriver;
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
                .setCommandTimeouts(Duration.ofSeconds(30))
                .setNewCommandTimeout(Duration.ofSeconds(30));
        options.setCapability("browserName", "Safari");

        driver = new SafariDriver(new URL(APPIUM_SERVER_URL), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void test() {
        driver.navigate().to("https://magento.softwaretestingboard.com/push-it-messenger-bag.html");
        driver.findElement(AppiumBy.cssSelector("button#product-addtocart-button")).click();
        driver.findElement(AppiumBy.xpath("//div[@data-block='minicart']")).click();
    }
}
