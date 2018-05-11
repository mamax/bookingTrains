package ua.gov.uz.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class OrderPage extends Page {

  public OrderPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id='search-frm']/form/div[2]/div[2]/div[1]/div[3]/div/div[3]/table/tbody/tr[4]/td[3]/a")
	private WebElement currentElement;

	@FindBy (xpath="//div[text()='Откуда']/../ul/li[1]")
	private WebElement suggestChoiceStationFrom;

	@FindBy (xpath="//div[text()='Куда']/../ul/li[1]")
	private WebElement suggestChoiceStationTo;
	
	@FindBy (name="from-title")
	private WebElement textFieldFrom ;
	
	@FindBy (name="to-title")
	private WebElement textFieldTo ;
	
	@FindBy (xpath="//button[@name='search']")
	private WebElement searchButton;

	@FindBy (xpath = "//div[@class='date']/input[@type='text']")
	private WebElement chooseFieldDateTrip;

	@FindBy(name = "timep")
	private WebElement timeSct;

  public ResultPage getTicket(String stationFrom, String stationTo, String date, String time)
      throws InterruptedException {

    stationFrom = getCodeByString(stationFrom);
    stationTo = getCodeByString(stationTo);

    driver.navigate().to("https://booking.uz.gov.ua/ru/?from="+stationFrom+"&to="+stationTo+"&date="+date+"&time="+time+"%3A00&url=train-list");

//		typeIn(textFieldFrom, stationFrom);
//		wait.until(ExpectedConditions.elementToBeClickable(suggestChoiceStationFrom));
//		suggestChoiceStationFrom.click();
//
//		typeIn(textFieldTo, stationTo);
//		wait.until(ExpectedConditions.elementToBeClickable(suggestChoiceStationTo));
//		suggestChoiceStationTo.click();
//
//		setDate(date);
//		setTime(time);
//		searchButton.click();
    Thread.sleep(2000L);
//    wait.until(ExpectedConditions.visibilityOf(table));
		return new ResultPage(driver);
  }

  private String getCodeByString(String station) {
    switch (station){
      case "Винница":
        station = "2200200";
        break;
      case "Киев":
        station = "2200001";
        break;
      case "Вінниця":
        station = "2200200";
        break;
      case "Київ":
        station = "2200001";
        break;
    }
    return station;
  }

  private void setTime(String time) {
		Select timeSlct = new Select(timeSct);
		timeSlct.selectByVisibleText(time);
	}

	private void setDate(String date) {
		chooseFieldDateTrip.click();
		currentElement.click();
	}

	@Override
	public void open() {
		driver.get("http://booking.uz.gov.ua/ru/");
	}
	
}
