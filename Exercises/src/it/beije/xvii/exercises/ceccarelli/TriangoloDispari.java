package it.beije.xvii.exercises.ceccarelli;

public class TriangoloDispari {
	//Dato il seguente triangolo di numeri dispari:                    
	//1
	//3       5
	//7        9        11
	//13     15       17       19
	//21     23     25       27     29
	//….
	//si scriva la funzione rowSumOddNumbers(int n) che calcola la somma dei numeri nella riga n-esima. Per esempio:
	//rowSumOddNumbers(1) = 1
	//rowSumOddNumbers(3) = 7 + 9 + 11 = 27
	//ecc.
	
	public int rowSumOddNumbrs(int n) {
		int count =1;
		int somma=0;
		for(int i =0;i<10;i++) {
				for (int y=0;y<i;y++){
					
					if(count%2!=0) {
						//System.out.print(count);
						//System.out.print(" ");
						if(i==n) {
						somma += count;
					}
					}else {
						y--;
					}
					
					count+=1;
				}
		}
		return somma;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count =1;
		for(int i=0;i<10;i++) {
			for(int y=0;y<i;y++) {
				if(count%2!=0) {
					System.out.print(count);
					System.out.print(" ");
				}else {
					y--;
				}
				count+=1;
				
			}
			
			System.out.println();
		}
		System.out.println();
		int n = 5;
		
		TriangoloDispari t = new TriangoloDispari();
		System.out.println(t.rowSumOddNumbrs(n));
	}

}
