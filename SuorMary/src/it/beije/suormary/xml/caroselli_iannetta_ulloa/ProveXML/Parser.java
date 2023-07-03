package it.beije.suormary.xml.caroselli_iannetta_ulloa.ProveXML;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Parser {
	
    public static Node parse(String xml, Node...node) {

    	if (node.length == 0) {
    		node = new Node[1];
    	}
    	
    	
    	Node child = null; 
        xml = xml.trim();
        String content = xml.trim();
        String childContent;
        ParseUtilities parseUtilities= new ParseUtilities();
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
                isSelfClosingTag = parseUtilities.isSelfClosingTag(tagName);
                Map<String, String> attributes = element.getAttributes();
                
                //se la root è selfclosing tag
                if (isSelfClosingTag) { 
                	node[0] = new Node(tagName.replace("/", ""), attributes, "");	
                }
                else {
                	node[0] = new Node(tagName, attributes);
                	
                	//prendi il contenuto
                	if(xml.lastIndexOf("<") <= 0) {
                		System.out.println("Sono entrato");
                		throw new StringIndexOutOfBoundsException("Invalid XML format at '" + xml + "'");
                	}
                    content = xml.substring(xml.indexOf(">") + 1, xml.lastIndexOf("<")).trim();
                    System.out.println(content);
                    
                    //prendi il contenuto
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
                    	
                    	//System.out.println("<   " + childAngleBrackets + "   >");
                    	
                    	Element childElement= new Element(childAngleBrackets);
                    	String childTagName = childElement.getTag();
                    	Map<String, String> childAttributes = childElement.getAttributes();
                    	
                    	//System.out.println("attributi " + childAttributes);
                    	
                    	isSelfClosingTag = parseUtilities.isSelfClosingTag(childAngleBrackets);
                    	if (isSelfClosingTag) {
                    		child = new Node(childTagName.replace("/", ""), childAttributes, "");	
                    		node[0].addChild(child);
                    		content = content.substring(nextTagEndIndex + 1).trim();
  							//System.out.println("sono un contenuto if " + content);
                    	}
                    	else {
                    		int childTagCloseIndex = content.indexOf("</" + childTagName + ">");
                    		childContent = content.substring(0, childTagCloseIndex + childTagName.length() + 3);
                    		child = parse(childContent, node[0]);
                    		node[0].addChild(child);
                    		content = content.substring(childTagCloseIndex + childTagName.length() + 3).trim();  
                    		//System.out.println("sono un contenuto else" + content);
                    	}
                    }                  
                }
                if (!content.contains("<")) node[0].setValue(content);
            }             	
        } 
        }
        else {
        node[0].setValue(content);
        //throw new IllegalArgumentException("Invalid XML format: " + xml);
        }
        
//        System.out.println("nome: " + node[0].getTagName());
//        for (Node c : node[0].getChildNodes()) 	System.out.println("\tfiglio: " + c.getTagName());
    	
        return node[0];
    }


    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\Chiara\\Desktop\\Academy\\esercizi\\xml_parser_test\\test_parser3.xml";
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder s = new StringBuilder();

        bufferedReader.readLine();

        while (bufferedReader.ready()) {

            s.append(bufferedReader.readLine());
//            System.out.println(s);

        }
        //Node root = new NuovoParser().parse(s.toString());
        Node root = parse(s.toString());
        System.out.println(root);
    }
}



