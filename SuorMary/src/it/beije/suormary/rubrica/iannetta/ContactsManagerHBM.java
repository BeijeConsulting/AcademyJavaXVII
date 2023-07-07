package it.beije.suormary.rubrica.iannetta;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.build.AllowSysOut;
import org.hibernate.query.Query;
import org.xml.sax.SAXException;

public class ContactsManagerHBM {

	private Scanner in;
	private Scanner sc;
	private Session session;

	private final String[] contactFields = {"id", "name", "surname", "telephone number", "email", "note"};
	
 	public ContactsManagerHBM() {
		this.in = new Scanner(System.in);
		this.sc = new Scanner(System.in);
		this.session = HBMsessionFactory.openSession();
	}
	
	public void end() {
		in.close();
		sc.close();
		session.close();
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
		for (int i = 0; i < contactFields.length; i++) {
			if (dataToRead[i]) {
				System.out.println("Enter " + contactFields[i] + ":");
				//in.nextLine();
				result[i] = in.nextLine();				
			}
			else result[i] = null;
		}
		return result;
	}
	
 	public void showContacts(String orderBy) {
		
 		session = HBMsessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		if (!orderBy.equals("name") && !orderBy.equals("surname")) orderBy = "id";
		Query<Contact> query = session.createQuery("SELECT c FROM Contact as c ORDER BY " + orderBy); //SELECT * FROM rubrica
		List<Contact> contacts = query.getResultList();
		for (Contact c : contacts) System.out.println(c);
		
		transaction.commit();
		session.close();
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
	
	public void searchContact(){
		session = HBMsessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		System.out.println("Enter data (or part of it) to look for:");
		String answer = in.nextLine();
		
		Query<Contact> query = session.createQuery("SELECT c FROM Contact as c WHERE name LIKE '%" + answer + "%'  OR " + 
																				 "surname LIKE '%" + answer + "%' OR " + 
																		 	 "phoneNumber LIKE '%" + answer + "%' OR " +
																				   "email LIKE '%" + answer + "%' OR " +
																				    "note LIKE '%" + answer + "%'");
		List<Contact> contacts = query.getResultList();
		if(contacts.size() == 0) System.out.println("No contact matching \"" + answer + "\"");
		for (Contact c : contacts) System.out.println(c);
		
		transaction.commit();
		session.close();
	}

	public void insertContact() {
		session = HBMsessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Contact contact = new Contact();
		String [] newContactData = readData(new boolean[]{false, true, true, true, true, true});
		//System.out.println(Arrays.toString(newContactData));
		contact.setName(newContactData[1]);
		contact.setSurname(newContactData[2]);
		contact.setPhoneNumber(newContactData[3]);
		contact.setEmail(newContactData[4]);
		contact.setNote(newContactData[5]);
		session.save(contact);
		
		transaction.commit();
		session.close();
	}

	public int selectID(){
		int id;
		
		System.out.println("Enter id of contact. Enter 0 to see all contacts:");
		String answer = in.nextLine();
		if (answer.equals("0")) {
			sorting();
			System.out.println("Enter id of contact to edit:");	
			String a = in.next();
			in.nextLine();
			id = Integer.parseInt(a);
		} else id = Integer.parseInt(answer);
		
		return id;
	}

	public void editContact(int id) {
		session = HBMsessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Query<Contact> query = session.createQuery("SELECT c FROM Contact as c WHERE id = " + id);
		Contact contact = query.getSingleResult();
		System.out.println(contact);
		
		String[] updateContact  = readData(setDataToRead());
		
		//UPDATE
		contact.setId(id);
		if (updateContact[1] != null) contact.setName(updateContact[1]);
		if (updateContact[2] != null) contact.setSurname(updateContact[2]);
		if (updateContact[3] != null) contact.setPhoneNumber(updateContact[3]);
		if (updateContact[4] != null) contact.setEmail(updateContact[4]);
		if (updateContact[5] != null) contact.setNote(updateContact[5]);
		session.save(contact);

		transaction.commit();
		session.close();	
	}

	public void deleteContact(int id) {
		boolean isOpen = session.isOpen();
		Transaction transaction;
		if (!isOpen) session = HBMsessionFactory.openSession();
		transaction = session.beginTransaction();
		
		
		Query<Contact> query = session.createQuery("SELECT c FROM Contact as c WHERE id = " + id);
		Contact contact = query.getSingleResult();
		session.delete(contact);
		
		transaction.commit();
		if (!isOpen) session.close();
	}

	public List<List<Contact>> findDuplicates() {
		
		session = HBMsessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Query<Contact> query = session.createQuery("SELECT c FROM Contact as c"); //SELECT * FROM rubrica
		List<Contact> allContacts = query.getResultList();
		
		List<List<Contact>> listOfDuplicates = new ArrayList<>();
		String number;
		List<Contact> duplicates;
		while (allContacts.size() > 0) {
			number = allContacts.get(0).getPhoneNumber();
			query = session.createQuery("SELECT c FROM Contact as c WHERE phoneNumber = '" + number + "'");
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
		
		transaction.commit();
		session.close();
		return listOfDuplicates;
	}

	public void mergeDuplicates() {
		session = HBMsessionFactory.openSession();
		Transaction transaction;
		
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
			session = HBMsessionFactory.openSession();
			
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
			transaction = session.beginTransaction();
			session.save(keepThisContact);
			transaction.commit();
			deleteContact(id);
		}
		session.close();
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
		session = HBMsessionFactory.openSession();
		Transaction transaction = null; 
		
		System.out.println("Enter path of file containing contacts to add (.xml or .csv) : ");
		String path = in.nextLine();
		List<Contact> listOfContacts = null;
		
		ContactsList contacstList = new ContactsList();
		
		if (checkFile(path)) {
			if (path.endsWith(".xml")) {
				listOfContacts = contacstList.loadContactListFromXML(path);
				for (Contact con : listOfContacts) {
					transaction = session.beginTransaction();
					Contact newCon = new Contact();
					newCon.setName(con.getName());
					newCon.setSurname(con.getSurname());
					newCon.setPhoneNumber(con.getPhoneNumber());
					newCon.setEmail(con.getEmail());
					newCon.setNote(con.getNote());
					session.save(newCon);
					transaction.commit();
				}
			}
			else if (path.endsWith(".csv")) {
				System.out.println("Enter separator: ");
				String separator = in.nextLine();
				listOfContacts = contacstList.loadContactListFromCSV(path, separator);
				for (Contact con : listOfContacts) {
					transaction = session.beginTransaction();
					Contact newCon = new Contact();
					newCon.setName(con.getName());
					newCon.setSurname(con.getSurname());
					newCon.setPhoneNumber(con.getPhoneNumber());
					newCon.setEmail(con.getEmail());
					newCon.setNote(con.getNote());
					session.save(newCon);
					transaction.commit();
				}
			}
		}
		session.close();
		System.out.println("Contacts imported");
	}
	
	public void exportTo() throws IOException, ParserConfigurationException, SAXException, TransformerException {
		session = HBMsessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		System.out.println("Enter path of file where export contacts to (.xml or .csv) : ");
		String path = in.nextLine();
		List<Contact> listOfContacts = null;
		
		ContactsList contacstList = new ContactsList();
		
		if (path.endsWith(".xml")) {
			Query<Contact> query = session.createQuery("SELECT c FROM Contact as c");
			listOfContacts = query.getResultList();
			contacstList.writeContactListXLM(listOfContacts, path);
		}
		else if (path.endsWith(".csv")) {
			System.out.println("Enter separator: ");
			String separator = in.nextLine();
			Query<Contact> query = session.createQuery("SELECT c FROM Contact as c");
			listOfContacts = query.getResultList();
			contacstList.writeContactListCSV(listOfContacts, path, separator);
		}
		else System.out.println("Invalid format");
		session.close();
	}
	
}
