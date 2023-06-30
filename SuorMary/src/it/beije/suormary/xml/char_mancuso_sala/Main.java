package it.beije.suormary.xml.char_mancuso_sala;

import java.util.List;

public class Main {

	public static void main(String[] args) { 
		
//		String s =  "<nome>Paolino</nome>\r\n"
//				+ "		<cognome>Paperino</cognome>\r\n"
//				+ "		<telefono>00423803243423\r\n";
//		
//		Element root = new Element();
//		root.setBody(s);
//		
//		List<Node> nodes = root.getChildNodes();
//		
//		for(Node n : nodes) {
//			if(n instanceof Element) {
//				System.out.println("I am an element\n");
//				System.out.println(((Element)n).getTagName());
//				System.out.println(((Element)n).attributesToString());
//			}else {
//				System.out.println("I am a node");
//			}
//			System.out.println(n.getTextContent());
//			System.out.println(n.getBody());
//		}
		Document d = Document.parse("/v/test_parser1.xml");
		Element e = d.getRootElement();
		List<Node> nodelist = e.getChildNodes();
//		for(Node node : nodelist) {
//			System.out.println(node.getTagName());
//		}
		List<Element> elementList = e.getChildElements();
		for(Element elem : elementList) {
			System.out.println(elem.getTagName());
			List<Attribute> listAttributes= elem.getAttributes();
			for(Attribute at : listAttributes) {
				System.out.println(at.getName());
				System.out.println(at.getContent());
			}
			System.out.println(elem.getTextContent());
		}
		
		
	}

}
