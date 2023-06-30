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
	
	public Element(String tagName, String content) {
		attributes = new ArrayList<>();
		this.setTagName(tagName);
		this.setTextContent(content);
	}
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagName() {
		return this.tagName;
	}
	
	public String getAttributes(){
		String result = "";
		for(Attribute a : attributes) {
			result += a.toString();
		}
		return result;
	}
	
	public String getAttribute() {
		return null;
	}
	
	public List<Node> getChildNodes(){
		
		//StringBuilder sb = new StringBuilder(body);
		List<Node> nodes = new ArrayList<>();
		List<Attribute> attributes = new ArrayList<>();
		
		Element e = null;
		Attribute att = null;
		Node n = null;
		
		boolean inTag = false;
		boolean skip = false;
		boolean inAttributeContent=false;
		boolean inClosing = false;
		boolean inBody = false;
		boolean inTextContent = false;
		
		boolean inBodyTag = false;
		
		String name = "";
		String attributeName = "";
		String attributeContent = "";
		String contentNode = "";
		String checkName = "";
		
		String textContent = "";
		
		String body = this.getBody();
		
		String innerElementBody = "";
		
		for(int i=0; i< body.length(); i++) {
			
			if(inBody) {
				innerElementBody += body.charAt(i);
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
								e.setTextContent(textContent);
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
					inClosing = true;
				}
				
				if(!inClosing) {
					
					if(body.charAt(i) == '>') {
						inTag = false;
						
						e = new Element(name);
						e.attributes = attributes;
						
						attributes = new ArrayList<>();
						
						inBody = true;
						inTextContent = true;
						
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
								
							} else {
								attributeContent += body.charAt(i);
							}
						}
					}
					
					
					
				} else {
					if(body.charAt(i) == '>') {
						inTag = false;
						inClosing = false;
					}
				}
				
				
				
				if(body.charAt(i) == '<') {
					if(!contentNode.equals("")) {
						n = new Node();
						n.setBody(contentNode);
						n.setTextContent(contentNode);
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
	
	public List<Element> getChildElements(){
		return null;
	}
	
	public List<Element> getElementsByTagName(String tagName){
		return null;
	}
}

