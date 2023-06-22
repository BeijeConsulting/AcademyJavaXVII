package it.beije.xvii.exercises.trapani;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class AggiuntaArray {
	
	public static String[] addString(String s, String[] a) {
		List<String> ris = Arrays.asList(a);
		ris.set((a.length -1),s);
		String[] risultato = ris.toArray(new String[0]);
		return risultato;
	}
	

	public static void main(String[] args) {
		String[] array = {"Ben ", "arrivata ", " "};  //creo l'array di stringhe
		Scanner in = new Scanner (System.in);
		
		System.out.println("inserisci tuo nome:");
		String nome =in.nextLine();		
		
		
		
		String[] ris = addString(nome,array);
		
		for (int i=0; i<ris.length; i++ ) {
			System.out.print(ris[i]);
		}
		
		

	}

}
