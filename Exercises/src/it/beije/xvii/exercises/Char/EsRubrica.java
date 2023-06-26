package it.beije.xvii.exercises.Char;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
public class EsRubrica {

	public static void main(String[] args) throws Exception {
//		loadRubricaFromCSV("/v/rubricacsv.txt",";");
		List<Contact> contatti = loadRubricaFromXML("/v/rubrica.xml");
		writeRubricaCSV(contatti,"/v/writeRubrica.txt",";");

	}
	public static List<Contact> loadRubricaFromCSV(String pathFile, String separator) throws Exception {
		File file = new File(pathFile);
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<Contact> contacts = new ArrayList<>();
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
		fileReader.close();
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

	
}
