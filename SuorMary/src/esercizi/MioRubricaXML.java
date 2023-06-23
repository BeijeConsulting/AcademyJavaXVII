package esercizi;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class MioRubricaXML {
	
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

	public static void main(String[] args) throws Exception {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse("C:\\Users\\Padawan\\Desktop\\rubrica.xml");
		
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

		List<MioContact> contacts = new ArrayList<MioContact>();
		MioContact c = null;
		List<Element> els = null;
		for (Element el : elements) {
			System.out.println("et� contatto = " + el.getAttribute("eta"));
			//System.out.println("contenuto contatto = " + el.getTextContent());
			els = getChildElements(el);
			c = new MioContact();
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

}
