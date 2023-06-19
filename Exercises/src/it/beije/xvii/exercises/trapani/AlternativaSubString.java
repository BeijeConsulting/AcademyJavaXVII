package it.beije.xvii.exercises.trapani;
import java.util.Scanner;


public class AlternativaSubString {
	
	public static void main(String[] args) {
		String frase = "Supercalifragilistichespiralidoso";
		Scanner in = new Scanner(System.in);
		
		System.out.println("Inserire inizio: ");
		int inizio=in.nextInt();
		
		for(int i=inizio; i<frase.length(); i++) {
			System.out.print(frase.charAt(i));
		}

	}

}
