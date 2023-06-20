package it.beije.xvii.exercises.mancuso;

/*
 * Scrivere una funzione che verifichi la correttezza di una stringa in cui sono inserite delle parentesi. 
 * In programmazione, ma anche nel linguaggio naturale, le istruzioni o le frasi possono essere inserite in 
 * gruppi di parentesi: (), [] o {}. All’apertura di una parentesi ci si aspetta una chiusura coerente, tipo: 
 * [], ([]), {[]({})}. I gruppi che non vengono chiusi o non vengono chiusi in ordine corretto vengono considerati 
 * sbagliati. Per esempio: [{], ([)], [(.

 */

public class Groups {
	
	public static boolean groupCheck(String s) {

		int sum = 0;
		
		for(int i=0; i<s.length(); i++) {
			int value;
			switch(s.charAt(i)) {
			case '{':
				value = 3;
				break;
			case '}':
				value = -3;
				break;
			case '[':
				value = 2;
				break;
			case ']':
				value = -2;
				break;
			case '(':
				value = 1;
				break;
			case ')':
				value = -1;
				break;
			default:
				value = 0;
				break;
			}
			
			sum += value;
		}
		
		if(sum == 0) {
			return true;
		}else {
			return false;
		}	
	}
	
	
	public static void main(String[] args) {
		String s = "[{(s)}kl]";
		System.out.println(groupCheck(s));
	}

}
