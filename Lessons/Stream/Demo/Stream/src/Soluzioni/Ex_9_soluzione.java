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
	
	public static Map<Persona, String> personsByCity(List<Persona> inputList) {
        return inputList.stream() // crea uno stream a partire dalla lista di input "inputList"
        		.collect( //  il metodo collect serve per raccogliere gli elementi dello stream in una mappa
        				Collectors.toMap( // crea una mappa in cui le chiavi sono le persone e i valori sono le città corrispondenti
        						p -> p, p -> p.getCitta())
        				);
    }

    public static Map<String, List<Persona>> personsByCityOfSea(List<Persona> inputList) {
        return inputList.stream()//  // crea uno stream a partire dalla lista di input "inputList"
        		.collect(
        				Collectors.groupingBy( // raggruppa le persone in base alla città delle persone
        						p -> p.getCitta())
        				); // La mappa risultante ha le città come chiavi e le liste di persone che vivono in ciascuna città come valori
    }
}
