package it.beijeit.xvii.exercises.sala;
import java.util.Scanner;
/*
 * Scrivere il metodo: “public int mostRecurrent(int [] array)” , che trova l’elemento più ricorrente 
 * in un array. Il metodo restituisce l’elemento trovato.
 */
public class ArrayEs5 {

	public static void main(String[] args) {
		Scanner ts = new Scanner(System.in);
		int nvalori;
		int [] a;
		ArrayEs5 o = new ArrayEs5();
		int valorepiufrequente;
		
		System.out.println("inserisci il numero di valori");
		nvalori = ts.nextInt();
		a = new int[nvalori];
		
		System.out.println("inserisci i valori");
		for(int i=0; i<a.length; i++) {
			a[i]=ts.nextInt();
		}
		
		valorepiufrequente = o.mostRecurrent(a);
		if(valorepiufrequente==0) {
			System.out.println("ciascun valore è presente una sola volta");
		} else {
			System.out.println("il valore più frequente è: "+o.mostRecurrent(a));
		}
		
		ts.close();
	}
	
	public int mostRecurrent(int [] array) {
		//creo array dove salvo le occorrenze, metto a array.lenght la lunghezza perchè se ogni elemento
		//occorre una volta, quella sarà la lunghezza massima possibile
		int [] occorrenze = new int[array.length]; //tutti i suoi valori sono inizializzati a zero, per questo
		//l'istruzione alla riga 36 funziona da contatore
		
		for(int i=0; i<occorrenze.length; i++) {
			for(int j=0; j<array.length; j++) {
				//di fatto se un valore è presente più volte, in corrispondenza di tutte le sue occorrenze
				//avrò il totale delle volte che occorre
				if(array[i]==array[j]) {
					occorrenze[i]=(occorrenze[i]+1);
				}
			}
		}
		
		//trovo l'occorrenza più alta
		int maxoccorrenza=0;
		boolean trovo=false;
		
		//verifico se tutti i valori sono presenti una sola volta
		for(int i=0; i<occorrenze.length && !trovo; i++) {
			if(occorrenze[i]!=1) {
				trovo=true;
			}
			maxoccorrenza=0;
		}
		
		//verifico se qualche valore è presente maggiormente
		if(trovo) {
			for(int i=0; i<occorrenze.length; i++) {
				if(maxoccorrenza<occorrenze[i]) {
					maxoccorrenza=occorrenze[i];
				} 
			}
		}
		
		
		return maxoccorrenza;
	}

}
