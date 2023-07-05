package it.beije.suormary.rubrica.Char;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class RubricaJPA {

		public static List<Contact> loadRubricaJPA(EntityManager entityManager) {
			Scanner scanner = new Scanner(System.in);
            List<Contact> listContacts = null;

	    	 try {
	    		 System.out.print("Vuoi ordinare i contatti per nome e cognome? (si/no) : ");
	    		 String ord = scanner.nextLine();
	    		 Query query = null;
	    		 if(ord.equals("si")) query = entityManager.createQuery("SELECT c FROM Contact as c ORDER BY c.name,c.surname");
	    		 
	    		 else query = entityManager.createQuery("SELECT c FROM Contact as c");
	    		 
	    		  listContacts = query.getResultList();
	    		 
	    	 } catch(Exception e) {
	    		 System.out.println("Si è verificato un errore  : " + e.getMessage());
	    	 } 
	    	 return listContacts;
		}
		public static void writeRubricaJPA(List<Contact> contacts, EntityManager entityManager) {
		   	 try {
		   		 for(Contact c : contacts) {
		   			entityManager.persist(c);
		   		 }
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
	    		 Query query = entityManager.createQuery("SELECT c FROM Contact as c WHERE c.name = :name AND c.surname = :surname");
	    		 query.setParameter("name", name);
	    		 query.setParameter("surname", surname);
	    		 listContacts = query.getResultList();
	    		 
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
		public static void createContact(EntityManager entityManager) {
			 Scanner scanner = new Scanner(System.in);
	    	 List<Contact> listContacts = null;
	    	 try {
	    		 System.out.print("Inserisci il nome : ");
	    		 String name = scanner.nextLine();
	    		 System.out.print("Inserisci il cognome : ");
	    		 String surname = scanner.nextLine();
	    		 System.out.print("Inserisci l'email : ");
	    		 String email = scanner.nextLine();
	    		 System.out.print("Inserisci il numero di telefono : ");
	    		 String phone = scanner.nextLine();
	    		 System.out.print("Inserisci delle note : ");
	    		 String note = scanner.nextLine();
	    		 EntityTransaction transaction = entityManager.getTransaction();
	    		 transaction.begin();
	    		 Contact c = new Contact();
	    		 c.setName(name);
	    		 c.setSurname(surname);
	    		 c.setEmail(email);
	    		 c.setPhoneNumber(phone);
	    		 c.setNote(note);
	    		 System.out.println("Informazioni riguardo al nuovo contatto");
	    		 System.out.println(c);
	    		 System.out.print("Vuoi salvare il contatto? (si/no) : ");
	    		 String salva = scanner.nextLine();
	    		 if(salva.equals("si")) {
	    			 entityManager.persist(c);
	    			 transaction.commit();
	    			 System.out.println("Contatto salvato correttamente");
	    		 }
	    		 else System.out.println("Contatto non salvato");
	    		 
	    	 } catch(Exception e) {
	    		 System.out.println("Si è verificato un errore  : " + e.getMessage());
	    	 }
		}
		public static void updateContact(EntityManager entityManager) {
			 Scanner scanner = new Scanner(System.in);

			try {
				 Contact c = findContactByNameSurname(entityManager);
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				System.out.print("Inserisci il campo che vuoi modificare : ");
				String campo = scanner.nextLine();
				System.out.print("Inserisci il nuovo valore del campo : ");
				String valore = scanner.nextLine();
				switch(campo) {
				case "name" : c.setName(valore); break;
				case "surname" : c.setSurname(valore); break;
				case "email" : c.setEmail(valore); break;
				case "phone" : c.setPhoneNumber(valore); break;
				case "note" : c.setNote(valore); break;
				}
//				Query query = entityManager.createQuery("UPDATE Contact SET " +  campo + " = :valore WHERE id = :id");
//		         query.setParameter("valore", valore);
//		         query.setParameter("id", c.getId());
//		         query.executeUpdate();
				entityManager.persist(c);
			    transaction.commit();
			    System.out.println("Modifica eseguita");
			    System.out.print("Vuoi effettuare un'altra modifica? (si/no) : ");
			    String mod = scanner.nextLine();
			    if(mod.equals("si")) updateContact(entityManager);
			} catch (Exception e) {
				 System.out.println("Si è verificato un errore  : " + e.getMessage());
			} 
		}
		public static void deleteContact(EntityManager entityManager) {
			Scanner scanner = new Scanner(System.in);
			try {
				 Contact c = findContactByNameSurname(entityManager);
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();
				System.out.print("Sei sicuro di voler eliminare il contatto? (si/no) : ");
				String del = scanner.nextLine();
				if(del.equals("si")) {
					entityManager.remove(c);
					transaction.commit();
					System.out.println("Contatto eliminato");
				}
				else System.out.println("Contatto non eliminato");
			} catch (Exception e) {
				 System.out.println("Si è verificato un errore  : " + e.getMessage());
			} 
		}
		public static List<Contact> findDuplicatedContacts(EntityManager entityManager) {
			List<Contact> duplicatedContacts = new ArrayList<>();
			List<Contact> contacts = loadRubricaJPA(entityManager);

			 for (int i = 0; i < contacts.size(); i++) {
			        for (int j = i + 1; j < contacts.size(); j++) {
			            if (contacts.get(i).equals(contacts.get(j))) {
			                duplicatedContacts.add(contacts.get(i));
			                break; 
			            }
			        }
			    }
			 if(duplicatedContacts.size() == 0) {
				 System.out.println("Non sono stati trovati contatti duplicati"); 
			 }
			 else {
				 System.out.println("Contatti duplicati trovati : ");
					for(Contact c : duplicatedContacts) {
						System.out.println(c);
					} 
			 }
			return duplicatedContacts;
			
		}
		public static void mergeDuplicatedContacts(EntityManager entityManager) {
			List<Contact> duplicatedContacts = findDuplicatedContacts(entityManager);

			try {
				if(duplicatedContacts.size() > 0) {
					EntityTransaction  transaction = null;
			    
				//elimino tutti i contatti duplicati
			    for (Contact c : duplicatedContacts) {
			    	 transaction = entityManager.getTransaction();
			    	transaction.begin();
			    	Query query = entityManager.createQuery("DELETE FROM Contact WHERE name = :name AND surname = :surname AND email = :email AND note = :note AND phone = :phone");
			    	query.setParameter("name", c.getName());
			    	query.setParameter("surname", c.getSurname());
			    	query.setParameter("email", c.getEmail());
			    	query.setParameter("phone", c.getPhoneNumber());
			    	query.setParameter("note", c.getNote());
			    	query.executeUpdate();
			    	transaction.commit();
			    	
			    }
			    for (Contact c : duplicatedContacts) {
			    	Contact newContact = new Contact();
			    	newContact.setName(c.getName());
			    	newContact.setSurname(c.getSurname());
			    	newContact.setPhoneNumber(c.getPhoneNumber());
			    	newContact.setNote(c.getNote());
			    	newContact.setEmail(c.getEmail());
			    	 transaction = entityManager.getTransaction();
				    	transaction.begin();
			    	entityManager.persist(newContact);
			    	transaction.commit();
			    }
			    System.out.println("I contatti duplicati sono stati uniti");
			}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Si è verificato un errore nell`inserimento dei dati : " + e.getMessage());
			} 
		}
		 public static void exportDbToCSV (EntityManager entityManager) {
		  	   Scanner scanner = new Scanner(System.in);
		  	   System.out.print("Indica il path del file CSV : ");
		  	   String pathCSV = scanner.nextLine();
		  	   System.out.print("Indica il tipo di separatore per i campi : ");
		  	   String separator = scanner.nextLine();
		  	  List<Contact> contacts = loadRubricaJPA(entityManager);
		  	  EsRubrica.writeRubricaCSV(contacts, pathCSV, separator);
		     }
		     public static void exportDbToXML (EntityManager entityManager) {
		  	   Scanner scanner = new Scanner(System.in);
		  	   System.out.print("Indica il path del file XML : ");
		  	   String pathXML = scanner.nextLine();
		  	  List<Contact> contacts = loadRubricaJPA(entityManager);
		  	  EsRubrica.writeRubricaXML(contacts,pathXML);
		     }
		     public static void exportCSVToDb(EntityManager entityManager) {
		  	   Scanner scanner = new Scanner(System.in);
		  	   System.out.print("Indica il path del file CSV : ");
		  	   String pathFile = scanner.nextLine();
		  	   System.out.print("Indica il tipo di separatore per i campi : ");
		  	   String separator = scanner.nextLine();
		  	  List<Contact> contacts = EsRubrica.loadRubricaFromCSV(pathFile, separator);
		  	  writeRubricaJPA(contacts,entityManager );
		     }
		     public static void exportXMLToDb(EntityManager entityManager) {
		  	   Scanner scanner = new Scanner(System.in);
		  	   System.out.print("Indica il path del file CSV : ");
		  	   String pathFile = scanner.nextLine();
		   	  List<Contact> contacts = EsRubrica.loadRubricaFromXML(pathFile);
		   	  writeRubricaJPA(contacts,entityManager);
		      }
		


}
