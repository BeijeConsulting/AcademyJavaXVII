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
 * Servlet implementation class UpdateBook
 */
@WebServlet("/updateBook")
public class UpdateBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		Book book = BookStoreUtility.getBookById(idStr);
		System.out.println(book.getPrice());
		HttpSession session = request.getSession();
		session.setAttribute("book", book);
		List<Author> authors = BookStoreUtility.getAuthors();
		session.setAttribute("authors", authors);
		response.sendRedirect("updateBook.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("id");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String editor = request.getParameter("editor");
		String price = request.getParameter("price");
		String quantity = request.getParameter("quantity");
		String authorId = request.getParameter("authorId");
		BookStoreUtility.updateBook(title, description, editor, price, quantity, authorId,bookId);
		response.sendRedirect("welcome");
	}

}
