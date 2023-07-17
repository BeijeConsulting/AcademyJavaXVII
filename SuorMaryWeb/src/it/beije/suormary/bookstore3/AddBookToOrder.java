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
 * Servlet implementation class AddBookToOrder
 */
@WebServlet("/addBookToOrder")
public class AddBookToOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Book> booksOrder = (List)session.getAttribute("booksOrder");
		String id = request.getParameter("bookOrderId");
		String quantity = request.getParameter("quantity");
		Book book = BookStoreUtility.getBookById(id);
		int quantityInt = Integer.parseInt(quantity);
		book.setQuantity(quantityInt);
		booksOrder.add(book);
		for(Book b : booksOrder) {
			System.out.println(b.getId() +  " " + b.getQuantity());
		}
		session.removeAttribute("quantity");
		response.sendRedirect("newOrder.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
