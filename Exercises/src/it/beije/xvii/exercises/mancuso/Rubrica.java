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
			String overwrite;
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
