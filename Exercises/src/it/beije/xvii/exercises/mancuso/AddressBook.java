package it.beije.xvii.exercises.mancuso;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
		Contact c = null;
		for(Element e : elements) {
			
			innerElements = getChildrenElements(e);
			
			c = new Contact();
			for(Element inEl : innerElements) {
							
				switch(inEl.getTagName()) {
				
				case "nome":
					c.setFirstName(inEl.getTextContent());
					break;
				case "cognome":
					c.setLastName(inEl.getTextContent());
					break;
				case "telefono":
					c.setPhoneNumber(inEl.getTextContent());
					break;
				case "email":
					c.setEmail(inEl.getTextContent());
					break;
				case "note":
					c.setNotes(inEl.getTextContent());
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
	
	public void writeAddressBookCSV(String pathFile, String separator, List<String> rows) {
		return;
	}
	
	public void writeAddressBookXML(List<Contact> contacts) throws ParserConfigurationException, TransformerException {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		
		Element root = document.createElement("addressBook");
		document.appendChild(root);
		
		Element el = null;
		
		for (Contact c : contacts) {
			el = document.createElement("contact");
			
			if (c.getFirstName() != null) {
				Element name = document.createElement("name");
				name.setTextContent(c.getFirstName());
				el.appendChild(name);
			}
			if (c.getLastName() != null) {
				Element surname = document.createElement("surname");
				surname.setTextContent(c.getLastName());
				el.appendChild(surname);
			}
			if (c.getPhoneNumber() != null) {
				Element phoneNumber = document.createElement("phone");
				phoneNumber.setTextContent(c.getPhoneNumber());
				el.appendChild(phoneNumber);
			}
			if (c.getEmail() != null) {
				Element email = document.createElement("email");
				email.setTextContent(c.getEmail());
				el.appendChild(email);
			}
			if (c.getNotes() != null) {
				Element note = document.createElement("note");
				note.setTextContent(c.getNotes());
				el.appendChild(note);
			}
			
			root.appendChild(el);
			
		}
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
				
		StreamResult result = new StreamResult(new File("/temp/contacts.xml"));

		// Output to console for testing
		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source, syso);

		//System.out.println("File saved!");
		
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
		
		//List<Contact> newContacts = addressBook.loadAddressesFromCSV("/Temp/addressBook.csv", ";");
		//addressBook.contacts = newContacts;
		
		//System.out.println(addressBook.toString());
		
		List<Contact> newContacts = addressBook.loadAddressesFromXML("/Temp/rubrica.xml");
		addressBook.contacts = newContacts;
		
		addressBook.writeAddressBookXML(addressBook.contacts);
		
		//System.out.println(addressBook.toString());
	}
	
	
}
