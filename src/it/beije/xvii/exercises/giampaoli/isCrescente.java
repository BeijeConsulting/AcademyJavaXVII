package it.beije.xvii.exercises.giampaoli;

public class isCrescente {

	static boolean isCrescente(int [] array) {
		
		int max = 0;
		
		for (int con = 0; con < array.length; con++) {
			if (max < array[con]) {
				max = array[con];
			} else {
				return false;
			}
		}
		return true;
	}
	
	
	
	public static void main(String args[])   {
		
		int array[] = {1, 2, 3,4,5,6,7,8,13,9};
		if (isCrescente(array) == true) {
			System.out.print("L'array è ordinato in modo crescente");
		} else {
			System.out.print("L'array NON è ordinato in modo crescente");
		}
	}
}
