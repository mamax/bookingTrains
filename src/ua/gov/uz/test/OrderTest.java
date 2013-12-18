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

	@Test(enabled = true, priority = 0)
	public void Kyiv() throws Exception {

		String fromCity = "Киев";
		String toCity = "Винница";
		String date = "28.12.2013";
		String time = "18:00";

		orderPage = PageFactory.initElements(getWebdriver(), OrderPage.class);
		orderPage.open();
		resultPage = orderPage.getBilet(fromCity, toCity, date, time);
		resultPage.showingPrices();

		CaptureScreenshot("Kyiv");

	}

	@Test(enabled = true, priority = 1)
	public void Vinnytsya() throws Throwable {

		String fromCity = "Винница";
		String toCity = "Киев";
		String date = "28.12.2013";
		String time = "18:00";

		orderPage = PageFactory.initElements(getWebdriver(), OrderPage.class);
		orderPage.open();
		resultPage = orderPage.getBilet(fromCity, toCity, date, time);
		resultPage.showingPrices();

		CaptureScreenshot("Vinnytsya");

	}

}
