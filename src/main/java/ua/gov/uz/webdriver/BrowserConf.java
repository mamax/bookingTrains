package ua.gov.uz.webdriver;

import com.opera.core.systems.OperaDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BrowserConf{

    public static EventFiringWebDriver driver = null;
    protected WebDriver dr;
    protected static String bro;
    protected static boolean isBrowserOpened = false;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public WebDriver setUp(@Optional("htmlunit") String browser) throws IOException, InterruptedException {
        if (!isBrowserOpened) {

            bro = browser;

            switch (browsers.valueOf(browser)) {
                case debug:
                    dr = new FirefoxDriver(getProfileForDebug());
                    break;

                case firefox:
                    dr = new FirefoxDriver();
                    break;

                case chrome:
                    System.setProperty("webdriver.chrome.driver", getBrowserPath("chromedriver.exe"));
                    dr = new ChromeDriver();
                    break;

                case ie:
                    System.setProperty("webdriver.ie.driver", getBrowserPath("IEDriverServer.exe"));
                    dr = new InternetExplorerDriver(/*capabilities*/);
                    break;

                case htmlunit:
                    dr = new HtmlUnitDriver();
                    break;

                default:
                    dr = new FirefoxDriver();

            }
            isBrowserOpened = true;

            driver = new EventFiringWebDriver(dr);
            driver.manage().deleteAllCookies();
            driver.manage().window().setSize(new Dimension(1024, 1024));
            System.out.println("Dimension : " + driver.manage().window().getSize());
            driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
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


    public enum browsers
    {
        firefox, chrome, ie, debug, htmlunit
    }

}
