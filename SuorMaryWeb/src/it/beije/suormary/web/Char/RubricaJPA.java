package it.beije.suormary.web.Char;

import java.util.ArrayList;


import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transaction;

public class RubricaJPA {

		public static List<Contact> loadRubricaJPA(EntityManager entityManager) {
			Scanner scanner = new Scanner(System.in);
            List<Contact> listContacts = null;
            List<ContactDetail> listContactDetails = null;

	    	 try {
	    		 CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    		 CriteriaQuery<Contact> criteriaQuery = criteriaBuilder.createQuery(Contact.class);
	    		 Root<Contact> contactRoot = criteriaQuery.from(Contact.class);    		 
	    			 criteriaQuery.select(contactRoot);
	    			 listContacts = entityManager.createQuery(criteriaQuery).getResultList();
	    			 
		    		 CriteriaQuery<ContactDetail> criteriaQuery2 = criteriaBuilder.createQuery(ContactDetail.class);
		    		 Root<ContactDetail> contactDetailRoot = criteriaQuery2.from(ContactDetail.class);    		 
		    			 criteriaQuery2.select(contactDetailRoot);
		    			 listContactDetails = entityManager.createQuery(criteriaQuery2).getResultList();
		    			 for(Contact c : listContacts) {
		    				 for(ContactDetail cd : listContactDetails) {
		    					 if(cd.getId_rubrica() == c.getId()) {
		    						 c.addContactDetail(cd);
		    					 }
		    				 }
		    			 }
	    	 } catch(Exception e) {
	    		 System.out.println("Si è verificato un errore  : " + e.getMessage());
	    	 }  finally {
	    		 entityManager.close();
	    	 }
	    	 return listContacts;
		}
		public static void writeRubricaJPA(List<Contact> contacts, EntityManager entityManager) {
		   	 try {
		   		 EntityTransaction transaction = entityManager.getTransaction();
		   		 transaction.begin();
		   		 for(Contact c : contacts) {
		   			entityManager.persist(c);
		   		 }
		   		transaction.commit();
		   	 }catch(Exception e) {
		   		 e.printStackTrace();
		   	 } 
			}
		public static Contact findContactByNameSurname(EntityManager entityManager) throws Exception {
		     Scanner scanner = new Scanner(System.in);
	    	 List<Contact> listContacts = null;
	    	 try {
	    		 System.out.print("Inserisci il nome : ");
	    		 String name = scanner.nextLine();
	    		 System.out.print("Inserisci il cognome : ");
	    		 String surname = scanner.nextLine();
	    		 CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
	    		 CriteriaQuery<Contact> criteriaQuery = criteriaBuilder.createQuery(Contact.class);
	    		 Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
	    		 criteriaQuery.select(contactRoot).where(criteriaBuilder.equal(contactRoot.get("name"), name),criteriaBuilder.equal(contactRoot.get("surname"), surname));

	    		 listContacts =  entityManager.createQuery(criteriaQuery).getResultList();
	    		 
	    	 } catch(Exception e) {
	    		 System.out.println("Si è verificato un errore  : " + e.getMessage());
	    	 } 
			 for(int i = 0; i < listContacts.size(); i++) {
				 System.out.println(i + ". " + listContacts.get(i));
			 }
	    	 if(listContacts.size() == 1) return listContacts.get(0);
			 else if (listContacts.size() == 0) throw new Exception("Non sono stati trovati contatti con quel nome e cognome");
			 System.out.print("Sono stati trovati più contatti con lo stesso nome e cognome, inserisci il numero del contatto che ti interessa : ");
				 int num = scanner.nextInt();
				 scanner.nextLine();
				 return listContacts.get(num);
			
			
		}
		public static void findContact(EntityManager entityManager) {
			try {
				Contact c = findContactByNameSurname(entityManager);
				System.out.println("Informazioni riguardo al contatto selezionato...");
				System.out.println(c);
			} catch (Exception e) {
				 System.out.println("Si è verificato un errore  : " + e.getMessage());
			}
		}
		public static void createContact(String name, String surname, String note, EntityManager entityManager) {
	    	 List<Contact> listContacts = null;
	    	 try {
	    		
	    		 EntityTransaction transaction = entityManager.getTransaction();
	    		 transaction.begin();
	    		 Contact c = new Contact();
	    		 c.setName(name);
	    		 c.setSurname(surname);
	    		 c.setNote(note);
	    			 entityManager.persist(c);
	    			 transaction.commit();
	    		 
	    	 } catch(Exception e) {
	    		 System.out.println("Si è verificato un errore  : " + e.getMessage());
	    	 } finally {
	    		 entityManager.close();
	    	 }
		}
		public static void createContactDetail(String idRubricaString, String contatto, String tipoString, String label, EntityManager entityManager) {
			int idRubrica = Integer.parseInt(idRubricaString);
			Character tipo =  tipoString.charAt(0);
			try {
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				 ContactDetail contactDetail = new ContactDetail();
				 contactDetail.setContatto(contatto);
				 contactDetail.setId_rubrica(idRubrica);
				 contactDetail.setTipo(tipo);
				 contactDetail.setLabel(label);
				 entityManager.persist(contactDetail);
				 transaction.commit();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				entityManager.close();
			}
		}
		public static void updateContact(String idString, String name, String surname, String note,EntityManager entityManager) {
			int id = Integer.parseInt(idString);
			Contact c = null;
			try {
				Query query = entityManager.createQuery("SELECT c FROM Contact as c WHERE c.id = :id");
				query.setParameter("id", id);
				 c = (Contact) query.getSingleResult();
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				 c.setName(name);
				 c.setSurname(surname); 
//				 c.setEmail(email); 
//				 c.setPhoneNumber(phone);
				 c.setNote(note); 
				
				entityManager.persist(c);
			    transaction.commit();
			    
			} catch (Exception e) {
				 System.out.println("Si è verificato un errore  : " + e.getMessage());
			} finally {
				entityManager.close();
			}
		}
		public static void updateContactDetail(String idString, String contatto, String tipoString, String label, EntityManager entityManager) {
			int id = Integer.parseInt(idString);
			ContactDetail cd = null;
			Character tipo = tipoString.charAt(0);
			try {
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				 Query query = entityManager.createQuery("Select c FROM ContactDetail as c WHERE c.id = :id");
				 query.setParameter("id", id);
				 cd = (ContactDetail) query.getSingleResult();
				 cd.setContatto(contatto);
				 cd.setLabel(label);
				 cd.setTipo(tipo);
				 transaction.commit();
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				entityManager.close();
			}
		}
		public static ContactDetail findContactDetail(EntityManager entityManager, int id) {
			ContactDetail cd = null;
			try {
				Query query = entityManager.createQuery("SELECT cd FROM ContactDetail as cd WHERE cd.id = :id");
				query.setParameter("id", id);
				 cd = (ContactDetail)query.getSingleResult();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				entityManager.close();
			}
			return cd;
		}
		public static void deleteContact(EntityManager entityManager, String idString) {
			int id = Integer.parseInt(idString);
			Contact c = null;
			try {
				Query query = entityManager.createQuery("SELECT c FROM Contact as c WHERE c.id = :id");
				query.setParameter("id", id);
				 c = (Contact) query.getSingleResult();
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				Query query2 = entityManager.createQuery("DELETE FROM ContactDetail cd  WHERE cd.id_rubrica = :id");
				query2.setParameter("id", id);
				query2.executeUpdate();
					entityManager.remove(c);
					transaction.commit();
			} catch (Exception e) {
				 e.printStackTrace();
			} finally {
				entityManager.close();
			}
		}
		public static Contact findContactById(EntityManager entityManager,String idString) {
			int id = Integer.parseInt(idString);
			Contact c = null;
			try {
				Query query = entityManager.createQuery("SELECT c FROM Contact as c WHERE c.id = :id");
				query.setParameter("id", id);
				 c = (Contact) query.getSingleResult();
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				entityManager.close();
			}
			return c;
		}

