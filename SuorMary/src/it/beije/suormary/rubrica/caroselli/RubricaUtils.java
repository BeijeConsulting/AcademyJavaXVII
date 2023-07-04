package it.beije.suormary.rubrica.caroselli;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class RubricaUtils {
	
	
	public static Session session() {
		
		SessionFactory factory = null;
		
	try { 
		
		Configuration config = new Configuration().configure()
				.addAnnotatedClass(Contact.class);
		
		factory = config.buildSessionFactory();
	
		
		
	} catch (Exception e) {
        e.printStackTrace();
    } 
	
	return factory.openSession();
		  
}
	
	
	
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
                            c.setPhone(e.getTextContent());
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

            return contacts;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeRubricaCSV(List<Contact> contacts, String pathFile, String separator) {
        boolean fileExists = new File(pathFile).exists();

        try (FileWriter fileWriter = new FileWriter(pathFile, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            // Se il file esiste già aggiungo un separatore di riga iniziale
            if (fileExists) {
                bufferedWriter.newLine();
            }

            Field[] fields = Contact.class.getDeclaredFields();

            // Build della prima riga del file basata sui field
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

            // scrivo i nuovi contatti in coda al file
            for (Contact contact : contacts) {
                String row = contact.getName() + separator + contact.getSurname() + separator + contact.getPhone() + separator + contact.getEmail() + separator + contact.getNote();

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
                if (c.getPhone() != null) {
                    Element phoneNumber = document.createElement("phone");
                    phoneNumber.setTextContent(c.getPhone());
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

            StreamResult syso = new StreamResult(System.out);

            transformer.transform(source, result);
            transformer.transform(source, syso);

        } catch (ParserConfigurationException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }
    

    public static List<Contact> readContactsFromDb() {

        List<Contact> newList = new ArrayList<>();
        
        System.out.println("Vuoi cecare i contatti per nome o per cognome?");
        String yesOrNo = useScanner();
        if (yesOrNo.equalsIgnoreCase("si")) {
            System.out.println("Scrivi 'nome' per cercare i contatti per nome, altrimenti 'cognome' per cercare i contatti per cognome");
            String value = useScanner();
            orderContactsByNameOrSurname(value);
            
        } else {
            	
            	Query<Contact> query = session().createQuery("SELECT c FROM Contact as c");
            	List<Contact> contacts = query.getResultList();
            	for(Contact c : contacts) System.out.println(c);
            	
    			session().close();

        }

        return newList;
    }
    
    

    public static List<Contact> orderContactsByNameOrSurname(String nameOrSurname) {

        List<Contact> result = new ArrayList<>();
        

            if (nameOrSurname.equals("nome")) {
            	
            	Query<Contact> query = session().createQuery("SELECT c FROM Contact as c ORDER BY name ASC");
            	List<Contact> contacts = query.getResultList();
            	for(Contact c : contacts) System.out.println(c);
            	
    			session().close();

    			
            } else if (nameOrSurname.equals("cognome")) {
            	
            	Query<Contact> query = session().createQuery("SELECT c FROM Contact as c ORDER BY surname ASC");
            	List<Contact> contacts = query.getResultList();
            	for(Contact c : contacts) System.out.println(c);
            	
    			session().close();
            }
           
            
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
 
            List<Contact> contacts = readContactsFromDb();
            writeRubricaToXML(contacts, "/home/flaviana/fromDbToXML.xml");

    }


    public static void importContactsToDbFromCSV() {

    	Contact contact = new Contact();
    	
    	Transaction transaction = session().beginTransaction();

        List<Contact> contactListFromCSV = loadRubricaFromCSV("/home/flaviana/dev/beije/AcademyJavaXVII/Exercises/src/it/beije/xvii/exercises/Caroselli/myRubrica/rubrica.csv", ";");
        for (Contact c : contactListFromCSV) {
            contact.setName(c.getName());
            contact.setSurname(c.getSurname());
            contact.setPhone(c.getPhone());
            contact.setEmail(c.getEmail());
            contact.setNote(c.getNote());
            
            session().save(contact);
            
            transaction.commit();
        }
        
        session().close();
 
   }


    public static void importContactsToDbFromXML() {
        
            List<Contact> contactListFromXML = loadRubricaFromXML("/home/flaviana/dev/beije/AcademyJavaXVII/Exercises/src/it/beije/xvii/exercises/Caroselli/myRubrica/rubrica.xml");
        
            Contact contact = new Contact();
            
        	Transaction transaction = session().beginTransaction();
        	
            for (Contact c : contactListFromXML) {
            	 contact.setName(c.getName());
                 contact.setSurname(c.getSurname());
                 contact.setPhone(c.getPhone());
                 contact.setEmail(c.getEmail());
                 contact.setNote(c.getNote());
                 
                 session().save(contact);
                 
                 transaction.commit();

            }

        session().close();

    }


//    public static List<Contact> findContacts(String name, String surname, String email, String phone) {
//
//        List<Contact> contactsFounded = new ArrayList<>();
//        String query = "SELECT * FROM rubrica WHERE ";
//
//        if (name != null && !name.isEmpty()) {
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
//
//        try {
//            Statement statement = connection("suor_mary", "root");
//            ResultSet rs = statement.executeQuery(query);
//
//            while (rs.next()) {
//                contactsFounded.add(new Contact(rs.getString("name"), rs.getString("surname"), rs.getString("phone"), rs.getString("email"), rs.getString("note")));
//            }
//
//            rs.close();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        return contactsFounded;
//    }


    public static void insertContact() {

        System.out.println("Inserisci i campi del contatto che vuoi aggiungere alla rubrica");
        System.out.println("Inserisci il nome");
        String name = useScanner();
        System.out.println("Inserisci il cognome");
        String surname = useScanner();
        System.out.println("Inserisci il telefono");
        String phone = useScanner();
        System.out.println("Inserisci l'email");
        String email = useScanner();
        System.out.println("Inserisci la nota");
        String note = useScanner();

        Contact contact = new Contact(name, surname, phone, email, note);
        
        Transaction transaction = session().beginTransaction();
        session().save(contact);
        System.out.println("Contatto aggiunto alla rubrica");
       
        System.out.println(contact);
        session().close();
    }
    

    public static void deleteContact() {

        System.out.println("Inserisci il valore (esempio mario) per trovare i contatti e cancellarne uno");
        String value = useScanner();

        List<Contact> contacts = findContactFromInsertedValue(value);

        printContacts(contacts);

        // chiedo all'utente di selezionare il contatto da eliminare
        System.out.print("Inserisci l'id del numero del contatto da eliminare: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        Contact contactToDelete = returnAContact(contacts, choice);

        if (areYouSure()) {

            try {
                Statement statement = connection("suor_mary", "root");

                String deleteQuery = "DELETE FROM rubrica WHERE id = '" + contactToDelete.getId() + "';";
                statement.executeUpdate(deleteQuery);

                System.out.println("Contatto eliminato con successo.");
                System.out.println(contactToDelete);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void changeContact() {

        System.out.println("Inserisci il valore del contatto che vuoi trovare (esempio mario) per poi cambiarlo");
        String value = useScanner();
        List<Contact> contacts = findContactFromInsertedValue(value);
        printContacts(contacts);
        System.out.print("Inserisci l'id numero del contatto da cambiare: ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        Contact contactToChange = returnAContact(contacts, choice);
        System.out.println("Contatto selezionato:");
        System.out.println(contactToChange);
        String name = contactToChange.getName();
        String surname = contactToChange.getSurname();
        String phone = contactToChange.getPhone();
        String email = contactToChange.getEmail();
        String note = contactToChange.getNote();

            try {

                Statement statement = connection("suor_mary", "root");

                System.out.print("Inserisci il nuovo nome (o premi Invio per mantenere lo stesso valore): ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) {
                    name = newName;
                }

                System.out.print("Inserisci il nuovo cognome (o premi Invio per mantenere lo stesso valore): ");
                String newSurname = scanner.nextLine();
                if (!newSurname.isEmpty()) {
                    surname = newSurname;
                }

                System.out.print("Inserisci il nuovo telefono (o premi Invio per mantenere lo stesso valore): ");
                String newPhone = scanner.nextLine();
                if (!newPhone.isEmpty()) {
                    phone = newPhone;
                }

                System.out.print("Inserisci la nuova email (o premi Invio per mantenere lo stesso valore): ");
                String newEmail = scanner.nextLine();
                if (!newEmail.isEmpty()) {
                    email = newEmail;
                }

                System.out.print("Inserisci le nuove note (o premi Invio per mantenere lo stesso valore): ");
                String newNote = scanner.nextLine();
                if (!newNote.isEmpty()) {
                    note = newNote;
                }

                if (areYouSure()) {

                    String updateQuery = "UPDATE rubrica SET name = '" + name + "', surname = '" + surname + "', phone = '" + phone + "', email = '" + email + "', note = '" + note + "' WHERE id = " + contactToChange.getId();
                    statement.executeUpdate(updateQuery);


//                    System.out.println(updateQuery);
                    System.out.println("Contatto cambiato correttamente:");


                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }

    public static List<Contact> findContactFromInsertedValue(String value) {

        List<Contact> contacts = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM rubrica WHERE ");

        Field[] fields = Contact.class.getDeclaredFields();
        List<String> searchFields = new ArrayList<>();

        // faccio una lista di nomi dei campi in cui cercare
        for (Field field : fields) {
            searchFields.add(field.getName());
        }

        // faccio la clausola WHERE basata sui campi di ricerca
        for (int i = 0; i < searchFields.size(); i++) {
            String field = searchFields.get(i);
            query.append(field).append(" = '").append(value).append("'");

            // aggiungo OR se non è l'ultimo campo di contact
            if (i < searchFields.size() - 1) {
                query.append(" OR ");
            }
        }
        query.append(";");

        try {
            Statement statement = connection("suor_mary", "root");
            ResultSet rs = statement.executeQuery(query.toString());
            while (rs.next()) {
                contacts.add(new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("phone"), rs.getString("email"), rs.getString("note")));
            }

            rs.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//
//        System.out.println(query);
//
        System.out.println(contacts);

        return contacts;
    }

//    public static List<Contact> findDuplicatesContactByValue() {
//
//        System.out.println("Inserisci il valore (esempio mario) per vedere i duplicati del contatto in rubrica");
//        String value = useScanner();
//
////        return getContacts(value);
//
//    }

    public static List<Contact> findDuplicatesContactByValue() throws Exception{
    	
    	System.out.println("Inserisci il valore(esempio mario) di cui vuoi cercare i duplicati");
    	
    	Scanner scanner = new Scanner(System.in);
    	String value = scanner.nextLine();
    	
        List<Contact> duplicateContacts = new ArrayList<>();
        
        Statement statement = connection("suor_mary", "root");

        
        StringBuilder query = new StringBuilder("SELECT id, name, surname, phone, email, note\n"
        		+ "FROM rubrica\n"
        		+ "WHERE (name, surname, phone, email, note) IN (\n"
        		+ "    SELECT name, surname, phone, email, note\n"
        		+ "    FROM rubrica\n"
        		+ "    where name = '" + value +  "' OR surname = '" +  value + 
        		"' OR phone = '" +  value + "' OR email = '" +  value +  "' OR note = '" +  value + "' \n"
        		+ "    GROUP BY name, surname, phone, email, note\n"
        		+ "    HAVING COUNT(*) > 1 \n"
        		+ "  );");
        
        ResultSet rs = statement.executeQuery(query.toString());
        while (rs.next()) {
            duplicateContacts.add(new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("phone"), rs.getString("email"), rs.getString("note")));
        }

        scanner.close();
        return duplicateContacts;
    }

    

    public static void mergeDuplicatesContact() throws Exception {

      
        List<Contact> duplicateContacts = findDuplicatesContactByValue();
        
       printContacts(duplicateContacts);

        // chiedo all'utente di selezionare il contatto principale
        System.out.print("Inserisci l'id del contatto principale da tenere: ");
        
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (areYouSure())  {
  
        // elimino i contatti duplicati
        for(Contact c : duplicateContacts) {
                	if(c.getId() != choice) {

                        Statement statement = connection("suor_mary", "root");
                        String deleteQuery = "DELETE FROM rubrica\n"
                        		+ "WHERE id NOT IN ('" + choice + "');";

                		statement.executeUpdate(deleteQuery);
						statement.close();
                	}
                	
        	}
                     
					
                } else {
                    System.out.println("Operazione annullata");
                }
                scanner.close();
    }


    public static void deleteContactById(int id) {
        try {
            Statement statement = connection("suor_mary", "root");
            String deleteQuery = "DELETE FROM rubrica WHERE id = " + id;
            statement.executeUpdate(deleteQuery);

            statement.close();

            System.out.println("Contatto eliminato con successo.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void updateContact(Contact contact) {

        try {

            Statement statement = connection("suor_mary", "root");
            String updateQuery = "UPDATE rubrica SET " +
                    "name = '" + contact.getName() + "', " +
                    "surname = '" + contact.getSurname() + "', " +
                    "phone = '" + contact.getPhone() + "', " +
                    "email = '" + contact.getEmail() + "', " +
                    "note = '" + contact.getNote() + "' " +
                    "WHERE id = " + contact.getId();
            statement.executeUpdate(updateQuery);

            statement.close();

            System.out.println("Contatto aggiornato con successo.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void printContacts(List<Contact> contacts) {

        if (contacts.isEmpty()) {
            System.out.println("Nessun contatto trovato.");
            return;
        }

        //lista dei contatti trovati
        for (Contact contact : contacts) {
            System.out.println(contact.toString());
        }
    }

    public static Contact returnAContact(List<Contact> contacts, int choice) {

        // verifico la scelta dell'utente
        boolean validChoice = false;
        Contact currentContact = null;
        for (Contact contact : contacts) {
            if (contact.getId() == choice) {
                validChoice = true;
                currentContact = contact;
                break;
            }
        }

        if (!validChoice) {
            System.out.println("ID del contatto non valido.");
        }
        return currentContact;
    }

    public static boolean areYouSure() {
        System.out.println("Sei sicuro di voler effettuare questa operazione?");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        return choice.equalsIgnoreCase("si");
    }

    public static String useScanner() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
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

        } catch (Exception e) {
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
