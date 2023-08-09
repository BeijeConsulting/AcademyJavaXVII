package it.beije.suormary.bookstore2.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.model.Order;
import it.beije.suormary.bookstore2.model.OrderItem;
import it.beije.suormary.bookstore2.model.User;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/bookstoreOrderList")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderServlet() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
            // Utente non autenticato, reindirizza alla pagina di login
            response.sendRedirect("bookstoreLogin.jsp");
        } else {
        	List<Order> orders = BookstoreUtility.readOrdersFromDb(user.getId());
        	request.setAttribute("orders", orders);
        	request.getRequestDispatcher("bookstoreOrderList.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
	    Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
	    
	    String action = request.getParameter("action");
	    
	    if (action != null && action.equals("cancel")) {
	        int orderId = Integer.parseInt(request.getParameter("id"));
	        // Esegui l'operazione di cancellazione dell'ordine con l'ID specificato
	        BookstoreUtility.cancelOrder(orderId);
	    } else {
	    	// Creazione di un nuovo ordine nel database
	        Order newOrder = new Order();
	        newOrder.setUserId(user.getId());
	        newOrder.setDate(LocalDateTime.now());
	        newOrder.setStatus("I"); // Stato "Inserito" per il nuovo ordine

	        // Calcolo del totale dell'ordine
	        double totalAmount = 0.0;
	        List<OrderItem> orderItems = new ArrayList<>();

	        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
	            int bookId = entry.getKey();
	            int quantity = entry.getValue();
	            Book book = BookstoreUtility.findBook(bookId);
	            double price = book.getPrice();
	            
	         // Creazione di un oggetto OrderItem per ogni libro nel carrello
	            OrderItem orderItem = new OrderItem();
	            orderItem.setBookId(bookId);
	            orderItem.setQuantity(quantity);
	            orderItem.setPrice(price);
	            orderItems.add(orderItem);

	            // Calcolo del totale dell'ordine aggiungendo il prezzo del libro moltiplicato per la quantità
	            totalAmount += price * quantity;
	        }

	        newOrder.setItems(orderItems);
	        newOrder.setAmount(totalAmount);
	        
	        // Salvataggio dell'ordine nel database
	        int orderId = BookstoreUtility.insertOrder(newOrder);

	        // Rimozione del carrello dalla sessione
	        session.removeAttribute("cart");
	        
	    }
	    // Reindirizzamento alla pagina di visualizzazione degli ordini
	    response.sendRedirect(request.getContextPath() + "/bookstoreOrderList");
        
	}
}
