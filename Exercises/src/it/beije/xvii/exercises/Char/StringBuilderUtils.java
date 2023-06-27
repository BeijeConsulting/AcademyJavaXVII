package it.beije.xvii.exercises.Char;

public class StringBuilderUtils {

	public static void main(String[] args) {
		StringBuilder ap = new StringBuilder("animal");
//		ap = append(ap," stringa3");
//		System.out.println(ap);
//		ap = append(ap," stringa4");
//		System.out.println(ap);
//		System.out.println(insert(ap, 4, "la"));
		System.out.println(delete(ap,2,4));
	
	}
	public static StringBuilder append(StringBuilder sb, String s) {
	String newString = sb.toString() + s;
	sb = new StringBuilder(newString);
	return sb;
	}
	public static StringBuilder append(StringBuilder sb, char s) {
		String newString = sb.toString() + s;
		sb = new StringBuilder(newString);
		return sb;
		}
	public static StringBuilder insert(StringBuilder sb, int offset, String str) {
 
		 StringBuilder newString = new StringBuilder();
		 newString = append(newString,sb.substring(0, offset));
		 newString = append(newString,str);
		 newString = append(newString,sb.substring(offset));
	        
	      return newString;
	}
	public static StringBuilder delete(StringBuilder sb, int start, int end) {

		 StringBuilder newString = new StringBuilder();
		 for(int i = 0; i< sb.length(); i++) {
			 if(!(i >= start && i < end)) {
				 newString = append(newString, sb.charAt(i));
			 }
			
		 }
		 return newString;
	}
	public static StringBuilder deleteCharAt(StringBuilder sb, int index) {

		 StringBuilder newString = new StringBuilder();
		 for(int i = 0; i< sb.length(); i++) {
			 if(i != index) {
				 newString = append(newString, sb.charAt(i));
			 }
			
		 }
		 return newString;
	}
	public static StringBuilder reverse(StringBuilder sb) {

		 StringBuilder newString = new StringBuilder();
		 for(int i = sb.length() - 1; i >= 0; i--) {
			 newString = append(newString, sb.charAt(i));
		 }
		 return newString;
	}

}
