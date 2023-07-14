package it.beije.suormary.bookstore3;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.suormary.bookstore.Book;
import it.beije.suormary.bookstore.User;
public class BookStoreUtility {
       public static void registerUser(String name, String surname, String email, String password, LocalDateTime date) {
    	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
    	   try {
    		   EntityTransaction transaction = entityManager.getTransaction();
    		   transaction.begin();
    		   User user = new User();
    		   user.setEmail(email);
    		   user.setName(name);
    		   user.setSurname(surname);
    		   user.setCreationDate(date);
    		   user.setPassword(password);
    		   entityManager.persist(user);
    		   transaction.commit();
    		   	   
    	   } catch(Exception e) {
    		   e.printStackTrace();
    	   } finally {
    		   entityManager.close();
    	   }
    	   
       }
       public static User loginUser(String email, String password) {
    	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
    	   User user = null;
    	   try {
    		   Query query = entityManager.createQuery("SELECT u FROM User as u WHERE u.email = :email AND u.password = :password");
    		   query.setParameter("email", email);
    		   query.setParameter("password", password);
    		    user = (User) query.getSingleResult();
    		   
    	   }catch(Exception e) {
    		   e.printStackTrace();
    		   
    	   } finally {
    		   entityManager.close();
    	   }
    	   return user;
       }
       public static List<Book> loadBooks(){
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