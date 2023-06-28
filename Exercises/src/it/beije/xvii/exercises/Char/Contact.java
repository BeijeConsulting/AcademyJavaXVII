package it.beije.xvii.exercises.Char;

public class Contact {
       private String name;
       private String surname;
       private String email;
       private String phoneNumber;
       private String note;
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
		return "Nome : " + getName() + " - " + "Cognome : " + getSurname();
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
