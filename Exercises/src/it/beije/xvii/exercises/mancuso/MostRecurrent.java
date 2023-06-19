package it.beije.xvii.exercises.mancuso;

/*
 * Scrivere il metodo: “public int mostRecurrent(int [] array)” , 
 * che trova l’elemento più ricorrente in un array. Il metodo restituisce l’elemento trovato.
 */

public class MostRecurrent {
	
	public static int mostRecurrent(int[] array) {
		int length = array.length;
		
		int[][] occurrences = new int[6][2];
		
		for(int i=0; i<length; i++) {
			occurrences[i][0] = -1;
		}
		
		for(int i=0; i<length; i++) {
			boolean found = false;
			for(int j=0; j<6 && !found; j++) {	
				if(occurrences[j][0] == array[i]) {
					occurrences[j][1] += 1;
					found = true;
				}
			}
			if(!found) {
				int idx = -1;
				int k = 0;
				while(idx == -1) {
					if(occurrences[k][0] == -1) {
						idx = k;
					}
					k++;
				}
				occurrences[idx][0] = array[i];
				occurrences[idx][1] = 1;
			}
		}
		
		int max = 0;
		int num = -1;
		
		for(int i=0; i<length; i++) {
			if(occurrences[i][1]>max) {
				max = occurrences[i][1];
				num = occurrences[i][0];
				
			}
		}
		
		return num;
	}
	
	public static void main(String[] args) {
		int[] numeri = {2,2,3,2,1,1};
		
		int max = MostRecurrent.mostRecurrent(numeri);
		
		System.out.println(max);
	}

}
