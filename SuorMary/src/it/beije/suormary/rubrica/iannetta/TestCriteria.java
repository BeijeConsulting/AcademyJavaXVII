package it.beije.suormary.rubrica.iannetta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class TestCriteria {

	public static void main(String[] args) {
		EntityManager entityManager = Singleton.getInstance();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		//SELECT
		
//		CriteriaQuery<Contact> cr = cb.createQuery(Contact.class);
//		Root<Contact> root = cr.from(Contact.class);
//		String answer = "%ar%";
//		cr.select(root).where(cb.like(root.get("name"), answer));
//		Query query = entityManager.createQuery(cr);
//		List<Contact> results = query.getResultList();
//		for (Contact c : results) System.out.println(c);
//		transaction.commit();
		
		//UPDATE
//		CriteriaUpdate<Contact> cu = cb.createCriteriaUpdate(Contact.class);
//		Root<Contact> root = cu.from(Contact.class);
//		cu.set("note", "nnnn");
//		cu.where(cb.equal(root.get("note"), "prova"));
//		
//		//Query query = entityManager.createQuery(cu);
//		//query.executeUpdate(); 
//		entityManager.createQuery(cu).executeUpdate(); 
//		//questi qui non funzionano, non li puoi usare
////		List<Contact> results = query.getResultList();
////		for (Contact c : results) System.out.println(c);
//		transaction.commit();
		
//		//DELETE
//		CriteriaDelete<Contact> cd = cb.createCriteriaDelete(Contact.class);
//		Root<Contact> root = cd.from(Contact.class);
//		int id = 5;
//		cd.where(cb.greaterThan(root.get("id"), id));
//
//		entityManager.createQuery(cd).executeUpdate();
//		transaction.commit();
		
		
//		//test multiple like()
//		CriteriaQuery<Contact> cr = cb.createQuery(Contact.class);
//		Root<Contact> root = cr.from(Contact.class);
//		String answer = "%ar%";
//		Predicate p = cb.or(cb.like(root.get("name"), answer), cb.like(root.get("surname"), answer),
//							cb.like(root.get("phoneNumber"), answer), cb.like(root.get("email"), answer),
//							cb.like(root.get("note"), answer));
//		cr.select(root).where(p);
//		
//		Query query = entityManager.createQuery(cr);
//		List<Contact> contacts = query.getResultList();
//		for (Contact c : contacts) System.out.println(c);
		
		
		
		//test multiple set
		CriteriaUpdate<Contact> cu = cb.createCriteriaUpdate(Contact.class);
		Root<Contact> root = cu.from(Contact.class);
		String[] updateContact = {null, null, null, null, null, "testa"};
		//if (true) cu.set("note", "aa");
		int id = 61;
		if (updateContact[1] != null) cu.set("name", updateContact[1]);
		if (updateContact[2] != null) cu.set("surname", updateContact[2]);
		if (updateContact[3] != null) cu.set("phoneNumber", updateContact[3]);
		if (updateContact[4] != null) cu.set("email", updateContact[4]);
		if (updateContact[5] != null) cu.set("note", updateContact[5]);

		cu.where(cb.equal(root.get("id"), id));
		entityManager.createQuery(cu).executeUpdate(); 
		transaction.commit();
		
		CriteriaQuery<Contact> cr = cb.createQuery(Contact.class);
		root = cr.from(Contact.class);
		cr.select(root);
		Query query = entityManager.createQuery(cr);
		List<Contact> contacts = query.getResultList();
		for (Contact c : contacts) System.out.println(c);
	}

}
