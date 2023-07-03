package it.beije.suormary.xml.Ceccarelli_Giampaoli_Trapani;

import java.util.List;
import java.util.ArrayList;

public class Node {
	
	protected String tagName;
	private List<Attributes> attributes;
	private List<Node> childEl;
	
	 public Node() {
	        this.childEl = new ArrayList<Node>();
	    }

	    public Node(String tagName) {

	        this.tagName = tagName;
	        this.attributes = new ArrayList<Attributes>();
	        this.childEl = new ArrayList<Node>();

	    }

	    public String getTagName() {
	        return tagName;
	    }
	    public void setTagName(String tagName) {
	        this.tagName = tagName;
	    }
	    
	    
	 
	    public List<Attributes> getAttributes() {
			return attributes;
		}

		public void setAttributes(List<Attributes> attributes) {
			this.attributes = attributes;
		}

		public List<Node> getChildEl() {
	        return childEl;
	    }
	    public void setChildEl(List<Node> childEl) {
	        this.childEl = childEl;
	    }
	    
	    public void printNode() {

	        System.out.print(tagName);

	        for (Attributes a : attributes) {
	            System.out.print("nome: " + a.getName());
	            System.out.print("valore: " + a.getValue());
	        }


//	        for (Node n : childEl) {
//	            System.out.println(n.tagName);
	//
//	            if (n.childEl.size() > 0) {
//	                for () {
//	                System.out.println();
//	            } }
//	        }

	    }


}
