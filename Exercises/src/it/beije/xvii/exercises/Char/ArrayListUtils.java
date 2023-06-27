package it.beije.xvii.exercises.Char;
import java.util.ArrayList;
import java.util.List;

public class ArrayListUtils {

	public static void main(String[] args) {
		ArrayList<Integer> arr1 = new ArrayList<Integer>();
		arr1.add(2);
		arr1.add(3);
		arr1.add(4);
		arr1.add(12);
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		arr2.add(2);
		arr2.add(3);
		arr2.add(4);
		arr2.add(12);
		System.out.println(arr1.equals(arr2));
		System.out.println(equals(arr1,arr2));

	}
	public static boolean equals(ArrayList arr1, ArrayList arr2) {
		if(arr1.get(0) != arr2.get(0)) {
			return false;
		}
       StringBuilder string1 = new StringBuilder();
       StringBuilder string2 = new StringBuilder();
		for(int i = 0; i< arr1.size(); i++) {
			string1.append(arr1.get(i));
		}
		for(int i = 0; i< arr2.size(); i++) {
			string2.append(arr2.get(i));
	}

		if(string1.toString().equals(string2.toString())) {
			return true;
		}
		return false;
	}

}
