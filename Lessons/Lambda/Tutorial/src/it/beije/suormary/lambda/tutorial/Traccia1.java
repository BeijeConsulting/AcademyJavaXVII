package it.beije.suormary.lambda.tutorial;

import java.util.Scanner;

interface Square {
	Integer square(Integer number);
}

public class Traccia1 {

	public static void main(String[] args) {
		
		boolean correct = false;
		Scanner keyboard = new Scanner(System.in);
		
		while(!correct) {
		
			StringBuilder exercise = new StringBuilder();
			
			System.out.println("\n-----------------------------");
			
			exercise
			.append("\n\ninterface Square {\r\n"
					+ "	Integer square(Integer number);\r\n"
					+ "}")
			.append("\n")
			.append("\n")
			.append("public class Traccia1 {")
			.append("\n")
			.append("\tpublic static void main(String[] args) {")
			.append("\n")
			.append("\n")
			.append("\t\t// COMPLETE CODE HERE WITH LAMBDA //")
			.append("\n")
			.append("\t\tSystem.out.println(number.square(10));")
			.append("\n")
			.append("\t}")
			.append("\n}")
			.append("\n");
			System.out.println(exercise.toString());
			
			System.out.println("Quale delle seguenti opzioni è corretta? (risposta multipla)");
			System.out.println("Scrivi in questo formato: A,B,C la risposta\n");
			
			System.out.println("A) Square number = Integer n -> { n*n;};");
			System.out.println("B) Square number = (n) -> (n*n);");
			System.out.println("C) Square number = n -> { return n*n};");
			System.out.println("D) Square number = n -> { return n*n;};");
			
			System.out.println("\n");
			
			
			String input = keyboard.nextLine();
			
			input = input.trim();
			
			if(input.equals("B,D") || input.equals("D,B")) {
				System.out.println("\nRisposta corretta! :D");
				correct = true;
			}else if(input.contains("B") || input.contains("D")) {
				System.out.println("\nRisposta parzialmente corretta... :|");
			}else {
				System.out.println("\nRisposta errata :(");
			}
		}
		
		keyboard.close();
	}

}
