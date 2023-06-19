package it.beije.xvii.exercises.mancuso;

public class StringBuilderUtils {

	public static StringBuilder append(StringBuilder sb, String str) {
		String myString = sb + str;
		StringBuilder mySb = new StringBuilder(myString);
		return mySb;
	}
	
	public static StringBuilder insert(StringBuilder sb, int offset, String str) {
		String myString = sb.toString();
		
		String subStart = StringUtils.substring(myString, 0, offset);
		String subEnd = StringUtils.substring(myString, offset);
		
		myString = subStart + str + subEnd;
		StringBuilder finalSb = new StringBuilder(myString);
		
		return finalSb;
	}
	
	public static StringBuilder delete(StringBuilder sb, int start, int end) {
		if(start >= 0 && start < sb.length() && end <= sb.length()) {
			String myString = sb.toString();
			String subStart = StringUtils.substring(myString, 0, start);
			String subEnd;
			if(end == sb.length()) {
				subEnd = "";
			}else {
				subEnd = StringUtils.substring(myString, end); 
			}
			String finalString = subStart + subEnd;
			StringBuilder finalSb = new StringBuilder(finalString);
			return finalSb;
		}else {
			throw new IllegalArgumentException("L'indice non è accettabile.");
		}
	}
	
	public static StringBuilder deleteCharAt(StringBuilder sb, int index) {			
			sb = delete(sb, index, index + 1);			
			return sb;	
	}
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("hello");
		//String toAppend = " there";
		//System.out.println(append(sb, toAppend));
		
		//String toInsert = " ouch ";
		//System.out.println(insert(sb, 1, toInsert));
		
		System.out.println(deleteCharAt(sb, 4));
	}

}
