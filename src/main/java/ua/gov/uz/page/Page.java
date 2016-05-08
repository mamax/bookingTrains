package ua.gov.uz.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * @author maksym.mazurkevych
 *
 */
public abstract class Page {

	protected WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;
        PageFactory.initElements(driver, this);
        
	}
	
	public abstract void open();
	
	
	
	
}
