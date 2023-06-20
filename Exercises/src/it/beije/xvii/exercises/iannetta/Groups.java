package it.beije.xvii.exercises.iannetta;

public class Groups {

	public static boolean GroupCheck(String s) {
		String check = "";
		int oldLength = s.length();
		for (int i =0; i < oldLength; i++) {
			char c = s.charAt(i);
			if (c == '(' || c == ')'||
				c == '[' || c == ']'||
				c == '{' || c == '}') check += c;
		}
		
		boolean keepChecking = true;
		while (keepChecking && oldLength > 0) {
			//System.out.println(check);
			check = check.replace("()", "").replace("[]", "").replace("{}", "");
			int newLength = check.length();
			if (oldLength == newLength) return false;
			oldLength = newLength;
		}
		return true;
	}
					
	public static void main(String[] args) {
		String examples[] = {"({})","[[]()]", "[{()}]", "({d})", "[[s]d()]", "[{(s)}kl]", //true
							 "{(})", "([]", "[])"}; //false
		
		for (String s : examples) {
			System.out.println(s);
			System.out.println(GroupCheck(s) + "\n");
		}

	}

}
