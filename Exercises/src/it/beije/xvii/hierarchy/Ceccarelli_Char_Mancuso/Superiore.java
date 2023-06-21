package it.beije.xvii.hierarchy.Ceccarelli_Char_Mancuso;

public class Superiore extends Studente implements Guida{

	public Superiore() {
		// TODO Auto-generated constructor stub
	}
	
	public void insegna() {
		System.out.println("Sto facendo ripetizioni");
		
	}
	
	@Override
	public void studio() {
		// TODO Auto-generated method stub
		System.out.println("Sono all'anno accademico " + annoAccademico + "delle superiori e la mia scuola si chiama" + nomeIstituto);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
