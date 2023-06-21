package it.beije.xvii.hierarchy.Ceccarelli_Char_Mancuso;

public class Universitario extends Studente implements Guida, Insegna{

	public Universitario() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insegna() {
		System.out.println("Sto facendo ripetizioni");
		
	}
	
	@Override
	public void studio() {
		// TODO Auto-generated method stub
		System.out.println("Sono all'anno accademico " + annoAccademico + "dell'università e la mia università si chiama" + nomeIstituto);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
