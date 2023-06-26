package it.beije.xvii.exercises.trapani;

public class SoloVocali {

	public static void main(String[] args) {
		String frase = "Supercalifragilistichespiralidoso";
		
		for(int i=0; i<frase.length(); i++) {
			if((frase.charAt(i)=='a') || (frase.charAt(i)=='e')||(frase.charAt(i)=='i')|| (frase.charAt(i)=='o') || (frase.charAt(i)=='u')){
				System.out.print(frase.charAt(i));
			}
		}
	}

}
