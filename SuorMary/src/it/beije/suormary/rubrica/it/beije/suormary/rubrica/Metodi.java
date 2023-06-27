package it.beije.suormary.rubrica;
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Metodi{
	
	//public List<Contatto> loadRubricaFromCSV(String pathFile, String separator) 
	//aperture file rubricaCSV passandogli path e separatore
	
	public List<Contact> loadRubricaFromCSV(String pathFile, String separator) throws Exception {

		File file = new File(pathFile);  //apertura file da pathFile
		
		FileReader fileReader = new FileReader(file);			
		BufferedReader bufferedReader = new BufferedReader(fileReader); 	 
		List<String> rows = new ArrayList<String>();			//"salvo" file in arraylist di stringhe	
		List<Contact> contacts = new ArrayList<Contact>();		//creo lista contatti
		Contact c = null;										
		while(bufferedReader.ready()) {								
			String r = bufferedReader.readLine();
			rows.add(r);
			for (String row : rows) {								//per ogni stringa in arraylist rows leggo la stringa
				String[] cont = row.split(separator);				//separate da separator
				c = new Contact();									//creo contatto
				c.setSurname(cont[0]);								//SETTO LE VARIE INFO
				c.setName(cont[1]);
				c.setPhoneNumber(cont[2]);
				c.setEmail(cont[3]);
				c.setNote(cont[4]);
				contacts.add(c);									//aggiungo contatto a lista contatto
			}
		}	
		fileReader.close();
		bufferedReader.close();
		return contacts;
	}
	
	
	public List<Contact> loadRubricaFromXML(String pathFile) throws Exception {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document rubrica = documentBuilder.parse("Users/marty/Desktop/Marti/Beije/Esercizi Academy/FILE ESERCIZI/rubrica.xml");
		
		Element docEl = rubrica.getDocumentElement();
		List<Element> cont = getChildElements(docEl);		
		List<Contact> contactsList = new ArrayList<Contact>();
		List<Element> atrCont = null;
		Contact c = null;
		for (Element elCon : cont) {				//elemento per ogni contatto 
			atrCont = getChildElements(elCon);		//prendo attributi di ogni contatto
			c = new Contact();
			for(Element e : atrCont) {				//elemento per ogni attr
				switch(e.getTagName()) {								//setto info
				case "nome": c.setName(e.getTextContent());
				break;
				case "cognome": c.setSurname(e.getTextContent());
					break;
				case "telefono": c.setPhoneNumber(e.getTextContent());
					break;
				case "email": c.setEmail(e.getTextContent());
					break;
				case "note": c.setNote(e.getTextContent());
					break;
				default: System.out.println("TagName non riconosciuto!");
					break; 
				}
			}
		}
		
		
		return null;
	}
	
	public static List<Element> getChildElements(Element el) {						//trovo elementi figlio contatto
		NodeList nodeList = el.getChildNodes();
		List<Element> elements = new ArrayList<Element>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			if (n instanceof Element) elements.add((Element) n);
		}
		return elements;
	}

	public static void main(String[] args) {

	}

}
