package it.beije.suormary.xml.char_mancuso_sala;

import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		
		String s =  "<contatto>\n\r"
				+ "		<nome>lara</nome>\n\r"
				+ "		<cognome>mancuso</cognome>\n\r"
				+ "</contatto>";
		
		/*Document doc = Document.parse("/Temp/filexml.xml");
		
		Element root = new Element();
		
		root = doc.getRootElement();
		
		System.out.println("I am an element\n");
		System.out.println(root.getTagName());
		System.out.println(root.attributesToString());
		System.out.println(root.getBody());*/
		
		
		Element root = new Element();
		root.setBody(s);
		
		System.out.println(root.getElementTextContent());
		
		/*List<Node> nodes = root.getChildNodes();
		
		for(Node n : nodes) {
			if(n instanceof Element) {
				System.out.println("I am an element\n");
				System.out.println(((Element)n).getTagName());
				System.out.println(((Element)n).attributesToString());
			}else {
				System.out.println("I am a node");
			}
			System.out.println(n.getBody());
			//System.out.println(n.getBody());
		}*/
		
		
		
		
	}

}
