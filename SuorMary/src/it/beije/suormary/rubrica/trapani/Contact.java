package it.beije.suormary.rubrica.trapani;



public class Contact {

	private int id;
	private String name;
	private String surname;
	private String phoneNumber;
	private String email;
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
	
	//nei bin conviene mettere metodo toString() per stampare tutto il bin

	
	public String toString() {
		StringBuilder builder = new StringBuilder("{ ")
				.append("id: ").append(id)
				.append(", name: ").append(name)
				.append(", surname: ").append(surname)
				.append(", phoneNumber: ").append(phoneNumber)
				.append(", email: ").append(email)
				.append(", note: ").append(note)
				.append(" }");
		
		return builder.toString();
	}
	public String toStringDup() {
		StringBuilder builder = new StringBuilder("{ ")
				.append("name: ").append(name)
				.append(", surname: ").append(surname)
				.append(", phoneNumber: ").append(phoneNumber)
				.append(", email: ").append(email)
				.append(" }");
		
		return builder.toString();
	}

}