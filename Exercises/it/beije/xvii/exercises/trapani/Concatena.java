package it.beije.xvii.exercises.trapani;
import java.util.Scanner;

class Concatena {

	public static void main(String[] args) {
		StringBuilder str = new StringBuilder();
		Scanner in = new Scanner(System.in);
		for(int i=0; i<3; i++) {
		System.out.println("Inserisci parola " + (i+1) + " :");
		str.append(in.nextLine());
		if(i!=2) str.append('*');
		}
		
		System.out.println(str);

	}

}
