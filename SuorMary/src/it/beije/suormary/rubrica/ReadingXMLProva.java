package it.beije.suormary.rubrica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadingXMLProva {
	
	public static List<Element> getChildElements(Element el) {						//trovo elementi figlio contatto
		NodeList nodeList = el.getChildNodes();
		//System.out.println("nodeList size: " + nodeList.getLength());
		List<Element> elements = new ArrayList<Element>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			if (n instanceof Element) elements.add((Element) n);
		}
		
		return elements;
	}
	
	
	public static void main(String[] args) throws ParserConfigurationException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document rubrica = null;
		try {
			rubrica = documentBuilder.parse("/Users/marty/Desktop/Marti/Beije/Esercizi Academy/FILE ESERCIZI/rubrica.xml");
		} catch (SAXException | IOException e) {

			e.printStackTrace();
		}
		
		Element docEl = rubrica.getDocumentElement();		//prende il nome del root element
//		System.out.println(docEl.getTagName());
		
//		NodeList cognomi = docEl.getElementsByTagName("cognome");						mi prende i figli attraverso i tag
//		System.out.println("nodeList cognomi size: " + cognomi.getLength());
		
		List<Element> elements = getChildElements(docEl);
//		System.out.println("elements size: " + elements.size());
		
		List<Contact> contacts = new ArrayList<Contact>();
		Contact c = null;
		List<Element> els = null;
		for (Element el : elements) {
			//System.out.println("eta' contatto = " + el.getAttribute("eta"));
			//System.out.println("contenuto contatto = " + el.getTextContent());
			els = getChildElements(el);
			c = new Contact();
			for (Element e : els) {
				//System.out.println(e.getTagName() + " = " + e.getTextContent());
				switch (e.getTagName()) {
				
				}
			}
		}
	}

}
