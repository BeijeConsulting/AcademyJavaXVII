package it.beije.suormary.bookstore1.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;



/*
 
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB

*/

//@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "users")
public class User {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@JsonProperty(value="creationDate")
	@Column(name = "create_date")
	private LocalDateTime creationDate;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

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
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
	@JsonProperty(value="creationDate")
	public String getCrationDateAsString() {
		return creationDate!=null ? creationDate.toString() : null;
	}
	
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
	@JsonProperty(value="creationDate")
	public void setCreationDate(String creationDate) {
		this.creationDate=LocalDateTime.parse(creationDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}
	
	public User() {}
	
	public User(String email, String password, String name, String surname) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.creationDate = LocalDateTime.now();
	}
	
	public User(String email, String password, String name, String surname, LocalDateTime ldt) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.creationDate = ldt;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("{ ")
				.append("id : ").append(id)
				.append(", email : ").append(email)
				.append(", name : ").append(name)
				.append(", surname : ").append(surname)
				.append(", creationDate : ").append(creationDate)
				.append(" }");
		
		return builder.toString();
	}	

}
