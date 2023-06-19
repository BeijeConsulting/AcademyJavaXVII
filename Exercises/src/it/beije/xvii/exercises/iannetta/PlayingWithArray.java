package it.beije.xvii.exercises.iannetta;

import java.util.Arrays;
import java.util.Random;

public class PlayingWithArray {

	public static boolean isCrescente(int[] array) {
		for (int i  = 0; i < array.length - 1; i++) {
			if(array[i+1] < array[i]) return false;
		}
		return true;
	}
	
	public static boolean contains(int element, int[] array) {
		for (int i : array) {
			if (i == element) return true;
		}
		return false;
	}
	
	public static boolean contains(Object object, Object[] array) {
		for (Object i : array) {
			if (i.equals(object)) return true;
		}
		return false;
	}
	
	//case of same number of occurrences return first element 
	public static int mostRecurrent(int[] array) {
		int element = array[0];
		int occurrences = 1;
		for (int i = 0; i < array.length; i++) {
			int currentOccurrences = 1;
			for (int j = i; j < array.length; j++) {
				if (array[j] == array[i]) currentOccurrences++;
			}
			if (currentOccurrences > occurrences) {
				occurrences = currentOccurrences;
				element = array[i];
			}
		}
		return element;
	}
	
	public static void stampaZigZag(int[] array) {
		for (int i = 0; i < array.length / 2; i++) {
			System.out.print(array[i] + " ");
			System.out.print(array[array.length - i - 1] + " ");
		}
		if (array.length % 2 == 1) System.out.print(array[array.length / 2] + " ");
 	}
	
	public static double media(int[] array) {
		double sum = 0;
		for (int i : array)	sum += i;
		return sum / array.length;
	}
	
	public static double mediaMultipliDiTre(int[] array) {
		int sum = 0;
		int count = 0;
		for (int i : array) {
			if (i % 3 == 0) {
				sum += i;
				count++;
			}
		}
		return (count == 0) ? 0 : (1.0 * sum / count);
	}
	
	public static String[] addString(String s, String[] array) {
		String[] result = new String[array.length + 1];
		
		for (int i = 0; i < array.length; i++) result[i] = array[i];
		result[array.length] = s;
		return result;
	}
 	
	public static void main(String[] args) {
		Random rand = new Random();
		int[] array = new int[10];
		
		for (int i  = 0; i < array.length; i++) array[i] = rand.nextInt(10) + 1;
			
		//Arrays.sort(array);
		boolean isAscendingOrder = isCrescente(array);
		System.out.println(Arrays.toString(array));
		System.out.println("Array sorted in asceding order: " + isAscendingOrder);
		
		System.out.println("Most recurrent element: " + mostRecurrent(array));
		
		int elementToCheck = 5;
		System.out.println("Array contains " + elementToCheck + ": " + contains(elementToCheck, array));
		
		System.out.println("Mean of all elements: " + media(array));
		
		System.out.println("Mean of multiples of 3: " + mediaMultipliDiTre(array));
		
		System.out.print("Zig zag: ");
		stampaZigZag(array);
		
		
		String[] stringArray = {"casa", "burro", "penna", "violino"};
		System.out.println("\n\n" + Arrays.toString(stringArray));
		System.out.println(Arrays.toString(addString("aggiungi", stringArray)));
		
		Object[] objectArray = {3, "blu", true, 's', 27.2};
		System.out.println("\n" + Arrays.toString(objectArray));
		Object objectToCHeck = "blu";
		System.out.println("Array contains " + objectToCHeck + ": " + contains(objectToCHeck, objectArray));
	}

}
