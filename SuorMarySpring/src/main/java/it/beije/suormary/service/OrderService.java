package it.beije.suormary.service;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.controller.Book;
import it.beije.suormary.controller.JPAmanagerFactory;
import it.beije.suormary.controller.Order;
import it.beije.suormary.controller.OrderItem;
import it.beije.suormary.controller.User;

@Service
public class OrderService {
	@Autowired
	private UserService userService;
	
	 public Order createOrder(String email) {
  	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
  	   Order order = null;
  	   try {
  		   Query query = entityManager.createQuery("SELECT u FROM User as u WHERE u.email = :email");
  		   query.setParameter("email", email);
  		   User user =(User) query.getSingleResult();
              order = new Order();
             LocalDateTime date = LocalDateTime.now();
             order.setDate(date);
             order.setStatus("I");
             order.setUserId(user.getId());
  		   EntityTransaction transaction = entityManager.getTransaction();
  		   transaction.begin();
             entityManager.persist(order);
             transaction.commit();

  		   
  	   } catch(Exception e) {
  		   e.printStackTrace();
  	   } finally {
  		   entityManager.close();
  	   }
  	   return order;
     }

     public Order findOrder(int orderId) {
  	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
  	   Order orderFound = null;
  	   try {		
  		   orderFound = entityManager.find(Order.class, orderId);
 	       System.out.println(orderFound.toString());
  	   } catch(Exception e) {
  		   e.printStackTrace();
  	   } finally {
  		   entityManager.close();
  	   }
  	   return orderFound;
		
	}

     public void deleteOrder(int orderId) {
  	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
  	   Book book = null;
  	   try {
  		   Order orderFound = entityManager.find(Order.class, orderId);
     	       EntityTransaction transaction = entityManager.getTransaction();
     	       transaction.begin();
     	       Query query = entityManager.createQuery("SELECT o FROM OrderItem as o WHERE o.orderId = :id");
     	       query.setParameter("id", orderFound.getId());
     	       List<OrderItem> orderItems = query.getResultList();
     	       for(OrderItem orderItem : orderItems) {
     	    	 if(orderItem.getId() == orderItem.getId()) { 
	   				  book = entityManager.find(Book.class, orderItem.getBookId());
	   				  book.setQuantity(book.getQuantity() + orderItem.getQuantity());
	       	    	  entityManager.remove(orderItem); 
     	    	 }
     	       }
     	       entityManager.remove(orderFound);
     	       transaction.commit();
  		   
  	   } catch(Exception e) {
  		 e.printStackTrace();
  	   }  finally {
			   entityManager.close();
		   }
     }
     public List<Order> usersOrders(String email) {
  	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
  	   List<Order> myOrders = null;
  	   try {
  		   User user = userService.loginUser(email);
  		   Query query = entityManager.createQuery("SELECT o FROM Order as o WHERE o.userId = :userId");
     	       query.setParameter("userId", user.getId());
  		   myOrders = query.getResultList();
  		   
  	   } catch(Exception e) {
  		   e.printStackTrace();
  	   } finally {
  		   entityManager.close();
  	   }

  	   return myOrders;
		
	}
     
     public  Order getOrderById(int orderId) {
  	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
  	   Order order = null;
  	   try {
  		  order = entityManager.find(Order.class, orderId);
  		  Query query = entityManager.createQuery("SELECT o FROM OrderItem as o WHERE o.orderId = :id");
  		  query.setParameter("id", order.getId());
  		  List<OrderItem> orderItems= query.getResultList();
  		  for(OrderItem orderItem : orderItems) {
  			  order.addOrderItem(orderItem);
  		  }
  	   }catch(Exception e) {
  		   e.printStackTrace();
  	   } finally {
  		   entityManager.close();
  	   }
  	   return order;
     }
     
     
}
