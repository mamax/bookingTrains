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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.opera.core.systems.OperaDriver;

/**
 * @author maksym.mazurkevych
 * 
 */
public class BaseTest {

	private static boolean isBrowserOpened = false;
	public static Connection conn;
	public static Properties CONFIG = null;
	public static Properties OR = null;
	public static WebDriver dr=null;
	public static EventFiringWebDriver driver=null;
	public static String path = System.getProperty("user.dir") + "\\screenshots\\";
	private boolean isInitalized;
	public static DesiredCapabilities capability=null;
	

	
	@BeforeTest
	protected void initializeProperties() throws Exception {

		if (!isInitalized) {
			CONFIG = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//ua//gov//uz//config//config.properties");
			CONFIG.load(ip);
			System.out.println("reading properties from file");
//			OR = new Properties();
//			FileInputStream ip2 = new FileInputStream(
//					System.getProperty("user.dir")+ "//src//ua//gov//uz//resources//OR.properties");		
//			OR.load(ip2);
			isInitalized = true;

		}
	}

	
	
	public static WebDriver setUp() {
			
	if (!isBrowserOpened) {
		
		if (CONFIG.getProperty("browserType").equals("MOZILLA")) {
			dr = new FirefoxDriver();
			System.out.println("Opening browser");
			isBrowserOpened = true;
		} 
		else if (CONFIG.getProperty("browserType").equals("IE")){
			
			System.setProperty("webdriver.ie.driver", "c:\\jars-core\\IEDriverServer.exe");
			dr = new InternetExplorerDriver();
		}
		
		else if (CONFIG.getProperty("browserType").equals("OPERA")){
					
					dr = new OperaDriver();
					}
		
		else if (CONFIG.getProperty("browserType").equals("CHROME")) {
			System.setProperty("webdriver.chrome.driver", "c:\\jars-core\\chromedriver.exe");
			dr = new ChromeDriver();
			System.out.println("Opening browser");
			isBrowserOpened = true;
		}
		isBrowserOpened = true;
	
//		driver = new EventFiringWebDriver(dr);
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
		driver.manage().window().maximize(); 
	
	}
			return driver;
	}
	
	
	@BeforeMethod
	@Parameters({"browser"})
	public static WebDriver setUp(@Optional("firefox")String browser) {
		
		if (!isBrowserOpened) {
			
			if (browser.equalsIgnoreCase("firefox")) {
				dr = new FirefoxDriver();
				System.out.println("Opening browser");
				isBrowserOpened = true;
			} 
			else if (browser.equalsIgnoreCase("ie")){
				
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +"\\libs\\IEDriverServer.exe");
				dr = new InternetExplorerDriver();
			}
			
			else if (browser.equalsIgnoreCase("opera")){
						
						dr = new OperaDriver();
						}
			
			else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "\\libs\\chromedriver.exe");
				dr = new ChromeDriver();
				System.out.println("Opening browser");
				isBrowserOpened = true;
			}
			isBrowserOpened = true;
		
			driver = new EventFiringWebDriver(dr);
			driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
//			driver.manage().window().maximize(); 
		
		}
				return driver;
		}
	
	
//	remote webdriver
//	public static  WebDriver  setUpRemote(){
//	@BeforeMethod
//	@Parameters({"browser"})
	public static  WebDriver setUpRemote(String browser) throws Exception {
		
		if (!isBrowserOpened) {
		
		 if(browser.equalsIgnoreCase("firefox")){
	            System.out.println("Opening firefox");
	              capability= DesiredCapabilities.firefox();
	              capability.setJavascriptEnabled(true);
	             capability.setBrowserName("firefox");
	          capability.setPlatform(org.openqa.selenium.Platform.ANY);
	              //capability.setVersion("7.0.1");
	          }
	        
	           if(browser.equalsIgnoreCase("ie")){
	              System.out.println("opening internet explorer");
	              System.setProperty("webdriver.ie.driver", "c:\\jars-core\\IEDriverServer.exe");
	              capability= DesiredCapabilities.internetExplorer();
	              capability.setJavascriptEnabled(true);
	               capability.setBrowserName("internet explorer");
	            //capability.setPlatform(org.openqa.selenium.Platform.ANY);
	              //capability.setVersion("");
	           }
	           
	           if(browser.equalsIgnoreCase("chrome")){
	        	   System.out.println("Opening chrome");
		              System.setProperty("webdriver.chrome.driver", "c:\\jars-core\\chromedriver.exe");
//		              browser.add(setupDriver(new ChromeDriver()));
		              capability= DesiredCapabilities.chrome();
		              capability.setJavascriptEnabled(true);
		               capability.setBrowserName("chrome");
		            capability.setPlatform(org.openqa.selenium.Platform.LINUX);
		            capability.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
		            HashMap<String, String> chromePreferences = new HashMap<String, String>();
		            chromePreferences.put("profile.password_manager_enabled", "false");
		            capability.setCapability("chrome.prefs", chromePreferences);
		              capability.setVersion("");
		           }
	           
	           if(browser.equalsIgnoreCase("opera")){
		              System.out.println("Opening opera");
		              capability= DesiredCapabilities.opera();
		              capability.setJavascriptEnabled(true);		              capability.setCapability("opera.arguments", "-nowin -nomail");
		              capability.setPlatform(org.openqa.selenium.Platform.ANY);
		            capability.setPlatform(org.openqa.selenium.Platform.ANY);
		              capability.setVersion("11.15");
		           }
	 
//		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
		isBrowserOpened = true;
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
	
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
	
	//for remote webdriver
	public static void CaptureScreenwithOutServer(String fileName) throws IOException {

		// System.out.println("path " + path);

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(path + fileName + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
