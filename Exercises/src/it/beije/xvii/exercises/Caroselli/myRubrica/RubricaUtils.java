package it.beije.xvii.exercises.Caroselli.myRubrica;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RubricaUtils {

    public static List<Contact> loadRubricaFromCSV(String pathFile, String separator) {

        File file = new File(pathFile);
        if (!file.exists() && !file.canRead()) {
            System.out.println("Il file non esiste o non puo' essere letto");
        }

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            List<Contact> newList = new ArrayList<>();

            //consumo la prima riga, la leggo
            reader.readLine();

            while (reader.ready()) {
                String r = reader.readLine();
                String[] values = r.split(separator);
                newList.add(new Contact(values[1], values[0], values[2], values[3], values[4]));
            }

            System.out.println(newList);
            return newList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Contact> loadRubricaFromXML(String pathFile) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(pathFile);
            List<Contact> contacts = new ArrayList<>();
            Contact c = new Contact();

            Element docEl = document.getDocumentElement();
            List<Element> elements = getChildElements(docEl);

            List<Element> els;
            for (Element el : elements) {
                System.out.println("et� contatto = " + el.getAttribute("eta"));
                els = getChildElements(el);
                for (Element e : els) {
                    System.out.println(e.getTagName() + " = " + e.getTextContent());

                    switch (e.getTagName()) {
                        case "nome":
                            c.setName(e.getTextContent());
                            break;
                        case "cognome":
                            c.setSurname(e.getTextContent());
                            break;
                        case "telefono":
                            c.setPhoneNumber(e.getTextContent());
                            break;
                        case "email":
                            c.setEmail(e.getTextContent());
                            break;
                        case "note":
                            c.setNote(e.getTextContent());
                            break;
                        default:
                            System.out.println("TagName non riconosciuto!");
                            break;
                    }
                }

                contacts.add(c);
            }
            System.out.println(contacts);
            return contacts;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeRubricaCSV(List<Contact> contacts, String pathFile, String separator) {
        boolean fileExists = new File(pathFile).exists();

        try (FileWriter fileWriter = new FileWriter(pathFile, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            // Se il file esiste già aggiungi un separatore di riga iniziale
            if (fileExists) {
                bufferedWriter.newLine();
            }

            // Scrivi i nuovi contatti in coda al file
            for (Contact contact : contacts) {
                String row = contact.getName() + separator + contact.getEmail() + separator + contact.getPhoneNumber();
                bufferedWriter.write(row);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Errore durante la scrittura del file", e);
        }


    }

    public static void writeRubricaFromXML() {

    }

    public static void readFromDb() {

        Connection connection = null;
        Statement statement = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "password");

            statement = connection.createStatement();
            System.out.println("connection open? " + !connection.isClosed());

            //SELECT
            ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");

            while (rs.next()) {

                System.out.println("id : " + rs.getInt("id"));
                System.out.println("name : " + rs.getString("name"));
                System.out.println("surname : " + rs.getString("surname"));
                System.out.println("phone : " + rs.getString("phone"));
                System.out.println("email : " + rs.getString("email"));
                System.out.println("note : " + rs.getString("note"));

                System.out.println("---------------");
            }
            rs.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
//        } finally {
//            try {
//                if (connection != null && statement != null) {
//                    statement.close();
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }


    }



    public static List<Element> getChildElements(Element el) {
        NodeList nodeList = el.getChildNodes();
        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node n = nodeList.item(i);
            if (n instanceof Element) elements.add((Element) n);
        }

        return elements;
    }


    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        Contact contact1 = new Contact("Pippo", "Rossi", "09876543", "Pippo@beije.it", "friend");
        Contact contact2 = new Contact("Minnie", "Bianchi", "09876883", "Minnie@beije.it", "bestFriend");

        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact1);
        contacts.add(contact2);

////        loadRubricaFromCSV("/home/flaviana/dev/corso-beije/AcademyJavaXVII/SuorMary/src/rubrica.csv", ";");
////            loadRubricaFromXML("/home/flaviana/dev/corso-beije/AcademyJavaXVII/SuorMary/src/rubrica.xml");
//        writeRubricaCSV(contacts, "/home/flaviana/proveFile1.csv", ";");
       readFromDb();
    }

}

