package it.beije.suormary.rubrica.trapani;

import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import it.beije.suormary.rubrica.Contact;
import it.beije.suormary.rubrica.JPAentity;

public class ManagerJPA {
	
	public static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
//		List<Contact> contattiContacts = listContacts();
		List<Contact> contattiContacts =findContacts();
//		insertContact();
//		
//		updateContact();
//		List<Contact> contattiContacts = listContacts();
//
//		deleteContact();
		
//		List<Contact> contatti= listContacts();
		
		
		JPAentity.getEntityManager().close();
	}

	public static List<Contact> listContacts() {
		
		EntityManager eM = JPAentity.getEntityManager();
		System.out.println("Lista contatti ordinata? (y/n): ");
		String answ = in.nextLine().toLowerCase();
		
		if(answ.equals("n")) {
			CriteriaBuilder cb = eM.getCriteriaBuilder();			
			CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);		//creo oggetto query
			Root<Contact> contact = cq.from(Contact.class);				
			cq.select(contact);												//uso la select
			TypedQuery<Contact> q = eM.createQuery(cq);						//query con select
			List<Contact> contacts = q.getResultList();						//salvo in lista
			
			for (Contact c : contacts) 
				System.out.println(c);
			
			return contacts;
			} else {		//LISTA CONTATTI ORDINATA
				System.out.println("Ordinamento per nome o cognome? (n/c): ");
				answ=in.nextLine().toLowerCase();
				
				switch(answ) {
				
				case "n":   CriteriaBuilder cb = eM.getCriteriaBuilder();			
							CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);		//creo oggetto query
							Root<Contact> contact = cq.from(Contact.class);				
							cq.orderBy(cb.asc(contact.get("name")));										//uso la select
							TypedQuery<Contact> q = eM.createQuery(cq);						//query con select
							List<Contact> contacts = q.getResultList();		
							for (Contact c : contacts) 
								System.out.println(c);
							return contacts;
				
				case "c": 	CriteriaBuilder cb1 = eM.getCriteriaBuilder();			
							CriteriaQuery<Contact> cq1 = cb1.createQuery(Contact.class);		//creo oggetto query
							Root<Contact> contact1 = cq1.from(Contact.class);				
							cq1.orderBy(cb1.asc(contact1.get("surname")));										//uso la select
							TypedQuery<Contact> q1 = eM.createQuery(cq1);						//query con select
							List<Contact> contacts1 = q1.getResultList();	
							for (Contact c : contacts1) 
								System.out.println(c);
							return contacts1;
				default:	System.out.println("Comando non riconosciuto"); 
							break;
			}
		}
		return null;
		
		
//		Query query = eM.createQuery("SELECT c from Contact as c"); 
//		List<Contact> contacts = query.getResultList();

//		return contacts;
	}

	public static List<Contact> findContacts() {
		
		EntityManager eM = JPAentity.getEntityManager();
		
//		Query query = eM.createQuery("SELECT c from Contact as c WHERE c.name LIKE :nome OR c.surname LIKE :cognome "
//				+ "OR c.phoneNumber LIKE :telefono OR c.email LIKE :email OR c.note LIKE :note");
//		
//		System.out.println("Inserisci valore da cercare: ");
//		String search = "%" + in.nextLine() + "%";
//		
//		query.setParameter("nome", search);
//		query.setParameter("cognome", search);
//		query.setParameter("telefono", search);
//		query.setParameter("email", search);
//		query.setParameter("note", search);
//
//		List<Contact> contacts = query.getResultList();
//		
//		for (Contact c : contacts) 
//			System.out.println(c);
		
		CriteriaBuilder cb = eM.getCriteriaBuilder();
		CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
		Root<Contact> contact = cq.from(Contact.class);
		Predicate[] predicates = new Predicate[5];
		System.out.println("Inserisci valore da cercare: ");
		String search = "%" + in.nextLine() + "%";
		
		predicates[0] = cb.like(contact.get("name"), search);
		predicates[1] = cb.like(contact.get("surname"), search);
		predicates[2] = cb.like(contact.get("phoneNumber"), search);
		predicates[3] = cb.like(contact.get("email"), search);
		predicates[4] = cb.like(contact.get("note"), search);
		
		cq.select(contact).where(cb.or(predicates));
		TypedQuery<Contact> q = eM.createQuery(cq);	
		List<Contact> contacts = q.getResultList();		
		for (Contact c : contacts) 
			System.out.println(c);
		return contacts;
		
	}
	
	public static void insertContact() {
		
		EntityManager eM = JPAentity.getEntityManager();
		
		EntityTransaction transaction= eM.getTransaction();
		transaction.begin();
		
		Contact contact = new Contact();
		
		System.out.println("Inserisci dati nuovo contatto\n Nome: ");
		contact.setName(in.nextLine());
		System.out.println("Cognome:");
		contact.setSurname(in.nextLine());
		System.out.println("Telefono:");
		contact.setPhoneNumber(in.nextLine());
		System.out.println("Email:");
		contact.setEmail(in.nextLine());
		System.out.println("Note:");
		contact.setNote(in.nextLine());
		
		eM.persist(contact);
		
		transaction.commit();
	}
	
	public static void updateContact() {
		EntityManager eM = JPAentity.getEntityManager();
		
		EntityTransaction transaction= eM.getTransaction();
		transaction.begin();
		
		listContacts();

		System.out.println("Seleziona id contatto da modificare:");
		int idagg = in.nextInt();
		
		
		Contact contact = eM.find(Contact.class, idagg);
		
		System.out.println(contact.toString());
		System.out.println();
		
		//nome
		System.out.println("Nuovo nome: (se invariato null)");
		String pino = in.next();
		if(pino.equals("null")) {
			pino = contact.getName();
		}
		System.out.println(pino);
		//cognome
		System.out.println("Nuovo cognome: (se invariato null)");
		String surname = in.next();
		if(surname.equals("null")){
			surname = contact.getSurname();			
		} 
		System.out.println(surname);
		
		//telefono
		System.out.println("Nuovo telefono: (se invariato null)");
		String phoneNumber = in.next();
		if(phoneNumber.equals("null")){
			phoneNumber = contact.getPhoneNumber();
		} 
		
		//email
		System.out.println("Nuovo email: (se invariato null)");
		String email = in.next();
		if(email.equals("null")){
			email = contact.getEmail();			
		} 
		
		//note
		System.out.println("Nuovo note: (se invariato null)");
		String note = in.next();
		if(note.equals("null")){
			note = contact.getNote();			
		} 
		
		contact.setName(pino);
		contact.setSurname(surname);
		contact.setPhoneNumber(phoneNumber);
		contact.setEmail(email);
		contact.setNote(note);
		
		eM.persist(contact);
		transaction.commit();
		
		
	}
	
	public static void deleteContact() {

		EntityManager eM = JPAentity.getEntityManager();
		
		EntityTransaction transaction= eM.getTransaction();
		transaction.begin();
		
		System.out.println("Seleziona id contatto da modificare:");
		int idagg = in.nextInt();
		
		
		Contact contact = eM.find(Contact.class, idagg);
		
		eM.remove(contact);
		transaction.commit();
		
		System.out.println("Contatto rimosso");
	}
}
