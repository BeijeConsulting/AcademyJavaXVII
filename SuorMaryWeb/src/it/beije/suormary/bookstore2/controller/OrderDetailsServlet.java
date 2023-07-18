package it.beije.suormary.bookstore2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.model.Order;
import it.beije.suormary.bookstore2.model.OrderItem;

/**
 * Servlet implementation class OrderDetailsServlet
 */
@WebServlet("/bookstoreOrderDetails")
public class OrderDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public OrderDetailsServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("id"));
		Order order = BookstoreUtility.getOrderFromId(orderId);
		List<Book> books = new ArrayList<>();
		List<OrderItem> oi = order.getItems();
		for (OrderItem orderItem : oi) {
			books.add(BookstoreUtility.findBook(orderItem.getBookId()));	
		}
		
        System.out.println("order: " + order);
        request.setAttribute("order", order);
        request.setAttribute("books", books);

        // Forward the request to the JSP page for displaying book details
        request.getRequestDispatcher("bookstoreOrder.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
