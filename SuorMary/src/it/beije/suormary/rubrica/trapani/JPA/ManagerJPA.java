package it.beije.suormary.rubrica.trapani.JPA;

import java.util.List;
import java.util.Scanner;

import it.beije.suormary.rubrica.Contact;
import it.beije.suormary.rubrica.JPAentity;

public class ManagerJPA {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String select = null;
		List<Contact> contatti = null;
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
				contatti = DBthroughJPA.listContacts();
				for(Contact s : contatti) {
					System.out.println(s);
				}
				System.out.println();
				break;
				
			case "2": 
				List<Contact> contacts = DBthroughJPA.findContacts();
			
				System.out.println();
				break;
				
			case "3": 
				DBthroughJPA.insertContact();
				System.out.println();
				break;
				
			case "4":
				DBthroughJPA.updateContact();
				System.out.println();
				break;
				
			case "5":
				DBthroughJPA.deleteContact();
				System.out.println();
				break;
				
			case "6":
				contatti = DBthroughJPA.findDuplicateContact();
				if(!contatti.isEmpty()) {
					for(Contact s : contatti) {
						System.out.println(s);
					}
				}
				System.out.println();
				break;
				
			case "7":
				DBthroughJPA.mergeDuplicate();
				System.out.println();
				break;
				
			case "close": continua = false;
				break;

			default: System.out.println("Opzione non valida");
				System.out.println();
				break;
			}
			
		}	
		
		JPAentity.getEntityManager().close();
	}

}
