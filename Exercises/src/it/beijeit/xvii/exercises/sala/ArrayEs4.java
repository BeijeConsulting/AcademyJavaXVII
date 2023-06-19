package it.beijeit.xvii.exercises.sala;
import java.util.Scanner;
/*
 * Verificare la sequenza crescente di un array. Il metodo “boolean isCrescente(int [] array)” 
 * restituisce true se tutti gli elementi dell’array passato sono in ordine crescente, false altrimenti.
 */
public class ArrayEs4 {

	public static void main(String[] args) {
		Scanner ts = new Scanner(System.in);
		int nvalori;
		int [] a;
		ArrayEs4 o = new ArrayEs4();
		
		System.out.println("inserisci un numero di valori");
		nvalori=ts.nextInt();
		a = new int[nvalori];
		
		System.out.println("inserisci i valori");
		for(int i=0; i<a.length; i++) {
			a[i] = ts.nextInt();
		}
		
		System.out.println("l'array è ordinato? "+o.isCrescente(a));
		
		ts.close();
	}
	
	public boolean isCrescente(int [] array) {
		for(int i=1; i<array.length; i++) {
			if(array[i-1]>array[i]) {
				return false;
			} 
	
		}
		return true;
	}

}