		 public static void exportDbToCSV (EntityManager entityManager) {
		  	   Scanner scanner = new Scanner(System.in);
		  	   System.out.print("Indica il path del file CSV : ");
		  	   String pathCSV = scanner.nextLine();
		  	   System.out.print("Indica il tipo di separatore per i campi : ");
		  	   String separator = scanner.nextLine();
		  	  List<Contact> contacts = loadRubricaJPA(entityManager);
		  	  EsRubrica.writeRubricaCSV(contacts, pathCSV, separator);
		  	  System.out.println("I contatti sono stati importati nel file csv");
		     }
		     public static void exportDbToXML (EntityManager entityManager) {
		  	   Scanner scanner = new Scanner(System.in);
		  	   System.out.print("Indica il path del file XML : ");
		  	   String pathXML = scanner.nextLine();
		  	  List<Contact> contacts = loadRubricaJPA(entityManager);
		  	  EsRubrica.writeRubricaXML(contacts,pathXML);
		  	System.out.println("I contatti sono stati importati nel file xml");
		     }
		     public static void exportCSVToDb(EntityManager entityManager) {
		  	   Scanner scanner = new Scanner(System.in);
		  	   System.out.print("Indica il path del file CSV : ");
		  	   String pathFile = scanner.nextLine();
		  	   System.out.print("Indica il tipo di separatore per i campi : ");
		  	   String separator = scanner.nextLine();
		  	  List<Contact> contacts = EsRubrica.loadRubricaFromCSV(pathFile, separator);
		  	  writeRubricaJPA(contacts,entityManager );
		  	System.out.println("I contatti sono stati importati nel database");
		     }
		     public static void exportXMLToDb(EntityManager entityManager) {
		  	   Scanner scanner = new Scanner(System.in);
		  	   System.out.print("Indica il path del file XML : ");
		  	   String pathFile = scanner.nextLine();
		   	  List<Contact> contacts = EsRubrica.loadRubricaFromXML(pathFile);
		   	  writeRubricaJPA(contacts,entityManager);
		   	  System.out.println("I contatti sono stati importati nel database");
		      }
		


}
