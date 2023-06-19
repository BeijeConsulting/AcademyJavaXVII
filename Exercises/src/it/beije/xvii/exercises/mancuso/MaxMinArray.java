package it.beije.xvii.exercises.mancuso;

/*
 * Trovare il massimo elemento in un array (o il minimo)
*/

public class MaxMinArray {

	public static void main(String[] args) {
		int [] numeri = {7,3,5,2,9,12,4};
		
		int max = numeri[0];
		int min = numeri[0];
		
		for(int i=0;i<numeri.length; i++) {
			if(numeri[i]>max) {
				max = numeri[i];
			}
			if(numeri[i]<min) {
				min = numeri[i];
			}
		}
		
		System.out.println("Numero massimo: " + max);
		System.out.println("Numero minimo: " + min);
	}

}
