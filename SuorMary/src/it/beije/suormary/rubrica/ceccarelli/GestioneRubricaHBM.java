package it.beije.suormary.rubrica.ceccarelli;

import java.util.Scanner;

public class GestioneRubricaHBM {

	public static void menu() {
		System.out.println("Scegli tra le seguenti opzioni: ");
        System.out.println("1. Visualizza lista contatti");
        System.out.println("2. Cerca contatto");
        System.out.println("3. Inserisci nuovo contatto");
        System.out.println("4. Modifica contatto");
        System.out.println("5. Cancella contatto");
        System.out.println("6. Trova contatti duplicati");
        System.out.println("7. Unisci contatti duplicati");
        System.out.println("8. Esci");
        System.out.print("Scelta: ");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		MetodiRubricaHBM mr  = new MetodiRubricaHBM();
		menu();
		String scelta = scan.next();
		
		switch(scelta) {
			case "1":
				//System.out.println("scelta 1");
				mr.listContacts();
				
				break;
			case "2":
				//System.out.println("scelta 2");
				mr.searchContacts();
				break;
			case "3":
				//System.out.println("scelta 3");
				mr.insertContacts();
				break;
			case "4":
				//System.out.println("scelta 4");
				mr.updateContacts();
				break;
			case "5":
				//System.out.println("scelta 5");
				mr.deleteContact();
				break;
			case "6":
				System.out.println("scelta 6");
				break;
			case "7":
				System.out.println("scelta 7");
				break;
			case "8":
				System.exit(0);
				break;
		}
		System.out.println("finito");
		scan.close();
	}

}
