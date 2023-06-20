package it.beije.xvii.exercises.iannetta;

public class ConvertToBrackets {

	public static String convert(String s) {
		s = s.toLowerCase();
		int oldLength = s.length();
		int newLength;
		
		
		if(s.contains("(") || s.contains(")")) {
			int openBrackets = oldLength - s.replace("(", "").length();
			int closedBrackets = oldLength - s.replace(")", "").length();
		
			if (closedBrackets <= 1) {
				if (openBrackets <= 1) s = s.replace(")", "(");
				else if (openBrackets > 1) {
					int index = s.indexOf(')');
					s = s.replace("(", ")");
					if (index >= 0) s = s.substring(0, index) + "(" + s.substring(index+1);
				}
			}	
			else if (openBrackets > 1) s = s.replace("(", ")");
		}
		
		for (int i =0; i < oldLength; i++) {
			String c = "" + s.charAt(i);
			if (!c.equals("(") && !c.equals(")")) {
				newLength = s.replace(c, "").length();
				if (oldLength - newLength == 1) s = s.replace(c, "(");
				else s = s.replace(c, ")");
			}
		}
		return s;
	}
	
	
	public static void main(String[] args) {
	
		String[] strings = {"din", "recede", "Success", "(( @", "()", "(()"};
		
		for (String s : strings) {
			System.out.println(s);
			System.out.println(convert(s) + "\n");
		}
	}
}
