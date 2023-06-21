package it.beije.xvii.exercises.giampaoli;

public class Media {

		public static void main(String args[])   {
			
			int array[] = {1, 3, 5, 7, 1, 3, 6, 300, 400};
			int totale = 0;
			int nNumeri = 0;
			
			for (int cont = 0; cont < array.length; cont++) {
					totale = totale + array[cont];
					nNumeri += 1;				
			}
			
			System.out.println("La media dei numeri è: " + (totale / nNumeri));
			
			
	}

	
}
