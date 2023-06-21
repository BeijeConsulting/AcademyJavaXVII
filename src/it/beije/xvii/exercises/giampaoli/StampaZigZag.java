package it.beije.xvii.exercises.giampaoli;

public class StampaZigZag {

	public static void main(String args[])   {
		
		int array[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int contat = 0;
		int tatnoc = array.length - 1;
		boolean turno = true;
		
		for (int con = 0; con < array.length; con++) {
			if (contat != tatnoc) {
				if (turno) {
					System.out.print(array[contat]);
					turno = false;
					contat++;
				} else {
					System.out.print(array[tatnoc]);
					turno = true;
					tatnoc--;
				}
								
			} else {
				if (turno) {
					System.out.print(array[contat]);
					turno = false;
					contat++;
				} else {
					System.out.print(array[tatnoc]);
					turno = true;
					tatnoc--;
			}
		}
	}
  }
}
