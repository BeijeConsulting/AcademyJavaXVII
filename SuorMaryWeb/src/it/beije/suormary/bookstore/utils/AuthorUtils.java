package it.beije.suormary.bookstore.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.suormary.bookstore.entities.Author;
import it.beije.suormary.bookstore.entities.JPAManagerFactory;

public class AuthorUtils {
	
	public static boolean addAuthor(String name, String surname, String description) {
		Author author = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			
			entityManager = JPAManagerFactory.getEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			
			author = new Author(name, surname, description);
			entityManager.persist(author);
			
			transaction.commit();
			
			return true;
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			entityManager.close();
		}
	}
	
	public static List<Author> getAuthorList(){
		EntityManager entityManager = null;
		List<Author> la = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			Query query = entityManager.createQuery("SELECT a FROM Author as a");
			la=query.getResultList();
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return la;
	}
	
}
