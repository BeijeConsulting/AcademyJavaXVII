package web.rubrica.caroselli;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;



public class RubricaUtilsJPA {

	public static List<Contact> readContactsFromDb() {

		List<Contact> newList = new ArrayList<>();
		EntityTransaction transaction = null;
		EntityManager entityManager = null;

			try {

				entityManager = PersistenceManagerJPA.getEntityManager();
				transaction = entityManager.getTransaction();
				transaction.begin();
				Query query = entityManager.createQuery("SELECT c FROM Contact as c");
				newList = query.getResultList();
				for (Contact c : newList) {
					System.out.println(c.toString());
					List<ContactDetail> cd = new ArrayList<>();
					query = entityManager.createQuery("SELECT cd FROM ContactDetail as cd WHERE id_contact = :contact");
					query.setParameter("contact", c.getId());

					c.setDetails(cd);
				}

				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			} finally {
				entityManager.close();
			}


		return newList;
	}


	public static Contact insertContact(HttpServletRequest request) {

	    EntityManager entityManager = null;

	    String name = request.getParameter("name");
	    String surname = request.getParameter("surname");
	    String phone = request.getParameter("phone");
	    String email = request.getParameter("email");
	    String note = request.getParameter("note");

	    Contact contact = new Contact(name, surname, note);

	    try {
	        entityManager = PersistenceManagerJPA.getEntityManager();
	        EntityTransaction transaction = entityManager.getTransaction();
	        transaction.begin();
	        entityManager.persist(contact);
	        transaction.commit();
	        System.out.println("Contatto aggiunto alla rubrica");
	        System.out.println(contact.toString());
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        entityManager.close();
	    }

	    return contact;
	}


}
