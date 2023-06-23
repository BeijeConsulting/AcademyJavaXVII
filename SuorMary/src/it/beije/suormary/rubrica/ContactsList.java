package it.beije.suormary.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ContactsList {

	public static List<Contact> loadContactListFromCSV (String pathFile, String separator) throws IOException{
		FileReader fileReader = new FileReader(pathFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String [] contactInfo = null;
		Contact con = null;
		List<Contact> listOfContacts = new ArrayList<>();
 		
		while (bufferedReader.ready()) {
			String line = bufferedReader.readLine();
			 contactInfo = line.split(separator);
			 con = new Contact();
			 con.setName(contactInfo[0]);
			 con.setSurname(contactInfo[1]);
			 con.setPhoneNumber(contactInfo[2]);
			 con.setEmail(contactInfo[3]);
			 con.setNote(contactInfo[4]);
			 listOfContacts.add(con);
			}
		bufferedReader.close();
		fileReader.close();
		
		return listOfContacts;
	}
	
	public static List<Contact> loadContactListFromXML(String pathFile) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(pathFile);
		
		Element docEl = document.getDocumentElement();
		List<Element> elements = getChildElements(docEl);
		
		Contact con = null;
		List<Contact> listOfContacts = new ArrayList<>();
		List<Element> els = null;
		
		for (Element el : elements) {
			els = getChildElements(el);
			con = new Contact();
			for (Element e : els) {
				
				switch (e.getTagName()) {
					case "nome": con.setName(e.getTextContent());
						break;
					case "cognome": con.setSurname(e.getTextContent());
						break;
					case "telefono": con.setPhoneNumber(e.getTextContent());
						break;
					case "email": con.setEmail(e.getTextContent());
						break;
					case "note": con.setNote(e.getTextContent());
						break;
					default: System.out.println("TagName non riconosciuto!");
						break;
				}
			}	
			listOfContacts.add(con);
		}
		return listOfContacts;
	}
	
	public static List<Element> getChildElements(Element el) {
		NodeList nodeList = el.getChildNodes();
		List<Element> elements = new ArrayList<Element>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			if (n instanceof Element) elements.add((Element) n);
		}
		return elements;
	}
	
	public static void writeContactListCSV(List<Contact> listOfContacts, String pathFile, String separator) throws Exception{
		
		FileWriter fileWriter = null;
		if ((new File(pathFile)).exists()) fileWriter = new FileWriter(pathFile, true);
		else fileWriter = new FileWriter(pathFile);

		StringBuilder line = null;
		
		for (Contact con : listOfContacts) {
			line = new StringBuilder(con.getName() + separator + con.getSurname() + separator + con.getPhoneNumber() 
												   + separator + con.getEmail() + separator + con.getNote() + "\n");
			fileWriter.write(line.toString());
			line = null;
		}
		fileWriter.flush();
		fileWriter.close();
		
		System.out.println("Contacts added");
	}
	
	
	public static void main(String[] args) throws Exception{
		
		String pathReadFile = "C:\\Users\\Chiara\\Desktop\\Academy\\esercizi\\rubrica.xml";
		String separator = "|";
		String pathWriteFile = "C:\\Users\\Chiara\\Desktop\\Academy\\esercizi\\primotentativo.csv";
		
		List<Contact> listOfContacts = new ArrayList<>();
		if (pathReadFile.endsWith(".csv")) listOfContacts = loadContactListFromCSV(pathReadFile, separator);
		else if (pathReadFile.endsWith(".xml")) listOfContacts = loadContactListFromXML(pathReadFile);

		writeContactListCSV(listOfContacts, pathWriteFile, separator);	
		
	}

}
