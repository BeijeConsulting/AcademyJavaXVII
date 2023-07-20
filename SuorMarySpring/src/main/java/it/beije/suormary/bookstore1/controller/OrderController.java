package it.beije.suormary.bookstore1.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.bookstore1.model.Book;
import it.beije.suormary.bookstore1.model.Order;
import it.beije.suormary.bookstore1.service.OrderService;
import it.beije.suormary.bookstore1.service.UserService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String orderGet (Model model, HttpSession session) {
		Integer id = userService.getUserId((String)(session.getAttribute("email")));
		if(id != null) {
			List<Order> oi = orderService.getOrders(id);
			model.addAttribute("orders", oi);
			model.addAttribute("inserted", orderService.getInserted());
			model.addAttribute("paid", orderService.getPaid());
			model.addAttribute("cancelled", orderService.getCancelled());
			return "orders";
		}else {
			return "login";
		}
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String orderPost(Model model, Book book, HttpSession session,  
			@RequestParam String updateOrder, 
			@RequestParam(name = "address", required = false) String address, 
			@RequestParam(name = "orderId", required = false) String orderId) {
		
		Integer id = userService.getUserId((String)(session.getAttribute("email")));
		
		if(id != null) {
			switch(updateOrder) {
				case "Invia":
					orderService.createOrder(address, session);
					break;
				case "Paga":
					orderService.editStatus("P", Integer.valueOf(orderId));
					break;
				case "Annulla":
					orderService.editStatus("C", Integer.valueOf(orderId));
					break;
				default:
					System.out.println("Errore");
					break;
			}
			
			List<Order> oi = orderService.getOrders(id);
			model.addAttribute("orders", oi);
			model.addAttribute("inserted", orderService.getInserted());
			model.addAttribute("paid", orderService.getPaid());
			model.addAttribute("cancelled", orderService.getCancelled());
			
			return "orders";
		}else {
			return "login";
		}
		
		
		
	}
	
}
