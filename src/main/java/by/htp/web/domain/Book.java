package by.htp.web.domain;

public class Book {

	private String title;
	private String author;
	private double price;
	private String imageLink;
	private int year;

	public Book(String title, String author, double price, String imageLink, int year) {
		super();
		this.title = title;
		this.author = author;
		this.price = price;
		this.imageLink = imageLink;
		this.year = year;
	}

	public Book() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
