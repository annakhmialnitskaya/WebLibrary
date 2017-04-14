package by.htp.web.service.impl;

import java.util.List;

import by.htp.web.dao.BookDao;
import by.htp.web.dao.BookDaoException;
import by.htp.web.dao.impl.BookDaoImpl;
import by.htp.web.domain.Book;
import by.htp.web.service.BookService;
import by.htp.web.service.BookServiceException;

public class BookServiceImpl implements BookService {

	private BookDao bookDao;

	{
		bookDao = new BookDaoImpl();
	}

	@Override
	public List<Book> listBooks() throws BookServiceException {
		List<Book> books = null;
		try {
			books = bookDao.read();
		} catch (BookDaoException e) {
			throw new BookServiceException();
		}
		return books;
	}

	@Override
	public void addBook(Book book) throws BookServiceException {
		try {
			bookDao.add(book);
		} catch (BookDaoException e) {
			throw new BookServiceException();
		}

	}
}
