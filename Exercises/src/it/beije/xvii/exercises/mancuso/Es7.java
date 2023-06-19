package it.beije.xvii.exercises.mancuso;

/*
 * Scrivere un programma che stampi i primi 100 elementi della successione di Fibonacci.
 */

public class Es7 {
	public static void main(String[] args) {
		long n1 = 1;
		long n2 = 1;
		for(int i=0; i<100; i++) {
			if(i<2) {
				System.out.println(1 + ", ");
			}else {
				long n = n1 + n2;
				n2 = n1;
				n1 = n;
				System.out.println(n + ", ");
			}
		}
		
	}
}
