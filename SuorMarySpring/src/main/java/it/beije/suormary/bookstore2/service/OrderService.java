package it.beije.suormary.bookstore2.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.beije.suormary.bookstore2.model.Order;
import it.beije.suormary.bookstore2.repository.OrderRepository;

@Service
public class OrderService {

	
	@Autowired
	private OrderRepository orderRepository;
	
	
	public List<Order> getOrderList(Integer userId){
		List<Order> orders = orderRepository.findOrderListByUserId(userId);
		return orders;
	}
	
	@Transactional
	public Order getOrderById(Integer orderId) {
		Optional<Order> o = orderRepository.findById(orderId);
		Order order = o.isPresent() ? o.get() : null; 
		return order;
	}
	
}
