package it.beije.suormary.bookstore2.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.bookstore2.model.Order;
import it.beije.suormary.bookstore2.model.User;
import it.beije.suormary.bookstore2.service.OrderService;

@Controller
public class OrderController {

	
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping(value = "/bookstore_order_list", method = RequestMethod.GET)
	public String getListOfOrders(HttpSession session, Model model) {
		
		User user = (User) session.getAttribute("user");
		if (user == null) {
            return "bookstore_login.jsp";
        } else {
        	List<Order> orders = orderService.getOrderList(user.getId());
        	model.addAttribute("orders", orders);
        }
		return "bookstore_order_list";
	}
	
	
	@RequestMapping(value = "/bookstore_order_details", method = RequestMethod.GET)
	public String getOrderById(HttpSession session, Model model,
			@RequestParam(required = false) Integer id) {
		
		if (id == null) {
            return "bookstore_order_list";
        } else {
    
    		Order order = orderService.getOrderById(id);
        	model.addAttribute("order", order);
        }
		return "bookstore_order_details";
	}
	
	
	
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		User user = (User) session.getAttribute("user");
//	    Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
//	    
//	    String action = request.getParameter("action");
//	    
//	    if (action != null && action.equals("cancel")) {
//	        int orderId = Integer.parseInt(request.getParameter("id"));
//	        // Esegui l'operazione di cancellazione dell'ordine con l'ID specificato
//	        BookstoreUtility.cancelOrder(orderId);
//	    } else {
//	    	// Creazione di un nuovo ordine nel database
//	        Order newOrder = new Order();
//	        newOrder.setUserId(user.getId());
//	        newOrder.setDate(LocalDateTime.now());
//	        newOrder.setStatus("I"); // Stato "Inserito" per il nuovo ordine
//
//	        // Calcolo del totale dell'ordine
//	        double totalAmount = 0.0;
//	        List<OrderItem> orderItems = new ArrayList<>();
//
//	        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
//	            int bookId = entry.getKey();
//	            int quantity = entry.getValue();
//	            Book book = BookstoreUtility.findBook(bookId);
//	            double price = book.getPrice();
//	            
//	         // Creazione di un oggetto OrderItem per ogni libro nel carrello
//	            OrderItem orderItem = new OrderItem();
//	            orderItem.setBookId(bookId);
//	            orderItem.setQuantity(quantity);
//	            orderItem.setPrice(price);
//	            orderItems.add(orderItem);
//
//	            // Calcolo del totale dell'ordine aggiungendo il prezzo del libro moltiplicato per la quantità
//	            totalAmount += price * quantity;
//	        }
//
//	        newOrder.setItems(orderItems);
//	        newOrder.setAmount(totalAmount);
//	        
//	        // Salvataggio dell'ordine nel database
//	        int orderId = BookstoreUtility.insertOrder(newOrder);
//
//	        // Rimozione del carrello dalla sessione
//	        session.removeAttribute("cart");
//	        
//	    }
//	    // Reindirizzamento alla pagina di visualizzazione degli ordini
//	    response.sendRedirect(request.getContextPath() + "/bookstoreOrderList");
//        
//	}
	
}
