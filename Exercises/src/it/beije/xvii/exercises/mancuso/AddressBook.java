package it.beije.xvii.exercises.mancuso;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;


public class AddressBook {
	
	public List<Contact> contacts;

	private static List<String[]> readCSV(File file, String separator){
		FileReader fReader = null;
		List<String[]> csv = new ArrayList<>();
		BufferedReader bReader = null;
		try {
			fReader = new FileReader(file);
			bReader = new BufferedReader(fReader);

			while(bReader.ready()) {
				String line = bReader.readLine();
				String[] values = line.split(separator, -1);
				csv.add(values);
			}		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fReader.close();
				bReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return csv;
	}
	
	public List<Contact> loadAddressesFromCSV(String pathFile, String separator) throws IllegalArgumentException{
		
		File file = null;
		
		file = new File(pathFile);
		
		if(file.exists()) {
			if(!file.isDirectory()) {
				
				String filename = file.getName();

				
				if(filename.contains(".csv")) {
					
					List<Contact> newContacts = new ArrayList<Contact>();
					List<String[]> csv = readCSV(file, separator);
					
					String[] headers = csv.get(0);
					
					int idxFirstName = -1;
					int idxLastName = -1;
					int idxPhone = -1;
					int idxEmail = -1;
					int idxNotes = -1;
					
					for(int i=0;i<headers.length;i++) {
						if(headers[i].toLowerCase().equals("NAME")) {
							idxFirstName = i;
						}
						if(headers[i].toLowerCase().equals("SURNAME")) {
							idxLastName = i;
						}
						if(headers[i].toLowerCase().equals("PHONE")) {
							idxPhone = i;
						}
						if(headers[i].toLowerCase().equals("EMAIL")) {
							idxEmail = i;
						}
						if(headers[i].toLowerCase().equals("NOTES")) {
							idxNotes = i;
						}
					}
					
					Contact c = null;
					for(int i=1; i<csv.size(); i++) {
						c = new Contact();
						if(idxFirstName != -1) {
							c.setFirstName(csv.get(i)[idxFirstName]);
						}
						if(idxLastName != -1) {
							c.setLastName(csv.get(i)[idxLastName]);
						}
						if(idxPhone != -1) {
							c.setPhoneNumber(csv.get(i)[idxPhone]);
						}
						if(idxEmail != -1) {
							c.setEmail(csv.get(i)[idxEmail]);
						}
						if(idxNotes != -1) {
							c.setNotes(csv.get(i)[idxNotes]);
						}				
						newContacts.add(c);
					}
					return newContacts;
				}
				throw new IllegalArgumentException("Specified file is not a .CSV.");
			}
			throw new IllegalArgumentException("File path leads to a directory.");
		}	
		throw new IllegalArgumentException("File does not exist.");
		
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
	
	public List<Contact> loadAddressesFromXML(String pathFile) throws IllegalArgumentException{
		File file = new File(pathFile);
		String filename = file.getName();
		List<Contact> newContacts = null;
		if(file.exists()) {
			if(!file.isDirectory()) {
				if(filename.contains(".xml")) {
					try {
						
						DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
						Document document = documentBuilder.parse("/Temp/rubrica.xml");
						
						Element rootElement = document.getDocumentElement();
						
						List<Element> elements = getChildrenElements(rootElement);	
						newContacts = new ArrayList<>();
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
							newContacts.add(c);
						}
					} catch (ParserConfigurationException e1) {
						e1.printStackTrace();
					} catch (SAXException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					return newContacts;
				}
				throw new IllegalArgumentException("Specified file is not a .xml.");
			}
			throw new IllegalArgumentException("File path leads to a directory.");
		}	
		throw new IllegalArgumentException("File does not exist.");
		
	}
	
	public void writeAddressBookCSV(String pathFile, String separator, List<Contact> contacts) {
		
		FileWriter writer = null;
		
		try {
			writer = new FileWriter(pathFile, true);
			
			writer.write("NAME;SURNAME;PHONE;EMAIL;NOTES\n");
			
			for (Contact contact : contacts) {
				if(contact.getFirstName() != null) {
					writer.write(contact.getFirstName());
				}else {
					writer.write("");
				}
				writer.write(separator);
				if(contact.getLastName() != null) {
					writer.write(contact.getLastName());
				}else {
					writer.write("");
				}
				writer.write(separator);
				if(contact.getPhoneNumber() != null) {
					writer.write(contact.getPhoneNumber());
				}else {
					writer.write("");
				}
				writer.write(separator);
				if(contact.getEmail() != null) {
					writer.write(contact.getEmail());
				}else {
					writer.write("");
				}
				writer.write(separator);
				if(contact.getNotes() != null) {
					writer.write(contact.getNotes());
				}else {
					writer.write("");
				}
				writer.write('\n');
			}
		}catch (IOException e) {
			e.printStackTrace();
		} finally {			
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void writeAddressBookXML(List<Contact> contacts, String filePath) {
		
		File file = new File(filePath);
		
		if(file.exists()) {
			List<Contact> oldContacts = loadAddressesFromXML(filePath);
			for (int i=0; i<oldContacts.size(); i++) {
				contacts.add(oldContacts.get(i));
			}
		}
		
		try {
		
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
					
			StreamResult result = new StreamResult(file);
	
			// Output to console for testing
			StreamResult syso = new StreamResult(System.out);
	
			transformer.transform(source, result);
			transformer.transform(source, syso);
	
			//System.out.println("File saved!");
		} catch (ParserConfigurationException pEx) {
			pEx.printStackTrace();
		} catch (TransformerConfigurationException tcEx) {
			tcEx.printStackTrace();
		} catch (TransformerException tEx) {
			tEx.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		String myString = "";
		for(Contact c : contacts) {
			myString += c.toString();
			myString += "\n";
		}
		return myString;
	}
	
	public static void main(String[] args) {
		AddressBook addressBook = new AddressBook();
		
		List<Contact> newContacts = addressBook.loadAddressesFromCSV("/Temp/addressBook.csv", ";");
		addressBook.contacts = newContacts;
		
		//System.out.println(addressBook.toString());
		
		//newContacts = addressBook.loadAddressesFromXML("/Temp/rubrica.xml");
		//addressBook.contacts = newContacts;
		
		addressBook.writeAddressBookXML(addressBook.contacts, "/Temp/contacts.xml");
		
		//addressBook.writeAddressBookCSV("/Temp/newCSV.csv", ";", newContacts);
		
		//System.out.println(addressBook.toString());
	}
	
	
}
