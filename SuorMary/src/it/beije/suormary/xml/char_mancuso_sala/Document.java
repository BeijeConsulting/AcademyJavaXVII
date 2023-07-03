package it.beije.suormary.xml.char_mancuso_sala;

public class Document  { 
	
	public String document;
	
	public Element getRootElement() {

		Element el = null;

		try {
			//rimuovo eventuali commenti o spazi
			//document=Document.removeCommentAndSpace(document);
			
			//rimuove spazi a inizio e fine e rimuove tutti i commenti che trova nel file
			document=Document.removeAllComment(document);
			
			//rimuovo il percorso
			document=Document.removePath(document);
			
			
			int startIndex = document.indexOf("<");
			int endIndex = document.indexOf(">");
			
			if(startIndex == -1 || endIndex == -1) {
				throw new IllegalArgumentException("Nessun elemento radice trovato");
			}
			
			
			//estraggo l'elemento radice con <>
			String rootEl = document.substring(startIndex, endIndex+1);
			
			//controllo se ho attributi
			String [] root = rootEl.split(" ");
			
			if(root.length>1) {
				int lunghezzaPrimoEl=root[0].length();
				rootEl=document.substring(0, lunghezzaPrimoEl+1)+">";
			}
			
			
			
			//tolgo <> 
			String nameRootEl = rootEl.substring(1,rootEl.length()-1);
			

			 el = new Element(nameRootEl);
			 
			 
			 //salvo gli attributi se presenti
			 if(root.length>1) {
				 Attribute a=null;
				 for(int i=1; i<root.length; i++) {
					 //salvo la parte prima dell'uguale
					 int end=root[i].indexOf("=");
					 String name=root[i].substring(end);
					 //salvo la parte dopo l'uguale senza le virgolette
					 String content=root[i].substring(end+2, root[i].length());
					 //imposto i valori dell'attributo corrente
					 a=new Attribute();
					 a.setName(name);
					 a.setContent(content);
					 el.attributes.add(a);
				 }
			 }

			
			document=document.trim();
			//la stringa con il corpo
			//??dovrei togliere dal fondo gli n caratteri
			//di cui è composto il tag di chiusura??

			//HO AGGIUNTO -1
			String body = document.substring(endIndex+1, document.length()-rootEl.length()-1);

			el.setBody(body);
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		

		return el;

	}
	public static Document parse(String file) {		
		return XMLUtilities.readXML(file);
	}

	/*SOSTITUITO DA REMOVEALLCOMMENT
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
*/
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
	
	public static String removeAllComment(String document) {
			
			document=document.trim();
		    StringBuilder result = new StringBuilder();
		    
		    //trovo l'indice del commento iniziale
		    int start = document.indexOf("<!--");
		    //trovo l'indice di chiusura del commento iniziale
		    int end = document.indexOf("-->");
		    
		    //la uso per tenere traccia dell'indice successivo al commento corrente
		    int last = 0;
		    
		    //ciclo per trovare nuovi commenti
		    //se entrambi sono a -1 non ho più commenti da trovare nel file
		    while (start != -1 && end != -1) {
		        // aggiungo tutto ciò che precede il commento
		        result.append(document, last, start);

		        // avanzo all'indice successivo dopo il commento
		        last = end + 3;

		        // cerco il prossimo commento
		        start = document.indexOf("<!--", last);
		        end = document.indexOf("-->", last);
		    }

		    // Aggiungo il resto del documento dopo l'ultimo commento
		    result.append(document.substring(last));

		    return result.toString();
		
	}
	
	
	
}
