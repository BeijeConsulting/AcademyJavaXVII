package it.beije.suormary.xml.caroselli_iannetta_ulloa.ProveXML;
import java.util.*;


public class Node {
    private String tagName;
    private Map<String, String> attributes;
    private String value;
    private List<Node> listOfChildren;
    //private Map<String, List<Node>> children;
    //private static int level = 0;
    
    public Node(String tagName, Map<String, String> attributes) {
        this(tagName, attributes, null);
    }

    public Node(String tagName, Map<String, String> attributes, String value) {
        this.tagName = tagName;
        this.attributes = attributes;
        this.value = value;
        this.listOfChildren = new ArrayList<>();
        //this.children = new HashMap<>();
        //this.children.put(this.tagName, this.listOfChildren); 
    }

    public void addChild(Node child) {
        listOfChildren.add(child);
    }
    
//    public void addChild(String tagName, Node child) {
//        listOfChildren.add(child);
//    	//this.children.put(tagName, child);
//    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
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
//    	if (!listOfChildren.isEmpty()) level++;
//    	StringBuilder tab = new StringBuilder("");
//    	for (int i = 0; i <level; i++) tab.append("\t");
    	StringBuilder s = new StringBuilder("Node{" +
    	         "tagName='" + tagName + "'," +
    	         "\n" + attributes.toString() + "," +
    	         "\nvalue='" + value + "'," +
    	         ", children=" + listOfChildren.toString());
//    	if (!listOfChildren.isEmpty()) {
//    		for (Node child: listOfChildren) {
//    			String c = child.toString();
//    			s.append("\t" + c);
//    		}
//    	}
    	
    	s.append("}");
    	 return s.toString();
    }

}
