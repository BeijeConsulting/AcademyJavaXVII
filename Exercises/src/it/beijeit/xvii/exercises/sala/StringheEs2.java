package it.beijeit.xvii.exercises.sala;
import java.util.Scanner;
/*
 * Scrivere un programma StampaMaiuscole che, dato un array di stringhe, ne stampa 
 * solo quelle con la prima lettera maiuscola
 */
public class StringheEs2 {

	public static void main(String[] args) {
		Scanner ts=new Scanner(System.in);
		System.out.println("inserisci numero parole");
		int n = ts.nextInt();
		
		String[] a1 = new String[n];
		String s = new String();
		
		for(int i=0; i< a1.length; i++) {
			System.out.println("inserisci nuova parola");
			s=ts.nextLine();
			a1[i]=s;
		}
		
		System.out.println("le parole cercate sono: ");
		for(int i=0; i<a1.length; i++) {
			if(a1[i].charAt(0)>='A' && a1[i].charAt(0)<='Z') {
				System.out.println(a1[i]);
			}
		}
		ts.close();
	}

}
