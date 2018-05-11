package ua.gov.uz.webdriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class BrowserConf{

    protected static WebDriver driver = null;
    protected static String bro;
    protected static boolean isBrowserOpened = false;

//    @BeforeMethod(alwaysRun = true)
//    @Parameters({"browser"})
//    public WebDriver setUp(@Optional("htmlunit") String browser) throws IOException, InterruptedException {
//        if (!isBrowserOpened) {
//
//            bro = browser;
//
//            switch (browsers.valueOf(browser)) {
//                case debug:
//                    dr = new FirefoxDriver(getProfileForDebug());
//                    break;
//
//                case firefox:
//                    dr = new FirefoxDriver();
//                    break;
//
//                case chrome:
//                    System.setProperty("webdriver.chrome.driver", getBrowserPath("chromedriver.exe"));
//                    dr = new ChromeDriver();
//                    break;
//
//                case ie:
//                    System.setProperty("webdriver.ie.driver", getBrowserPath("IEDriverServer.exe"));
//                    dr = new InternetExplorerDriver(/*capabilities*/);
//                    break;
//
//                case htmlunit:
//                    dr = new HtmlUnitDriver();
//                    break;
//
//                default:
//                    dr = new FirefoxDriver();
//
//            }
//            isBrowserOpened = true;
//
//            driver = new EventFiringWebDriver(dr);
//
//            driver.manage().window().setSize(new Dimension(1024, 1024));
//            System.out.println("Dimension : " + driver.manage().window().getSize());
//            driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
//            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//        }
//
//        return driver;
//    }

    @BeforeTest
    public static void setupClass() {
        ChromeDriverManager.getInstance().setup();
    }

    @BeforeClass
    public static WebDriver setUp() throws IOException {
        if (!isBrowserOpened) {

            driver = new ChromeDriver();
            System.out.println("Opening Chrome");
            isBrowserOpened = true;
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
            driver.manage().window().setSize(new Dimension(1500, 1300));
        }

        return driver;
    }

    private static FirefoxProfile getProfileForDebug() throws IOException {
        FirefoxProfile fxProfile = new FirefoxProfile();
        fxProfile.addExtension(new File(System.getProperty("user.dir") + "//src//main//resources//firebug-2.0.16.xpi"));
        fxProfile.addExtension(new File(System.getProperty("user.dir") + "//src//main//resources//firepath-0.9.7.1-fx.xpi"));
        return fxProfile;
    }


    public String getBrowserPath(String fileName) throws IOException {
        return new File(".").getCanonicalPath()+ File.separator + "src"+ File.separator +"main"+ File.separator +"resources"+ File.separator + "browsers"+ File.separator +
                fileName ;
    }

}
