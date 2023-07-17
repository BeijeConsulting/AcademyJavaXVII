package it.beije.suormary.bookstore1;

import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;

public class OrderUtils {
	
	public static void createOrder(String address, HttpServletRequest request) {
		EntityManager em = null;
		
		try {
			
			em = JPAManagerFactory.getEntityManager();
			EntityTransaction transaction = em.getTransaction();
			
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
			
			OrderItem om = null;
			for (Map.Entry<Book, Integer> entry : books.entrySet()) {
				om = new OrderItem();
				om.setBookId(entry.getKey().getId());
				om.setOrderId(id);
				em.persist(om);
				
				
			}
			
		} catch(Exception e) {
			
		}finally {
			
		}
	}
	
}
