package it.beije.xvii.exercises.Caroselli.stringUtils;

import java.util.ArrayList;

public class StringUtils {
	public static int indexOf(String s, char c) {
		// -1 per indicare che il carattere non è stato trovato
		int indexOfChar = -1;
		s = s.toLowerCase();
		c = Character.toLowerCase(c);

		for (int i = 0; i < s.length(); i++) {
			if (c == s.charAt(i)) {
				indexOfChar = i;
				break;
			}
		}

		if (indexOfChar == -1) {
			System.out.println("Il carattere '" + c + "' non è stato trovato nella stringa '" + s + "'");
		} else {
			System.out.println("L'indice del carattere '" + c + "' nella stringa '" + s + "' è " + indexOfChar);
		}

		return indexOfChar;
	}

	public static int indexOfFromIndexEx(String s, char c, int fromIndex) {

		int index = -1;

		if (fromIndex >= s.length() || fromIndex < 0) {
			System.out.println("L'indice da cui vuoi partire e' fuori dai limiti della stringa");
		}

		for (int i = fromIndex; i < s.length(); i++) {
			// mi trovo il vero indice del carattere c nella stringa s
			index = indexOf(s, c);
			// devo controllare che l'indice da cui parto
			// cioe' fromIndex non sia maggiore dell'indice restituito
			if (fromIndex > index) {
				// System.out.println("L'indice del carattere che vuoi cercare non e' contenuto
				// nella stringa dall'indice : " + fromIndex);
				return -1;
			}

		}

		return index;
	}

	public static int indexOfStrFromStr(String str, String s) {

		int strLength = str.length();
		int sLength = s.length();

		// vedo se s sta in str
		for (int i = 0; i <= strLength - sLength; i++) {
			boolean found = true;

			// per confrontare i caratteri della stringa str a partire dall'indice i con i
			// caratteri di s
			for (int j = 0; j < sLength; j++) {
				if (str.charAt(i + j) != s.charAt(j)) {
					found = false;
					break;
				}
			}

			if (found) {
				return i; // Restituisce l'indice se la sottostringa viene trovata
			}
		}

		return -1; // La sottostringa non è stata trovata

	}

