package it.beije.suormary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class TestService {
	
	public List<String> getBooks() {
		
		//carico lista da DB....
		List<String> libri = new ArrayList<String>();
		libri.add("I Promessi Sposi");
		libri.add("La Divina Commedia");
		libri.add("Manuale OCA");
		libri.add("Tre metri sopra il cielo");
		libri.add("Guida galattica per programmatori");
		
		return libri;
	}

}
