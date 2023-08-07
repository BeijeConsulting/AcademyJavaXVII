package it.beije.suormary.model;


import java.util.List;


public class ContactDTO {

	private Integer id;

	private String name;

	private String surname;

	private String note;

	private List<ContactDetail> details;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	
	public List<ContactDetail> getDetails() {
		return details;
	}
	
	public void setDetails(List<ContactDetail> details) {
		this.details = details;
	}
	
	
	public String toString() {
		StringBuilder builder = new StringBuilder("{ ")
				.append("id : ").append(id)
				.append(", name : ").append(name)
				.append(", surname : ").append(surname)
//				.append(", phoneNumber : ").append(phoneNumber)
//				.append(", email : ").append(email)
				.append(", note : ").append(note)
				.append(", details : ").append(details)
				.append(" }");
		
		return builder.toString();
	}
}