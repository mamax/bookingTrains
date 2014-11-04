package ua.gov.uz.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ua.gov.uz.page.OrderPage;
import ua.gov.uz.page.ResultPage;



@Listeners(ua.gov.uz.listener.Screenshot.class)
public class OrderTest extends BaseTest{

    private ResultPage resultPage;
    private OrderPage orderPage;

	@Test(enabled = true, priority  = 0)
	public void Kyiv() throws Exception{
		
		orderPage = PageFactory.initElements(setUp("firefox"), OrderPage.class);
		orderPage.open();
		resultPage = orderPage.getBilet("Киев", "Винница", "08.11.2014", "22:00");
		resultPage.WaitForPageToLoadByCss(".num");
		resultPage.showingPrices();
	    CaptureScreenshot("Kyiv");
		
	}
	
	@Test(enabled = false, priority  = 2)
	public void Kyiv2() throws Exception{
		
		orderPage = PageFactory.initElements(setUp("firefox"), OrderPage.class);
		orderPage.open();
		resultPage = orderPage.getBilet("Киев", "Винница", "08.11.2014", "22:00");
		resultPage.WaitForPageToLoadByCss(".num");
		resultPage.showingPrices();
		
	    CaptureScreenshot("Kyiv2");
		
	}
	
	@Test(enabled = false, priority  = 1)
	public void Vinnytsya() throws Throwable {
		
		orderPage = PageFactory.initElements(setUp("firefox"), OrderPage.class);
		orderPage.open();
		resultPage = orderPage.getBilet("Киев", "Винница", "08.11.2014", "22:00");
		resultPage.WaitForPageToLoadByCss(".num");
		resultPage.showingPrices();
		
	    CaptureScreenshot("Vinnytsya");
		
	}
	
	@Test(enabled = false, priority  = 3)
	public void Vinnytsya2() throws Throwable {
		
		orderPage = PageFactory.initElements(setUp("firefox"), OrderPage.class);
		orderPage.open();
		resultPage = orderPage.getBilet("Киев", "Винница", "08.11.2014", "22:00");
		resultPage.WaitForPageToLoadByCss(".num");
		resultPage.showingPrices();
		
	    CaptureScreenshot("Vinnytsya2");
		
	}

}
