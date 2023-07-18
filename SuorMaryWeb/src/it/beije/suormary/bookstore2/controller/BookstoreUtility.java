package it.beije.suormary.bookstore2.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import javax.persistence.Query;

import it.beije.suormary.bookstore2.model.Author;
import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.model.Order;
import it.beije.suormary.bookstore2.model.OrderItem;
import it.beije.suormary.bookstore2.model.PersistenceManagerJPA;
import it.beije.suormary.bookstore2.model.User;

public class BookstoreUtility {

	public static List<Book> readBooksFromDb() {

		List<Book> newList = new ArrayList<>();
		EntityTransaction transaction = null;
		EntityManager entityManager = null;

		try {

			entityManager = PersistenceManagerJPA.getEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			Query query = entityManager.createQuery("SELECT b FROM Book as b");
			newList = query.getResultList();
			for (Book b : newList) {
				System.out.println(b.toString());

			}

			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

		return newList;
	}
	

	public static Book findBook(int bookId) {

		EntityManager entityManager = null;

		Book book = null;

		try {

			entityManager = PersistenceManagerJPA.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			book = entityManager.find(Book.class, bookId);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

		return book;

	}

	public static List<Order> readOrdersFromDb(int userId) {

		List<Order> orders = new ArrayList<>();
		List<OrderItem> booksInOrder = new ArrayList<>();
		EntityTransaction transaction = null;
		EntityManager entityManager = null;

		try {

			entityManager = PersistenceManagerJPA.getEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			Query query = entityManager.createQuery("SELECT o FROM Order as o WHERE user_id = '" + userId + "'");
			orders = query.getResultList();
			System.out.println(orders);
			for (Order o : orders) {
				Query query2 = entityManager
						.createQuery("SELECT oi FROM OrderItem as oi WHERE order_id = '" + o.getId() + "'");
				booksInOrder = query2.getResultList();
				o.setItems(booksInOrder);
				System.out.println(booksInOrder);
			}

			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

		return orders;
	}

	public static Order getOrderFromId(int orderId) {
		Order order = new Order();
		List<OrderItem> orderItems = new ArrayList<>();
		EntityTransaction transaction = null;
		EntityManager entityManager = null;

		try {

			entityManager = PersistenceManagerJPA.getEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			order = entityManager.find(Order.class, orderId);
			Query query = entityManager.createQuery("SELECT oi FROM OrderItem as oi WHERE order_id = '" + order.getId() + "'");
			orderItems = query.getResultList();
			order.setItems(orderItems);
			System.out.println(orderItems);
			transaction.commit();

		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

		return order;
	}
	
	public static Author findAuthorFromId(int authorId) {

		EntityManager entityManager = null;

		Author author = null;

		try {

			entityManager = PersistenceManagerJPA.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			author = entityManager.find(Author.class, authorId);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

		return author;

	}
	
	
	public static void insertABook(Book book) {

		EntityManager entityManager = null;

		try {

			entityManager = PersistenceManagerJPA.getEntityManager();

			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(book);
			transaction.commit();

			System.out.println(book.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

	}
	
	public static List<Author> getAllAuthor() {
		List<Author> authors = new ArrayList<>();
		EntityManager entityManager = null;
		
		try {

			entityManager = PersistenceManagerJPA.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			Query query = entityManager.createQuery("SELECT a FROM Author as a");
			authors = query.getResultList();
			System.out.println(authors);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

		
		return authors;
	}
	
	
	
	
	public static Author getAuthorFromId(int authorId) {
		Author author = new Author();
		EntityManager entityManager = null;
		
		try {

			entityManager = PersistenceManagerJPA.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			author = entityManager.find(Author.class, authorId);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

		
		return author;
	}
	
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
					transaction.commit();
					orderId = order.getId();
					for (OrderItem orderItem : order.getItems()) {
						orderItem.setOrderId(orderId);
						BookstoreUtility.insertOrderItem(orderItem);
			        }
					
				} catch (Exception e) {
					System.out.println("Insert non valido: " + order.toString());
					transaction.rollback();
					throw e; //rilancia eccezione al catch più esterno
				} 
					
			} else {
				System.out.println("Order mancante");
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
				System.out.println("OrderItem mancante");
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
	
	public static void cancelOrder(int orderId) {
		EntityManager entityManager = null;
		try {
			entityManager = PersistenceManagerJPA.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			
			try {
				Order order = entityManager.find(Order.class, orderId);
				List<OrderItem> items = BookstoreUtility.getOrderFromId(orderId).getItems();
				order.setStatus("C");
				order.setItems(items);
	            for (OrderItem orderItem : order.getItems()) {
	            	Book book = entityManager.find(Book.class, orderItem.getBookId());
	                book.setQuantity(book.getQuantity() + orderItem.getQuantity());
	            }
	            transaction.commit();

				} catch (Exception e) {
					System.out.println("Aggiornamento Cancel Order non valido id:" + orderId);
					transaction.rollback();
					throw e; //rilancia eccezione al catch più esterno
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
