package it.beije.xvii.exercises.sala;

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

public class CicloEs8 {
	public static void main(String[] args) {
		CicloEs8 c = new CicloEs8();
		Scanner ts = new Scanner(System.in);
		System.out.println("inserire il valore di cui calcolare la sequenza di fibonacci");
		int num=ts.nextInt();
		
		for(int i=1; i<=num; i++) {
			c.fibonacci(i);
			System.out.println();
	}
		
	}
	
	public void fibonacci(int n) {
		int contatore=1;
		int primoval=1;
		int secondoval=1;
		int somma=0;//primoval+secondoval;
		System.out.print(primoval);
		
		while(contatore<n) {
			somma=primoval+secondoval;
			System.out.print(", "+secondoval);
			primoval=secondoval;
			secondoval=somma;
			contatore++;
		}
	}
		

}
