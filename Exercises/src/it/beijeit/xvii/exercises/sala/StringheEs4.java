package it.beijeit.xvii.exercises.sala;
import java.util.Scanner;
/*
 * Scrivere un programma Contrario che, data una stringa, la stampa al contrario. 
 * Per esempio, la stringa “Viva Java!” verrà “!avaJ aviV”
 */

public class StringheEs4 {
	public static void main(String [] args) {
		Scanner ts = new Scanner(System.in);
		System.out.println("inserisci una nuova parola");
		String s = ts.nextLine();
		//System.out.print(s);
		String reverse="";
		
		for(int i=s.length()-1; i>=0 ;i--) {
			reverse= reverse+s.charAt(i);
			//System.out.print(reverse);
		}
		
		System.out.println("la stringa al contrario è: "+reverse);
		ts.close();
	}
	
	
}
