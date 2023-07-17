package it.beije.suormary.bookstore1;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class AuthorUtils {
	
	public static void addAuthor(String name, String surname, String description) {
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
			
		} catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
}
