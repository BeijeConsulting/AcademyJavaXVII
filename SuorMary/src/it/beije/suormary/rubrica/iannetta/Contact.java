package it.beije.suormary.rubrica.iannetta;

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

	
	public String toString() {
		StringBuilder builder = new StringBuilder("{")
				.append("id: ").append(id)
				.append(" Name: ").append(name)
				.append(" Surname: ").append(surname)
				.append(" Phone number: ").append(phoneNumber)
				.append(" Email: ").append(email)
				.append(" Note: ").append(note).append("}");
		return builder.toString();
	}
	
}
