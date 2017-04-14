package by.htp.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends Page {

	private static final String URL = "https://oz.by/";
	private static final String BOOKS_CATALOG_XPATH = "//*[@id='category_goods_container_books']/div[1]/h2/a";
	private static final String RESULTS_CATALOG_XPATH = "//*[@id='top-page']/div[3]/main/div[1]/section/div[2]/div/div/div/ul/li[5]/a/div[2]/span";

	public MainPage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		getDriver().get(URL);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public CatalogBooksPage getBooksCatalogPage() {
		WebElement booksLink = (new WebDriverWait(driver, 2))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BOOKS_CATALOG_XPATH)));
		booksLink.click();

		(new WebDriverWait(driver, 20))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(RESULTS_CATALOG_XPATH)));

		return new CatalogBooksPage(driver);
	}

}
