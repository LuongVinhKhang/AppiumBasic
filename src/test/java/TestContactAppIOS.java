import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestContactAppIOS extends TestBaseline {

    @BeforeClass
    public void setUp() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions()
                .setUdid("8982FE73-D834-457D-A0F6-879475B63C5A")
                .setNewCommandTimeout(Duration.ofSeconds(30))
                .setApp("com.apple.MobileAddressBook");

        driver = new IOSDriver(new URL(APPIUM_SERVER_URL), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void test() {
        driver.findElement(AppiumBy.accessibilityId("Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("All iPhone")).click();
        driver.findElement(AppiumBy.iOSNsPredicateString("name == \"Add\"")).click();
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`label == \"First name\"`]")).sendKeys("John");
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == \"Last name\"`]")).sendKeys("Doe");
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == \"Company\"`]")).sendKeys("Apple Inc.");
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeCell[`label == \"add phone\"`]")).click();
        driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`name == \"mobile\"`]")).sendKeys("1234567890");
        driver.findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name=\"Done\"]")).click();
    }
}
