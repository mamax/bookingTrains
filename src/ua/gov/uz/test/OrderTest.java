package ua.gov.uz.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ua.gov.uz.page.OrderPage;
import ua.gov.uz.page.ResultPage;

@Listeners(ua.gov.uz.listener.Screenshot.class)
public class OrderTest extends BaseTest {
	
	 private ResultPage resultPage;
	 private OrderPage orderPage;
	    
	String toCity = "Винница";
	String fromCity = "Киев";
	String date = "28.01.2014";
	String time = "22:00";
	
	
	@Test(enabled = true, priority = 0)
	public void Kyiv() throws Exception {

		//no remote 
		orderPage = PageFactory.initElements(setUp(), OrderPage.class);
		orderPage.open();
		ResultPage resultPage = orderPage.getBilet("Киев", "Винница", "07.03.2014", "16:00");
		resultPage.showingPrices();

		CaptureScreenshot("Kyiv");
		
	}
	
	@Test(enabled = false, priority = 1)
	public void Vinnytsya() throws Throwable {

		//remote webdriver
		
		String fromCity = "Винница";
		String toCity = "Киев";
		String date = "07.03.2014";
		String time = "16:00";
		
		OrderPage orderPage = new OrderPage(driver);
		orderPage.open();
		ResultPage resultPage = orderPage.getBilet(fromCity, toCity, date, time);
		resultPage.showingPrices();

		CaptureScreenshot("Vinnytsya");
		
	}

}
