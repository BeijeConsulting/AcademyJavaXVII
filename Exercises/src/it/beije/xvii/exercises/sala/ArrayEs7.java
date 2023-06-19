package it.beije.xvii.exercises.sala;

/*
 * Scrivere un programma StampaZigZag che, dato un array di 10 numeri interi contenente valori a 
 * piacere, ne stampa gli elementi secondo il seguente ordine: il primo, l’ultimo, il secondo, il 
 * penultimo, il terzo, il terz’ultimo, ecc…
 */
public class ArrayEs7 {

	public static void main(String[] args) {
	int [] a = {12, 3, 45, 67, 2, 89, 2, 1, 9, 0};
	
	/*for(int i=0; i<a.length; i++) {
		System.out.println(a[i]);
	}
	*/
	int j=0;
	for (int i=0; i<5; i++) {
		j=9-i;
		System.out.println(a[i]);
		System.out.println(a[j]);
	}
	

		
	}
	
	

}
