package it.beije.xvii.exercises.iannetta;

import java.util.Scanner;

public class TestMethods {

	public static String name; 
	
	public static void setter(String s) {
		name = s;
	}
	
	public static String getter() {
		return name;
	}
	public static void main(String[] args) {
		
		System.out.println("Name: " + name);
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a new name: ");
		String string = in.nextLine();
		in.close();
		System.out.println("Setting new name");
		setter(string);
		System.out.println("Getter: " + getter());
		
		
		
		
		
		
		
	}

}
