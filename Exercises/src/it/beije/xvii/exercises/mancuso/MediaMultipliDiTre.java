package it.beije.xvii.exercises.mancuso;

/*
 * Scrivere un programma MediaMultipliDiTre che calcoli la 
 * media di un array di numeri interi, considerando i soli numeri divisibili per tre.
 */

public class MediaMultipliDiTre {

	public static void main(String[] args) {
		int[] numeri = {3,3,2,4,6};
		int sum = 0;
		int num = 0;
		for(int n : numeri) {
			if(n%3==0) {
				num++;
				sum += n;
			}
		}
		
		int media = sum/num;
		
		System.out.print(media);

	}

}
