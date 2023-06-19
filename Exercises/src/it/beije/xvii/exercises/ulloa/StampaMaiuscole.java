package it.beije.xvii.exercises.ulloa;

import java.util.Scanner;

/*
 * Scrivere un programma StampaMaiuscole che, dato un array di stringhe, 
 * ne stampa solo quelle con la prima lettera maiuscola
 */

public class StampaMaiuscole {

	public static void main(String[] args) {
		int numStrings;
		String strings[];
		Scanner tastiera = new Scanner(System.in);
		
		System.out.print("Inserisci il numero di parole che vuoi inserire: ");
		numStrings = tastiera.nextInt();
		strings = new String[numStrings];
		for(int i=0; i<numStrings;i++) {
			System.out.print("Inserisci parola "+(i+1)+": ");
			strings[i] = tastiera.next();
		}
		
		for(int i=0; i<numStrings;i++) {
			int primaLettera = strings[i].charAt(0);
			if(primaLettera>=65 && primaLettera <=90) { //codice ASCII da A a Z
				System.out.println(strings[i]);
			}
		}

	}

}
