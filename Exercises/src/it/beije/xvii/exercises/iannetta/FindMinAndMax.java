package it.beije.xvii.exercises.iannetta;

import java.util.Arrays;
import java.util.Random;

public class FindMinAndMax {

	public static void main(String[] args) {
		Random rand = new Random();
		int[] array = new int[rand.nextInt(10) + 1];
		
		for (int i  = 0; i < array.length; i++) array[i] = rand.nextInt(10) + 1;
			
		int min = array[0];
		int max = array[0];
		int minIndex = 0;
		int maxIndex = 0;
		for (int i  = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
				maxIndex = i;
			}
			if (array[i] < min) {
				min = array[i];
				minIndex = i;
			}
		}
		System.out.println(Arrays.toString(array));
		System.out.println("Minimum value: " + min + " at index: " + minIndex);
		System.out.println("Maximum value: " + max + " at index: " + maxIndex);
	}

}
