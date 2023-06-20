package it.beije.xvii.exercises.mancuso;

/*
 * Lo scopo di questo esercizio è convertire una stringa in una nuova stringa, 
 * dove ogni carattere della nuova stringa è ‘(‘ se il carattere corrispondente 
 * nella stringa originale è unico, altrimenti vale ‘)’. Ignorare le maiuscole/minuscole 
 * per decidere se un carattere è duplicato o meno.Per esempio:”din” => “(((“”recede” => “()()()”

“Success” => “)())())”

“(( @” => “))((“
 * 
 * 
 */

public class ConvertiStringa {

	public static void main(String[] args) {
		
		String s = "(( @";
		
		String recurring = "";
		
		String result = "";
		
		for(int i=0; i<s.length(); i++) {
			String myCharacter = String.valueOf(s.charAt(i));
			
			if(recurring.contains(myCharacter.toLowerCase())) {
				result += ")";
			}else {
			
				String preceding = s.substring(0, i).toLowerCase();
				String succeding;
				
				if(i<s.length()-1) {
					succeding = s.substring(i+1).toLowerCase();
				}else {
					succeding = "";
				}
					
				if(preceding.contains(myCharacter.toLowerCase()) || succeding.contains(myCharacter.toLowerCase())) {
					recurring += myCharacter.toLowerCase();
					result += ")";
				}else {
					result += "(";
				}
			}
		}
		
		System.out.println(result);
	}

}
