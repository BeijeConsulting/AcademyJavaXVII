package it.beije.suormary.xml.char_mancuso_sala;

import java.util.ArrayList;
import java.util.List;

public class Element extends Node{ 
	
	
	private String tagName;
	public List<Attribute> attributes;

	public Element() {
		attributes = new ArrayList<>();
	}
	
	public Element(String tagName) {
		attributes = new ArrayList<>();
		this.setTagName(tagName);
	}
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagName() {
		return this.tagName;
	}
	

	public String attributesToString(){
		String result = "";
		for(Attribute a : attributes) {
			result += a.toString();
		}
		return result;
	}

	public List<Attribute> getAttributes(){
		return this.attributes;
	}
	
	public String getAttribute(String attribute) {
		String attContent = "";
		for(Attribute at : attributes) {
			if(at.getName().equals(attribute))  attContent = at.getContent();
		}
		return attContent;
	}
	
	public List<Node> getChildNodes() throws Exception{
		
		// List of nodes to be returned at the end
		List<Node> nodes = new ArrayList<>();
		
		// List of an element's attributes
		List<Attribute> attributes = new ArrayList<>();
		
		// List of all the tags' names that are encountered while reading the whole body.
		// They are saved in order of appearance and removed when the corresponding tag is closed.
		// It is used to recognize an attempt to close a tag which name does not correspond 
		// with the last one appended
		List<String> tags = new ArrayList<>();
		
		// Supporting variables used to create new nodes, elements and attributes to add to their lists
		Element e = null;
		Attribute att = null;
		Node n = null;

		// Flag which indicates if the pointer is currently inside a tag
		boolean inTag = false;
		
		// Flag which indicates if the pointer has finished reading a tag's name and is starting 
		// to read possible attributes
		boolean skip = false;
		
		// Flag which indicates if the pointer is reading an attribute's value
		boolean inAttributeContent=false;
		
		// Flag which indicates if the pointer is reading a closing tag (</tag>)
		boolean inClosing = false;
		
		// Flag which indicates if the pointer is reading a child element's body
		boolean inBody = false;

		// Flag which indicates if the pointer is reading a tag while inside a child element's body
		boolean inBodyTag = false;
		

		// Supporting variables to memorize the elements' names as well as their attributes', and the latter's value
		String name = "";
		String attributeName = "";
		String attributeContent = "";
		
		// Supporting variable to save the content of the nodes between each element
		String contentNode = "";
		
		// Supporting variable used to confront the current closing tag to the current child element's one 
		String checkName = "";
		
		// Supporting variable used to save the name of the element that's being closed to confront it later
		String closingName = "";
		
		// Duplicating the body of the current object to avoid modyifing it by mistake
		String body = this.getBody();
		
		// Supporting variable used to save the name of  an element that's inside the current child element, to later
		// add it to the tag list
		String innerElementName = "";
		
		// Supporting variable used to save the body of the current child element
		String innerElementBody = "";
		
		for(int i=0; i< body.length(); i++) {
			
			// Se ci troviamo nel body di un elemento figlio ne va letto il contenuto
			if(inBody) {
				innerElementBody += body.charAt(i);
				//Se ci troviamo in un tag interno al body
				if(inBodyTag) {			
					
					if(body.charAt(i) == ' ') {
						skip = true;
					}
					if(body.charAt(i) == '/') {
						//Controllo di non essere nel valore di un attributo
						if(!inAttributeContent) {
							inClosing = true;
						}
						
					}
					// a differenza del controllo inClosing fuori dal body, qui non è necessario creare un nuovo
					// elemento nè memorizzare i suoi attributi.
					if(!inClosing) {
						
						if(body.charAt(i) == '>') {
		
							tags.add(innerElementName);
							innerElementName = "";					
							inBody = true;
							skip = false;
						}
						
						if(!skip) {
							if(body.charAt(i) != '>') {
								innerElementName += body.charAt(i);
							}
							
						}else {
							if(!inAttributeContent) {
								if(body.charAt(i) != ' ') {
									if(body.charAt(i) != '=') {
										if(body.charAt(i) == '"') {
											inAttributeContent = true;
										}
									}
								}
							}else {
								if(body.charAt(i) == '"') {
									inAttributeContent = false;		
								}
							}
						}	
					} else {
						// Quando si chiude un tag ed esso non chiude l'ultimo tag di apertura inserito significa che è
						// presente un errore di ordine dei tag nel body quindi lancio un'eccezione
						if(body.charAt(i) == '>') {
							
							if(i > 0 && body.charAt(i-1) == '/') {
								inBodyTag = false;
								inClosing = false;
							}else {
							
								if(tags.get(tags.size()-1).equals(closingName)) {
									tags.remove(tags.size()-1);
									closingName="";
								}else {
									throw new Exception("I tag del documento vengono chiusi in ordine errato.");
								}
								inBodyTag = false;
								inClosing = false;
							}
						}else {
							if(body.charAt(i) != '/' && body.charAt(i) != '<') {
								closingName += body.charAt(i);
							}
							
							
						}
					}			
					
				}
				//Qui capisco se dopo aver letto il nuovo carattere sono entrato in un tag o ne sono uscito
				//Il controllo viene messo dopo l'if(inBodyTag) precedente per non contare ">" o "<" come parte
				//di eventuale tag o body
				if(body.charAt(i) == '<') {
					inBodyTag = true;
				}	
				if(body.charAt(i) == '>') {
					inBodyTag = false;
				}
				//Qui inizio il controllo per vedere se sto chiudendo il tag dell'elemento di cui sto leggendo il body
				if(checkName.equals("")) {
					if(body.charAt(i) == '<') {
						checkName += '<';
					}else {
						checkName = "";
					}
				}else {
					//Se checkName non è vuoto significa che sono in un tag, devo controllare se è il corretto tag di chiusura
					if(checkName.equals("<")) {
						if(body.charAt(i) == '/') {
							checkName += '/';
						}else {
							// Se non è un tag di chiusura resetto checkName
							checkName = "";
						}
					}else {
						//Se è un tag di chiusura devo controllare che ciascun carattere successivo corrisponda
						//col tag che devo chiudere
						if(checkName.startsWith("</")){
							if(checkName.substring(2).equals(name) && body.charAt(i) == '>') {
								// Se alla fine il tag corrispondeva
								checkName += body.charAt(i);
								
								// Rimuovo il tag di chiusura dal body
								innerElementBody = innerElementBody.substring(0,innerElementBody.length() - checkName.length());
								
								// imposto il body dell'elemento con quello che ho letto
								e.setBody(innerElementBody);
								
								// aggiungo l'elemento letto a nodes
								nodes.add(e);
								
								// imposto il flag inBody a false perchè ho finito di leggerlo
								inBody=false;
								
								// Resetto le variabili di appoggio
								innerElementBody = "";
								checkName = "";
								name = "";
								
							}else {
								// Se il tag che sto leggendo non corrisponde resetto checkName
								checkName += body.charAt(i);
								if(!name.substring(0,checkName.length()-2).equals(checkName.substring(2))) {
									checkName = "";
								}
							}
							
						}
					}
				}
				//Se invece non siamo nel body di un elemento bisogna distinguere tra interno di un tag
				//ed esterno
			}else {
				//Se siamo all'interno di un tag e incontriamo un carattere spazio significa che abbiamo
				//finito di leggere il nome e passiamo agli attributi
				if(inTag && body.charAt(i) == ' ') {
					skip = true;
				}
				//Se siamo all'interno di un tag e troviamo un / significa che stiamo leggendo un tag di chiusura
				if(inTag && body.charAt(i) == '/') {
					//Controllo di non essere nel valore di un attributo
					if(!inAttributeContent) {
						inClosing = true;
					}
				}
				// Controllo di non essere in un tag di chiusura
				if(!inClosing) {
					//Se incontro un ">" e non sono in un tag di chiusura significa che ho appena finito di leggere un tag
					// di apertura
					if(body.charAt(i) == '>') {
						
						// Quindi sto uscendo dal tag -> in tag = false
						inTag = false;
						
						// creo l'elemento del tag appena letto
						e = new Element(name);
						
						// devo salvare eventuali attributi letti dell'elemento
						e.attributes = attributes;
						
						//Inserisco il nome del tag appena letto nella lista tags che tiene traccia dei tag aperti
						tags.add(name);
						
						// infine resetto la lista di attributi
						attributes = new ArrayList<>();
						
						inBody = true;
						
						//imposto skip = false perchè serve solo per sapere se devo leggere attributi
						skip = false;
					}
					
					if(!skip) {
						if(inTag) {
							name += body.charAt(i);
						}
					}else {
						//Qui so che devo leggere attributi perchè skip è true
						//Se inAttributeContent è falso devo leggere il nome dell'attributo finchè non incontro ="
						if(!inAttributeContent) {
							if(body.charAt(i) != ' ') {
								if(body.charAt(i) != '=') {
									if(body.charAt(i) == '"') {
										inAttributeContent = true;
									}else {
										attributeName += body.charAt(i);
									}
								}
							}
						}else {
							// Se inAttributeContent è vero significa che devo leggere il contenuto finchè non incontro "
							if(body.charAt(i) == '"') {
								inAttributeContent = false;
								
								att = new Attribute();
								att.setName(attributeName);
								att.setContent(attributeContent);
								
								attributes.add(att);
								attributeContent = "";
								attributeName = "";
							} else {
								attributeContent += body.charAt(i);
							}
						}
					}
					
					
					
				} else {
					// Quando si chiude un tag ed esso non chiude l'ultimo tag di apertura inserito significa che è
					// presente un errore di ordine dei tag nel body quindi lancio un'eccezione
					if(body.charAt(i) == '>') {
						if(i > 0 && body.charAt(i-1) == '/') {
							inTag = false;
							
							// creo l'elemento del tag appena letto
							e = new Element(name);
							
							// devo salvare eventuali attributi letti dell'elemento
							e.attributes = attributes;
							
							// infine resetto la lista di attributi
							attributes = new ArrayList<>();
							
							inBody = true;
							
							//imposto skip = false perchè serve solo per sapere se devo leggere attributi
							skip = false;
						}else {
							if(tags.get(tags.size()-1).equals(closingName)) {
								tags.remove(tags.size()-1);
								closingName="";
							}else {
								throw new Exception("I tag del documento vengono chiusi in ordine errato.");
							}
							inTag = false;
							inClosing = false;
						}
					}else {
						closingName += body.charAt(i);
						
					}
				}
				
				
				//Se non sono in un body e trovo un tag di apertura controllo se contentNode ha dentro qualcosa
				if(body.charAt(i) == '<') {
					//Se non è vuoto significa che tra i due elementi figlio c'è un nodo
					if(!contentNode.equals("")) {
						n = new Node();
						n.setBody(contentNode);
						
						// Aggiungo il nodo alla lista e resetto la variabile di appoggio
						nodes.add(n);
						contentNode = "";
					}
					inTag = true;
				}
				
				// Se leggo un carattere mentre non sono né in un tag né in un body significa che sto leggendo un nodo
				if(!inTag) {
					if(!inBody) {
						contentNode += body.charAt(i);
					}	
				}
			}
			
			
			
		}
		return nodes;
		
	}
	
	public List<Element> getChildElements() {
		List<Node> nodeList = null;
		List<Element> elements =  null;
		try {
			nodeList = this.getChildNodes();
			elements = new ArrayList<>();
			for(int i = 0; i< nodeList.size(); i++) {
				Node node = nodeList.get(i);
				if(node instanceof Element) elements.add((Element)node);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return elements;
	}
	
	public List<Element> getElementsByTagName(String tagName) {
		List<Element> elementList = this.getChildElements();
		List<Element> elementNameList = new ArrayList<>();
		for(Element el : elementList) {
			if(el.getTagName().equals(tagName)) elementNameList.add(el);
			elementNameList.addAll(el.getElementsByTagName(tagName));
		}
		return elementNameList;
		
	}
	
	
	public String getElementTextContent()  {
		String result = null;
		try {
			result = "";
			List<Node> children = this.getChildNodes();
			if(children.size() == 0) {
				return this.getBody();
			}else {
				for(Node n : children) {
					if(n instanceof Element) {
						result += ((Element)n).getElementTextContent();
					}else {
						result += n.getBody();
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}
	
}

