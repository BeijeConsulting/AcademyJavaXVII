package it.beije.xvii.exercises.giampaoli;
public class Esercizio6
{  
	public static void main(String args[])  
	{  
		int numeroPrimo = 1;
		int numeroSecondo = 6;		
		int numeroRighe = 0;
		String figura = "";
				
		while (numeroRighe < 6)	{
			for(int contatore = 1; contatore <= numeroPrimo; contatore++) {
				//for(int secondoContatore = 0; secondoContatore > 0; secondoContatore--) {
					figura = figura + contatore;
					//}
				
			}
			
			figura = figura + "  ";
			
			for(int contatore = 1; contatore <= numeroSecondo; contatore++) {
				//for(int secondoContatore = 0; secondoContatore > 0; secondoContatore--) {
					figura = figura + contatore;
					//}
			}
			
			figura = figura + "\n";
			numeroRighe++;
			numeroPrimo++;
			numeroSecondo--;
		}
		System.out.print(figura);
	}
}  