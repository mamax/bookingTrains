/**
 * 
 */
package ua.gov.uz.page;

import org.openqa.selenium.WebDriver;

/**
 * @author maksym.mazurkevych
 *
 */
public abstract class Page {
	protected WebDriver driver;
//
	public Page(WebDriver driver) {
		this.driver = driver;
	}
	
	public abstract void open();
	
	
	
	
}
