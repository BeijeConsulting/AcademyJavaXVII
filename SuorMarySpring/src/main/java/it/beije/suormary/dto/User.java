package it.beije.suormary.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	
	private String email;
	private String name;
	private String surname;
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFullName() {
		return this.name + " " + this.surname;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("{ ")
				.append("email : ").append(email)
				.append(", name : ").append(name)
				.append(", surname : ").append(surname)
				.append(", password : ").append(password)
				.append(" }");
		
		return builder.toString();
	}	

}
