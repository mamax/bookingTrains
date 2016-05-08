package ua.gov.uz;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ua.gov.uz.page.OrderPage;
import ua.gov.uz.page.ResultPage;
import ua.gov.uz.webdriver.BaseTest;

/**
 * Created by msks on 08.05.2016.
 */
@Listeners(ua.gov.uz.listener.Screenshot.class)
public class OrderTest extends BaseTest {

    private ResultPage resultPage;

    @Test(enabled = true, priority  = 0)
    public void testKyiv() throws Exception{

        OrderPage orderPage = new OrderPage(driver);

        orderPage.open();
        resultPage = orderPage.getBilet("Киев", "Винница", "08.06.2016", "22:00");
        resultPage.WaitForPageToLoadByCss(".num");
        resultPage.showingPrices();
        CaptureScreenshot("Kyiv");
    }

}
