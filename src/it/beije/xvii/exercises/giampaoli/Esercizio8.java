package it.beije.xvii.exercises.giampaoli;
public class Esercizio8
{  
	long fibonacci1 = 0;
	long fibonacci2 = 1;
	long temporaneo = 0;
	int numeroFinale = 0;
	String figuraFibonacci = "";
	int nRighe = 10;
	
	public static void main(String args[])  
	{  
		
		long fibonacci1 = 0;
		long fibonacci2 = 1;
		long temporaneo = 0;
		String figuraFibonacci = "";
		int nRigheTot = 50;
		int nRiga = 0;
		boolean primo = true;
				
		 while (nRiga <= nRigheTot) {
		
			
				temporaneo = fibonacci1;
				fibonacci1 = fibonacci1 + fibonacci2;
				fibonacci2 = temporaneo;
				
				if (primo) {
					figuraFibonacci = figuraFibonacci + fibonacci1;
					primo = false;
				} else {
					figuraFibonacci = figuraFibonacci + "," + fibonacci1;
				}
		
			System.out.print(figuraFibonacci +"\n");
			nRiga++;
		}
	}
}
  