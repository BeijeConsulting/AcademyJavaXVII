package it.beije.xvii.exercises.Char;
import java.util.Arrays;
public class esString {

	public static void main(String[] args) {
//		esString.soleVocali("animal");
//		String[] array = {"Parola", "animale","Maiuscola"};
//		esString.stampaMaiuscole(array);
		
//		System.out.println(StringArray.contaLettere('c', "conta"));
//		String stringa = "Viva Java!";
//		esString.stampaContrario(stringa);
//		esString.concatenaParole("gatto", "cane", "topo");
//		esString.esSetter("nome");
		esString.morraCinese("sasso", "carta");
      
	}
	//1
	public static void soleVocali(String stringa) {
		StringBuilder build = new StringBuilder();
	 for(int i = 0; i < stringa.length(); i++) {
		 char lettera = stringa.charAt(i);
		 if(lettera == 'a' || lettera == 'e' || lettera == 'i' || lettera == 'o' || lettera == 'u' )
			 build.append(lettera);
		 
	 }
	 System.out.println(build);
	}
	//2
	public static void stampaMaiuscole(String [] array) {
		for(int i = 0; i< array.length; i++) {
			String parola = array[i];
			String primaLettera = parola.substring(0 , 1);
			if(parola.startsWith(primaLettera.toUpperCase())) {
				System.out.println(parola);
			}
		}
	}
	//3
	public static int contaLettere(char c, String parola) {
		int count = 0;
		for(int i = 0; i< parola.length(); i++) {
			char lettera = parola.charAt(i);
			if(lettera == c) {
				count++;
			}
		}
		return count;
	}
	//4
	public static void stampaContrario(String stringa) {
		StringBuilder string = new StringBuilder(stringa);
		string.reverse();
		System.out.println(string);
	}
	//5
	public static void concatenaParole(String parola1, String parola2, String parola3) {
		StringBuilder frase = new StringBuilder();
		String [] array = {parola1,parola2,parola3};
		for(int i =0; i< array.length;i++) {
			if(i == (array.length - 1)) 
				frase.append(array[i]);
			else
				frase.append(array[i] + "*");	
		}

		System.out.println(frase);
	
	}
	//6
	public static void esSetter(String parola) {
		StringBuilder frase = new StringBuilder();
		frase.append("set");
		for(int i = 0; i< parola.length(); i++) {
			if( i == 0) {
				String lettera = parola.substring(0,1);
				frase.append(lettera.toUpperCase());
				
			}
			else {
				char let = parola.charAt(i);
				frase.append(let);
			}
				
		}
		System.out.println(frase);
	}
	//7
	public static void esgetter(String parola) {
		StringBuilder frase = new StringBuilder();
		frase.append("get");
		for(int i = 0; i< parola.length(); i++) {
			if( i == 0) {
				String lettera = parola.substring(0,1);
				frase.append(lettera.toUpperCase());
				
			}
			else {
				char let = parola.charAt(i);
				frase.append(let);
			}
				
		}
		System.out.println(frase);
	}
	//8
	public static void morraCinese(String parola1, String parola2) {
		if(parola1.equals("sasso") ||parola1.equals("carta") || parola1.equals("forbice") && parola2.equals("sasso") ||parola2.equals("carta") || parola2.equals("forbice")) {

				if ((parola1.equals("carta") && parola2.equals("sasso"))|| (parola1.equals("forbice") && parola2.equals("carta")) || (parola1.equals("sasso") && parola2.equals("carta"))) {
	            System.out.println("Ha vinto il primo giocatore");
	        }
				else
		 System.out.println("Ha vinto il secondo giocatore");
     
		}
		else {
			System.out.println("errore");
		}
		 
	}

}
