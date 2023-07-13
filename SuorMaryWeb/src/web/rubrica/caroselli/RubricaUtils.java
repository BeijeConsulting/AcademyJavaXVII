package web.rubrica.caroselli;

//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import java.io.*;
//import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;

public class RubricaUtils {


	public static List<Contact> readContactsFromDb() {

		List<Contact> newList = new ArrayList<>();

		String choice = ScannerUtil
				.readStringValue("Vuoi cecare i contatti per nome o per cognome? Scegli 'si' o 'no'");
		if (choice.equalsIgnoreCase("si")) {

			String value = ScannerUtil.readStringValue(
					"Scrivi 'nome' per cercare i contatti per nome, altrimenti 'cognome' per cercare i contatti per cognome");
			orderContactsByNameOrSurname(value);

		} else {

			try {

				Statement statement = connection("suor_mary", "root");

				ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");

				while (rs.next()) {

					newList.add(new Contact(rs.getString("name"), rs.getString("surname"), rs.getString("note")));
				}
				rs.close();

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		return newList;
	}


	public static List<Contact> readNewContactsFromDb() {

		List<Contact> newList = new ArrayList<>();

//
//		String choice = ScannerUtil
//				.readStringValue("Vuoi cecare i contatti per nome o per cognome? Scegli 'si' o 'no'");
//		if (choice.equalsIgnoreCase("si")) {
//
//			String value = ScannerUtil.readStringValue(
//					"Scrivi 'nome' per cercare i contatti per nome, altrimenti 'cognome' per cercare i contatti per cognome");
//			orderContactsByNameOrSurname(value);
//
//		} else {

			try {

				Statement statement = connection("suor_mary", "root");
				ContactDetail cd = new ContactDetail();
				Contact c = new Contact();

				ResultSet rs = statement.executeQuery("SELECT r.id, r.name, r.surname, r.note, ref.contact, ref.label, ref.type FROM rubrica as r JOIN reference as ref on r.id = ref.id_rubrica;");

				while (rs.next()) {

					//newList.add(new Contact2(rs.getString("name"), rs.getString("surname"), rs.getString("note")));
					cd.setDetail(rs.getString("contact"));
					cd.setLabel(rs.getString("label"));
					cd.setType(rs.getString("type").charAt(0));

//					c.setDetails(cd);
					c.setName(rs.getString("name"));
					c.setSurname(rs.getString("surname"));
					c.setNote(rs.getString("note"));

					newList.add(c);
				}
				rs.close();

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			System.out.println(newList);

		return newList;
	}


	public static List<Contact> orderContactsByNameOrSurname(String nameOrSurname) {

		List<Contact> result = new ArrayList<>();

		try {

			Statement statement = connection("suor_mary", "root");

			if (nameOrSurname.equals("nome")) {

				ResultSet rs = statement.executeQuery("SELECT * FROM rubrica ORDER BY name ASC;");

				while (rs.next()) {
					result.add(new Contact(rs.getString("name"), rs.getString("surname"), rs.getString("note")));
				}

				rs.close();

			} else if (nameOrSurname.equals("cognome")) {

				ResultSet rs = statement.executeQuery("SELECT * FROM rubrica ORDER BY surname ASC;");

				while (rs.next()) {
					result.add(new Contact(rs.getString("name"), rs.getString("surname"), rs.getString("note")));
				}

				rs.close();
			}

		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		System.out.println(result);
		return result;
	}


	public static Statement connection(String dbName, String user) throws ClassNotFoundException {

		Connection connection = null;
		Statement statement = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=CET",
					"'" + user + "'", "password");

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