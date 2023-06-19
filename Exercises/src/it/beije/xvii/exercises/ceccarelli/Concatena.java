package it.beije.xvii.exercises.ceccarelli;
import java.util.Scanner;
public class Concatena {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("inserisci tre stringhe:");
		StringBuilder s = new StringBuilder();
		for(int i=0;i<3;i++) {
			s.append(scan.next());
			if(i!=2) {
			s.append("*");
			}
		}
		System.out.println(s);
	}

}
