import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TestContactAppAndroid extends TestBaseline {

    @BeforeClass
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setAutomationName("UiAutomator2")
                .setPlatformVersion("15")
                .setDeviceName("emulator-5554")
                .setUdid("emulator-5554")
                .setNewCommandTimeout(Duration.ofSeconds(30))
                .setAppPackage("com.google.android.contacts")
                .setAppActivity("com.android.contacts.activities.PeopleActivity");

        driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test(priority = 1)
    public void clickOnAddButton() {
        driver.findElement(AppiumBy.accessibilityId("Create contact")).click();
    }

    @Test(dependsOnMethods = "clickOnAddButton")
    public void inputNewContactInfo() {
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='First name']")).sendKeys("John");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Last name']")).sendKeys("Doe");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Company']")).sendKeys("Apple Inc.");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Phone']")).sendKeys("1234567890");
    }

    @Test(dependsOnMethods = "inputNewContactInfo")
    public void clickOnSaveButton() {
        driver.findElement(AppiumBy.id("com.google.android.contacts:id/menu_save")).click();
    }
}
