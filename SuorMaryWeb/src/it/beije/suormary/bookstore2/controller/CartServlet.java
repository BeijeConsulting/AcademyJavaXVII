package it.beije.suormary.bookstore2.controller;

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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bookId = Integer.parseInt(request.getParameter("id"));

	    HttpSession session = request.getSession();

	    // Retrieve the existing cart from the session or create a new one if it doesn't exist
	    Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
	    if (cart == null) {
	        cart = new HashMap<>();
	        session.setAttribute("cart", cart);
	    }

	 // Add the book to the cart and update the quantity
        int quantity = cart.getOrDefault(bookId, 0);
        cart.put(bookId, quantity + 1);

        System.out.println("Added to cart: bookId=" + bookId + ", quantity=" + (quantity));


	    // Redirect back to the previous page or any other desired page
	    response.sendRedirect(request.getContextPath() + "/bookstoreBookDetails?id=" + bookId);
	
		//doGet(request, response);
	}

}
