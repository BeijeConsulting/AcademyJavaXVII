package it.beije.suormary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ListModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.suormary.model.Book;
import it.beije.suormary.model.OrderItem;
import it.beije.suormary.service.BookService;
import it.beije.suormary.service.OrderItemService;


@RestController
@RequestMapping(value = "/api")
public class OrderItemRestController {

	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private BookService bookService;
	
	@GetMapping(value = "/order_item/{id}")
	public List<OrderItem> orderItems(@PathVariable Integer id){
		
		List<OrderItem> orderItems = orderItemService.orderItemsOrder(id);
		
		return orderItems;
	}
	
	
}
