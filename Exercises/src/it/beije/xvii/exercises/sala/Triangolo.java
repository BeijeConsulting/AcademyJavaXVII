package it.beije.xvii.exercises.sala;

/*
 * Dato il seguente triangolo di numeri dispari:                    1
1
3       5
7        9        11
13     15       17       19
21     23     25       27     29
….
si scriva la funzione rowSumOddNumbers(int n) che calcola la somma dei numeri nella riga n-esima. Per esempio:
rowSumOddNumbers(1) = 1
rowSumOddNumbers(3) = 7 + 9 + 11 = 27
 */
public class Triangolo {
	
	
	public static void main(String[] args) {
		//System.out.println(calcolaSommaDispari(5));
		
		int n = 1;
				/*for(int i=0; i<4; i++) {
					for(int j=0; j<=i; j++) {
					System.out.print(n+" ");
					n=n+2;
					}
					System.out.println();
				}*/
				
			//la prima dim indica le righe e la seconda le colonne
				//salvo gli elementi in un array bidimensionale
			
			
			//System.out.println(mat[0][0]);
			//System.out.println(mat[1][0]);
			//System.out.println(mat[1][1]);
			
			System.out.println(rowSumOddNumbers(2));
			
	}
	
	//somma di tutti i dispari fino a un certo numero
	/*public static int calcolaSommaDispari(int n) {
		int somma;
		if(n==1) {
			somma=1;
		} else {
			somma=n+calcolaSommaDispari(n-2);
		}
		return somma;	
	}
	*/
	public static int rowSumOddNumbers(int n) {
		//se io conosco la riga so quanti elementi devo sommare su quella riga
		int num =1;
		int[][] mat = new int[n][n];	
		for(int i=0; i<n; i++) {
			for(int j=0; j<=i; j++) {
				mat[i][j]=num;
				num=num+2;
				//System.out.print(mat[i][j]);
			}
			//System.out.println();
		}
		
	
		
		int somma=0;
		for(int j=0; j<n; j++) {
			somma=somma+mat[n-1][j];
		}
		return somma;
	}

}
