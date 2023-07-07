package it.beije.suormary.rubrica.iannetta;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.hibernate.internal.build.AllowSysOut;
import org.xml.sax.SAXException;


public class ContactsManagerJPA {
	
	private final String[] contactFields = {"id", "name", "surname", "telephone number", "email", "note"};
	private Scanner in;
	public EntityManager entityManager;
	
	public ContactsManagerJPA() {
		this.in = new Scanner(System.in);
		this.entityManager = (EntityManager) Singleton.getInstance();
	}
	
	public void end() {
		in.close();
		entityManager.close();
		System.out.println("Bye (:");
	}
	
	public boolean confirm() {
		System.out.println("1: Confirm\n0: Cancel");
		String answer = in.nextLine();
		if (!answer.equals("1")) {
			System.out.println("Ctrl + z :)");
			return false;
		}
		return true;
	}
	
	public boolean[] setDataToRead() {
		String answer;
		boolean[] result = new boolean[contactFields.length];
		result[0] = false; //id
		for (int i = 1; i < contactFields.length; i++) {
			System.out.println("Do you want to edit " + contactFields[i] + "? \n0: NO\n1: YES");
			answer = in.nextLine();
			//in.nextLine();
			if (answer.equals("1")) result[i] = true;
			else result[i] = false;
		}
		return result;
	}
	
	public String[] readData(boolean[] dataToRead){
		String result[] = new String[contactFields.length];
//		String answer;
		for (int i = 0; i < contactFields.length; i++) {
			if (dataToRead[i]) {
				System.out.println("Enter " + contactFields[i] + ":");
				result[i] = in.nextLine();
				//in.nextLine();
			}
			else result[i] = null;
		}
		//System.out.println(Arrays.toString(result));
		return result;
	}
	
	public void showContacts(String orderBy) {
		if (!orderBy.equals("name") && !orderBy.equals("surname")) orderBy = "id";
		
		Query query = entityManager.createQuery("SELECT c from Contact as c ORDER BY " + orderBy);
		List<Contact> contacts = query.getResultList();
		for (Contact c : contacts) System.out.println(c);
		query = entityManager.createQuery("SELECT count(c) from Contact as c");
		System.out.println("Total contacts: " + query.getSingleResult());
	}
	
	public void sorting(){		
		System.out.println("1: Sort by name" + 
						 "\n2: Sort by surname" + 
						 "\n0: Sort by id (default sorting)");
		String answer = in.nextLine();		
		if (answer.equals("1")) showContacts("name");
		else if (answer.equals("2")) showContacts("surname");
		else showContacts("id");
	}

	public void searchContact() {
		System.out.println("Enter data (or part of it) to look for:");
		String answer = in.nextLine();
		
		Query query = entityManager.createQuery("SELECT c FROM Contact as c WHERE c.name like :answer OR " + 
																				 "c.surname LIKE :answer OR " + 
																		 	 "c.phoneNumber LIKE :answer OR " +
																				   "c.email LIKE :answer OR " +
																				    "c.note LIKE :answer");
		query.setParameter("answer", "%" + answer + "%");
		List<Contact> contacts = query.getResultList();
		if(contacts.size() == 0) System.out.println("No contact matching \"" + answer + "\"");
		for (Contact c : contacts) System.out.println(c);
	}

	public void insertContact() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Contact contact = new Contact();
		String [] newContactData = readData(new boolean[]{false, true, true, true, true, true});
		//System.out.println(Arrays.toString(newContactData));
		
