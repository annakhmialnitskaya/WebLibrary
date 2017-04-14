package by.htp.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CatalogBooksPage extends Page {

	private static final String FANTASY_FANTASTIC_BOOKS_XPATH = "//*[@id='top-page']/div[3]/main/div[1]/section/div[2]/div/div/div/ul/li[5]/a/div[2]/span";
	private static final String RESULT_XPATH = "//*[@id='top-page']/div[3]/main/div[1]/section/div[2]/div/div/div/ul/li[2]/a/div[2]/span";

	public CatalogBooksPage(WebDriver driver) {
		super(driver);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public CatalogFantasyFictionBooksPage clickLinkFantasyFantasticBooks() {
		WebElement fantasyFantasticBooksLink = driver.findElement(By.xpath(FANTASY_FANTASTIC_BOOKS_XPATH));
		fantasyFantasticBooksLink.click();

		(new WebDriverWait(driver, 20)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(RESULT_XPATH)));

		return new CatalogFantasyFictionBooksPage(driver);
	}

}
