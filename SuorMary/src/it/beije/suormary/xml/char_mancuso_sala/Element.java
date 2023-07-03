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
			
			// If the cursor is currently inside the body of a child element 
			if(inBody) {
				innerElementBody += body.charAt(i);
				// If the cursor is currently inside a tag, also inside the body
				if(inBodyTag) {			
					
					// The space character inside the tag indicates the end of the tag name
					if(body.charAt(i) == ' ') {
						skip = true;
					}
					// The slash character inside the tag indicates it is a closing tag, unless it's found inside
					// an attribute's content
					if(body.charAt(i) == '/') {
						if(!inAttributeContent) {
							inClosing = true;
						}
						
					}

					// Differently from the inClosing check outside the inBody flow, here it's not necessary to 
					// create a new element nor its attributes if a tag is closed, as the elements here 
					// are not direct children of the element calling the method.
					// It is necessary to monitor the tags that are opened and closed and their order.
					if(!inClosing) {
						
						// If the current character is a '>' it indicates the end of a tag
						if(body.charAt(i) == '>') {
		
							tags.add(innerElementName);
							innerElementName = "";					
							//inBody = true;
							skip = false;
						}
						
						// If the skip tag is false it means we are not reading attributes
						if(!skip) {
							// The '>' character is not part of the tag name so we check for that
							if(body.charAt(i) != '>') {
								innerElementName += body.charAt(i);
							}
						// If we are reading an attribute we just need to track if we're reading its name or value
						// We need it to know at all times where the cursor is as this information is necessary in other
						// cases.
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
						// Here we are in a closing tag
						
						// When a tag is closed its name is added to the tags list, unless it's a self-closing tag
						// If the tag being closed does not correspond with the previous opened tag an exception is
						// thrown as it means the document is not correct
						if(body.charAt(i) == '>') {
							
							if(i > 0 && body.charAt(i-1) == '/') {
							}else {
							
								if(tags.get(tags.size()-1).equals(closingName)) {
									tags.remove(tags.size()-1);
									closingName="";
								}else {
									throw new Exception("I tag del documento vengono chiusi in ordine errato.");
								}
							}
							inBodyTag = false;
							inClosing = false;
						}else {
							if(body.charAt(i) != '/' && body.charAt(i) != '<') {
								closingName += body.charAt(i);
							}
						}
					}			
					
				}

				// Here we check if the cursor has entered a tag or left one. The check is placed here to avoid
				// saving ">" or "<" as part of the element's name/body
				if(body.charAt(i) == '<') {
					inBodyTag = true;
				}	
				if(body.charAt(i) == '>') {
					inBodyTag = false;
				}
				
				// This if sequence keeps checking if the tag being closed is the element's i'm reading the body of
				if(checkName.equals("")) {
					if(body.charAt(i) == '<') {
						checkName += '<';
					}else {
						checkName = "";
					}
				}else {
	
					// If checkName is not empty it means the cursor is reading a tag, we must check if it's the one
					// that closes the primary child element or reset the variable if it's an inner tag
					if(checkName.equals("<")) {
						if(body.charAt(i) == '/') {
							checkName += '/';
						}else {
							// If it's not a closing tag reset checkName
							checkName = "";
						}
					}else {

						// If we are in a closing tag we must check every next character  corresponds with the tag
						// we are checking
						if(checkName.startsWith("</")){
							// If the tag fully corresponds
							if(checkName.substring(2).equals(name) && body.charAt(i) == '>') {
								
								// add the > to the checkName
								checkName += body.charAt(i);
								
								// Remove the closing tag from the element's body
								innerElementBody = innerElementBody.substring(0,innerElementBody.length() - checkName.length());
								
								// set the element's body with the one we read and saved in innerElementBody
								e.setBody(innerElementBody);
								
								// add the element to the nodes list
								nodes.add(e);
								
								// Set the inBody flag to false because we finished reading it
								inBody=false;
								
								// Resetting supporting variables
								innerElementBody = "";
								checkName = "";
								name = "";
								
							}else {
								// If the tag we are reading does not correspond we reset checkName
								checkName += body.charAt(i);
								if(!name.substring(0,checkName.length()-2).equals(checkName.substring(2))) {
									checkName = "";
								}
							}
							
						}
					}
				}

			// If we are not reading a child's element body we need to 
			// distinguish between being INSIDE a tag or OUTSIDE
			}else {
				
				// If INSIDE a tag, a space character means we have finished reading the tag name and start
				// reading attributes
				if(inTag && body.charAt(i) == ' ') {
					skip = true;
				}
				// The slash character inside the tag indicates it is a closing tag, unless it's found inside
				// an attribute's content
				if(inTag && body.charAt(i) == '/') {
					if(!inAttributeContent) {
						inClosing = true;
					}
				}
				// Check if in a closing tag or not
				if(!inClosing) {

					// If in a not closing tag and reading ">" it means the end of an opening tag
					if(body.charAt(i) == '>') {
						
						inTag = false;
						
						// create a new element and set its name and attributes
						e = new Element(name);
						
						e.attributes = attributes;
						
						// Save the name of the tag that just opened in the tags list
						tags.add(name);
						
						// reset attribute list
						attributes = new ArrayList<>();
						
						inBody = true;
						
						// set skip to false because it's just to read attributes
						skip = false;
					}
					// If the skip tag is false it means we are not reading attributes
					if(!skip) {
						if(inTag) {
							name += body.charAt(i);
						}
					}else {

						// Here we are reading attributes because skip is true
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
					
							// If inAttributeContent is true we read attribute content until we read the character "
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
					
					// 
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

