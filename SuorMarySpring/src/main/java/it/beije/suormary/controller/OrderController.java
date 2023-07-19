package it.beije.suormary.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.suormary.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/my_orders", method = RequestMethod.GET)
	public String myOrderGet(HttpSession session, Model model) {

        if(session.getAttribute("email") != null) {
        	List<Order> usersOrders = orderService.usersOrders((String) session.getAttribute("email"));
        	model.addAttribute("usersOrder", usersOrders);
        	 return "my_order";
         }
         else return "login";
	}
}
