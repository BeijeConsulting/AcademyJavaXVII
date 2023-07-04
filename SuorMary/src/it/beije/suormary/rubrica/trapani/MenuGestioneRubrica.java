package it.beije.suormary.rubrica.trapani;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


//vedi lista contatti (con possibilità di ordinare per nome e cognome a scelta)  --
//cerca contatto --
//inserisci nuovo contatto  --
//modifica contatto --
//cancella contatto --
//trova contatti duplicati
//unisci contatti duplicati

public class MenuGestioneRubrica {
	

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String select = null;
		List<Contact> contatti = new ArrayList<>();
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
				contatti = AzioniGestore.listContact();
				for(Contact s : contatti) {
					System.out.println(s);
				}
				System.out.println();
				continue;
			case "2": 
				contatti = AzioniGestore.findContact();
				for(Contact s : contatti) {
					System.out.println(s);
				}
				System.out.println();
				continue;
				
			case "3":
				continue;
			case "4":
				continue;
			case "5":
				continue;
			case "6":
				continue;
			case "7":
				continue;
			case "close": continua = false;
				continue;

			default: System.out.println("Opzione non valida");
				break;
			}
			
		}
			
			
		



	}

}
