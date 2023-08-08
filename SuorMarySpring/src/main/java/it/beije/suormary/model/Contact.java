//
//package it.beije.suormary.model;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonInclude.Include;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//
//@JsonInclude(Include.NON_NULL)
//@Entity
//@Table(name = "rubrica")
//public class Contact {
//	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name = "id")
//	private Integer id;
//
//	@Column(name = "nome")
//	private String name;
//	
//	@Column(name = "cognome")
//	private String surname;
//	
////	@Column(name = "telefono")
////	private String phoneNumber;
//	
////	@Column(name = "email")
////	private String email;
//	
//	@Column(name = "note")
//	private String note;
//	
//	@JsonProperty(value = "birth_date")
//	@Column(name = "data_nascita")
//	private LocalDate birthDate;
//	
//	
//	//@OneToMany(targetEntity = ContactDetail.class, fetch = FetchType.LAZY)
//	@OneToMany(targetEntity = ContactDetail.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//	@JoinColumn(name = "id_rubrica")
//	private List<ContactDetail> details;
//	
//	
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	public String getSurname() {
//		return surname;
//	}
//	public void setSurname(String surname) {
//		this.surname = surname;
//	}
//		
//	public String getNote() {
//		return note;
//	}
//	public void setNote(String note) {
//		this.note = note;
//	}
//
//	
//	public List<ContactDetail> getDetails() {
//		return details;
//	}
//	
//	public void setDetails(List<ContactDetail> details) {
//		this.details = details;
//	}
//	
//	public LocalDate getBirthDate() {
//		return birthDate;
//	}
//	
//	@JsonProperty(value = "birth_date")
//	public String getBirthDateAsString() {
//		return birthDate != null ? birthDate.toString() : null;
//	}
//	
//	public void setBirthDate(LocalDate birthDate) {
//		this.birthDate = birthDate;
//	}
//	
//	@JsonProperty(value = "birth_date")
//	public void setBirthDate(String birthDate) {
//		this.birthDate =  LocalDate.parse(birthDate, DateTimeFormatter.ISO_LOCAL_DATE);
//	}
//
//	
//	public String toString() {
//		StringBuilder builder = new StringBuilder("{ ")
//				.append("id : ").append(id)
//				.append(", name : ").append(name)
//				.append(", surname : ").append(surname)
////				.append(", phoneNumber : ").append(phoneNumber)
////				.append(", email : ").append(email)
//				.append(", note : ").append(note)
//				.append(", birthDate : ").append(birthDate)
//				.append(", details : ").append(details)
//				.append(" }");
//		
//		return builder.toString();
//	}
//}
//
