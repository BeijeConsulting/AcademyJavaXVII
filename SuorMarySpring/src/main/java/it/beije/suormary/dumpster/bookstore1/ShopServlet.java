package it.beije.suormary.dumpster.bookstore1;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.suormary.bin.bookstore1.Book;
import it.beije.suormary.bin.bookstore1.Cart;

/**
 * Servlet implementation class ShopServlet
 */
@WebServlet("/ShopServlet")
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Book> books = BookUtils.getAllBooks();
		request.getSession().setAttribute("books", books);
		request.getRequestDispatcher("./shop.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookId = Integer.valueOf(request.getParameter("bookId"));
		int quantity = Integer.valueOf(request.getParameter("quantity"));
		
		Map<Integer,Integer> cart = Cart.getCart(request);
		
		if(cart.containsKey(bookId)) {
			int newQuantity = cart.get(bookId) + quantity;
			cart.replace(bookId, newQuantity);
		}else {
			cart.put(bookId, quantity);
		}
		
		request.getSession().setAttribute("cart", cart);
		
		System.out.println(request.getSession().getAttribute("cart"));
		
		response.sendRedirect("./ShopServlet");
	}

}
