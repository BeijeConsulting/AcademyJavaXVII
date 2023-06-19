package it.beije.xvii.exercises.ulloa;

import java.util.Scanner;

/*
 * Scrivere un metodo che, data una stringa in input, assuma questa come un nome di variabile e 
 * stampi per questa variabile il suo metodo “setter” 
 * Scrivere un metodo che, data una stringa in input, assuma questa come un nome di variabile e 
 * stampi per questa variabile il suo metodo “getter”
 */

public class GetterSetter {

	public static void main(String[] args) {
		GetterSetter gs = new GetterSetter();
		String s;

		Scanner tastiera = new Scanner(System.in);
		System.out.print("Inserisci nome variabile: ");
		s = tastiera.next();
		
		System.out.println(gs.getter(s));
		System.out.println(gs.setter(s));
	}
	
	public String setter(String s) {
		s = "set" + capitalize(s);
		return s;
	}
	
	public String getter(String s) {
		s = "get" + capitalize(s);
		return s;
	}
	
	/*
	 * N.B.: Non utilizzare StringBuilder come parametro in ingresso nei metodi perché essendo
	 * mutevole può portare a degli errori man mano il programma cresce
	 */
	protected String capitalize(String s) { 
		StringBuilder sb = new StringBuilder();
		sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1).toLowerCase());
		s = sb.toString();
		return s;
	}

}
