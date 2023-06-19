package it.beije.xvii.exercises.iannetta;

import java.util.Random;

public class StampaMaiuscole {

	public static String createRanodmString() {
		String result = "";
		Random rand = new Random();
		int upperbound = 57;
		int shift = 65;
		int length = rand.nextInt(15) + 1;
		for (int i = 0; i < length; i++) {
			result += (char)(rand.nextInt(upperbound) + shift);
		}
		return result;
	}
	
	public static int contaLettera(char c, String str) {
		int oldLength = str.length();
		String letter = ("" + c).toLowerCase();
		int newLength = str.toLowerCase().replace(letter, "").length();
		return oldLength - newLength;
	}
	
	public static void main(String[] args) {
		String [] array = new String[10];
		char letterToFind = 'v';
		
		for (int i = 0; i < array.length; i++) {
			array[i] = createRanodmString();
		}
		
		for (String s : array) {
			System.out.print(s);
			int count = contaLettera(letterToFind, s);
			System.out.println("   " + count + "" + letterToFind + "  ");
		}
		
		System.out.println("\nStarting with capital letter: ");
		for (String s : array) {
			char firstLetter = s.charAt(0);
			if (firstLetter >= 'A' && firstLetter <= 'Z')	System.out.println(s);
		}

	}

}
