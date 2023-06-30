package it.beije.suormary.xml.char_mancuso_sala;

import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {

		

//		}
		Document d = Document.parse("/v/test_parser1.xml");
		Element e = d.getRootElement();
		List<Node> nodelist = e.getChildNodes();
		for(Node node : nodelist) {
			if(node instanceof Element) {
				Element el = (Element)node;
				System.out.println(el.getElementsByTagName("nome"));
			}
		}
//		List<Element> elementList = e.getChildElements();
//		for(Element elem : elementList) {
//			System.out.println(elem.getTagName());
//			List<Attribute> listAttributes= elem.getAttributes();
//			for(Attribute at : listAttributes) {
//				System.out.println(at.getName());
//				System.out.println(at.getContent());
//
//			}
//		
//		
//		}
   }
}


