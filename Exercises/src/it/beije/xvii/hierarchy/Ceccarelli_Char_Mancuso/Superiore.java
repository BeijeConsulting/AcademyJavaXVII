package it.beije.xvii.hierarchy.Ceccarelli_Char_Mancuso;

public class Superiore extends Studente implements Insegna, Guida{
	public void insegna() {
		System.out.println("Sto facendo ripetizioni.");
	}
	public void studio() {
		System.out.println("Sono al " + annoAccademico + " anno delle superiori e la mia scuola si chiama " + nomeIstituto);
	}
}
