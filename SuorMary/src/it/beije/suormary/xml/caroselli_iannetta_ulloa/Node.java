package it.beije.suormary.xml.caroselli_iannetta_ulloa;

import java.util.List;

public class Node {
 
	private String tagName;
	private String content;
	private boolean isBlank;
	private List <Node> listOfChildren;
	
	public Node() {
		tagName = null;
		content = null;
		isBlank = true;
		listOfChildren = null;
	}
	
	public Node (String tagName, List<Node> listOfChildren) {
		this.tagName = tagName;
		isBlank = false;
		this.listOfChildren = listOfChildren;
	}
	
	public Node(String tagName, String content) {
		this.tagName = tagName;
		this.content = content;
		isBlank = false;
	}
	
	public String getTagname () {return tagName;}
	public boolean isBlank() {return isBlank;}
	
	@Override
	public String toString() {
		return "Node [tagName=" + tagName + "]";
	}

	public void setName (String tagName) {this.tagName = tagName;}
	
	//public default String getTextContent(Node node) {return "";}
	
	//public default List<Node> getChildNodes (Node node) { return null;}
	
}
