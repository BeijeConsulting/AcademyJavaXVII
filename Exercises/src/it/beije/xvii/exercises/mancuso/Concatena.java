package it.beije.xvii.exercises.mancuso;

import java.util.Scanner;

/*
 * Scrivere un programma Concatena che chiede all’utente di inserire tre singole parole e le ristampa interponendovi un asterisco.
 *  Per esempio, se l’utente inserisce “gatto”, “cane” e “topo” il programma stamperà “gatto*cane*topo”.
 */

public class Concatena {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);	
		String concat = "";
		
		for(int i=0; i<3; i++) {
			System.out.print("Inserisci una parola:\n");
			String s = reader.next();
			concat += s;
			if(i<2) {
				concat += "*";
			}
		}
		
		System.out.print(concat);
	}

}
