package it.beije.suormary.rubrica.ceccarelli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.beije.suormary.rubrica.Contact;

public class MetodiRubricaHBM {
	 
	public DbWithHBM hbm;
	public MetodiRubricaJPA jpa;
	
	public MetodiRubricaHBM() {
		hbm = new DbWithHBM();
		jpa = new MetodiRubricaJPA();
	}
	
	//list of contacts
	public void listContacts() {
		
		List<Contact> contacts = new ArrayList<Contact>();
		// CON HBM
		//contacts = hbm.listContactHBM();
		// CON JPA
		contacts = jpa.listContactJPA();
		if(contacts.isEmpty()) {
			System.out.println("Non ci sono risultati");
			return;
		}
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
		chooseFile(contacts, hbm);
	}
	
	//search contact
	public List<Contact> searchContacts() {
		List<Contact> result = new ArrayList<Contact>();
		Scanner scanSearch = new Scanner(System.in);
		String s = MetodiFile.selection();
		String name;
		String surname;
		switch(s) {
		case "1": 
			System.out.print("Inserisci il nome da cercare: ");
			
			name = scanSearch.next();
			// HBM
			//result = hbm.searchContactsName(name);
			//JPA
			result = jpa.searchContactNameJPA(name);
			break;
		case "2":
			System.out.print("Inserisci il cognome da cercare: ");
			
			surname = scanSearch.next();
			// HBM
			//result = hbm.searchContactsSurname(surname);
			//JPA
			result = jpa.searchContactSurnameJPA(surname);
			break;
		case "3":
			System.out.println("Inserisci il nome e  il cognome da cercare: ");
			System.out.print("Inserisci il nome: ");
			name = scanSearch.next();
			System.out.print("Inserisci il cognome: ");
			surname = scanSearch.next();
			//HBM
			//result = hbm.searchContactsNameSurname(name, surname);
			//JPA
			result = jpa.searchContactsNameSurname(name, surname);
			break;
		}
		
		if(result.isEmpty()) {
			System.out.println("Non ci sono risultati");
			return null;
		}
		for(Contact contact : result) {
			System.out.println(contact.toString());
		}
		
		chooseFile(result, hbm);
		return result;
	}
	
	//insert contacts
	public void insertContacts() {
		Scanner scanInsert= new Scanner(System.in);
		System.out.println("Da dove vuoi inserire(XML,CSV, default): ");
		String risp = scanInsert.next();
		String path="";
		List<Contact> list2 = null;
		switch(risp) {
			case "XML":
				System.out.println("Inserisci il path del file");
				path=scanInsert.next();
				list2 = MetodiFile.loadRubricaFromXML(path);
				try {
					for(Contact c : list2) {
						hbm.insertContacts(c);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "CSV":
				System.out.println("Inserisci il path del file");
				path=scanInsert.next();
				list2 = MetodiFile.loadRubricaFromCSV(path,";");
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
				scanModifies.nextLine();
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
		//String name="";
		String name = scanModifies.nextLine();
		//scanModifies.nextLine();
		System.out.print("cognome: ");
		String surname = scanModifies.nextLine();
		//scanModifies.nextLine();
		System.out.print("telefono: "); 
		String phoneNumber = scanModifies.nextLine();
		//scanModifies.nextLine();
		System.out.print("email: "); 
		String email = scanModifies.next();
		scanModifies.nextLine();
		System.out.print("note: "); 
		String note = scanModifies.nextLine();
		
		if(!name.equals("null")) {
			cModifies.setName(name);
		}if(!surname.equals("null")) {
			cModifies.setSurname(surname);
		}if(!phoneNumber.equals("null")) {
			cModifies.setPhoneNumber(phoneNumber);
		}if(!email.equals("null")) {
			cModifies.setEmail(email);
		}if(note!="null") {
			cModifies.setNote(note);
		}
		//System.out.println("contact POST : " + cModifies);
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
	
		
		
		//save into one type of file
		public static void chooseFile(List<Contact> c, DbWithHBM h) {
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
							MetodiFile.writeRubricaFromDbToCSV(c);	
						}catch(Exception e) {
							System.out.println("errore");
						}
					}else if(s.equalsIgnoreCase("xml")){
						try {
							rispostaValida = true;
							MetodiFile.writeRubricaFromDbToXML(c);
						
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
