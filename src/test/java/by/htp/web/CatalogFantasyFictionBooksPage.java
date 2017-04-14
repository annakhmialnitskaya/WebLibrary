package by.htp.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CatalogFantasyFictionBooksPage extends Page {

	private static final String NOVELTIES_BOOKS_XPATH = "//*[@id='top-page']/div[3]/main/div[1]/section/div[2]/div/div/div/ul/li[2]/a/div[2]/span";

	public CatalogFantasyFictionBooksPage(WebDriver driver) {
		super(driver);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public NoveltiesFantasyFictionBooksPage clickLinkNoveltiesBooks() {
		WebElement NoveltiesBooksLink = driver.findElement(By.xpath(NOVELTIES_BOOKS_XPATH));
		NoveltiesBooksLink.click();

		return new NoveltiesFantasyFictionBooksPage(driver);
	}

}
