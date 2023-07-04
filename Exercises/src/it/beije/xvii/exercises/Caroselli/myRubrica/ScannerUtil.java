package it.beije.xvii.exercises.Caroselli.myRubrica;

import java.util.Scanner;


public class ScannerUtil {
	
	private static final String defaultInvalidMessage = "Input non valido. Inserisci di nuovo";
	
	public static int readIntValue(String message) {
		return readIntValue(message, defaultInvalidMessage);
	}

	public static int readIntValue(String message, String invalidInputMessage) {
		System.out.println(message);
		Scanner sc = RubricaManager.scanner;
		while (true) {
			try {
				if (sc.hasNextInt()) {
					return sc.nextInt();
				} else {
					System.out.println(invalidInputMessage);
				}
			} catch (Exception e) {
				System.out.println(invalidInputMessage);
			}
			sc.nextLine();
		}

	}

	public static String readStringValue(String message) {
		return readStringValue(message, defaultInvalidMessage);
	}

	public static String readStringValue(String message, String invalidInputMessage) {
		System.out.println(message);
		Scanner sc = RubricaManager.scanner;
		while (true) {
			try {
				if (sc.hasNext()) {
                    return sc.nextLine();
				} else {
					System.out.println(invalidInputMessage);
				}
			} catch (Exception e) {
				System.out.println(invalidInputMessage);
			}
			sc.nextLine();
		}
	}

}
