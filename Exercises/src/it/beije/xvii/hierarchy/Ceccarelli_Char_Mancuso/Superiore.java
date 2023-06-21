package it.beije.xvii.hierarchy.Ceccarelli_Char_Mancuso;

public class Superiore extends Studente implements Insegna, Guida{
    public void insegna() {
    	System.out.println("Sto facendo ripetizioni");
    }
    public void studio() {
		System.out.println("Sono al " + annoAccademico + " delle superiori e l a mia scuola si chiama " + nomeIstituto);
	}
}
