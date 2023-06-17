package it.beije.xvii.exercises.iannetta;

import java.util.Scanner;

public class CartaForbiceSasso {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Carta Forbice Sasso");
		System.out.print("Giocatore 1: ");
		String s1 = in.nextLine().trim();
		System.out.print("Giocatore 2: ");
		String s2 = in.nextLine().trim();
		String sequenza = s1 + s2;
		if (sequenza.equalsIgnoreCase("cartaforbice") ||
			sequenza.equalsIgnoreCase("forbicesasso") ||
			sequenza.equalsIgnoreCase("sassocarta")) System.out.println("Giocatore 2 ha vinto");
		else if (sequenza.equalsIgnoreCase("forbicecarta") ||
				sequenza.equalsIgnoreCase("sassoforbice") ||
				sequenza.equalsIgnoreCase("cartasasso")) System.out.println("Giocatore 1 ha vinto");
		else System.out.println("Input non valido");
		in.close();

	}

}
