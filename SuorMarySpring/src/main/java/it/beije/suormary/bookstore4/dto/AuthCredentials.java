package it.beije.suormary.bookstore4.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AuthCredentials implements Serializable {
	
	private static final long serialVersionUID = -2965313686186766776L;

	private String email;
    private String password;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "AuthCredentials [email=" + email + ", password=" + password + "]";
	}
	
	
}
