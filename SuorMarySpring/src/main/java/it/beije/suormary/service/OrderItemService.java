package it.beije.suormary.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Service;

import it.beije.suormary.controller.Book;
import it.beije.suormary.controller.JPAmanagerFactory;
import it.beije.suormary.controller.Order;
import it.beije.suormary.controller.OrderItem;

@Service
public class OrderItemService {
	
	   public void createOrderItems(List<Book> booksOrder, int orderId) {
    	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
    	   OrderItem orderItem = null;
    	   EntityTransaction transaction = entityManager.getTransaction();
    	   transaction.begin();
    	   try {		   
    		   Order order = entityManager.find(Order.class, orderId);
    		   
   	       for(Book b : booksOrder) {
    	    	   orderItem = new OrderItem();
    	    	   orderItem.setBookId(b.getId());
    	    	   orderItem.setOrderId(order.getId());
    	    	   orderItem.setQuantity(b.getQuantity());
    	    	   orderItem.setPrice(b.getPrice() * b.getQuantity());
    	    	   entityManager.persist(orderItem);
    	    	   order.addOrderItem(orderItem);
    	    	   Book book = entityManager.find(Book.class, b.getId());
    	    	   book.setQuantity(book.getQuantity()-b.getQuantity());
    	       }
   	       double amount = 0;
   	       System.out.println(order.getItems().size());
   	       for(OrderItem orderIt : order.getItems()) {
   	    	   amount += orderIt.getPrice();
   	       } 
    	   order.setAmount(amount);
    	   transaction.commit();
 		   
    	   } catch(Exception e) {
    		   e.printStackTrace();
    	   } finally {
    		   entityManager.close();
    	   }
       }

}
