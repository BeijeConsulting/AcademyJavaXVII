package it.beije.suormary.bookstore.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.suormary.bookstore.entities.Book;
import it.beije.suormary.bookstore.entities.CartItem;
import it.beije.suormary.bookstore.entities.JPAManagerFactory;
import it.beije.suormary.bookstore.entities.Order;
import it.beije.suormary.bookstore.entities.OrderItem;

public class OrderUtils {
	

	public static boolean createOrder(String address, int userId) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		List<Book> books = new ArrayList<Book>();
		
		try {
			
			em = JPAManagerFactory.getEntityManager();
			transaction = em.getTransaction();
			
			transaction.begin();
			
			Order order = new Order();
					
			order.setUserId(userId);
			order.setShippingAddress(address);
			order.setDate(LocalDateTime.now());
			order.setStatus('I');
			
			List<CartItem> cartItems = (List<CartItem>) CartUtils.getCartItems(userId);
			
			for(int i=0; i < cartItems.size(); i++) {
				int bookId = cartItems.get(i).getBookId();
				Book book = BookUtils.getBook(bookId);
				books.add(book);
			}
			
			double amount = 0;
			for (Book b : books) {
				double price = b.getPrice();
				amount += price;
			}
			
			order.setAmount(amount);
			em.persist(order);
			em.flush();
		
			int idOrder = order.getId();
			
			System.out.println("Ordine inserito, inizio gli item");
			insertOrderItems(idOrder, userId, em, transaction);
			
			CartUtils.deleteCart(userId, em);
			
			transaction.commit();
			
			return true;
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}finally {
			em.close();
		}
	}
	
	public static void insertOrderItems(int orderId, int userId, EntityManager em, EntityTransaction transaction) throws Exception {

			System.out.println("Item iniziati");
			OrderItem om = null;
			Book book = null;
			
			List<CartItem> cartItems = CartUtils.getCartItems(userId);
			
			for (CartItem ci : cartItems) {
				book = BookUtils.getBook(ci.getBookId());
				om = new OrderItem();
				om.setBookId(ci.getBookId());
				om.setOrderId(orderId);
				om.setPrice(book.getPrice());
				om.setQuantity(ci.getQuantity());
				em.persist(om);
				
				BookUtils.editBookQuantity(ci.getBookId(), ci.getQuantity(), em);
			}
			System.out.println("Item finiti");

	}
	
	public static boolean deleteOrder(int idOrder) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		try {
			em = JPAManagerFactory.getEntityManager();
			transaction = em.getTransaction();

			transaction.begin();
					
			Query query = em.createQuery("SELECT o FROM Order as o WHERE o.id = :id");
			query.setParameter("id", idOrder);
			Order order = (Order) query.getSingleResult();
			
			em.remove(order);
			
			transaction.commit();
			
			return true;
			
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}

			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}
	
	public static boolean editStatus(Character status, int orderId) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		try {
			em = JPAManagerFactory.getEntityManager();
			transaction = em.getTransaction();

			transaction.begin();
					
			Query query = em.createQuery("SELECT o FROM Order as o WHERE o.id = :id");
			query.setParameter("id", orderId);
			Order order = (Order) query.getSingleResult();
			
			order.setStatus(status);
			em.persist(order);
			
			transaction.commit();
			
			return true;
			
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}
	
	public static List<Order> getOrders(int userId){
		EntityManager em = null;
		List<Order> orders = new ArrayList<Order>();
		try {
			em = JPAManagerFactory.getEntityManager();
					
			Query query = em.createQuery("SELECT o FROM Order as o WHERE o.userId = :id");
			query.setParameter("id", userId);
			orders = (List<Order>) query.getResultList();
			
			for(int i=0; i<orders.size(); i++) {
				orders.get(i).setItems(OrderItemUtils.getOrderItems(orders.get(i)));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return orders;
	}
	
	public static Order getOrder(int orderId) {
		EntityManager em = null;
		Order order = null;
		try {
			em = JPAManagerFactory.getEntityManager();
			Query query = em.createQuery("SELECT o FROM Order as o WHERE o.id = :id");
			query.setParameter("id", orderId);
			order = (Order) query.getSingleResult();
	
			order.setItems(OrderItemUtils.getOrderItems(order));
		
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return order;
	}
	
}
