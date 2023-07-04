package it.beije.suormary.xml.Ceccarelli_Giampaoli_Trapani;


public class Element extends Node {


	    private String values;
	    

	    public Element() {
	    }

	    public Element(String tagName) {

	        this.tagName = tagName;
	    }


	    public String getValues() {
	        return values;
	    }
	    public void setValues(String values) {
	        this.values = values;
	    }



	}
