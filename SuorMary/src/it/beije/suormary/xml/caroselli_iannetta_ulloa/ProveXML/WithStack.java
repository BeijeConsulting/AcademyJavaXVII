package it.beije.suormary.xml.caroselli_iannetta_ulloa.ProveXML;

import java.io.*;

public class WithStack {


    public static Node parse(String xml) {


        xml = xml.trim();
        if (xml.startsWith("<") && xml.endsWith(">")) {
            //prendo il tagName
            String tagName = xml.substring(1, xml.indexOf(">"));
            Node node = new Node(tagName, null);

            //prendi il contenuto
            String content = xml.substring(xml.indexOf(">") + 1, xml.lastIndexOf("<")).trim();

            //controllo se ci sono tag innestati
            while (content.startsWith("<")) {
                //per i tag innestati, li metto in child
                int nextTagEndIndex = content.indexOf(">");
                String childTagName = content.substring(1, nextTagEndIndex);

                int childTagCloseIndex = content.indexOf("</" + childTagName + ">");
                String childContent = content.substring(0, childTagCloseIndex + childTagName.length() + 3);

                //aggiungo il figlio al nodo padre
                Node childNode = parse(childContent);
                node.addChild(childTagName, childNode);

                // Remove the parsed child content
                content = content.substring(childTagCloseIndex + childTagName.length() + 3).trim();
            }

            //se non ci sono tag innestati, il contenuto e' del nodo padre
            if (content.length() > 0 && !content.startsWith("<")) {
                node.setValue(content);
            }

            return node;
        } else {
            throw new IllegalArgumentException("Invalid XML format: " + xml);
        }

    }


    public static void main(String[] args) throws IOException {

        String path = "/home/flaviana/IdeaProjects/prove/src/TryingWIthStack/test.xml";
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



