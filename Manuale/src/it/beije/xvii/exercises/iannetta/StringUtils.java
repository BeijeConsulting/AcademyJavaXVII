package it.beije.xvii.exercises.iannetta;

public class StringUtils {

	//index of char
	public static int indexOf(String s, char c, int fromIndex) {
		return indexOf(s, "" + c, fromIndex);
		/*for (int i = fromIndex; i < s.length(); i++) {
			if (i < 0) i = 0;
			if (s.charAt(i) == c) return i;
		}
		return -1;*/
	}
	
	public static int indexOf(String s, char c) {
		return indexOf(s, c, 0);
	}
	
	//index of string
	public static int indexOf(String s, String str, int fromIndex) {	
		boolean isFound = false;
		
		for (int i = fromIndex; i < s.length() - str.length(); i++) {
			if (i < 0) i = 0;
			for (int j = 0; j < str.length(); j++) {
				if (s.charAt(i + j) != str.charAt(j)) {
					isFound = false;
					break;
				}
				else isFound = true;
			}
			if (isFound) return i;
		}
		return -1;
	}
	
	public static int indexOf(String s, String str) {
		return indexOf(s, str, 0);
	}
	
	//substring
	public static String substring(String s, int beginIndex, int endIndex) throws Exception {
		if (endIndex < 0 || beginIndex < 0 ||
			endIndex > s.length() ||
			endIndex < beginIndex ) throw new Exception("String index out of bounds");
		
		String result = "";
		for (int i = beginIndex; i < endIndex; i++) result += s.charAt(i);
		return result;
	}
	
	public static String substring(String s, int beginIndex) throws Exception {
		return substring(s, beginIndex, s.length());
	}
	
	//to lowercase and uppercase
	public static String toLowerCase(String s) {
		String result = "";
		for (int i = 0; i < s.length(); i++) {
			char charToCheck = s.charAt(i);
			if (charToCheck >= 'A' && charToCheck <= 'Z') result += (char) ((int)charToCheck + 32);
			else result += charToCheck;
		}
		return result;
	}
	
	public static String toUpperCase(String s) {
		String result = "";
		for (int i = 0; i < s.length(); i++) {
			char charToCheck = s.charAt(i);
			if (charToCheck >= 'a' && charToCheck <= 'z') result += (char) ((int)charToCheck - 32);
			else result += charToCheck;
		}
		return result;
	}

