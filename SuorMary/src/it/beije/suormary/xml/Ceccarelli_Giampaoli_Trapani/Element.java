package it.beije.suormary.xml.Ceccarelli_Giampaoli_Trapani;

import java.util.List;

public class Element {
	
	private String tagName;
	private Attributes attributes;
	private List<Element> childEl;
	private String values;
	
	public Element() {
	}
	
	public Element(String tagName,List<Element> childEl, String values) {
		
		this.tagName = tagName;
		this.attributes = new Attributes();
		this.childEl = childEl;
		this.values = values;
	}

	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	
	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	public List<Element> getChildEl() {
		return childEl;
	}
	public void setChildEl(List<Element> childEl) {
		this.childEl = childEl;
	}

	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	
	

}
