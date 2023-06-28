package it.beije.xvii.exercises.ceccarelli.DB;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.beije.suormary.rubrica.Contact;

public class ExerciseswithDB {
	
	private Connection connection = null;
	private Statement statement = null;
	
	//connection check
	public boolean connectionCheck() {
		boolean check=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/alice_ceccarelli?serverTimezone=CET", "root", "Ali1196");
			statement = connection.createStatement();
			if(connection.isClosed()) {
				return false;
			}
		
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	//importare dati da DB
	public List<Contact> loadRubricaFromDb() throws SQLException {
		List<Contact> contacts = new ArrayList<>();
		Contact c = null;
		if(connectionCheck()) {
			ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
			while (rs.next()) {
				c = new Contact();
				//System.out.println(rs.getInt("id"));
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("nome"));
				c.setSurname(rs.getString("cognome"));
				c.setPhoneNumber(rs.getString("telefono"));
				c.setEmail(rs.getString("email"));
				c.setNote(rs.getString("note"));
				
				//System.out.println(c);
				contacts.add(c);
				}
		} else {
			throw new SQLException();
		}
		return contacts;
		
	}
	
	public void writeRubricaFromDbToCSV(List<Contact> list) {
		FileWriter fileWriter= null;
		
		//scrivere dati su CSV
		try {
			fileWriter = new FileWriter("/Users/Padawan/eclipse-workspace/File/rubricaFromDb.csv");
			for(Contact cr : list) {
				System.out.println(cr.getId());
				fileWriter.write(cr.getId());
				fileWriter.write(';');
				fileWriter.write(cr.getName());
				fileWriter.write(';');
				fileWriter.write(cr.getSurname());
				fileWriter.write(';');
				fileWriter.write(cr.getPhoneNumber());
				fileWriter.write(';');
				fileWriter.write(cr.getEmail());
				fileWriter.write(';');
				fileWriter.write(cr.getNote());
				fileWriter.write('\n');
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExerciseswithDB db = new ExerciseswithDB();
		try {
			List<Contact> list = db.loadRubricaFromDb();
			db.writeRubricaFromDbToCSV(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("FINE DA DB A CSV");
	}

}
