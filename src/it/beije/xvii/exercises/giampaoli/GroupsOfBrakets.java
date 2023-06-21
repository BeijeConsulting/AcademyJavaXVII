package it.beije.xvii.exercises.giampaoli;

public class GroupsOfBrakets {

		public static boolean groupCheck(String s) {
			boolean b_Giusto = false;
			
			
			for (int i = 0; i < s.length(); i++) {
			//	if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
					
				
				switch(s.charAt(i)) {
					case '(':
						for (int x = i; x < s.length(); x++) {
							if (s.charAt(x) == ')') {
								b_Giusto = true;
								break;
							} else {
								b_Giusto= false;
							}
						}
						break;
					case '{':
						for (int x = i; x < s.length(); x++) {
							if (s.charAt(x) == '}') {
								b_Giusto = true;
								break;
							} else {
								b_Giusto= false;
							}
						}
						break;
					case '[':
						for (int x = i; x < s.length(); x++) {
							if (s.charAt(x) == ']') {
								b_Giusto = true;
								break;
							} else {
								b_Giusto= false;
							}
						}
						break;
				}	
						
			}
		
			return b_Giusto;
		}
	
		

		public static void main(String args[])  
		{  
			System.out.print(groupCheck("()()"));
			
		}
		
}
