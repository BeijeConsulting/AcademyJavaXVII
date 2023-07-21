package it.beije.suormary.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.model.Book;
import it.beije.suormary.model.Order;
import it.beije.suormary.model.OrderItem;
import it.beije.suormary.repository.BookRepository;
import it.beije.suormary.repository.OrderItemRepository;
import it.beije.suormary.repository.OrderRepository;
 
@Service
public class OrderItemService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderService orderService;
	@Autowired
	private BookService bookService;
	
	  	public void createOrderItems(List<Book> booksOrder, Integer orderId) {
    	   
		   Order order = orderService.findOrder(orderId);
		   OrderItem orderItem;
		   double amount;
		   if(order.getItems().size() > 0) {
			  amount = order.getAmount();
		   }
		   else {
			    amount = 0;
		   } 
		  
   	       for(Book b : booksOrder) {
    	    	   orderItem = new OrderItem();
    	    	   orderItem.setBookId(b.getId());
    	    	   orderItem.setOrderId(order.getId());
    	    	   orderItem.setQuantity(b.getQuantity());
    	    	   orderItem.setPrice(b.getPrice() * b.getQuantity());
    	    	   orderItemRepository.save(orderItem);
    	    	   amount += b.getPrice();
    	    	   Book book = bookService.getBookById(b.getId());
    	    	   book.setQuantity(book.getQuantity()-b.getQuantity());
    	    	   bookRepository.save(book);
    	       }

    	   order.setAmount(amount);
    	   orderRepository.save(order);
       }
	   
	    public  void deleteOrderItem(String idStr) {
	    	  
	    	   int id = Integer.parseInt(idStr);
	    	   Book book = null;
	    		   OrderItem orderItem = findOrderItem(id);
	    		   Optional<Order> o = orderRepository.findById(orderItem.getOrderId());
	    		   Order order = o.isPresent() ? o.get() : null;
	    		   orderItemRepository.delete(orderItem);    		
	       }
 
	    private OrderItem findOrderItem(int orderItemId) {
	    	
	    	Optional <OrderItem> orIt = orderItemRepository.findById(orderItemId);
	    	
	    	OrderItem orderItem = orIt.isPresent() ? orIt.get() : null;
	 		
	 		return orderItem;
		}
}
