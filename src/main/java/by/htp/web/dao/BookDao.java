package by.htp.web.dao;

import java.util.List;

import by.htp.web.domain.Book;

public interface BookDao {

	public List<Book> read() throws BookDaoException;

	public void add(Book book) throws BookDaoException;

}
