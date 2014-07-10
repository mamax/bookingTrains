/**
 * 
 */
package ua.gov.uz.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import ua.gov.uz.test.BaseTest;

/**
 * @author maksym.mazurkevych
 *
 */
public abstract class Page {
	protected WebDriver driver;
//
	public Page(WebDriver driver) {
//		this.driver = driver;
		this.driver = BaseTest.setUp("firefox");
        PageFactory.initElements(driver, this);
        
	}
	
	public abstract void open();
	
	
	
	
}
