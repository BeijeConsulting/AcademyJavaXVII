package it.beije.suormary.bookstore3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreateBook
 */
@WebServlet("/createBook")
public class CreateBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("email") != null) {
			List<Author> authors = BookStoreUtility.getAuthors();
			session.setAttribute("authors", authors);
			response.sendRedirect("createBook.jsp");
		}
		else response.sendRedirect("login.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String editor = request.getParameter("editor");
		String price = request.getParameter("price");
		String quantity = request.getParameter("quantity");
		String authorId = request.getParameter("authorId");
		BookStoreUtility.addBook(title, description, editor, price, quantity, authorId);
		response.sendRedirect("welcome.jsp");
	}

}
