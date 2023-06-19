package it.beije.xvii.exercises.trapani;
import java.util.Scanner;

public class MorraCinese {

	public static void main(String[] args) {
		String[] p = new String[2];
		Scanner in = new Scanner(System.in);
		
		for(int i=0; i<p.length; i++) {
			System.out.println("Giocatore " + (i+1) + ":");
			p[i] = in.nextLine();
			if((p[i].equalsIgnoreCase("carta")) || (p[i].equalsIgnoreCase("forbice")) || (p[i].equalsIgnoreCase("sasso"))){}
			else {
				System.out.println("ERRORE GIOCATORE " + (i+1));
				break;
			}
		}
		if(((p[0].equalsIgnoreCase("carta")) && (p[1].equalsIgnoreCase("sasso"))) || 
			((p[0].equalsIgnoreCase("forbice")) && (p[1].equalsIgnoreCase("carta"))) ||
			((p[0].equalsIgnoreCase("sasso")) && (p[1].equalsIgnoreCase("forbice")))) {
			System.out.println("Giocatore 1 vince");
		}
		if(((p[1].equalsIgnoreCase("carta")) && (p[0].equalsIgnoreCase("sasso"))) || 
				((p[1].equalsIgnoreCase("forbice")) && (p[0].equalsIgnoreCase("carta"))) ||
				((p[1].equalsIgnoreCase("sasso")) && (p[0].equalsIgnoreCase("forbice")))) {
				System.out.println("Giocatore 2 vince");
		}
	
		if((p[0].equalsIgnoreCase(p[1]))) {
			System.out.println("Parita'");
		}
		
	}
		
}