		contact.setName(newContactData[1]);
		contact.setSurname(newContactData[2]);
		contact.setPhoneNumber(newContactData[3]);
		contact.setEmail(newContactData[4]);
		contact.setNote(newContactData[5]);
		System.out.println(contact);
		if (confirm()) {
			entityManager.persist(contact);
			transaction.commit();
		}
		else transaction.rollback();
	}

	public int selectID(){
		int id;
		
		System.out.println("Enter id of contact. Enter 0 to see all contacts:");
		String answer = in.nextLine();
		if (answer.equals("0")) {
			sorting();
			System.out.println("Enter id of contact:");	
			String a = in.nextLine();
			//in.nextLine();
			id = Integer.parseInt(a);
		} else id = Integer.parseInt(answer);
		return id;
	}

	private void print(int id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Contact> cr = cb.createQuery(Contact.class);
		Root<Contact> root = cr.from(Contact.class);
		cr.select(root).where(cb.equal(root.get("id"), id));
		Query query = entityManager.createQuery(cr);
//		List<Contact> results = query.getResultList();
//		for (Contact c : results) System.out.println(c);
		System.out.println(query.getResultList());
		transaction.rollback();	//non so perchè, ma se metto transaction.commit() lui salva i cambiamenti
								//ma non me li fa vedere immediatamente, invece così sì. 
	}
	
	public void editContact(int id) {
		print(id);
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaUpdate<Contact> cu = cb.createCriteriaUpdate(Contact.class);
		Root<Contact> root = cu.from(Contact.class);
		
		String[] updateContact  = readData(setDataToRead());
		if (updateContact[1] != null) cu.set("name", updateContact[1]);
		if (updateContact[2] != null) cu.set("surname", updateContact[2]);
		if (updateContact[3] != null) cu.set("phoneNumber", updateContact[3]);
		if (updateContact[4] != null) cu.set("email", updateContact[4]);
		if (updateContact[5] != null) cu.set("note", updateContact[5]);
		
		if(confirm()) {
			cu.where(cb.equal(root.get("id"), id));
			entityManager.createQuery(cu).executeUpdate(); 
			transaction.commit();
			System.out.println("id: " + id + " edited");
		} 
		else transaction.rollback();
	}
	
	public void deleteContact(int id) {
		EntityTransaction transaction = entityManager.getTransaction();
		boolean isActive = transaction.isActive();
		if (!isActive) transaction.begin();
		else print(id);
		Contact contact = entityManager.find(Contact.class, id);
		
		/*serve per quando richiamo il metodo da merge perchè la transaction potrebbe già essere attiva
		quindi ho bisogno di differenziare i casi così però è consistente all if di prima
		se è solo delete chiede, se è da merge non chiede
		*/
		if(!isActive) {								
			if (confirm()) {						
				entityManager.remove(contact);		
				transaction.commit();	
				System.out.println("id: " + id + " deleted");
			}										
			else transaction.rollback();			
		}											
		else {
			entityManager.remove(contact);
			transaction.commit();					
		}
	}

	public List<List<Contact>> findDuplicates() {
		//EntityTransaction transaction = entityManager.getTransaction();
		//transaction.begin();
		
		String number;
		List<Contact> duplicates = new ArrayList<>();
		List<List<Contact>> listOfDuplicates = new ArrayList<>();
		Query query = entityManager.createQuery("SELECT c from Contact as c");
		List<Contact> allContacts = query.getResultList();
		
		while (allContacts.size() > 0) {
			number = allContacts.get(0).getPhoneNumber();
			query = entityManager.createQuery("SELECT c FROM Contact as c WHERE phoneNumber = '" + number + "'");
			duplicates = query.getResultList();
			if (duplicates.size() > 1) {
				listOfDuplicates.add(duplicates);
				for (Contact con : duplicates) {
					allContacts.remove(con);
				}
			}
			else allContacts.remove(0);
		}
		if (listOfDuplicates.size() == 0) System.out.println("No duplicates");
		for (List<Contact> duplicatesContacts : listOfDuplicates) {
			System.out.println(duplicatesContacts);
		}
		
		//transaction.commit();
		return listOfDuplicates;
	}

	public void mergeDuplicates() {
		
		EntityTransaction transaction = entityManager.getTransaction();

		List<List<Contact>> listOfDuplicates = findDuplicates();
		if (listOfDuplicates.size() > 1 && confirm()) {
			Contact keepThisContact;
			Contact checkThisContact;
			//int id;
			String name;
			String surname;
			String email;
			String note;
			String checkName;
			String checkSurname;
			String checkEmail;
			String checkNote;
			
			String answer;
//			boolean[] enterData = new boolean[contactFields.length];
//			Arrays.fill(enterData, false);
			
			for (List<Contact> duplicates: listOfDuplicates) {
				keepThisContact = duplicates.get(0);
				//id = keepThisContact.getId();
				
				name = keepThisContact.getName();
				if (name == null) {
					name = "";
					keepThisContact.setName(name);
				}
				
				surname = keepThisContact.getSurname();
				if (surname == null) {
					surname = "";
					keepThisContact.setName(surname);
				}
				
				//non dovrebbe essere null in generale
				if (keepThisContact.getPhoneNumber() == null) keepThisContact.setPhoneNumber("");
				
				email = keepThisContact.getEmail();
				if (email == null) {
					email = "";
					keepThisContact.setEmail(email);
				}
				
				note = keepThisContact.getNote();
				if (note == null) {
					note = "";
					keepThisContact.setNote(note);
				}
				
				for (int i = 1; i < duplicates.size(); i++) {
					checkThisContact = duplicates.get(i);
					//conflitti
					
					//nome
					checkName = checkThisContact.getName();
					if (checkName == null || checkName.contentEquals("") || name.equals(checkName));
					else {
						System.out.println("Conflict: Which name do you want to keep?");
						System.out.println("0: " + name);
						System.out.println("1: " + checkName);
						answer = in.nextLine();
						if (answer.equals("1")) keepThisContact.setName(checkName);
					}
					
					//cognome
					checkSurname = checkThisContact.getSurname();
					if (checkSurname == null || checkSurname.contentEquals("") || surname.equals(checkSurname));
					else {
						System.out.println("Conflict: Which surname do you want to keep?");
						System.out.println("0: " + surname);
						System.out.println("1: " + checkThisContact.getSurname());
						answer = in.nextLine();
						if (answer.equals("1")) keepThisContact.setSurname(checkThisContact.getSurname());
					}
					
					//email
					checkEmail = checkThisContact.getEmail();
					if (checkEmail == null || checkEmail.contentEquals("") || email.equals(checkEmail));
					else {
						System.out.println("Conflict: Which email do you want to keep?");
						System.out.println("0: " + email);
						System.out.println("1: " + checkThisContact.getEmail());
						answer = in.nextLine();
						if (answer.equals("1")) keepThisContact.setEmail(checkThisContact.getEmail());
					}
					
					//note
					checkNote = checkThisContact.getNote();
					if (checkNote == null || checkNote.contentEquals("") || note.equals(checkNote));
					else {
						System.out.println("Conflict: Which note do you want to keep?");
						System.out.println("0: " + note);
						System.out.println("1: " + checkThisContact.getNote());
						answer = in.nextLine();
						if (answer.equals("1")) keepThisContact.setNote(checkThisContact.getNote());
					}
					
					deleteContact(checkThisContact.getId());
				}
				transaction.begin();
				entityManager.persist(keepThisContact);
				transaction.commit();
				System.out.println("Contacts merged");
			}
		}
	}

	public boolean checkFile (String path) {
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("File does not exist");
			return false;
		}
		if (!path.endsWith(".xml") && !path.endsWith(".csv")) {
			System.out.println("Invalid format");
			return false;
		}
		return true;
	}

	public void importFrom() throws ParserConfigurationException, SAXException, IOException {
		EntityTransaction transaction = entityManager.getTransaction();
		
		System.out.println("Enter path of file containing contacts to add (.xml or .csv) : ");
		String path = in.nextLine();
		List<Contact> listOfContacts = null;
		
		if (confirm()) {
			ContactsList contacstList = new ContactsList();
			
			if (checkFile(path)) {
				if (path.endsWith(".xml")) {
					listOfContacts = contacstList.loadContactListFromXML(path);
					for (Contact con : listOfContacts) {
						transaction.begin();
						entityManager.persist(con);
						transaction.commit();
					}
				}
				else if (path.endsWith(".csv")) {
					System.out.println("Enter separator: ");
					String separator = in.nextLine();
					listOfContacts = contacstList.loadContactListFromCSV(path, separator);
					for (Contact con : listOfContacts) {
						transaction.begin();
						entityManager.persist(con);
						transaction.commit();
					}
				}
			}
			System.out.println("Contacts imported");
		}
		else transaction.rollback();
	}

	public void exportTo() throws ParserConfigurationException, SAXException, IOException, TransformerException{
		EntityTransaction transaction;
		transaction = entityManager.getTransaction();
	
		System.out.println("Enter path of file where export contacts to (.xml or .csv) : ");
		String path = in.nextLine();
		List<Contact> listOfContacts = null;
		
		ContactsList contacstList = new ContactsList();
		
		if (path.endsWith(".xml")) {
			
			transaction.begin();
			Query query = entityManager.createQuery("SELECT c from Contact as c");
			listOfContacts = query.getResultList();
			if (confirm()) {
				contacstList.writeContactListXLM(listOfContacts, path);
				transaction.commit();
			}
			else transaction.rollback();
		}
		
		else if (path.endsWith(".csv")) {
			System.out.println("Enter separator: ");
			String separator = in.nextLine();
			
			transaction.begin();
			Query query = entityManager.createQuery("SELECT c from Contact as c");
			listOfContacts = query.getResultList();
			if (confirm()) {
				contacstList.writeContactListCSV(listOfContacts, path, separator);
				transaction.commit();
			}
			else transaction.rollback();
		}
		
		else System.out.println("Invalid format");
	}
}