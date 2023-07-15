package it.beije.sourmary.web.sala.prove;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import it.beije.sourmary.web.sala.*;
import it.beije.suormary.web.sala.Contatto;
import it.beije.suormary.web.sala.JPAEntityManagerFactory;

public class ContattoUtils {
	
	public static Contatto trovaContatto(String nome, String cognome) {
		Contatto c = null;
		EntityManager em = null;
		try {
			em = JPAEntityManagerFactory.createEM();
			
		
			Query query=em.createQuery("SELECT c FROM Contatto as c WHERE c.name=: nome AND c.surname=: cognome");
			query.setParameter("nome", nome); //il primo rappresenta il parametro nella query e il secondo quello passato dal metodo
			query.setParameter("cognome", cognome);
			c = (Contatto)query.getSingleResult();
					
			
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return c;
		
	}
	
}
