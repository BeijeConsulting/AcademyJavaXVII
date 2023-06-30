package it.beije.suormary.xml.caroselli_iannetta_ulloa;

import java.util.List;

public abstract class ElementNode extends Node{ 

	public String getTagName (ElementNode el) {return "";}
	
	public List<ElementNode> getElementsByTagName (String tagName) { return null;}
	
	public List<ChildNode> getChildElement (ElementNode el) { return null;}
	
	public List<Node> childNodes;
	
}
