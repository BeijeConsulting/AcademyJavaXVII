package it.beije.suormary.rubrica;


import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AzioniGestore {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "Rick&Morty63!!");
	}

	public List<Contact> listContact(){
		
		Connection connection = null;
		PreparedStatement preparedStatement= null;
		
		try {
			connection = getConnection();
			
			
			
		} 
		
		
		
		return null;
	}

}
