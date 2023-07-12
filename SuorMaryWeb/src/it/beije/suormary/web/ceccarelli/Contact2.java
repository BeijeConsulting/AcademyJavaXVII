package it.beije.suormary.web.ceccarelli;

import java.util.List;

public class Contact2 {
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name = "id")
	private int id;

//	@Column(name = "nome")
	private String name;
	
//	@Column(name = "cognome")
	private String surname;
	
//	@Column(name = "note")
	private String note;
	
//	@Transient
	private ContactDetail details;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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

	
	public ContactDetail getDetails() {
		return details;
	}
	public void setDetails(ContactDetail details) {
		this.details = details;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder("{ ")
				.append("id : ").append(id)
				.append(", name : ").append(name)
				.append(", surname : ").append(surname)
				.append(", note : ").append(note)
				.append(", details : ").append(details)
				.append(" }");
		
		return builder.toString();
	}
}
