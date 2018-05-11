package ua.gov.uz.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

import ru.yandex.qatools.htmlelements.element.CheckBox;

public class ResultPage extends Page {

	@FindBy(xpath = "//button[@class='complex_btn']")
	private WebElement recycleBin;

	public ResultPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = ".num")
	private List<WebElement> numberTrains;

	public List<WebElement> getListButtonSelect() {
		return listButtonSelect;
	}

	@FindBy(xpath = "//input[@value='Выбрать']")
	private List<WebElement> listButtonSelect;

	@FindBy(id = "places")
	private WebElement ListPlaces;

	@FindBy(xpath = "//div[@class='wagon-floors']/div/div[@role='button']")
	private List<WebElement> freePlacesList;

	@FindBy(css = ".lastname")
	private WebElement lastName;

	@FindBy(css = ".firstname")
	private WebElement firstName;

	@FindBy(xpath = ".//*[@id='ts_chs_tbl']/button")
	private WebElement addOrderButton;

	@FindBy(css = "#ts_chs_tbl")
	private WebElement AddChoise;

	@FindBy(xpath = "//*[@id='cart']")
	private WebElement trash;

	@FindBy(xpath = "//*[@class='bedding']/input")
	private CheckBox postelCheckBox;

	@Override
	public void open() {

	}

	public void showPrices() throws InterruptedException {
		for (int i = 0; i < listButtonSelect.size(); i++) {

			listButtonSelect.get(i).click();
			Thread.sleep(4000L);
//			wait.until(ExpectedConditions.visibilityOfAllElements(freePlacesList));

      for (WebElement element :freePlacesList){
        if (element.getAttribute("aria-label").contains("свободно")){
          element.click();
        }
        break;
      }
      driver.navigate().back();
			wait.until(ExpectedConditions.elementToBeClickable(recycleBin));

				if (isPresentAndDisplayed(postelCheckBox.getWrappedElement())) {
					postelCheckBox.deselect();
				}

			firstName.sendKeys("Max");
			lastName.sendKeys("Mazur");

			addOrderButton.click();
			wait.until(ExpectedConditions.visibilityOf(trash));
			Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='cart_table']/tbody/tr["+i+1+"]")).isDisplayed());

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
