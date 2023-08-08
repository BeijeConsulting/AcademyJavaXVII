package it.beije.suormary;

import java.util.List;

import it.beije.suormary.model.Contact;
import it.beije.suormary.model.ContactDetail;

public class ContactWithDetailsRequest {
	 private Contact contact;
	 private List<ContactDetail> details;
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public List<ContactDetail> getDetails() {
		return details;
	}
	public void setDetails(List<ContactDetail> details) {
		this.details = details;
	}
	 
	 
}
