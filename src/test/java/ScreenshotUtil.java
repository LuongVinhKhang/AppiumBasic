import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static void takeScreenshot(WebDriver driver, String className) {
        // Capture the screenshot as a temporary file
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Generate a timestamp for the screenshot
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Define the destination file path with class name and timestamp
        File destFile = new File("screenshots/" + className + "_" + timestamp + ".png");

        try {
            // Ensure the directory exists
            if (!destFile.getParentFile().mkdirs() && !destFile.getParentFile().exists()) {
                throw new IOException("Failed to create directories for " + destFile.getAbsolutePath());
            }
            // Copy the screenshot to the destination file
            Files.copy(srcFile.toPath(), destFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}