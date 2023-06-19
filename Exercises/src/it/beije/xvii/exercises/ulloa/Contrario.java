package it.beije.xvii.exercises.ulloa;

import java.util.Scanner;

/*
 * Scrivere un programma Contrario che, data una stringa, la stampa al contrario. 
 * Per esempio, la stringa “Viva Java!” verrà “!avaJ aviV” 
 */

public class Contrario {

	public static void main(String[] args) {
		Scanner tastiera = new Scanner(System.in);
		String s;
		
		System.out.print("Inserisci una frase: ");
		s = tastiera.nextLine();
		
		StringBuilder sb = new StringBuilder(s);
		s = sb.reverse().toString();
		System.out.println(s);
	}

}
