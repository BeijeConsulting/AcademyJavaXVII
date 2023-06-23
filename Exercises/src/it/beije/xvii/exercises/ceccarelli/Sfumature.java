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
		if(n<0) {
			return sf;
		}else if(n>254) {
			sf = new String[254];
		}
		
		
		for(int i=0;i<n;i++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			int decina = i/16;
			int unità = i-(decina*16);
			for(int v =0;v<3;v++) {
				if(decina>=10) {
					char c = (char)(decina+55);
					//System.out.println("carattere" + c);
					sb.append(c);
				}else {
					sb.append(decina);
				}
				if(unità>=10) {
					char c = (char)(unità+55);
					sb.append(c);
				}else {
					sb.append(unità);
				}
			}
			sf[i] = sb.toString();
		}
		
		return sf;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 254;
		Sfumature sf = new Sfumature();
		for(String s:sf.sfumature(n) ) {
			System.out.println(s);
		}
		

	}

}
