package it.beije.suormary.bookstore4.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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

@Entity
@Table(name = "users")
public class User {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "create_date")
	private LocalDateTime creationDate;
	
//	@Transient
//	private List<OrderItem> basket;

	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
//	public List<OrderItem> getBasket() {
//		return basket;
//	}
//	public void setBasket(List<OrderItem> basket) {
//		this.basket = basket;
//	}
	
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
