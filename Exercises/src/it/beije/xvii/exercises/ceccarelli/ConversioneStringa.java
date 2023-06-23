package it.beije.xvii.exercises.ceccarelli;

public class ConversioneStringa {
	/*
	 * Lo scopo di questo esercizio è convertire una stringa in una nuova stringa, 
	 * dove ogni carattere della nuova stringa è ‘(‘ se il carattere corrispondente nella stringa originale è unico, altrimenti vale ‘)’. 
	 * Ignorare le maiuscole/minuscole per decidere se un carattere è duplicato o meno.Per esempio:”din” => “(((“”recede” => “()()()”
	“Success” => “)())())”
	“(( @” => “))((“
	 */

	public ConversioneStringa() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "(( @";
		String newS ="";
		for(int i=0;i<s.length();i++) {
			char curr = s.charAt(i);
			int count=0;
			for(int y=0;y<s.length();y++) {
				if(s.charAt(y)==curr) {
					count++;
				}
			}
			if(count>1) {
				newS+=')';
			}else {
				newS+='(';
			}
		}
		System.out.println("nuova stringa: " + newS);
	}

}
