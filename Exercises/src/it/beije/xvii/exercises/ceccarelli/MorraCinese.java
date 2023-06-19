package it.beije.xvii.exercises.ceccarelli;

import java.util.Scanner;

public class MorraCinese {
	//Scrivere un programma che chieda agli utenti due stringhe in ingresso, 
	//le stringhe possono valere solo: “carta”, “forbice” o “sasso”. 
	//Il programma dovrà quindi effettuare i dovuti controlli e dichiarare 
	//il vincitore secondo le note regole della “morra cinese” 
	//(forbice vince su carta, carta vince su sasso, sasso vince su forbice).

	public MorraCinese() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("inserisci due stringhe: ");
		String s1 = scan.next();
		String s2 = scan.next();
		//System.out.println(s1);
		if(!(s1.equals("carta")) || (s1.equals("forbice"))|| (s1.equals("sasso"))) {
			System.out.println("mossa s1 non valida");
			return;
		
		}else if(!s2.equals("carta") || s2.equals("forbice") || s2.equals("sasso")) {
			System.out.println("mossa s2 non valida");
			return;
		}
		
		if(s1.equals("forbice") && s2.equals("carta") || s1.equals("carta") && s2.equals("sasso") || s1.equals("sasso") && s2.equals("forbice")) {
			System.out.println("Ha vinto il giocatore s1!");
		}else if(s1.equals(s2)) {
			System.out.println("PAREGGIO!");
		}else {
			System.out.println("Ha vinto il giocatore s2!");
		}
		

	}

}
