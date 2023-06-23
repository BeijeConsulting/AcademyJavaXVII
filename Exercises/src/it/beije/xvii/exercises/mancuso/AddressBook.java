package it.beije.xvii.exercises.mancuso;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;


public class AddressBook {
	
	public List<Contact> contacts;

	private static List<String[]> readCSV(File file, String separator) throws IOException{
		FileReader fReader = new FileReader(file);
		BufferedReader bReader = new BufferedReader(fReader);
		List<String[]> csv = new ArrayList<>();
		
		while(bReader.ready()) {
			String line = bReader.readLine();
			String[] values = line.split(separator, -1);
			csv.add(values);
		}
		
		bReader.close();
		fReader.close();
		
		return csv;
	}
	
	// Da debuggare
	
	public List<Contact> loadAddressesFromCSV(String pathFile, String separator) throws Exception{
		
		File file = new File(pathFile);
		
		if(file.exists()) {
			if(!file.isDirectory()) {
				
				String filename = file.getName();

				
				if(filename.contains(".csv")) {
					
					List<Contact> newContacts = new ArrayList<Contact>();
					List<String[]> csv = readCSV(file, separator);
					
					Contact c = null;
					for(int i=1; i<csv.size(); i++) {
						c = new Contact();
						c.setFirstName(csv.get(i)[0]);
						c.setLastName(csv.get(i)[1]);
						c.setPhoneNumber(csv.get(i)[2]);
						c.setEmail(csv.get(i)[3]);
						c.setNotes(csv.get(i)[4]);
						
						newContacts.add(c);
					}
					return newContacts;
				}
				throw new Exception("Specified file is not a .CSV.");
			}
			throw new Exception("File path leads to a directory.");
		}	
		throw new Exception("File does not exist.");
		
	}
	
	public static List<Element> getChildrenElements(Element el) {
		NodeList nodeList = el.getChildNodes();
		//System.out.println("nodeList size: " + nodeList.getLength());
		List<Element> elements = new ArrayList<Element>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			if (n instanceof Element) elements.add((Element) n);
		}
		
		return elements;
	}
	
	public List<Contact> loadAddressesFromXML(String pathFile) throws Exception{
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse("/Temp/rubrica.xml");
		
		Element rootElement = document.getDocumentElement();
		
		List<Element> elements = getChildrenElements(rootElement);	
		List<Contact> contacts = new ArrayList<>();
		List<Element> innerElements;
		
		for(Element e : elements) {
			
			innerElements = getChildrenElements(e);
			Contact c = null;
			
			for(Element inEl : innerElements) {
				c = new Contact();
				
				switch(e.getTagName()) {
				
				case "nome":
					c.setFirstName(e.getTextContent());
					break;
				case "cognome":
					c.setLastName(e.getTextContent());
					break;
				case "telefono":
					c.setPhoneNumber(e.getTextContent());
					break;
				case "email":
					c.setEmail(e.getTextContent());
					break;
				case "note":
					c.setNotes(e.getTextContent());
					break;
				default:
					System.out.println("Elemento non riconosciuto.");
					break;				
				}
				
			}
			contacts.add(c);
		}
		return contacts;
	}
	
	public void writeAddressBook(String pathFile, String separator) {
		return;
	}
	
	public String toString() {
		String myString = "";
		for(Contact c : contacts) {
			myString += c.toString();
			myString += "\n";
		}
		return myString;
	}
	
	public static void main(String[] args) throws Exception {
		AddressBook addressBook = new AddressBook();
		
		List<Contact> newContacts = addressBook.loadAddressesFromCSV("/Temp/addressBook.csv", ";");
		addressBook.contacts = newContacts;
		
		System.out.println(addressBook.toString());
		
		newContacts = addressBook.loadAddressesFromXML("/Temp/rubrica.xml");
		addressBook.contacts = newContacts;
		
		System.out.println(addressBook.toString());
	}
	
	
}
