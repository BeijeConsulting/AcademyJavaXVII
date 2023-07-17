package it.beije.suormary.bookstore4_ceccarelli_iannetta;

import java.time.LocalDateTime;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class EcommerceManager {

    private EntityManager em;
    private EntityTransaction transaction;
    
    public User isUser(String email, String password) {
    	em = JPAEntityFactory.openEntity();
       //transaction = em.getTransaction();
        //transaction.begin();
        
        //Query query = em.createQuery("SELECT u from User as u");
    	Query query = em.createQuery("SELECT u from User as u WHERE u.email = :email AND password = :password");

    	query.setParameter("email", email);
    	query.setParameter("password", password);

    	
    	List<User> users = query.getResultList();
    	
    	//for (User u : users) System.out.println(u);
    	if (users.size() == 0) return null;
    	
    	//transaction.commit();
    	em.close();
    	return users.get(0);
    }
    

    public User insertUser(String name, String surname, String email, String password) {
    	em = JPAEntityFactory.openEntity();
        transaction = em.getTransaction();
        transaction.begin();
//        Query<User> query = session.createQuery("SELECT u FROM User as u "
//				  + "WHERE email=:email");
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
    	user.setPassword(password);
    	user.setCreationDate(LocalDateTime.now());
    	
    	try {
    		em.persist(user);
    		transaction.commit();
    	} catch (Exception e) {
    		System.out.println("Non va bene");
    		user = null;
    	} finally {
        	em.close();    		
    	}
    
    	return user;
    }
    
    public List<Book> listBook(){
    	em = JPAEntityFactory.openEntity();
    	Query query = em.createQuery("SELECT b from Book as b order by b.title");
    	List<Book> books = query.getResultList();
    	if (books.size() == 0) return null;
    	em.close();
    	return books;
    }
    
    public List<Author> listAuthor(){
	   	em = JPAEntityFactory.openEntity();
       //transaction = em.getTransaction();
        //transaction.begin();

    	Query query = em.createQuery("SELECT a from Author as a order by a.surname");
    	List<Author> authors = query.getResultList();
    	if (authors.size() == 0) return null;
    	
    	//transaction.commit();
    	em.close();
    	return authors;
	}
        
    public void addAuthor(String name, String surname, String description) {
    	
    }
}
