package it.beije.suormary.xml.char_mancuso_sala;

import java.util.List;

public class Document  {
	
	public String document;
	
	public Element getRootElement() {
		return null;
	}
	public static Document parse(String file) {		
		return XMLUtilities.readXML(file);
	}
	
}
