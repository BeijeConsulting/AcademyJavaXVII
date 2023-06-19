package it.beije.xvii.exercises.ulloa;

import java.util.Scanner;

/*
 * Scrivere un programma che stampi le tabellina del numero dato come argomento
 */

public class Esercizio3 {
	public static void main(String[] args) {
		int num;
		Scanner tastiera = new Scanner(System.in);
		System.out.print("Inserisci il numero della tabellina che vuoi vedere: ");
		num = tastiera.nextInt();
		for (int i = 1; i <= 12; i++) {
			System.out.println(num + " x "+ i + " = " + num*i);
		}
	}
}
