//in futuro: deve estendere nodes, per avere metodi getChildNodes, getChildlements

package it.beije.suormary.xml.caroselli_iannetta_ulloa;

import java.util.HashMap;
import java.util.Map;

public class Element {

	String tag;
	Map<String, String> attributes = new HashMap<>();
	
	public String getTag() {
		return tag;
	}
	
	public Map<String, String> getAttributes() {
		return attributes;
	}
	
	public Element(String tag, Map<String, String> attributes) {
		this.tag = tag;
		this.attributes = attributes;
	}
	
	public Element(String s) {
		int index = s.indexOf(" ");
		if (index == -1) {
			this.tag = s;
		}
		else {
			this.tag = s.substring(0, index);
			String content = s.substring(index +1);
			this.attributes = splitAttributes(content);
		}
	}
	
	public Map<String, String> splitAttributes(String content) {
		Map<String, String> attributes = new HashMap<>();
		String attributeName;
		String attributeValue;
		int index;
		if (content.endsWith("/")) content = content.substring(0, content.length()-1);
		for (String sub : content.split("\" ")) {
			index = sub.indexOf("=");
			attributeName = sub.substring(0, index);
			attributeValue = sub.substring(index + 1).replaceAll("\"", "");
			attributes.put(attributeName, attributeValue);
		}
		return attributes;
	}

	
	@Override
	public String toString() {
		return "Element [tag=" + tag + ", attributes=" + attributes + "]";
	}


}
