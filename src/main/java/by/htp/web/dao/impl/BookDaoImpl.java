package by.htp.web.dao.impl;

import static by.htp.web.dao.impl.DaoConstants.DB_CONNECTION;
import static by.htp.web.dao.impl.DaoConstants.DB_DRIVER;
import static by.htp.web.dao.impl.DaoConstants.DB_PASSWORD;
import static by.htp.web.dao.impl.DaoConstants.DB_USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.web.dao.BookDao;
import by.htp.web.dao.BookDaoException;
import by.htp.web.domain.Book;

public class BookDaoImpl implements BookDao {

	private static final String QUERY_ALL_BOOKS_SELECT = "SELECT * FROM book";
	private static final String QUERY_BOOK_INSERT = "INSERT INTO book (`title`, `author`, `price`, `imageLink`, `year`) VALUES (?,?,?,?,?)";

	@Override
	public List<Book> read() throws BookDaoException {

		List<Book> books = new ArrayList<Book>();
		try (Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
				Statement statement = dbConnection.createStatement();
				ResultSet rs = statement.executeQuery(QUERY_ALL_BOOKS_SELECT)) {
			Class.forName(DB_DRIVER);
			while (rs.next()) {
				String title = rs.getString("title");
				String author = rs.getString("author");
				double price = rs.getDouble("price");
				String imageLink = rs.getString("imageLink");
				int year = rs.getInt("year");
				Book book = new Book(title, author, price, imageLink, year);
				books.add(book);
			}

		} catch (SQLException | ClassNotFoundException e) {
			throw new BookDaoException("Exception with DB!", e);
		}
		return books;
	}

	@Override
	public void add(Book book) throws BookDaoException {
		try (Connection dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
				PreparedStatement statement = dbConnection.prepareStatement(QUERY_BOOK_INSERT)) {
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setDouble(3, book.getPrice());
			statement.setString(4, book.getImageLink());
			statement.setInt(5, book.getYear());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new BookDaoException("Exception with DB!", e);
		}
	}
}
