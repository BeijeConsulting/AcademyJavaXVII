package it.beije.xvii.exercises.iannetta;

import java.util.Scanner;

public class SoloVocali {

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		System.out.println("Enter a sentence");
		String string = in.nextLine();
		for (String s : string.split("")) {
			if (s.toLowerCase().equals("a") ||
				s.toLowerCase().equals("e")	||
				s.toLowerCase().equals("i")	||
				s.toLowerCase().equals("o")	||
				s.toLowerCase().equals("u")) System.out.print(s);
			else System.out.print(" ");
		}
		in.close();
	}

}
