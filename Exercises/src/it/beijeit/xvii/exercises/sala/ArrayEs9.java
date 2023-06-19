package it.beijeit.xvii.exercises.sala;

import java.util.Scanner;

/*
 * Scrivere il metodo: “public String [] addString(String s, String[] a)”, che accetta come parametri 
 * una stringa ed un array di stringhe. Restituisce un nuovo array, identico ad array, aggiungendo però, 
 * come ultimo elemento, la stringa s
 */
public class ArrayEs9 {

	public static void main(String[] args) {
		String [] a;
		Scanner ts = new Scanner(System.in);
		int nvalori;
		
		System.out.println("inserire numero valori");
		nvalori = ts.nextInt();
		a = new String[nvalori];
		
		
		System.out.println("inserire valori");
		for(int i=0; i<a.length; i++) {
			a[i]=ts.next();
			}
		
		
		System.out.println("scrivi una nuova parola");
		String s1 = ts.next();
		String[] a1 = new String[a.length+1];
		ArrayEs9 o = new ArrayEs9();
		a1=	o.addString(s1, a);

			for(String s : a1) {
				System.out.println(s);
			}
			
		//System.out.println(a1.length);
		//System.out.println(a1[0]);
		//System.out.println(a1[1]);
		
		/*
		//array originario
		for(String s : a) {
			System.out.println(s);
		}
		
		//array nuovo
		for(String s : a1) {
			System.out.println(s);
		}*/
		
		/*for(int i=0; i<a1.length; i++) {
			System.out.println(a[i]);
		}*/
			ts.close();
	}
	
	public String[] addString(String s, String[] a) {
		String [] copia = new String[a.length+1];
		
		for(int i=0; i<a.length; i++) {
			copia[i]=a[i];
		}
		
			copia[copia.length-1]=s;
		
		return copia;
	}

}
