package it.beije.xvii.exercises.trapani;

import java.time.*;
import java.time.format.*;
import java.util.Scanner;

public class DataOutput {

	public static void main(String[] args) {
		String str = "";
		Scanner in = new Scanner(System.in);
		
		System.out.println("Inserisci data DD/MM/YYYY: ");
		str =in.nextLine();
		
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate date = LocalDate.parse(str,f);				// formattazione stringa in ingresso come data
		
		System.out.print(date.getMonth());
		
	

	}

}
