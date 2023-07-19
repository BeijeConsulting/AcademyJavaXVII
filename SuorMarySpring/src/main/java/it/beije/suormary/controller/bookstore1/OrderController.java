package it.beije.suormary.controller.bookstore1;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.bin.bookstore1.Book;
import it.beije.suormary.bin.bookstore1.Order;
import it.beije.suormary.service.bookstore1.OrderService;
import it.beije.suormary.service.bookstore1.UserService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String orderGet (Model model, HttpSession session) {
		List<Order> oi = orderService.getOrders(userService.getUserId((String)(session.getAttribute("email"))));
		model.addAttribute("orders", oi);
		return "orders";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String orderPost(Model model, Book book, HttpSession session,  
			@RequestParam String updateOrder, 
			@RequestParam(name = "address", required = false) String address, 
			@RequestParam(name = "orderId", required = false) String orderId) {

		switch(updateOrder) {
			case "Invia":
				orderService.createOrder(address, session);
				break;
			case "Paga":
				orderService.editStatus('P', Integer.valueOf(orderId));
				break;
			case "Annulla":
				orderService.editStatus('C', Integer.valueOf(orderId));
				break;
			default:
				System.out.println("Errore");
				break;
		}
		
		List<Order> oi = orderService.getOrders(userService.getUserId((String)(session.getAttribute("email"))));
		model.addAttribute("orders", oi);
		
		return "orders";
		
	}
	
}
