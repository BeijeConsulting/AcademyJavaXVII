package it.beije.suormary.bookstore2.controller;

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

}
