package it.beije.xvii.exercises.iannetta;

import java.util.Scanner;

public class Concatena {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter 3 strings: ");
		String s = in.nextLine();
		s += "*" + in.nextLine();
		s += "*" + in.nextLine();
		System.out.println(s);
		in.close();
		
		
		/*
		int n = 3; 
		Scanner in = new Scanner(System.in);
		System.out.println("Enter "+ n +" strings: ");
		String s = "";
		for (int i = 0; i < n; i++) s += "*" + in.nextLine();
		System.out.println(s.substring(1));
		in.close();
		*/
	}

}
