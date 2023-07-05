package it.beije.suormary.rubrica.caroselli;

import java.util.Scanner;

import it.beije.suormary.rubrica.caroselli.HBM.RubricaManagerHBM;

public class ScannerUtil {

	private static final String defaultInvalidMessage = "Input non valido. Inserisci di nuovo";

	private ScannerUtil() {
	}

	public static int readIntValue(String message) {
		return readIntValue(message, defaultInvalidMessage);
	}

	public static int readIntValue(String message, String invalidInputMessage) {
		System.out.println(message);
		Scanner sc = RubricaManagerHBM.scanner;
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
		Scanner sc = RubricaManagerHBM.scanner;
		while (true) {
//			try {
//				if (sc.hasNext()) {
//                    return sc.nextLine();
//				} else {
//					System.out.println(invalidInputMessage);
//				}
//			} catch (Exception e) {
//				System.out.println(invalidInputMessage);
//			}
//			sc.nextLine();
//		}
			try {
	            if (sc.hasNextLine()) {
	                String input = sc.nextLine();
	                if (input.isEmpty()) {
	                    continue; // Se l'input è vuoto, continua con il ciclo
	                } else {
	                    return input;
	                }
	            } else {
	                System.out.println(invalidInputMessage);
	            }
	        } catch (Exception e) {
	            System.out.println(invalidInputMessage);
	        }
	    }
	}

}
