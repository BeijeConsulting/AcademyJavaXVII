package it.beije.suormary.xml.caroselli_iannetta_ulloa.ProveXML;

import java.util.HashMap;

public class ParseUtilities {

	public boolean isSelfClosingTag(String s) {
		//System.out.println(tagName.endsWith("/"));
		return s.endsWith("/");
		
	}
	
//	public Node commentNode(String content) {
//		
//		if (content.startsWith("<!--") ){
//    		content = content.substring(content.indexOf("!") - 1, content.indexOf("-->") + 3);
//        	child = new Node("comment", new HashMap<>(), childContent);
//        	node[0].addChild(child);
//        	content = content.substring(content.indexOf("--") + 3);
//        }
//	}
}
