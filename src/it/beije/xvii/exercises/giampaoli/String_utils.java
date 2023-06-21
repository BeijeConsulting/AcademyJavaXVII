package it.beije.xvii.exercises.giampaoli;

public class String_utils {

	public static int indexOf(String s, char c) {  
		
		int a = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) ==  c) {
				a = i;
				break;
			}
		}
		
			return a;
	}		
	
	
	public static int indexOfFromIndex(String s, char c, int fromIndex) {  
		
		int a = 0;
		for (int i = fromIndex; i < s.length(); i++) {
			if (s.charAt(i) ==  c) {
				a = i;
				break;
			}
		}
		
			return a;
	}		


	public static String toUpperCaseae(String s) {  
		
		String frase = "";
		
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case 'a': 	
				frase = frase + "A";
				break;
			case 'b': 	
				frase = frase + "B";
				break;
			case 'c': 	
				frase = frase + "C";
				break;
			case 'd': 	
				frase = frase + "D";
				break;
			case 'e': 	
				frase = frase + "E";
				break;
			case 'f': 	
				frase = frase + "F";
				break;
			case 'g': 	
				frase = frase + "G";
				break;
			case 'h': 	
				frase = frase + "H";
				break;
			case 'i': 	
				frase = frase + "I";
				break;
			case 'j': 	
				frase = frase + "J";
				break;
			case 'k': 	
				frase = frase + "K";
				break;
			case 'l': 	
				frase = frase + "L";
				break;
			case 'm': 	
				frase = frase + "M";
				break;
			case 'n': 	
				frase = frase + "N";
				break;
			case 'o': 	
				frase = frase + "O";
				break;
			case 'p': 	
				frase = frase + "P";
				break;
			case 'q': 	
				frase = frase + "Q";
				break;
			case 'r': 	
				frase = frase + "R";
				break;
			case 's': 	
				frase = frase + "S";
				break;
			case 't': 	
				frase = frase + "T";
				break;
			case 'u': 	
				frase = frase + "U";
				break;
			case 'v': 	
				frase = frase + "V";
				break;
			case 'w': 	
				frase = frase + "W";
				break;
			case 'x': 	
				frase = frase + "X";
				break;
			case 'y': 	
				frase = frase + "Y";
				break;
			case 'z': 	
				frase = frase + "Z";
				break;
			}
		
		}
		return frase;
	}	

	public static String toLowerCaseae(String s) {  
		
		String frase = "";
		
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case 'A': 	
				frase = frase + "A";
				break;
			case 'B': 	
				frase = frase + "B";
				break;
			case 'C': 	
				frase = frase + "C";
				break;
			case 'D': 	
				frase = frase + "D";
				break;
			case 'E': 	
				frase = frase + "E";
				break;
			case 'F': 	
				frase = frase + "F";
				break;
			case 'G': 	
				frase = frase + "G";
				break;
			case 'H': 	
				frase = frase + "H";
				break;
			case 'I': 	
				frase = frase + "I";
				break;
			case 'J': 	
				frase = frase + "J";
				break;
			case 'K': 	
				frase = frase + "K";
				break;
			case 'L': 	
				frase = frase + "L";
				break;
			case 'M': 	
				frase = frase + "M";
				break;
			case 'N': 	
				frase = frase + "N";
				break;
			case 'O': 	
				frase = frase + "O";
				break;
			case 'P': 	
				frase = frase + "p";
				break;
			case 'Q': 	
				frase = frase + "q";
				break;
			case 'R': 	
				frase = frase + "r";
				break;
			case 'S': 	
				frase = frase + "s";
				break;
			case 'T': 	
				frase = frase + "t";
				break;
			case 'U': 	
				frase = frase + "u";
				break;
			case 'V': 	
				frase = frase + "v";
				break;
			case 'W': 	
				frase = frase + "w";
				break;
			case 'X': 	
				frase = frase + "x";
				break;
			case 'Y': 	
				frase = frase + "y";
				break;
			case 'Z': 	
				frase = frase + "z";
				break;
			}
		
		}
		return frase;
	}	


	
	public static boolean equals(String s, String s2) {  
		
		boolean a = false;
		
		if (s.length() == s2.length()) {
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == s2.charAt(i)) {
					a = true;
				} else {
					a = false;
					break;
				} 
			} 
				
		} 
		 
		return a;
	}
	
	
	public static boolean equalsIgnoreCase(String s, String s2) {  
		
		boolean a = false;
		
//		if (s.length() == s2.length()) {
//			for (int i = 0; i < s.length(); i++) {
//				if (s.charAt(i) == s2.charAt(i)) {
//					a = true;
//				} else {
//					a = false;
//					break;
//				} 
//			} 
//				
//		} 
		 
		return a;
	}

	
	public static boolean startsWith(String s, String prefix) {  
		
		boolean a = false;
		
		for (int i = 0; i < prefix.length(); i++) {
			if (s.charAt(i) ==  prefix.charAt(i)) {
				a = true;
				
			} else {
				a = false;
				break;
			}
		}
		
		return a;

	}

	public static boolean endsWith(String s, String prefix) {  
		
		boolean a = false;
		
		for (int i = (s.length() - 1); i >= (s.length() - prefix.length()); i--) {
			if (s.charAt(i) ==  prefix.charAt(i - ((s.length() - prefix.length())))) {
				a = true;
				
			} else {
				a = false;
				break;
			}
		}
		
		return a;

	}
	
	public static String replaceChar(String s, char oldChar, char newChar) {  
		
		String a = "";
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == oldChar) {
				a = a + newChar;
			} else {
				a = a + s.charAt(i);
			}
		}
		
		
		
		return a;

	}

	public static String replaceString(String s, String oldChar, String newChar) {  
		
		String a = "";
//		
//		for (int i = 0; i < s.length(); i++) {
//			if (s.charAt(i) == oldChar) {
//				a = a + newChar;
//			} else {
//				a = a + s.charAt(i);
//			}
//		}
//		
//		
//		
		return a;

	}
	
	
	
public static String trim(String s) {  
		
		String a = "";
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ' ') {
				a = a + s.charAt(i);
			}
		}
		
		
		
		return a;

	}


	public static void main(String args[])   {
		System.out.print(trim("       ciaomamma            "));
	}
}
