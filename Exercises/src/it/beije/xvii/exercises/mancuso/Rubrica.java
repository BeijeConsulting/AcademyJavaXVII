package it.beije.xvii.exercises.mancuso;

import java.util.List;
import java.util.Scanner;

public class Rubrica {

	public static void main(String[] args) {
		Scanner input = null;
		
		try {
			AddressBook ab = new AddressBook();
			
			input = new Scanner(System.in);
			
			String command = "";
			String pathFile;
			String separator;
			String overwrite;
			boolean exit = false;
			
			String name;
			String surname;
			String phone;
			String email;
			String notes;
			
			while(!exit) {
				System.out.println("Menu funzionalita' rubrica:\n");
				System.out.println("\t1) IMPORTA contatti da file CSV");
				System.out.println("\t2) IMPORTA contatti da file XML");
				System.out.println("\t3) IMPORTA contatti da DATABASE\n");
				
				System.out.println("\t4) ESPORTA contatti su file CSV");
				System.out.println("\t5) ESPORTA contatti su file XML");
				System.out.println("\t6) ESPORTA contatti su DATABASE\n");
				
				System.out.println("\t7) VISUALIZZA contatti su CONSOLE\n");
				
				System.out.println("\t8) INSERISCI nuovo contatto");
				System.out.println("\t9) MODIFICA contatto");
				System.out.println("\tA) ELIMINA contatto\n");
				
				System.out.println("\tB) RICERCA contatti duplicati");
				System.out.println("\tC) UNISCI contatti duplicati\n");
				
				System.out.println("\tD) RICERCA contatto per nome\n");
				System.out.println("\tE) RICERCA contatto per cognome\n");
				System.out.println("\tF) RICERCA contatto per nome e cognome\n");
				System.out.println("\tG) RICERCA contatto per numero di telefono\n");
				System.out.println("\tH) RICERCA contatto per email\n");
				
				System.out.println("\t0) Chiudi il programma");
				
				command = input.nextLine();
				
				Contact c = null;
				int index;
				
				switch(command) {
				case "1":
					System.out.println("\nInserisci il path del file CSV da cui importare i dati: ");
					pathFile = input.nextLine();
					
					System.out.println("\nInserisci il separatore del file CSV ( ; , : [etc...] ): ");
					separator = input.nextLine();
					
					try {
						ab.contacts = ab.loadAddressesFromCSV(pathFile, separator);
					} catch (IllegalArgumentException ex) {
						ex.printStackTrace();
					}
					break;
				case "2":
					System.out.println("\nInserisci il path del file XML da cui importare i dati: ");
					pathFile = input.nextLine();
					
					try {
						ab.contacts = ab.loadAddressesFromXML(pathFile);
					} catch (IllegalArgumentException ex) {
						ex.printStackTrace();
					}
					break;
				case "3":
					ab.contacts = ab.loadAddressesFromJDBC();
					break;
				case "4":
					System.out.println("\nInserisci il path del file CSV su cui esportare i dati: ");
					pathFile = input.nextLine();
					
					System.out.println("\nInserisci il separatore del file CSV ( ; , : [etc...] ): ");
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
				case "5":
					System.out.println("\nInserisci il path del file XML su cui esportare i dati: ");
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
				case "6":
					overwrite = "";
					
					while(!overwrite.equals("y") && !overwrite.equals("n")) {
						System.out.println("\nDesideri sovrascrivere il database esistente? (Y/n)");
						overwrite = input.nextLine().toLowerCase();
					}
					if(overwrite.equals("y")) {
						ab.writeAddressBookJDBC(true);
					} else {
						ab.writeAddressBookJDBC(false);
					}
					
					break;
				case "7":
					System.out.println("\nI contatti presenti in rubrica sono i seguenti: \n");
					System.out.println(ab.toString());
					break;
				case "8":
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
					
					ab.contacts.add(c);
					
					break;
				case "9":
					index = -1;
					if(ab.contacts.size()>0) {
						while(index<0 || index>=ab.contacts.size()) {
							System.out.println("I contatti presenti sono i seguenti, inserire l'indice del contatto che si desidera modificare. Inserire \"exit\" per annullare l'operazione.");
							System.out.println(ab.toString());
							command = input.nextLine();
							if(command.equals("exit")) {
								break;
							}else {
								try {
									index = Integer.valueOf(command);
								}catch(NumberFormatException ex) {
									System.out.println("Inserire un indice numerico o \"exit\".");
								}
							}
						}
						if(command.equals("exit")) {
							break;
						}
						// EDIT NAME
						String response = "";
						while(!response.equals("y") && !response.equals("n")) {
							System.out.println("Modificare il nome? (Y/n)");
							response = input.nextLine().toLowerCase();
						}
						
						if(response.equals("y")) {
							System.out.println("Inserire il nuovo nome: ");
							String newName = input.nextLine();
							ab.contacts.get(index).setFirstName(newName);
						}
						
						// EDIT SURNAME
						response = "";
						while(!response.equals("y") && !response.equals("n")) {
							System.out.println("Modificare il cognome? (Y/n)");
							response = input.nextLine().toLowerCase();
						}
						
						if(response.equals("y")) {
							System.out.println("Inserire il nuovo cognome: ");
							String newSurname = input.nextLine();
							ab.contacts.get(index).setLastName(newSurname);
						}
						
						// EDIT PHONE NUMBER
						response = "";
						while(!response.equals("y") && !response.equals("n")) {
							System.out.println("Modificare il numero di telefono? (Y/n)");
							response = input.nextLine().toLowerCase();
						}
						
						if(response.equals("y")) {
							System.out.println("Inserire il nuovo numero di telefono: ");
							String newNumber = input.nextLine();
							ab.contacts.get(index).setPhoneNumber(newNumber);
						}
						
						// EDIT MAIL
						response = "";
						while(!response.equals("y") && !response.equals("n")) {
							System.out.println("Modificare la email? (Y/n)");
							response = input.nextLine().toLowerCase();
						}
						
						if(response.equals("y")) {
							System.out.println("Inserire la nuova email: ");
							String newEmail = input.nextLine();
							ab.contacts.get(index).setEmail(newEmail);
						}
						
						// EDIT NOTES
						response = "";
						while(!response.equals("y") && !response.equals("n")) {
							System.out.println("Modificare le note? (Y/n)");
							response = input.nextLine().toLowerCase();
						}
						
						if(response.equals("y")) {
							System.out.println("Inserire le nuove note: ");
							String newNotes = input.nextLine();
							ab.contacts.get(index).setNotes(newNotes);
						}
					}else {
						System.out.println("La lista dei contatti e' vuota. Non e' possibile modificare un contatto.");
					}
					break;
				case "A":
					index = -1;
					if(ab.contacts.size()>0) {
						while(index<0 || index>=ab.contacts.size()) {
							System.out.println("I contatti presenti sono i seguenti, inserire l'indice del contatto che si desidera modificare. Scrivere \"exit\" per annullare l'operazione. ");
							System.out.println(ab.toString());
							command = input.nextLine();
							if(command.equals("exit")) {
								break;
							}else {
								try {
									index = Integer.valueOf(command);
								}catch(NumberFormatException ex) {
									System.out.println("Inserire un indice numerico o \"exit\".");
								}
							}
						}
						if(command.equals("exit")) {
							break;
						}else {
							ab.contacts.remove(index);
						}
						
					}else {
						System.out.println("La lista dei contatti e' vuota. Non e' possibile eliminare un contatto.");
					}
					
					break;
				case "B":
					List<Contact> dups = ab.findDuplicates();
					System.out.println("I contatti duplicati sono i seguenti: ");
					for(Contact contact : dups) {
						System.out.println(contact.toString());
						System.out.println("--------------------------\n");
					}
					break;
				case "C":
					ab.mergeDuplicates();
					break;
				case "D":
	
					break;
				case "E":
					
					break;
				case "F":
					
					break;
				case "G":
					
					break;
				case "H":
					
					break;
				case "0":
					exit = true;
					break;
				default:
					System.out.println("Comando non riconosciuto.\n");
					break;
				}
				
			}
			System.out.println("Programma terminato.");
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			input.close();
		}
		
	}

}
