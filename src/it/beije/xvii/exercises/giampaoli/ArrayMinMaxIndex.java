package it.beije.xvii.exercises.giampaoli;
import java.util.*;

public class ArrayMinMaxIndex {

	public static void main(String args[])   {
		
		int array[] = {3, 2, 5, 7, 1, 0, 3, 120, 9, 5000};
		int maxMin = 0;
		boolean primo = true;
		int indMinMax = 0;
		String mod = "";
		
		
		Scanner sc = new Scanner(System.in); 
		System.out.print("Scrivere 'M' per trovare il massimo e 'm' per trovare il minimo: ");  
		mod = sc.nextLine().trim();
	
		
		if (mod.equals("M"))  {
			for (int cont = 0; cont < array.length; cont ++) {
				if (maxMin < array[cont]) {
					maxMin = array[cont];
					indMinMax = cont;
				}
				
			}
		} else if (mod.equals("m")) {
			for (int cont = 0; cont < array.length; cont ++) {
				if (primo == true) {
					maxMin = array[cont];
					indMinMax = cont;
					primo = false;
				}
				
				if (maxMin > array[cont]) {
					maxMin = array[cont];
					indMinMax = cont;
				}
				
			}
		} else {
			System.out.println("Input errato");
			System.exit(0);
		}
		
		System.out.print("L'indice del numero " + ((mod.equals("m")) ? "minimo" : "massimo") + " è: " + indMinMax);
	
	
	
	
	
	
	}
}
