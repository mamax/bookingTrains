/**
 * 
 */
package ua.gov.uz.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

/**
 * @author maksym.mazurkevych
 * 
 */
public class BaseTest {

	private static boolean isBrowserOpened = false;
	public static Connection conn;
	public static WebDriver dr = null;
	public static WebDriver driver = null;
	public static Properties CONFIG = null;
	public static Properties OR = null;
	public static String path = System.getProperty("user.dir")
			+ "\\screenshots\\";
	private boolean isInitalized;

	
	
	

	
	@BeforeMethod
	public static WebDriver setUp() {
		
		if (!isBrowserOpened) {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		isBrowserOpened = true;
		System.out.println("Opening browser");
		}
		return driver;
	}

	
	@AfterMethod
	public void tearDown() {
		
		if (isBrowserOpened) {
			driver.quit();
			System.out.println("Closing browser");
			isBrowserOpened = false;
		}

	}

	public static void CaptureScreenshot(String fileName) throws IOException {

		// System.out.println("path " + path);

		try {

			WebDriver augmentedDriver = new Augmenter().augment(driver);
			File source = ((TakesScreenshot) augmentedDriver)
					.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(source, new File(path + fileName + ".jpg"));
		} catch (IOException e) {
			path = "Failed to capture screenshot: " + e.getMessage();
		}

	}

}
