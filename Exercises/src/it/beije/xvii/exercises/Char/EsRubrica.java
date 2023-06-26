package it.beije.xvii.exercises.Char;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
public class EsRubrica {

	public static void main(String[] args) throws Exception {
//		loadRubricaFromCSV("/v/rubricacsv.txt",";");
		List<Contact> contatti = loadRubricaFromXML("/v/rubrica.xml");
		writeRubricaXML(contatti,"/v/writeRubrica.txt");

	}
	public static List<Contact> loadRubricaFromCSV(String pathFile, String separator)  {
		FileReader fileReader = null;
		List<Contact> contacts = null;
		BufferedReader bufferedReader = null;
		try {
			File file = new File(pathFile);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			contacts = new ArrayList<>();
			Contact con = null;
			List<String> arr = new ArrayList<>();
			while(bufferedReader.ready()) {
				String line = bufferedReader.readLine();
				arr.add(line);
			}
	
			for(String str : arr) {
				String[] cont = str.split(separator);
				con = new Contact();
				con.setSurname(cont[0]);
				con.setName(cont[1]);
				con.setPhoneNumber(cont[2]);
				con.setEmail(cont[3]);
				con.setNote(cont[4]);
				contacts.add(con);
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				fileReader.close();
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return contacts;
	
	}
	
	public static List<Contact> loadRubricaFromXML(String pathFile) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(pathFile);
		Element el = document.getDocumentElement();
		List<Contact> contacts = new ArrayList<>();
		List<Element> elements = getChildElements(el);
		List<Element> els = null;
		Contact c = null;
		for(Element contact : elements) {
			els = getChildElements(contact);
			c = new Contact();
			for(Element elem : els) {
				switch(elem.getTagName()) {
				case "nome" : c.setName(elem.getTextContent()); break;
				case "cognome" : c.setSurname(elem.getTextContent()); break;
				case "email" : c.setEmail(elem.getTextContent()); break;
				case "telefono" : c.setPhoneNumber(elem.getTextContent()); break;
				case "note" : c.setNote(elem.getTextContent()); break;
				default : System.out.println("Il tag non è stato riconosciuto"); break;
				}
			}
			contacts.add(c);
		}
	
		return contacts;
	}
	
	public static List<Element> getChildElements(Element el){
		NodeList nodeList = el.getChildNodes();
		List<Element> elements = new ArrayList<>();
		for(int i = 0; i< nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if(node instanceof Element) elements.add((Element)node);
		}
		return elements;
		
	}
	public static void writeRubricaCSV(List<Contact> contatti, String pathFile, String separator) throws Exception {
		File file = new File(pathFile);
		FileWriter fileWriter = new FileWriter(file,true);
		for(Contact contatto : contatti) {
			fileWriter.write(contatto.getSurname() + separator + contatto.getName() + separator + contatto.getPhoneNumber() + separator + contatto.getEmail()  + (contatto.getNote() == null ? "" : separator + contatto.getNote()) + "\n");
			fileWriter.flush();
		}
		fileWriter.close();
	}
	
	public static void writeRubricaXML(List<Contact> contatti, String pathFile) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		Element contacts = document.createElement("contacts");
		document.appendChild(contacts);
		Element contact = null;
		for(Contact c : contatti) {
			contact = document.createElement("contact");
			if(c.getName() != null) {
				Element name = document.createElement("name");
				name.setTextContent(c.getName());
				contact.appendChild(name);
			}
			if(c.getSurname() != null) {
				Element surname = document.createElement("surname");
				surname.setTextContent(c.getSurname());
				contact.appendChild(surname);
			}
			if(c.getEmail() != null) {
				Element email = document.createElement("email");
				email.setTextContent(c.getEmail());
				contact.appendChild(email);
			}
			if(c.getPhoneNumber() != null) {
				Element phone = document.createElement("phone");
				phone.setTextContent(c.getPhoneNumber());
				contact.appendChild(phone);
			}
			if(c.getNote() != null) {
				Element note = document.createElement("note");
				note.setTextContent(c.getNote());
				contact.appendChild(note);
			}
			contacts.appendChild(contact);
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult file = new StreamResult(new File("/v/contacts.xml"));
		StreamResult syso = new StreamResult(System.out);
		transformer.transform(source, file);
		transformer.transform(source, syso);

		
	}

	
}
