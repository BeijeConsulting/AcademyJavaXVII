package it.beije.suormary.xml.Ceccarelli_Giampaoli_Trapani;

import java.util.List;
import java.util.ArrayList;


public class ParserXML {
	
//	public static VostroOggettoDocumento parse(String file) {
//		
//	}

	public static void main(String[] args) {
		String s="\\Users\\marty\\Desktop\\test_parser2.xml";
		
		
		 ToolsParser toolsParser = new ToolsParser();
	        List<String> s2 = new ArrayList<>();
	        s2 = toolsParser.readXML(s);
	        Node treeTry = toolsParser.tree(toolsParser.readXML(s));
	        toolsParser.getRootElement(treeTry);
	        System.out.println("-----------------------");
//	        System.out.println(treeTry.getChildEl());
//	        System.out.println("-----------------------");
	        //toolsParser.getChildNode("<contatti>");
	        toolsParser.getChildElement("<contatto>");



//	        ToolsParser toolsParser1 = new ToolsParser();
//	        toolsParser1.getRootElement(s1);

		
		
	    
		

	}

}
