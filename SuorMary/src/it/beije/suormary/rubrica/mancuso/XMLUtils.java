package it.beije.suormary.rubrica.mancuso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLUtils {
	
	public static Document getDocument() throws ParserConfigurationException {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();
		
		return document;
	}
	
	public static Document getDocument(String pathFile) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(pathFile);
		
		return document;
	}
	
	public static void write(Document document, File file) throws TransformerException {
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
							
		StreamResult result = new StreamResult(file);
			
		// Output to console for testing
		//StreamResult syso = new StreamResult(System.out);
			
		transformer.transform(source, result);
		//transformer.transform(source, syso);
	}
	
	public static List<Element> getChildrenElements(Element el) {
		NodeList nodeList = el.getChildNodes();
		//System.out.println("nodeList size: " + nodeList.getLength());
		List<Element> elements = new ArrayList<Element>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			if (n instanceof Element) elements.add((Element) n);
		}
		
		return elements;
	}
	
}
