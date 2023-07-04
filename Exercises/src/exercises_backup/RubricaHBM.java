package exercises_backup;
/*
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class RubricaHBM {

	public static void main(String[] args) {
		
		
		//Inizializza un oggetto Configuration, che è una classe fornita da Hibernate per configurare l'ambiente di persistenza. 
		//Il metodo configure() viene chiamato per caricare la configurazione predefinita di Hibernate dal file hibernate.cfg.xml. 
		//Questo file di configurazione contiene informazioni come il driver del database, l'URL del database, le credenziali di 
		//accesso, ecc.
		//addAnnotatedClass() Aggiunge una classe di entità Contact alla configurazione di Hibernate. Le classi di entità sono classi 
		//che rappresentano le tabelle del database e contengono i dati che desideri persistere.
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contact.class);
		
		//Crea un oggetto SessionFactory che rappresenta una fabbrica per le sessioni di Hibernate. La sessione è un'interfaccia tra 
		//l'applicazione e il database ed è utilizzata per interagire con il database e persistere/leggere i dati.
		SessionFactory factory = configuration.buildSessionFactory();
		
		//Crea una variabile session di tipo Session. Questa variabile sarà utilizzata successivamente per aprire una sessione di 
		//lavoro con il database.
		Session session = null;
		
		//Queste linee di codice costituiscono un punto di partenza per l'utilizzo di Hibernate in un'applicazione Java. Una volta 
		//che hai ottenuto una sessione, puoi utilizzare i metodi della sessione per eseguire operazioni come il salvataggio, 
		//l'aggiornamento, la cancellazione e la query dei dati nel database utilizzando oggetti di entità.
		
		try {
			
			//Questa riga apre una sessione di lavoro utilizzando l'oggetto SessionFactory creato in precedenza. Una sessione di 
			//lavoro è una connessione logica tra l'applicazione e il database, che consente di interagire con il database 
			//utilizzando le funzionalità fornite da Hibernate. La chiamata al metodo openSession() restituisce un oggetto Session 
			//che rappresenta la sessione di lavoro appena aperta.
			session = factory.openSession();
			
			
			//Qui viene ottenuto un oggetto Transaction dalla sessione di lavoro. La transazione rappresenta un'unità di lavoro 
			//che comprende una o più operazioni sul database. Ad esempio, quando si desidera eseguire una serie di operazioni 
			//di inserimento o aggiornamento dati nel database, è possibile raggrupparle all'interno di una transazione. In 
			//caso di errore o eccezione, la transazione può essere annullata per ripristinare lo stato del database.
//			Transaction transaction = session.getTransaction();
 
 			//Questa riga avvia la transazione. Il metodo begin() indica all'oggetto Transaction che le operazioni successive 
 			//eseguite sulla sessione di lavoro devono essere incluse all'interno della transazione corrente
//			transaction.begin();
			
			
			//Questa chiamata inizia una nuova transazione sulla sessione di lavoro e 
			//restituisce un oggetto Transaction che può essere utilizzato per eseguire operazioni all'interno della 
			//transazione, come il commit o il rollback delle modifiche effettuate sul database. MODO ALTERNATIVO ALLE DUE RIGHE 
			//SOPRA
			Transaction transaction = session.beginTransaction();
			
			
			Contact contact = null;
			
			//INSERT
//			contact = new Contact();
//			//contact.setId(20);
//			contact.setName("Pippo");
//			contact.setSurname("Gialli");
//			contact.setPhoneNumber("09876543");
//			contact.setEmail("Pippo@beije.it");
//			contact.setNote("contatto inserito con Hibernate");
//			
//			System.out.println("contact PRE : " + contact);
//			session.save(contact);
//			System.out.println("contact POST : " + contact);
			
//			transaction.commit();
		

			//SELECT HQL
			Query<Contact> query = session.createQuery("SELECT c FROM Contact as c"); //SELECT * FROM rubrica
			List<Contact> contacts = query.getResultList();
			for (Contact c : contacts) System.out.println(c);

			//UPDATE
			contact = contacts.get(contacts.size()-1);
			System.out.println("contact PRE UPDATE: " + contact);
//			contact.setId(10);
//			contact.setName("Chiara");
//			contact.setSurname("Sala");
//			contact.setPhoneNumber("09876543");
//			contact.setEmail("lara.sala@beije.it");
//			contact.setNote("contatto modificato con Hibernate");
//			
//			System.out.println("contact PRE : " + contact);
//			session.save(contact);
//			System.out.println("contact POST : " + contact);
			
			
			//DELETE
//			session.delete(contact);
			
			transaction.commit();
			//transaction.rollback();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}
}
*/