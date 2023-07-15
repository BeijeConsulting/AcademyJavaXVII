package it.beije.suormary.bookstore4_ceccarelli_iannetta;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.internal.build.AllowSysOut;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public class EcommerceManager {

    private EntityManager em;
    private EntityTransaction transaction;
    
    public User isUser(String email, String password) {
    	em = JPAEntityFactory.openEntity();
        transaction = em.getTransaction();
        transaction.begin();
        System.out.println("1");
    	Query query = em.createQuery("SELECT u from User as u WHERE u.email = :email AND password = :password");
    	System.out.println("2");
    	query.setParameter("email", email);
    	query.setParameter("password", password);
    	System.out.println("3");
    	
    	List <User> users = query.getResultList();
    	for (User u : users) System.out.println(u);
    	if (users.size() == 0) return null;
    	System.out.println("4");    	
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
    
}
