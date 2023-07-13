package web.rubrica.caroselli;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "rubrica")
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "note")
	private String note;

	@Transient
	private List<ContactDetail> details;

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

	public List<ContactDetail> getDetails() {
		return details;
	}
	public void setDetails(List<ContactDetail> details) {
		this.details = details;
	}
	public Contact() {

	}

	public Contact(String name, String surname, String note) {
		super();
		this.name = name;
		this.surname = surname;
		this.note = note;
	}

	public Contact(int id, String name, String surname, String note) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.note = note;
	}




	@Override
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
