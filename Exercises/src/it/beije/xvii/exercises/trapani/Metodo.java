package it.beije.xvii.exercises.trapani;

public class Metodo {
	private String frase = "Supercalifragilistichespiralidoso";

	public static void main(String[] args) {

	
	}
	
	public int contaLettera(char c, String str) {
		int n=0;
		frase = str;
		
		for (int i=0; i<str.length(); i++) {
			if(str.charAt(i) == c) {
				n++;
			}
		}
				
		return n;
		
	}

}
