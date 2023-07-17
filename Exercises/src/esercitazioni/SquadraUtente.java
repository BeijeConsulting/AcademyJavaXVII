package esercitazioni;

public class SquadraUtente {
	private String name;
	private String surname;
	private String squadra;
	
	public SquadraUtente(String name, String surname, String squadra) {
		setName(name);
		setSurname(surname);
		setSquadra(squadra);
	}
	
	public String getSquadra() {
		return squadra;
	}

	public void setSquadra(String squadra) {
		this.squadra = squadra;
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name: "+name);
		sb.append(" ");
		sb.append("surname: "+surname);
		sb.append(" ");
		sb.append("squadra: "+squadra);
		return sb.toString();
	}

}
