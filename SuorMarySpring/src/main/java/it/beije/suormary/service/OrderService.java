package it.beije.suormary.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.beije.suormary.model.Book;
import it.beije.suormary.model.Order;
import it.beije.suormary.model.OrderItem;
import it.beije.suormary.model.User;
import it.beije.suormary.repository.OrderItemRepository;
import it.beije.suormary.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private UserService userService;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private BookService bookService;
	
	 public Order createOrder(String email) {
		 User user = userService.loginUser(email);
             Order order = new Order();
             LocalDateTime date = LocalDateTime.now();
             order.setDate(date);
             order.setStatus("I");
             order.setUserId(user.getId());
             orderRepository.save(order);
         return order;
	 }

     public Order findOrder(int orderId) {
    	 
    	Optional<Order> o = orderRepository.findById(orderId);
 		
 		Order order = o.isPresent() ? o.get() : null;
 		
 		System.out.println("order : " + order);
 		
 		return order;
 	}


     
     public void deleteOrder(int orderId) {
    	 	Order orderFound = findOrder(orderId);
    	    List<OrderItem> orderItems = orderItemRepository.getListByOrderId(orderId);

    	    for (OrderItem orderItem : orderItems) {   	        
    	        Book book = bookService.getBookById(orderItem.getBookId());
    	        book.setQuantity(book.getQuantity() + orderItem.getQuantity()); 	    
    	        System.out.println(" QUI!! :" + orderItem);
    	        //orderFound.getItems().remove(orderItem);
    	        
    	        
    	        
    	    }
    	    orderItemRepository.deleteAll(orderItems);	  
    	    orderRepository.delete(orderFound);
    	    
    	    
    	}
     
     public List<Order> findByUserId(String email) {
    	 User user = userService.loginUser(email);
    	 Integer id = user.getId();
  	   
    	 List<Order> myOrders = orderRepository.findByUserId(id);

  	   return myOrders;
		
	}
  
     public  Order getOrderById(int orderId) {
  	   
  	   Order order = findOrder(orderId);
 
  		  List<OrderItem> orderItems= orderItemRepository.getListByOrderId(orderId);
  		  for(OrderItem orderItem : orderItems) {
  			  order.addOrderItem(orderItem);
  		  }
  	   
  	   return order;
     }
     
     public void payment(int orderId, String address) {
    	 Order order = findOrder(orderId);
    	 if(order!=null) {
    		 order.setStatus("P");
    		 order.setShippingAddress(address);
    		 orderRepository.save(order);
    	 }
    }

}
