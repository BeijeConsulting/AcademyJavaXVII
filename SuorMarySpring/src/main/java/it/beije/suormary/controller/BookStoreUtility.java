package it.beije.suormary.controller;
import java.time.LocalDateTime;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.suormary.model.Author;
import it.beije.suormary.model.Book;
import it.beije.suormary.model.Order;
import it.beije.suormary.model.OrderItem;
import it.beije.suormary.model.User;

public class BookStoreUtility {
       public static void registerUser(String name, String surname, String email, String password, LocalDateTime date) { 
    	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
    	   try {
    		   EntityTransaction transaction = entityManager.getTransaction();
    		   transaction.begin();
    		   User user = new User();
    		   user.setEmail(email);
    		   user.setName(name);
    		   user.setSurname(surname);
    		   user.setCreationDate(date);
    		   user.setPassword(password);
    		   entityManager.persist(user);
    		   transaction.commit();
    		   	   
    	   } catch(Exception e) {
    		   e.printStackTrace();
    	   } finally {
    		   entityManager.close();
    	   }
    	   
       }
       public static User loginUser(String email) {
    	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
    	   User user = null;
    	   try {
    		   Query query = entityManager.createQuery("SELECT u FROM User as u WHERE u.email = :email");
    		   query.setParameter("email", email);
    		  // query.setParameter("password", password);
    		    user = (User) query.getSingleResult();
    		   
    	   }catch(Exception e) {
    		   e.printStackTrace();
    		   
    	   } finally {
    		   entityManager.close();
    	   }
    	   return user;
       }
       public static List<Book> loadBooks(){
    	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
    	   List<Book> listBooks = null;
    	    try {
    	    	Query query = entityManager.createQuery("SELECT b FROM Book as b");
    	    	listBooks = query.getResultList();
  	
    	    } catch(Exception e) {
    	    	e.printStackTrace();
    	    	
    	    } finally {
    	    	entityManager.close();
    	    }
    	    return listBooks;
       }
       public static List<Author> getAuthors(){
    	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
    	   List<Author> listAuthors = null;
    	    try {
    	    	Query query = entityManager.createQuery("SELECT a FROM Author as a");
    	    	listAuthors = query.getResultList();
  	
    	    } catch(Exception e) {
    	    	e.printStackTrace();
    	    	
    	    } finally {
    	    	entityManager.close();
    	    }
    	    return listAuthors;
       }
       public static void addBook(String title, String description, String editor, String priceString, String quantityString, String authorIdStr) {
    	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
    	   int authorId = Integer.parseInt(authorIdStr);
    	   double price = Double.parseDouble(priceString);
    	   int quantity = Integer.parseInt(quantityString);
    	   try {
    		   EntityTransaction transaction = entityManager.getTransaction();
    		   transaction.begin();
    		   Book book = new Book();
    		   book.setTitle(title);
    		   book.setDescription(description);
    		   book.setEditor(editor);
    		   book.setQuantity(quantity);
    		   book.setPrice(price);
//    		   book.setAuthor(author);
    		   entityManager.persist(book);
    		   transaction.commit();
    		   
    	   } catch(Exception e) {
    		   e.printStackTrace();
    	   } finally {
    		   entityManager.close();
    	   }
       }
       public static Book getBookById(String idStr) {
    	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
    	   Book book = null;
    	   int id = Integer.parseInt(idStr);
    	   
    	   try {
    		   Query query = entityManager.createQuery("SELECT b FROM Book as b WHERE b.id = :id ");
    		   query.setParameter("id", id);
    		   book = (Book) query.getSingleResult();
    		    		   
    	   } catch(Exception e) {
    		   
    	   } finally {
    		   entityManager.close();
    		   
    	   }
    	   return book;
       }
       public static Book getBookById(int id) {
    	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
    	   Book book = null;
    	   
    	   try {
    		   Query query = entityManager.createQuery("SELECT b FROM Book as b WHERE b.id = :id ");
    		   query.setParameter("id", id);
    		   book = (Book) query.getSingleResult();
    		    		   
    	   } catch(Exception e) {
    		   
    	   } finally {
    		   entityManager.close();
    		   
    	   }
    	   return book;
       }
       public static void updateBook(String title, String description, String editor, String priceString, String quantityString, String authorIdStr,String bookIdStr) {
    	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
    	   int authorId = Integer.parseInt(authorIdStr);
    	   int bookId = Integer.parseInt(bookIdStr);
    	   double price = Double.parseDouble(priceString);
    	   int quantity = Integer.parseInt(quantityString);
    	   try {
    		   EntityTransaction transaction = entityManager.getTransaction();
    		   transaction.begin();
    		   Query query = entityManager.createQuery("SELECT b FROM Book as b WHERE b.id= :id");
    		   query.setParameter("id", bookId);
    		   Book book = (Book)query.getSingleResult();
    		   book.setTitle(title);
    		   book.setDescription(description);
    		   book.setEditor(editor);
    		   book.setQuantity(quantity);
    		   book.setPrice(price);
//    		   book.setAuthorId(authorId);
    		   transaction.commit();
    		   
    	   } catch(Exception e) {
    		   e.printStackTrace();
    	   } finally {
    		   entityManager.close();
    	   }
       }
       public static void deleteBook(String idStr) {
    	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
    	   int id = Integer.parseInt(idStr);
    	   try {
    		   EntityTransaction transaction = entityManager.getTransaction();
    		   transaction.begin();
    		   Query query = entityManager.createQuery("SELECT b FROM Book as b WHERE b.id= :id");
    		   query.setParameter("id", id);
    		   Book book = (Book)query.getSingleResult();
    		   entityManager.remove(book);
    		   transaction.commit();
    		   
    	   } catch(Exception e) {
    		   e.printStackTrace();
    	   } finally {
    		   entityManager.close();
    	   }
       }
       public static Order createOrder(String email) {
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
       public static void createOrderItems(List<Book> booksOrder, int orderId) {
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

       public static Order findOrder(int orderId) {
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

       public static void payment(int orderId, String address) {

    	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
    	   Order orderFound = null;
    	   try {	
    		   EntityTransaction transaction = entityManager.getTransaction();
        	   transaction.begin();
    		   Order order = entityManager.find(Order.class, orderId);
    		   order.setStatus("P");
    		   order.setShippingAddress(address);
    		   transaction.commit();
    	   } catch(Exception e) {
    		   e.printStackTrace();
    	   } finally {
    		   entityManager.close();
    	   }
    	 
	}

       public static Author getAuthorById(int id) {
    	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
    	   Author author = null;
    	   try {
    		   Query query = entityManager.createQuery("SELECT a FROM Author as a WHERE a.id = :id");
    		   query.setParameter("id", id);
    		    author = (Author) query.getSingleResult();
    	   }catch(Exception e) {
    		   e.printStackTrace();
    	   } finally {
    		   entityManager.close();
    	   }
    	   return author;
       }
       public static void deleteOrder(int orderId) {
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
       public static void deleteOrderItem(String idStr) {
    	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
    	   int id = Integer.parseInt(idStr);
    	     EntityTransaction transaction = entityManager.getTransaction();
    		   transaction.begin();
    	   Book book = null;
    	   try {
    		   OrderItem orderItem = entityManager.find(OrderItem.class, id);
    		   Order order = entityManager.find(Order.class, orderItem.getOrderId());
    		   for(OrderItem ord : order.getItems()) {
    			   if(ord.getId() == orderItem.getId()) { 
    				  book = entityManager.find(Book.class, ord.getBookId());
    				  book.setQuantity(book.getQuantity() + ord.getQuantity());
    				  order.getItems().remove(ord);
    			   }
    		   }
    		 
    		   
	    	  
    		   entityManager.remove(orderItem);
    		   transaction.commit();
    				   
    	   } catch(Exception e) {
    		 e.printStackTrace();
    	   }  finally {
			   entityManager.close();
		   }
    	   
       }
       public static Order getOrderById(int orderId) {
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
       public static void saveOrderModified(List<Book> booksOrder, int orderId) {
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

       public static List<Order> usersOrders(String email) {
    	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
    	   List<Order> myOrders = null;
    	   try {
    		   User user = loginUser(email);
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
      
       

}
