package it.beije.suormary.xml.caroselli_iannetta_ulloa.ProveXML;

import java.util.HashMap;
import java.util.Map;

public class NuovoParser {
	
	public Node parse(String xml, Node...node) {
		if (node.length == 0) {
    		node = new Node[1];
    	}
		
		String content = xml.trim();
		boolean isSelfClosingTag = false;
		String angleBrackets;
		Element element;
		String tagName;
		Map<String, String> attributes;
		
		int start = 0;
		while (content.startsWith("<") && content.endsWith(">")) {
		          
            // controlla se è un commento
            	if (content.startsWith("<!--") ){
            		angleBrackets = content.substring(0, content.indexOf("-->") +3);
            		node[0] = new Node("comment", new HashMap<>(), angleBrackets);
            		start = angleBrackets.length();
            	}
            	
            	else {
            		//prendo il tagName
                    angleBrackets = content.substring(1, content.indexOf(">"));
                    element = new Element(angleBrackets);
                    tagName = element.getTag();
                    attributes = element.getAttributes();
                    
                    isSelfClosingTag = tagName.endsWith("/");
                    if (isSelfClosingTag) {
                		node[0] = new Node(tagName.replace("/", ""), attributes, "");
                		start = angleBrackets.length() + 2; 
                	}
                    else {
                    	int startInnerContent = content.indexOf(">") + 1;
                    	int endInnerContent = content.indexOf("</" + tagName + ">");
                    	node[0] = new Node(tagName, attributes);
                    	String innerContent = content.substring(startInnerContent, endInnerContent).trim();
                    	node[0].addChild(parse(innerContent));
                    	start = endInnerContent + tagName.length() + 3;
                    } 
            	}
            	//System.out.println("start = " + start);
            	content = content.substring(start).trim();
            	System.out.println(content);
            	start = 0;
            	if (!content.contains("<") ) node[0].setValue(content);
            }   
			//System.out.println("exit");
		return node[0];
	}
	
}
