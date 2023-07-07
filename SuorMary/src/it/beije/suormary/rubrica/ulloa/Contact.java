package it.beije.suormary.rubrica.ulloa;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rubrica")
public class Contact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name == null ? "" : name;
	}
	public void setName(String name) {
		if (name == null) {
			name = "";
		} else {
			this.name = name.trim();
		}
	}
	
	public String getSurname() {
		return surname == null ? "" : surname;
	}
	public void setSurname(String surname) {
		if (surname == null) {
			surname = "";
		} else {
		this.surname = surname.trim();
		}
	}
	
	public String getPhoneNumber() {
		return phoneNumber == null ? "" : phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		if (phoneNumber == null) {
			phoneNumber = "";
		} else {
			this.phoneNumber = phoneNumber.trim();
		}
	}
	
	public String getEmail() {
		return email == null ? "" : email;
	}
	public void setEmail(String email) {
		if (email == null) {
			email = "";
		} else {
			this.email = email.trim();
		}
	}
	
	public String getNote() {
		return note == null ? "" : email;
	}
	public void setNote(String note) {
		if (note == null) {
			note = "";
		} else {
		this.note = note.trim();
		}
	}
	
	public String toStringExcludingId() {
		StringBuilder builder = new StringBuilder("{ ")
				.append("name : ").append(this.getName().toLowerCase())
				.append(", surname : ").append(this.getSurname().toLowerCase())
				.append(", phoneNumber : ").append(this.getPhoneNumber().toLowerCase())
				.append(", email : ").append(this.getEmail().toLowerCase())
				.append(", note : ").append(this.getNote().toLowerCase())
				.append(" }");
		
		return builder.toString();
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("{ ")
				.append("id : ").append(id)
				.append(", name : ").append(this.getName())
				.append(", surname : ").append(this.getSurname())
				.append(", phoneNumber : ").append(this.getPhoneNumber())
				.append(", email : ").append(this.getEmail())
				.append(", note : ").append(this.getNote())
				.append(" }");
		
		return builder.toString();
	}
	
	public boolean equalsExcludingId(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    Contact other = (Contact) obj;

	    if (this.getName() == null) {
	        if (other.getName() != null) {
	            return false;
	        }
	    } else if (!this.getName().equalsIgnoreCase(other.name)) {
	        return false;
	    }

	    if (this.getSurname() == null) {
	        if (other.getSurname() != null) {
	            return false;
	        }
	    } else if (!this.getSurname().equalsIgnoreCase(other.getSurname())) {
	        return false;
	    }

	    if (this.getPhoneNumber() == null) {
	        if (other.getPhoneNumber() != null) {
	            return false;
	        }
	    } else if (!this.getPhoneNumber().equalsIgnoreCase(other.getPhoneNumber())) {
	        return false;
	    }

	    if (this.getEmail() == null) {
	        if (other.getEmail() != null) {
	            return false;
	        }
	    } else if (!this.getEmail().equalsIgnoreCase(other.getEmail())) {
	        return false;
	    }

	    if (this.getNote() == null) {
	        if (other.getNote() != null) {
	            return false;
	        }
	    } else if (!this.getNote().equalsIgnoreCase(other.getNote())) {
	        return false;
	    }

	    return true;
	}
	
	public boolean contains(Contact other) {
	    if (other == null) {
	        return false;
	    }

	    // Verifica se i campi (tranne l'ID) sono uguali
	    if (this.getName().equalsIgnoreCase(other.getName()) 
	    		&& this.getSurname().equalsIgnoreCase(other.getSurname()) 
	    		&& this.getEmail().equalsIgnoreCase(other.getEmail()) 
	    		&& this.getPhoneNumber().equalsIgnoreCase(other.getPhoneNumber())
	    		&& this.getNote().equalsIgnoreCase(other.getNote())) {
	        return true;
	    }

	    return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return this.getName().equalsIgnoreCase(other.getName()) 
	    		&& this.getSurname().equalsIgnoreCase(other.getSurname()) 
	    		&& this.getEmail().equalsIgnoreCase(other.getEmail()) 
	    		&& this.getPhoneNumber().equalsIgnoreCase(other.getPhoneNumber())
	    		&& this.getNote().equalsIgnoreCase(other.getNote());
	}	

}
