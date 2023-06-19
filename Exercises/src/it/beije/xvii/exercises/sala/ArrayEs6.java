package it.beije.xvii.exercises.sala;
import java.util.Scanner;
/*
 * Scrivere un programma MediaMultipliDiTre che calcoli la media di un array di numeri interi, 
 * considerando i soli numeri divisibili per tre.
 */
public class ArrayEs6 {

	public static void main(String[] args) {
		Scanner ts=new Scanner(System.in);
		int media;
		int nvalori;
		int [] a;
		ArrayEs6 o = new ArrayEs6();
		
		System.out.println("inserisci numero di elementi");
		nvalori=ts.nextInt();
		a = new int[nvalori];
		
		System.out.println("inserisci valori");
		for(int i=0; i<a.length; i++) {
			a[i]=ts.nextInt();
		}
		
		media=o.mediaMultipliDiTre(a);
		if(media==0) {
			System.out.println("non ci sono multipli di 3");
		} else
			System.out.println("ci sono multipli di 3 e la loro media fa: "+media);
		
		ts.close();
	}
	
	
	public int mediaMultipliDiTre(int [] a) {
		int somma=0;
		int freq = 0;
		int media=0;
		
		for(int i=0; i<a.length; i++) {
			if(a[i]%3==0) {
				somma=somma+a[i];
				freq++;
			}
		}
		
		if(somma!=0) {
			media=somma/freq;	
		}
		
		return media;
	}
	
	

}
