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
		
		//StringBuilder sb = new StringBuilder(body);
		List<Node> nodes = new ArrayList<>();
		List<Attribute> attributes = new ArrayList<>();
		
		List<String> tags = new ArrayList<>();
		
		Element e = null;
		Attribute att = null;
		Node n = null;
		
		boolean inTag = false;
		boolean skip = false;
		boolean inAttributeContent=false;
		boolean inClosing = false;
		boolean inBody = false;
		//boolean inTextContent = false;
		
		boolean inBodyTag = false;
		
		String name = "";
		String attributeName = "";
		String attributeContent = "";
		String contentNode = "";
		String checkName = "";
		
		String closingName = "";
		
		String textContent = "";
		
		String body = this.getBody();
		
		String innerElementName = "";
		String innerElementBody = "";
		
		for(int i=0; i< body.length(); i++) {
			
			// Se ci troviamo nel body di un elemento figlio
			if(inBody) {
				innerElementBody += body.charAt(i);
				//Se ci troviamo in un tag
				if(inBodyTag) {			
					
					if(body.charAt(i) == ' ') {
						skip = true;
					}
					if(body.charAt(i) == '/') {
						if(i != 0 && body.charAt(i-1) == '<') {
							inClosing = true;
						}
						
					}
					
					if(!inClosing) {
						
						if(body.charAt(i) == '>') {
		
							tags.add(innerElementName);
							innerElementName = "";					
							inBody = true;
							//inTextContent = true;
							
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
						if(body.charAt(i) == '>') {
							if(tags.get(tags.size()-1).equals(closingName)) {
								tags.remove(tags.size()-1);
								closingName="";
							}else {
								throw new Exception("I tag del documento vengono chiusi in ordine errato.");
							}
							inTag = false;
							inClosing = false;
						}else {
							if(body.charAt(i) != '/' && body.charAt(i) != '<') {
								closingName += body.charAt(i);
							}
							
							
						}
					}
					
					
				}
				if(body.charAt(i) == '<') {
					inBodyTag = true;
				}	
				if(!inBodyTag) {
					textContent += body.charAt(i);
				}
				if(body.charAt(i) == '>') {
					inBodyTag = false;
				}
				if(checkName.equals("")) {
					if(body.charAt(i) == '<') {
						checkName += '<';
					}else {
						checkName = "";
					}
				}else {
					if(checkName.equals("<")) {
						if(body.charAt(i) == '/') {
							checkName += '/';
						}else {
							checkName = "";
						}
					}else {
						if(checkName.startsWith("</")){
							if(checkName.substring(2).equals(name) && body.charAt(i) == '>') {
								checkName += body.charAt(i);
								innerElementBody = innerElementBody.substring(0,innerElementBody.length() - checkName.length());
									
								e.setBody(innerElementBody);
								nodes.add(e);
								inBody=false;
								textContent = "";
								innerElementBody = "";
								checkName = "";
								name = "";
								
							}else {
								checkName += body.charAt(i);
								if(name.substring(0,checkName.length()-2).equals(checkName.substring(2))) {
									
								}else {
									checkName = "";
								}
							}
							
						}
					}
				}
				
			}else {
				if(inTag && body.charAt(i) == ' ') {
					skip = true;
				}
				if(inTag && body.charAt(i) == '/') {
					if(i != 0 && body.charAt(i-1) == '<') {
						inClosing = true;
					}
				}
				
				if(!inClosing) {
					
					if(body.charAt(i) == '>') {
						inTag = false;
						
						e = new Element(name);
						e.attributes = attributes;
						
						tags.add(name);
						
						attributes = new ArrayList<>();
						
						inBody = true;
						//inTextContent = true;
						
						skip = false;
					}
					
					if(!skip) {
						if(inTag) {
							name += body.charAt(i);
						}
					}else {
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
					
					if(body.charAt(i) == '>') {
						if(tags.get(tags.size()-1).equals(closingName)) {
							tags.remove(tags.size()-1);
							closingName="";
						}else {
							throw new Exception("I tag del documento vengono chiusi in ordine errato.");
						}
						inTag = false;
						inClosing = false;
					}else {
						closingName += body.charAt(i);
						
					}
				}
				
				
				
				if(body.charAt(i) == '<') {
					if(!contentNode.equals("")) {
						n = new Node();
						n.setBody(contentNode);
						nodes.add(n);
						contentNode = "";
					}
					inTag = true;
				}
				
				if(!inTag) {
					if(!inBody) {
						contentNode += body.charAt(i);
					}	
				}
			}
			
			
			
		}
		return nodes;
		
	}
	
	public List<Element> getChildElements() throws Exception{
		List<Node> nodeList = this.getChildNodes();
		List<Element> elements = new ArrayList<>();
		for(int i = 0; i< nodeList.size(); i++) {
			Node node = nodeList.get(i);
			if(node instanceof Element) elements.add((Element)node);
		}
		return elements;
	}
	
	public List<Element> getElementsByTagName(String tagName) throws Exception{
		List<Element> elementList = getChildElements();
		List<Element> elementNameList = new ArrayList<>();
		for(Element el : elementList) {
			if(el.getTagName().equals(tagName)) elementNameList.add(el);
		}
		return elementNameList;
		
	}
	
	
	public String getElementTextContent() throws Exception {
		//Element e = new Element();
		//e.setBody(this.getBody());
		String result = "";
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
		return result;
	}
	
}

