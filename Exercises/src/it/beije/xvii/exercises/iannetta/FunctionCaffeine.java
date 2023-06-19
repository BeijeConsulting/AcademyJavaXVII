package it.beije.xvii.exercises.iannetta;

import java.util.Scanner;

public class FunctionCaffeine {

	private static String caffeine(int n) {
		String result;
		if (n % 3 == 0) {
			if (n % 4 == 0) result = "Coffee";
			else result = "Java";
			result += "Script";
		}
		else result = "match_missed!";
		return result;
	}
	public static void main(String[] args) {
		
		Scanner in  = new Scanner (System.in);
		System.out.print("Enter an integer number: ");
		int n= in.nextInt();
		in.close();
		System.out.println(caffeine(n));
	}

}
