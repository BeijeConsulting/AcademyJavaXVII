package it.beije.xvii.exercises.giampaoli;
import java.util.Scanner;

public class ArrayMinMaxElement {
	
	public static void main(String args[])   {
		
	
		int array[] = {1, 2, 5, 7, 1, 3, 120, 9};
		int maxMin = 0;
		boolean primo = true;
		String mod = "";
		
		Scanner sc = new Scanner(System.in); 
		System.out.print("Scrivere 'M' per trovare il massimo e 'm' per trovare il minimo: ");  
		mod = sc.nextLine().trim();
		
		if (mod.equals("M"))  {
			for (int cont = 0; cont < array.length; cont ++) {
				if (maxMin < array[cont]) {
					maxMin = array[cont];
				}
				
			}
		} else if (mod.equals("m")) {
			for (int cont = 0; cont < array.length; cont ++) {
				if (primo = true) {
					maxMin = array[cont];
					primo = false;
				}
				if (maxMin > array[cont]) {
					maxMin = array[cont];
				}
				
			}
		} else {
			System.out.println("Input errato");
			System.exit(0);
		}
		
		System.out.print("Il numero " + ((mod.equals("m")) ? "minimo" : "massimo") + " è: " + maxMin );
		
	}
}