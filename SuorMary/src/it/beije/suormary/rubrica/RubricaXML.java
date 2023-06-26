package it.beije.suormary.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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


public class RubricaXML {
	
	public static List<Element> getChildElements(Element el) {
		NodeList nodeList = el.getChildNodes();
		//System.out.println("nodeList size: " + nodeList.getLength());
		List<Element> elements = new ArrayList<Element>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			if (n instanceof Element) elements.add((Element) n);
		}
		
		return elements;
	}

	public static void readXML(String[] args) throws Exception {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse("/Users/Padawan/git/file/rubrica.xml");
		
		Element docEl = document.getDocumentElement();
		System.out.println(docEl.getTagName());
		
//		NodeList nomi = docEl.getElementsByTagName("nome");
//		System.out.println("nodeList nomi size: " + nomi.getLength());
		
		List<Element> elements = getChildElements(docEl);
		System.out.println("elements size: " + elements.size());
//		for (int i = 0; i < nodeList.getLength(); i++) {
//			Node n = nodeList.item(i);
//			System.out.println(n instanceof Element ? "Elemento " + ((Element)n).getTagName() : "Altro");
//		}

		List<Contact> contacts = new ArrayList<Contact>();
		Contact c = null;
		List<Element> els = null;
		for (Element el : elements) {
			System.out.println("eta' contatto = " + el.getAttribute("eta"));
			//System.out.println("contenuto contatto = " + el.getTextContent());
			els = getChildElements(el);
			c = new Contact();
			for (Element e : els) {
				System.out.println(e.getTagName() + " = " + e.getTextContent());
				
				switch (e.getTagName()) {
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
			
			contacts.add(c);
			System.out.println("-------");
		}
		
		System.out.println("contacts.size() : " + contacts.size());
	}

	public static void main(String[] args) {
		
		try {
		
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			
			Element contacts = document.createElement("contacts");
			document.appendChild(contacts);
			
			Contact contact1 = new Contact();
			contact1.setName("Pippo");
			contact1.setSurname("Rossi");
			contact1.setPhoneNumber("09876543");
			contact1.setEmail("Pippo@beije.it");
			
			Contact contact2 = new Contact();
			contact2.setName("Pluto");
			contact2.setSurname("Bianchi");
			contact2.setPhoneNumber("098767564");
			contact2.setEmail("pluto@beije.it");
			
			List<Contact> contactsList = new ArrayList<Contact>();
			contactsList.add(contact1);
			contactsList.add(contact2);
			
			Element contact = null;
			for (Contact c : contactsList) {
				contact = document.createElement("contact");
				contact.setAttribute("age", "50");
				if (c.getName() != null) {
					Element name = document.createElement("name");
					name.setTextContent(c.getName());
					contact.appendChild(name);
				}
				if (c.getSurname() != null) {
					Element surname = document.createElement("surname");
					surname.setTextContent(c.getSurname());
					contact.appendChild(surname);
				}
				if (c.getPhoneNumber() != null) {
					Element phoneNumber = document.createElement("phone");
					phoneNumber.setTextContent(c.getPhoneNumber());
					contact.appendChild(phoneNumber);
				}
				if (c.getEmail() != null) {
					Element email = document.createElement("email");
					email.setTextContent(c.getEmail());
					contact.appendChild(email);
				}
				if (c.getNote() != null) {
					Element note = document.createElement("note");
					note.setTextContent(c.getNote());
					contact.appendChild(note);
				}
				
				contacts.appendChild(contact);
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

}
