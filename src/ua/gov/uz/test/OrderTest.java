package ua.gov.uz.test;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ua.gov.uz.page.OrderPage;
import ua.gov.uz.page.ResultPage;


@Listeners( ua.gov.uz.listener.Screenshot.class)
public class OrderTest extends BaseTest{

    private ResultPage resultPage;
    private OrderPage orderPage;

	
	@Test(enabled = true, priority  = 0)
	public void Kyiv() throws Exception{
		
		orderPage = PageFactory.initElements(getWebdriver(), OrderPage.class);
		orderPage.open();
		resultPage = orderPage.getBilet("Киев", "Винница", "19.12.2013", "18:00");
		resultPage.WaitForPageToLoadByCss(".num");
		resultPage.showingPrices();
		
		
	    CaptureScreenshot("Kyiv");
		
	}
	
	@Test(enabled = true, priority  = 1)
	public void Vinnytsya() throws Throwable {
		
		
		orderPage = PageFactory.initElements(getWebdriver(), OrderPage.class);
		orderPage.open();
		resultPage = orderPage.getBilet("Винница","Киев",  "18.12.2013", "18:00");
		resultPage.WaitForPageToLoadByCss(".num");
		resultPage.showingPrices();
		
		
	    CaptureScreenshot("Vinnytsya");
		
		
	}
	
	
	
	

}
