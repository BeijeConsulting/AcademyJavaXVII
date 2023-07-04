package it.beije.suormary.rubrica.Char;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class RubricaHBM {
     public static void main(String[] args) {
    	 Configuration configuration = new Configuration().configure().addAnnotatedClass(Contact.class);
    	 SessionFactory sessionFactory = configuration.buildSessionFactory();
    	 Session session = null;
    	 try {
    		 session = sessionFactory.openSession();
    		 Transaction transaction = session.beginTransaction();
    		 //SELECT
    		 Query<Contact> query = session.createQuery("SELECT c FROM Contact");
    		 List<Contact> listContacts = query.getResultList();
    		 for(Contact c : listContacts) {
    			 System.out.println(c);
    		 }
    		 
    	 } catch(Exception e) {
    		 e.printStackTrace();
    	 } finally {
    		 session.close();
    	 }
     }
}
