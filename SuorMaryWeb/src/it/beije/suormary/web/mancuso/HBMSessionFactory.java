package it.beije.suormary.web.mancuso;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HBMSessionFactory {
	private HBMSessionFactory() {};
	
	private static SessionFactory instance;
	
	public static Session getInstance() {
		if(instance == null) {
			Configuration configuration = new Configuration().configure()
					.addAnnotatedClass(Contact.class);
			
			instance = configuration.buildSessionFactory();
		}
		
		
		return instance.openSession();
	}
	
}
