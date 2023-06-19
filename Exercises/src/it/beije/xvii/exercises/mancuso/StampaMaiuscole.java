package it.beije.xvii.exercises.mancuso;

import java.util.Scanner;

// Scrivere un programma StampaMaiuscole che, dato un array di stringhe, ne stampa solo quelle con la prima lettera maiuscola

// Farei o lo stesso approccio di SoloVocali oppure controlla il valore ascii..? non so se si può in java però

public class StampaMaiuscole {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);	
		System.out.print("Inserisci 5 stringhe.");
		String stringa;
		String[] stringhe = new String [5];
		int i=0;
		
		String maiuscole = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		while(i<5){
			stringa = reader.nextLine();
			stringhe[i] = stringa;
			i++;
		}
		
		for(String s : stringhe) {
			if(maiuscole.contains(String.valueOf(s.charAt(0)))) {
				System.out.println(s);
			}
		}

	}

}
