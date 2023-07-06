package it.beije.suormary.rubrica.caroselli.JPA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
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

import it.beije.suormary.rubrica.caroselli.Contact;
import it.beije.suormary.rubrica.caroselli.ScannerUtil;


public class RubricaUtilsJPA {

	public static List<Contact> readContactsFromDb() {

		List<Contact> newList = new ArrayList<>();
		EntityTransaction transaction = null;
		PersistenceManagerJPA persistenceManager = PersistenceManagerJPA.getInstance();
		EntityManager entityManager = persistenceManager.getEntityManager();

		String choice = ScannerUtil
				.readStringValue("Vuoi cecare i contatti per nome o per cognome? Scegli 'si' o 'no'");
		if (choice.equalsIgnoreCase("si")) {

			String value = ScannerUtil.readStringValue(
					"Scrivi 'nome' per cercare i contatti per nome, altrimenti 'cognome' per cercare i contatti per cognome");
			orderContactsByNameOrSurname(value);

		} else {

			try {

				transaction = entityManager.getTransaction();
				transaction.begin();
				Query query = entityManager.createQuery("SELECT c FROM Contact as c");
				List<Contact> contacts = query.getResultList();
				for (Contact c : contacts)
					System.out.println(c);

				transaction.commit(); // Commit the transaction if successful
			} catch (Exception e) {
				transaction.rollback(); // Rollback the transaction in case of an exception
				e.printStackTrace();
			} finally {
				entityManager.close(); // Close the entityManager
				persistenceManager.close(); // Close the PersistenceManager
			}

		}

		return newList;
	}

