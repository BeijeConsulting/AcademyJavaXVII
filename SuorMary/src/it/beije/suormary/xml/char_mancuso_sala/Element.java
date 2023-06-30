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
	
	public String getTextContent() {
		
	}
	
	public String getAttributes(){
		
	}
	
	public String getAttribute() {
		
	}
}

