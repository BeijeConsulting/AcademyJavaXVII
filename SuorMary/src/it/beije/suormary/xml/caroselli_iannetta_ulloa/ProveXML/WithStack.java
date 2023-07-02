package it.beije.suormary.xml.caroselli_iannetta_ulloa.ProveXML;

import java.io.*;
import java.util.Map;

public class WithStack {


    public static Node parse(String xml) {

    	Node node  = null;
        xml = xml.trim();
        String content = xml.trim();
        if (xml.startsWith("<") && xml.endsWith(">")) {
            //prendo il tagName
        	
            String element = xml.substring(1, xml.indexOf(">"));
            ScanAngleBrackets angleBrackets= new ScanAngleBrackets(element);
            String tagName = angleBrackets.getTag();
            Map<String, String> attributes = angleBrackets.getAttributes();
            node = new Node(tagName, attributes);

            //prendi il contenuto
            content = xml.substring(xml.indexOf(">") + 1, xml.lastIndexOf("<")).trim();

            //controllo se ci sono tag innestati
            while (content.startsWith("<")) {
                //per i tag innestati, li metto in child
                int nextTagEndIndex = content.indexOf(">");
                String childElement = content.substring(1, nextTagEndIndex);
                ScanAngleBrackets childAngleBrackets= new ScanAngleBrackets(childElement);
                String childTagName = childAngleBrackets.getTag();
                
                int childTagCloseIndex = content.indexOf("</" + childTagName + ">");
                String childContent = content.substring(0, childTagCloseIndex + childTagName.length() + 3);

                //aggiungo il figlio al nodo padre
                Node childNode = parse(childContent);
                node.addChild(childNode);
                //node.addChild(childTagName, childNode);

                // Remove the parsed child content
                content = content.substring(childTagCloseIndex + childTagName.length() + 3).trim();
            }

            //se non ci sono tag innestati, il contenuto e' del nodo padre
            if (content.length() > 0 && !content.startsWith("<")) {
                node.setValue(content);
            }

        } 
            else {
            	if (content.length() > 0 && !content.startsWith("<")) {
                    node.setValue(content);
                }
            //System.out.println("End file");
        	//throw new IllegalArgumentException("Invalid XML format: " + xml);
        }
        return node;
    }


    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\Chiara\\Desktop\\Academy\\esercizi\\xml_parser_test\\test_parser2.xml";
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



