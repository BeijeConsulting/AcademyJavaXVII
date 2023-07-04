package exercises;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class RubricaHBM {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contact.class);
		
		SessionFactory factory = configuration.buildSessionFactory();
		
		Session session = null;
		
		try {
			session = factory.openSession();
			
//			Transaction transaction = session.getTransaction();
//			transaction.begin();
			Transaction transaction = session.beginTransaction();
			
			Contact contact = null;
			
			//INSERT
//			contact = new Contact();
//			//contact.setId(20);
//			contact.setName("Pippo");
//			contact.setSurname("Gialli");
//			contact.setPhoneNumber("09876543");
//			contact.setEmail("Pippo@beije.it");
//			contact.setNote("contatto inserito con Hibernate");
//			
//			System.out.println("contact PRE : " + contact);
//			session.save(contact);
//			System.out.println("contact POST : " + contact);
			
//			transaction.commit();
		

			//SELECT HQL
			Query<Contact> query = session.createQuery("SELECT c FROM Contact as c"); //SELECT * FROM rubrica
			List<Contact> contacts = query.getResultList();
			for (Contact c : contacts) System.out.println(c);

			//UPDATE
			contact = contacts.get(contacts.size()-1);
			System.out.println("contact PRE UPDATE: " + contact);
//			contact.setId(10);
//			contact.setName("Chiara");
//			contact.setSurname("Sala");
//			contact.setPhoneNumber("09876543");
//			contact.setEmail("lara.sala@beije.it");
//			contact.setNote("contatto modificato con Hibernate");
//			
//			System.out.println("contact PRE : " + contact);
//			session.save(contact);
//			System.out.println("contact POST : " + contact);
			
			
			//DELETE
//			session.delete(contact);
			
			transaction.commit();
			//transaction.rollback();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}
}
