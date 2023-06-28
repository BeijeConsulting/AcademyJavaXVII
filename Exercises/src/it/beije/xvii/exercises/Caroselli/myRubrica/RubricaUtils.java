package it.beije.xvii.exercises.Caroselli.myRubrica;

import com.mysql.cj.protocol.a.NativePacketPayload;
import com.sun.xml.internal.ws.spi.db.FieldSetter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Field;
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

            Element docEl = document.getDocumentElement();
            List<Element> elements = getChildElements(docEl);

            for (Element el : elements) {

                // Create a new Contact object for each element
                Contact c = new Contact();

                List<Element> els = getChildElements(el);
                for (Element e : els) {

                    switch (e.getTagName()) {
                        case "name":
                            c.setName(e.getTextContent());
                            break;
                        case "surname":
                            c.setSurname(e.getTextContent());
                            break;
                        case "phone":
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

                // Add the Contact object to the contacts list
                contacts.add(c);
            }

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

            Field[] fields = Contact.class.getDeclaredFields();

            // Build the header row based on the field names
            StringBuilder headerBuilder = new StringBuilder();
            for (Field field : fields) {
                if (headerBuilder.length() > 0) {
                    headerBuilder.append(separator);
                }
                headerBuilder.append(field.getName().toUpperCase());
            }
            String headerRow = headerBuilder.toString();

            // Write the header row to the file
            bufferedWriter.write(headerRow);
            bufferedWriter.newLine();

            // Scrivi i nuovi contatti in coda al file
            for (Contact contact : contacts) {
                String row = contact.getName() + separator + contact.getSurname() + separator + contact.getPhoneNumber() + separator + contact.getEmail() + separator + contact.getNote();

                bufferedWriter.write(row);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Errore durante la scrittura del file", e);
        }

    }

    public static void writeRubricaToXML(List<Contact> contacts, String pathFile) {

        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();
            Element contactsList = document.createElement("contacts");
            document.appendChild(contactsList);

            Element contact = null;

            for (Contact c : contacts) {
                contact = document.createElement("contact");
                contact.setAttribute("age", "50");
                if (c.getName() != null) {
                    Element name = document.createElement("name");
                    name.setTextContent(c.getName());
                    contact.appendChild(name);
                }
                if (c.getSurname() != null) {
                    Element surname = document.createElement("surname");
                    surname.setTextContent(c.getSurname());
                    contact.appendChild(surname);
                }
                if (c.getPhoneNumber() != null) {
                    Element phoneNumber = document.createElement("phone");
                    phoneNumber.setTextContent(c.getPhoneNumber());
                    contact.appendChild(phoneNumber);
                }
                if (c.getEmail() != null) {
                    Element email = document.createElement("email");
                    email.setTextContent(c.getEmail());
                    contact.appendChild(email);
                }
                if (c.getNote() != null) {
                    Element note = document.createElement("note");
                    note.setTextContent(c.getNote());
                    contact.appendChild(note);
                }

                contactsList.appendChild(contact);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);

            StreamResult result = new StreamResult(new File(pathFile));

            // Output to console for testing
            StreamResult syso = new StreamResult(System.out);

            transformer.transform(source, result);
            transformer.transform(source, syso);

        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Contact> readContactsFromDb() {

        List<Contact> newList = new ArrayList<>();

        try {
            Statement statement = connection("suor_mary", "root");

            //SELECT
            ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");

            while (rs.next()) {

                newList.add(new Contact(rs.getString("name"), rs.getString("surname"), rs.getString("phone"), rs.getString("email"), rs.getString("note")));
            }

            rs.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return newList;
    }

    public static List<Contact> orderContactsByNameOrSurname(String nameOrSurname) {

        List<Contact> result = new ArrayList<>();

        try {

            Statement statement = connection("suor_mary", "root");

            if (nameOrSurname == null) {
                ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
            } else if (nameOrSurname.equals("nome")) {

                ResultSet rs = statement.executeQuery("SELECT * FROM rubrica ORDER BY name ASC;");

                while (rs.next()) {
                    result.add(new Contact(rs.getString("name"), rs.getString("surname"), rs.getString("phone"), rs.getString("email"), rs.getString("note")));
                }

                rs.close();

            } else if (nameOrSurname.equals("cognome")) {

                ResultSet rs = statement.executeQuery("SELECT * FROM rubrica ORDER BY surname ASC;");

                while (rs.next()) {
                    result.add(new Contact(rs.getString("name"), rs.getString("surname"), rs.getString("phone"), rs.getString("email"), rs.getString("note")));
                }

                rs.close();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
        return result;
    }


    public static void exportContactsFromDbToCSV() {
        try {
            connection("suor_mary", "root");
            List<Contact> contacts = readContactsFromDb();
//            System.out.println(contacts);
            writeRubricaCSV(contacts, "/home/flaviana/fromDbToCSV.csv", ";");


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void exportContactsFromDbToXML() {
        try {
            connection("suor_mary", "root");
            List<Contact> contacts = readContactsFromDb();
//            System.out.println(contacts);
            writeRubricaToXML(contacts, "/home/flaviana/fromDbToXML.xml");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static void importContactsToDbFromCSV() {

        try {

            Statement statement = connection("suor_mary", "root");
            List<Contact> contactListFromCSV = loadRubricaFromCSV("/home/flaviana/dev/beije/AcademyJavaXVII/Exercises/src/it/beije/xvii/exercises/Caroselli/myRubrica/rubrica.csv", ";");
            for (Contact c : contactListFromCSV) {
                String query = "INSERT INTO rubrica (`name`, `surname`, `phone`, `email`) VALUES ('" +
                        c.getName() + "', '" + c.getSurname() + "', '" +
                        c.getPhoneNumber() + "', '" + c.getEmail() + "')";
                statement.executeUpdate(query);
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void importContactsToDbFromXML() {
        try {
            Statement statement = connection("suor_mary", "root");
            List<Contact> contactListFromXML = loadRubricaFromXML("/home/flaviana/dev/beije/AcademyJavaXVII/Exercises/src/it/beije/xvii/exercises/Caroselli/myRubrica/rubrica.xml");

            for (Contact c : contactListFromXML) {
                String query = "INSERT INTO rubrica (`name`, `surname`, `phone`, `email`) VALUES ('" +
                        c.getName() + "', '" + c.getSurname() + "', '" +
                        c.getPhoneNumber() + "', '" + c.getEmail() + "')";
                statement.executeUpdate(query);

            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static List<Contact> findContacts(String name, String surname, String email, String phone) {

        List<Contact> contactsFounded = new ArrayList<>();
        String query = "SELECT * FROM rubrica WHERE ";

        if (name != null && !name.isEmpty()) {
            query = query + " name = '" + name + "';";

        } else if (surname != null && !surname.isEmpty()) {
            query = query + " surname = '" + surname + "';";

        } else if (email != null && !email.isEmpty()) {
            query = query + " email = '" + email + "';";

        } else if (phone != null && !phone.isEmpty()) {
            query = query + " phone = '" + phone + "';";

        }

        try {
            Statement statement = connection("suor_mary", "root");
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                contactsFounded.add(new Contact(rs.getString("name"), rs.getString("surname"), rs.getString("phone"), rs.getString("email"), rs.getString("note")));
            }

            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return contactsFounded;
    }


    public static void insertContact(String name, String surname, String phone, String email, String note) {

        Contact contact = new Contact(name, surname, phone, email, note);

        try {

            Statement statement = connection("suor_mary", "root");

            String query = "INSERT INTO rubrica (`name`, `surname`, `phone`, `email`, `note`) VALUES (";
            query += "'" + (contact.getName() != null ? contact.getName() : "NULL") + "', ";
            query += "'" + (contact.getSurname() != null ? contact.getSurname() : "NULL") + "', ";
            query += "'" + (contact.getPhoneNumber() != null ? contact.getPhoneNumber() : "NULL") + "', ";
            query += "'" + (contact.getEmail() != null ? contact.getEmail() : "NULL") + "', ";
            query += "'" + (contact.getNote() != null ? contact.getNote() : "NULL") + "'";
            query += ")";

            statement.executeUpdate(query);

            System.out.println("Contatto aggiunto alla rubrica");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(contact);
    }

    public static void changeContact(String value) {

        List<Contact> contactsFounded = new ArrayList<>();
        String query = "SELECT * FROM rubrica WHERE ";

        Field[] fields = Contact.class.getDeclaredFields();

        // Build the header row based on the field names
        StringBuilder headerBuilder = new StringBuilder();
        for (Field field : fields) {


//
//        if () {
//            query = query + " name = '" + name + "';";
//
//        } else if (surname != null && !surname.isEmpty()) {
//            query = query + " surname = '" + surname + "';";
//
//        } else if (email != null && !email.isEmpty()) {
//            query = query + " email = '" + email + "';";
//
//        } else if (phone != null && !phone.isEmpty()) {
//            query = query + " phone = '" + phone + "';";
//
//        }

    }


    //per distinguere i tag effettivi da elementi a capo o tab
    public static List<Element> getChildElements(Element el) {
        NodeList nodeList = el.getChildNodes();
        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node n = nodeList.item(i);
            if (n instanceof Element) elements.add((Element) n);
        }

        return elements;
    }


    public static Statement connection(String dbName, String user) throws ClassNotFoundException {

        Connection connection = null;
        Statement statement = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=CET", "'" + user + "'", "password");

            statement = connection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
//        } finally {
//            try {
//                if (connection != null && statement != null) {
//                    statement.close();
//                    connection.close();
//                }
//
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }

        }

        return statement;
    }


}

