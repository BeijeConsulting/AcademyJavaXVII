package it.beije.xvii.exercises.giampaoli;

public class MediaMultipliDiTre {

	public static void main(String args[])   {
		
		int array[] = {1, 3, 5, 7, 1, 3, 6, 300};
		int totale = 0;
		int nNumeri = 0;
		
		for (int cont = 0; cont < array.length; cont++) {
			if (array[cont] % 3 == 0) {
				totale = totale + array[cont];
				nNumeri += 1;
			}
			
		}
		
		System.out.println("La media dei numeri divisibili per 3 è: " + (totale / nNumeri));
		
		
	}
}
