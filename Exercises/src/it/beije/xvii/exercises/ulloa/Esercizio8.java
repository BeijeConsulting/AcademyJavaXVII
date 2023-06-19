package it.beije.xvii.exercises.ulloa;

import java.util.Scanner;

/*
 * Scrivere un programma che stampi le n righe della successione di Fibonacci in questo modo:
1
1, 1
1, 1, 2
1, 1, 2, 3
1, 1, 2, 3, 5
1, 1, 2, 3, 5, 8
1, 1, 2, 3, 5, 8, 13
 */

public class Esercizio8 {
	
	public void fibonacci(int n) {
		int n1=1;
		int n2=1;
		int n3;
		System.out.print(n1);
		for(int i=1;i<n;i++) {
			System.out.print(", "+ n2);
			n3=n1+n2;
			n1=n2;
			n2=n3;    
		 }
	}
	
	public static void main(String[] args) {
		int num;
		Scanner tastiera = new Scanner(System.in);
		System.out.print("Inserisci il numero di riga per vedere la successione di Fibonacci : ");
		num = tastiera.nextInt();
		Esercizio8 t = new Esercizio8();
		for(int i=1; i <= num ;i++) {
			t.fibonacci(i);
			System.out.println();
		}
	}
}
