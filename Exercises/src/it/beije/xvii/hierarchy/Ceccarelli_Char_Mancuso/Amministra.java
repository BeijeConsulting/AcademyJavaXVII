package it.beije.xvii.hierarchy.Ceccarelli_Char_Mancuso;

public interface Amministra {
	 public default void firmaDocumento(String doc) {
	     	System.out.println("Sto firmando il seguente documento : " + doc);
	     }
}
