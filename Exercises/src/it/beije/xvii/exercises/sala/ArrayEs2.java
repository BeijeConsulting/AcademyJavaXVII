package it.beije.xvii.exercises.sala;
import java.util.Scanner;

/*
 * Trovare l’indice del massimo elemento in un array (o il minimo)
 */

public class ArrayEs2 {

	public static void main(String[] args) {
		Scanner ts = new Scanner(System.in);
		int max;
		int min;
		int nvalori;
		int [] a1;
		int indice=-1;
		
		
		System.out.println("inserisci il numero di valori che vuoi verificare");
		nvalori = ts.nextInt();
		a1 = new int[nvalori];
		
		System.out.println("inserisci i valori");
		for(int i=0; i<a1.length; i++) {
			a1[i] = ts.nextInt();
		}
		
		//trovo indice del max
		max = ArrayEs2.massimo(a1);
		indice = ArrayEs2.indice(a1, max);
		
		
		System.out.println("il max è: "+max+" e si trova alla posizioni con indice: "+indice);
		
		
		//trovo indice del min
		min = ArrayEs2.minimo(a1);
		indice = ArrayEs2.indice(a1, min);
		
		System.out.println("il min è: "+min+" e si trova alla posizioni con indice: "+indice);
		
		ts.close();
	}
	
	public static int massimo(int [] a) {
		int max = a[0];
		for(int i=0; i<a.length; i++) {
			if(max<a[i]) {
				max=a[i];
			}
		}
		return max;
	}
	
	public static int minimo(int [] a) {
		int min = a[0];
		for(int i=0; i<a.length; i++) {
			if(min>a[i]) {
				min=a[i];
			}
		}
		return min;
	}
	
	public static int indice (int [] a, int valore) {
		int indice=-1;
		for(int i=0; i<a.length; i++) {
			if(a[i]==valore) {
				indice=i;
				break;
			}
		}
		return indice;
	}

}
