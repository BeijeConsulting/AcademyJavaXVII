package esercitazioni;
import java.util.Scanner;

public class PartitaMain {
	public static void main(String[]args) {
		System.out.println("inserisci i valori richiesti");
		Scanner ts = new Scanner(System.in);
		System.out.println("nome prima squadra: ");
		String nomePS = ts.next();
		System.out.println("goal prima squadra: ");
		int goalPS = Integer.valueOf(ts.next());
		PartitaSquadra ps1 = new PartitaSquadra(nomePS, goalPS);
		System.out.println("nome prima squadra: ");
		String nomeSS = ts.next();
		System.out.println("goal prima squadra: ");
		int goalSS = Integer.valueOf(ts.next());
		PartitaSquadra ps2 = new PartitaSquadra(nomePS, goalPS);
		
		System.out.println("calcolando i risultati...");
		
		if(ps1.getGoal()==ps2.getGoal()) {
			System.out.println("pareggio");
		} else if(ps1.getGoal()>ps2.getGoal()) {
			System.out.println("vince ps1 con "+ps1.getGoal()+" goal");
		} else if(ps2.getGoal()>ps1.getGoal()) {
			System.out.println("vince ps2 con "+ps2.getGoal()+" goal");
		}
		
	}
}