	public static List<Contact> orderContactsByNameOrSurname(String nameOrSurname) {

		List<Contact> result = new ArrayList<>();
		EntityTransaction transaction = null;

		PersistenceManagerJPA persistenceManager = PersistenceManagerJPA.getInstance();
		EntityManager entityManager = persistenceManager.getEntityManager();

		if (nameOrSurname.equals("nome")) {

			try {

				transaction = entityManager.getTransaction();
				transaction.begin();

				Query query = entityManager.createQuery("SELECT c FROM Contact as c ORDER BY name ASC");
				List<Contact> contacts = query.getResultList();
				for (Contact c : contacts) {
					System.out.println(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				entityManager.close();
				persistenceManager.close();
			}

		} else if (nameOrSurname.equals("cognome")) {

			try {

				transaction = entityManager.getTransaction();
				transaction.begin();

				Query query = entityManager.createQuery("SELECT c FROM Contact as c ORDER BY surname ASC");
				List<Contact> contacts = query.getResultList();
				for (Contact c : contacts)
					System.out.println(c);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				entityManager.close();
				persistenceManager.close();
			}
		}

		return result;
	}

	public static List<Contact> findContactsFromInsertedValue() {

		PersistenceManagerJPA persistenceManager = PersistenceManagerJPA.getInstance();
		EntityManager entityManager = persistenceManager.getEntityManager();
		EntityTransaction transaction = null;

		String value = ScannerUtil
				.readStringValue("Inserisci il valore (esempio Mario o Rossi) per cercare i contatti desiderati");

		List<Contact> contacts = new ArrayList<>();

		try {

			transaction = entityManager.getTransaction();
			transaction.begin();
			Query query = entityManager
					.createQuery("SELECT c from Contact as c WHERE name = '" + value + "' OR surname = '" + value
							+ "' OR phone = '" + value + "' OR email = '" + value + "' OR note = '" + value + "'");

			contacts = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			persistenceManager.close();

		}

		return contacts;
	}

	public static void insertContact() {

		PersistenceManagerJPA persistenceManager = PersistenceManagerJPA.getInstance();
		EntityManager entityManager = persistenceManager.getEntityManager();
		EntityTransaction transaction = null;

		System.out.println("Inserisci i campi del contatto che vuoi aggiungere alla rubrica");

		System.out.println("Inserisci il nome");
		String name = RubricaManagerJPA.scanner.nextLine();
		System.out.println("Inserisci il cognome");
		String surname = RubricaManagerJPA.scanner.nextLine();
		System.out.println("Inserisci il telefono");
		String phone = RubricaManagerJPA.scanner.nextLine();
		System.out.println("Inserisci l'email");
		String email = RubricaManagerJPA.scanner.nextLine();
		System.out.println("Inserisci la nota");
		String note = RubricaManagerJPA.scanner.nextLine();

		Contact contact = new Contact(name, surname, phone, email, note);

		try {

			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(contact);
			transaction.commit();
			System.out.println("Contatto aggiunto alla rubrica");

			System.out.println(contact);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			persistenceManager.close();
		}

	}

	public static void changeContact() {

		List<Contact> contacts = findContactsFromInsertedValue();
		PersistenceManagerJPA persistenceManager = PersistenceManagerJPA.getInstance();
		EntityManager entityManager = persistenceManager.getEntityManager();
		EntityTransaction transaction = null;

		if (contacts == null) {
			return;
		}
		printContacts(contacts);

		System.out.print("Inserisci l'id nel contatto da cambiare: ");

//		int choice = ScannerUtil.readIntValue("Inserisci l'id numero del contatto da cambiare: ");
		int choice = RubricaManagerJPA.scanner.nextInt();
		RubricaManagerJPA.scanner.nextLine();

		Contact contactToChange = returnAContact(contacts, choice);
		System.out.println("Contatto selezionato:");
		System.out.println(contactToChange);
		int id = contactToChange.getId();
		String name = contactToChange.getName();
		String surname = contactToChange.getSurname();
		String phone = contactToChange.getPhone();
		String email = contactToChange.getEmail();
		String note = contactToChange.getNote();

		System.out.print("Inserisci il nuovo nome (o premi Invio per mantenere lo stesso valore): ");
		String newName = RubricaManagerJPA.scanner.nextLine();
		// String newName = ScannerUtil.readStringValue("Inserisci il nuovo nome (o
		// premi Invio per mantenere lo stesso valore): ");
		if (!newName.isEmpty()) {
			name = newName;
		}

//		String newSurname = ScannerUtil.readStringValue("Inserisci il nuovo cognome (o premi Invio per mantenere lo stesso valore): ");
		System.out.print("Inserisci il nuovo cognome (o premi Invio per mantenere lo stesso valore): ");
		String newSurname = RubricaManagerJPA.scanner.nextLine();
		if (!newSurname.isEmpty()) {
			surname = newSurname;
		}

//		String newPhone = ScannerUtil.readStringValue("Inserisci il nuovo telefono (o premi Invio per mantenere lo stesso valore): ");
		System.out.print("Inserisci il nuovo telefono (o premi Invio per mantenere lo stesso valore): ");
		String newPhone = RubricaManagerJPA.scanner.nextLine();
		if (!newPhone.isEmpty()) {
			phone = newPhone;
		}

		System.out.print("Inserisci la nuova email (o premi Invio per mantenere lo stesso valore): ");
		String newEmail = RubricaManagerJPA.scanner.nextLine();
		if (!newEmail.isEmpty()) {
			email = newEmail;
		}

		System.out.print("Inserisci le nuove note (o premi Invio per mantenere lo stesso valore): ");
		String newNote = RubricaManagerJPA.scanner.nextLine();
		if (!newNote.isEmpty()) {
			note = newNote;
		}

		if (areYouSure()) {
			try {
				transaction = entityManager.getTransaction();

				transaction.begin();

				Contact contact = entityManager.find(Contact.class, id);

				if (contact != null) {
					contact.setName(name);
					contact.setSurname(surname);
					contact.setPhone(phone);
					contact.setEmail(email);
					contact.setNote(note);

					entityManager.persist(contact);
					transaction.commit();

					System.out.println("Contatto cambiato correttamente");

				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				entityManager.close();
				persistenceManager.close();

			}

		}
	}

	public static void deleteContact() {

		List<Contact> contacts = findContactsFromInsertedValue();
		PersistenceManagerJPA persistenceManager = PersistenceManagerJPA.getInstance();
		EntityManager entityManager = persistenceManager.getEntityManager();

		if (contacts == null) {
			return;
		}

		printContacts(contacts);

		// chiedo all'utente di selezionare il contatto da eliminare
		int choice = ScannerUtil.readIntValue("Inserisci l'id del numero del contatto da eliminare: ");
		Contact contactToDelete = returnAContact(contacts, choice);
		int id = contactToDelete.getId();

		if (areYouSure()) {
			try {

				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();

				// Query query = entityManager.createQuery("DELETE c FROM Contact as c");
				Contact contact = entityManager.find(Contact.class, id);
				entityManager.remove(contact);

				transaction.commit();
				System.out.println(contact);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				entityManager.close();

				persistenceManager.close();
			}

		}

	}

	public static List<Contact> findDuplicatesContactByValue() {
		String value = ScannerUtil
				.readStringValue("Inserisci il valore(esempio mario) di cui vuoi cercare i duplicati");
		List<Contact> duplicateContacts = new ArrayList<>();
		PersistenceManagerJPA persistenceManager = PersistenceManagerJPA.getInstance();
		EntityManager entityManager = persistenceManager.getEntityManager();

		try {

			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			Query query = entityManager.createQuery("SELECT c FROM Contact c WHERE c.id IN "
					+ "(SELECT c2.id FROM Contact c2 WHERE c2.id <> :choice AND (c2.name = :value OR c2.surname = :value OR c2.phone = :value OR c2.email = :value OR c2.note = :value))");
			query.setParameter("choice", 0);
			query.setParameter("value", value);

			List<Contact> results = query.getResultList();

			System.out.println("Number of results: " + results.size());

			for (Contact result : results) {
				System.out.println(result);
				duplicateContacts.add(result);
			}
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return duplicateContacts;

	}

	public static void mergeDuplicatesContact() throws Exception {

		List<Contact> duplicateContacts = findDuplicatesContactByValue();
		PersistenceManagerJPA persistenceManager = PersistenceManagerJPA.getInstance();
		EntityManager entityManager = persistenceManager.getEntityManager();

		if (duplicateContacts.isEmpty()) {
			return;
		}

		int choice = ScannerUtil.readIntValue("Inserisci l'id del contatto principale da tenere: ");
		System.out.println("Id scelto: " + choice);
		if (areYouSure()) {
			try {
				EntityTransaction transaction = entityManager.getTransaction();
				transaction.begin();

				Query deleteQuery = entityManager
						.createQuery("DELETE FROM Contact c WHERE c.id NOT IN (:choice) AND c IN :duplicateContacts");
				deleteQuery.setParameter("choice", choice);
				deleteQuery.setParameter("duplicateContacts", duplicateContacts);
				int deletedCount = deleteQuery.executeUpdate();

				transaction.commit();

				System.out.println("Eliminati " + deletedCount + " contatti duplicati.");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				entityManager.close();
				persistenceManager.close();
			}
			System.out.println("Operazione completata!");
		} else {
			System.out.println("Operazione annullata");
		}
	}
	
	
	public static void importExportFromToCSV() {
		System.out.println(
				"scrivi 'importa' per importare i contatti dal db al csv, 'esporta' per esportare i contatti dal cvs al db");
		String choice = RubricaManagerJPA.scanner.nextLine();
		if (choice != "importa" || choice != "esporta") {
			System.out.println("Scelte non corrette");
		} else if (choice == "importa") {
			importContactsToDbFromCSV(
					"/home/flaviana/git/AcademyJavaXVII/SuorMary/src/it/beije/suormary/rubrica/caroselli/rubrica.csv");
			System.out.println("Contatti importati correttamente");
		} else {
			exportContactsFromDbToCSV("/home/flaviana/fromDbToCSV.csv");
		}
	}

	public static void importExportFromToXML() {
		System.out.println(
				"scrivi 'importa' per importare i contatti dal db all'xml, 'esporta' per esportare i contatti dal cvs all'xml");
		String choice = RubricaManagerJPA.scanner.nextLine();
		if (choice != "importa" || choice != "esporta") {
			System.out.println("Scelte non corrette");
		} else if (choice == "importa") {
			importContactsToDbFromXML(
					"/home/flaviana/dev/beije/AcademyJavaXVII/Exercises/src/it/beije/xvii/exercises/Caroselli/myRubrica/rubrica.xml");
			System.out.println("Contatti importati correttamente");
		} else {
			exportContactsFromDbToXML("/home/flaviana/fromDbToXML.xml");
		}
	}

	public static void exportContactsFromDbToCSV(String path) {

		try {

			List<Contact> contacts = readContactsFromDb();
			writeRubricaCSV(contacts, path, ";");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exportContactsFromDbToXML(String path) {

		List<Contact> contacts = readContactsFromDb();
		writeRubricaToXML(contacts, path);

	}

	public static void importContactsToDbFromCSV(String path) {

		Contact contact = new Contact();
		List<Contact> contactListFromCSV = loadRubricaFromCSV(path, ";");
		PersistenceManagerJPA persistenceManager = PersistenceManagerJPA.getInstance();
		EntityManager entityManager = persistenceManager.getEntityManager();
		try {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			for (Contact c : contactListFromCSV) {
				contact.setName(c.getName());
				contact.setSurname(c.getSurname());
				contact.setPhone(c.getPhone());
				contact.setEmail(c.getEmail());
				contact.setNote(c.getNote());

				entityManager.persist(contact);
				transaction.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			persistenceManager.close();

		}

	}

	public static void importContactsToDbFromXML(String path) {

		List<Contact> contactListFromXML = loadRubricaFromXML(path);

		Contact contact = new Contact();
		PersistenceManagerJPA persistenceManager = PersistenceManagerJPA.getInstance();
		EntityManager entityManager = persistenceManager.getEntityManager();

		try {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			for (Contact c : contactListFromXML) {
				contact.setName(c.getName());
				contact.setSurname(c.getSurname());
				contact.setPhone(c.getPhone());
				contact.setEmail(c.getEmail());
				contact.setNote(c.getNote());

				entityManager.persist(contact);
				transaction.commit();

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			persistenceManager.close();

		}

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

			// consumo la prima riga, la leggo
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
				String row = contact.getName() + separator + contact.getSurname() + separator + contact.getPhone()
						+ separator + contact.getEmail() + separator + contact.getNote();

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
	
	
	public static void printContacts(List<Contact> contacts) {

		if (contacts.isEmpty()) {
			System.out.println("Nessun contatto trovato.");
			return;
		}

		// lista dei contatti trovati
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
		Scanner scanner = RubricaManagerJPA.scanner;
		String choice = scanner.next();
		return choice.equalsIgnoreCase("si");
	}
	
	public static List<Element> getChildElements(Element el) {
		NodeList nodeList = el.getChildNodes();
		List<Element> elements = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			if (n instanceof Element)
				elements.add((Element) n);
		}

		return elements;
	}

}
