package it.beijeit.xvii.exercises.sala;
import java.util.Scanner;
/*
 * Trovare il massimo elemento in un array (o il minimo)
 */
public class ArrayEs1 {

	public static void main(String[] args) {
		Scanner ts = new Scanner(System.in);
		System.out.println("indica il numero di valori che vuoi inserire");
		int nvalori = ts.nextInt();
		int [] a1 = new int[nvalori];
		int max;
		int min;
	
		
		System.out.println("indica quali valori inserire");
		
		for(int i=0; i<a1.length; i++) {
			a1[i] = ts.nextInt();
		}
		
		//trovo il max
		max=a1[0];
		for(int i=0; i<a1.length; i++) {
			if(max<a1[i]) {
				max=a1[i];
			}
		}
		
		System.out.println("il max è: "+max);
		
		//trovo il min
		min=a1[0];
		for(int i=0; i<a1.length; i++) {
			if(min>a1[i]) {
				min=a1[i];
			}
		}
		
		System.out.println("il min è: "+min);
		
		ts.close();
	}

}
