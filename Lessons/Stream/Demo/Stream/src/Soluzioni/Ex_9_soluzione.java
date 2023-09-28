package Soluzioni;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Demo.Persona;
import Esercizi.Citta;

/*
 * Raggruppare, utilizzando lo stream(), le persone in base all'attributo citta'
 * Raggruppare le persone in base all'attributo booleano diMare della classe Citta creata precedentemente utilizzando lo stream()
 * */

public class Ex_9_soluzione {
	
	 public static Map<Persona, Citta> personsByCity(List<Persona> inputList) {
	        return inputList.stream().collect(Collectors.toMap(p -> p, p -> p.getCitta()));
	    }

	    public static Map<Citta, List<Persona>> personsByCityOfSea(List<Persona> inputList) {
	        return inputList.stream().collect(Collectors.groupingBy(p -> p.getCitta()));
	    }

}
