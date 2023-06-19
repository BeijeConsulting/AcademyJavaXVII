package it.beije.xvii.exercises.sala;
import java.util.Scanner;
/*
 * Scrivere un programma SoloVocali che, data una stringa, ne stampa le sole vocali
 */
public class StringheEs1 {

	public static void main(String[] args) {
		Scanner ts=new Scanner (System.in);
		System.out.println("inserisci una frase o una parola");
		
		ts.close();
	}

	public String soloVocali(String s) {
		String nuova="";
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i)=='a' || s.charAt(i)=='A' || s.charAt(i)=='e' || s.charAt(i)=='E' ||
					s.charAt(i)=='i' || s.charAt(i)=='I' || s.charAt(i)=='o' || s.charAt(i)=='O' ||
					s.charAt(i)=='u' || s.charAt(i)=='U') {
				nuova=nuova+s.charAt(i);
			}
		}
		return nuova;
	}
}
