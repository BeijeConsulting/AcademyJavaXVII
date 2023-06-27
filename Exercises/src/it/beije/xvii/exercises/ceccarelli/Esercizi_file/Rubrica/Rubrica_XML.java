package it.beije.xvii.exercises.ceccarelli.Esercizi_file.Rubrica;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Rubrica_XML {
	
	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document document;
	private Element docEl;
	//System.out.println(docEl.getTagName());
	
		
	public Rubrica_XML() {
		this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			this.documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Contatto> loadRubricaFromXML(String pathFile) {
		List<Contatto> list = null;
			try {
				this.document = documentBuilder.parse(pathFile);
			
			}catch (IOException e) {
				
				e.printStackTrace();
			
			} catch (SAXException e) {
				e.printStackTrace();
			}
			
			docEl = document.getDocumentElement();
		return list;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rubrica_XML rxml = new Rubrica_XML();
		String path = "/Users/Padawan/eclipse-workspace/File/rubrica.xml";

	}

}



