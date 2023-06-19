package it.beije.xvii.exercises.sala;
import java.util.Scanner;
/*
 * Scrivere un programma Media che calcoli la media di un array di numeri interi
 */
public class ArrayEs8 {

	public static void main(String[] args) {
		int [] a;
		Scanner ts = new Scanner(System.in);
		int nvalori;
		int somma=0;
		
		System.out.println("inserire numero valori");
		nvalori = ts.nextInt();
		a = new int[nvalori];
		
		System.out.println("inserire valori");
		for(int i=0; i<a.length; i++) {
			a[i]=ts.nextInt();
			}
		
		for(int i=0; i<a.length; i++) {
			somma=somma+a[i];
		}
		
		System.out.println("la media è: "+ (double)(somma)/(double)(nvalori));
		
		ts.close();
	}

}
