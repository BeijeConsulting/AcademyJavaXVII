package it.beije.xvii.exercises.ulloa;

/*
 * Metodi da implementare in una classe StringUtils
 * (tutti static e fattibili con i solo length() e charAt() ): 
 * 		int indexOf(String s, char c)
 * 		int indexOf(String s, char c, index fromIndex)
 * 		int indexOf(String s, String str)
 * 		int indexOf(String s, String str, index fromIndex)
 * 		String substring(String s, int beginIndex)
 * 		String substring(String s, int beginIndex, int endIndex)
 * 		String toLowerCase(String s)
 * 		String toUpperCase(String s)
 * 		boolean equals(String s1, String s2)
 * 		boolean equalsIgnoreCase(String s1, String s2)
 * 		boolean contains(String s, String str)
 * 		boolean startsWith(String s, String prefix)
 * 		boolean endsWith(String s, String suffix)
 * 		String replace(String s, char oldChar, char newChar)
 * 		String replace(String s, String oldChar, String newChar)
 * 		String trim(String s)
 * 
 * la String s presente in tutti i metodi come primo parametro è 
 * naturalmente la stringa su cui fare l'elaborazione.
 * Il risultato di ciascun metodo deve essere lo stesso del metodo originale 
 * chiamato sulla stringa 
 * (es. StringUtils.substring(String s, int beginIndex) 
 * deve restituire lo stesso risultato di s.substring(int beginIndex) ) 
 */

public class StringUtils {
	//solo per testing
	public static void main(String[] args) {
		
	}
	
	public static int indexOf(String s, char c) {
        return indexOf(s, c, 0);
    }
    
    public static int indexOf(String s, char c, int fromIndex) {
        int length = s.length();
        if (fromIndex < 0) {
            fromIndex = 0;
        } else if (fromIndex >= length) {
            return -1;
        }
        for (int i = fromIndex; i < length; i++) {
            if (s.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }
    
    public static int indexOf(String s, String str) {
        return indexOf(s, str, 0);
    }
    
    public static int indexOf(String s, String str, int fromIndex) {
        int sLength = s.length();
        int strLength = str.length();
        if (strLength == 0) {
            return fromIndex;
        }
        for (int i = fromIndex; i <= sLength - strLength; i++) {
            boolean match = true;
            for (int j = 0; j < strLength; j++) {
                if (s.charAt(i + j) != str.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return i;
            }
        }
        return -1;
    }
    
    public static String substring(String s, int beginIndex) {
        return substring(s, beginIndex, s.length());
    }
    
    public static String substring(String s, int beginIndex, int endIndex) {
        int length = s.length();
        if (beginIndex < 0) {
            beginIndex = 0;
        } else if (beginIndex > length) {
            beginIndex = length;
        }
        if (endIndex < beginIndex) {
            endIndex = beginIndex;
        } else if (endIndex > length) {
            endIndex = length;
        }
        int resultLength = endIndex - beginIndex;
        char[] resultChars = new char[resultLength];
        for (int i = 0; i < resultLength; i++) {
            resultChars[i] = s.charAt(beginIndex + i);
        }
        return new String(resultChars);
    }
    
    public static String toLowerCase(String s) {
        int length = s.length();
        char[] resultChars = new char[length];
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                c = (char) (c + 32);
            }
            resultChars[i] = c;
        }
        return new String(resultChars);
    }
    
    public static String toUpperCase(String s) {
        int length = s.length();
        char[] resultChars = new char[length];
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c = (char) (c - 32);
            }
            resultChars[i] = c;
        }
        return new String(resultChars);
    }
    
    public static boolean equals(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();
        if (length1 != length2) {
            return false;
        }
        for (int i = 0; i < length1; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean equalsIgnoreCase(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();
        if (length1 != length2) {
            return false;
        }
        for (int i = 0; i < length1; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2 && Character.toUpperCase(c1) != Character.toUpperCase(c2)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean contains(String s, String str) {
        return indexOf(s, str) != -1;
    }
    
    public static boolean startsWith(String s, String prefix) {
        int sLength = s.length();
        int prefixLength = prefix.length();
        if (prefixLength > sLength) {
            return false;
        }
        for (int i = 0; i < prefixLength; i++) {
            if (s.charAt(i) != prefix.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean endsWith(String s, String suffix) {
        int sLength = s.length();
        int suffixLength = suffix.length();
        if (suffixLength > sLength) {
            return false;
        }
        for (int i = 0; i < suffixLength; i++) {
            if (s.charAt(sLength - suffixLength + i) != suffix.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    public static String replace(String s, char oldChar, char newChar) {
        int length = s.length();
        char[] resultChars = new char[length];
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == oldChar) {
                c = newChar;
            }
            resultChars[i] = c;
        }
        return new String(resultChars);
    }
    
    public static String replace(String s, String oldStr, String newStr) {
        int sLength = s.length();
        int oldLength = oldStr.length();
        if (oldLength == 0) {
            return s;
        }
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < sLength) {
            if (startsWith(s.substring(i), oldStr)) {
                result.append(newStr);
                i += oldLength;
            } else {
                result.append(s.charAt(i));
                i++;
            }
        }
        return result.toString();
    }
    
    public static String trim(String s) {
        int length = s.length();
        int start = 0;
        int end = length - 1;
        while (start <= end && s.charAt(start) <= ' ') {
            start++;
        }
        while (end >= start && s.charAt(end) <= ' ') {
            end--;
        }
        if (start == 0 && end == length - 1) {
            return s;
        } else {
            return s.substring(start, end + 1);
        }
    }
}