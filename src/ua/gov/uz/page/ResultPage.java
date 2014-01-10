package ua.gov.uz.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ResultPage extends Page {

	public ResultPage(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	String begin = "//tbody/tr[";
	String end = "]";

	@FindBy(css = ".num")
	private List<WebElement> numberTrains;

	@FindBy(css = ".place>div>button")
	private List<WebElement> ListButtonSelect;

	@FindBy(id = "places")
	private WebElement ListPlaces;

	@FindBy(css = ".lastname")
	private WebElement LastName;

	@FindBy(css = ".firstname")
	private WebElement FirstName;

	@FindBy(xpath = ".//*[@id='ts_chs_tbl']/button")
	private WebElement AddOrder;

	@FindBy(css = "#ts_chs_tbl")
	private WebElement AddChoise;

	@FindBy(css = ".bedding>input")
	private WebElement Postel;

	@Override
	public void open() {
		// TODO Auto-generated method stub

	}

	public void showingPrices() {

		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(numberTrains));

		for (int i = 0; i < ListButtonSelect.size(); i++) {

			ListButtonSelect.get(i).click();
			
			ListPlaces.findElement(By.className("free")).click();
			Assert.assertTrue(AddChoise.isDisplayed());
			Assert.assertTrue(AddOrder.isDisplayed());

			if (isPresentAndDisplayed(Postel)) {

				Postel.click();
			}

			FirstName.sendKeys("Max");
			LastName.sendKeys("Mazur");

			AddOrder.click();

			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(begin + (i + 1) + end)));

		}

	}
	

	public static boolean isPresentAndDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void getNumberTrains() {
		for (WebElement e : numberTrains) {
			System.out.println(e.getText());
		}

	}

}
