package it.beije.xvii.exercises.ulloa;

/*
 * Scrivere un programma Media che calcoli la media di un array di numeri interi
 */

public class Media {
	public static void main(String[] args) {
		int [] numbers = {3,2,-1,0,0,0,2,2};
		int somma = 0;
		double media = 0.0;
		
		for(int i=0;i<numbers.length;i++) {
			somma = somma + numbers[i];
		}
		
		media = (double) somma / numbers.length;
		System.out.println("La media calcolata e': " + media);
	}
}
