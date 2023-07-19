package it.beije.suormary.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import it.beije.suormary.controller.Author;
import it.beije.suormary.controller.Book;
import it.beije.suormary.controller.BookStoreUtility;
import it.beije.suormary.controller.JPAmanagerFactory;

@Service
public class BookService {
	 public List<Author> getAuthors(){
  	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
  	   List<Author> listAuthors = null;
  	    try {
  	    	Query query = entityManager.createQuery("SELECT a FROM Author as a");
  	    	listAuthors = query.getResultList();
	
  	    } catch(Exception e) {
  	    	e.printStackTrace();
  	    	
  	    } finally {
  	    	entityManager.close();
  	    }
  	    return listAuthors;
     }
     public void addBook(String title, String description, String editor, String priceString, String quantityString, String authorIdStr) {
  	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
  	   int authorId = Integer.parseInt(authorIdStr);
  	   double price = Double.parseDouble(priceString);
  	   int quantity = Integer.parseInt(quantityString);
  	   try {
  		   EntityTransaction transaction = entityManager.getTransaction();
  		   transaction.begin();
  		   Book book = new Book();
  		   book.setTitle(title);
  		   book.setDescription(description);
  		   book.setEditor(editor);
  		   book.setQuantity(quantity);
  		   book.setPrice(price);
  		   book.setAuthorId(authorId);
  		   entityManager.persist(book);
  		   transaction.commit();
  		   
  	   } catch(Exception e) {
  		   e.printStackTrace();
  	   } finally {
  		   entityManager.close();
  	   }
     }
     public Book getBookById(String idStr) {
  	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
  	   Book book = null;
  	   int id = Integer.parseInt(idStr);
  	   
  	   try {
  		   Query query = entityManager.createQuery("SELECT b FROM Book as b WHERE b.id = :id ");
  		   query.setParameter("id", id);
  		   book = (Book) query.getSingleResult();
  		    		   
  	   } catch(Exception e) {
  		   
  	   } finally {
  		   entityManager.close();
  		   
  	   }
  	   return book;
     }
     public Book getBookById(int id) {
  	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
  	   Book book = null;
  	   
  	   try {
  		   Query query = entityManager.createQuery("SELECT b FROM Book as b WHERE b.id = :id ");
  		   query.setParameter("id", id);
  		   book = (Book) query.getSingleResult();
  		    		   
  	   } catch(Exception e) {
  		   
  	   } finally {
  		   entityManager.close();
  		   
  	   }
  	   return book;
     }
     public  void updateBook(String title, String description, String editor, String priceString, String quantityString, String authorIdStr,String bookIdStr) {
  	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
  	   int authorId = Integer.parseInt(authorIdStr);
  	   int bookId = Integer.parseInt(bookIdStr);
  	   double price = Double.parseDouble(priceString);
  	   int quantity = Integer.parseInt(quantityString);
  	   try {
  		   EntityTransaction transaction = entityManager.getTransaction();
  		   transaction.begin();
  		   Query query = entityManager.createQuery("SELECT b FROM Book as b WHERE b.id= :id");
  		   query.setParameter("id", bookId);
  		   Book book = (Book)query.getSingleResult();
  		   book.setTitle(title);
  		   book.setDescription(description);
  		   book.setEditor(editor);
  		   book.setQuantity(quantity);
  		   book.setPrice(price);
  		   book.setAuthorId(authorId);
  		   transaction.commit();
  		   
  	   } catch(Exception e) {
  		   e.printStackTrace();
  	   } finally {
  		   entityManager.close();
  	   }
     }
     public  void deleteBook(String idStr) {
  	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
  	   int id = Integer.parseInt(idStr);
  	   try {
  		   EntityTransaction transaction = entityManager.getTransaction();
  		   transaction.begin();
  		   Query query = entityManager.createQuery("SELECT b FROM Book as b WHERE b.id= :id");
  		   query.setParameter("id", id);
  		   Book book = (Book)query.getSingleResult();
  		   entityManager.remove(book);
  		   transaction.commit();
  		   
  	   } catch(Exception e) {
  		   e.printStackTrace();
  	   } finally {
  		   entityManager.close();
  	   }
     }
 
     public List<Book> loadBooks(){
  	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
  	   List<Book> listBooks = null;
  	    try {
  	    	Query query = entityManager.createQuery("SELECT b FROM Book as b");
  	    	listBooks = query.getResultList();
	
  	    } catch(Exception e) {
  	    	e.printStackTrace();
  	    	
  	    } finally {
  	    	entityManager.close();
  	    }
  	    return listBooks;
     }
}
