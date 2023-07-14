package it.beije.suormary.bookstore1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class BookUtils {
	
	public static List<Book> getAllBooks(){
		EntityManager entityManager = null;
		List<Book> books = new ArrayList<Book>();
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			Query query = entityManager.createQuery("SELECT b FROM Book as b ");
			books = query.getResultList();
			
			for(int i=0; i<books.size(); i++) {
				query = entityManager.createQuery("SELECT a FROM Author as a WHERE a.id = :authId ");
				query.setParameter("authId", books.get(i).getAuthorId());
				Author a = (Author) query.getSingleResult();
				books.get(i).setAuthor(a);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			entityManager.close();
		}
		return books;
	}
	
	public static Book getBook(int id) {
		EntityManager entityManager = null;
		Book book = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			Query query = entityManager.createQuery("SELECT b FROM Book as b WHERE b.id = :id");
			query.setParameter("id", id);
			book = (Book) query.getSingleResult();
			
			
			query = entityManager.createQuery("SELECT a FROM Author as a WHERE a.id = :authId ");
			query.setParameter("authId", book.getAuthorId());
			Author a = (Author) query.getSingleResult();
			book.setAuthor(a);			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			entityManager.close();
		}
		return book;
	}
	
}
