package it.beije.xvii.exercises.sala;
/*
 * Scrivere una funzione che prende un numero intero n come parametro e ritorna un array di sfumature di grigio in 
 * codice esadecimale (#aaaaaa, per esempio). L’array dovrebbe essere ordinato in senso ascendente (#010101, #020202, ecc), 
 * usando le lettere minuscole.
 * public class ShadesOfGrey {
static String[] shadesOfGrey(int num) {
// returns n shades of grey in an array
}
}Ricorda che: il grigio è un colore composto dalla stessa quantità di rosso, verde e blu: #010101, #aeaeae, #555555. 
Inoltre: #000000 e #FFFFFF non sono valori accettati.Se n è negativo ritornare un array vuoto, se n è maggiore di 254, 
ritornare un array di 254 elementi.
 */
public class Shades {

	public static String[] shadesOfGrey(int num) {
		if(num<=0)
			return new String[0];
		if(num>254) {
			num=254;
		}
		
		String[] s = new String[num];
		
			for(int i=0; i<num; i++) {
				String hex = String.format("%2dx", i);
				String finale="#"+hex+hex+hex;
				finale=finale.toLowerCase();
				s[i]=finale;
			}
		
		return s;
	}
	
	
	public static void main(String[] args) {
		String[] s = Shades.shadesOfGrey(5);
		if(s.length==0) {
			System.out.println("l'array è vuoto");
		} else{
			
			for(String st : s) {
				System.out.println(st);
			}
			
		}
		
		}
	
}

