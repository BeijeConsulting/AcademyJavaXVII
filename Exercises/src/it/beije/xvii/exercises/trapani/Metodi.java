package it.beije.xvii.exercises.trapani;
		//Implementate metodi analoghi a questi:
		//
		//public List<Contatto> loadRubricaFromCSV(String pathFile, String separator) {...}
		//public List<Contatto> loadRubricaFromXML(String pathFile) {...}
		//public void writeRubricaCSV(List<Contatto> contatti, String pathFile, String separator) {...}
		//public void writeRubricaXML(List<Contatto> contatti, String pathFile) {...}
		//
		//Dopo i metodi base per la scrittura, fate in modo che se indicate un file xml o csv già esistente, 
		//i nuovi contatti non vadano a sovrascrivere quelli già presenti, bensì vengano aggiunti in coda.
		//
		//Estendere la potenzialità dei metodi aggiungendo 
		//l'interpretazione automatica delle colonne tramite la lettura della prima riga,
		//ovvero distinguere e gestire in modo automatico SURNAME;NAME;TELEPHONE;EMAIL
		//come anche EMAIL;NAME;SURNAME (senza colonna TELEPHONE) 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Metodi{
	
	//public List<Contatto> loadRubricaFromCSV(String pathFile, String separator) 
	//aperture file rubricaCSV passandogli path e separatore
	
	public List<Contatto> loadRubricaFromCSV(String pathFile, String separator) throws Exception {

		File file = new File(pathFile);  //apertura file da pathFile
		
		FileReader fileReader = new FileReader(file);			
		BufferedReader bufferedReader = new BufferedReader(fileReader); 	 
		List<String> rows = new ArrayList<String>();		//"salvo" file in arraylist di stringhe	
		while(bufferedReader.ready()) {								
			String r = bufferedReader.readLine();
			rows.add(r);
		}
		
		List<Contatto> contact = new ArrayList<Contatto>();		//creo lista contatti
		Contatto c = null;										//creo oggetto contatto punta a null
		for (String row : rows) {								//per ogni stringa in arraylist rows leggo la stringa
			String[] cont = row.split(separator);				//separate da separator
			c = new Contatto();									//creo contatto
			c.setInfoContatto(row);								//SETTO INFO
		contact.add(c);											//aggiungo contatto a lista contatto
		}
			
		fileReader.close();
		bufferedReader.close();
		return contact;
	}
	
	
	

	public static void main(String[] args) {

	}

}
