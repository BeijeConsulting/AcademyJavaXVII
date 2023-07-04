package it.beije.suormary.rubrica.Char;

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
	@Column
	private int id;
	@Column
	private String name;
	@Column
    private String surname;
    @Column
    private String email;
    @Column(name = "phone")
    private String phoneNumber;
    @Column
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
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	public String toString() {
		return "Nome : " + getName() + " - " + "Cognome : " + getSurname() + " - " + "Email : " + getEmail() + " - " + "Telefono : " + getPhoneNumber() + " - " + "Note : " + getNote();
	}
	public boolean equals(Contact c2) {
		if(this.getName().equals(c2.getName())&& 
		   this.getSurname().equals(c2.getSurname()) && 
		   this.getEmail().equals(c2.getEmail()) && 
		   this.getPhoneNumber().equals(c2.getPhoneNumber())&&
		   this.getNote().equals(c2.getNote())) return true;
		else return false;
	    
	}
}