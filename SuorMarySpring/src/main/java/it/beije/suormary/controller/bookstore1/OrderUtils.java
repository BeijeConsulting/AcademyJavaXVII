package it.beije.suormary.controller.bookstore1;

import java.time.LocalDateTime;
import java.util.List;
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
			em.flush();
			
			
			
			int idOrder = order.getId();
			
			System.out.println("Ordine inserito, inizio gli item");
			insertOrderItems(idOrder, books, em, transaction);
			transaction.commit();
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	public static void insertOrderItems(int orderId, Map<Book,Integer> books, EntityManager em, EntityTransaction transaction) throws Exception {

			System.out.println("Item iniziati");
			OrderItem om = null;
			Book book = null;
			for (Map.Entry<Book, Integer> entry : books.entrySet()) {
				om = new OrderItem();
				om.setBookId(entry.getKey().getId());
				om.setOrderId(orderId);
				om.setPrice(entry.getKey().getPrice());
				om.setQuantity(entry.getValue());
				em.persist(om);
				
				Query query = em.createQuery("SELECT b FROM Book as b WHERE b.id = :id");
				query.setParameter("id", entry.getKey().getId());
				book = (Book) query.getSingleResult();
				
				book.setQuantity(book.getQuantity() - entry.getValue());
				em.persist(book);
			}
			System.out.println("Item finiti");

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
	
	public static List<Order> getOrders(int userId){
		EntityManager em = null;
		List<Order> orders = null;
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
	
}
