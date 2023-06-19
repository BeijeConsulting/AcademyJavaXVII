package it.beije.xvii.exercises.mancuso;

import java.util.Scanner;

/*
 * Scrivere un programma che chieda agli utenti due stringhe in ingresso, le stringhe possono 
 * valere solo: “carta”, “forbice” o “sasso”. Il programma dovrà quindi effettuare i dovuti
 * controlli e dichiarare il vincitore secondo le note regole della “morra cinese” 
 * (forbice vince su carta, carta vince su sasso, sasso vince su forbice).
 */

public class CartaForbiceSasso {

	private String player1;
	private String player2;
	
	public final String carta = "carta";
	public final String forbice = "forbice";
	public final String sasso = "sasso";
	
	public void setPlayer1(String mossa) {
		this.player1 = mossa;
	}
	
	public String getPlayer1() {
		return this.player1;
	}
	
	public void setPlayer2(String mossa) {
		this.player2 = mossa;
	}
	
	public String getPlayer2() {
		return this.player2;
	}
	
	public String play() {
		String p1 = this.getPlayer1();
		String p2 = this.getPlayer2();
		
		switch(p1) {
		case "sasso":
			if(p2.equals(carta)) {
				return "Vince: Giocatore 2";
			}else {
				if(p2.equals(forbice)) {
					return "Vince: Giocatore 1";
				}else {
					return "Pareggio";
				}
			}
		case "forbice":
			if(p2.equals(carta)) {
				return "Vince: Giocatore 1";
			}else {
				if(p2.equals(forbice)) {
					return "Pareggio";
				}else {
					return "Vince: Giocatore 2";
				}
			}
		case "carta":
			if(p2.equals(carta)) {
				return "Pareggio";
			}else {
				if(p2.equals(forbice)) {
					return "Vince: Giocatore 2";
				}else {
					return "Vince: Giocatore 1";
				}
			}
		default:
			return "Errore";
		}	
	}
	
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		
		CartaForbiceSasso game = new CartaForbiceSasso();
		boolean inputCheck = false;
		
		while(!inputCheck) {
			System.out.println("Mossa del giocatore 1: ");
			String s = reader.nextLine().toLowerCase();	
			if(s.equals(game.sasso) || s.equals(game.carta) || s.equals(game.forbice)) {
				game.setPlayer1(s);
				inputCheck=true;
			}
		}
		
		inputCheck=false;
		
		while(!inputCheck) {
			System.out.println("Mossa del giocatore 2: ");
			String s = reader.nextLine().toLowerCase();	
			if(s.equals(game.sasso) || s.equals(game.carta) || s.equals(game.forbice)) {
				game.setPlayer2(s);
				inputCheck=true;
			}
		}
		
		String outcome = game.play();
		
		System.out.println("Risultato: " + outcome);
		
		reader.close();
	}

}
