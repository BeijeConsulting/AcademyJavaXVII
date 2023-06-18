package it.beije.xvii.exercises.iannetta;

import java.util.Scanner;

public class TestMethods {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a new name: ");		
		String name = in.nextLine();
		in.close();
		String setter = "set" + (name.charAt(0) + "").toUpperCase() + name.substring(1).toLowerCase(); 
		System.out.println("Setter name: " + setter);
		String getter = "get" + (name.charAt(0) + "").toUpperCase() + name.substring(1).toLowerCase(); 
		System.out.println("Getter name: " + getter);	
	}
}
