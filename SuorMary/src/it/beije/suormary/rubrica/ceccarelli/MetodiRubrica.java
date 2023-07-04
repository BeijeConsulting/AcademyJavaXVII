package it.beije.suormary.rubrica.ceccarelli;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.beije.suormary.rubrica.Contact;

public class MetodiRubrica {
	
	private ExerciseswithDB db;
	public MetodiRubrica() {
		db = new ExerciseswithDB();
	}
	
	// list of contacts
	public void listContact() {
		List<Contact> names = new ArrayList<Contact>();
		try {
			List<Contact> contacts = db.loadRubricaFromDb();
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chooseFile(names, db);
		
	} 
	
	// search contacts
	public List<Contact> searchContact() {
		List<Contact> result = new ArrayList<Contact>();
		Scanner scanSearch = new Scanner(System.in);
		String s = selection();
		String name;
		String surname;
		switch(s) {
		case "1": 
			System.out.print("Inserisci il nome da cercare: ");
			
			name = scanSearch.next();
			result = db.selectionName(name);
			break;
		case "2":
			System.out.print("Inserisci il cognome da cercare: ");
			
			surname = scanSearch.next();
			result = db.selectionSurname(surname);
			break;
		case "3":
			System.out.println("Inserisci il nome e  il cognome da cercare: ");
			System.out.print("Inserisci il nome: ");
			name = scanSearch.next();
			System.out.print("Inserisci il cognome: ");
			surname = scanSearch.next();
			result = db.selectionNameSurname(name, surname);
			break;
		}
		
		for(Contact contact : result) {
			System.out.println(contact.toString());
		}
		
		chooseFile(result, db);
		return result;
//		System.out.println("Vuoi modificare il contatto? ");
//		String r = scanSearch.next();
//		if(r.equalsIgnoreCase("Si")|| r.equalsIgnoreCase("Sì")) {
//			modifiesContact(result);
//		}else {
//			System.out.println("Si è scelto di non modificare il contatto");
//			return;
//		}
		
	}
	
	// modifies contact
	public void modifiesContact() {
		List<Contact> c = searchContact();
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
		List<Contact> singleContact = new ArrayList<>();
		singleContact.add(cModifies);
		//chooseFile(singleContact, db);
		saveToDb(singleContact, db);
		
		
	}
	
	//insert contact
	public void insertContact() {
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
					db.writeRubricaFromXMLtoDb(list2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "CSV":
				path=scanInsert.next();
				list2 = db.loadRubricaFromCSV(path,";");
				try {
					db.writeRubricaFromCSVtoDb(list2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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
				db.insertContact(contact);
				//System.out.print(contact.toString());
				break;
		}
		
		
		
	}
	
	// selection type of search
	public static String selection() {
		System.out.println("Scegli che tipo di ricerca vuoi fare: ");
		System.out.println("1. Per nome");
	    System.out.println("2. Per cognome");
	    System.out.println("3. Per nome e cognome");
	    Scanner scanSelect = new Scanner(System.in);
	    String r = scanSelect.next();
	    return r;
	    
	}
	
	//delete contact
	public void deleteContact() {
		List<Contact> c = searchContact();
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
			db.deleteContact(cDelete);
		}else {
			System.out.println("Si è deciso di NON eliminare il contatto");
			return;
		}
		
		
	}
	
	//save into one type of file
	public static void chooseFile(List<Contact> c, ExerciseswithDB db) {
		Scanner scanChoose = new Scanner(System.in);
		System.out.print("Vuoi salvare il risultato su un file? ");
		boolean rispostaValida = false;
		while(!rispostaValida) {
			String r = scanChoose.next();
			if(r.equalsIgnoreCase("Si") || r.equalsIgnoreCase("Sì") ) {
				System.out.print("CSV o XML? ");
				String s = scanChoose.next().trim();
				if(s.equalsIgnoreCase("csv")) {
					try {
						rispostaValida = true;
						db.writeRubricaFromDbToCSV(c);	
					}catch(Exception e) {
						System.out.println("errore");
					}
				}else if(s.equalsIgnoreCase("xml")){
					try {
						rispostaValida = true;
						db.writeRubricaFromDbToXML(c);
					
					}catch(Exception e) {
						System.out.println("errore");
					}
				}else {
					System.out.println("Valore inserito non valido");
				}
			}else if(r.equalsIgnoreCase("No")){
				System.out.println("Valori non salvati");
				rispostaValida = true;
			}else {
				System.out.println("Valore inserito non valido");
			};
		}
	}
	
	public static void saveToDb(List<Contact> c, ExerciseswithDB db) {
		Scanner scansave = new Scanner(System.in);
		System.out.print("Vuoi aggiornare il dato sul db? ");
		boolean rispostaValida = false;
		while(!rispostaValida) {
			String r = scansave.next();
			if(r.equalsIgnoreCase("Si") || r.equalsIgnoreCase("Sì") ) {
				rispostaValida = true;
				try {
					db.updateContact(c);
				}catch(Exception e) {
					e.fillInStackTrace();
				}
				
			}else if(r.equalsIgnoreCase("No")){
				System.out.println("Contatto non aggiornato");
				rispostaValida = true;
			}else{
				System.out.println("Valore inserito non valido");
			}
		}
	}
}
