package it.beije.xvii.exercises.giampaoli;

public class ConvertToBracket {
	public static String convertToBracket(String s) {  
		
		String s_Risultato = "";
		char c_Temp = ' ';
		boolean b_Presente = false;
		
		for (int i = 0; i < s.length(); i++) {
			b_Presente = false;
			c_Temp = s.charAt(i);
			
			for (int x = 0; x < s.length(); x++) {
				if (x == i) {
					continue;
				}
				if (c_Temp == s.charAt(x)) {
					b_Presente = true;
					break;
				}
				
			}
			
			if (b_Presente == true) {
				s_Risultato = s_Risultato + ")";
			} else {
				s_Risultato = s_Risultato + "(";
			}
		}
		return s_Risultato;
		
	}
	
	
	public static void main(String args[])   {
		System.out.print(convertToBracket("(( @"));
	}
	
	
	
}
