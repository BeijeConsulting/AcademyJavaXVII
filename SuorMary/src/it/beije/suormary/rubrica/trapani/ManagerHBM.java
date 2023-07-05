package it.beije.suormary.rubrica.trapani;

import java.util.List;
import java.util.Scanner;

import it.beije.suormary.rubrica.Contact;

public class ManagerHBM {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String select = null;
//		 contatti = new ArrayList<>();
		boolean continua = true;
		//MENU OPZIONI
		while(continua) {
			System.out.println("Scegliere una tra le seguenti opzioni: \n"
						+ "1. Visualizza lista contatti completa\n"
						+ "2. Cerca contatto\n"
						+ "3. Inserisci nuovo contatto\n"
						+ "4. Modifica contatto\n"
						+ "5. Cancella contatton\n"
						+ "6. Trova contatti doppi\n"
						+ "7. Unisci contatti doppi\n"
						+ "Per uscire digitare: close\n");
			
			select = in.nextLine();
			
			switch (select) {
			case "1":
				List<Contact> contatti = DBthroughHBM.listContacts();
				for(Contact s : contatti) {
					System.out.println(s);
				}
				System.out.println();
				break;
				
			case "2": 
//				List<Contact> contacts = DBthroughHBM.findContact();
//				for(Contact s : contacts) {
//					System.out.println(s);
//				}
				System.out.println();
				break;
				
			case "3": 
				DBthroughHBM.insertContact();
				System.out.println();
				break;
				
			case "4":
				DBthroughHBM.updateContact();
				System.out.println();
				break;
				
			case "5":
				DBthroughHBM.deleteContact();
				System.out.println();
				break;
				
			case "6":
				AzioniGestore.findDuplicateContact();
				System.out.println();
				break;
				
			case "7":
				AzioniGestore.mergeDuplicateContac();
				System.out.println();
				break;
				
			case "close": continua = false;
				break;

			default: System.out.println("Opzione non valida");
				System.out.println();
				break;
			}
			
		}
			
			
		



	}

	

}
