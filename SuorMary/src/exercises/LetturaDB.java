package exercises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LetturaDB {

	public static void main(String[] args) {
		
		//Viene dichiarata una variabile di tipo Connection chiamata connection e viene inizializzata con il valore null.
				Connection connection = null;
				
				//Viene dichiarata una variabile di tipo Statement chiamata statement e viene inizializzata con il valore null.
				//Questa variabile sarà utilizzata per creare e eseguire le istruzioni SQL
				Statement statement = null;
				
				// Inizia un blocco di codice in cui vengono gestite le eccezioni potenziali.
				try {
					
					//Carica dinamicamente il driver JDBC per il database MySQL. 
					//Questo passo è necessario per poter utilizzare il driver durante l'esecuzione del programma.
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					/*
					 * Crea una connessione al database MySQL utilizzando l'URL di connessione specificato, 
					 * l'utente "root" e la password "Arlabunakti". 
					 * L'URL di connessione indica la posizione del database, 
					 * che in questo caso è "localhost" sulla porta "3306", 
					 * con il nome del database "suor_mary" e la timezone "CET".
					 */
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/suor_mary?serverTimezone=CET", "root", "Arlabunakti");
					
					/*
					 * Crea un oggetto Statement dalla connessione, che verrà utilizzato per eseguire istruzioni SQL sul database. 
					 */
					statement = connection.createStatement();
					
					/*
					 * Stampa un messaggio che indica se la connessione al database è aperta o chiusa. 
					 * La condizione !connection.isClosed() restituisce true se la connessione è aperta.
					 */
					System.out.println("connection open? " + !connection.isClosed());
					
					/*
					 * Esegue una query SQL per selezionare tutti i record dalla tabella "rubrica" nel database. 
					 * Il risultato della query viene memorizzato in un oggetto ResultSet chiamato rs.
					 */
					//SELECT
					ResultSet rs = statement.executeQuery("SELECT * FROM rubrica");
					
					/*
					 * Inizia un ciclo while che itera su ciascun record nel ResultSet. 
					 */
					while (rs.next()) {
						/*All'interno del ciclo, il codice recupera i valori dal record corrente 
						 * utilizzando indici di colonna (rs.getInt(1)) o nomi di colonna (rs.getString("nome")).*/
						
//						System.out.println("id : " + rs.getInt(1));
//						System.out.println("nome : " + rs.getString(2));
//						System.out.println("cognome : " + rs.getString(3));
//						System.out.println("telefono : " + rs.getString(4));
//						System.out.println("email : " + rs.getString(5));
//						System.out.println("note : " + rs.getString(6));
						
						/*
						 * Stampa il valore del campo "id" del record corrente nel ResultSet.
						 */
						System.out.println("id : " + rs.getInt("id"));
						System.out.println("note : " + rs.getString("note"));
						System.out.println("nome : " + rs.getString("nome"));
						System.out.println("cognome : " + rs.getString("cognome"));
						System.out.println("telefono : " + rs.getString("telefono"));
						System.out.println("email : " + rs.getString("email"));
						
						//Stampa una linea di separazione per distinguere i record nel risultato.
						System.out.println("---------------");
					}
					/*
					 * chiude l'oggetto ResultSet una volta completato il ciclo.
					 */
					rs.close();
					
				/*
				 * Il codice include blocchi catch per gestire le eccezioni ClassNotFoundException e 
				 * SQLException nel caso si verifichino durante l'esecuzione del programma.
				 */
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally { 
					try {
						/*
						 * gli oggetti statement e connection vengono chiusi per rilasciare le risorse in loro possesso.
						 */
						statement.close();
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
	}
	
}
