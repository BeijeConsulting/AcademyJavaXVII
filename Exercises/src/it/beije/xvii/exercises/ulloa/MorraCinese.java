package it.beije.xvii.exercises.ulloa;

import java.util.Scanner;

/*
 * Scrivere un programma che chieda agli utenti due stringhe in ingresso, 
 * le stringhe possono valere solo: “carta”, “forbice” o “sasso”. 
 * Il programma dovrà quindi effettuare i dovuti controlli e dichiarare 
 * il vincitore secondo le note regole della “morra cinese” (forbice vince 
 * su carta, carta vince su sasso, sasso vince su forbice).
 */

public class MorraCinese {
	public static void main(String[] args) {
		final int NUM_UTENTI = 2;
		boolean errore;
		String utenti[] = new String[NUM_UTENTI];
		String mosse[] = new String[NUM_UTENTI];
		Scanner tastiera = new Scanner(System.in);
		
		for(int i=0; i<NUM_UTENTI;i++) {
			System.out.print("Inserisci nome dell'utente "+(i+1)+": ");
			utenti[i] = tastiera.next();
			
			do {
				System.out.print("Inserisci mossa dell'utente "+(i+1)+": ");
				mosse[i] = tastiera.next().toLowerCase();
				
				switch(mosse[i]) {
				case "carta": 
				case "forbice":
				case "sasso": errore = false; break;
				default: System.out.print("Hai sbagliato!\n"
						+ "Devi inserire solo carta, forbice o sasso. Riprova:");
				errore=true;break;
				}
			} while(errore);
			
		}
		
		if (mosse[0].equals(mosse[1])){
			System.out.println("Pareggio!");
		} else if (mosse[0].equals("sasso") && mosse[1].equals("forbice")
        || mosse[0].equals("carta") && mosse[1].equals("sasso")
        || mosse[0].equals("forbice") && mosse[1].equals("carta")) {
			System.out.println("Ha vinto " + utenti[0]);
		} else 
			System.out.println("Ha vinto " + utenti[1]);
		}
	}
