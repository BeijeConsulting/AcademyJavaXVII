package dumpster.bookstore1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.suormary.bookstore1.model.Book;
import it.beije.suormary.bookstore1.model.Cart;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<Integer,Integer> cart = Cart.getCart(request);
		
		Map<Book,Integer> books = BookUtils.getBooks(cart);
		request.getSession().setAttribute("books", books);
		request.getRequestDispatcher("./cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.valueOf(request.getParameter("bookId"));
		int quantity = Integer.valueOf(request.getParameter("quantity"));
		
		Map<Integer,Integer> cart = Cart.getCart(request);
		
		int newQuantity = cart.get(bookId) - quantity;
		
		if(newQuantity > 0) {	
			cart.replace(bookId, newQuantity);
		}else {	
			cart.remove(bookId);
		}
		
		request.getSession().setAttribute("cart", cart);
		response.sendRedirect("./CartServlet");
	}

}
