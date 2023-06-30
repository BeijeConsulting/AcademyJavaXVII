package it.beije.suormary.xml.char_mancuso_sala;

import java.util.List;

public class Document  {
	
	public String document;
	
	public Element getRootElement() {
		Element e = null;
		try {
			//rimuovo eventuali commenti o spazi
			document=Document.removeCommentAndSpace(document);
			
			//rimuovo il percorso
			document=Document.removePath(document);
			
			
			int startIndex = document.indexOf("<");
			int endIndex = document.indexOf(">");
			
			if(startIndex == -1 || endIndex == -1) {
				throw new IllegalArgumentException("Nessun elemento radice trovato");
			}
			
			//estraggo l'elemento radice con <>
			String rootEl = document.substring(startIndex, endIndex+1);
			
			//tolgo <>
			String nameRootEl = rootEl.substring(1,rootEl.length());
			
			 e = new Element(nameRootEl);
			
			document=document.trim();
			//la stringa con il corpo
			//??dovrei togliere dal fondo gli n caratteri
			//di cui è composto il tag di chiusura??
			String body = document.substring(endIndex+1, document.length()-rootEl.length());
			
			e.setBody(body);
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return e;
	}
	public static Document parse(String file) {		
		return XMLUtilities.readXML(file);
	}
	
private static String removeCommentAndSpace(String document){
	//rimuove spazi bianchi iniziali
			document=document.trim();
			
			while(document.startsWith("<!--")) {
				int commentEnd = document.indexOf("-->")+3;
				if(commentEnd==-1) {
					throw new IllegalArgumentException("commento non chiuso correttamente");
				}
				document=document.substring(commentEnd).trim();
			}
			return document;
	}

	private static String removePath(String document) {
		String declaration="<?xml";
		if(document.startsWith(declaration)) {
			int end=document.indexOf("?>")+2;
			if(end==-1) {
				throw new IllegalArgumentException("dichiarazione non chiusa correttamente");
			}
			document=document.substring(end).trim();
		}
		
		return document;
		
	}
	
	
	
}
