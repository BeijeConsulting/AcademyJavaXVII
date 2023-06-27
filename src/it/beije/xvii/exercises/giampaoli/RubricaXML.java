package it.beije.xvii.exercises.giampaoli;

import java.util.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;

public class RubricaXML {

	public static void main(String[] args) throws Exception {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder(); 
		
		Document document =  documentBuilder.parse("C:\\Users\\Jamp\\Desktop\\Prova.xml");
		
		Element docEl = document.getDocumentElement();
		System.out.println(docEl.getTagName());
		
		NodeList nomi = docEl.getElementsByTagName("nome");
		
		System.out.println("Size nomi: " + nomi.getLength());
		
		NodeList nodeList = docEl.getChildNodes();
		
		System.out.println("Nodelist size :" + nodeList.getLength());
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			System.out.println(n instanceof Element ? "Elemento : " + ((Element)n).getTagName() : "Altro");
			
		}
//		List<Elements> element =
// 		
//		List<Contact> contacts = new ArrayList<Contact>();
//		Contact c = null;
//		List<Element> els = null;
//		
//		for (Element el : elements) {
//			
//			c = new Contact();
//			els = getChildElements(el);
//			
//			for (Element e : els) {
//						
//				switch (e.getTagName()) {
//					case "nome": c.setName(e.getTextContent());
//						break;
//					case "cognome" : c.setSurname(e.getTextContent());
//						break;
//					default:
//						break;
//				}
//			}
//		}
		
		
	}

}
