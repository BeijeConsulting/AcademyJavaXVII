package it.beije.suormary.xml.caroselli_iannetta_ulloa.ProveXML;

import java.util.HashMap;
import java.util.Map;

public class ScanAngleBrackets {

	String tag;
	Map<String, String> attributes = new HashMap<>();
	
	public String getTag() {
		return tag;
	}
	
	public Map<String, String> getAttributes() {
		//System.out.println(this.attributes);
		return attributes;
		
	}
	
	public ScanAngleBrackets(String s) {
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
		for (String sub : content.split("\" ")) {
			index = sub.indexOf("=");
			attributeName = sub.substring(0, index);
			attributeValue = sub.substring(index + 1).replaceAll("\"", "");
			attributes.put(attributeName, attributeValue);
		}
		return attributes;
	}
}
