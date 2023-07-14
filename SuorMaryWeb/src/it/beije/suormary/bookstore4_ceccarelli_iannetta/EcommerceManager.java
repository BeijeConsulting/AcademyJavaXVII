package it.beije.suormary.bookstore4_ceccarelli_iannetta;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class EcommerceManager {

    private Session session;
    private Transaction transaction;
    
    public User isUser(String email, String password) {
    	session = HBMsessionFactory.openSession();
    	Query<User> query = session.createQuery("SELECT u FROM User as u "
    										  + "WHERE email=:email AND "
    										  + "password=:password");
    	query.setParameter("email", email);
    	query.setParameter("password", password);

    	if (query.getResultList().size() == 0) return null;
    	return query.getResultList().get(0);
    }
    

    public User insertUser(String name, String surname, String email, String password) {
    	session = HBMsessionFactory.openSession();
        transaction = session.beginTransaction();
//        Query<User> query = session.createQuery("SELECT u FROM User as u "
//				  + "WHERE email=:email");
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
    	user.setPassword(password);
    	user.setCreationDate(LocalDateTime.now());
    	
    	try {
    		session.save(user);
    		transaction.commit();
    	} catch (Exception e) {
    		System.out.println("Non va bene");
    		user = null;
    	} finally {
        	session.close();    		
    	}
    
    	return user;
    }
    
}
