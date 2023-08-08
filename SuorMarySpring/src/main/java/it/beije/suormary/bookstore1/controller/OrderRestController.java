package it.beije.suormary.bookstore1.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.suormary.bookstore1.model.Order;
import it.beije.suormary.bookstore1.service.OrderService;

@RestController
@RequestMapping(value = "/api")
public class OrderRestController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping(value = "/order/{id}")
	public Order selectOrder(@PathVariable Integer id) {
		Order order = orderService.getOrder(id);
		return order;
	}
	
	@GetMapping(value = "/orders")
	public List<Order> selectOrders() {
		return orderService.getAllOrders();
	}
	
	@PostMapping(value = "/new_order")
	public Order insertOrder(@RequestBody String address, HttpSession session) {
		return orderService.createOrder(address, session);
	}
	
	@PutMapping(value = "/order/{id}")
	public Order updateOrder(@PathVariable Integer id, @RequestBody String status) {
		return orderService.editStatus(status, id);
	}
}
