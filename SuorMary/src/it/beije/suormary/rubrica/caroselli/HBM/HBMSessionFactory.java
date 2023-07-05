package it.beije.suormary.rubrica.caroselli.HBM;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.beije.suormary.rubrica.caroselli.Contact;

public class HBMSessionFactory {

private HBMSessionFactory() {}
	
	private static SessionFactory sessionFactory;
	
	public static Session openSession() {
		if (sessionFactory == null) {
			System.out.println("creo SessionFactory...");
			
			Configuration configuration = new Configuration().configure()
					.addAnnotatedClass(Contact.class);

			sessionFactory = configuration.buildSessionFactory();
		}
		
		return sessionFactory.openSession();
	}
}
