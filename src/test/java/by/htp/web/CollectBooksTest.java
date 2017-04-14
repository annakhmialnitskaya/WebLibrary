package by.htp.web;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.htp.web.domain.Book;
import by.htp.web.service.BookService;
import by.htp.web.service.BookServiceException;
import by.htp.web.service.impl.BookServiceImpl;

public class CollectBooksTest {

	private static final String DRIVER_NAME = "webdriver.chrome.driver";
	private static final String DRIVER_LOCATION = "C:/programs/selenium/chromedriver.exe";

	private static final String XPATH_START = "//*[@id='goods-table']/li[";
	private static final String XPATH_END_TITLE = "]/div/div/div/div/a/p[1]";
	private static final String XPATH_END_AUTHOR = "]/div/div/div/div/a/p[2]";
	private static final String XPATH_END_IMAGE = "]/div/div/div/a/span/span/span/img";
	private static final String XPATH_END_PRICE = "]/div/div/div/div/div/a/span";

	private static WebDriver driver;

	@BeforeClass
	public static void initBrowser() {
		System.setProperty(DRIVER_NAME, DRIVER_LOCATION);
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}

	@Test
	public void collectBooksTest() {
		// Open main page
		MainPage mainPage = new MainPage(driver);
		mainPage.open();
		// Open catalog books page
		CatalogBooksPage catalogBooksPage = mainPage.getBooksCatalogPage();
		// Open catalog fantasy fantastic books page
		CatalogFantasyFictionBooksPage catalogFantasyFantasticBooksPage = catalogBooksPage
				.clickLinkFantasyFantasticBooks();
		// Open novelties fantasy fantastic books page
		NoveltiesFantasyFictionBooksPage noveltiesBooksPage = catalogFantasyFantasticBooksPage
				.clickLinkNoveltiesBooks();
		// calculate count of books
		int itemCount = noveltiesBooksPage.calculateBooks();
		// add books
		addBooks(itemCount);
	}

	private void addBooks(int itemCount) {

		BookService bookService = new BookServiceImpl();

		for (int i = 1; i <= itemCount; i++) {
			Book book = new Book();
			// set title
			WebElement titleElement = driver.findElement(By.xpath(XPATH_START + i + XPATH_END_TITLE));
			String title = titleElement.getText();
			book.setTitle(title);
			// set author and year
			WebElement authorYearElement = driver.findElement(By.xpath(XPATH_START + i + XPATH_END_AUTHOR));
			String authorYear = authorYearElement.getText();
			int length = authorYear.length();
			String yearStr = authorYear.substring(length - 4, length);
			String author = authorYear.substring(0, length - 6);
			book.setAuthor(author);
			book.setYear(Integer.parseInt(yearStr));
			// set imageLink
			WebElement imageLinkElement = driver.findElement(By.xpath(XPATH_START + i + XPATH_END_IMAGE));
			String imageLink = imageLinkElement.getAttribute("src");
			book.setImageLink(imageLink);
			// set price
			WebElement priceElement = driver.findElement(By.xpath(XPATH_START + i + XPATH_END_PRICE));
			String[] priceArray = priceElement.getText().split(" ");
			double price = Double.parseDouble(priceArray[0].replace(',', '.'));
			book.setPrice(price);

			// save book
			try {
				bookService.addBook(book);
			} catch (BookServiceException e) {
				System.out.println("An error occurred during insert book into DB!");
			}
		}

	}

	@AfterClass
	public static void destroyBrowser() {
		// driver.quit();
	}
}
