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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.hibernate.Transaction;
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
	}
	
	public boolean[] setDataToRead() {
		int answer;
		boolean[] result = new boolean[contactFields.length];
		result[0] = false; //id
		for (int i = 1; i < contactFields.length; i++) {
			System.out.println("Do you want to edit " + contactFields[i] + "? \n0: NO\n1: YES");
			answer = in.nextInt();
			if (answer == 1) result[i] = true;
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
				in.nextLine();
				result[i] = in.nextLine();
			}
			else result[i] = null;
		}
		System.out.println(Arrays.toString(result));
		return result;
	}
	
	public void showContacts(String orderBy) {
		if (!orderBy.equals("name") && !orderBy.equals("surname")) orderBy = "id";
		
		Query query = entityManager.createQuery("SELECT c from Contact as c ORDER BY " + orderBy);
		List<Contact> contacts = query.getResultList();
		for (Contact c : contacts) System.out.println(c);
	}
	
	public void sorting() throws SQLException, ClassNotFoundException {		
		System.out.println("1: Sort by name" + 
						 "\n2: Sort by surname" + 
						 "\n0: Sort by id (default sorting)");
		int answer = in.nextInt();		
		if (answer == 1) showContacts("name");
		else if (answer == 2) showContacts("surname");
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
		entityManager.persist(contact);
		transaction.commit();
	}

	public int selectID() throws ClassNotFoundException, SQLException {
		int id;
		
		System.out.println("Enter id of contact. Enter 0 to see all contacts:");
		String answer = in.next();
		if (answer.equals("0")) {
			sorting();
			System.out.println("Enter id of contact:");	
			String a = in.next();
			in.nextLine();
			id = Integer.parseInt(a);
		} else id = Integer.parseInt(answer);
		
		return id;
	}

	public void editContact(int id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Contact contact = entityManager.find(Contact.class, id);
		System.out.println(contact);
		
		String[] updateContact  = readData(setDataToRead());
		//contact.setID(id);
		if (updateContact[1] != null) contact.setName(updateContact[1]);
		if (updateContact[2] != null) contact.setSurname(updateContact[2]);
		if (updateContact[3] != null) contact.setPhoneNumber(updateContact[3]);
		if (updateContact[4] != null) contact.setEmail(updateContact[4]);
		if (updateContact[5] != null) contact.setNote(updateContact[5]);
		transaction.commit();
		entityManager.persist(contact);
	}
	
	public void deleteContact(int id) {
		
		EntityTransaction transaction = entityManager.getTransaction();
		boolean isActive = transaction.isActive();
		if (!isActive) transaction.begin();
		Contact contact = entityManager.find(Contact.class, id);
		entityManager.remove(contact);
		if(!isActive) transaction.commit();
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
		for (List<Contact> duplicatesContacts : listOfDuplicates) {
			System.out.println(duplicatesContacts);
		}
		
		//transaction.commit();
		return listOfDuplicates;
	}

	public void mergeDuplicates() {
		EntityTransaction transaction = entityManager.getTransaction();

		List<List<Contact>> listOfDuplicates = findDuplicates();
		
		Contact keepThisContact;
		Contact checkThisContact;
		int id;
		String name;
		String surname;
		String email;
		String note;
		String checkName;
		String checkSurname;
		String checkEmail;
		String checkNote;
		
		String answer;
//		boolean[] enterData = new boolean[contactFields.length];
//		Arrays.fill(enterData, false);
		
		for (List<Contact> duplicates: listOfDuplicates) {
			transaction.begin();
			keepThisContact = duplicates.get(0);
			
			id = keepThisContact.getId();
			
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
			entityManager.persist(keepThisContact);
			//deleteContact(id);
			transaction.commit();
		}
		System.out.println("Contacts merged");
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
		
		ContactsList contacstList = new ContactsList();
		
		if (checkFile(path)) {
			if (path.endsWith(".xml")) {
				listOfContacts = contacstList.loadContactListFromXML(path);
				for (Contact con : listOfContacts) {
					transaction.begin();;
					entityManager.persist(con);
					transaction.commit();
				}
			}
			else if (path.endsWith(".csv")) {
				System.out.println("Enter separator: ");
				String separator = in.nextLine();
				listOfContacts = contacstList.loadContactListFromCSV(path, separator);
				for (Contact con : listOfContacts) {
					transaction.begin();;
					entityManager.persist(con);
					transaction.commit();
				}
			}
		}
		System.out.println("Contacts imported");
	}

	public void exportTo() throws IOException, ParserConfigurationException, SAXException, TransformerException {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
	
		System.out.println("Enter path of file where export contacts to (.xml or .csv) : ");
		String path = in.nextLine();
		List<Contact> listOfContacts = null;
		
		ContactsList contacstList = new ContactsList();
		
		if (path.endsWith(".xml")) {
			Query query = entityManager.createNamedQuery("SELECT c FROM Contact as c");
			listOfContacts = query.getResultList();
			contacstList.writeContactListXLM(listOfContacts, path);
		}
		else if (path.endsWith(".cvs")) {
			System.out.println("Enter separator: ");
			String separator = in.nextLine();
			Query query = entityManager.createNamedQuery("SELECT c FROM Contact as c");
			listOfContacts = query.getResultList();
			contacstList.writeContactListCSV(listOfContacts, path, separator);
		}
		else System.out.println("Invalid format");
	}

}