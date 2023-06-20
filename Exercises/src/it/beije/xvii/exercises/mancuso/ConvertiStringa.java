package it.beije.xvii.exercises.mancuso;

public class ConvertiStringa {

	public static void main(String[] args) {
		
		String s = "(( @";
		
		String recurring = "";
		
		String result = "";
		
		for(int i=0; i<s.length(); i++) {
			String myCharacter = String.valueOf(s.charAt(i));
			
			if(recurring.contains(myCharacter.toLowerCase())) {
				result += ")";
			}else {
			
				String preceding = s.substring(0, i).toLowerCase();
				String succeding;
				
				if(i<s.length()-1) {
					succeding = s.substring(i+1).toLowerCase();
				}else {
					succeding = "";
				}
					
				if(preceding.contains(myCharacter.toLowerCase()) || succeding.contains(myCharacter.toLowerCase())) {
					recurring += myCharacter.toLowerCase();
					result += ")";
				}else {
					result += "(";
				}
			}
		}
		
		System.out.println(result);
	}

}
