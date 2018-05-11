package ua.gov.uz;

import org.openqa.selenium.By;
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

    @Test
    public void testDepartureFrom() throws Exception{
      String from = "Киев";
      String to = "Винница";
      String time = "2018-05-29";
        OrderPage orderPage = new OrderPage(driver);
        orderPage.open();
        resultPage = orderPage.getTicket(to, from, "2018-05-29", "02");
        if (!orderPage.isElementPresent(By.xpath("//div[@class='search-error']"))) {
//          resultPage.showPrices();
          CaptureScreenshot("fromHome");
        }
    }

  @Test
  public void testDepartureTo() throws Exception{
    String from = "Киев";
    String to = "Винница";
    String time = "2018-07-25";
    OrderPage orderPage = new OrderPage(driver);
    orderPage.open();
    resultPage = orderPage.getTicket(from, to, time, "02");
    if (!orderPage.isElementPresent(By.xpath("//div[@class='search-error']"))) {
//      resultPage.showPrices();
      CaptureScreenshot("toHome");
    }
  }
}
