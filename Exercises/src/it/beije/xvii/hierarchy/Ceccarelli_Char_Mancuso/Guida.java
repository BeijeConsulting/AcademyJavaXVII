package it.beije.xvii.hierarchy.Ceccarelli_Char_Mancuso;

public interface Guida extends Amministra {
	public default void guidando() {
		System.out.println("Sto guidando");
	}

}
