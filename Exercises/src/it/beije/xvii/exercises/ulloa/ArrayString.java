package it.beije.xvii.exercises.ulloa;

import java.util.ArrayList;

/*
 * Scrivere il metodo: “public String [] addString(String s, String[] a)”, 
 * che accetta come parametri una stringa ed un array di stringhe. 
 * Restituisce un nuovo array, identico ad array, aggiungendo però, 
 * come ultimo elemento, la stringa s. 
 */

public class ArrayString {
	public static void main(String[] args) {
		ArrayString arrayString = new ArrayString();
		
		String[] stringhe = {"pippo", "pluto", "paperino"};
		String s = "topolino";
		
		String[] newStringArray = arrayString.addString(s, stringhe);
		
		for(int i=0;i<newStringArray.length;i++) {
			System.out.println(newStringArray[i]);
		}
		
	
	}
	
	public String [] addString(String s, String[] a) {
		ArrayList<String> arrayList = new ArrayList<>();
		for(int i=0; i<a.length;i++) {
			arrayList.add(a[i]);
		}
		arrayList.add(s);
		
		//Converte una ArrayList<String> in un array di String
		String[] newStringArray = arrayList.toArray(new String[0]);
		
		return newStringArray;
	}
}
