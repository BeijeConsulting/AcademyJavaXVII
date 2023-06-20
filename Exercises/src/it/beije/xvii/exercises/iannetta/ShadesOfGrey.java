package it.beije.xvii.exercises.iannetta;

import java.util.Arrays;

public class ShadesOfGrey {
	
	private static String decToHex(int n) {
		int rem;
		int number = n;
		String result = "";
		char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		while (number > 0) {
			rem = number % 16;
			result = digit[rem] + result;
			number = number / 16;
		}
		return (result.length() == 1) ? "0" + result : result;
	}

	private static String[] shades(int n) throws Exception {
		if (n < 0) return new String[] {};
		if (n == 0) throw new Exception("That's white");
		if (n == 255) throw new Exception("That's black");
		if (n > 255) n = 254;
		
		String[] result = new String[n];
		for (int i = 0; i < n; i++) {
			String temp = decToHex(i+1);
			temp += temp + temp; 
			result[i] = "#" + temp;
		}
		return result;
 	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(Arrays.toString(shades(15)));
	}

}
