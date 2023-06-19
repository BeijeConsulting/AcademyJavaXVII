package it.beije.xvii.exercises.mancuso;

import java.util.ArrayList;

/*
 * Scrivere una funzione che prende un numero intero n come parametro e ritorna un array di sfumature di grigio 
 * in codice esadecimale (#aaaaaa, per esempio). L’array dovrebbe essere ordinato in senso ascendente 
 * (#010101, #020202, ecc), usando le lettere minuscole.public class ShadesOfGrey {
 * static String[] shadesOfGrey(int num) {
 *     //returns n shades of grey in an array
 * }
 * }Ricorda che: il grigio è un colore composto dalla stessa quantità di rosso, verde e blu: #010101, #aeaeae, #555555. 
 * Inoltre: #000000 e #FFFFFF non sono valori accettati.Se n è negativo ritornare un array vuoto, se n è 
 * maggiore di 254, ritornare un array di 254 elementi.
 * 
 */

public class GrigioEsadecimale {
	
	public ArrayList<String> generateArray(int n) {
		
		ArrayList<String> grigi = new ArrayList<>();
		
		for(int i=1; i<n && i<255; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			int decina = i/16;
			int unita = i-(decina*16);
			for(int j=0; j<3; j++) {
				if(decina>=10) {
					char lettera = (char)(decina+55);
					sb.append(lettera);
				}else {
					sb.append(decina);
				}
				if(unita>=10) {
					char lettera = (char)(unita+55);
					sb.append(lettera);
				}else {
					sb.append(unita);
				}
			}
			grigi.add(sb.toString());
		}
		
		return grigi;
	}
	
	public static void main(String[] args) {
		
		GrigioEsadecimale ge = new GrigioEsadecimale();
		ArrayList<String> myArray = ge.generateArray(255);
		for(String s : myArray) {
			System.out.println(s);
		}
		//System.out.println(16/16);
	}

}
