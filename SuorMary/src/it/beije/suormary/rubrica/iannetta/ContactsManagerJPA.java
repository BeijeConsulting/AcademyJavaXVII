package it.beije.suormary.rubrica.iannetta;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class ContactsManagerJPA {
	
	private final String[] contactFields = {"id", "name", "surname", "telephone number", "email", "note"};
	private Scanner in;
	public EntityTransaction transaction;
	public EntityManager entityManager;
	
	public ContactsManagerJPA() {
		this.in = new Scanner(System.in);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
		this.entityManager = entityManagerFactory.createEntityManager();
		this.transaction = entityManager.getTransaction();
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
		String answer;
		for (int i = 1; i < contactFields.length; i++) {
			if (dataToRead[i]) {
				System.out.println("Enter " + contactFields[i] + ":");
				answer = in.nextLine();
				in.next();
				
				result[i] = answer;
				System.out.println(result[i]);
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
			System.out.println("Enter id of contact to edit:");	
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
		transaction.begin();
		Contact contact = entityManager.find(Contact.class, id);
		entityManager.remove(contact);
		transaction.commit();
	}
}
