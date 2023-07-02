package it.beije.suormary.xml.caroselli_iannetta_ulloa.ProveXML;

import java.io.*;
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
        boolean isOpen = false;
        
        if (xml.startsWith("<") && xml.endsWith(">")) {
        	
            //prendo il tagName
            String element = xml.substring(1, xml.indexOf(">"));
            
            // controlla se è un commento
            if (element.startsWith("!--") ){
            	node[0] = new Node("comment", new HashMap<>(), content.substring(3, content.indexOf("--")));
            }
            
            else { //non è un commento
            	
            	ScanAngleBrackets angleBrackets= new ScanAngleBrackets(element);
                String tagName = angleBrackets.getTag();
                isOpen = tagName.endsWith("/");
                Map<String, String> attributes = angleBrackets.getAttributes();
                
                if (isOpen) {
                	
                	node[0] = new Node(tagName.replace("/", ""), attributes, "");	
                }
                else {
                	node[0] = new Node(tagName, attributes);
                	
                	 //prendi il contenuto
                    content = xml.substring(xml.indexOf(">") + 1, xml.lastIndexOf("<")).trim();
                                
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
                    	String childElement = content.substring(1, nextTagEndIndex);
                    	ScanAngleBrackets childAngleBrackets= new ScanAngleBrackets(childElement);
                    	String childTagName = childAngleBrackets.getTag();
                    	
                    	isOpen = childTagName.endsWith("/");
                    	if (isOpen) {
                    		child = new Node(tagName.replace("/", ""), childAngleBrackets.getAttributes(), "");	
                    	}
                    	else {
                    		int childTagCloseIndex = content.indexOf("</" + childTagName + ">");
                    		childContent = content.substring(0, childTagCloseIndex + childTagName.length() + 3);
                    		child = parse(childContent, node[0]);
                    		node[0].addChild(child);
                    		content = content.substring(childTagCloseIndex + childTagName.length() + 3).trim();  
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
        return node[0];
    }


    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\Chiara\\Desktop\\Academy\\esercizi\\xml_parser_test\\test_parser6.xml";
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder s = new StringBuilder();

        bufferedReader.readLine();

        while (bufferedReader.ready()) {

            s.append(bufferedReader.readLine());
//            System.out.println(s);

        }
        
        Node root = parse(s.toString());
        System.out.println(root);
    }
}



