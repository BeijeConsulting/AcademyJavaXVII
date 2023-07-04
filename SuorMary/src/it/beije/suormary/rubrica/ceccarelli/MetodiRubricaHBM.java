package it.beije.suormary.rubrica.ceccarelli;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.beije.suormary.rubrica.Contact;

public class MetodiRubricaHBM {
	
	// variabili 
	public DbWithHBM hbm;
	public ExerciseswithDB db;
	
	public MetodiRubricaHBM() {
		hbm = new DbWithHBM();
		db = new ExerciseswithDB();
	}
	
	//list of contacts
	public void listContacts() {
		
		List<Contact> contacts = new ArrayList<Contact>();
		contacts = hbm.listContactHBM();
		List<Contact> names = new ArrayList<Contact>();
		
		Contact tmp = contacts.get(0);
		for(int y=1;y<=contacts.size();y++) {
			if(y==contacts.size()) {
				names.add(tmp);
				break;
			}
			//System.out.println(tmp.getName() + contacts.get(y).getName());
			int comp = tmp.getName().compareToIgnoreCase(contacts.get(y).getName());
			//System.out.println(comp);
			if(comp<0) {
				names.add(tmp);
				tmp = contacts.get(y);
			}else if(comp>0){
				names.add(contacts.get(y)); 
			}else {
				names.add(contacts.get(y));
				tmp = contacts.get(y);
			}
		}
		System.out.println("Di seguito la lista dei contatti: ");
		for(Contact contact : names) {
			System.out.println(contact.toString());
		}
		MetodiRubrica.chooseFile(contacts, db);
	}
	
	//search contact
	public List<Contact> searchContacts() {
		List<Contact> result = new ArrayList<Contact>();
		Scanner scanSearch = new Scanner(System.in);
		String s = MetodiRubrica.selection();
		String name;
		String surname;
		switch(s) {
		case "1": 
			System.out.print("Inserisci il nome da cercare: ");
			
			name = scanSearch.next();
			result = hbm.searchContactsName(name);
			break;
		case "2":
			System.out.print("Inserisci il cognome da cercare: ");
			
			surname = scanSearch.next();
			result = hbm.searchContactsSurname(surname);
			break;
		case "3":
			System.out.println("Inserisci il nome e  il cognome da cercare: ");
			System.out.print("Inserisci il nome: ");
			name = scanSearch.next();
			System.out.print("Inserisci il cognome: ");
			surname = scanSearch.next();
			result = hbm.searchContactsNameSurname(name, surname);
			break;
		}
		
		for(Contact contact : result) {
			System.out.println(contact.toString());
		}
		
		MetodiRubrica.chooseFile(result, db);
		return result;
	}
	
	//insert contacts
	public void insertContacts() {
		Scanner scanInsert= new Scanner(System.in);
		System.out.println("Da dove vuoi inserire(XML,CSV): ");
		String risp = scanInsert.next();
		String path="";
		List<Contact> list2 = null;
		switch(risp) {
			case "XML":
				path=scanInsert.next();
				list2 = db.loadRubricaFromXML(path);
				try {
					for(Contact c : list2) {
						hbm.insertContacts(c);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "CSV":
				path=scanInsert.next();
				list2 = db.loadRubricaFromCSV(path,";");
				try {
					for(Contact c : list2) {
						hbm.insertContacts(c);
					}
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				break;	
			default:
				System.out.println("Inserisci il nuovo contatto:");
				Contact contact = new Contact();
				
				System.out.print("nome: ");
				contact.setName(scanInsert.next());
				System.out.print("cognome: ");
				contact.setSurname(scanInsert.next());
				System.out.print("telefono: "); 
				contact.setPhoneNumber(scanInsert.next());
				System.out.print("email: "); 
				contact.setEmail(scanInsert.next());
				System.out.print("note: "); 
				contact.setNote(scanInsert.next());
				hbm.insertContacts(contact);
//				//System.out.print(contact.toString());
				break;
		}
		
	}
	
	// update contact
	public void updateContacts() {
		List<Contact> c = searchContacts();
		Contact cModifies = null;
		Scanner scanModifies = new Scanner(System.in);
		if(c.size()==1) {
			cModifies = c.get(0);
		}else {
			System.out.println("seleziona il contatto da modificare: ");
			
			for(Contact contact : c) {
				System.out.println(contact);
				System.out.println("E' questo?");
				String choose = scanModifies.next();
				if(choose.equalsIgnoreCase("Si")|| choose.equalsIgnoreCase("Sì")) {
					cModifies = contact;
					break;
				}
			}
		}
		if(cModifies==null) {
			System.out.println("Non è stato selezionato nessun contatto");
			return;
		}
		
		
		//System.out.println("ID MODIFIES" + cModifies.getId());
		System.out.println("Inserisci i dati da modificare(null se non si vuole modificare): ");
		System.out.print("nome: ");
		String name="";
		name = scanModifies.next();
		System.out.print("cognome: ");
		String surname = scanModifies.next();
		System.out.print("telefono: "); 
		String phoneNumber = scanModifies.next();
		System.out.print("email: "); 
		String email = scanModifies.next();
		//System.out.print("note: "); 
		//String note = scanModifies.next();
		
		
		if(!name.equals("null")) {
			cModifies.setName(name);
		}if(!surname.equals("null")) {
			cModifies.setSurname(surname);
		}if(!phoneNumber.equals("null")) {
			cModifies.setPhoneNumber(phoneNumber);
		}if(!email.equals("null")) {
			cModifies.setEmail(email);
		}
//		}if(note!="null") {
//			cModifies.setNote(note);
//		}
		//System.out.println("il nome è: " + name);
		System.out.println("Il dato viene aggiornato sul db");
		hbm.updateContact(cModifies);
		
	}
	
	//delete contact
	public void deleteContact() {
		List<Contact> c = searchContacts();
		Contact cDelete = null;
		Scanner scanDelete = new Scanner(System.in);
		if(c.size()==1) {
			cDelete = c.get(0);
		}else {
			System.out.println("seleziona il contatto da eliminare: ");
			
			for(Contact contact : c) {
				System.out.println(contact);
				System.out.println("E' questo?");
				String choose = scanDelete.next();
				if(choose.equalsIgnoreCase("Si")|| choose.equalsIgnoreCase("Sì")) {
					cDelete = contact;
					break;
				}
			}
		}
		if(cDelete==null) {
			System.out.println("Non è stato selezionato nessun contatto");
			return;
		}
		
		System.out.println("Sicuro di voler eliminare il contatto?");
		String response = scanDelete.next();
		if(response.equalsIgnoreCase("Si")|| response.equalsIgnoreCase("Sì")) {
			hbm.deleteContact(cDelete);
		}else {
			System.out.println("Si è deciso di NON eliminare il contatto");
			return;
		}
		
		
	}
}	
