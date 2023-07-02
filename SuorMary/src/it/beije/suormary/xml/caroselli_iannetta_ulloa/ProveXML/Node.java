package it.beije.suormary.xml.caroselli_iannetta_ulloa.ProveXML;
import java.util.*;


public class Node {
	public static List<Node> tree =  new ArrayList<>();
    private String tagName;
    private Map<String, String> attributes = new HashMap<>();
    private String value;
    private List<Node> listOfChildren = new ArrayList<>();
    private static int level = 0;
    
    public Node(String tagName, Map<String, String> attributes) {
        this(tagName, attributes, "");
    }

    public Node(String tagName, Map<String, String> attributes, String value) {
        this.tagName = tagName;
        this.attributes = attributes;
        this.value = value;
        tree.add(this);
       // this.listOfChildren ;
    }

    public void addChild(Node child) {
        listOfChildren.add(child);
    }
    

    public String getTagName() {
        return tagName;
    }

//    public void setTagName(String tagName) {
//        this.tagName = tagName;
//    }

	public String getAttributes() {
		return Arrays.asList(attributes).toString();
	}

	public String getAttribute(String attribute) {
		return attributes.get(attribute);
	}
	
//	public void setAttributes(Map<String, String> attributes) {
//		this.attributes = attributes;
//	}
	
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Node> getChildren(Node node) {
        return listOfChildren;
    }

    @Override
    public String toString() {
    	return "Node: tagName = " + tagName + "\tattributes: " + Arrays.asList(attributes) + 
    											 "\nvalue: " + value +
    											 "\nchildren: \t" + listOfChildren.toString() + "\n";

    }
    
    
    public String getRootElement() {
    	return "Tag name : " + tree.get(0).tagName  + " Attributes + " + Arrays.asList(attributes);
    }
    
    public String getChildNodes(){
    	StringBuilder s = new StringBuilder();
    	for (Node c : listOfChildren) s.append(c.toString() + "\n");
    	return s.toString();
    }
    
    public String getChildElement() {
    	StringBuilder s = new StringBuilder();
    	for (Node c : listOfChildren) {
    		if (!c.tagName.equals("comment")) {
    			s.append("Tag name : " + c.tagName  + " Attributes + " + Arrays.asList(attributes));
    		}
    	}
    	return s.toString();
    }
    
    public String getElementsByTagName(String tagName) {
    	StringBuilder s = new StringBuilder();
    	for (Node c : tree) {
    		if (c.tagName.equals(tagName)) {
    			s.append("Tag name : " + c.tagName  + " Attributes + " + Arrays.asList(attributes));
    		}
    	}
    	return s.toString();
    }
    
//    public String getTextContent(){
//    	
//    }
    
    
    
    

}
