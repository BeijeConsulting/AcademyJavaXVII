package it.beije.suormary.rubrica.mancuso;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	public static final String divider1 = "--------------------------------------------------------------------";
	public static final String divider2 = "_____________________________________________________________________";
	public static final String divider3 = "------------------------------------------";
	
 	public static int selectIndex(Scanner input, List<Contact> resultContacts) {
		String command = "";
		int index = -1;

		while(index<0 || index>=resultContacts.size()) {
			System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLUE + "I contatti presenti sono i seguenti, "
					+ "inserire l'indice del contatto su cui effettuare l'operazione. "
					+ "Inserire \"exit\" per annullare l'operazione." + ANSI_RESET);
			int i=0;
			for(Contact cont : resultContacts) {
				System.out.println("Index : " + i);
				System.out.println(cont);
				System.out.println(divider3);
				i++;
			}
			command = input.nextLine();
			if(command.equals("exit")) {
				return -1;
			}else {
				try {
					index = Integer.valueOf(command);
					return index;
				}catch(NumberFormatException ex) {
					System.out.println("Inserire un indice numerico o \"exit\".");
					return -1;
				}
			}
		}
		return -1;
	}
	
	
	public static void printOptions() {
		System.out.println(divider2 + "\n");
		System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLUE + "\t\t\tMenu funzionalita' rubrica:\t\t\t\n" + ANSI_RESET);
		
		System.out.println("import csv\t\tIMPORTA contatti da file CSV");
		System.out.println("import xml\t\tIMPORTA contatti da file XML");
		System.out.println("import db\t\tIMPORTA contatti da DATABASE");
		
		System.out.println(divider1);
		
		System.out.println("export csv\t\tESPORTA contatti su file CSV");
		System.out.println("export xml\t\tESPORTA contatti su file XML");
		System.out.println("export db\t\tESPORTA contatti su DATABASE");
		
		System.out.println(divider1);
		
		System.out.println("view\t\t\tVISUALIZZA contatti su CONSOLE");
		System.out.println("sort by name\t\tVISUALIZZA contatti su CONSOLE");
		System.out.println("sort by surname\t\tVISUALIZZA contatti su CONSOLE");
		
		System.out.println(divider1);
		
		System.out.println("add\t\t\tINSERISCI nuovo contatto");
		System.out.println("edit\t\t\tMODIFICA contatto");
		System.out.println("delete\t\t\tELIMINA contatto");
		
		System.out.println(divider1);
		
		System.out.println("duplicates\t\tRICERCA contatti duplicati");
		System.out.println("merge\t\t\tUNISCI contatti duplicati");
		
		System.out.println(divider1);
		
		System.out.println("find name\t\tRICERCA contatto per nome");
		System.out.println("find surname\t\tRICERCA contatto per cognome");
		System.out.println("find fullname\t\tRICERCA contatto per nome e cognome");
		System.out.println("find phone\t\tRICERCA contatto per numero di telefono");
		System.out.println("find email\t\tRICERCA contatto per email");
		
		System.out.println(divider2);
		
		System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "\tI seguenti comandi verranno eseguiti direttamente su DATABASE\t" + ANSI_RESET + "\n");
		
		System.out.println("find by field\t\tRICERCA per campo e valore");
		System.out.println("multi find\t\tRICERCA per n campi e valori");
		System.out.println("read all\t\tVISUALIZZA tutti i contatti presenti");
		System.out.println("new contact\t\tINSERISCI nuovo contatto");
		System.out.println("edit contact\t\tMODIFICA contatto");
		System.out.println("delete contact\t\tELIMINA contatto");
		System.out.println("db dups\t\tRICERCA contatti duplicati");
		
		System.out.println(divider1);
		
		System.out.println("exit\t\t\tChiudi il programma");
		System.out.println(divider2 + "\n");
	}
	
	public static boolean executeCommand(String command, Scanner input, AddressBook ab) {
		
		String name;
		String surname;
		String phone;
		String email;
		String notes;
		
		String pathFile;
		String separator;
		String overwrite;
		
		List<Contact> resultContacts = null;
		
		Contact c = null;
		int index;
		
		System.out.println("\n");
		
		switch(command) {
		case "import csv":
			System.out.println("Inserisci il path del file CSV da cui importare i dati: ");
			pathFile = input.nextLine();
			
			System.out.println("Inserisci il separatore del file CSV ( ; , : [etc...] ): ");
			separator = input.nextLine();
			
			try {
				ab.contacts = ab.loadAddressesFromCSV(pathFile, separator);
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			}
			break;
		case "import xml":
			System.out.println("Inserisci il path del file XML da cui importare i dati: ");
			pathFile = input.nextLine();
			
			try {
				ab.contacts = ab.loadAddressesFromXML(pathFile);
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			}
			break;
		case "import db":
			ab.contacts = ab.loadAddressesHBM();
			break;
		case "export csv":
			System.out.println("Inserisci il path del file CSV su cui esportare i dati: ");
			pathFile = input.nextLine();
			
			System.out.println("\n");
			
			System.out.println("Inserisci il separatore del file CSV ( ; , : [etc...] ): ");
			separator = input.nextLine();
			
			overwrite = "";
			
			while(!overwrite.equals("y") && !overwrite.equals("n")) {
				System.out.println("\nDesideri sovrascrivere il file se esistente? (Y/n)");
				overwrite = input.nextLine().toLowerCase();
			}
			// methods takes "append" not "overwrite"
			if(overwrite.equals("y")) {
				ab.writeAddressBookCSV(pathFile, separator, false);
			} else {
				ab.writeAddressBookCSV(pathFile, separator, true);
			}
			
			break;
		case "export xml":
			System.out.println("Inserisci il path del file XML su cui esportare i dati: ");
			pathFile = input.nextLine();
			
			overwrite = "";
			
			while(!overwrite.equals("y") && !overwrite.equals("n")) {
				System.out.println("\nDesideri sovrascrivere il file se esistente? (Y/n)");
				overwrite = input.nextLine().toLowerCase();
			}
			// methods takes "append" not "overwrite"
			if(overwrite.equals("y")) {
				ab.writeAddressBookXML(pathFile, false);
			} else {
				ab.writeAddressBookXML(pathFile, true);
			}
			
			break;
		case "export db":
			overwrite = "";
			
			while(!overwrite.equals("y") && !overwrite.equals("n")) {
				System.out.println("\nDesideri sovrascrivere il database esistente? (Y/n)");
				overwrite = input.nextLine().toLowerCase();
			}
			if(overwrite.equals("y")) {
				ab.writeAddressBookHBM(true);
			} else {
				ab.writeAddressBookHBM(false);
			}
			
			break;
		case "view":
			System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLUE + "\nI contatti presenti in rubrica sono i seguenti: \n" + ANSI_RESET);
			System.out.println(ab.toString());
			break;
		case "add":
			System.out.println("Inserisci il nome del contatto:");
			name = input.nextLine();
			
			System.out.println("\nInserisci il cognome del contatto:");
			surname = input.nextLine();

			System.out.println("\nInserisci il telefono del contatto:");
			phone = input.nextLine();
			
			System.out.println("\nInserisci la mail del contatto:");
			email = input.nextLine();
			
			System.out.println("\nInserisci le note del contatto:");
			notes = input.nextLine();
			
			c = new Contact(name,surname,phone,email,notes);
			
			ab.contacts.add(c);
			
			break;
		case "edit":
			index = -1;
			if(ab.contacts.size()>0) {
				index = selectIndex(input, ab.contacts);
				if(index == -1) {
					break;
				}
				// EDIT NAME
				String response = "";
				while(!response.equals("y") && !response.equals("n")) {
					System.out.println("\nModificare il nome? (Y/n)");
					response = input.nextLine().toLowerCase();
				}
				
				if(response.equals("y")) {
					System.out.println("\nInserire il nuovo nome: ");
					String newName = input.nextLine();
					ab.contacts.get(index).setFirstName(newName);
				}
				
				// EDIT SURNAME
				response = "";
				while(!response.equals("y") && !response.equals("n")) {
					System.out.println("\nModificare il cognome? (Y/n)");
					response = input.nextLine().toLowerCase();
				}
				
				if(response.equals("y")) {
					System.out.println("\nInserire il nuovo cognome: ");
					String newSurname = input.nextLine();
					ab.contacts.get(index).setLastName(newSurname);
				}
				
				// EDIT PHONE NUMBER
				response = "";
				while(!response.equals("y") && !response.equals("n")) {
					System.out.println("\nModificare il numero di telefono? (Y/n)");
					response = input.nextLine().toLowerCase();
				}
				
				if(response.equals("y")) {
					System.out.println("\nInserire il nuovo numero di telefono: ");
					String newNumber = input.nextLine();
					ab.contacts.get(index).setPhoneNumber(newNumber);
				}
				
				// EDIT MAIL
				response = "";
				while(!response.equals("y") && !response.equals("n")) {
					System.out.println("\nModificare la email? (Y/n)");
					response = input.nextLine().toLowerCase();
				}
				
				if(response.equals("y")) {
					System.out.println("\nInserire la nuova email: ");
					String newEmail = input.nextLine();
					ab.contacts.get(index).setEmail(newEmail);
				}
				
				// EDIT NOTES
				response = "";
				while(!response.equals("y") && !response.equals("n")) {
					System.out.println("\nModificare le note? (Y/n)");
					response = input.nextLine().toLowerCase();
				}
				
				if(response.equals("y")) {
					System.out.println("\nInserire le nuove note: ");
					String newNotes = input.nextLine();
					ab.contacts.get(index).setNotes(newNotes);
				}
			}else {
				System.out.println("La lista dei contatti e' vuota. Non e' possibile modificare un contatto.");
			}
			break;
		case "delete":
			index = -1;
			if(ab.contacts.size()>0) {
				index = selectIndex(input, ab.contacts);
				if(index == -1) {
					break;
				}else {
					ab.contacts.remove(index);
				}
				
			}else {
				System.out.println("La lista dei contatti e' vuota. Non e' possibile eliminare un contatto.");
			}
			
			break;
		case "duplicates":
			List<Contact> dups = ab.findDuplicates();
			System.out.println("I contatti duplicati sono i seguenti: ");
			System.out.println(AddressBook.print(dups)); 
			break;
		case "merge":
			ab.mergeDuplicates(input);
			System.out.println("I contatti duplicati sono stati uniti.\n");
			break;
		case "find name":
			System.out.println("Inserire il nome da cercare: ");
			name = input.nextLine();
				
			resultContacts = ab.findContactByName(name);
			System.out.println("\n");
			System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Sono stati trovati i seguenti contatti con il nome simile a quello inserito: \n" + ANSI_RESET);
			System.out.println(AddressBook.print(resultContacts));
				
			break;
		case "find surname":
			
			System.out.println("\nInserire il cognome da cercare: ");
			surname = input.nextLine();
				
			resultContacts = ab.findContactBySurname(surname);
			System.out.println("\n");
			System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Sono stati trovati i seguenti contatti con il cognome simile a quello inserito: \n" + ANSI_RESET);
			System.out.println(AddressBook.print(resultContacts));
			
			break;
		case "find fullname":
			
			System.out.println("Inserire il nome da cercare: ");
			name = input.nextLine();
			
			System.out.println("Inserire il cognome da cercare: ");
			surname = input.nextLine();
				
			resultContacts = ab.findContactByName(name, surname);
			System.out.println("\n");
			System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Sono stati trovati i seguenti contatti con il nome e cognome simili a quelli inseriti: \n" + ANSI_RESET);
			System.out.println(AddressBook.print(resultContacts));
			
			break;
		case "find phone":
			
			System.out.println("Inserire il numero di telefono da cercare: ");
			phone = input.nextLine();
				
			resultContacts = ab.findContactByPhone(phone);
			System.out.println("\n");
			System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Sono stati trovati i seguenti contatti con il numero di telefono uguale a quello inserito: \n" + ANSI_RESET);
			System.out.println(AddressBook.print(resultContacts));
			
			break;
		case "find email":
			
			System.out.println("Inserire l'email da cercare: ");
			phone = input.nextLine();
				
			resultContacts = ab.findContactByEmail(phone);
			System.out.println("\n");
			System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Sono stati trovati i seguenti contatti con l'email uguale a quella inserita: \n" + ANSI_RESET);
			System.out.println(AddressBook.print(resultContacts));
			
			break;
		case "exit":
			return true;
		
		case "sort by name":
			System.out.println(ab.toString("nome"));
			break;
		case "sort by surname":
			System.out.println(ab.toString("cognome"));
			break;
		case "find by field":
			
			String column = "";
			boolean okInput = false;
			

			while(!okInput) {
				System.out.println("Inserire il campo su cui effettuare la ricerca selezionando uno dei seguenti: ");
				System.out.println("--- nome ---");
				System.out.println("--- cognome ---");
				System.out.println("--- email ---");
				System.out.println("--- telefono ---");
				System.out.println("--- note ---");
				System.out.println("--- exit (annulla operazione) ---");
				command = input.nextLine();
				if(AddressBook.getOkColumns().contains(command) || command.equals("exit")) {
					okInput = true;
				}
			}
			
			if(command.equals("exit")) {
				break;
			}
			
			System.out.println("Inserire il valore da cercare: ");
			String value = input.nextLine();

			switch(command) {
				case "nome":
					column = "firstName";
					break;
				case "cognome":
					column = "lastName";
					break;
				case "email":
					column = "email";
					break;
				case "telefono":
					column = "phoneNumber";
					break;
				case "note":
					column = "notes";
					break;
			}
			
			resultContacts = JPAUtils.selectColumn(column, value);
			
			System.out.println("\n");
			System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Sono stati trovati i seguenti contatti: \n" + ANSI_RESET);
			
			System.out.println(AddressBook.print(resultContacts));
			break;
		case "multi find":
			String col = "";
			List<String> fields = new ArrayList<>();
			List<String> values = new ArrayList<>();
			boolean end = false;

			while(!end) {
				System.out.println("Inserire il campo su cui effettuare la ricerca selezionando uno dei seguenti: ");
				System.out.println("--- nome ---");
				System.out.println("--- cognome ---");
				System.out.println("--- email ---");
				System.out.println("--- telefono ---");
				System.out.println("--- note ---");
				System.out.println("--- end (fine selezione colonne) ---");
				System.out.println("--- exit (annulla operazione) ---\n");
				command = input.nextLine();
				if(AddressBook.getOkColumns().contains(command)) {
					switch(command) {
					case "nome":
						col = "firstName";
						break;
					case "cognome":
						col = "lastName";
						break;
					case "email":
						col = "email";
						break;
					case "telefono":
						col = "phoneNumber";
						break;
					case "note":
						col = "notes";
						break;
					}
					fields.add(col);
					System.out.println("Inserire il valore da cercare: ");
					values.add(input.nextLine());
					System.out.println("");
				}
				if(command.equals("exit") || command.equals("end")) {
					end = true;
				}
			}
			
			if(command.equals("exit")) {
				break;
			}
			
			resultContacts = JPAUtils.multiColumn(fields, values);
			System.out.println("\n");
			System.out.println(AddressBook.print(resultContacts));
			
			break;
		case "read all":
			resultContacts = JPAUtils.getAllContacts();
			//System.out.println("\n");
			System.out.println(AddressBook.print(resultContacts));
			break;
		case "new contact":
			System.out.println("\nInserisci il nome del contatto:");
			name = input.nextLine();
			
			System.out.println("\nInserisci il cognome del contatto:");
			surname = input.nextLine();

			System.out.println("\nInserisci il telefono del contatto:");
			phone = input.nextLine();
			
			System.out.println("\nInserisci la mail del contatto:");
			email = input.nextLine();
			
			System.out.println("\nInserisci le note del contatto:");
			notes = input.nextLine();
			
			c = new Contact(name,surname,phone,email,notes);
			
			JPAUtils.addContact(c);
			
			System.out.println("\nContatto inserito: \n");
			
			System.out.println(c);
			
			break;
		case "edit contact":
			index = -1;
			resultContacts = JPAUtils.getAllContacts();
			if(resultContacts.size()>0) {
				index = selectIndex(input, resultContacts);
				if(index == -1) {
					break;
				}
				
				// EDIT NAME
				String response = "";
				Contact toEdit = JPAUtils.getContact(resultContacts.get(index).getId());
				
				String newName = toEdit.getFirstName();
				String newSurname = toEdit.getLastName();
				String newNumber = toEdit.getPhoneNumber();
				String newEmail = toEdit.getEmail();
				String newNotes = toEdit.getNotes();
				
				while(!response.equals("y") && !response.equals("n")) {
					System.out.println("Modificare il nome? (Y/n)");
					response = input.nextLine().toLowerCase();
				}
				
				if(response.equals("y")) {
					System.out.println("Inserire il nuovo nome: ");
					newName = input.nextLine();
				}
				
				// EDIT SURNAME
				response = "";
				while(!response.equals("y") && !response.equals("n")) {
					System.out.println("Modificare il cognome? (Y/n)");
					response = input.nextLine().toLowerCase();
				}
				
				if(response.equals("y")) {
					System.out.println("Inserire il nuovo cognome: ");
					newSurname = input.nextLine();
				}
				
				// EDIT PHONE NUMBER
				response = "";
				while(!response.equals("y") && !response.equals("n")) {
					System.out.println("Modificare il numero di telefono? (Y/n)");
					response = input.nextLine().toLowerCase();
				}
				
				if(response.equals("y")) {
					System.out.println("Inserire il nuovo numero di telefono: ");
					newNumber = input.nextLine();
				}
				
				// EDIT MAIL
				response = "";
				while(!response.equals("y") && !response.equals("n")) {
					System.out.println("Modificare la email? (Y/n)");
					response = input.nextLine().toLowerCase();
				}
				
				if(response.equals("y")) {
					System.out.println("Inserire la nuova email: ");
					newEmail = input.nextLine();
				}
				
				// EDIT NOTES
				response = "";
				while(!response.equals("y") && !response.equals("n")) {
					System.out.println("Modificare le note? (Y/n)");
					response = input.nextLine().toLowerCase();
				}
				
				if(response.equals("y")) {
					System.out.println("Inserire le nuove note: ");
					newNotes = input.nextLine();
				}
				
				JPAUtils.editContact(toEdit, newName, newSurname, newNumber, newEmail, newNotes);
				
				System.out.println("Contatto modificato\n");
				//System.out.println(toEdit);
				
			}else {
				System.out.println("La lista dei contatti e' vuota. Non e' possibile modificare un contatto.");
			}
			break;
		case "delete contact":
			index = -1;
			resultContacts = JPAUtils.getAllContacts();
			if(resultContacts.size()>0) {
				index = selectIndex(input, resultContacts);
				if(index == -1) {
					break;
				}else {
					JPAUtils.deleteContact(resultContacts.get(index));
				}
				
			}else {
				System.out.println("La lista dei contatti e' vuota. Non e' possibile eliminare un contatto.");
			}
			
			break;
		case "db dups":
			resultContacts = JDBCUtils.findDuplicates();
			System.out.println(AddressBook.print(resultContacts));
			break;
		default:
			System.out.println(ANSI_RED + "Comando non riconosciuto.\n" + ANSI_RESET);
			break;
		}
		return false;
	}
}