package it.beije.xvii.exercises.giampaoli;
public class Esercizio4
{  
	public static void main(String args[])  
	{  
		String figura = "";
		int x = 0;
		
		for(int contatore = 0; contatore < 6; contatore++) {
			for(int secondoContatore = x; secondoContatore < 6; secondoContatore++) {
				figura = figura + "*";
				}
			x++;
			figura = figura + "\n";
		}
		System.out.print(figura);
	}
}  