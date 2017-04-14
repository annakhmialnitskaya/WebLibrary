package by.htp.web.service;

import java.util.List;

import by.htp.web.domain.Book;

public interface BookService {

	public List<Book> listBooks() throws BookServiceException;

	public void addBook(Book book) throws BookServiceException;

}
