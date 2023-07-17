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

import it.beije.suormary.bookstore2.model.Author;
import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.model.User;

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
		System.out.println("cart doGet");
		
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            // Utente non autenticato, reindirizza alla pagina di login
            response.sendRedirect("bookstoreLogin.jsp");
        } else {
        	Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        	System.out.println("cart: " + cart);
        	List<Book> books =  new ArrayList<>();
        	List<Author> authors =  new ArrayList<>();
        	List<Integer> quantities =  new ArrayList<>();
        	if (cart != null && !cart.isEmpty()) {
        		for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
        			Book book = BookstoreUtility.findBook(entry.getKey());
                    int quantity = entry.getValue();
                    Author author = BookstoreUtility.findAuthorFromId(book.getAuthorId());
                    books.add(book);
                    authors.add(author);
                    quantities.add(quantity);
        		}
        	}
        	request.setAttribute("books", books);
        	request.setAttribute("authors", authors);
        	request.setAttribute("quantities", quantities);
            
         // chiama la jsp
            request.getRequestDispatcher("bookstoreCart.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bookId = Integer.parseInt(request.getParameter("id"));
		String action = request.getParameter("action");

	    HttpSession session = request.getSession();

	    // Retrieve the existing cart from the session or create a new one if it doesn't exist
	    Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
	    if (cart == null) {
	        cart = new HashMap<>();
	        session.setAttribute("cart", cart);
	    }

	 // Add the book to the cart and update the quantity
        int quantity = cart.getOrDefault(bookId, 0);
        Book book = BookstoreUtility.findBook(bookId);
        int maxQuantity = book.getQuantity();
        
        if (action.equals("add") || action.equals("addFromBookDet")) {
        	if (quantity < maxQuantity) {
        		cart.put(bookId, quantity + 1);
                System.out.println("Added to cart: bookId=" + bookId + ", quantity=" + (quantity + 1));
        	} else {
        		System.out.println("Maximum quantity reached for bookId=" + bookId);
        		session.setAttribute("cartError", "maximum amount of books reached for the book "+book.getTitle());
        	}
        	
            if (action.equals("addFromBookDet")) {
                // Redirect back to the previous page or any other desired page
                response.sendRedirect(request.getContextPath() + "/bookstoreBookDetails?id=" + bookId);
            } else {
                response.sendRedirect(request.getContextPath() + "/cart");
            }
        } else if (action.equals("remove")) {
            if (quantity > 0) {
                cart.put(bookId, quantity - 1);
                System.out.println("Removed from cart: bookId=" + bookId + ", quantity=" + (quantity - 1));
                if (quantity - 1 == 0) {
                	cart.remove(bookId);
                }
            }
            response.sendRedirect(request.getContextPath() + "/cart");
        } else {
            // Gestire l'azione sconosciuta o altri casi
            response.sendRedirect(request.getContextPath() + "/cart");
        }
	}

}
