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
		
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        List<Author> authors = new ArrayList<>();
        System.out.println("user:" + user);
        System.out.println("cart:" + cart);
        if (user == null) {
            // Utente non autenticato, reindirizza alla pagina di login
            response.sendRedirect("bookstoreLogin.jsp");
        } else {
            List<Book> books = BookstoreUtility.readBooksFromDb();
            for (Book book : books) {
            	authors.add(BookstoreUtility.findAuthorFromId(book.getAuthorId()));
            }
            request.setAttribute("books", books);
            request.setAttribute("authors", authors);
         // chiama la jsp
            request.getRequestDispatcher("bookstoreWelcome.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
