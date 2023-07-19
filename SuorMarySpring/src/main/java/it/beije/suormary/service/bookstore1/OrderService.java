package it.beije.suormary.service.bookstore1;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import dumpster.bookstore1.BookUtils;
import dumpster.bookstore1.OrderItemUtils;
import dumpster.bookstore1.UserUtils;
import it.beije.suormary.bin.bookstore1.Book;
import it.beije.suormary.bin.bookstore1.Cart;
import it.beije.suormary.bin.bookstore1.Order;
import it.beije.suormary.bin.bookstore1.OrderItem;

@Service
public class OrderService {

	public void createOrder(String address, HttpSession session) {
		EntityManager em = null;
		EntityTransaction transaction = null;
		try {
			
			em = JPAManagerFactory.getEntityManager();
			transaction = em.getTransaction();
			
			transaction.begin();
			
			int id = UserUtils.getUserId((String)session.getAttribute("email"));
			
			Order order = new Order();
					
			order.setUserId(id);
			order.setShippingAddress(address);
			order.setDate(LocalDateTime.now());
			order.setStatus('I');
			
			Map<Integer,Integer> cart = Cart.getCart(session);
			
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
	
	public void insertOrderItems(int orderId, Map<Book,Integer> books, EntityManager em, EntityTransaction transaction) throws Exception {

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
	
	public void deleteOrder(int idOrder) {
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
	
	public void editStatus(Character status, int orderId) {
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
	
	public List<Order> getOrders(int userId){
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
	
	public List<OrderItem> getOrderItems(Order o){
		int idO = o.getId();
		EntityManager entityManager = null;
		List<OrderItem> loi=null;
		
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			Query query = entityManager.createQuery("SELECT oi FROM OrderItem as oi WHERE oi.orderId = :idO  ");
			query.setParameter("idO", idO);
			
			loi = (List<OrderItem>) query.getResultList();
			Book b = null;
			for(int i=0; i<loi.size(); i++) {
				query= entityManager.createQuery("SELECT b FROM Book as b WHERE b.id = :id  ");
				query.setParameter("id", loi.get(i).getBookId());
				b=(Book)query.getSingleResult();
				loi.get(i).setBook(b);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			entityManager.close();
		}
		return loi;
	}
	
	
	
}
