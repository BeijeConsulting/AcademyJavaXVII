package it.beije.suormary.bookstore.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.suormary.bookstore.entities.Cart;
import it.beije.suormary.bookstore.entities.CartItem;
import it.beije.suormary.bookstore.entities.JPAManagerFactory;
import it.beije.suormary.bookstore.entities.Order;

public class CartUtils {
	
	public static List<CartItem> getCartItems(int userId) {
		EntityManager entityManager = null;
		List<CartItem> items = new ArrayList<CartItem>();
		
		try {
			
			entityManager = JPAManagerFactory.getEntityManager();
			
			Query query = entityManager.createQuery("SELECT c FROM CartItem as c WHERE c.userId = :userId  ");
			query.setParameter("userId", userId);
			
			items = query.getResultList();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			entityManager.close();
		}
		return items;
	}
	
	public static Cart getCart(int userId) {
		List<CartItem> items = getCartItems(userId);
		Cart cart = new Cart();
		cart.setItems(items);
		cart.setUserId(userId);
		return cart;
	}
	
	public static void deleteCart(int userId) {
		EntityManager em = null;
		try {
			em = JPAManagerFactory.getEntityManager();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
			Query query = em.createQuery("SELECT c FROM CartItem as c WHERE c.userId = :id");
			query.setParameter("id", userId);
			List<CartItem> items = query.getResultList();
			
			for(CartItem i : items) {
				em.remove(i);
			}
			
			transaction.commit();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void removeCartItem(int itemId) {
		EntityManager em = null;
		try {
			em = JPAManagerFactory.getEntityManager();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
			Query query = em.createQuery("SELECT c FROM CartItem as c WHERE c.id = :id");
			query.setParameter("id", itemId);
			CartItem item = (CartItem)query.getSingleResult();
			
			em.remove(item);
			
			transaction.commit();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void addCartItem(CartItem item) {
		EntityManager em = null;
		try {
			em = JPAManagerFactory.getEntityManager();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
		
			em.persist(item);
			
			transaction.commit();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
}
