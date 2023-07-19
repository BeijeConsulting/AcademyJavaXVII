package it.beije.suormary.service.bookstore1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import it.beije.suormary.bin.bookstore1.Author;
import it.beije.suormary.bin.bookstore1.Book;
import it.beije.suormary.dumpster.bookstore1.BookUtils;

@Service
public class BookService {

	public List<Book> getAllBooks(){
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
	
	public Book getBook(int id) {
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
	
	public Map<Book,Integer> getBooks(Map<Integer,Integer> cart){
		
		Map<Book,Integer> books = new HashMap<>();
		Book b = null;
		for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
			b = BookUtils.getBook(entry.getKey());
			books.put(b, entry.getValue());
		}
		return books;
		
	}
	

	
	public void addNewBook(Book book){
		EntityManager em = null;
		try {
			em = JPAManagerFactory.getEntityManager();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
			em.persist(book);
			
			transaction.commit();
						
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
			
	}
	
}
