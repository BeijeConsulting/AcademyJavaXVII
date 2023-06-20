package it.beije.xvii.exercises.ulloa;

/*
 * Trovare il massimo elemento in un array (o il minimo) 
 * Trovare l’indice del massimo elemento in un array (o il minimo)
 * 
 */

public class ArrayMaxMin {
	public static void main(String[] args) {
		int [] numbers = {0,0,1,1,2,3,4,-1,-7,10};
		
		int max = numbers[0];
		int min = numbers[0];
		int indexMax = 0;
		int indexMin = 0;
		
		//parto dal primo, avendo già inizzializato le mie variabili
		//con il primo elemento dell'array
		for(int i=1; i<numbers.length; i++) {
			if(numbers[i]>max) {
				max = numbers[i];
				indexMax = i;
			}
			
			if(numbers[i]<min) {
				min = numbers[i];
				indexMin = i;
			}
		}
		
		System.out.println("Il massimo elemento nell'array e': " + max);
		System.out.println("L'indice del massimo elemento nell'array e': " + indexMax);
		System.out.println("Il minimo elemento nell'array e': " + min);
		System.out.println("L'indice del minimo elemento nell'array e': " + indexMin);
	}
}
