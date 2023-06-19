package it.beije.xvii.exercises.ceccarelli;
import java.util.Scanner;
public class Tabellina {

	public Tabellina() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("Scrivi numero: ");
		int n = scan.nextInt();
		for(int i=0;i<=10;i++) {
			System.out.println(n*i);
		}

	}

}
