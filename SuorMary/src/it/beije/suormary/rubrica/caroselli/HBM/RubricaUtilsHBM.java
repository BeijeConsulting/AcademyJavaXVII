package it.beije.suormary.rubrica.caroselli.HBM;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
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

import it.beije.suormary.rubrica.caroselli.Contact;
import it.beije.suormary.rubrica.caroselli.ScannerUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RubricaUtilsHBM {

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

			for(Contact c: newList) {
				System.out.println(c.toString());
			}
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

				for(Contact contact: contacts) {
					System.out.println(contact.toString());
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

	public static List<Contact> readContactsFromDb() {

		List<Contact> newList = new ArrayList<>();
		Session session = null;

		String choice = ScannerUtil
				.readStringValue("Vuoi cecare i contatti per nome o per cognome? Scegli 'si' o 'no'");
		if (choice.equalsIgnoreCase("si")) {

			String value = ScannerUtil.readStringValue(
					"Scrivi 'nome' per cercare i contatti per nome, altrimenti 'cognome' per cercare i contatti per cognome");
			orderContactsByNameOrSurname(value);

		} else {
			try {

				session = HBMSessionFactory.openSession();
				Query<Contact> query = session.createQuery("SELECT c FROM Contact as c");
				List<Contact> contacts = query.getResultList();
				for (Contact c : contacts)
					System.out.println(c.toString());

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}

		}

		return newList;
	}

	public static List<Contact> orderContactsByNameOrSurname(String nameOrSurname) {

		List<Contact> result = new ArrayList<>();
		Session session = null;

		if (nameOrSurname.equals("nome")) {

			try {
				session = HBMSessionFactory.openSession();
				Query<Contact> query = session.createQuery("SELECT c FROM Contact as c ORDER BY name ASC");
				List<Contact> contacts = query.getResultList();
				for (Contact c : contacts) {
					System.out.println(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}

		} else if (nameOrSurname.equals("cognome")) {

			try {
				session = HBMSessionFactory.openSession();
				Query<Contact> query = session.createQuery("SELECT c FROM Contact as c ORDER BY surname ASC");
				List<Contact> contacts = query.getResultList();
				for (Contact c : contacts)
					System.out.println(c.toString());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}

		return result;
	}

	public static void importExportFromToCSV() {
		System.out.println(
				"scrivi 'importa' per importare i contatti dal db al csv, 'esporta' per esportare i contatti dal cvs al db");
		String choice = RubricaManagerHBM.scanner.nextLine();
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
		String choice = RubricaManagerHBM.scanner.nextLine();
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
		Session session = null;
		List<Contact> contactListFromCSV = loadRubricaFromCSV(path, ";");
		try {
			session = HBMSessionFactory.openSession();
			Transaction transaction = session.beginTransaction();

			for (Contact c : contactListFromCSV) {
				contact.setName(c.getName());
				contact.setSurname(c.getSurname());
				contact.setPhone(c.getPhone());
				contact.setEmail(c.getEmail());
				contact.setNote(c.getNote());

				session.save(contact);

				transaction.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();

		}

	}

	public static void importContactsToDbFromXML(String path) {

		List<Contact> contactListFromXML = loadRubricaFromXML(path);

		Contact contact = new Contact();
		Session session = null;

		try {
			session = HBMSessionFactory.openSession();
			Transaction transaction = session.beginTransaction();

			for (Contact c : contactListFromXML) {
				contact.setName(c.getName());
				contact.setSurname(c.getSurname());
				contact.setPhone(c.getPhone());
				contact.setEmail(c.getEmail());
				contact.setNote(c.getNote());

				session.save(contact);

				transaction.commit();

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();

		}

	}

	public static void insertContact() {
		Session session = null;

		System.out.println("Inserisci i campi del contatto che vuoi aggiungere alla rubrica");

		System.out.println("Inserisci il nome");
		String name = RubricaManagerHBM.scanner.nextLine();
		System.out.println("Inserisci il cognome");
		String surname = RubricaManagerHBM.scanner.nextLine();
		System.out.println("Inserisci il telefono");
		String phone = RubricaManagerHBM.scanner.nextLine();
		System.out.println("Inserisci l'email");
		String email = RubricaManagerHBM.scanner.nextLine();
		System.out.println("Inserisci la nota");
		String note = RubricaManagerHBM.scanner.nextLine();

		Contact contact = new Contact(name, surname, phone, email, note);
		try {
			session = HBMSessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(contact);
			System.out.println("Contatto aggiunto alla rubrica");
			transaction.commit();

			System.out.println(contact.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public static void deleteContact() {

		List<Contact> contacts = findContactsFromInsertedValue();
		Session session = null;

		if (contacts == null) {
			return;
		}

		printContacts(contacts);

		// chiedo all'utente di selezionare il contatto da eliminare
		int choice = ScannerUtil.readIntValue("Inserisci l'id del numero del contatto da eliminare: ");
		Contact contactToDelete = returnAContact(contacts, choice);

		if (areYouSure()) {
			try {
				session = HBMSessionFactory.openSession();

				Transaction transaction = session.beginTransaction();

				Query<Contact> query = session.createQuery("DELETE c FROM Contact as c");
				Contact contact = query.getSingleResult();
				session.delete(contact);
				transaction.commit();
				System.out.println(contact.toString());

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}

		}

	}

	// non cambia effettivamente il contatto
	public static void changeContact() {

		List<Contact> contacts = findContactsFromInsertedValue();
		Session session = null;
		if (contacts == null) {
			return;
		}
		printContacts(contacts);

		System.out.print("Inserisci l'id nel contatto da cambiare: ");

//		int choice = ScannerUtil.readIntValue("Inserisci l'id numero del contatto da cambiare: ");
		int choice = RubricaManagerHBM.scanner.nextInt();
		RubricaManagerHBM.scanner.nextLine();

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
		String newName = RubricaManagerHBM.scanner.nextLine();
		// String newName = ScannerUtil.readStringValue("Inserisci il nuovo nome (o
		// premi Invio per mantenere lo stesso valore): ");
		if (!newName.isEmpty()) {
			name = newName;
		}

//		String newSurname = ScannerUtil.readStringValue("Inserisci il nuovo cognome (o premi Invio per mantenere lo stesso valore): ");
		System.out.print("Inserisci il nuovo cognome (o premi Invio per mantenere lo stesso valore): ");
		String newSurname = RubricaManagerHBM.scanner.nextLine();
		if (!newSurname.isEmpty()) {
			surname = newSurname;
		}

//		String newPhone = ScannerUtil.readStringValue("Inserisci il nuovo telefono (o premi Invio per mantenere lo stesso valore): ");
		System.out.print("Inserisci il nuovo telefono (o premi Invio per mantenere lo stesso valore): ");
		String newPhone = RubricaManagerHBM.scanner.nextLine();
		if (!newPhone.isEmpty()) {
			phone = newPhone;
		}

		System.out.print("Inserisci la nuova email (o premi Invio per mantenere lo stesso valore): ");
		String newEmail = RubricaManagerHBM.scanner.nextLine();
		if (!newEmail.isEmpty()) {
			email = newEmail;
		}

		System.out.print("Inserisci le nuove note (o premi Invio per mantenere lo stesso valore): ");
		String newNote = RubricaManagerHBM.scanner.nextLine();
		if (!newNote.isEmpty()) {
			note = newNote;
		}

		if (areYouSure()) {
			try {
				session = HBMSessionFactory.openSession();
				Transaction transaction = session.beginTransaction();

				Contact contact = session.get(Contact.class, id);
				if (contact != null) {
					contact.setName(name);
					contact.setSurname(surname);
					contact.setPhone(phone);
					contact.setEmail(email);
					contact.setNote(note);

					session.update(contact);
					transaction.commit();

					System.out.println("Contatto cambiato correttamente");

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();

			}
		}

	}

	public static List<Contact> findContactsFromInsertedValue() {

		String value = ScannerUtil
				.readStringValue("Inserisci il valore (esempio Mario o Rossi) per cercare i contatti desiderati");

		Session session = null;
		List<Contact> contacts = new ArrayList<>();

		try {
			session = HBMSessionFactory.openSession();
			Query<Contact> query = session
					.createQuery("SELECT c from Contact as c WHERE name = '" + value + "' OR surname = '" + value
							+ "' OR phone = '" + value + "' OR email = '" + value + "' OR note = '" + value + "'");

			contacts = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();

		}
		

		return contacts;
	}


	public static List<Contact> findDuplicatesContactByValue() throws Exception {

		Session session = null;

		String value = ScannerUtil
				.readStringValue("Inserisci il valore(esempio mario) di cui vuoi cercare i duplicati");

		List<Contact> duplicateContacts = new ArrayList<>();

		try {
			session = HBMSessionFactory.openSession();
			Query<Contact> query = session.createQuery("SELECT c FROM Contact c WHERE (c.name, c.surname, c.phone, c.email, c.note) IN ("
				    + "SELECT c2.name, c2.surname, c2.phone, c2.email, c2.note FROM Contact c2 "
				    + "WHERE (c2.name = :value OR c2.surname = :value OR c2.phone = :value OR c2.email = :value OR c2.note = :value)"
				    + "GROUP BY c2.name, c2.surname, c2.phone, c2.email, c2.note HAVING COUNT(*) > 1)");

			query.setParameter("value", value);

			List<Contact> results = query.getResultList();

			System.out.println("Number of results: " + results.size());

			for (Contact result : results) {
				System.out.println(result.toString());
				duplicateContacts.add(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();

		}

		return duplicateContacts;
	}

	public static void mergeDuplicatesContact() throws Exception {

		List<Contact> duplicateContacts = findDuplicatesContactByValue();
		printContacts(duplicateContacts);
		List<Contact> contactsToDelete = new ArrayList<>();
		Session session = null;

		if (!duplicateContacts.isEmpty()) {

			int choice = ScannerUtil.readIntValue("Inserisci l'id del contatto principale da tenere: ");
			System.out.println("Id scelto: " + choice);
			Contact mainContact = returnAContact(duplicateContacts, choice);
			for(Contact c: duplicateContacts) {
				if(c.equals(mainContact)) {
					if(c.getId() != mainContact.getId()) {
						contactsToDelete.add(c);
					}	
				}
			}
			
			if (areYouSure()) {
				try {
					session = HBMSessionFactory.openSession();

					Query<Contact> deleteQuery = session.createQuery(
							"DELETE FROM Contact c WHERE c.id NOT IN (:choice) AND c IN :contactsToDelete");
					deleteQuery.setParameter("choice", mainContact.getId());
					deleteQuery.setParameter("contactsToDelete", contactsToDelete);
					int deletedCount = deleteQuery.executeUpdate();

					System.out.println("Eliminati " + deletedCount + " contatti duplicati.");

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					session.close();

				}
			}

			System.out.println("Operazione completata!");

		} else {
			System.out.println("Operazione annullata");
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
		Scanner scanner = RubricaManagerHBM.scanner;
		String choice = scanner.next();
		return choice.equalsIgnoreCase("si");
	}

	// per distinguere i tag effettivi da elementi a capo o tab
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
