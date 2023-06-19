package it.beijeit.xvii.exercises.sala;
import java.util.Scanner;
/*
 * Scrivere un programma che chieda agli utenti due stringhe in ingresso, le stringhe possono valere 
 * solo: “carta”, “forbice” o “sasso”. Il programma dovrà quindi effettuare i dovuti controlli e 
 * dichiarare il vincitore secondo le note regole della “morra cinese” (forbice vince su carta, carta 
 * vince su sasso, sasso vince su forbice).
 */
public class StringheEs8D {

	public static void main(String[] args) {
		Scanner ts = new Scanner(System.in);
		System.out.println("inserire valore primo giocatore");
		String primogiocatore = ts.next();
		String secondogiocatore;
		boolean corretto=false;

		do {
			if(primogiocatore.equals("forbice") || primogiocatore.equals("sasso") || 
					primogiocatore.equals("carta")) {
				corretto=true;
			} else {
				System.out.println("valore incorretto, riprovare");
				primogiocatore = ts.next();
			
		}
	}while(!corretto);
		
		System.out.println("valore primo giocatore corretto! inserire valore secondo giocatore");
		secondogiocatore = ts.next();
		
		corretto = false;
		
		do {
			if(secondogiocatore.equals("forbice") || secondogiocatore.equals("sasso") || 
					secondogiocatore.equals("carta")) {
				corretto=true;
			} else {
				System.out.println("valore incorretto, riprovare");
				secondogiocatore = ts.next();
			
		}
	}while(!corretto);
		
	
	System.out.println("valore secondo giocatore corretto!");
	
	
	if(primogiocatore.equals(secondogiocatore)) {
		System.out.println("pari");
	} else if(primogiocatore.equals("forbice") && secondogiocatore.equals("carta")) {
		System.out.println("vince primo giocatore");
	} else if(secondogiocatore.equals("forbice") && primogiocatore.equals("carta")) {
		System.out.println("vince secondo giocatore");
	} else if(primogiocatore.equals("carta") && secondogiocatore.equals("sasso")) {
		System.out.println("vince primo giocatore");
	} else if(secondogiocatore.equals("carta") && primogiocatore.equals("sasso")) {
		System.out.println("vince secondo giocatore");
	} else if(primogiocatore.equals("sasso") && secondogiocatore.equals("forbice")) {
		System.out.println("vince primo giocatore");
	} else if(secondogiocatore.equals("sasso") && primogiocatore.equals("forbice")) {
		System.out.println("vince secondo giocatore");
	} else {
		System.out.println("nessun vincitore");
	}
	
	ts.close();
	
	}
}
