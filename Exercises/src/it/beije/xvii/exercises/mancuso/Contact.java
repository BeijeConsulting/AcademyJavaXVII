package it.beije.xvii.exercises.mancuso;

public class Contact {
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String notes;
	
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
	
	public String toString() {
		String myString = "First Name: " + this.getFirstName() + "\n";
		myString += "Last Name: " + this.getLastName() + "\n";
		myString += "Email: " + this.getEmail() + "\n";
		myString += "Phone Number: " + this.getPhoneNumber() + "\n";
		myString += "Notes: " + this.getNotes() + "\n";
		
		return myString;
	}
	
}
