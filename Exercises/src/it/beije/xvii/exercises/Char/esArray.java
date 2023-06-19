package it.beije.xvii.exercises.Char;

import java.util.Arrays;

public class esArray {

	public static void main(String[] args) {
		Integer[] arrayNum = {3,7,1,6};
		int[] arrayNum2 = {6,5,3,5,9,2,9,7,5,3};
		String[] arrayString = {"cane","gatto"};
		
//		System.out.println(maxIndiceElemento(arrayNum));
//		System.out.println(contains(Integer.valueOf(3),arrayNum));
//		System.out.println(isCrescente(arrayNum2));
//		System.out.println(MediaMultipliDiTre(arrayNum2));
//		ZigZag(arrayNum2);
		addString("leone",arrayString);


	}
	public static int maxElemento(int[] array) {
		int max = array[0];
		for(int i = 1; i< array.length; i++) {
			if(array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
	public static int maxIndiceElemento(int[] array) {
		int max = array[0];
		int indice = 0;
		for(int i = 1; i< array.length; i++) {
			if(array[i] > max) {
				indice = i;
			}
		}
		return indice;
	}
//	public static boolean contains(int e, int[] array) {
//		boolean bool = false;
//
//		for(int i = 0; i< array.length; i++) {
//			if(array[i] == e) {
//				bool = true;
//			}
//		}
//		return bool;
//	}
	public static boolean contains(Object e, Object[] array) {
		boolean bool = false;

		for(int i = 0; i< array.length; i++) {
			if(array[i] == e) {
				bool = true;
			}
		}
		return bool;
	}
	public static boolean isCrescente(int [] array) {
		int[] array2 = new int[array.length];
		for(int i = 0; i<array.length; i++) {
			array2[i] = array[i];
		}
		for(int i = 0; i< array.length; i ++) {
			System.out.println(array[i]);
		}
		Arrays.sort(array2);
		if(Arrays.equals(array2, array)) 
			return true;
		
		else 
			return false;
	}
	 public static double MediaMultipliDiTre(int [] array) {
		 int somma = 0;
		 for(int i = 0; i< array.length; i++) {
			 if(array[i] % 3 == 0) {
				 somma += array[i];
			 }
		 }
		 double media = somma / array.length;
		 return media;
	
	 }
	 public static double Media(int [] array) {
		 int somma = 0;
		 for(int i = 0; i< array.length; i++) {
				 somma += array[i];
		 }
		 double media = somma / array.length;
		 return media;
	
	 }
	 public static void ZigZag(int[] array) {
		 for(int i =0; i< 5; i++) {
			 System.out.println(array[i]);
			 System.out.println(array[array.length - 1 - i]);
		 }
	 }
	  public static String [] addString(String s, String[] a) {
		  String[] nuovoArray = new String[a.length + 1];
		  for(int i = 0; i< nuovoArray.length; i++) {
			  if(i == nuovoArray.length - 1) 
				nuovoArray[i] = s;
			  
			  else 
				nuovoArray[i] = a[i];  
			  	 
		  }
		  for(int i = 0; i< nuovoArray.length; i++) {
			  System.out.println(nuovoArray[i]);
		  }
		  return nuovoArray;
	  }

}
