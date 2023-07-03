package it.beije.suormary.xml.caroselli_iannetta_ulloa;

public class Main {

	public static void main(String[] args) {
		
		
		String path[] = {"C:\\Users\\Chiara\\Desktop\\Academy\\esercizi\\xml_parser_test\\test_parser3.xml"
		, "C:\\Users\\Chiara\\Desktop\\Academy\\esercizi\\xml_parser_test\\test_parser2.xml"
		, "C:\\Users\\Chiara\\Desktop\\Academy\\esercizi\\xml_parser_test\\test_parser4.xml"
		,"C:\\Users\\Chiara\\Desktop\\Academy\\esercizi\\xml_parser_test\\test_parser5.xml"
		,"C:\\Users\\Chiara\\Desktop\\Academy\\esercizi\\xml_parser_test\\test_parser6.xml"};
		
		
			//System.out.println(path[2].substring(60));
			Document document = Parser.parse(path[0]);
//			//System.out.println(document.getRootElement());
//			System.out.println(document.getElementsByTagName("nome"));
		
			for (Node n : document.getTree()) {
				System.out.println(n.getTagName());
			}
		
//        System.out.println("CHILDREN NODES " + document.getTree().get(1).getChildNodes());
//        System.out.println("");
//        System.out.println("CHILDREN ELEMENTS " + document.getTree().get(1).getChildElements());
//		  System.out.println("GET TAG NAME " + document.getTree().get(1).getTagName());
//        System.out.println("TEXT CONTENT " + document.getTree().get(0).getTextContent());

        System.out.println("ATTRIBUTES " + document.getTree().get(1).getAttributes());
        System.out.println("");
        System.out.println("ATTRIBUTE BY NAME " + document.getTree().get(1).getAttribute("id"));

	}

}
