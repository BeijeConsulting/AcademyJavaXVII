package it.beije.suormary.rubrica.exercises;

public class Contact {
	
	private String id;
	private String name;
	private String surname;
	private String phoneNumber;
	private String email;
	private String note;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id.trim();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name.trim();
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname.trim();
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber.trim();
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email.trim();
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note.trim();
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
	
	@Override
	public boolean equals(Object obj) {
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
	    } else if (!this.getName().equals(other.name)) {
	        return false;
	    }

	    if (this.getSurname() == null) {
	        if (other.getSurname() != null) {
	            return false;
	        }
	    } else if (!this.getSurname().equals(other.getSurname())) {
	        return false;
	    }

	    if (this.getPhoneNumber() == null) {
	        if (other.getPhoneNumber() != null) {
	            return false;
	        }
	    } else if (!this.getPhoneNumber().equals(other.getPhoneNumber())) {
	        return false;
	    }

	    if (this.getEmail() == null) {
	        if (other.getEmail() != null) {
	            return false;
	        }
	    } else if (!this.getEmail().equals(other.getEmail())) {
	        return false;
	    }

	    if (this.getNote() == null) {
	        if (other.getNote() != null) {
	            return false;
	        }
	    } else if (!this.getNote().equals(other.getNote())) {
	        return false;
	    }

	    return true;
	}
	
	

}
