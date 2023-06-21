package it.beije.xvii.exercises.giampaoli;
public class Contrario {
	
	public static void main(String args[])   {
		
		String frase = "Angolo bar a Bologna";
		String fraseContraria = "";
				
	        for (int contatore = (frase.length()- 1); contatore >= 0 ; contatore--) {
	        	
	        	fraseContraria = fraseContraria + frase.charAt(contatore);
	                              
	           }
	     System.out.println(frase);
	       
	     System.out.println(fraseContraria);
	        
	}
}