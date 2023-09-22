package it.beije.suormary.bookstore.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import it.beije.suormary.bookstore.entities.Book;
import it.beije.suormary.bookstore.entities.JPAManagerFactory;
import it.beije.suormary.bookstore.entities.Order;
import it.beije.suormary.bookstore.entities.OrderItem;

public class OrderItemUtils {

	public static List<OrderItem> getOrderItems(Order o){
		int idO = o.getId();
		EntityManager entityManager = null;
		List<OrderItem> loi = new ArrayList<OrderItem>();
		
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
