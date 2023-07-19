package it.beije.suormary.service.bookstore1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import it.beije.suormary.bin.bookstore1.Author;
import it.beije.suormary.controller.bookstore1.JPAManagerFactory;

@Service
public class AuthorService {

	public void addAuthor(String name, String surname, String description) {
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
	
	public List<Author> getAuthorList(){
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
