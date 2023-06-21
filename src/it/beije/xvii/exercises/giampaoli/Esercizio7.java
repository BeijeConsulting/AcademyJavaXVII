package it.beije.xvii.exercises.giampaoli;
public class Esercizio7
{  
	public static void main(String args[])  
	{  
		
		long fibonacci1 = 0;
		long fibonacci2 = 1;
		long temporaneo = 0;
		String figuraFibonacci = "";
		
			for (int contatore = 0; contatore < 100; contatore ++) {
				temporaneo = fibonacci1;
				fibonacci1 = fibonacci1 + fibonacci2 ;
				fibonacci2 = temporaneo;
				figuraFibonacci = figuraFibonacci + fibonacci1 + ",";
				System.out.print(fibonacci1 + " ");
			}
	
		
	}
}  