package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotHelper {
    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        Path path = Paths.get("src/main/resources/screenshots", screenshotName + ".png");
        try {
            Files.createDirectories(path.getParent());
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshotFile.toPath(), path);
            return path.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}