package it.beije.xvii.exercises.mancuso;

/*
 * Metodi da implementare solo con length e charAt
 */

public class StringUtils {

	public static int indexOf(String s, char c) {
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) == c) {
				return i;
			}
		}
		return -1;
	}
	
	public static int indexOf(String s, char c, int fromIndex) {
		if(fromIndex >= 0 && fromIndex<s.length()) {
			for(;fromIndex<s.length(); fromIndex++) {
				if(s.charAt(fromIndex) == c) {
					return fromIndex;
				}
			}
			return -1;
		}else{
			throw new IllegalArgumentException("L'indice di partenza non può eccedere la grandezza della stringa.");
		}
	}
		
	// s è la stringa intera, str quella da cercare
	public static int indexOf(String s, String str) {
		int j=0;
		int index = -1;
		char lastMatch = '\u0000';
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) == str.charAt(j)) {
				if(j==0) {
					index = i;
					lastMatch = s.charAt(i);
				}
				j++;
				if(j>=str.length()) {
					return index;
				}
			}else {
				if(s.charAt(i) == lastMatch) {
					continue;
				}else {
					j=0;
					index = -1;
				}
			}
		}
		return -1;
	}
	
	public static int indexOf(String s, String str, int fromIndex) {
		if(fromIndex >= 0 && fromIndex<s.length()) {
			int i = fromIndex;
			int j=0;
			int index = -1;
			char lastMatch = '\u0000';
			for(; i<s.length(); i++) {
				if(s.charAt(i) == str.charAt(j)) {
					if(j==0) {
						index = i;
						lastMatch = s.charAt(i);
					}
					j++;
					if(j>=str.length()) {
						return index;
					}
				}else {
					if(s.charAt(i) == lastMatch) {
						continue;
					}else {
						j=0;
						index = -1;
					}
				}
			}
			return -1;
		}else{
			throw new IllegalArgumentException("L'indice di partenza non può eccedere la grandezza della stringa.");
		}
	}
	
	public static int indexOf(String s, String str, int fromIndex, int toIndex) {
		if(fromIndex >= 0 && fromIndex<s.length()) {
			int i = fromIndex;
			int j=0;
			int index = -1;
			char lastMatch = '\u0000';
			for(; i<toIndex; i++) {
				if(s.charAt(i) == str.charAt(j)) {
					if(j==0) {
						index = i;
						lastMatch = s.charAt(i);
					}
					j++;
					if(j>=str.length()) {
						return index;
					}
				}else {
					if(s.charAt(i) == lastMatch) {
						continue;
					}else {
						j=0;
						index = -1;
					}
				}
			}
			return -1;
		}else{
			throw new IllegalArgumentException("L'indice di partenza non è accettabile.");
		}
	}
	
	public static String substring(String s, int beginIndex) {
		String myString = "";
		if(beginIndex >= 0 && beginIndex<s.length()) {
			for(int i=beginIndex; i<s.length(); i++) {
				myString += s.charAt(i);
			}
			return myString;
		}else {
			throw new IllegalArgumentException("L'indice di partenza non è accettabile.");
		}
	}
	
	public static String substring(String s, int beginIndex, int endIndex) {
		String myString = "";
		if(beginIndex >= 0 && beginIndex<s.length()) {
			if(endIndex<=s.length()) {
				for(int i=beginIndex; i<endIndex; i++) {
					myString += s.charAt(i);
				}
				return myString;
			}else {
				throw new IllegalArgumentException("L'indice di partenza non è accettabile.");
			}
		}else {
			throw new IllegalArgumentException("L'indice di partenza non è accettabile.");
		}
	}
	
	public static String toLowerCase(String s) {
		String myString = "";
		for(int i=0; i<s.length(); i++) {
			int asciiCode = (int)s.charAt(i);
			if(asciiCode > 64 && asciiCode < 91) {
				asciiCode += 32;
				myString += (char)asciiCode;
			}else {
				myString += s.charAt(i);
			}
		}
		return myString;	
	}
	
	public static String toUpperCase(String s) {
		String myString = "";
		for(int i=0; i<s.length(); i++) {
			int asciiCode = (int)s.charAt(i);
			if(asciiCode > 96 && asciiCode < 123) {
				asciiCode -= 32;
				myString += (char)asciiCode;
			}else {
				myString += s.charAt(i);
			}
		}
		return myString;	
	}
	
	public static boolean equals(String s1, String s2) {
		if(s1.length() == s2.length()) {
			for(int i=0; i<s1.length(); i++) {
				if(s1.charAt(i) == s2.charAt(i)) {
					continue;
				}else {
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean equalsIgnoreCase(String s1, String s2) {
		if(s1.length() == s2.length()) {
			s1 = s1.toLowerCase();
			s2 = s2.toLowerCase();
			for(int i=0; i<s1.length(); i++) {
				if(s1.charAt(i) == s2.charAt(i)) {
					continue;
				}else {
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean contains(String s, String str) {
		if(indexOf(s,str) == -1) {
			return false;
		}else {
			return true;
		}
	}
	
	public static boolean startsWith(String s, String prefix) {
		if(prefix.length() < s.length()) {
			int endIndex = prefix.length();
			if(indexOf(s, prefix, 0, endIndex) != -1) {
				return true;
			}else {
				return false;
			}
		}else {
			throw new IllegalArgumentException("Il prefisso non può essere più lungo della stringa.");
		}
	}
	
	public static boolean endsWith(String s, String suffix) {
		if(suffix.length() < s.length()) {
			int startIndex = s.length() - suffix.length();
			int endIndex = s.length();
			if(indexOf(s, suffix, startIndex, endIndex) != -1) {
				return true;
			}else {
				return false;
			}
		}else {
			throw new IllegalArgumentException("Il prefisso non può essere più lungo della stringa.");
		}
	}
	
	public static String replace(String s, char oldChar, char newChar) {
		
		String myString = "";
		
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) == oldChar) {
				myString += newChar;
			}else {
				myString += s.charAt(i);
			}
		}
		
		return myString;
	}
	
	public static String replace(String s, String oldChar, String newChar) {
		if(oldChar.length()<s.length()) {
			String myString = "";
			for(int i=0; i<s.length(); i++) {
				if(s.charAt(i) == oldChar.charAt(0)) {
					int indexStart = indexOf(s, oldChar, 0);
					if(indexStart != -1) {
						for(int j=0; j<newChar.length(); j++) {
							myString += newChar.charAt(j);
						}
						i = indexStart + oldChar.length() - 1;
					}else {
						myString += s.charAt(i);
					}
				}else {
					myString += s.charAt(i);
				}
			}
			return myString;
		}else {
			throw new IllegalArgumentException("La stringa da sostituire non può essere più lunga di quella base.");
		}
	}
	
	public static String trim(String s) {
		String myString = s;
		while(startsWith(myString, "\t") || startsWith(myString," ")) {
			myString = substring(myString, 1);
		}
		while(endsWith(myString, "\t") || endsWith(myString," ")) {
			myString = substring(myString, 0, myString.length()-1);
		}
		return myString;
	}
	
	// Usato main x testing
	public static void main(String[] args) {
		//String s = "ABacd123ZKxY";
		//int index = 5;
		
		//System.out.println(substring(s,index));
		//System.out.println(toUpperCase(s));
		
		/*String s1 = "Hello";
		String s2 = "hello";
		
		System.out.println(equals(s1, s2));
		System.out.println(equalsIgnoreCase(s1, s2));
		*/
		
		/*String str = "Hello";
		String old = "el";
		String news = "boop";
		
		System.out.println(replace(str, old, news));
		*/
		
		String s = "		ciao 	 ";
		System.out.println(trim(s));
	}

}
