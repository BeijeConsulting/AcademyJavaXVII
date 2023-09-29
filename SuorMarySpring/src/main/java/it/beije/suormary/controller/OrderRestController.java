package it.beije.suormary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.suormary.model.Order;
import it.beije.suormary.service.OrderService;

@RestController
@RequestMapping(value = "/api")
public class OrderRestController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(value = "/orders")
	public List<Order> getOrders(){
		return orderService.getOrders();
	}
	
	@GetMapping(value = "/orders_user/{id}")
	public List<Order> getOrder(@PathVariable Integer id) {
		
		List<Order> orders = orderService.getOrdersByUserId(id);
		return orders;
	}
	
	@PutMapping(value = "/order_paid/{id}")
	public Map<String,String> payOrder(@PathVariable Integer id, @RequestBody String address) {
		Map<String,String> message = new HashMap<String,String>();
		try {
		orderService.payment(id, address);
		message.put("message:", "Order paid"); 
		} catch (Exception e) {
			message.put("message:", "Order not paid");
		}
		return message;
	}
	
	@DeleteMapping(value = "/delete_order/{id}")
	public Map<String,String> deleteOrder(@PathVariable Integer id) {
		Map<String,String> message = new HashMap<String,String>();
		try {
			orderService.deleteOrder(id);
			message.put("message:", "Order deleted"); 
			} catch (Exception e) {
				message.put("message:", e.getMessage());
			}
			return message;
	}

}
