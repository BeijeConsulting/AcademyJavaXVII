package it.beije.suormary.bookstore2.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.beije.suormary.bookstore2.model.Order;
import it.beije.suormary.bookstore2.repository.OrderRepository;

@Service
public class OrderService {

	
	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional
	public List<Order> getOrderList(Integer userId){
		List<Order> orders = orderRepository.findOrderListByUserId(userId);
		return orders;
	}
	
}
