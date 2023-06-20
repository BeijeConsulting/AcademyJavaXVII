package it.beije.xvii.exercises.ulloa;

import java.util.Scanner;

/*
 * Scrivere un programma StampaZigZag che, dato un array di 10 numeri 
 * interi contenente valori a piacere, ne stampa gli elementi secondo 
 * il seguente ordine: 
 * il primo, l’ultimo, il secondo, il penultimo, il terzo, il terz’ultimo, ecc… 
 */

public class StampaZigZag {
	public static void main(String[] args) {
		int [] numbers = new int[10]; 
		Scanner tastiera = new Scanner(System.in);

		System.out.println("Inserisci "+ numbers.length+" numeri interi");
		for(int i=0; i<numbers.length;i++) {
			System.out.print("Inserisci valore " + (i+1) +": ");
			numbers[i] = tastiera.nextInt();
		}
		
		for(int i=0,j=numbers.length-1; i<numbers.length/2; i++,j--) {
			System.out.println(numbers[i]);
			System.out.println(numbers[j]);
		}
		

	}
}
