package it.beije.xvii.exercises.ceccarelli;

import java.util.Scanner;

public class Occorrenze {

	public Occorrenze() {
		// TODO Auto-generated constructor stub
		
	}
	public int contaLettera(char c, String str) {
		int count =0;
		for(int i=0; i<str.length();i++) {
			if (str.charAt(i)==c) {
				count+=1;
			}
		}
		return count;
		
	};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("inserisci stringa e carattere: ");
		String s = scan.next();
		char c = scan.next().charAt(0);
		Occorrenze occ = new Occorrenze();
		System.out.println(occ.contaLettera(c, s));

	}

}
