package it.beije.xvii.exercises.giampaoli;

public class Persistenza {
	
	static int persistenza(int n) {
		
		int nDigit = 1;
		int nTemp = n;
		
		while(nTemp >= 10)  {  
			nTemp = nTemp / 10;  
			nDigit = nDigit + 1;  
		}  
	
	
		int nMolt[] = new int[nDigit];
		
		int num = n;
		int conto = 0;
		int risultato = 1;
		boolean oneDigit = false;
		
		while (oneDigit == false) {
			if (num > 9) {
				
				nMolt[conto] = (num % 10);
				num = num / 10;
				conto += 1;
						
			} else if (num <= 9 && num >=0) {
				oneDigit = true;
				nMolt[conto] = num;
				conto += 1;
			} else if (num < 0) {
				System.out.print("Numero non positivo");
				System.exit(conto);
			}
		}
		
		for (int i = 0; i < conto; i++) {
			risultato = risultato * nMolt[i];
		}
		
		return risultato;
	}
	
	
	public static void main(String args[])  
	{  
		System.out.print(persistenza(22222));
		
	}
	
	
}
