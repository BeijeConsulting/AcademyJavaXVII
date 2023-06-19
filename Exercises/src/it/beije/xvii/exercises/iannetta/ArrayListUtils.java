package it.beije.xvii.exercises.iannetta;

import java.util.ArrayList;
import java.util.Random;

public class ArrayListUtils {

	public static boolean equals(ArrayList<Object> one, ArrayList<Object> two) {
		if (one.size() != two.size()) return false;
		for (int i = 0; i < one.size(); i++) {
			if (!one.get(i).equals(two.get(i))) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {

		Random rand = new Random();
		ArrayList <Object> one = new ArrayList<>();
		ArrayList <Object> two = new ArrayList<>();
		
		for (int i = 0; i < 2; i++) {
			one.add(rand.nextInt(2));
			two.add(rand.nextInt(2));
		}
		
		for (int i = 0; i < 2; i++) {
			one.add("aa");
			two.add("aa");
		}
		
		for (int i = 0; i < 2; i++) {
			one.add(new int[] {0,1});
			two.add(new int[] {0,1});
		}
		
		//one = two;
		System.out.println(one.toString());
		System.out.println(two.toString());
		
		System.out.println("Lists are equals: " + equals(one, two));
		System.out.println("Lists are equals: " + one.equals(two));
	}

}
