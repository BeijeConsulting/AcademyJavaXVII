package it.beije.xvii.exercises.mancuso;

/*
 * Scrivere un programma StampaZigZag che, dato un array di 10 numeri interi 
 * contenente valori a piacere, ne stampa gli elementi secondo il seguente ordine: 
 * il primo, l’ultimo, il secondo, il penultimo, il terzo, il terz’ultimo, ecc…
 */

public class StampaZigZag {

	public static void main(String[] args) {
		int[] numeri = {1,2,3,4,5,6,7,8,9,10};
		
		int i=0, j=9;
		
		for(; i<10; i++,j--) {
			System.out.println(numeri[i] + " - " + numeri[j]);
		}
	}

}
