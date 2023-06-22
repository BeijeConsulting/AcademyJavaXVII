package it.beije.xvii.exercises.trapani;
import java .util.Arrays;

public class ArrayMaxEMin {

	public static void main(String[] args) {
		int[] num = {2, 67, 3, 83, 56, 8, 58, 4, 1, 74};
		int max, min;
		
		if( num[0] < num[1]) {		//per inizializzare min e max paragono i primi due valori
			min=num[0];
			max=num[1];
		} else {
			min=num[1];
			max=num[0];
		}
		
		for(int i=2; i<num.length; i++) {
			if(num[i]<min) {
				min=num[i];
			} else if (num[i]>max) {
				max = num[i];
			}
		}
		
		System.out.println("Valore max: "+ max + ", Valore min: " + min);

	}

}
