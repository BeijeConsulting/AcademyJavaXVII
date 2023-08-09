package it.beije.suormary.bookstore2.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.bookstore2.model.Author;
import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.model.Order;
import it.beije.suormary.bookstore2.model.OrderItem;
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
			@RequestParam(required = false) String id) {
		
		Integer orderId = Integer.parseInt(id);
		
		if (id == null) {
            return "bookstore_order_list";
        } else {
    
    		Order order = orderService.getOrderById(orderId);
    		
    		List<OrderItem> orderItems = order.getItems();
    		
    		List<Book> books = orderService.booksInOrder(orderItems);
    		
    		
        	model.addAttribute("order", order);
        	model.addAttribute("books", books);
 	
        }
		return "bookstore_order_details";
	}
	
	@RequestMapping(value = "bookstore_order_list", method = RequestMethod.POST)
	public String insertOrder(HttpSession session, Model model,
			@RequestParam(name = "action", required = false) String action,
			@RequestParam(name = "id", required = false) String id,
			@RequestParam(name = "shipping_address", required = false) String shippingAddress
			) {
		User user = (User) session.getAttribute("user");
		
		Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
		
		if (action != null && action.equals("cancel")) {
			Integer orderId = Integer.parseInt(id);
			orderService.cancelOrder(orderId);
		} else {
			Order order = orderService.insertOrder(user.getId(), cart, shippingAddress);
			System.out.println("Order nuovo: "+ order.toString() );
			System.out.println("OrderItems: "+ order.getItems().toString());
			session.removeAttribute("cart");
	    }
		return "redirect:bookstore_order_list";

	}
	
	@RequestMapping(value = "bookstore_shipping_address", method = RequestMethod.POST)
	public String insertShippingAddress(HttpSession session, Model model,
	        @RequestParam(name = "street", required = true) String street,
	        @RequestParam(name = "city", required = true) String city,
	        @RequestParam(name = "postal_code", required = true) String postalCode,
	        @RequestParam(name = "country", required = true) String country) {

	    StringBuilder shippingAddress = new StringBuilder();
	    shippingAddress.append(street).append(" ").append(city).append(" ").append(postalCode).append(" ").append(country);


		Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
		List<Book> books =  new ArrayList<>();
    	List<Integer> quantities =  new ArrayList<>();
    	Book book = null;
    	int quantity;
    	if (cart != null && !cart.isEmpty()) {
    		for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
    			book = orderService.getBookInOrderByBookId(entry.getKey());
                quantity = entry.getValue();
                books.add(book);
                quantities.add(quantity);
    		}
    	}
    	model.addAttribute("books", books);
    	model.addAttribute("quantities", quantities);    
	    model.addAttribute("shipping_address", shippingAddress);
	    
	    return "bookstore_order_summary";
	}
	
	@RequestMapping(value = "bookstore_shipping_address", method = RequestMethod.GET)
	public String getShippingAddressForm() {

	    return "bookstore_shipping_address";
	}
	
	
}
