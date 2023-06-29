package esercizi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class QueryPreparedS {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//CAMBIARE PASSWORD PER PROVARE!!
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "beije");
	}

	public static void main(String[] args) {
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			/*
			 *  Richiama il metodo getConnection() definito nella stessa classe QueryPreparedS per ottenere una 
			 *  connessione al database. Questo metodo utilizza la classe DriverManager per ottenere una connessione 
			 *  al database MySQL specificato nella stringa di connessione.
			 */
			connection = QueryPreparedS.getConnection();

			
			System.out.println("connection open? " + !connection.isClosed());
			
			//Vengono definiti variabili per contenere valori di esempio per nome, cognome, telefono ed email.
			String nome = "Alessandro";
			String cognome = "Sala";
			String telefono = "3223334842";
			String email = "alessandro.sala@beije.it";
			
			/*
			 * Crea un oggetto PreparedStatement utilizzando la connessione al database e una stringa SQL parametrizzata 
			 * per l'inserimento di dati nella tabella rubrica. La stringa SQL utilizza segnaposto "?" per i valori che 
			 * verranno impostati successivamente.
			 * è la stessa sintassi SQL solo ci sono dei segnaposto invece dei valori reali
			 * IDEA: potrei inserire il tutto in un for each e andare a inserire i valori puntuali di un elenco di contatti
			 */
			PreparedStatement preparedStatement = 
					connection.prepareStatement("INSERT INTO rubrica (`nome`, `cognome`, `telefono`, `email`) VALUES (?, ?, ?, ?)");
			
			/*
			 * preparedStatement.setString(1, nome);: Imposta il valore del primo segnaposto "?" nella query preparata 
			 * con il valore della variabile nome.
			 * preparedStatement.setString(2, cognome);: Imposta il valore del secondo segnaposto "?" nella query 
			 * preparata con il valore della variabile cognome.
			 * ecc.
			 */
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, cognome);
			preparedStatement.setString(3, telefono);
			preparedStatement.setString(4, email);
			
			/*
			 * Esegue la query preparata utilizzando il metodo execute(). Questa query inserisce i dati specificati 
			 * nella tabella rubrica utilizzando i valori forniti.
			 */
			preparedStatement.execute();
						
			
//			StringBuilder query = new StringBuilder("INSERT INTO rubrica (`nome`, `cognome`, `telefono`, `email`) VALUES ('")
//					.append(nome).append("', '").append(cognome).append("', '")
//					.append(telefono).append("', '").append(email).append("')");
			//INSERT
			//statement.executeUpdate("INSERT INTO rubrica VALUES (null, 'Marco', 'Gialli', '0432555311', 'marco.gialli@beije.it', 'sono un contatto');");
			//statement.executeUpdate(query.toString());
			
			//UPDATE
//			int u = statement.executeUpdate("UPDATE rubrica set telefono = '987654' WHERE id < 4");
//			System.out.println(u + " record modificati");

			//DELETE
//			int u = statement.executeUpdate("DELETE FROM rubrica WHERE id = 5");
//			System.out.println(u + " record eliminati");

			//SELECT
			
			/*
			 * Esegue una query di selezione per recuperare tutti i dati dalla tabella rubrica 
			 * utilizzando l'oggetto Statement creato in precedenza. Il risultato viene memorizzato 
			 * in un oggetto ResultSet
			 */
			ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
			
			/*
			 * tera attraverso le righe del ResultSet utilizzando il metodo next(). All'interno del 
			 * ciclo while, i valori delle colonne del risultato vengono recuperati utilizzando i 
			 * metodi getInt(), getString(), ecc., e quindi vengono stampati a console.
			 */
			while (rs.next()) {
//				System.out.println("id : " + rs.getInt(1));
//				System.out.println("nome : " + rs.getString(2));
//				System.out.println("cognome : " + rs.getString(3));
//				System.out.println("telefono : " + rs.getString(4));
//				System.out.println("email : " + rs.getString(5));
//				System.out.println("note : " + rs.getString(6));
				
				
				
				System.out.println("id : " + rs.getInt("id"));
				System.out.println("note : " + rs.getString("note"));
				System.out.println("nome : " + rs.getString("nome"));
				System.out.println("cognome : " + rs.getString("cognome"));
				System.out.println("telefono : " + rs.getString("telefono"));
				System.out.println("email : " + rs.getString("email"));

				System.out.println("---------------");
			}
			rs.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