	public static int indexOfStrFromIndex(String s, String str, int fromIndex) {

		for (int i = fromIndex; i < s.length(); i++) {
			boolean found = true;

			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(i + j) != str.charAt(j)) {
					found = false;
					break;
				}
			}
			if (found) {
				return i;
			}
		}
		return -1;

	}

	private static boolean equalsSubstring(String s1, int startIndex1, String s2, int startIndex2, int length) {
		for (int i = 0; i < length; i++) {
			if (s1.charAt(startIndex1 + i) != s2.charAt(startIndex2 + i)) {
				return false;
			}
		}
		return true;
	}

	public static String substring(String s, int beginIndex) {

		StringBuilder result = new StringBuilder();

		if (beginIndex < 0 || beginIndex >= s.length()) {
			throw new IndexOutOfBoundsException("L'indice di inizio è fuori dai limiti della stringa");
		}

		char c = s.charAt(beginIndex);

		int indexFromBeginIndex = indexOf(s, c);

		if (indexFromBeginIndex != -1) {
			for (int i = indexFromBeginIndex; i < s.length(); i++) {
				result.append(s.charAt(i));
			}

		}
		return result.toString();
	}

	public static String substring(String s, int beginIndex, int endIndex) {

		if (beginIndex < 0 || beginIndex > endIndex || endIndex > s.length()) {
			throw new IndexOutOfBoundsException("Gli indici di inizio o fine sono fuori dai limiti della stringa");
		}
		String result = "";
		for (int i = beginIndex; i <= endIndex; i++) {
			result += s.charAt(i);
		}

		return result;
	}

	public static String toLowerCase(String s) {

		// i caratteri in minuscolo hanno valori ASCII compresi tra 97 e 122,
		// mentre i caratteri in maiuscolo hanno valori ASCII compresi tra 65 e 90.

		StringBuilder result = new StringBuilder();

		if (s == null || s.isEmpty()) {
			System.out.println("La stringa da convertire e' vuota");
		}

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'A' && c <= 'Z') {
				// in minuscolo
				c = (char) (c + 32);
			}

			result.append(c);
		}

		return result.toString();

	}

	public static String toUpperCase(String s) {
		if (s == null || s.isEmpty()) {
			System.out.println("La stringa da convertire e' vuota");
		}
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'a' && c <= 'z') {
				// in maiuscolo
				c = (char) (c - 32);
			}

			result.append(c);
		}
		return result.toString();
	}

	public static boolean equals(String s1, String s2) {
		if (s1 == s2) {
			return true;
		}

		if (s1 == null || s2 == null) {
			return false;
		}

		if (s1.length() != s2.length()) {
			return false;
		}

		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				return false;
			}
		}

		return true;
	}

	public static boolean equals1(ArrayList<?> one, ArrayList<?> two) {
		if (one == two) {
			return true;
		}

		if (one == null || two == null) {
			return false;
		}

		if (one.size() != two.size()) {
			return false;
		}

		for (int i = 0; i < one.size(); i++) {
			Object elementOne = one.get(i);
			Object elementTwo = two.get(i);

			if (elementOne == null) {
				if (elementTwo != null) {
					return false;
				}
			} else {
				if (!elementOne.equals(elementTwo)) {
					return false;
				}
			}
		}

		return true;
	}

	public static boolean equalsIgnoreCase(String s1, String s2) {
		s1 = toLowerCase(s1);
		s2 = toLowerCase(s2);

		return equals(s1, s2);
	}

	public static boolean contains(String s, String str) {

		boolean isInTheString = false;

		int index = indexOfStrFromStr(s, str);
		if (index != -1) {
			isInTheString = true;
		}
		return isInTheString;

	}

	public static boolean startsWith(String s, String prefix) {
		if (s.length() < prefix.length()) {
			return false;
		}

		for (int i = 0; i < prefix.length(); i++) {
			if (s.charAt(i) != prefix.charAt(i)) {
				return false;
			}
		}

		return true;
	}

	public static boolean endsWith(String s, String suffix) {
		if (s.length() < suffix.length()) {
			return false;
		}

		int startIndex = s.length() - suffix.length();
		for (int i = 0; i < suffix.length(); i++) {
			if (s.charAt(startIndex + i) != suffix.charAt(i)) {
				return false;
			}
		}

		return true;
	}

	public static String replace(String s, char oldChar, char newChar) {

		int index = indexOf(s, oldChar);

		char[] charArray = s.toCharArray();

		if (index != -1) {

			for (int i = 0; i < charArray.length; i++) {
				if (charArray[i] == oldChar) {
					charArray[i] = newChar;
				}
			}
		}
		return new String(charArray);
	}

	public static String replace(String s, String oldChar, String newChar) {

		boolean contains = contains(s, oldChar);
		StringBuilder result = new StringBuilder();
		int startIndex = 0;
		int index = indexOf(s, oldChar.charAt(0));

		if (contains) {

			while (index != -1) {
				result.append(s, startIndex, index);
				result.append(newChar);
				startIndex = index + oldChar.length();
				index = s.indexOf(oldChar, startIndex);
			}

			result.append(s.substring(startIndex));

		}
		return result.toString();
	}

	public static String trim(String s) {

		int startIndex = 0;
		int endIndex = s.length() - 1;

		// trovo l'indice del primo carattere non bianco
		while (startIndex <= endIndex && Character.isWhitespace(s.charAt(startIndex))) {
			startIndex++;
		}

		// trovo l'indice dell'ultimo carattere non bianco
		while (endIndex >= startIndex && Character.isWhitespace(s.charAt(endIndex))) {
			endIndex--;
		}

		if (startIndex > endIndex) {
			// se la stringa e' vuota
			return "";
		}

		// faccio la substring
//            return s.substring(startIndex, endIndex + 1);
		return substring(s, startIndex, endIndex);
	}

}
