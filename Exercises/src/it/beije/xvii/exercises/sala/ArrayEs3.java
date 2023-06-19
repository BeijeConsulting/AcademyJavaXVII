package it.beije.xvii.exercises.sala;
import java.util.Scanner;
/*
 * scrivere un metodo “boolean contains(int e, int[] array)” che restituisca true se l’elemento e è 
 * presente nell’array, false altrimenti.
 * Ripetere l’esercizio con “boolean contains(Object e, Object[] array)”, quali differenze ci sono?
 */
public class ArrayEs3 {

	public static void main(String[] args) {
		Scanner ts = new Scanner(System.in);
		int nvalori;
		int[] a;
		boolean presente;
		int verifica;
		
		System.out.println("inserisci il numero di valori");
		nvalori = ts.nextInt();
		a = new int[nvalori];
		
		System.out.println("inserisci i valori");
		for(int i=0; i<a.length; i++) {
			a[i]=ts.nextInt();
		}
		
		System.out.println("inserisci un nuovo valore per controllare se presente");
		verifica = ts.nextInt();
		presente = ArrayEs3.contains(verifica, a);
		System.out.println("il valore è presente? "+presente);
		
		//passando un oggetto da controllare serve fare conversione a Integer
		System.out.println("inserisci un altro valore");
		verifica = ts.nextInt();
		Integer v = Integer.valueOf(verifica);
		System.out.println("il valore è presente? "+ArrayEs3.contains(v, a));
		
		ts.close();
	}
	
	public static boolean contains(int e, int[] array) {
		for(int i=0; i<array.length; i++) {
			if(array[i]==e) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean contains(Object e, int[] array) {
        Integer intero;
		for(int i=0; i<array.length; i++) {
			intero = Integer.valueOf(array[i]);
			if(intero.equals(e)) {
				return true;
			}
		}
		return false;
	}

}
