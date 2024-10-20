import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;

public class TestBaseline {
    protected final String APPIUM_SERVER_URL = "http://127.0.0.1:3434";
    protected AppiumDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        System.out.println("SETUP");
    }

    @AfterClass
    public void tearDown(ITestContext context) {
        System.out.println("TEARDOWN");
        // Get the class name from the context
        String className = this.getClass().getSimpleName();

        // Check if the test class has failed tests
        if (!context.getFailedTests().getAllResults().isEmpty()) {
            ScreenshotUtil.takeScreenshot(driver, className);
        }

        // Quit the driver
        if (driver != null) {
            driver.quit();
        }
    }
}
