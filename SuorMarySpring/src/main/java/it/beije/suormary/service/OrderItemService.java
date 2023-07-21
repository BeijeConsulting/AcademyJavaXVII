package it.beije.suormary.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.model.Book;
import it.beije.suormary.model.Order;
import it.beije.suormary.model.OrderItem;
import it.beije.suormary.repository.OrderItemRepository;
import it.beije.suormary.repository.OrderRepository;
 
@Service
public class OrderItemService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderService orderService;
	@Autowired
	private BookService bookService;
	
	
	  	public void createOrderItems(List<Book> booksOrder, int orderId) {
    	  
		   Order order = orderService.findOrder(orderId);
		   OrderItem orderItem;
   	       for(Book b : booksOrder) {
    	    	   orderItem = new OrderItem();
    	    	   orderItem.setBookId(b.getId());
    	    	   orderItem.setOrderId(order.getId());
    	    	   orderItem.setQuantity(b.getQuantity());
    	    	   orderItem.setPrice(b.getPrice() * b.getQuantity());
    	    	   orderItemRepository.save(orderItem);
    	    	   order.addOrderItem(orderItem);
    	    	   Book book = bookService.getBookById(b.getId());
    	    	   book.setQuantity(book.getQuantity()-b.getQuantity());
    	       }
   	       double amount = 0;
   	       System.out.println(order.getItems().size());
   	       for(OrderItem orderIt : order.getItems()) {
   	    	   amount += orderIt.getPrice();
   	       } 
    	   order.setAmount(amount);
    	   orderRepository.save(order);
       }
	   
	    public  void deleteOrderItem(String idStr) {
	    	  
	    	   int id = Integer.parseInt(idStr);
	    	   Book book = null;
	    		   OrderItem orderItem = findOrderItem(id);
	    		   Order order = orderItemRepository.findByOrderId(orderItem.getOrderId());
	    		   for(OrderItem ord : order.getItems()) {
	    			   if(ord.getId() == orderItem.getId()) { 
	    				  book = bookService.getBookById(ord.getBookId());
	    				  book.setQuantity(book.getQuantity() + ord.getQuantity());
	    				  order.getItems().remove(ord);
	    			   }
	    		   }
	    		 
	    		
	       }

	    private OrderItem findOrderItem(int orderItemId) {
	    	
	    	Optional <OrderItem> orIt = orderItemRepository.findById(orderItemId);
	    	
	    	OrderItem orderItem = orIt.isPresent() ? orIt.get() : null;
	 		
	 		return orderItem;
		}
}
