package ua.gov.uz.webdriver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author maksym.mazurkevych
 * 
 */
public class BaseTest extends BrowserConf {

	private boolean isInitialized;
	public static Properties CONFIG = null;

	protected void initializeProperties() throws Exception {

		if (!isInitialized) {
			CONFIG = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir")
							+ "//src//main//resources//config.properties");
			CONFIG.load(ip);
			System.out.println("reading properties from file");
			// OR = new Properties();
			// FileInputStream ip2 = new FileInputStream(
			// System.getProperty("user.dir")+
			// "//src//ua//gov//uz//resources//OR.properties");
			// OR.load(ip2);
			isInitialized = true;
		}
	}

	@AfterClass
	public void tearDown() {
		if (isBrowserOpened) {
			System.out.println("Closing browser");
			driver.quit();
			isBrowserOpened = false;
		}
	}

	public static void CaptureScreenshot(String fileName) throws IOException {
    String path = System.getProperty("user.dir") + File.separator + "target" + File.separator + "surefire-reports" + File.separator + "html" + File.separator + fileName + ".jpg";
		System.out.println("pictures place " + path);

		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
