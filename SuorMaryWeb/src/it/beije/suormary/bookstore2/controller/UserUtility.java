package it.beije.suormary.bookstore2.controller;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.model.Order;
import it.beije.suormary.bookstore2.model.OrderItem;
import it.beije.suormary.bookstore2.model.PersistenceManagerJPA;
import it.beije.suormary.bookstore2.model.User;

public class UserUtility {
	
	public static void insertUser(User user) {
		EntityManager entityManager = null;
		
		try {
			entityManager = PersistenceManagerJPA.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			
			User newUser = new User();
			
			if(user!=null) {
				try {
					newUser.setEmail(user.getEmail());
					newUser.setPassword(user.getPassword());
					newUser.setSurname(user.getSurname());
					newUser.setName(user.getName());
					newUser.setCreationDate(LocalDateTime.now());
					entityManager.persist(newUser); // salva l'user nel database
					transaction.commit();
				} catch (Exception e) {
					System.out.println("Insert non valido: " + user.toString());
					transaction.rollback();
					throw e; //rilancia eccezione al catch più esterno
				} 
					
			} else {
				System.out.println("User mancante");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				entityManager.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static User checkUser(String email, String password) {
		EntityManager entityManager = null;
		User user=null;
		try {
			entityManager = PersistenceManagerJPA.getEntityManager();
			// Recupera l'utente dal database utilizzando JPA

	        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
	        query.setParameter("email", email);
	        query.setParameter("password", password);
	        user = (User) query.getSingleResult();
	        
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				entityManager.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	public static int insertOrder(Order order) {
		EntityManager entityManager = null;
		int orderId = -1;
		try {
			entityManager = PersistenceManagerJPA.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			
			if(order!=null) {
				try {
					entityManager.persist(order); // salva l'order nel database
//					for (OrderItem orderItem : order.getItems()) {
//						entityManager.persist(orderItem);
//						Book book = entityManager.find(Book.class, orderItem.getBookId());
//						book.setQuantity(book.getQuantity()-orderItem.getQuantity());
//			        }
					transaction.commit();
					orderId = order.getId();
					for (OrderItem orderItem : order.getItems()) {
						orderItem.setOrderId(orderId);
						UserUtility.insertOrderItem(orderItem);
			        }
					
				} catch (Exception e) {
					System.out.println("Insert non valido: " + order.toString());
					transaction.rollback();
					throw e; //rilancia eccezione al catch più esterno
				} 
					
			} else {
				System.out.println("User mancante");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				entityManager.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return orderId;
	}
	
	public static void insertOrderItem(OrderItem orderItem) {
		EntityManager entityManager = null;
		try {
			entityManager = PersistenceManagerJPA.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			
			if(orderItem!=null) {
				try {
					entityManager.persist(orderItem); // salva l'order nel database
					Book book = entityManager.find(Book.class, orderItem.getBookId());
					book.setQuantity(book.getQuantity()-orderItem.getQuantity());
					transaction.commit();
				} catch (Exception e) {
					System.out.println("Insert non valido: " + orderItem.toString());
					transaction.rollback();
					throw e; //rilancia eccezione al catch più esterno
				} 
					
			} else {
				System.out.println("User mancante");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				entityManager.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
