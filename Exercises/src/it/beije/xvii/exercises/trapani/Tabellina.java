package it.beije.xvii.exercises.trapani;
import java.util.Scanner;


public class Tabellina {


	public static void main(String[] args) {
		int ris;
		Scanner in = new Scanner(System.in);
		System.out.println("Inserire numero: \n");
		int num = in.nextInt();
		
		for(int i=1; i<11; i++) {
			ris = i*num;
			System.out.print(ris + " ");
		}
	
	}

}
