package it.beije.xvii.exercises.mancuso;

import java.util.ArrayList;

/*
 * Scrivere il metodo: “public String [] addString(String s, String[] a)”, che accetta come
 * parametri una stringa ed un array di stringhe. Restituisce un nuovo array, identico ad array,
 * aggiungendo però, come ultimo elemento, la stringa s.
 */

public class AggiungiStringa {
	
	public String[] addString(String s, String[] a) {
		ArrayList<String> newString = new ArrayList<>();
		
		for(int i=0; i<a.length; i++) {
			newString.add(a[i]);
		}
		
		newString.add(s);
		
		String[] newArray = newString.toArray(new String[0]);
		
		return newArray;
	}
	
	public static void main(String[] args) {
		String[] myArray = {"ciao", "hello", "konnichiwa"};
		String s = "bonjour";
		
		AggiungiStringa agg = new AggiungiStringa();
		
		String[] newArray = agg.addString(s, myArray);
		
		for(int i=0; i<newArray.length; i++) {
			System.out.println(newArray[i]);
		}

	}

}
