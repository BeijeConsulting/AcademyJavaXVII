package it.beije.xvii.exercises.ulloa;

/*
 * Scrivere un programma MediaMultipliDiTre che calcoli la media di un 
 * array di numeri interi, considerando i soli numeri divisibili per tre.
 */

public class MediaMultipliDiTre {
	public static void main(String[] args) {
		int [] numbers = {3,2,-1,0,0,0,2,2};
		int somma = 0;
		int n=0;
		double media = 0.0;
		
		for(int i=0;i<numbers.length;i++) {
			if(numbers[i]%3==0) {
				somma = somma + numbers[i];
				n++;
			}
		}
		
		media = (double) somma / n;
		System.out.println("La media calcolata e': " + media);
	}

}
