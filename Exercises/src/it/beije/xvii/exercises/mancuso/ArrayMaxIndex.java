package it.beije.xvii.exercises.mancuso;

// Trovare l’indice del massimo elemento in un array (o il minimo)

public class ArrayMaxIndex {

	public static void main(String[] args) {
		int [] numeri = {7,3,5,2,9,12,4};
		
		int max = numeri[0];
		int min = numeri[0];
		
		int idxMax = 0;
		int idxMin = 0;
		
		for(int i=0;i<numeri.length; i++) {
			if(numeri[i]>max) {
				max = numeri[i];
				idxMax = i;
			}
			if(numeri[i]<min) {
				min = numeri[i];
				idxMin = i;
			}
		}
		
		System.out.println("Indice numero massimo: " + idxMax);
		System.out.println("Indice numero minimo: " + idxMin);

	}

}
