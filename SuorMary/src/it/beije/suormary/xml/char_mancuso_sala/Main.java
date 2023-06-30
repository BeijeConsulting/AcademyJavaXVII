package it.beije.suormary.xml.char_mancuso_sala;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		String s =  "<nome>Paolino</nome>\r\n"
				+ "		<cognome>Paperino</cognome>\r\n"
				+ "		<telefono>00423803243423\r\n";
		
		Element root = new Element();
		root.setBody(s);
		
		List<Node> nodes = root.getChildNodes();
		
		for(Node n : nodes) {
			if(n instanceof Element) {
				System.out.println("I am an element\n");
				System.out.println(((Element)n).getTagName());
				System.out.println(((Element)n).attributesToString());
			}else {
				System.out.println("I am a node");
			}
			System.out.println(n.getTextContent());
			System.out.println(n.getBody());
		}
		
		
	}

}
