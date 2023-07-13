package it.beije.suormary.rubrica.caroselli.JPA;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class RubricaManagerJPA {

	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
	

		boolean exit = false;

		while (!exit) {
			
			System.out.println("\nBenvenuto nella rubrica\n");
			System.out.println("Inserisci 1 per vedere la lista dei contatti,");
			System.out.println("Inserisci 2 per cercare un contatto,");
			System.out.println("Inserisci 3 per inserire un nuovo contatto,");
			System.out.println("Inserisci 4 per modificare un contatto");
			System.out.println("Inserisci 5 per cancellare un contatto,");
			System.out.println("Inserisci 6 per trovare i contatti duplicati,");
			System.out.println("Inserisci 7 per unire contatti duplicati");
			System.out.println("Inserisci 8 per importare o esportare i contatti da/a CSV");
			System.out.println("Inserisci 9 per importare o esportare i contatti da/a XML");
			System.out.println("Inserisci 0 per uscire");
			System.out.println("----------------------------------------------------------\n");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.println(RubricaUtilsJPA.readContactsFromDb());
				break;
			case 2:
				RubricaUtilsJPA.findContactsFromInsertedValue();
				break;
			case 3:
				RubricaUtilsJPA.insertContact();
				break;
			case 4:
				RubricaUtilsJPA.changeContact();
				break;
			case 5:
				RubricaUtilsJPA.deleteContact();
				break;
			case 6:
				RubricaUtilsJPA.findDuplicatesContactByValue();
				break;
			case 7:
				RubricaUtilsJPA.mergeDuplicatesContact();
				break;
			case 8:
				RubricaUtilsJPA.importExportFromToCSV();
				break;
			case 9:
				RubricaUtilsJPA.importExportFromToXML();
				break;
			case 0:
				exit = true;
				break;
			default:
				System.out.println("Scelta non valida. Riprova.");
			}

			
		}

	scanner.close();
	}
}
