package it.beije.xvii.exercises.mancuso;

import java.util.Scanner;

/*
 * Scrivere un programma Contrario che, data una stringa, la stampa al contrario. Per esempio, la stringa “Viva Java!” verrà “!avaJ aviV”
 * */

public class Contrario {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);	
		
		System.out.print("Inserisci una stringa:\n");
		String s = reader.nextLine();
		
		StringBuilder sb = new StringBuilder(s);
		
		sb.reverse();
		
		System.out.println(sb);
	}

}
