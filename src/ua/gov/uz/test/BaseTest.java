/**
 * 
 */
package ua.gov.uz.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

/**
 * @author maksym.mazurkevych
 *
 */
public class BaseTest {
	
	private boolean isBrowserOpened = false;
	public static Connection conn;
	public static WebDriver dr=null;
	public static RemoteWebDriver driver=null;
	public static Properties CONFIG = null;
	public static Properties OR = null;
	public static String path = System.getProperty("user.dir")+"\\screenshots\\";
	
	
	
	protected  WebDriver getWebdriver(){
		
		if (!isBrowserOpened){
			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			isBrowserOpened = true;
			System.out.println("Opening browser");
			}
		
		
		return driver;	
	}
	
	
	@AfterMethod
	public void tearDown(){
		if (isBrowserOpened){
		System.out.println("Closing browser");
		driver.quit();
		isBrowserOpened=false;
		}
		
	}
	
	
	
public static void CaptureScreenshot(String fileName) throws IOException {
		
		
//		System.out.println("path " + path);
		
			
	try {
		
	    WebDriver augmentedDriver = new Augmenter().augment(driver);
	    File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
	    
	    FileUtils.copyFile(source, new File(path  + fileName+".jpg")); 
	}
	catch(IOException e) {
		path = "Failed to capture screenshot: " + e.getMessage();
	}
	
	}


}
