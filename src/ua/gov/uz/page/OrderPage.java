package ua.gov.uz.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrderPage extends Page {

	public OrderPage(WebDriver driver) throws Exception {
		super(driver);

		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = ".//*[@id='stations_from']/div[1]")
	private WebElement suggestChoiceStationFrom;

	@FindBy(xpath = ".//*[@id='stations_till']/div[1]")
	private WebElement suggestChoiceStationTo;

	@FindBy(name = "station_from")
	private WebElement textFieldFrom;

	@FindBy(name = "station_till")
	private WebElement textFieldTo;

	@FindBy(xpath = "//button[@name='search']")
	private WebElement searchButton;

	@FindBy(xpath = ".//*[@id='date_dep']")
	private WebElement chooseFieldDateTrip;
	

	public ResultPage getBilet(String stationFrom, String stationTo,
			String date, String time) throws Exception {

		textFieldFrom.sendKeys(stationFrom);
		suggestChoiceStationFrom.click();

		textFieldTo.sendKeys(stationTo);
		suggestChoiceStationTo.click();

		setDate(date);
		setTime(time);
		searchButton.click();

		return new ResultPage(driver);

	}

	private void setTime(String time) {
		// TODO Auto-generated method stub
		Select sizeSct = new Select(driver.findElement(By.name("time_dep")));
		sizeSct.selectByValue(time);
		// sizeSct.selectByValue(value);
	}

	private void setDate(String date) {
		// TODO Auto-generated method stub
		chooseFieldDateTrip.clear();
		chooseFieldDateTrip.sendKeys(date);

	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
		driver.get(baseUrl + "/");
		
	}

}
