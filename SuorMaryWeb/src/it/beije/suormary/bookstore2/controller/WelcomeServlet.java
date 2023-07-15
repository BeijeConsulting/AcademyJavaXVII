package it.beije.suormary.bookstore2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.suormary.bookstore2.model.Book;

/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/bookstoreWelcome")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("bookstoreWelcome doGet");

		List<Book> books = BookstoreUtility.readBooksFromDb();
		request.setAttribute("books", books);
		// chiama la jsp
		request.getRequestDispatcher("bookstoreWelcome.jsp").forward(request, response);
		
//		response.sendRedirect("bookstoreWelcome.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<Book> books = BookstoreUtility.readBooksFromDb();
//		request.setAttribute("books", books);
//		// chiama la jsp
//		request.getRequestDispatcher("bookstoreWelcome.jsp").forward(request, response);

		//doGet(request, response);
	}

}
