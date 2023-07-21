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
     public Order findOrderById(String orderId) {
    	 
     	Integer id = Integer.valueOf(orderId);
     	Optional<Order> o = orderRepository.findById(id);
     	Order order = o.get();
     	return order;
  	}

     public void deleteOrder(int orderId) {
  	   
  	   Book book = null;
  	   Order orderFound = findOrder(orderId);
  	   List<OrderItem> orderItems = orderItemRepository.getListByOrderId(orderId);
  	   System.out.println("SIZE list"  + orderItems.size());
  	  System.out.println("SIZEE : " + orderFound.getItems().size());
  	  System.out.println(orderFound);
     	  for(OrderItem orderItem : orderFound.getItems()) {      
	   			orderItemRepository.delete(orderItem);    	    
     	       }

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
