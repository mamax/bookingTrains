package ua.gov.uz;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ua.gov.uz.page.OrderPage;
import ua.gov.uz.page.ResultPage;
import ua.gov.uz.util.SendMail;
import ua.gov.uz.webdriver.BaseTest;

/**
 * Created by msks on 08.05.2016.
 */
@Listeners(ua.gov.uz.listener.Screenshot.class)
public class OrderTest extends BaseTest {

  private ResultPage resultPage;
  private String to = "Киев";
  private String from = "Винница";

    @Test
    public void testDepartureFrom() throws Exception{
      String time = "2018-05-29";
        OrderPage orderPage = new OrderPage(driver);
        orderPage.open();
        resultPage = orderPage.getTicket(from, to, time, "00");
        if (!orderPage.isElementPresent(By.xpath("//div[@class='search-error']"))) {
          CaptureScreenshot("fromHome");
          sendMailAfter(from+"-"+to, "fromHome");
        }
    }

  @Test
  public void testDepartureTo() throws Exception{
    String time = "2018-07-25";
    OrderPage orderPage = new OrderPage(driver);
    orderPage.open();
    resultPage = orderPage.getTicket(to, from, time, "02");
    if (!orderPage.isElementPresent(By.xpath("//div[@class='search-error']"))) {
      CaptureScreenshot("toHome");
      sendMailAfter(to+"-"+from, "toHome");
    }
  }

  private void sendMailAfter(String theme, String filename){
    SendMail.sendMail(theme + new java.util.Date().toString() ,
        System.getProperty("user.dir")+"/target/surefire-reports/html/"+filename+".jpg",
        filename+".jpg");
  }
}
