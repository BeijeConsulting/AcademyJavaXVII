package it.beije.suormary.bookstore1.dto;

public class UserCredential {
	private String email;
	private String password;
	
	public UserCredential() {
		
	}
	
	public UserCredential(String email, String password) {
		setEmail(email);
		setPassword(password);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
