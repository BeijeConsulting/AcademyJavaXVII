package it.beije.xvii.exercises.ulloa;

import java.util.Scanner;

/*
 * Scrivere un programma Concatena che chiede all’utente di inserire tre singole parole e 
 * le ristampa interponendovi un asterisco. Per esempio, se l’utente inserisce “gatto”, 
 * “cane” e “topo” il programma stamperà “gatto*cane*topo”. 
 */

public class Concatena {

	public static void main(String[] args) {
		String s;
		Scanner tastiera = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		System.out.println("Inserisci tre singole parole");
		
		for(int i=0; i<3;i++) {
			System.out.print("Inserisci parola "+(i+1)+": ");
			s = tastiera.next();
			sb.append(s).append('*');
		}
		
		//cancello l'ultimo carattere *
		s = sb.deleteCharAt(sb.length()-1).toString();
		System.out.print(s);

	}

}
