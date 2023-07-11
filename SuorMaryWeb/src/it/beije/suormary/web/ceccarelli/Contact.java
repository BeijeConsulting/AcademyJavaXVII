package it.beije.suormary.web.ceccarelli;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "rubrica")
public class Contact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "nome")
	private String name;
	
	@Column(name = "cognome")
	private String surname;
	
	@Column(name = "telefono")
	private String phoneNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "note")
	private String note;
	//private String id;
	
//	@Transient
//	private String note2;
	public Contact() {}
	
	public Contact( String name, String surname, String phoneNumber, String email, String note) {
		super();
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.note = note;
	}
	
	
	public Contact(int id, String name, String surname, String phoneNumber, String email, String note) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.note = note;
	}

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
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
//	public String getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("{ ")
				.append("id : ").append(id)
				.append(", name : ").append(name)
				.append(", surname : ").append(surname)
				.append(", phoneNumber : ").append(phoneNumber)
				.append(", email : ").append(email)
				.append(", note : ").append(note)
				.append(" }");
		
		return builder.toString();
	}
	
//	//confronto due contatti
//	public boolean equals(Object obj) {
//		if(this == obj) {
//			return true;
//		}
//		if(obj==null) {
//			return false;
//		}
//		Contact other = (Contact) obj;
//		if(
//				this.getName().equals(other.getName()) &&
//				this.getSurname().equals(other.getSurname()) &&
//				this.getPhoneNumber().equals(getPhoneNumber()) &&
//				this.getEmail().equals(other.getEmail()) &&
//				this.getNote().equals(other.getNote())){
//			return true;
//		}
//		return false;
//	}
}
