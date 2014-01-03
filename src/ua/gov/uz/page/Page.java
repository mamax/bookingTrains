/**
 * 
 */
package ua.gov.uz.page;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;

import ua.gov.uz.test.BaseTest;

/**
 * @author maksym.mazurkevych
 *
 */
public abstract class Page {
	
	
	protected WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = BaseTest.setUp();
        PageFactory.initElements(driver, this);
	}
	
	public abstract void open();
	
	
	
	
	
	
}
