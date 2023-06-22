package it.beije.xvii.exercises.trapani;

import java.util.Arrays;

public class RicorrenzaNumArray {
	public static int mostRecurrent(int[] array) {
		int n = 0;
		int temp = 0;
		int max = 0;
		
		for (int i=0; i<array.length; i++) {
			for(int j=0; j<array.length; j++) {
				if(array[i]==array[j]) {
					n++;
				}
			}
			if (n>temp) {
				temp = n;
				max = array[i];
			}
			n=0;
		}
		
		
		return max;	
	}

	public static void main(String[] args) {
		int[] num = {1,3,2,4,3,3,1,1,2,1,4,3,3};
		
		int nmax = mostRecurrent(num);
		System.out.println("Il numero con ricorrenza maggiore e': " + nmax);
		
	}

}
