package it.beije.xvii.exercises.mancuso;

import java.util.Scanner;

/*
 * Scrivere il metodo " public int contaLettera(char c, String str) "
 * che conta le occorrenze della lettera c nella stringa str
 */

public class Es11 {
	
	public int contaLettera(char c, String str) {
		int cont=0;
		for(int i=0; i<str.length();i++) {
			if(str.charAt(i) == c) {
				cont++;
			}
		}
		return cont;
	}
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);	
		
		System.out.print("Inserisci una stringa:\n");
		String s = reader.nextLine();
		
		System.out.print("Inserisci il carattere da contare:\n");
		char c = reader.next().charAt(0);
		reader.close();
		Es11 letsCount = new Es11();
		
		System.out.println(letsCount.contaLettera(c,s));
	}

}
