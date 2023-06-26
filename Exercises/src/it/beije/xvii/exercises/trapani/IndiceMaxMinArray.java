package it.beije.xvii.exercises.trapani;

public class IndiceMaxMinArray {

	public static void main(String[] args) {
		int[] num = {2, 67, 3, 83, 56, 8, 58, 4, 1, 74};
		int max, min;
		int tempMax, tempMin;
		
		if( num[0] < num[1]) {		//per inizializzare min e max paragono i primi due valori
			min=num[0];
			max=num[1];
			tempMin=0;
			tempMax=1;
		} else {
			min=num[1];
			max=num[0];
			tempMin=1;
			tempMax=0;
		}
		
		for(int i=2; i<num.length; i++) {
			if(num[i]<min) {
				min=num[i];
				tempMin=i;
			} else if (num[i]>max) {
				max = num[i];
				tempMax=i;
			}
		}
		
		System.out.println("Indice valore max: "+ tempMax + ", Indice valore min: " + tempMin);

	}

}
