package it.beije.xvii.hierarchy.Ceccarelli_Char_Mancuso;

public interface Amministra {
	
	public default void firmaDocumento(String documento) {
		System.out.println("Sto firmando il seguente documento: " + documento);
	};

}
