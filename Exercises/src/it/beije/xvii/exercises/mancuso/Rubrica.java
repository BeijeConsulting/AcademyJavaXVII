package it.beije.xvii.exercises.mancuso;

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
			boolean exit = false;
			
			while(!exit) {
				System.out.println("Menu funzionalita' rubrica:\n");
				System.out.println("\t1) IMPORTA contatti da file CSV");
				System.out.println("\t2) IMPORTA contatti da file XML");
				System.out.println("\t3) IMPORTA contatti da DATABASE\n");
				System.out.println("\t4) ESPORTA contatti su file CSV");
				System.out.println("\t5) ESPORTA contatti su file XML");
				System.out.println("\t6) ESPORTA contatti su DATABASE\n");
				System.out.println("\t7) VISUALIZZA contatti su CONSOLE\n");
				System.out.println("\t0) Chiudi il programma");
				
				command = input.nextLine();
				
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
					
					ab.writeAddressBookCSV(pathFile, separator);
					break;
				case "5":
					System.out.println("\\Inserisci il path del file XML su cui esportare i dati: ");
					pathFile = input.nextLine();
					
					ab.writeAddressBookXML(pathFile);
					break;
				case "6":
					ab.writeAddressBookJDBC();
					break;
				case "7":
					System.out.println(ab.toString());
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
