package it.beije.xvii.exercises.mancuso;

import java.util.Scanner;

/* Scrivere un programma SoloVocali che, data una stringa, ne stampa le sole vocali */

public class SoloVocali {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.print("Inserisci una stringa");
		String stringa = reader.nextLine();
		
		String vocali = "aeiouAEIOU";
		
		int length = stringa.length();
		
		for(int i=0; i<length; i++) {
			if(vocali.contains(String.valueOf(stringa.charAt(i)))){
				System.out.print(stringa.charAt(i));
			}
		}
	}
}
