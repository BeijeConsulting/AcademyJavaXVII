package it.beije.suormary.xml.caroselli_iannetta_ulloa;
import java.util.*;


public class Node {
	
	public static List<Node> tree =  new ArrayList<>();
	
    private String tagName;
    private Map<String, String> attributes = new HashMap<>();
    private Element element;
    private String value;
    private List<Node> listOfChildren = new ArrayList<>();
    
    public Node(String tagName, Map<String, String> attributes) {
        this(tagName, attributes, "");
    }

    public Node(String tagName, Map<String, String> attributes, String value) {
        this.tagName = tagName;
        this.attributes = attributes;
        this.element = new Element(tagName, attributes);
        this.value = value;
        tree.add(this);
    }

    public void addChild(Node child) {
        listOfChildren.add(child);
    }
    
    public static List<Node> getTree(){
    	return tree;
    }
    
    public String getTagName() {
        return tagName;
    }

	public Map<String,String> getAttributes() {
		return attributes;
	}

	public Element getElement() {
		return element;
	}
	
	public String getAttribute(String attribute) {
		return attributes.get(attribute);
	}
	
/*	public String getValue() {
//        return value;
    }*/

    public void setValue(String value) {
        this.value = value;
    }

    public List<Node> getChildNodes() {
        return listOfChildren;
    }
    
    public String getTextContent(){
    	StringBuilder s = new StringBuilder();
    	if (listOfChildren.isEmpty()) return value;
    	
    	for (Node c : listOfChildren) {
    		s.append(c.getTextContent() + "\n");
    	}
    	
    	return s.toString();
    }
    
    
    public List<Element> getChildElements() {
    	List<Element> listOfElements = new ArrayList<>();
    	for (Node c : listOfChildren) {
    		if (!c.tagName.equals("comment")) {
    			listOfElements.add(new Element(c.tagName, c.attributes));
    		}
    	}
    	return listOfElements;
    }
    
    @Override
    public String toString() {
    	//System.out.println("print attributes : " + listOfChildren.toString());
    	return "Node: tagName = " + tagName + ", attributes: " + attributes + 
    											 ", value: " + value +
    											 ", children: " + listOfChildren + "\n";

    } 

}
