package it.beije.suormary.web.sala;

	import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
	import javax.persistence.Transient;


	@Entity
	@Table(name = "rubrica")
	public class Contatto{
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name = "id")
		private int id;
		
		@Transient
		private List<ContactDetail> detail;

		public List<ContactDetail> getDetail() {
			return detail;
		}

		public void setDetail(List<ContactDetail> detail) {
			//this.detail = detail;
			this.detail=detail;
		}
		@Column(name = "nome")
		private String name;
		
		@Column(name = "cognome")
		private String surname;
		
		@Column(name = "telefono")
		private String phoneNumber;
		
		@Column(name = "email")
		private String email;
		
		@Column(name = "note")
		private String note;
		
//		@Transient
//		private String note2;
		
		@Transient
		private String fullName;
		
		public Contatto(int id, String fullName) {
		    this.id = id;
		   // if(fullName.equals(" ")){
		    //	this.fullName=null;
		    //}else {
		    	//this.fullName = fullName;
		    
		//}
		    
		}
		
		public Contatto(int id, String name, String surname) {
		    this.id = id;
		   // if(name.equals(" ")) {
		    //	this.name=null;
		    //}
		    this.name = name;
		    this.surname=surname;
		    this.detail= new ArrayList<>();
		}
		
		public Contatto(String name, String surname, String phoneNumber ) {
			this.name = name;
		    this.surname=surname;
		    this.phoneNumber=phoneNumber;
		}
		public Contatto(String name, String surname, String phoneNumber, String email, String note ) {
			this.name = name;
		    this.surname=surname;
		    this.phoneNumber=phoneNumber;
		    this.email=email;
		    this.note=note;
		    this.detail= new ArrayList<>();
		}
		@Transient
		private long count;
		public Contatto(String name, String surname, String phoneNumber, String email, String note, long count) {
			this.name = name;
		    this.surname=surname;
		    this.phoneNumber=phoneNumber;
		    this.email=email;
		    this.note=note;
		    this.count=count;
		    this.detail= new ArrayList<>();
		}
		
		public Contatto() {
			this.id = id;
		    this.name = name;
		    this.surname=surname;
		    this.phoneNumber=phoneNumber;
		    this.email=email;
		    this.note=note;
		    this.detail= new ArrayList<>();
		}
		
		public void addContactDetails(ContactDetail cd) {
			detail.add(cd);
		}
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

		 public  String buildGroupString(Contatto c) {
		       return name + " " + surname + " " + phoneNumber + " " + email + " " + note;
		    }

		    public Long getOccorrenze() {
		        return count;
		    }
		
		public String toString() {
			StringBuilder builder = new StringBuilder("{ ")
					.append("id : ").append(id)
					.append(", name : ").append(name)
					.append(", surname : ").append(surname)
					.append(", phoneNumber : ").append(phoneNumber)
					.append(", email : ").append(email)
					.append(", note : ").append(note)
					.append(" }");
			
			return builder.toString();
		}
	}

