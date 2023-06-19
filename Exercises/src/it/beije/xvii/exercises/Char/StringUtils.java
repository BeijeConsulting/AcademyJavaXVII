package it.beije.xvii.exercises.Char;
import java.util.ArrayList;
public class StringUtils {
	public static void main(String[] args) {
//		System.out.println(indexOf("animaln",'a'));
//	System.out.println(subString("animal",1,3));
//	System.out.println(toUpperCase("aniMAl"));
//		System.out.println(equalsIgnoreCase("caNe","Cane"));
//		System.out.println(contains("cane","g"));
//		System.out.println(endsWith("cavallo","llo"));
//		System.out.println(replace("animal",'a','b'));
//		System.out.println("animal".replace("mr", "mp"));
//		System.out.println(trim("    animal    3    "));
//		System.out.println(contains("animal","mal"));
		System.out.println(replace("animal","mal","lol"));
	}
	public static int indexOf(String s, char c) {
		for(int i = 0; i< s.length(); i++) {
			if(s.charAt(i) == c) {
				return i;
			}

		}
		return -1;
	}
	public static int indexOf(String s, char c, int fromIndex) {
		for(int i = fromIndex; i< s.length(); i++) {
			if(s.charAt(i) == c) {
				return i;
			}

		}
		return -1;
	}
	public static int indexOf(String s, String str) {

	    for (int i = 0; i <= s.length() - str.length(); i++) {
	        boolean found = true;

	        for (int j = 0; j < str.length(); j++) {
	            if (s.charAt(i + j) != str.charAt(j)) {
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
	public static String subString(String s, int beginIndex) {
		String word = "";
		for(int i = beginIndex; i < s.length(); i++) {
			word += s.charAt(i);;
		}
		return word;
	}
	public static String subString(String s, int beginIndex, int endIndex) {
		String word = "";
		for(int i = beginIndex; i < endIndex; i++) {
			word += s.charAt(i);
		}
		return word;
	}
	public static String toLowerCase(String s) {
		String word = "";
		for(int i = 0; i< s.length(); i++) {
			if(  s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
				char c = (char) (s.charAt(i) + 32);
				word += c;
			}
			else
				word += s.charAt(i);
		 
		}
		return word;
	}
	public static String toUpperCase(String s) {
		String word = "";
		for(int i = 0; i< s.length(); i++) {
			if(  s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
				char c = (char) (s.charAt(i) - 32);
				word += c;
			}
			else
				word += s.charAt(i);
		 
		}
		return word;
	}
	public static boolean equals(String s1, String s2) {
		boolean bool = true;
		if(s1.length() != s2.length()) {
			return false;
		}
		for(int i = 0; i< s1.length(); i++) {
			if(s1.charAt(i) != s2.charAt(i)) {
				bool = false;
			}
		}
		return bool;
	}
	public static boolean equalsIgnoreCase(String s1, String s2) {
		boolean bool = true;
		if(s1.length() != s2.length()) {
			return false;
		}
		for(int i = 0; i< s1.length(); i++) {

              if(s1.charAt(i) >= 'A' && s1.charAt(i) <= 'Z' && s2.charAt(i) >= 'a' && s2.charAt(i) <= 'z') {
            	  char letter1 = (char) (s1.charAt(i) + 32);
                  char letter2 = (char) (s2.charAt(i));
                  if(letter1 == letter2 ) {
            		  bool = true;
            		  
            	  }
              }
                  else if(s1.charAt(i) >= 'a' && s1.charAt(i) <= 'z' && s2.charAt(i) >= 'A' && s2.charAt(i) <= 'Z') {
                 	  char letter1 = (char) (s1.charAt(i));
                      char letter2 = (char) (s2.charAt(i) + 32);
                      if(letter1 == letter2 ) {
                		  bool = true;
                		  
                	  }
                  }
	
                  else if((s1.charAt(i) != s2.charAt(i))) {
				bool = false;
			}
		}
		return bool;
	}
	public static boolean contains(String s, String str) {
		 if (s.indexOf(str) == -1)
			return false;
		 else 
			 return true;
	}
	public static boolean startsWith(String s, String prefix) {
		boolean bool = false;
	for(int i = 0; i< prefix.length(); i++) {
		if(s.charAt(i) == prefix.charAt(i)) {
				bool = true;
			}
		else
			bool = false;
	}
			
		
		return bool;
		
	}
	public static boolean endsWith(String s, String suffix) {
		String word = "";
		String word2 = "";
		for(int i = s.length() - 1; i >= 0;i--) {
			word += s.charAt(i);
		}
		System.out.println(word);
		for(int j = suffix.length() - 1; j >= 0; j--) {
			word2+= suffix.charAt(j);
		}

		System.out.println(word2);
		if(startsWith(word,word2))
		   return true;
		else
		   return false;
	
	}
	public static String replace(String s, char oldChar, char newChar) {
		String word = "";
		for(int i = 0; i< s.length(); i++) {
			if(s.charAt(i) == oldChar) 
				 word += newChar;
			else 
				word += s.charAt(i);
		}
		return word;
	}
	public static String replace(String s, String oldChar, String newChar) {
		String word = "";
        if(contains(s,oldChar)) {
        	int ind = indexOf(s,oldChar); //3
            for(int a = 0; a < s.length(); a++) {
	        if(a >= ind && a < ind + oldChar.length()) {
//	        	"animal","mal","lol"
        	 for(int i = ind, j= 0; i < ind + oldChar.length(); i++, j++) {
        		word += newChar.charAt(j);
        	 }
        	 a += oldChar.length() - 1;
        	}
	        else
	        	word += s.charAt(a);
        }

        }

		return word;
	}

	public static String trim(String s) {
	    int start = 0;
	    int end = s.length() - 1;

	    while (start <= end && s.charAt(start) == ' ') {
	        start++;
	    }

	    while (end >= start && s.charAt(end) == ' ') {
	        end--;
	    }

	    return s.substring(start, end + 1);
	}
	
}
