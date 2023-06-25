package it.beije.suormary.rubrica.exFiles;

import com.sun.media.jfxmediaimpl.HostUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RubricaUtils {

    public static List<Contact> loadRubricaFromCSV(String pathFile, String separator) throws IOException {

        File file = new File(pathFile);
        if (!file.exists() && !file.canRead()) {
            System.out.println("Il file non esiste o non puo' essere letto");
        }

        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        List<Contact> newList = new ArrayList<>();

        //consumo la prima riga, la leggo
        reader.readLine();

        while (reader.ready()) {
            String r = reader.readLine();
            String[] values = r.split(separator);
            newList.add(new Contact(values[1], values[0], values[2], values[3], values[4], 0));
        }

        System.out.println(newList);
        return newList;

    }

    public static List<Contact> loadRubricaFromXML(String pathFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(pathFile);
        Element rootElement = document.getDocumentElement();
        List<Contact> contacts = new ArrayList<>();
        Contact c = new Contact();

        Element docEl = document.getDocumentElement();
        List<Element> elements = getChildElements(docEl);
        NodeList listOfContacts = rootElement.getElementsByTagName("contatto");

        // itero sugli elementi <contatto> e creo gli oggetti Contact
//        for (int i = 0; i < listOfContacts.getLength(); i++) {
//            Element contactElement = (Element) listOfContacts.item(i);
//            c.setName(contactElement.getElementsByTagName("nome").item(0).getTextContent());
//            c.setSurname(contactElement.getElementsByTagName("cognome").item(0).getTextContent());
//            c.setPhoneNumber(contactElement.getElementsByTagName("telefono").item(0).getTextContent());
//            c.setEmail(contactElement.getElementsByTagName("email").item(0).getTextContent());
//            c.setNote(contactElement.getElementsByTagName("note").item(0).getTextContent());
//            c.setAge(Integer.parseInt(contactElement.getAttribute("eta")));
        List<Element> els = null;
        for (Element el : elements) {
            System.out.println("et� contatto = " + el.getAttribute("eta"));
            //System.out.println("contenuto contatto = " + el.getTextContent());
            els = getChildElements(el);
            for (Element e : els) {
                System.out.println(e.getTagName() + " = " + e.getTextContent());

                switch (e.getTagName()) {
                    case "nome": c.setName(e.getTextContent());
                        break;
                    case "cognome": c.setSurname(e.getTextContent());
                        break;
                    case "telefono": c.setPhoneNumber(e.getTextContent());
                        break;
                    case "email": c.setEmail(e.getTextContent());
                        break;
                    case "note": c.setNote(e.getTextContent());
                        break;
                    default: System.out.println("TagName non riconosciuto!");
                        break;
                }
        }

            contacts.add(c);
        }
        System.out.println(contacts);
        return contacts;
    }

    public void writeRubricaCSV(List<Contact> contacts, String pathFile, String separator) {

    }

    public static List<Element> getChildElements(Element el) {
        NodeList nodeList = el.getChildNodes();
        //System.out.println("nodeList size: " + nodeList.getLength());
        List<Element> elements = new ArrayList<Element>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node n = nodeList.item(i);
            if (n instanceof Element) elements.add((Element) n);
        }

        return elements;
    }



    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

//        loadRubricaFromCSV("/home/flaviana/dev/corso-beije/AcademyJavaXVII/SuorMary/src/rubrica.csv", ";");
        loadRubricaFromXML("/home/flaviana/dev/corso-beije/AcademyJavaXVII/SuorMary/src/rubrica.xml");
    }

}

