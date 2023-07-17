package it.beije.suormary.bookstore1;

import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

public class OrderUtils {
	
	public static void createOrder(String address, HttpServletRequest request) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		try {
			
			em = JPAManagerFactory.getEntityManager();
			transaction = em.getTransaction();
			
			transaction.begin();
			
			int id = UserUtils.getUserId((String)request.getSession().getAttribute("email"));
			
			Order order = new Order();
					
			order.setUserId(id);
			order.setShippingAddress(address);
			order.setDate(LocalDateTime.now());
			order.setStatus('I');
			
			Map<Integer,Integer> cart = Cart.getCart(request);
			
			Map<Book,Integer> books = BookUtils.getBooks(cart);
			
			double amount = 0;
			for (Map.Entry<Book, Integer> entry : books.entrySet()) {
				double price = entry.getKey().getPrice() * entry.getValue();
				amount += price;
			}
			
			order.setAmount(amount);
			em.persist(order);
			transaction.commit();
			em.flush();
			
			int idOrder = order.getId();
			
			insertOrderItems(idOrder, books);
			
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	public static void insertOrderItems(int orderId, Map<Book,Integer> books) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		try {
			em = JPAManagerFactory.getEntityManager();
			transaction = em.getTransaction();

			transaction.begin();
			
			OrderItem om = null;
			Book book = null;
			for (Map.Entry<Book, Integer> entry : books.entrySet()) {
				om = new OrderItem();
				om.setBookId(entry.getKey().getId());
				om.setOrderId(orderId);
				om.setPrice(entry.getKey().getPrice());
				om.setQuantity(entry.getKey().getQuantity());
				em.persist(om);
				
				Query query = em.createQuery("SELECT b FROM Book as b WHERE b.id = :id");
				query.setParameter("id", entry.getKey().getId());
				book = (Book) query.getSingleResult();
				
				book.setQuantity(book.getQuantity() - entry.getValue());
				em.persist(book);
			}
			
			transaction.commit();
			
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
				deleteOrder(orderId);
				e.printStackTrace();
			}
		}finally {
			em.close();
		}
		
	}
	
	public static void deleteOrder(int idOrder) {
		EntityManager em = null;
		try {
			em = JPAManagerFactory.getEntityManager();
			EntityTransaction transaction = em.getTransaction();

			transaction.begin();
					
			Query query = em.createQuery("SELECT o FROM Order as o WHERE o.id = :id");
			query.setParameter("id", idOrder);
			Order order = (Order) query.getSingleResult();
			
			em.remove(order);
			
			transaction.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void editStatus(Character status, int orderId) {
		EntityManager em = null;
		try {
			em = JPAManagerFactory.getEntityManager();
			EntityTransaction transaction = em.getTransaction();

			transaction.begin();
					
			Query query = em.createQuery("SELECT o FROM Order as o WHERE o.id = :id");
			query.setParameter("id", orderId);
			Order order = (Order) query.getSingleResult();
			
			order.setStatus(status);
			em.persist(order);
			
			transaction.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
}