	//equals strings
	public static boolean equals(String s1, String s2) {
		if (s1.length() != s2.length()) return false;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) return false;
		}
		return true;
	}
	
	public static boolean equalsIgnoreCase(String s1, String s2) {
		return equals(toLowerCase(s1), toLowerCase(s2));
	}
	
	//contains
	public static boolean contains(String s, String str) {
		int index = indexOf(s, str);
		return (index >= 0);
	}
	
	//starts - ends with
	public static boolean startsWith(String s, String prefix) {
		for (int i = 0; i < prefix.length(); i++) {
			if (s.charAt(i) != prefix.charAt(i)) return false;
		}
		return true;
	}
	
	public static boolean endsWith(String s, String suffix) {
		for (int i = 0; i < suffix.length(); i++) {
			int shift = s.length() - suffix.length();
			if (s.charAt(i + shift) != suffix.charAt(i)) return false;
		}
		return true;
	}
	
	//replace
	public static String replace(String s, String oldChar, String newChar) throws Exception {
			
		String result = "";
		int shift = 0;
		
		int index = indexOf(s, oldChar);
		while(index != -1) {
			result += substring(s,  shift, index) + newChar;
			shift = index + newChar.length();
			index = indexOf(s, oldChar, shift);
		}
		result += substring(s, shift);
		return result;
		
	}
	public static String replace(String s, char oldChar, char newChar) throws Exception {
		return replace(s, "" + oldChar, "" + newChar);
	}
	
	//trim
	public static String trim(String s) throws Exception {
		String result = s;
		boolean isWhiteSpace = false;;
		do {
			if (result.charAt(0) == '\n' ||
				result.charAt(0) == '\t' ||
				result.charAt(0) == ' ') {
				isWhiteSpace = true;
				result = substring(result, 1);
			}
			else isWhiteSpace = false;
		}
		while (isWhiteSpace);
		
		int length = result.length();
		do {
			if (result.charAt(length - 1)  == '\n' ||
				result.charAt(length - 1) == '\t' ||
				result.charAt(length - 1) == ' ') {
				isWhiteSpace = true;
				result = substring(result, 0, length - 1);
				length--;
			}
			else isWhiteSpace = false;
		}
		while (isWhiteSpace);
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		
		String s = "\tTHIS is a stRing to TesT meThods   ";
		System.out.println(s);
		
		/*char charToLookFor = 'a';
		System.out.println("\nFind index of char " + charToLookFor);
		System.out.println("My method: " + indexOf(s, charToLookFor));
		System.out.println("String method: " + s.indexOf(charToLookFor));
		int fromIndex = -5;
		System.out.println("\nFind index of char " + charToLookFor + " from index " + fromIndex);
		System.out.println("My method: " + indexOf(s, charToLookFor, fromIndex));
		System.out.println("String method: " + s.indexOf(charToLookFor, fromIndex));
		
		String stringToLookFor = "Th";
		System.out.println("\nFind index of string " + stringToLookFor);
		System.out.println("My method: " + indexOf(s, stringToLookFor));
		System.out.println("String method: " + s.indexOf(stringToLookFor));
		
		fromIndex = -2;
		System.out.println("\nFind index of string " + stringToLookFor + " from index " + fromIndex);
		System.out.println("My method: " + indexOf(s, stringToLookFor, fromIndex));
		System.out.println("String method: " + s.indexOf(stringToLookFor, fromIndex));
		
		int beginIndex = 15;
		System.out.println("\nGet substring from " + beginIndex);
		System.out.println("My method: " + substring(s, beginIndex));
		System.out.println("String method: " + s.substring(beginIndex));
		int endIndex =30;
		System.out.println("\nGet substring from " + beginIndex + " to index " + endIndex);
		System.out.println("My method: " + substring(s, beginIndex, endIndex));
		System.out.println("String method: " + s.substring(beginIndex, endIndex));
		
		System.out.println("\nTo lower case");
		System.out.println("My method: " + toLowerCase(s));
		System.out.println("String method: " + s.toLowerCase());
		System.out.println("\nTo upper case");
		System.out.println("My method: " + toUpperCase(s));
		System.out.println("String method: " + s.toUpperCase());
		
		String str = "This is a string to test methods";
		System.out.println("\nEquals to " + str);
		System.out.println("My method: " + equals(s, str));
		System.out.println("String method: " + s.equals(str));
		
		System.out.println("\nEquals ignore case to " + str);
		System.out.println("My method: " + equalsIgnoreCase(s, str));
		System.out.println("String method: " + s.equalsIgnoreCase(str));
		
		str = "a stR";
		System.out.println("\nContains " + str);
		System.out.println("My method: " + contains(s, str));
		System.out.println("String method: " + s.contains(str));
		
		str = "THIS";
		System.out.println("\nStarts with " + str);
		System.out.println("My method: " + startsWith(s, str));
		System.out.println("String method: " + s.startsWith(str));
		
		str = "hods";
		System.out.println("\nEnds with " + str);
		System.out.println("My method: " + endsWith(s, str));
		System.out.println("String method: " + s.endsWith(str));
		
		char oldChar = 't';
		char newChar = 'T';
		s = toLowerCase(s);
		System.out.println("\nReplace character " + oldChar + " with " + newChar);
		System.out.println(s);
		System.out.println("My method: " + replace(s, oldChar, newChar));
		System.out.println("String method: " + s.replace(oldChar, newChar));
				
		String oldString = "ing";
		String newString = "inging";
		System.out.println("\nReplace string " + oldString + " with " + newString);
		System.out.println("My method: " + replace(s, oldString, newString));
		System.out.println("String method: " + s.replace(oldString, newString));
		*/
		
		System.out.println("\nTrim string");
		System.out.println("My method: " + trim(s));
		System.out.println("String method: " + s.trim());
	}

}
