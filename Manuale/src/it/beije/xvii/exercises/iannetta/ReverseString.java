package it.beije.xvii.exercises.iannetta;

import java.util.Scanner;

public class ReverseString {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a string: ");
		String s = in.nextLine();
		String reverse = "";
		in.close();
		for (int i = s.length() - 1; i >= 0; i--) reverse += s.charAt(i);
		System.out.println(reverse);

	}

}
