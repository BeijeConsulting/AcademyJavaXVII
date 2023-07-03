package it.beije.suormary.xml.Ceccarelli_Giampaoli_Trapani;

import java.util.List;
import java.util.ArrayList;

public class Node {
	
	protected String tagName;
	private Attributes attributes;
	private List<Node> childEl;
	
	 public Node() {
	        this.childEl = new ArrayList<Node>();
	    }

	    public Node(String tagName) {

	        this.tagName = tagName;
	        this.attributes = new Attributes();
	        this.childEl = new ArrayList<Node>();

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

	    public List<Node> getChildEl() {
	        return childEl;
	    }
	    public void setChildEl(List<Node> childEl) {
	        this.childEl = childEl;
	    }


}
