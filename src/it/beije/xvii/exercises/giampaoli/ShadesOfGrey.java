package it.beije.xvii.exercises.giampaoli;

public class ShadesOfGrey {
	
	static String[] shadesOfGrey(int num) {		
			
		
		if (num < 0) {
				num = 0;
			} else if (num > 254) {
				num = 254;
		}
		
		String[] a_risultato = new String[num];
		
		for (int i = 1; i <= num; i++) {
				
				a_risultato[i - 1] = "#" + Integer.toHexString(i) + Integer.toHexString(i) + Integer.toHexString(i) + "\n";
				//s_Temp = String.format("%02d", 15);
				//a_risultato[i] = "#" + String.format("%02d", Integer.toHexString(i)) + String.format("%02d", Integer.toHexString(i)) + String.format("%02d", Integer.toHexString(i)) ; 
		}
		for (int i = 1; i < num; i++) {
			System.out.print(a_risultato[i]);
		}
		return a_risultato;
		}
	
	public static void main(String args[])   {
		shadesOfGrey(285);
	}
		
}
