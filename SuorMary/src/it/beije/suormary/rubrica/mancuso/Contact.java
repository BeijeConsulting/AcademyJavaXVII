package it.beije.suormary.rubrica.mancuso;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rubrica")
public class Contact{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="nome")
	private String firstName;
	
	@Column(name="cognome")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telefono")
	private String phoneNumber;
	
	@Column(name="note")
	private String notes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Contact() {}
	
	public Contact(String name, String surname, String phone, String email, String notes) {
		this.firstName = name;
		this.lastName = surname;
		this.phoneNumber = phone;
		this.email = email;
		this.notes = notes;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("First Name : ").append(this.getFirstName()).append("\n");
		builder.append("Last Name : ").append(this.getLastName()).append("\n");
		builder.append("Email : ").append(this.getEmail()).append("\n");
		builder.append("Phone Number : ").append(this.getPhoneNumber()).append("\n");
		builder.append("Notes : ").append(this.getNotes()).append("\n");
		
		return builder.toString();
	}
	
	public boolean equals(Contact c) {
		if (this.getFirstName().equals(c.getFirstName())) {
			if (this.getLastName().equals(c.getLastName())) {
				if (this.getPhoneNumber().equals(c.getPhoneNumber())) {
					if (this.getEmail().equals(c.getEmail())) {	
						return true;		
					}
				}
			}
		}
		return false;
	}
	
	public int compareToByName(Contact c) {
		return this.getFirstName().compareTo(c.getFirstName());
	}
	
	public int compareToBySurname(Contact c) {
		return this.getFirstName().compareTo(c.getFirstName());
	}
	
}
