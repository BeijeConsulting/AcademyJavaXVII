package esercitazioni;
import java.util.Scanner;
public class SquadraMain {
	
	public static void main(String[]args) {
		Scanner ts = new Scanner(System.in);
		System.out.println("inserisci i valori richiesti");
		System.out.println("nome: ");
		String name = ts.next();
		System.out.println("cognome: ");
		String surname = ts.next();
		System.out.println("squadra: ");
		String squadra = ts.next();
		
		SquadraUtente s = new SquadraUtente(name, surname, squadra);
		System.out.print(s.toString());
		ts.close();
	}
	
	
	
}
