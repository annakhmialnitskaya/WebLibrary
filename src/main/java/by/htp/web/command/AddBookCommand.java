package by.htp.web.command;

import static by.htp.web.util.Permanents.PAGE_ERROR;
import static by.htp.web.util.Permanents.PAGE_WELCOME_ADMIN;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.web.domain.Book;
import by.htp.web.service.BookService;
import by.htp.web.service.BookServiceException;
import by.htp.web.service.impl.BookServiceImpl;

public class AddBookCommand implements Command {

	private BookService serviceBook;

	{
		serviceBook = new BookServiceImpl();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String page = null;
		try {
			serviceBook.addBook(new Book());
		} catch (BookServiceException e) {
			request.setAttribute("ERROR", "Unable to create book!");
			page = PAGE_ERROR;
			return page;
		}
		page = PAGE_WELCOME_ADMIN;
		request.setAttribute("MESSAGE", "Book was successfuly created!");
		return page;
	}

}
