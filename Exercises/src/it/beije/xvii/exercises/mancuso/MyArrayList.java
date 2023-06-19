package it.beije.xvii.exercises.mancuso;

import java.util.ArrayList;

public class MyArrayList {

	public static boolean equals(ArrayList one, ArrayList two) {
		if(one.size() != two.size()) {
			return false;
		}else {
			for(int i=0; i<one.size(); i++) {
				if(one.get(i) != two.get(i)) {
					return false;
				}
			}
			return true;
		}
		
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> one = new ArrayList<>();
		one.add(12);
		one.add(4);
		one.add(3);
		one.add(9);
		
		ArrayList<Integer> two = new ArrayList<>();
		two.add(12);
		two.add(3);
		two.add(4);
		two.add(9);
		
		System.out.println(equals(one, two));
	}

}
