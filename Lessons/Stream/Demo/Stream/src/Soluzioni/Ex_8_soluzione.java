package Soluzioni;

import java.util.List;
import java.util.stream.Collectors;

import Esercizi.Citta;

/*
 *Dopo aver aggiunto degli attributi (String nome,boolean capoluogo,boolean diMare)
 *alla classe Citta' con i metodi getter e setter: 
 *1- Utilizzare lo stream per raggiuppare tutte le citta di mare e raccogli in una nuova lista gli elementi filtrati.
 *	 Esempio: [Citta("Roma", true, false), Citta("Milano", false, false), Citta("Napoli", true, true)] -> 
 *					   [Citta("Napoli", true, true)]
 *
 *2- Utilizzare lo stream per raggruppare tutte le citta' capoluogo e raccogli in una nuova lista gli elementi filtrati.
 *	 Esempio: [Citta("Roma", true, false), Citta("Milano", true, false), Citta("Musa Manola", false, true)] -> 
 *	   		  [Citta("Roma", true, false), Citta("Milano", true, false)]
 *
 *3- Utilizzare lo stream per stampare tutte le citta' che finscono con la lettera 'a' e raccogli in una nuova lista gli elementi filtrati.
 *	 Esempio: [Citta("Roma", true, false), Citta("Milano", true, false)] -> [Citta("Roma", true, false)]
 *
 * */

public class Ex_8_soluzione {
	// soluzione commentata in quanto deve essere compilata la classe "Citta"
	
/*	public static List<Citta> seaCities(List<Citta> inputList) {
		return inputList.stream().filter(c -> c.diMare()).collect(Collectors.toList());
	}

	public static List<Citta> listOfCities(List<Citta> inputList) {
		return inputList.stream().filter(c -> c.capoluogo()).collect(Collectors.toList());
	}

	public static List<Citta> cities(List<Citta> inputList) {
		return inputList.stream().filter(c -> c.getNome()).endsWith('a').collect(Collectors.toList());
	}
	
	*/
	
}
