package it.beije.xvii.exercises.mancuso;

import java.util.Scanner;

/*
 * Scrivere un metodo che, data una stringa in input, assuma questa come un nome di variabile e stampi per questa variabile il suo metodo “setter”
 * Scrivere un metodo che, data una stringa in input, assuma questa come un nome di variabile e stampi per questa variabile il suo metodo “getter”
 * 
 */

public class SetterGetter {
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		String s = reader.nextLine();
		reader.close();
				
		s = s.substring(0,1).toUpperCase() + s.substring(1);
		
		System.out.println("set" + s);
		System.out.println("get" + s);
	}

}
