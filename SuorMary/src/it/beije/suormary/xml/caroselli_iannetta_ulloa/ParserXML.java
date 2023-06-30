package it.beije.suormary.xml.caroselli_iannetta_ulloa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ParserXML {
	//
	public static Document parserXML(String pathFile) {
		FileReader fileReader = null;
		try {
			File file = new File(pathFile);
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//<pippo>
			// = fileReader.read();
			StringBuilder r = new StringBuilder();
			//skip intestazione
			bufferedReader.readLine();
			
			while (bufferedReader.ready()) {
				r.append(bufferedReader.readLine() + "\n");
			}
			
			//Node node = 
					findNodes(r.toString());
			
			
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			//bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
		
		return null;
	}
	
	public static void findNodes(String s) {
		//List<Node> nodes = new ArrayList<>();
		StringBuilder tagName = new StringBuilder();
		StringBuilder content = new StringBuilder();
		char c = s.charAt(0);
		if (c == '<') {
			
			tagName.setLength(0);
			char ch;
			int i;
			for (i = 1; i < s.length() && (ch = s.charAt(i)) != '>'; i++) {
				ch = s.charAt(i);
				tagName.append(ch);
			}
			i++;
			int index = s.indexOf("</" + tagName.toString() + ">");
			
			content.append(s.substring(i, index -1));
			System.out.println(content.toString());
			//Node = findNode(content.toString());
			
			//node
			//findNodes
			//nodes.add(node);
			//Element el  = new Element (tagName, attributes, contents)
		}
		else if (c == '\n' || c == '\t' ){ //consider space later
			// blank node
			//nodes.add(blankNode);
			System.out.println("sono bianco");
		}
		//Node node = new Node(tagName, attributes, listChildNodes>)
		//return null;
	}
	
	public static void main (String[] args) {
		
		ParserXML.parserXML("/Users/Padawan/git/file/rubrica.xml");
	}

}
