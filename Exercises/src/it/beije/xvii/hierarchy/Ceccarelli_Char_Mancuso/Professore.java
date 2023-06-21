package it.beije.xvii.hierarchy.Ceccarelli_Char_Mancuso;

public abstract class Professore extends PersonaleScolastico implements Insegna, Sostituisce {
	
	public boolean diRuolo;
	
	public Professore() {
		// TODO Auto-generated constructor stub
	}
	
	public void sostituisci() {
		System.out.println("Sto facendo supplenza");
	}

}
