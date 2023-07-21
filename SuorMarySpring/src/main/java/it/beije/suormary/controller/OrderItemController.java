package it.beije.suormary.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.suormary.model.Order;
import it.beije.suormary.model.Book;
import it.beije.suormary.service.OrderItemService;
import it.beije.suormary.service.OrderService;
 
@Controller
public class OrderItemController {
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private OrderService orderService;
	@RequestMapping(value = "/deleteOrderItem", method = RequestMethod.GET)
	public String deleteOrderItem(HttpSession session, Model model, HttpServletRequest request) {
		List<Book> booksOrder = (List) session.getAttribute("booksOrder");
		  String id = request.getParameter("orderItemId");
	      orderItemService.deleteOrderItem(id);
	  	String idOrderStr = request.getParameter("orderId");
	  	int idOrder = Integer.parseInt(idOrderStr);
		Order order = orderService.getOrderById(idOrder);
		model.addAttribute("order", order);
		session.setAttribute("order", order);
		model.addAttribute("deleteOrderItem", "Rimosso");
	      return "recap";
	}
}
