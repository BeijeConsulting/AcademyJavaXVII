package it.beije.suormary.xml.caroselli_iannetta_ulloa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document {
	
	private Node root;
	private List<Node> tree;
		
	public Node getRoot() {return root;}
	
	public List<Node> getTree () { return tree;}
	
	public Document(String xml) {
		root = createNode(xml);
		tree = Node.getTree();
	}
	
	// public List<Nodes>
	public List<Element> getElementsByTagName(String tagName) {
    	List<Element> listOfElements = new ArrayList<>();
    	for (Node c : tree) {
    		if (c.getTagName().equals(tagName)) {
    			listOfElements.add(new Element(c.getTagName(), c.getAttributes()));
    		}
    	}
    	return listOfElements;
    }
	
	public Element getRootElement() {
    	return tree.get(0).getElement();
    }
	
	private Node createNode(String xml, Node...node) {
    	if (node.length == 0) {
    		node = new Node[1];
    	}
    	
    	Node child = null; 
        xml = xml.trim();
        String content = xml.trim();
        String childContent;
        boolean isSelfClosingTag = false;
        
        if (xml.startsWith("<") && xml.endsWith(">")) {
        	
            //prendo il tagName
            String angleBrackets = xml.substring(1, xml.indexOf(">"));
            
            // controlla se è un commento
            if (angleBrackets.startsWith("!--") ){
            	node[0] = new Node("comment", new HashMap<>(), content.substring(0, content.indexOf("--") + 3));
            }
            
            else { //non è un commento
            	
            	Element element = new Element(angleBrackets);
                String tagName = element.getTag();
                isSelfClosingTag = isSelfClosingTag(tagName);
                Map<String, String> attributes = element.getAttributes();
                
                //se la root è selfclosing tag
                if (isSelfClosingTag) { 
                	node[0] = new Node(tagName.replace("/", ""), attributes, "");	
                }
                else {
                	node[0] = new Node(tagName, attributes);
                	
                	//prendi il contenuto
                	if(xml.lastIndexOf("<") <= 0) {
                		throw new StringIndexOutOfBoundsException("Invalid XML format at '" + xml + "'");
                	}
                    content = xml.substring(xml.indexOf(">") + 1, xml.lastIndexOf("<")).trim();
                                                   
                    //controllo se ci sono tag innestati
                    while (content.startsWith("<")) {
                	
                    	if (content.startsWith("<!--") ){
                    		childContent = content.substring(content.indexOf("!") - 1, content.indexOf("-->") + 3);
                    		child = new Node("comment", new HashMap<>(), childContent);
                    		node[0].addChild(child);
                    		content = content.substring(content.indexOf("--") + 3);
                    	}
                    	else {
                    		//per i tag innestati, li metto in child
                    		int nextTagEndIndex = content.indexOf(">");
                    		String childAngleBrackets = content.substring(1, nextTagEndIndex);
                    	
                    		Element childElement= new Element(childAngleBrackets);
                    		String childTagName = childElement.getTag();
                    		Map<String, String> childAttributes = childElement.getAttributes();
                    	
                    		isSelfClosingTag = isSelfClosingTag(childAngleBrackets);
                    		if (isSelfClosingTag) {
                    			child = new Node(childTagName.replace("/", ""), childAttributes, "");	
                    			node[0].addChild(child);
                    			content = content.substring(nextTagEndIndex + 1).trim();
                    		}
                    		else {
                    			int childTagCloseIndex = content.indexOf("</" + childTagName + ">");
                    			childContent = content.substring(0, childTagCloseIndex + childTagName.length() + 3);
                    			child = createNode(childContent, node[0]);
                    			node[0].addChild(child);
                    			content = content.substring(childTagCloseIndex + childTagName.length() + 3).trim();
                    		}
                    	}                  
                    }
                    if (!content.contains("<")) node[0].setValue(content);
                }             	
            } 
        }
        else node[0].setValue(content);

        return node[0];
    }
    
	private boolean isSelfClosingTag(String s) {
		return s.endsWith("/");
		
	}
}



