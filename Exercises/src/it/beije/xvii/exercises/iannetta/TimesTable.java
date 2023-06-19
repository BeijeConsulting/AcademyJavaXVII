package it.beije.xvii.exercises.iannetta;

import java.util.Scanner;

public class TimesTable {

	public static void main(String[] args) {
		System.out.print("Enter a number: ");
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		System.out.println("\nTimes table");
		for (int i = 0; i <= 10; i++) {
			int result = n * i;
			System.out.println(n + " * " + i + " = " + result);
		}
		in.close();
	}

}
