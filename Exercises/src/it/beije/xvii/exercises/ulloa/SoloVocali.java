package it.beije.xvii.exercises.ulloa;

import java.util.Scanner;

/*
 * Scrivere un programma SoloVocali che, data una stringa, ne stampa le sole vocali
 */

//provare a fare con contains e una variabile string vocali AaEeIiOoUu
public class SoloVocali {

	public static void main(String[] args) {
		Scanner tastiera = new Scanner(System.in);
		String s;
		String vocali = "";
		char c;
		
		System.out.print("Inserisci una parola: ");
		s = tastiera.next();
		
		for(int i=0; i<s.length(); i++) {
			c = s.charAt(i);
			switch(c) {
			case 'A':
			case 'a': 
			case 'E':
			case 'e':
			case 'I':
			case 'i':
			case 'O':
			case 'o':
			case 'U':
			case 'u': vocali+=c; break;
			default:break; //bisogna sempre metterlo anche se non è previsto fare nulla di default
			}
			
		}
		System.out.println(vocali);
		
	}

}
