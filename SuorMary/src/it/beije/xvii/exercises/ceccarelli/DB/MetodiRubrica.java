package it.beije.xvii.exercises.ceccarelli.DB;

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
	public void searchContact() {
		List<Contact> result = new ArrayList<Contact>();
		
			String s = selection();
			String name;
			String surname;
			switch(s) {
			case "1": 
				System.out.print("Inserisci il nome da cercare: ");
				Scanner scan = new Scanner(System.in);
				name = scan.next();
				result = db.selectionName(name);
				break;
			case "2":
				System.out.print("Inserisci il cognome da cercare: ");
				Scanner scan2 = new Scanner(System.in);
				surname = scan2.next();
				result = db.selectionSurname(surname);
				break;
			case "3":
				System.out.println("Inserisci il nome e  il cognome da cercare: ");
				System.out.print("Inserisci il nome: ");
				Scanner scan3 = new Scanner(System.in);
				name = scan3.next();
				System.out.print("Inserisci il cognome: ");
				surname = scan3.next();
				result = db.selectionNameSurname(name, surname);
				break;
			}
			
		chooseFile(result, db);
		System.out.println("Vuoi modificare il contatto? ");
		Scanner scan = new Scanner(System.in);
		String r = scan.next();
		if(r.equalsIgnoreCase("Si")|| r.equalsIgnoreCase("Sì")) {
			modifiesContact(result);
		}else {
			System.out.println("Si è scelto di non modificare il contatto");
			return;
		}
		
	}
	
	// modifies contact
	public void modifiesContact(List<Contact> c) {
		Contact cModifies = null;
		Scanner s = new Scanner(System.in);
		if(c.size()==1) {
			cModifies = c.get(0);
		}else {
			System.out.println("seleziona il contatto da modificare: ");
			
			for(Contact contact : c) {
				System.out.println(contact);
				System.out.println("E' questo?");
				String choose = s.next();
				if(choose.equalsIgnoreCase("Si")|| choose.equalsIgnoreCase("Sì")) {
					cModifies = contact;
					break;
				}
			}
		}
		s.close();
		if(cModifies==null) {
			System.out.println("Non è stato selezionato nessun contatto");
			return;
		}
		
		Scanner n = new Scanner(System.in);
		//System.out.println("ID MODIFIES" + cModifies.getId());
		System.out.println("Inserisci i dati da modificare: ");
		System.out.println("nome: "); 
		String name = n.nextLine();
		System.out.println("cognome: ");
		String surname = n.nextLine();
		System.out.println("telefono: "); 
		String phoneNumber = n.next();
		System.out.println("email: "); 
		String email = n.next();
		System.out.println("note: "); 
		String note = n.nextLine();
		if(!name.isBlank()) {
			cModifies.setName(name);
		}if(!surname.isBlank()) {
			cModifies.setSurname(surname);
		}if(!phoneNumber.isBlank()) {
			cModifies.setPhoneNumber(phoneNumber);
		}if(!email.isBlank()) {
			cModifies.setEmail(email);
		}if(!note.isBlank()) {
			cModifies.setNote(note);
		}
		
		List<Contact> singleContact = new ArrayList<>();
		singleContact.add(cModifies);
		
		chooseFile(singleContact, db);
		saveToDb(singleContact, db);
		
		
	}
	// selection type of search
	public String selection() {
		System.out.println("Scegli che tipo di ricerca vuoi fare: ");
		System.out.println("1. Per nome");
	    System.out.println("2. Per cognome");
	    System.out.println("3. Per nome e cognome");
	    Scanner scan = new Scanner(System.in);
	    String r = scan.next();
	    return r;
	    
	}
	
	//save into one type of file
	public static void chooseFile(List<Contact> c, ExerciseswithDB db) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Vuoi salvare il risultato su un file? ");
		boolean rispostaValida = false;
		while(!rispostaValida) {
			String r = scan.next();
			if(r.equalsIgnoreCase("Si") || r.equalsIgnoreCase("Sì") ) {
				System.out.print("CSV o XML? ");
				String s = scan.next().trim();
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
		Scanner scan = new Scanner(System.in);
		System.out.print("Vuoi aggiornare il dato sul db? ");
		boolean rispostaValida = false;
		while(!rispostaValida) {
			String r = scan.next();
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
