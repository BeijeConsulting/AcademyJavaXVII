package it.beije.suormary.xml.Ceccarelli_Giampaoli_Trapani;

import java.util.List;
import java.util.ArrayList;


public class ParserXML {
	
//	public static VostroOggettoDocumento parse(String file) {
//		
//	}

	public static void main(String[] args) {
		String s="\\Users\\marty\\Desktop\\test_parser3.xml";
		
		 ToolsParser toolsParser = new ToolsParser();
	        List<String> s2 = new ArrayList<>();
	        // s2 contiene il file formattato
	        s2 = toolsParser.readXML(s);
	        // treeTry contiene l'albero da root
//	        for(String s1:s2) {
//	        	System.out.println(s1);
//	        }
	        
//	        Node treeTry = toolsParser.tree(s2);
//	        toolsParser.getRootElement(treeTry);
//	        String name = "<contatto>";
////	        if(toolsParser.searchNode(name)!=null) {
////	        	System.out.println("ok");
////	        }
//	        
//	        toolsParser.getChildNode(toolsParser.searchNode(name));
//	        System.out.println("-----------------------");
//	        toolsParser.getChildElement(toolsParser.searchNode(name));
//	        System.out.println("-----------------------");
//	        toolsParser.getElementsByTagName("<nome>");
//	        toolsParser.getElementsByTagName("<cognome>");
//	        toolsParser.getElementsByTagName("<telefono>");
//	        toolsParser.getElementsByTagName("<email>");
//
//
////	        ToolsParser toolsParser1 = new ToolsParser();
////	        toolsParser1.getRootElement(s1);

		
		
	    
		

	}

}
