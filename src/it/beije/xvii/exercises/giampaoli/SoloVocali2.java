package it.beije.xvii.exercises.giampaoli;


public class SoloVocali2 {
	
	public static void main(String args[])   {
		//String Vocali[] = new String[10];         
		char vocali[] = {'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u'};         
		String frase = "A a B b E e T t";                      
		for (int contatore = 0; contatore < frase.length(); contatore++) {                 
			for (int contatore2 = (vocali.length - 1); contatore2 >= 0; contatore2--) {                         
				if (frase.charAt(contatore) == vocali[contatore2]) {                              
					System.out.println(frase.charAt(contatore));       
		}
	            
	        } 	
	    }
	}
}