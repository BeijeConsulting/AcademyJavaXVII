package it.beije.xvii.hierarchy.Ceccarelli_Char_Mancuso;

public class Universitario extends Studente implements Insegna, Guida{
	public void insegna() {
		System.out.println("Sto facendo ripetizioni.");
	}
	public void studio() {
		System.out.println("Sono al " + annoAccademico + " anno dell'universita' e la mia scuola si chiama " + nomeIstituto);
	}
}
