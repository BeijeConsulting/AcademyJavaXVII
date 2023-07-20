package it.beije.suormary.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.suormary.model.Order;
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
		  String id = request.getParameter("orderItemId");
	      orderItemService.deleteOrderItem(id);
	  	int idOrder = (int) session.getAttribute("orderId");
		Order order = orderService.getOrderById(idOrder);
		model.addAttribute("order", order);
		session.setAttribute("order", order);
	      return "updateOrder";
	}
}
