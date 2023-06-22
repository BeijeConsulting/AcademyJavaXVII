package it.beije.xvii.exercises.ceccarelli;

import java.lang.reflect.Array;

public class Sfumature {
	//Scrivere una funzione che prende un numero intero n come parametro e ritorna un array di sfumature di grigio 
	//in codice esadecimale (#aaaaaa, per esempio). L’array dovrebbe essere ordinato in senso ascendente (#010101, #020202, ecc), 
	//usando le lettere minuscole.public class ShadesOfGrey {
		//static String[] shadesOfGrey(int num) {
		// returns n shades of grey in an array
		//}
		//}
	//Ricorda che: il grigio è un colore composto dalla stessa quantità di rosso, verde e blu: #010101, #aeaeae, #555555. 
	//Inoltre: #000000 e #FFFFFF non sono valori accettati.Se n è negativo ritornare un array vuoto, 
	//se n è maggiore di 254, ritornare un array di 254 elementi.

	public Sfumature() {
		// TODO Auto-generated constructor stub
	}
	
	public static String[] sfumature(int n) {
		String[] sf = new String[n];
		String c ="";
		if(n<0) {
			return sf;
		}else if(n>254) {
			sf = new String[254];
		}
		return sf;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
