package it.beije.xvii.exercises.trapani;

public class StampaMaiuscole {

	public static void main(String[] args) {
		String[] stringhe = {"Ciao ", "come stai ", "Martina?"};
		
		for(int i = 0; i<stringhe.length; i++) {
			String b=stringhe[i];
			if((b.charAt(0) >= 'A') && (b.charAt(0) < 'Z')) {
				System.out.print(b);
			}
		}
		
		
	}

}
