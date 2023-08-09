package it.beije.suormary.bookstore2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.suormary.bookstore2.model.Author;
import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.model.User;

@WebServlet("/bookstoreHome")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("bookstoreWelcome doGet");

		List<Author> authors = new ArrayList<>();

		List<Book> books = BookstoreUtility.readBooksFromDb();
		for (Book book : books) {
			authors.add(BookstoreUtility.findAuthorFromId(book.getAuthorId()));
		}
		request.setAttribute("books", books);
		request.setAttribute("authors", authors);
		// chiama la jsp
		request.getRequestDispatcher("bookstoreHome.jsp").forward(request, response);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
