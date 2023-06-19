package it.beije.xvii.exercises.sala;

/*
 * Scrivere un programma che stampi i primi 100 elementi della successione di Fibonacci.
 */

public class CicloEs7 {

	public static void main(String[] args) {
		
		int primoval=1;
		int secondoval=1;
		int contatore=100;
		int somma=primoval+secondoval;
		System.out.print(primoval+" ");
		 
		
		
		while(contatore>0) {
			somma=primoval+secondoval;
			System.out.print(secondoval+" ");
			primoval=secondoval;
			secondoval=somma;
			contatore--;
			
		}
		

	}

}
