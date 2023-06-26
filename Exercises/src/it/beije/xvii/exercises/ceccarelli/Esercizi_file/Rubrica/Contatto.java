package it.beije.xvii.exercises.ceccarelli.Esercizi_file.Rubrica;

public class Contatto {
	
	private String nome;
	private String cognome;
	private String telefono;
	private String mail;
	private String note;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@Override
    public String toString() {
        return "Contatto [cognome=" + cognome + ", nome=" + nome + ", telefono=" + telefono +
                ", mail=" + mail + ", note=" + note + "]";
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
