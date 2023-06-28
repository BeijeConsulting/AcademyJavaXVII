package it.beije.xvii.exercises.iannetta;

public class Contact {
	
	private int id;
	private String name;
	private String surname;
	private String phoneNumber;
	private String email;
	private String note;
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
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
		StringBuilder builder = new StringBuilder("")
				.append("Name: ").append(name)
				.append("\nSurname: ").append(surname)
				.append("\nPhone number: ").append(phoneNumber)
				.append("\nEmail: ").append(email)
				.append("\nNote: ").append(note);
		return builder.toString();
	}
	
	public String toString(boolean showID) {
		if(!showID) return toString();
		StringBuilder builder = new StringBuilder("")
				.append("ID: ").append(id)
				.append("\nName: ").append(name)
				.append("\nSurname: ").append(surname)
				.append("\nPhone number: ").append(phoneNumber)
				.append("\nEmail: ").append(email)
				.append("\nNote: ").append(note);
		return builder.toString();
	}
}
