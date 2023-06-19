package it.beije.xvii.exercises.mancuso;

import java.util.Scanner;

// Scrivere un programma Media che calcoli la media di un array di numeri interi

public class Media {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		final int size = 10;
		
		int[] numeri = new int[size];
		
		for(int i=0; i<size; i++) {
			System.out.println("Inserisci un numero: ");
			numeri[i] = reader.nextInt();
		}
		reader.close();
		int sum = 0;
		
		for(int n : numeri) {
			sum += n;
		}
		
		int media = sum/size;
		
		System.out.println("Media dei numeri inseriti: " + media);
		
	}

}
