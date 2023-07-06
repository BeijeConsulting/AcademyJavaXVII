package it.beije.suormary.rubrica.sala;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;


public class RubricaUtilsJPA {
	
	public static List<Contatto> loadRubricaFromDB(){
			
			EntityManager em = null;
			List<Contatto> contacts=null;
			
			try {
				em = JPAEntityManagerFactory.createEM();
		
				Query query=null;
				
				query = em.createQuery("SELECT c FROM Contact as c");
				
				
				contacts = query.getResultList();
							
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				em.close();
			}
			
		return contacts;
	}
	
	
	public static List<Contatto> loadRubricaFromDBCON(){
		
		EntityManager em = null;
	    List<Contatto> contacts = null;

	    try {
	        em = JPAEntityManagerFactory.createEM();
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Contatto> query = cb.createQuery(Contatto.class);
	        Root<Contatto> root = query.from(Contatto.class);

	        query.select(root);

	        contacts = em.createQuery(query).getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }

	    return contacts;
	}
	
	
	public static List<Contatto> loadRubricaFromDBOrdinata(String ordine){
		
		//EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
		//EntityManager em = entityManagerFactory.createEntityManager();
		//EntityManager em=null;
		List<Contatto> contacts=null;
		EntityManager em=null;
		
		try {
			
			em=JPAEntityManagerFactory.createEM();
			
			//Query query = em.createQuery("SELECT c from Contatto as c"); 
			
			
			Query query=null;
			if(ordine.equalsIgnoreCase("n")) {
				query = em.createQuery("SELECT c FROM Contatto as c ORDER BY c.name");
			} else if(ordine.equalsIgnoreCase("c")) {
				query = em.createQuery("SELECT c FROM Contatto as c ORDER BY c.surname");
			} else {
				query = em.createQuery("SELECT c FROM Contatto as c");
			}
			
			contacts = query.getResultList();
						
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return contacts;
	}

	
	public static List<Contatto> loadRubricaFromDBOrdinataCON(String ordine){
		EntityManager em = null;
	    List<Contatto> contacts = null;

	    try {
	        em = JPAEntityManagerFactory.createEM();
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        CriteriaQuery<Contatto> cq = cb.createQuery(Contatto.class);
	        Root<Contatto> root = cq.from(Contatto.class);

	        if (ordine.equalsIgnoreCase("n")) {
	            cq.orderBy(cb.asc(root.get("name")));
	        } else if (ordine.equalsIgnoreCase("c")) {
	            cq.orderBy(cb.asc(root.get("surname")));
	        } else {
	        	cq.select(root);
	        }

	        contacts = em.createQuery(cq).getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }

	    return contacts;
	}
	
	public static List<Contatto> loadRubricaFromDBCerca(String searchName, String searchSurname){

		EntityManager em = null;
		
		List<Contatto> results=null;
		
		try {
			
			em = JPAEntityManagerFactory.createEM();
		
			//Transaction transaction = session.beginTransaction();
			
			String hql="SELECT DISTINCT c FROM Contatto c WHERE c.name = ?1 AND c.surname = ?2";
			Query query=em.createQuery(hql);
			query.setParameter(1, searchName);
			query.setParameter(2, searchSurname);
			
			results = query.getResultList();
						
			//selezionavo tutte le righe che presentavano stesso nome e cognome di quelli passati in ingresso
			//Se ci sono righe duplicate nella tabella, solo una di esse sarà inclusa nel risultato della query per via di
			//DISTINCT
			//creavo nuovi contatti da aggiungere a una lista a partire dalla lista di contatti ottenuta dalla query
			//??POTREI RITORNARE DIRETTAMENTE LA LISTA DI CONTATTI CHE MI RESTITUISCE LA QUERY DATO CHE è GIà UNA LISTA
			//DI CONTATTI?
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return results;
	}

	
	public static List<Contatto> loadRubricaFromDBCercaCON(String searchName, String searchSurname){

		EntityManager em = null;
		
		List<Contatto> results=null;
		
		try {
			
			em = JPAEntityManagerFactory.createEM();
			
		    CriteriaBuilder cb = em.getCriteriaBuilder();
		    CriteriaQuery<Contatto> query = cb.createQuery(Contatto.class);
		    Root<Contatto> root = query.from(Contatto.class);
		    //List<Predicate> predicate = new ArrayList<>();

		    Predicate nomePre=cb.equal(root.get("name"), searchName);
		    Predicate cognomePre=cb.equal(root.get("surname"), searchSurname);
		    Predicate finalePre=cb.and(nomePre, cognomePre);
		    
		    query.select(root).where(finalePre);
		    results=em.createQuery(query).getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return results;
	}
	
	
	public static Map<Integer, String> searchID(){
		
		EntityManager em = null;
		
		Map<Integer, String> map = null;
		
		try {
			
			em = JPAEntityManagerFactory.createEM();
			
			//String hql="SELECT new Contact(c.id as id, concat(c.name, ' ', c.surname) as fullName) FROM Contact c";
			String hql = "SELECT new Contatto(c.id as id, c.name as nome, c.surname as cognome) FROM Contatto c";
			Query query = em.createQuery(hql, Contatto.class);
			
			List<Contatto> results = query.getResultList();
			
			
			map= new HashMap<>();
			
			for(Contatto c : results ) {
				int index = c.getId();
				String name = c.getName();
				String surname = c.getSurname();
				String fullName=name+" "+surname;
				map.put(index, fullName);
			}
			
			
			//io selezionavo le colonne che mi servivano,
			//salvavo l'id corrente dentro un variabile intera
			//salvavo in uno stringBuilder nome e cognome separati dallo spazio
			//mappavo in una mappa ogni singolo valore
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		
		return map;
	}
	
	public static Map<Integer, String> searchIDCON(){
		EntityManager em = null;
		
		List<Contatto> contatti=null;
		Map<Integer, String> map=null;
		
		try {
			
			em = JPAEntityManagerFactory.createEM();
			
		    CriteriaBuilder cb = em.getCriteriaBuilder();
		    CriteriaQuery<Contatto> query = cb.createQuery(Contatto.class);
		    Root<Contatto> root = query.from(Contatto.class);
		    
		    //posso selezionare tutto e andare a mettere in una mappa solo le chiavi e nomi e cognomi
		    query.select(root);
		    
		    contatti = em.createQuery(query).getResultList();
		    map = new HashMap<>();
		    
		    for(Contatto c : contatti) {
		    	String nome = c.getName();
		    	String cognome = c.getSurname();
		    	String fullName=nome+" "+cognome;
		    	int id = c.getId();
		    	map.put(id, fullName);
		    }
		    
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return map;
	}

	public static void writeRubricaDBInsert(List<Contatto> contatti) {
				
		EntityManager em = null;
		
		try {
			em = JPAEntityManagerFactory.createEM();

			
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
			int numeroInseriti=0;
			for(Contatto c : contatti) {
				if(c!=null) {
					em.persist(c);
					numeroInseriti++;
				}
			}
			transaction.commit();
			System.out.println("sono stati inseriti: "+ numeroInseriti+" contatti");
			
			//INSERT
//			contact = new Contact();
//			//contact.setId(20);
//			contact.setName("Pippo");
//			contact.setSurname("Gialli");
//			contact.setPhoneNumber("09876543");
//			contact.setEmail("Pippo@beije.it");
//			contact.setNote("contatto inserito con Hibernate");
//			System.out.println("contact PRE : " + contact);
//			session.save(contact);
//			System.out.println("contact POST : " + contact);
			
//			transaction.commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void writeFromDBDelete(Integer id) {
		
		EntityManager em = null;
		
		try {
			em = JPAEntityManagerFactory.createEM();

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
			
			Contatto c=em.find(Contatto.class, id);
			
			if(c!=null) {
				em.remove(c);
				transaction.commit();
				System.out.println("il tuo contatto è stato cancellato");
			} else {
				System.out.println("il contatto con l'id specificato non è stato cancellato");
			}
			
			//DELETE
     		//session.remove(contact);
			//transaction.commit();
			
			
		} catch (Exception e) {
			//if(transaction!=null){
			//transaction.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static Contatto writeFromDBSet(String campo, String valore, Integer id) {
	
		EntityManager em = null;
		
		Contatto c=null;
		
		try {
			em=JPAEntityManagerFactory.createEM();

			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			
			
			//estraggo il contatto che ha l'id desiderato
			c=em.find(Contatto.class, id);
			System.out.println("contatto da modificare: " + c);
			if(campo.equalsIgnoreCase("nome")) {
				c.setName(valore);
				em.persist(c);
			} else if(campo.equalsIgnoreCase("cognome")) {
				c.setSurname(valore);
				em.persist(c);
			} else if(campo.equalsIgnoreCase("telefono")) {
				c.setPhoneNumber(valore);
				em.persist(c);
			} else if(campo.equalsIgnoreCase("email")) {
				c.setEmail(valore);
				em.persist(c);
			} else if(campo.equalsIgnoreCase("note")) {
				c.setNote(valore);
				em.persist(c);
			}
		
			//session.save(c);
			//System.out.println("contact POST : " + c);
		transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return c;	
		//UPDATE
//		System.out.println("contact PRE UPDATE: " + contact);
//		//contact.setId(30);
//		contact.setName("Alessandro");
//		contact.setSurname("Sala");
//		contact.setPhoneNumber("09876543");
//		contact.setEmail("Alessandro@beije.it");
//		contact.setNote("contatto modificato con JPA");
//		
//		System.out.println("contact PRE : " + contact);
//		entityManager.persist(contact);
//		System.out.println("contact POST : " + contact);
		
	}
	
	public static List<Contatto> groupBy() {
		
		EntityManager em = null;
				
		List<Contatto> contacts=null;
		
		try {
			 
			em = JPAEntityManagerFactory.createEM();
			
			//NON LEGGE QUESTA SINTASSI "SELECT c FROM Contact c GROUP BY c.name, c.surname, c.phoneNumber"
			String hql = "SELECT new Contatto (c.name, c.surname, c.phoneNumber) FROM Contatto c GROUP BY c.name, c.surname, c.phoneNumber";
			Query query=em.createQuery(hql);
			contacts=query.getResultList();
			
			
		} catch (Exception e) {
			//if(transaction!=null){
			//transaction.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return contacts;
		
		
	}
	
public static List<Contatto> groupByCON() {
		
		EntityManager em = null;
				
		List<Contatto> contacts=null;
		
		try {
			/* 
			em = JPAEntityManagerFactory.createEM();
			CriteriaBuilder cb = em.getCriteriaBuilder();
		    CriteriaQuery<Contatto> query = cb.createQuery(Contatto.class);
		    Root<Contatto> root = query.from(Contatto.class);
			
		    query.multiselect(root.get("name"), root.get("surname"), root.get("phoneNumber"));
		    query.groupBy(root.get("name"), root.get("surname"), root.get("phoneNumber"));
		    
		    contacts=em.createQuery(query).getResultList();*/
			
			/*em = JPAEntityManagerFactory.createEM();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<String> subquery = cb.createQuery(String.class);
	        Root<Contatto> subqueryRoot = subquery.from(Contatto.class);
	        subquery.select(subqueryRoot.get("name"))
	                .groupBy(subqueryRoot.get("name"))
	                .having(cb.gt(cb.count(subqueryRoot), 1));

	        // Query principale per ottenere i contatti duplicati
	        CriteriaQuery<Contatto> query = cb.createQuery(Contatto.class);
	        Root<Contatto> root = query.from(Contatto.class);
	        query.select(root)
	             .where(root.get("name").in(subquery));

	        contacts=em.createQuery(query).getResultList();*/
			
			
		} catch (Exception e) {
			//if(transaction!=null){
			//transaction.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return contacts;
		
		
	}

	/*public static void merge() {
		EntityManager em = null;
		List<Contatto> contatti = null;
	    try {
	        em = JPAEntityManagerFactory.createEM();
	        CriteriaBuilder cb = em.getCriteriaBuilder();
	        
	        // Criteri per identificare i contatti duplicati
	        CriteriaQuery<Contatto> duplicateQuery = cb.createQuery(Object[].class);
	        Root<Contatto> duplicateRoot = duplicateQuery.from(Contatto.class);
	        duplicateQuery.multiselect(duplicateRoot.get("name"), duplicateRoot.get("surname"), duplicateRoot.get("phoneNumber"))
	                      .groupBy(duplicateRoot.get("name"), duplicateRoot.get("surname"), duplicateRoot.get("phoneNumber"))
	                      .having(cb.gt(cb.count(duplicateRoot), 1));
	        
	     // Recupera i dati dei contatti duplicati
	       // List<Object[]> duplicateContacts = em.createQuery(duplicateQuery).getResultList();
	        contatti=em.createQuery(duplicateQuery).getResultList();

	        // Per ogni contatto duplicato, unifica i contatti
	        //for (Object[] duplicateContact : duplicateContacts) {
	          //  String name = (String) duplicateContact[0];
	            //String surname = (String) duplicateContact[1];
	            //String phoneNumber = (String) duplicateContact[2];
	        
	        for(Contatto c : contatti ) {
	        	String nome = c.getName();
	        	String cognome = c.getSurname();
	        	String numeroTelefono=c.getPhoneNumber();
	        }

	            CriteriaUpdate<Contatto> updateQuery = cb.createCriteriaUpdate(Contatto.class);
	            Root<Contatto> updateRoot = updateQuery.from(Contatto.class);

	            // Imposta il valore da mantenere per il contatto unificato (ad esempio, il contatto con il valore più recente)
	          /*  Expression<Integer> maxId = cb.max(updateRoot.get("id"));
	            updateQuery.set("unifiedValue", maxId)
	                       .where(cb.and(
	                           cb.equal(updateRoot.get("name"), name),
	                           cb.equal(updateRoot.get("surname"), surname),
	                           cb.equal(updateRoot.get("phoneNumber"), phoneNumber)
	                       ));

	            em.createQuery(updateQuery).executeUpdate();
	            System.out.println("duplicati unificati");
	        }QUI CHIUDE IL COMMENTO
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	}*/

}
