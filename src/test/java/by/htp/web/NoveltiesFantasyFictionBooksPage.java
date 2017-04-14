package by.htp.web;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NoveltiesFantasyFictionBooksPage extends Page {

	private static final String ITEMS_XPATH = "//*[@id='goods-table']/li";

	public NoveltiesFantasyFictionBooksPage(WebDriver driver) {
		super(driver);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public int calculateBooks() {
		List<WebElement> itemElements = driver.findElements(By.xpath(ITEMS_XPATH));
		return itemElements.size();
	}

}
